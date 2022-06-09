package cl.minisecurityserver.securityservertest.exceptions;

import cl.minisecurityserver.securityservertest.dto.ErrorDto;
import cl.minisecurityserver.securityservertest.util.Constant;
import cl.minisecurityserver.securityservertest.util.LogFormatter;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> handleValidationExceptions(
      MethodArgumentNotValidException exception) {
    Map<String, String> messages = new LinkedHashMap<>();
    for (int index = 0; index < exception.getBindingResult().getFieldErrors().size(); index++) {
      FieldError field = exception.getBindingResult().getFieldErrors().get(index);
      messages.put(field.getField(), field.getDefaultMessage());
    }
    ErrorDto errorResponse =
        new ErrorDto(
            Constant.ERROR,
            "MethodArgumentNotValidException",
            messages);
    log.error(
        new LogFormatter(Constant.ERROR, "MethodArgumentNotValidException", errorResponse));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(JsonMappingException.class)
  public ResponseEntity<ErrorDto> jsonMappingException(JsonMappingException e) {
    Map<String, String> messages = new LinkedHashMap<>();
    messages.put(Constant.ERROR, e.getMessage());
    ErrorDto errorResponse =
        new ErrorDto(
            Constant.ERROR,
            "JsonMappingException",
            messages);
    log.error(new LogFormatter(Constant.ERROR, "JsonMappingException", errorResponse));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SecurityServerException.class)
  public ResponseEntity<ErrorDto> appException(SecurityServerException e) {
    Map<String, String> messages = new LinkedHashMap<>();
    messages.put(e.getSeverity(), e.getMessage());
    ErrorDto errorResponse =
        new ErrorDto(
            e.getCodError(),
            "SecurityServerException",
            messages);
    log.error(new LogFormatter(Constant.ERROR, "SecurityServerException", errorResponse));
    return new ResponseEntity<>(errorResponse, e.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> exception(Exception e) {
    Map<String, String> messages = new LinkedHashMap<>();
    messages.put(Constant.ERROR, e.getMessage());
    ErrorDto errorResponse =
        new ErrorDto(
            Constant.ERROR,
            "Exception",
            messages);
    log.error(new LogFormatter(Constant.ERROR, "Exception", errorResponse));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
