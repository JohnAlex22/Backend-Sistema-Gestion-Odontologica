package com.puce.java.gestionhospital.fichas_tecnicas.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fichas_tecnicas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FichaTecnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "paciente_id", nullable = false)
    private Integer pacienteId;

    @Column(name = "diagnostico", nullable = false)
    private String diagnostico;

    @Column(name = "tratamiento", nullable = false)
    private String tratamiento;

    @Column(name = "presupuesto")
    private BigDecimal presupuesto;

    @Column(name = "pago")
    private BigDecimal pago;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    // Constructor sin el campo "fichaTecnicaId" y "creadoEn"
    public FichaTecnica(Integer pacienteId, String diagnostico, String tratamiento, BigDecimal presupuesto, BigDecimal pago) {
        this.pacienteId = pacienteId;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.presupuesto = presupuesto;
        this.pago = pago;
    }
}

