package cl.minisecurityserver.securityservertest.dao.entity;

import cl.minisecurityserver.securityservertest.dao.entity.pk.UserPK;
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
@Table(name = "user", schema = "security")
public class User {

  @EmbeddedId private UserPK id;
  private String dni;
  private String prefix;
  private String name;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
  @ManyToOne private Profile profile;
}