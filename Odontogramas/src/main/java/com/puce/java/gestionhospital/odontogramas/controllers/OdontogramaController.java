package com.puce.java.gestionhospital.odontogramas.controllers;

import com.puce.java.gestionhospital.odontogramas.models.Odontograma;
import com.puce.java.gestionhospital.odontogramas.repository.OdontogramaRepository;
import com.puce.java.gestionhospital.odontogramas.services.OdontogramaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/odontogramas")
@CrossOrigin("*")
public class OdontogramaController {

    @Autowired
    private OdontogramaService odontogramaService;
    @Autowired
    private OdontogramaRepository repository;
    private static String subida_dir = "odontogramas/";



    @PostMapping("/registrar")
    public ResponseEntity<?> registrarOdontograma(@RequestBody Map<String, String> body) {
        try {
            if (body.get("pacienteId") == null || body.get("diente") == null || body.get("estado") == null) {
                return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
            }

            Integer pacienteId = Integer.parseInt(body.get("pacienteId"));
            Integer diente = Integer.parseInt(body.get("diente"));
            String estado = body.get("estado");

            String img_url = null;
            Odontograma odontograma = odontogramaService.registrarOdontograma(pacienteId, diente, estado, img_url);
            return ResponseEntity.ok(odontograma);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping
    public List<Odontograma> listarOdontogramas() {
        return odontogramaService.listarOdontogramas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerOdontograma(@PathVariable Integer id) {
        try {
            Odontograma odontograma = odontogramaService.obtenerOdontograma(id);
            return ResponseEntity.ok(odontograma);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontograma no encontrado");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarOdontograma(@RequestBody Odontograma odontograma) {
        try {
            Odontograma actualizado = odontogramaService.actualizarOdontograma(odontograma);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontograma no encontrado");
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOdontograma(@RequestParam Integer id) {
        try {
            odontogramaService.eliminarOdontograma(id);
            return ResponseEntity.ok("Odontograma eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontograma no encontrado");
        }
    }

    @Operation(summary = "Subir imagen para odontograma")
    @PostMapping("/{id}/subirImg")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen subida con éxito",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "No se ha proporcionado ninguna imagen"),
            @ApiResponse(responseCode = "500", description = "Error al subir la imagen")
    })
    public ResponseEntity<String> subirImagen(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile image) {
        if (image == null) {
            return ResponseEntity.badRequest().body("No se ha proporcionado ninguna imagen");
        }
        try {
            Path subirPath = Paths.get(subida_dir);
            if (!Files.exists(subirPath)) {
                Files.createDirectories(subirPath);
            }

            String fileName = id + "_" + image.getOriginalFilename();
            Path filePath = subirPath.resolve(fileName);
            Files.copy(image.getInputStream(), filePath);

            Odontograma odontograma = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            odontograma.setImgUrl(filePath.toString());
            repository.save(odontograma);

            return ResponseEntity.ok().body("Imagen subida con éxito: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen");
        }
    }


    @Operation(summary = "Obtener imagen del odontograma")
    @GetMapping("/{id}/imagen")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable("id") Integer id) {
        Odontograma odontograma = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (odontograma.getImgUrl() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Path imagePath = Paths.get(odontograma.getImgUrl());
        if (!Files.exists(imagePath)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @Operation(summary = "Actualizar imagen del odontograma")
    @PutMapping("/{id}/actualizarImg")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen actualizada con éxito",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "No se ha proporcionado ninguna imagen"),
            @ApiResponse(responseCode = "404", description = "Odontograma no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error al actualizar la imagen")
    })
    public ResponseEntity<String> updateImageById(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile image) {
        if (image == null) {
            return ResponseEntity.badRequest().body("No se ha proporcionado ninguna imagen");
        }
        try {
            Odontograma odontograma = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Odontograma no encontrado"));

            Path oldImagePath = Paths.get(odontograma.getImgUrl());
            if (Files.exists(oldImagePath)) {
                Files.delete(oldImagePath);
            }

            Path subirPath = Paths.get(subida_dir);
            if (!Files.exists(subirPath)) {
                Files.createDirectories(subirPath);
            }

            String fileName = id + "_" + image.getOriginalFilename();
            Path filePath = subirPath.resolve(fileName);
            Files.copy(image.getInputStream(), filePath);

            odontograma.setImgUrl(filePath.toString());
            repository.save(odontograma);

            return ResponseEntity.ok().body("Imagen actualizada con éxito: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la imagen");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontograma no encontrado");
        }
    }
}
