package cl.minisecurityserver.securityservertest.service;

import cl.minisecurityserver.securityservertest.dto.PrivilegeRequestDto;
import cl.minisecurityserver.securityservertest.dto.PrivilegeResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPrivilegesService {
  PrivilegeResponseDto newRole(PrivilegeRequestDto roleRequestDto) throws SecurityServerException;

  Page<PrivilegeResponseDto> getAll(
      String id, String key, String description, String profileId, String roleId, Pageable pageable)
      throws SecurityServerException;
}
