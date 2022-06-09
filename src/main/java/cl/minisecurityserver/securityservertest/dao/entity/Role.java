package cl.minisecurityserver.securityservertest.dao.entity;

import cl.minisecurityserver.securityservertest.dao.entity.pk.RolePK;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles", schema = "security")
public class Role {

  @EmbeddedId private RolePK id;
  private String name;
  private String description;
  private long createdFor;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
  @ManyToOne private Profile profile;

  @OneToMany(mappedBy = "role")
  private List<Privilege> privileges;
}
