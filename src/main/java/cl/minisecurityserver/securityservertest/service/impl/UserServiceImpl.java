package cl.minisecurityserver.securityservertest.service.impl;

import static org.springframework.data.jpa.domain.Specification.where;

import cl.minisecurityserver.securityservertest.dao.entity.User;
import cl.minisecurityserver.securityservertest.dao.repository.UserRepository;
import cl.minisecurityserver.securityservertest.dto.UserRequestDto;
import cl.minisecurityserver.securityservertest.dto.UserResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IUserApplicationService;
import cl.minisecurityserver.securityservertest.specification.UserSpecification;
import cl.minisecurityserver.securityservertest.util.Constant;
import cl.minisecurityserver.securityservertest.util.UserMapper;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserApplicationService {

  private final UserRepository repository;

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserResponseDto newUser(UserRequestDto userRequestDto) throws SecurityServerException {
    Optional<User> optionalUser = repository.findByDni(userRequestDto.getDni());
    if (optionalUser.isPresent()) {
      throw new SecurityServerException(
          Constant.ERROR, Constant.USER_FOUND, Constant.GENERIC_ERROR, HttpStatus.BAD_REQUEST);
    }

    var user = UserMapper.build(userRequestDto);
    var userSalved = repository.save(user);
    return UserMapper.build(userSalved);
  }

  @Override
  public Page<UserResponseDto> getAll(
      String id, String profileId, String dni, String name, Pageable pageable)
      throws SecurityServerException {
    var response =
        repository.findAll(
            where(UserSpecification.idProfileEquals(profileId))
                .and(UserSpecification.idEquals(id))
                .and(UserSpecification.nameEquals(name))
                .and(UserSpecification.dniEquals(dni)),
            pageable);
    if (response.isEmpty())
      throw new SecurityServerException(
          Constant.ERROR, Constant.EMPTY_LIST, Constant.GENERIC_ERROR, HttpStatus.NOT_FOUND);
    return UserMapper.build(response);
  }
}
