package cl.minisecurityserver.securityservertest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PrivilegeRequestDto {

  @JsonProperty("profile_id")
  private String profileId;

  @JsonProperty("role_id")
  private String roleId;

  private String key;
  private String description;
}
