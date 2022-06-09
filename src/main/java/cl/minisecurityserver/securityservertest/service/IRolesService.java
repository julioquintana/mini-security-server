package cl.minisecurityserver.securityservertest.service;

import cl.minisecurityserver.securityservertest.dto.RoleRequestDto;
import cl.minisecurityserver.securityservertest.dto.RoleResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRolesService {

  RoleResponseDto newRole(RoleRequestDto roleRequestDto) throws SecurityServerException;

  Page<RoleResponseDto> getAll(
      String id, String key, String description, String profileId, Pageable pageable)
      throws SecurityServerException;
}
