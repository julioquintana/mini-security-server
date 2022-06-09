package cl.minisecurityserver.securityservertest.controller;

import cl.minisecurityserver.securityservertest.dto.ProfileRequestDto;
import cl.minisecurityserver.securityservertest.dto.ProfileResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
  private final IProfileService profileService;

  @Autowired
  public ProfileController(IProfileService profileService) {
    this.profileService = profileService;
  }

  @PostMapping(path = "/")
  public ResponseEntity<ProfileResponseDto> createOrder(
      @RequestBody ProfileRequestDto profileRequestDto) throws SecurityServerException {
    return new ResponseEntity<>(profileService.newProfile(profileRequestDto), HttpStatus.OK);
  }
}
