package cl.minisecurityserver.securityservertest.specification;

import cl.minisecurityserver.securityservertest.dao.entity.Profile;
import cl.minisecurityserver.securityservertest.dao.entity.Profile_;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class ProfileSpecification {

  public static Specification<Profile> idProfileEquals(final String idProfile) {
    return (root, query, criteriaBuilder) -> {
      if (idProfile != null) {
        return criteriaBuilder.equal(root.get(Profile_.ID), idProfile);
      }
      return criteriaBuilder.conjunction();
    };
  }

  public static Specification<Profile> nameProfileEquals(final String nameProfile) {
    return (root, query, criteriaBuilder) -> {
      if (nameProfile != null) {
        return criteriaBuilder.equal(root.get(Profile_.name), nameProfile);
      }
      return criteriaBuilder.conjunction();
    };
  }
}
