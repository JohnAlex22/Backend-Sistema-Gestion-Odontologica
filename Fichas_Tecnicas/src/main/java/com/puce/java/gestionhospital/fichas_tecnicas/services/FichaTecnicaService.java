package com.puce.java.gestionhospital.fichas_tecnicas.services;

import com.puce.java.gestionhospital.fichas_tecnicas.models.FichaTecnica;
import com.puce.java.gestionhospital.fichas_tecnicas.repository.FichaTecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class FichaTecnicaService {

    @Autowired
    private FichaTecnicaRepository fichaTecnicaRepo;

    public FichaTecnica registrarFichaTecnica(Integer pacienteId, String diagnostico, String tratamiento, BigDecimal presupuesto, BigDecimal pago) {
        FichaTecnica fichaTecnica = new FichaTecnica(pacienteId, diagnostico, tratamiento, presupuesto, pago);
        return fichaTecnicaRepo.save(fichaTecnica);
    }

    public List<FichaTecnica> listarFichasTecnicas() {
        return fichaTecnicaRepo.findAll();
    }

    public FichaTecnica obtenerFichaTecnica(Integer id) {
        return fichaTecnicaRepo.findById(id).orElseThrow(() -> new RuntimeException("Ficha técnica no encontrada"));
    }

    public FichaTecnica actualizarFichaTecnica(FichaTecnica fichaTecnica) {
        FichaTecnica existente = obtenerFichaTecnica(fichaTecnica.getId());
        existente.setDiagnostico(fichaTecnica.getDiagnostico());
        existente.setTratamiento(fichaTecnica.getTratamiento());
        existente.setPresupuesto(fichaTecnica.getPresupuesto());
        existente.setPago(fichaTecnica.getPago());
        return fichaTecnicaRepo.save(existente);
    }

    public void eliminarFichaTecnica(Integer id) {
        FichaTecnica fichaTecnica = obtenerFichaTecnica(id);
        fichaTecnicaRepo.delete(fichaTecnica);
    }
}
