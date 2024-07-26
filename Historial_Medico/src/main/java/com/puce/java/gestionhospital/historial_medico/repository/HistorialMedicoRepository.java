package com.puce.java.gestionhospital.historial_medico.repository;

import com.puce.java.gestionhospital.historial_medico.models.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Integer> {
}
