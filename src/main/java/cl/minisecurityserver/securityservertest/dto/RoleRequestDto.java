package cl.minisecurityserver.securityservertest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleRequestDto {

  @JsonProperty("profile_id")
  private String profileId;

  private String key;
  private String description;
}
