package com.ahmed.constat.repos;

import com.ahmed.constat.InsurenceCompanyInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurenceCompanyInformationRepository extends JpaRepository<InsurenceCompanyInformation, Long> {
}
