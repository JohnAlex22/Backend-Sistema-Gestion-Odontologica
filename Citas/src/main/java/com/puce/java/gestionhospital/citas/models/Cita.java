package com.puce.java.gestionhospital.citas.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "paciente_id", nullable = false)
    private Integer pacienteId;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "creado_en", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creadoEn;

    // Constructor sin ID
    public Cita(Integer pacienteId, LocalDate fecha, LocalTime hora, String motivo) {
        this.pacienteId = pacienteId;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }
}
