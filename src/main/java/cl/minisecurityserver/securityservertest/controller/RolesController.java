package cl.minisecurityserver.securityservertest.controller;

import cl.minisecurityserver.securityservertest.dto.RoleRequestDto;
import cl.minisecurityserver.securityservertest.dto.RoleResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IRolesService;
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
@RequestMapping(value = "/v1/role/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RolesController {
  private final IRolesService service;

  @Autowired
  public RolesController(IRolesService profileService) {
    this.service = profileService;
  }

  @PostMapping(path = "/")
  public ResponseEntity<RoleResponseDto> createProfile(@RequestBody RoleRequestDto requestDto)
      throws SecurityServerException {
    return new ResponseEntity<>(service.newRole(requestDto), HttpStatus.OK);
  }

  @GetMapping(path = "/")
  public ResponseEntity<Page<RoleResponseDto>> getByParameters(
      @RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "profile_id", required = false) String profileId,
      @RequestParam(value = "key", required = false) String key,
      @RequestParam(value = "description", required = false) String description,
      @SortDefault.SortDefaults({@SortDefault(sort = "key", direction = Sort.Direction.ASC)})
          Pageable pageable)
      throws SecurityServerException {
    return new ResponseEntity<>(
        service.getAll(id, key, description, profileId, pageable), HttpStatus.OK);
  }
}
