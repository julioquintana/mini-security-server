package cl.minisecurityserver.securityservertest.dto;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

  @Column(name = "profile_id")
  private String profileId;

  private String dni;
  private String prefix;
  private String name;
  private String password;
}
