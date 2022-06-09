package cl.minisecurityserver.securityservertest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.slf4j.MDC;

@Getter
@Setter
@AllArgsConstructor
public class LogFormatter extends ObjectMapperConfig {
  private String internalId = MDC.get("internalId");
  private String method;
  private String message;
  private String reason;
  private Object payload;

  public LogFormatter(String method, String message, Object payload) {
    this.method = method;
    this.payload = payload;
    this.message = message;
    this.reason = null;
  }

  @SneakyThrows
  @Override
  public String toString() {
    String json = "";
    try {
      json = objectMapper.writeValueAsString(this);

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return removeFirstAndLastChar(json);
  }

  private String removeFirstAndLastChar(String str) {
    return str.trim().substring(1, str.length() - 1);
  }
}
