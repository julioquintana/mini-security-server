package cl.minisecurityserver.securityservertest.dto;

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
public class ProfileResponseDto {
  private String id;
  private String name;
  private Boolean status;
  @UpdateTimestamp private java.sql.Timestamp updatedAt;
  @CreationTimestamp private java.sql.Timestamp createdAt;
}
