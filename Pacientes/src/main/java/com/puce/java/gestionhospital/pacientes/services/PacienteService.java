package com.puce.java.gestionhospital.pacientes.services;

import com.puce.java.gestionhospital.pacientes.models.Pacientes;
import com.puce.java.gestionhospital.pacientes.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepo;

    public Pacientes registrarPaciente(Pacientes paciente) {
        return pacienteRepo.save(paciente);
    }

    public List<Pacientes> listarPacientes() {
        return pacienteRepo.findAll();
    }

    public Pacientes obtenerPaciente(Integer id) {
        return pacienteRepo.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public Pacientes actualizarPaciente(Pacientes paciente) {
        Pacientes existente = obtenerPaciente(paciente.getid());
        existente.setNombre(paciente.getNombre());
        existente.setApellido(paciente.getApellido());
        existente.setFechaNacimiento(paciente.getFechaNacimiento());
        existente.setDireccion(paciente.getDireccion());
        existente.setTelefono(paciente.getTelefono());
        existente.setEmail(paciente.getEmail());
        return pacienteRepo.save(existente);
    }

    public void eliminarPaciente(Integer id) {
        Pacientes paciente = obtenerPaciente(id);
        pacienteRepo.delete(paciente);
    }

    public Optional<Pacientes> buscarPorEmail(String email) {
        return pacienteRepo.findByEmail(email);
    }
}

