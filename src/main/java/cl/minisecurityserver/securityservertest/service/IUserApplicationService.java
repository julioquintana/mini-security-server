package cl.minisecurityserver.securityservertest.service;

import cl.minisecurityserver.securityservertest.dto.UserRequestDto;
import cl.minisecurityserver.securityservertest.dto.UserResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserApplicationService {

  Page<UserResponseDto> getAll(
      String id, String profileId, String dni, String name, Pageable pageable)
      throws SecurityServerException;

  UserResponseDto newUser(UserRequestDto userRequestDto) throws SecurityServerException;
}
