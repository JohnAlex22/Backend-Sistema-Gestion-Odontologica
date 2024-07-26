package com.puce.java.gestionhospital.historial_medico.client;

import com.puce.java.gestionhospital.historial_medico.data.PacienteData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Pacientes")
public interface PacienteClient {

    @GetMapping("/pacientes/{id}")
    PacienteData obtenerPaciente(@PathVariable("id") Integer id);
}
