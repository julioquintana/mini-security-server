package cl.minisecurityserver.securityservertest.controller;

import cl.minisecurityserver.securityservertest.dto.PrivilegeRequestDto;
import cl.minisecurityserver.securityservertest.dto.PrivilegeResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IPrivilegesService;
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
@RequestMapping(value = "/v1/privilege/", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivilegesController {
  private final IPrivilegesService service;

  @Autowired
  public PrivilegesController(IPrivilegesService service) {
    this.service = service;
  }

  @PostMapping(path = "/")
  public ResponseEntity<PrivilegeResponseDto> createProfile(
      @RequestBody PrivilegeRequestDto requestDto) throws SecurityServerException {
    return new ResponseEntity<>(service.newRole(requestDto), HttpStatus.OK);
  }

  @GetMapping(path = "/")
  public ResponseEntity<Page<PrivilegeResponseDto>> getByParameters(
      @RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "profile_id", required = false) String profileId,
      @RequestParam(value = "role_id", required = false) String roleId,
      @RequestParam(value = "key", required = false) String key,
      @RequestParam(value = "description", required = false) String description,
      @SortDefault.SortDefaults({@SortDefault(sort = "key", direction = Sort.Direction.ASC)})
          Pageable pageable)
      throws SecurityServerException {
    return new ResponseEntity<>(
        service.getAll(id, key, description, profileId, roleId, pageable), HttpStatus.OK);
  }
}
