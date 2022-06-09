package cl.minisecurityserver.securityservertest.specification;

import cl.minisecurityserver.securityservertest.dao.entity.Privilege;
import cl.minisecurityserver.securityservertest.dao.entity.Privilege_;
import cl.minisecurityserver.securityservertest.dao.entity.pk.PrivilegePK_;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class PrivilegeSpecification {

  public static Specification<Privilege> idEquals(final String id) {
    return (root, query, criteriaBuilder) -> {
      if (id != null) {
        return criteriaBuilder.equal(root.get(Privilege_.ID).get(PrivilegePK_.ID), id);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<Privilege> idRoleEquals(final String idRole) {
    return (root, query, criteriaBuilder) -> {
      if (idRole != null) {
        return criteriaBuilder.equal(root.get(Privilege_.ID).get(PrivilegePK_.ROLE_ID), idRole);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<Privilege> idProfileEquals(final String idProfile) {
    return (root, query, criteriaBuilder) -> {
      if (idProfile != null) {
        return criteriaBuilder.equal(
            root.get(Privilege_.ID).get(PrivilegePK_.PROFILE_ID), idProfile);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<Privilege> keyEquals(final String key) {
    return (root, query, criteriaBuilder) -> {
      if (key != null) {
        return criteriaBuilder.equal(root.get(Privilege_.KEY), key);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<Privilege> descriptionEquals(final String description) {
    return (root, query, criteriaBuilder) -> {
      if (description != null) {
        return criteriaBuilder.equal(root.get(Privilege_.DESCRIPTION), description);
      }
      return criteriaBuilder.conjunction();
    };
  }
}
