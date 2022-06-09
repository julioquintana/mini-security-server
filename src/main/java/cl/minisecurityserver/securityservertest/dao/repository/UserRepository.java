package cl.minisecurityserver.securityservertest.dao.repository;

import cl.minisecurityserver.securityservertest.dao.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
    extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
  Optional<User> findByDni(String dni);
}
