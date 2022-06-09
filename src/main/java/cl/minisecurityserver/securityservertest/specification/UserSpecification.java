package cl.minisecurityserver.securityservertest.specification;

import cl.minisecurityserver.securityservertest.dao.entity.User;
import cl.minisecurityserver.securityservertest.dao.entity.User_;
import cl.minisecurityserver.securityservertest.dao.entity.pk.UserPK_;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class UserSpecification {

  public static Specification<User> idEquals(final String id) {
    return (root, query, criteriaBuilder) -> {
      if (id != null) {
        return criteriaBuilder.equal(root.get(User_.ID).get(UserPK_.ID), id);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<User> idProfileEquals(final String idProfile) {
    return (root, query, criteriaBuilder) -> {
      if (idProfile != null) {
        return criteriaBuilder.equal(root.get(User_.ID).get(UserPK_.PROFILE_ID), idProfile);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<User> dniEquals(final String dni) {
    return (root, query, criteriaBuilder) -> {
      if (dni != null) {
        return criteriaBuilder.equal(root.get(User_.DNI), dni);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<User> nameEquals(final String name) {
    return (root, query, criteriaBuilder) -> {
      if (name != null) {
        return criteriaBuilder.equal(root.get(User_.NAME), name);
      }
      return criteriaBuilder.conjunction();
    };
  }
}
