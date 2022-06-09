package cl.minisecurityserver.securityservertest.dao.repository;

import cl.minisecurityserver.securityservertest.dao.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository
    extends JpaRepository<Privilege, String>, JpaSpecificationExecutor<Privilege> {}
