package com.puce.java.gestionhospital.historial_medico.controllers;

import com.puce.java.gestionhospital.historial_medico.models.HistorialMedico;
import com.puce.java.gestionhospital.historial_medico.services.HistorialMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/historial_medico")
@CrossOrigin("*")
public class HistorialMedicoController {

    @Autowired
    private HistorialMedicoService historialMedicoService;

    @PostMapping("/registrar")
    public HistorialMedico registrarHistorialMedico(@RequestBody Map<String, String> body) {
        Integer pacienteId = Integer.parseInt(body.get("pacienteId"));
        String descripcion = body.get("descripcion");
        LocalDate fecha = LocalDate.parse(body.get("fecha"));
        return historialMedicoService.registrarHistorialMedico(pacienteId, descripcion, fecha);
    }

    @GetMapping
    public List<HistorialMedico> listarHistorialesMedicos() {
        return historialMedicoService.listarHistorialesMedicos();
    }

    @GetMapping("/{id}")
    public HistorialMedico obtenerHistorialMedico(@PathVariable Integer id) {
        return historialMedicoService.obtenerHistorialMedico(id);
    }

    @PutMapping("/actualizar")
    public HistorialMedico actualizarHistorialMedico(@RequestBody HistorialMedico historialMedico) {
        return historialMedicoService.actualizarHistorialMedico(historialMedico);
    }

    @DeleteMapping("/eliminar")
    public String eliminarHistorialMedico(@RequestParam Integer id) {
        historialMedicoService.eliminarHistorialMedico(id);
        return "Historial médico eliminado con éxito";
    }
}
