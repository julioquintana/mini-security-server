package cl.minisecurityserver.securityservertest.dao.entity.pk;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegePK implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Column(name = "profile_id")
  private String profileId;

  @Column(name = "role_id")
  private String roleId;

  private Integer id;
}
