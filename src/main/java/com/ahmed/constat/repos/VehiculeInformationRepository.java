package com.ahmed.constat.repos;


import com.ahmed.constat.VehiculeInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeInformationRepository extends JpaRepository<VehiculeInformation,Long> {
}
