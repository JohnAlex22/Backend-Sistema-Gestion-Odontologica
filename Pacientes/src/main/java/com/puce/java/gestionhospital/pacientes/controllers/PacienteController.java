package com.puce.java.gestionhospital.pacientes.controllers;

import com.puce.java.gestionhospital.pacientes.models.Pacientes;
import com.puce.java.gestionhospital.pacientes.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin("*")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPaciente(@RequestBody Pacientes paciente) {
        try {
            Pacientes registrado = pacienteService.registrarPaciente(paciente);
            return ResponseEntity.ok(registrado);
        } catch (Exception e) {
            // Log the exception and return a meaningful error message
            e.printStackTrace(); // Print stack trace to console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar paciente: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Pacientes> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public Pacientes obtenerPaciente(@PathVariable Integer id) {
        return pacienteService.obtenerPaciente(id);
    }

    @PutMapping("/actualizar")
    public Pacientes actualizarPaciente(@RequestBody Pacientes paciente) {
        return pacienteService.actualizarPaciente(paciente);
    }

    @DeleteMapping("/eliminar")
    public String eliminarPaciente(@RequestParam Integer id) {
        pacienteService.eliminarPaciente(id);
        return "Paciente eliminado con éxito";
    }
}
