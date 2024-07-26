package com.puce.java.gestionhospital.odontogramas.repository;

import com.puce.java.gestionhospital.odontogramas.models.Odontograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontogramaRepository extends JpaRepository<Odontograma, Integer> {
}
