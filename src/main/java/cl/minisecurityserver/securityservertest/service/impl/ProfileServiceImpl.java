package cl.minisecurityserver.securityservertest.service.impl;

import static org.springframework.data.jpa.domain.Specification.where;

import cl.minisecurityserver.securityservertest.dao.repository.ProfileRepository;
import cl.minisecurityserver.securityservertest.dto.ProfileRequestDto;
import cl.minisecurityserver.securityservertest.dto.ProfileResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IProfileService;
import cl.minisecurityserver.securityservertest.specification.ProfileSpecification;
import cl.minisecurityserver.securityservertest.util.Constant;
import cl.minisecurityserver.securityservertest.util.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements IProfileService {

  private final ProfileRepository repository;

  @Autowired
  public ProfileServiceImpl(ProfileRepository repository) {
    this.repository = repository;
  }

  @Override
  public ProfileResponseDto newProfile(ProfileRequestDto profileRequestDto)
      throws SecurityServerException {
    var profile = ProfileMapper.build(profileRequestDto);
    var profilesalved = repository.save(profile);
    return ProfileMapper.build(profilesalved);
  }

  @Override
  public Page<ProfileResponseDto> getAll(String id, String name, Pageable pageable)
      throws SecurityServerException {
    var response =
        repository.findAll(
            where(ProfileSpecification.idProfileEquals(id))
                .and(ProfileSpecification.nameProfileEquals(name)),
            pageable);
    if (response.isEmpty())
      throw new SecurityServerException(
          Constant.ERROR,
          Constant.EMPTY_LIST,
          Constant.GENERIC_ERROR,
          HttpStatus.NOT_FOUND);
    return ProfileMapper.build(response);
  }
}
