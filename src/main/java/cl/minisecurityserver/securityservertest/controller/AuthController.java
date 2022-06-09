package cl.minisecurityserver.securityservertest.controller;

import cl.minisecurityserver.securityservertest.dto.AuthRequestDto;
import cl.minisecurityserver.securityservertest.dto.AuthResponseDto;
import cl.minisecurityserver.securityservertest.dto.ChangePasswordRequestDto;
import cl.minisecurityserver.securityservertest.dto.ChangePasswordResponseDto;
import cl.minisecurityserver.securityservertest.exceptions.SecurityServerException;
import cl.minisecurityserver.securityservertest.service.IAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Auth Controller", description = "The level API from Server Security Core ")
public class AuthController {

  private final IAuthService service;

  @Autowired
  public AuthController(IAuthService service) {
    this.service = service;
  }

  @PostMapping("/login")
  public HttpEntity<AuthResponseDto> save(@Valid @RequestBody AuthRequestDto authRequestDto)
      throws SecurityServerException {
    return new ResponseEntity<>(service.login(authRequestDto), HttpStatus.CREATED);
  }

  @PostMapping("/change_password")
  public HttpEntity<ChangePasswordResponseDto> changePassword(
      @Valid @RequestBody ChangePasswordRequestDto authRequestDto) throws SecurityServerException {
    return new ResponseEntity<>(service.changePassword(authRequestDto), HttpStatus.CREATED);
  }
}
