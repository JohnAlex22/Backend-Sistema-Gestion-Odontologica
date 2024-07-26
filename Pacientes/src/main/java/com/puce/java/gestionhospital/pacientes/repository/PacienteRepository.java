package com.puce.java.gestionhospital.pacientes.repository;

import com.puce.java.gestionhospital.pacientes.models.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Pacientes, Integer> {
    Optional<Pacientes> findByEmail(String email);
}
