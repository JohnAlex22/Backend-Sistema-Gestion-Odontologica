package com.puce.java.gestionhospital.citas.data;

import lombok.Data;

import java.sql.Date;

@Data
public class PacienteData {

    private Integer pacienteId;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private String creadoEn;
}
