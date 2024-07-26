package com.puce.java.gestionhospital.odontogramas.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "odontogramas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Odontograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "paciente_id", nullable = false)
    private Integer pacienteId;

    @Column(name = "diente", nullable = false)
    private Integer diente;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    @Column (name = "img_url")
    private String imgUrl;

    // Constructor adicional
    public Odontograma(Integer pacienteId, Integer diente, String estado, String imgUrl) {
        this.pacienteId = pacienteId;
        this.diente = diente;
        this.estado = estado;
        this.imgUrl = imgUrl;
    }
}
