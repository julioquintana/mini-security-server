package cl.minisecurityserver.securityservertest.dao.entity;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "profiles", schema = "security")
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private long accountId;
  private String name;
  private String status;
  private java.sql.Timestamp updatedAt;
  private java.sql.Timestamp createdAt;

  @OneToMany(mappedBy = "profile")
  private List<Role> roles;

  @OneToMany(mappedBy = "profile")
  private List<User> users;

}
