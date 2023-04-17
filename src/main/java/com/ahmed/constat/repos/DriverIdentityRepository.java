package com.ahmed.constat.repos;



import com.ahmed.constat.DriverIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverIdentityRepository extends JpaRepository<DriverIdentity, Long> {
    // add any custom query methods here
}
