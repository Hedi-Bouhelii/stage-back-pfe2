package com.ahmed.constat.repos;


import com.ahmed.constat.Constat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstatRepository  extends JpaRepository<Constat,Long> {
    List<Constat> findByUserUsername(String username);
}

