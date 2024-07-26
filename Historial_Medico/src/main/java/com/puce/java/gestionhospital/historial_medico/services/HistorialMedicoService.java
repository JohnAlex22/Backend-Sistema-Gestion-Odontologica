package com.puce.java.gestionhospital.historial_medico.services;

import com.puce.java.gestionhospital.historial_medico.models.HistorialMedico;
import com.puce.java.gestionhospital.historial_medico.repository.HistorialMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class HistorialMedicoService {

    @Autowired
    private HistorialMedicoRepository historialMedicoRepo;

    public HistorialMedico registrarHistorialMedico(Integer pacienteId, String descripcion, LocalDate fecha) {
        HistorialMedico historialMedico = new HistorialMedico(pacienteId, descripcion, fecha);
        return historialMedicoRepo.save(historialMedico);
    }

    public List<HistorialMedico> listarHistorialesMedicos() {
        return historialMedicoRepo.findAll();
    }

    public HistorialMedico obtenerHistorialMedico(Integer id) {
        return historialMedicoRepo.findById(id).orElseThrow(() -> new RuntimeException("Historial médico no encontrado"));
    }

    public HistorialMedico actualizarHistorialMedico(HistorialMedico historialMedico) {
        HistorialMedico existente = obtenerHistorialMedico(historialMedico.getId());
        existente.setPacienteId(historialMedico.getPacienteId());
        existente.setDescripcion(historialMedico.getDescripcion());
        existente.setFecha(historialMedico.getFecha());
        return historialMedicoRepo.save(existente);
    }

    public void eliminarHistorialMedico(Integer id) {
        HistorialMedico historialMedico = obtenerHistorialMedico(id);
        historialMedicoRepo.delete(historialMedico);
    }
}