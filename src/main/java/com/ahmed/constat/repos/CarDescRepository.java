package com.ahmed.constat.repos;


import com.ahmed.constat.VehiculeDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDescRepository extends JpaRepository<VehiculeDescription, Long> {
    // add any custom query methods here
}
