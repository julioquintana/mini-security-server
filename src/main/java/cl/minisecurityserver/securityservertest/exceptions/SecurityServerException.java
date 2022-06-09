package cl.minisecurityserver.securityservertest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SecurityServerException extends Exception {

  private final String codError;
  private final String severity;
  private final HttpStatus status;

  public SecurityServerException(
      String severity, String message, String codError, HttpStatus status) {
    super(message);
    this.codError = codError;
    this.severity = severity;
    this.status = status;
  }
}
