package cl.minisecurityserver.securityservertest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequestDto {
  @NotNull
  @Size(min = 1, max = 128)
  private String dni;

  @NotNull
  @Size(min = 1, max = 512)
  private String password;
}
