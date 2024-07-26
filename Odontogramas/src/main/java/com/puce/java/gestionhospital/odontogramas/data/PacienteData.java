package com.puce.java.gestionhospital.odontogramas.data;

import lombok.Data;

import java.sql.Date;

@Data
public class PacienteData {

    private Integer id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private String creadoEn;
}
