package com.puce.java.gestionhospital.odontogramas.services;

import com.puce.java.gestionhospital.odontogramas.models.Odontograma;
import com.puce.java.gestionhospital.odontogramas.repository.OdontogramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OdontogramaService {

    @Autowired
    private OdontogramaRepository odontogramaRepository;

    public Odontograma registrarOdontograma(Integer pacienteId, Integer diente, String estado, String img_url) {
        Odontograma odontograma = new Odontograma(pacienteId, diente, estado, img_url);
        return odontogramaRepository.save(odontograma);
    }

    public List<Odontograma> listarOdontogramas() {
        return odontogramaRepository.findAll();
    }

    public Odontograma obtenerOdontograma(Integer id) {
        return odontogramaRepository.findById(id).orElseThrow(() -> new RuntimeException("Odontograma no encontrado"));
    }

    public Odontograma actualizarOdontograma(Odontograma odontograma) {
        Odontograma existente = obtenerOdontograma(odontograma.getId());
        existente.setDiente(odontograma.getDiente());
        existente.setEstado(odontograma.getEstado());
        return odontogramaRepository.save(existente);
    }

    public void eliminarOdontograma(Integer id) {
        Odontograma odontograma = obtenerOdontograma(id);
        odontogramaRepository.delete(odontograma);
    }
}

