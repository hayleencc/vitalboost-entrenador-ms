package org.vb.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.vb.dto.request.CreateEntrenadorDTO;
import org.vb.dto.request.UpdateEntrenadorDTO;
import org.vb.dto.response.EntrenadorResponseDTO;
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

    public EntrenadorResponseDTO createEntrenador(CreateEntrenadorDTO entrenador) {
        Entrenador nuevoEntrenador = entrenadorMapper.toEntity(entrenador);
        Entrenador entrenadorGuardado = entrenadorRepository.save(nuevoEntrenador);
        return entrenadorMapper.toResponseDTO(entrenadorGuardado);
    }

    public List<EntrenadorResponseDTO> getEntrenadores(String especialidad, String modalidad) {
        especialidad = (especialidad == null || especialidad.isBlank()) ? null : especialidad;
        modalidad = (modalidad == null || modalidad.isBlank()) ? null : modalidad;
        List<Entrenador> entrenadores = entrenadorRepository.searchEntrenadores(especialidad, modalidad);
        return entrenadores.stream()
                .map(entrenadorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EntrenadorResponseDTO getEntrenadorById(UUID id) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró al entrenador con ID: " + id));
        return entrenadorMapper.toResponseDTO(entrenador);
    }

    public EntrenadorResponseDTO patchEntrenador(UUID id, UpdateEntrenadorDTO entrenadorToUpdate) {
        Entrenador existingEntrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró al entrenador con ID: " + id));

        entrenadorMapper.updateEntrenadorFromDto(entrenadorToUpdate, existingEntrenador);
        if (entrenadorToUpdate.getCostos() != null) {
            List<ModalidadCosto> costos = entrenadorMapper.toModalidadCostoList(entrenadorToUpdate.getCostos());
            existingEntrenador.getCostos().clear();
            existingEntrenador.getCostos().addAll(costos);
        }
        Entrenador entrenadorGuardado = entrenadorRepository.save(existingEntrenador);
        return entrenadorMapper.toResponseDTO(entrenadorGuardado);
    }

}