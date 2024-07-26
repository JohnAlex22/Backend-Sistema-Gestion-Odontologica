package com.puce.java.gestionhospital.fichas_tecnicas.controllers;

import com.puce.java.gestionhospital.fichas_tecnicas.models.FichaTecnica;
import com.puce.java.gestionhospital.fichas_tecnicas.services.FichaTecnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fichas_tecnicas")
@CrossOrigin("*")
public class FichaTecnicaController {

    @Autowired
    private FichaTecnicaService fichaTecnicaService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarFichaTecnica(@RequestBody Map<String, String> body) {
        try {
            // Validar que los campos no sean nulos
            if (body.get("pacienteId") == null || body.get("diagnostico") == null ||
                    body.get("tratamiento") == null || body.get("presupuesto") == null ||
                    body.get("pago") == null) {
                return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
            }

            // Convertir los valores a los tipos esperados
            Integer pacienteId = Integer.parseInt(body.get("pacienteId"));
            String diagnostico = body.get("diagnostico");
            String tratamiento = body.get("tratamiento");
            BigDecimal presupuesto = new BigDecimal(body.get("presupuesto"));
            BigDecimal pago = new BigDecimal(body.get("pago"));

            // Registrar la ficha técnica
            FichaTecnica fichaTecnica = fichaTecnicaService.registrarFichaTecnica(pacienteId, diagnostico, tratamiento, presupuesto, pago);
            return ResponseEntity.ok(fichaTecnica);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Error en la conversión de datos numéricos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping
    public List<FichaTecnica> listarFichasTecnicas() {
        return fichaTecnicaService.listarFichasTecnicas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerFichaTecnica(@PathVariable Integer id) {
        try {
            FichaTecnica fichaTecnica = fichaTecnicaService.obtenerFichaTecnica(id);
            return ResponseEntity.ok(fichaTecnica);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ficha técnica no encontrada");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarFichaTecnica(@RequestBody FichaTecnica fichaTecnica) {
        try {
            FichaTecnica actualizada = fichaTecnicaService.actualizarFichaTecnica(fichaTecnica);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ficha técnica no encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarFichaTecnica(@RequestParam Integer id) {
        try {
            fichaTecnicaService.eliminarFichaTecnica(id);
            return ResponseEntity.ok("Ficha técnica eliminada con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ficha técnica no encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}

