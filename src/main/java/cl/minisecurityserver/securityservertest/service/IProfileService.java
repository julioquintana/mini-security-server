package cl.minisecurityserver.securityservertest.service;

import cl.minisecurityserver.securityservertest.dto.ProfileRequestDto;
import cl.minisecurityserver.securityservertest.dto.ProfileResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;

public interface IProfileService {

  ProfileResponseDto newProfile(ProfileRequestDto profileRequestDto) throws SecurityServerException;
}
