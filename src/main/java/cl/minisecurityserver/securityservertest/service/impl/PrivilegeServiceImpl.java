package cl.minisecurityserver.securityservertest.service.impl;

import static org.springframework.data.jpa.domain.Specification.where;

import cl.minisecurityserver.securityservertest.dao.repository.PrivilegeRepository;
import cl.minisecurityserver.securityservertest.dto.PrivilegeRequestDto;
import cl.minisecurityserver.securityservertest.dto.PrivilegeResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IPrivilegesService;
import cl.minisecurityserver.securityservertest.specification.PrivilegeSpecification;
import cl.minisecurityserver.securityservertest.util.Constant;
import cl.minisecurityserver.securityservertest.util.PrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements IPrivilegesService {

  private final PrivilegeRepository repository;

  @Autowired
  public PrivilegeServiceImpl(PrivilegeRepository repository) {
    this.repository = repository;
  }

  @Override
  public PrivilegeResponseDto newRole(PrivilegeRequestDto roleRequestDto)
      throws SecurityServerException {
    var profile = PrivilegeMapper.build(roleRequestDto);
    var profilesalved = repository.save(profile);
    return PrivilegeMapper.build(profilesalved);
  }

  @Override
  public Page<PrivilegeResponseDto> getAll(
      String id, String key, String description, String profileId, String roleId, Pageable pageable)
      throws SecurityServerException {
    var response =
        repository.findAll(
            where(PrivilegeSpecification.idProfileEquals(profileId))
                .and(PrivilegeSpecification.idRoleEquals(roleId))
                .and(PrivilegeSpecification.idEquals(id))
                .and(PrivilegeSpecification.keyEquals(key))
                .and(PrivilegeSpecification.descriptionEquals(description)),
            pageable);
    if (response.isEmpty())
      throw new SecurityServerException(
          Constant.ERROR, Constant.EMPTY_LIST, Constant.GENERIC_ERROR, HttpStatus.NOT_FOUND);
    return PrivilegeMapper.build(response);
  }
}
