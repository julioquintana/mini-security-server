package cl.minisecurityserver.securityservertest.dao.entity;

import cl.minisecurityserver.securityservertest.dao.entity.pk.PrivilegePK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.UUID;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "privileges", schema = "security")
public class Privilege {
  @EmbeddedId private PrivilegePK id;
  private String key;
  private String description;
  @UpdateTimestamp
  private java.sql.Timestamp updatedAt;
  @CreationTimestamp
  private java.sql.Timestamp createdAt;

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

  @PrePersist
  public void generateId() {
    this.getId().setId(UUID.randomUUID().toString());
  }
}
