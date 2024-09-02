package com.example.courseservice.lection.repo;

import com.example.courseservice.lection.model.Lection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectionRepo extends JpaRepository<Lection, Long> {

    @Query
    Optional<Lection> findByCodeLection(String codeLection);


}
