package cl.minisecurityserver.securityservertest.dao.entity.pk;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserPK implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Column(name = "profile_id")
  private String profileId;

  private String id;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserPK userPK = (UserPK) o;
    return Objects.equals(profileId, userPK.profileId) && Objects.equals(id, userPK.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profileId, id);
  }
}
