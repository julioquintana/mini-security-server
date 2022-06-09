package cl.minisecurityserver.securityservertest.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

  private String code;
  private String message;
  private Map<String, String> details;
}
