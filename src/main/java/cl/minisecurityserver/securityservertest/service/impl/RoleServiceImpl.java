package cl.minisecurityserver.securityservertest.service.impl;

import static org.springframework.data.jpa.domain.Specification.where;

import cl.minisecurityserver.securityservertest.dao.repository.RoleRepository;
import cl.minisecurityserver.securityservertest.dto.RoleRequestDto;
import cl.minisecurityserver.securityservertest.dto.RoleResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IRolesService;
import cl.minisecurityserver.securityservertest.specification.RoleSpecification;
import cl.minisecurityserver.securityservertest.util.Constant;
import cl.minisecurityserver.securityservertest.util.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRolesService {

  private final RoleRepository repository;

  @Autowired
  public RoleServiceImpl(RoleRepository repository) {
    this.repository = repository;
  }

  @Override
  public RoleResponseDto newRole(RoleRequestDto roleRequestDto) throws SecurityServerException {
    var profile = RoleMapper.build(roleRequestDto);
    var profilesalved = repository.save(profile);
    return RoleMapper.build(profilesalved);
  }

  @Override
  public Page<RoleResponseDto> getAll(
      String id, String key, String description, String profileId, Pageable pageable)
      throws SecurityServerException {
    var response =
        repository.findAll(
            where(RoleSpecification.idProfileEquals(profileId))
                .and(RoleSpecification.idEquals(id))
                .and(RoleSpecification.keyEquals(key))
                .and(RoleSpecification.descriptionEquals(description)),
            pageable);
    if (response.isEmpty())
      throw new SecurityServerException(
          Constant.ERROR,
          Constant.EMPTY_LIST,
          Constant.GENERIC_ERROR,
          HttpStatus.NOT_FOUND);
    return RoleMapper.build(response);
  }
}
