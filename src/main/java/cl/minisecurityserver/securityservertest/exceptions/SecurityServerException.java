package cl.minisecurityserver.securityservertest.exceptions;

public class SecurityServerException extends Exception {

  private final String codError;
  private final String severity;

  public SecurityServerException(String severity, String message, String codError) {
    super(message);
    this.codError = codError;
    this.severity = severity;
  }
}
