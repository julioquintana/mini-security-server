package cl.minisecurityserver.securityservertest.service;

import cl.minisecurityserver.securityservertest.dto.AuthRequestDto;
import cl.minisecurityserver.securityservertest.dto.AuthResponseDto;
import cl.minisecurityserver.securityservertest.dto.ChangePasswordRequestDto;
import cl.minisecurityserver.securityservertest.dto.ChangePasswordResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;

public interface IAuthService {

  AuthResponseDto login(AuthRequestDto authRequestDto) throws SecurityServerException;

  ChangePasswordResponseDto changePassword(ChangePasswordRequestDto authRequestDto)
      throws SecurityServerException;
}
