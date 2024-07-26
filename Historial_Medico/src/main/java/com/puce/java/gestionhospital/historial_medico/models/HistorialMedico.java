package com.puce.java.gestionhospital.historial_medico.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "paciente_id", nullable = false)
    private Integer pacienteId;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "creado_en", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creadoEn;

    // Constructor sin ID
    public HistorialMedico(Integer pacienteId, String descripcion, LocalDate fecha) {
        this.pacienteId = pacienteId;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
}
