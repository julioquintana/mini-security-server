package cl.minisecurityserver.securityservertest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PrivilegeResponseDto {
  @JsonProperty("profile_id")
  private String profileId;

  @JsonProperty("role_id")
  private String roleId;

  private String id;
  private String key;
  private String description;
  @UpdateTimestamp private java.sql.Timestamp updatedAt;
  @CreationTimestamp private java.sql.Timestamp createdAt;
}
