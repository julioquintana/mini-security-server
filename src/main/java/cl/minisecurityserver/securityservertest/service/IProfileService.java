package cl.minisecurityserver.securityservertest.service;

import cl.minisecurityserver.securityservertest.dto.ProfileRequestDto;
import cl.minisecurityserver.securityservertest.dto.ProfileResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProfileService {

  ProfileResponseDto newProfile(ProfileRequestDto profileRequestDto) throws SecurityServerException;

  Page<ProfileResponseDto> getAll(String id, String name, Pageable pageable)
      throws SecurityServerException;
}
