package cl.minisecurityserver.securityservertest.dao.entity;

import cl.minisecurityserver.securityservertest.dao.entity.pk.PrivilegePK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "privileges", schema = "security")
public class Privilege {
  @EmbeddedId private PrivilegePK id;
  private String name;
  private String description;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

  @ManyToOne
  @JsonBackReference
  @JoinColumns({
    @JoinColumn(
        name = "profile_id",
        referencedColumnName = "profile_id",
        insertable = false,
        updatable = false),
    @JoinColumn(
        name = "role_id",
        referencedColumnName = "id",
        insertable = false,
        updatable = false)
  })
  private Role role;
}
