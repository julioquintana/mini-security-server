package cl.minisecurityserver.securityservertest.controller;

import cl.minisecurityserver.securityservertest.dto.ProfileRequestDto;
import cl.minisecurityserver.securityservertest.dto.ProfileResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/profile/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
  private final IProfileService profileService;

  @Autowired
  public ProfileController(IProfileService profileService) {
    this.profileService = profileService;
  }

  @PostMapping(path = "/")
  public ResponseEntity<ProfileResponseDto> createProfile(
      @RequestBody ProfileRequestDto profileRequestDto) throws SecurityServerException {
    return new ResponseEntity<>(profileService.newProfile(profileRequestDto), HttpStatus.OK);
  }

  @GetMapping(path = "/")
  public ResponseEntity<Page<ProfileResponseDto>> getByParameters(
      @RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "name", required = false) String name,
      @SortDefault.SortDefaults({@SortDefault(sort = "name", direction = Sort.Direction.ASC)})
          Pageable pageable)
      throws SecurityServerException {
    return new ResponseEntity<>(profileService.getAll(id, name, pageable), HttpStatus.OK);
  }
}
