package cl.minisecurityserver.securityservertest.dao.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles", schema = "security")
public class Profile {

  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private String id;

  private String name;
  private Boolean status;
  @UpdateTimestamp private java.sql.Timestamp updatedAt;
  @CreationTimestamp private java.sql.Timestamp createdAt;

  @OneToMany
  @JoinColumn(
      name = "profile_id",
      referencedColumnName = "id",
      updatable = false,
      insertable = false)
  private List<Role> roles;

  @OneToMany
  @JoinColumn(
      name = "profile_id",
      referencedColumnName = "id",
      updatable = false,
      insertable = false)
  private List<User> users;
}
