package com.ahmed.constat.repos;


import com.ahmed.constat.InsuredInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuredInformationRepository extends JpaRepository<InsuredInformation,Long> {
}
