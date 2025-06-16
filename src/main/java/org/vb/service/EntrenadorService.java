package org.vb.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.vb.dto.request.CreateEntrenadorDTO;
import org.vb.dto.request.UpdateEntrenadorDTO;
import org.vb.mapper.EntrenadorMapper;
import org.vb.model.entity.Entrenador;
import org.vb.model.entity.ModalidadCosto;
import org.vb.repository.EntrenadorRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final EntrenadorMapper entrenadorMapper;

    public EntrenadorService(EntrenadorRepository entrenadorRepository, EntrenadorMapper entrenadorMapper) {

        this.entrenadorRepository = entrenadorRepository;
        this.entrenadorMapper = entrenadorMapper;
    }

    public Entrenador createEntrenador(CreateEntrenadorDTO entrenador) {
        Entrenador nuevoEntrenador = entrenadorMapper.toEntity(entrenador);
        return entrenadorRepository.save(nuevoEntrenador);

    }

    public List<Entrenador> searchEntrenadores(String especialidad, String modalidad) {
        especialidad = (especialidad == null || especialidad.isBlank()) ? null : especialidad;
        modalidad = (modalidad == null || modalidad.isBlank()) ? null : modalidad;
        return entrenadorRepository.searchCoaches(especialidad, modalidad);
    }

    public Entrenador findCoachById(UUID id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró al coach con ID: " + id));
    }

    public Entrenador patchEntrenador(UUID id, UpdateEntrenadorDTO entrenadorToUpdate) {
        Entrenador existingEntrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró al coach con ID: " + id));

//        if (entrenadorToUpdate.getNombreCompleto() != null) {
//            existingEntrenador.setNombreCompleto(entrenadorToUpdate.getNombreCompleto());
//        }
//        if (entrenadorToUpdate.getEspecialidad() != null) {
//            existingEntrenador.setEspecialidad(entrenadorToUpdate.getEspecialidad());
//        }
//        if (entrenadorToUpdate.getDatosConsultorio() != null) {
//            existingEntrenador.setDatosConsultorio(entrenadorToUpdate.getDatosConsultorio());
//        }
//        if (entrenadorToUpdate.getUniversidad() != null) {
//            existingEntrenador.setUniversidad(entrenadorToUpdate.getUniversidad());
//        }

        entrenadorMapper.updateEntrenadorFromDto(entrenadorToUpdate, existingEntrenador);
        if (entrenadorToUpdate.getCostos() != null) {
            List<ModalidadCosto> costos = entrenadorMapper.toModalidadCostoList(entrenadorToUpdate.getCostos());
            existingEntrenador.getCostos().clear();
            existingEntrenador.getCostos().addAll(costos);
        }

        return entrenadorRepository.save(existingEntrenador);
    }
}