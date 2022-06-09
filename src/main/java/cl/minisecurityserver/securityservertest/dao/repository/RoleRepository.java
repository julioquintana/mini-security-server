package cl.minisecurityserver.securityservertest.dao.repository;

import cl.minisecurityserver.securityservertest.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository
    extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {}
