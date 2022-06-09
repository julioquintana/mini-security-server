package cl.minisecurityserver.securityservertest.controller;

import cl.minisecurityserver.securityservertest.dto.UserRequestDto;
import cl.minisecurityserver.securityservertest.dto.UserResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IUserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_admin/v1/")
public class UserApplicationController {

  private final IUserApplicationService service;

  @Autowired
  public UserApplicationController(IUserApplicationService service) {
    this.service = service;
  }

  @PostMapping(path = "/")
  public ResponseEntity<UserResponseDto> createProfile(@RequestBody UserRequestDto requestDto)
      throws SecurityServerException {
    return new ResponseEntity<>(service.newUser(requestDto), HttpStatus.OK);
  }

  @GetMapping(path = "/")
  public ResponseEntity<Page<UserResponseDto>> getByParameters(
      @RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "profile_id", required = false) String profileId,
      @RequestParam(value = "dni", required = false) String dni,
      @RequestParam(value = "name", required = false) String name,
      @SortDefault.SortDefaults({@SortDefault(sort = "name", direction = Sort.Direction.ASC)})
          Pageable pageable)
      throws SecurityServerException {
    return new ResponseEntity<>(service.getAll(id, profileId, dni, name, pageable), HttpStatus.OK);
  }
}
