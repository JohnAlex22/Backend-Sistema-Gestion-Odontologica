package com.puce.java.gestionhospital.citas.controllers;

import com.puce.java.gestionhospital.citas.models.Cita;
import com.puce.java.gestionhospital.citas.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/citas")
@CrossOrigin("*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCita(@RequestBody Map<String, String> body) {
        try {
            if (body.get("pacienteId") == null || body.get("fecha") == null ||
                    body.get("hora") == null || body.get("motivo") == null) {
                return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
            }

            Integer pacienteId = Integer.parseInt(body.get("pacienteId"));
            LocalDate fecha = LocalDate.parse(body.get("fecha"));
            LocalTime hora = LocalTime.parse(body.get("hora"));
            String motivo = body.get("motivo");

            Cita cita = citaService.registrarCita(pacienteId, fecha, hora, motivo);
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping
    public List<Cita> listarCitas() {
        return citaService.listarCitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCita(@PathVariable Integer id) {
        try {
            Cita cita = citaService.obtenerCita(id);
            return ResponseEntity.ok(cita);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarCita(@RequestBody Cita cita) {
        try {
            Cita actualizada = citaService.actualizarCita(cita);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarCita(@RequestParam Integer id) {
        try {
            citaService.eliminarCita(id);
            return ResponseEntity.ok("Cita eliminada con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
        }
    }
}
