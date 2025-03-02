package com.puce.java.gestionhospital.citas.repository;
import com.puce.java.gestionhospital.citas.models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
}
