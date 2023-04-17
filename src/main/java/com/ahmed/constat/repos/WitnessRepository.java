package com.ahmed.constat.repos;



import com.ahmed.constat.Witness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WitnessRepository extends JpaRepository<Witness,Long> {
}
