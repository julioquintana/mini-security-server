package cl.minisecurityserver.securityservertest.dao.repository;

import cl.minisecurityserver.securityservertest.dao.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {}
