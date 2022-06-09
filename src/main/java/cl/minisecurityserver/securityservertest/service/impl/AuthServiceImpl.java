package cl.minisecurityserver.securityservertest.service.impl;

import cl.minisecurityserver.securityservertest.dao.entity.Privilege;
import cl.minisecurityserver.securityservertest.dao.entity.Role;
import cl.minisecurityserver.securityservertest.dao.entity.User;
import cl.minisecurityserver.securityservertest.dao.repository.UserRepository;
import cl.minisecurityserver.securityservertest.dto.AuthRequestDto;
import cl.minisecurityserver.securityservertest.dto.AuthResponseDto;
import cl.minisecurityserver.securityservertest.dto.ChangePasswordRequestDto;
import cl.minisecurityserver.securityservertest.dto.ChangePasswordResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.security.JWTCreator;
import cl.minisecurityserver.securityservertest.security.PasswordUtil;
import cl.minisecurityserver.securityservertest.service.IAuthService;
import cl.minisecurityserver.securityservertest.util.Constant;
import cl.minisecurityserver.securityservertest.util.UserMapper;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

  private final UserRepository repository;
  private final String secret;
  private final String expirationToken;
  private final String STATUS;

  @Autowired
  public AuthServiceImpl(
      UserRepository repositoryInterface,
      @Value("${values.security.hash}") String secret,
      @Value("${values.security.expitarionToken}") String expirationToken) {
    this.repository = repositoryInterface;
    this.secret = secret;
    this.expirationToken = expirationToken;
    STATUS = "active";
  }

  @Override
  public AuthResponseDto login(AuthRequestDto authRequestDto) throws SecurityServerException {
    Optional<User> loggedUser = itsLogged(authRequestDto);
    if (loggedUser.isEmpty()) {
      throw new SecurityServerException(
          Constant.GENERIC_ERROR,
          Constant.ERROR,
          Constant.USER_UNAUTHORIZED,
          HttpStatus.UNAUTHORIZED);
    }
    User user = loggedUser.get();

    Map<String, Object> header = new HashMap<>();
    header.put("alg", "HS256");
    header.put("typ", "JWT");

    String token =
        JWTCreator.init()
            .withHeader(header)
            .withIssuer("jcqs")
            .withClaim("id", user.getId().getId())
            .withClaim("dni", user.getDni())
            .withClaim("name", user.getName())
            .withClaim("prefix", user.getPrefix())
            .withExpiresAt(getExpitationDate())
            .withArrayClaim("authorities", getAuthorities(user.getProfile().getRoles()))
            .sign(Algorithm.HMAC256(secret));
    return UserMapper.buildAuthResponseDto(user, token);
  }

  private List<String> getAuthorities(List<Role> roles) {
    List<String> authorities = new ArrayList<>();
    roles.forEach(
        role -> {
          List<Privilege> privileges = role.getPrivileges();
          for (Privilege privilege : privileges) {
            authorities.add(role.getKey().concat("-").concat(privilege.getKey()));
          }
        });
    return authorities;
  }

  @Override
  public ChangePasswordResponseDto changePassword(
      ChangePasswordRequestDto changePasswordRequestDto) {
    Optional<User> loggedUser = itsLogged(changePasswordRequestDto);
    if (loggedUser.isEmpty()) {
      return UserMapper.buildChangePasswordResponseDto(Constant.ERROR, Constant.USER_UNAUTHORIZED);
    }
    var userToSave =
        UserMapper.buildChangePassword(changePasswordRequestDto.getNewPassword(), loggedUser.get());
    repository.save(userToSave);

    return UserMapper.buildChangePasswordResponseDto(Constant.OK, Constant.UPDATED_PASSWORD);
  }

  private Date getExpitationDate() {
    LocalDateTime timeExp = LocalDateTime.now();
    LocalDateTime plus = timeExp.plus(Long.parseLong(expirationToken), ChronoUnit.MINUTES);
    return Date.from(plus.atZone(ZoneId.systemDefault()).toInstant());
  }

  private Optional<User> itsLogged(AuthRequestDto authRequestDto) {
    Optional<User> userExist = repository.findByDni(authRequestDto.getDni());
    if (userExist.isPresent()
        && PasswordUtil.checkPassword(
            authRequestDto.getPassword(), userExist.get().getPassword())) {
      return userExist;
    }
    return Optional.empty();
  }

  private Optional<User> itsLogged(ChangePasswordRequestDto authRequestDto) {
    Optional<User> userExist = repository.findByDniAndStatusIsTrue(authRequestDto.getDni());
    if (userExist.isPresent()
        && PasswordUtil.checkPassword(
            authRequestDto.getPassword(), userExist.get().getPassword())) {
      return userExist;
    }
    return Optional.empty();
  }
}
