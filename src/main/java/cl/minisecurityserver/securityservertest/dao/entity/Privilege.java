package cl.minisecurityserver.securityservertest.dao.entity;

import cl.minisecurityserver.securityservertest.dao.entity.pk.PrivilegePK;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "privileges", schema = "security")
public class Privilege {
  @EmbeddedId private PrivilegePK id;
  private String name;
  private String description;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

  @ManyToOne private Role role;
}
