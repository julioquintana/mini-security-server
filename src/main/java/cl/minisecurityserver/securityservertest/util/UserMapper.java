package cl.minisecurityserver.securityservertest.util;

import cl.minisecurityserver.securityservertest.dao.entity.User;
import cl.minisecurityserver.securityservertest.dao.entity.pk.UserPK;
import cl.minisecurityserver.securityservertest.dto.AuthResponseDto;
import cl.minisecurityserver.securityservertest.dto.ChangePasswordResponseDto;
import cl.minisecurityserver.securityservertest.dto.UserRequestDto;
import cl.minisecurityserver.securityservertest.dto.UserResponseDto;
import cl.minisecurityserver.securityservertest.security.PasswordUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class UserMapper {
  public static User build(UserRequestDto requestDto) {
    return User.builder()
        .id(UserPK.builder().profileId(requestDto.getProfileId()).build())
        .dni(requestDto.getDni())
        .prefix(requestDto.getPrefix())
        .name(requestDto.getName())
        .password(PasswordUtil.getHashPassword(requestDto.getPassword()))
        .status(true)
        .build();
  }

  public static UserResponseDto build(User user) {

    return UserResponseDto.builder()
        .id(user.getId().getId())
        .profileId(user.getId().getProfileId())
        .dni(user.getDni())
        .prefix(user.getPrefix())
        .name(user.getName())
        .status(user.getStatus())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .build();
  }

  public static Page<UserResponseDto> build(Page<User> roles) {

    return new PageImpl<>(
        roles.stream().map(UserMapper::build).collect(Collectors.toList()),
        roles.getPageable(),
        roles.getTotalElements());
  }

  public static List<UserResponseDto> build(List<User> roles) {

    return roles.stream().map(UserMapper::build).collect(Collectors.toList());
  }

  public static AuthResponseDto buildAuthResponseDto(User user, String token) {
    return AuthResponseDto.builder()
        .id(user.getId().getId())
        .dni(user.getDni())
        .prefix(user.getPrefix())
        .name(user.getName())
        .token(token)
        .build();
  }

  public static ChangePasswordResponseDto buildChangePasswordResponseDto(
      String status, String description) {
    return ChangePasswordResponseDto.builder().status(status).description(description).build();
  }

  public static User buildChangePassword(String passwords, User user) {
    user.setPassword(passwords);
    return user;
  }
}
