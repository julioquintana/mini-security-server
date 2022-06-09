package cl.minisecurityserver.securityservertest.dao.entity;

import cl.minisecurityserver.securityservertest.dao.entity.pk.RolePK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "roles", schema = "security")
public class Role implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @EmbeddedId private RolePK id;
  private String key;
  private String description;
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

  @OneToMany
  @JoinColumns({
    @JoinColumn(
        name = "profile_id",
        referencedColumnName = "profile_id",
        updatable = false,
        insertable = false),
    @JoinColumn(
        name = "role_id",
        referencedColumnName = "id",
        updatable = false,
        insertable = false)
  })
  private List<Privilege> privileges;

  @PrePersist
  public void generateId() {
    this.getId().setId(UUID.randomUUID().toString());
  }
}
