package com.puce.java.gestionhospital.fichas_tecnicas.repository;

import com.puce.java.gestionhospital.fichas_tecnicas.models.FichaTecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaTecnicaRepository extends JpaRepository<FichaTecnica, Integer> {
}
