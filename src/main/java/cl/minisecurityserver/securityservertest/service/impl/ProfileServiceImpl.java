package cl.minisecurityserver.securityservertest.service.impl;

import cl.minisecurityserver.securityservertest.dao.repository.ProfileRepository;
import cl.minisecurityserver.securityservertest.dto.ProfileRequestDto;
import cl.minisecurityserver.securityservertest.dto.ProfileResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IProfileService;
import cl.minisecurityserver.securityservertest.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    var profile = Mapper.build(profileRequestDto);
    var profilesalved = repository.save(profile);
    return Mapper.build(profilesalved);
  }
}
