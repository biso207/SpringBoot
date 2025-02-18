package com.example.provespringboot.repository;

import com.example.provespringboot.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {
    Studente findByEmail(String email); // Trova uno studente per email
}
