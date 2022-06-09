package cl.minisecurityserver.securityservertest.dto;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Column;
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
public class UserResponseDto implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Column(name = "profile_id")
  private String profileId;

  private String id;
  private String dni;
  private String prefix;
  private String name;
  private Boolean status;
  private java.sql.Timestamp updatedAt;
  private java.sql.Timestamp createdAt;
}
