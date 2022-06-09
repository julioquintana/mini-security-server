package cl.minisecurityserver.securityservertest.specification;

import cl.minisecurityserver.securityservertest.dao.entity.Role;
import cl.minisecurityserver.securityservertest.dao.entity.Role_;
import cl.minisecurityserver.securityservertest.dao.entity.pk.RolePK_;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class RoleSpecification {

  public static Specification<Role> idEquals(final String id) {
    return (root, query, criteriaBuilder) -> {
      if (id != null) {
        return criteriaBuilder.equal(root.get(Role_.ID).get(RolePK_.ID), id);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<Role> idProfileEquals(final String idProfile) {
    return (root, query, criteriaBuilder) -> {
      if (idProfile != null) {
        return criteriaBuilder.equal(root.get(Role_.ID).get(RolePK_.PROFILE_ID), idProfile);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<Role> keyEquals(final String key) {
    return (root, query, criteriaBuilder) -> {
      if (key != null) {
        return criteriaBuilder.equal(root.get(Role_.KEY), key);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<Role> descriptionEquals(final String description) {
    return (root, query, criteriaBuilder) -> {
      if (description != null) {
        return criteriaBuilder.equal(root.get(Role_.DESCRIPTION), description);
      }
      return criteriaBuilder.conjunction();
    };
  }
}
