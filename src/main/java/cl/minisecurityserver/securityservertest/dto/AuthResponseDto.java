package cl.minisecurityserver.securityservertest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
  private String id;
  private String dni;
  private String prefix;
  private String name;
  private String token;
}
