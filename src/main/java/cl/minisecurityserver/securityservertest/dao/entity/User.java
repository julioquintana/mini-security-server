package cl.minisecurityserver.securityservertest.dao.entity;

import cl.minisecurityserver.securityservertest.dao.entity.pk.UserPK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "security")
public class User implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @EmbeddedId private UserPK id;
  private String dni;
  private String prefix;
  private String name;
  private String password;
  private Boolean status;
  @UpdateTimestamp private java.sql.Timestamp updatedAt;
  @CreationTimestamp private java.sql.Timestamp createdAt;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(
      name = "profile_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private Profile profile;

  @PrePersist
  public void generateId() {
    this.getId().setId(UUID.randomUUID().toString());
  }
}
