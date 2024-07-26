package com.puce.java.gestionhospital.citas.services;

import com.puce.java.gestionhospital.citas.models.Cita;
import com.puce.java.gestionhospital.citas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class CitaService {

    @Autowired
    private CitaRepository citaRepo;

    public Cita registrarCita(Integer pacienteId, LocalDate fecha, LocalTime hora, String motivo) {
        Cita cita = new Cita(pacienteId, fecha, hora, motivo);
        return citaRepo.save(cita);
    }

    public List<Cita> listarCitas() {
        return citaRepo.findAll();
    }

    public Cita obtenerCita(Integer id) {
        return citaRepo.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    public Cita actualizarCita(Cita cita) {
        Cita existente = obtenerCita(cita.getId());
        existente.setPacienteId(cita.getPacienteId());
        existente.setFecha(cita.getFecha());
        existente.setHora(cita.getHora());
        existente.setMotivo(cita.getMotivo());
        return citaRepo.save(existente);
    }

    public void eliminarCita(Integer id) {
        Cita cita = obtenerCita(id);
        citaRepo.delete(cita);
    }
}
