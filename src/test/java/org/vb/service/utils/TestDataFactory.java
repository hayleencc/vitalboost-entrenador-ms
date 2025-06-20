package org.vb.service.utils;

import org.vb.dto.request.CreateEntrenadorDTO;
import org.vb.dto.request.ModalidadCostoDTO;
import org.vb.dto.request.UpdateEntrenadorDTO;
import org.vb.dto.response.EntrenadorResponseDTO;
import org.vb.dto.response.ModalidadCostoResponseDTO;
import org.vb.model.entity.Entrenador;
import org.vb.model.entity.ModalidadCosto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TestDataFactory {
    public static CreateEntrenadorDTO createEntrenadorDTO() {
        CreateEntrenadorDTO dto = new CreateEntrenadorDTO();
        dto.setNombreCompleto("Jane Doe");
        dto.setBiografia("Bio");
        dto.setAniosExperiencia(5);

        ModalidadCostoDTO costoDTO = new ModalidadCostoDTO();
        costoDTO.setModalidad("online");
        costoDTO.setCosto(BigDecimal.valueOf(30.0));
        dto.setCostos(List.of(costoDTO));
        return dto;
    }

    public static CreateEntrenadorDTO createEntrenadorDTOTwo() {
        CreateEntrenadorDTO dto = new CreateEntrenadorDTO();
        dto.setNombreCompleto("Jhon Doe");
        dto.setBiografia("Bio");
        dto.setAniosExperiencia(3);

        ModalidadCostoDTO costoDTO = new ModalidadCostoDTO();
        costoDTO.setModalidad("presencial");
        costoDTO.setCosto(BigDecimal.valueOf(30.0));
        dto.setCostos(List.of(costoDTO));
        return dto;
    }

    public static Entrenador createEntrenadorEntity() {
        Entrenador entrenador = new Entrenador();
        entrenador.setNombreCompleto("Jane Doe");
        entrenador.setBiografia("Bio");
        entrenador.setAniosExperiencia(5);

        ModalidadCosto costo = new ModalidadCosto();
        costo.setModalidad("online");
        costo.setCosto(BigDecimal.valueOf(30.0));
        entrenador.setCostos(List.of(costo));
        return entrenador;
    }

    public static Entrenador createEntrenadorEntityTwo() {
        Entrenador entrenador = new Entrenador();
        entrenador.setNombreCompleto("Jhon Doe");
        entrenador.setBiografia("Bio");
        entrenador.setAniosExperiencia(3);

        ModalidadCosto costo = new ModalidadCosto();
        costo.setModalidad("presencial");
        costo.setCosto(BigDecimal.valueOf(30.0));
        entrenador.setCostos(List.of(costo));
        return entrenador;
    }

    public static EntrenadorResponseDTO createEntrenadorResponseDTO() {
        EntrenadorResponseDTO dto = new EntrenadorResponseDTO();
        dto.setNombreCompleto("Jane Doe");
        dto.setBiografia("Bio");
        dto.setAniosExperiencia(5);

        ModalidadCostoResponseDTO costoDTO = new ModalidadCostoResponseDTO();
        costoDTO.setModalidad("online");
        costoDTO.setCosto(BigDecimal.valueOf(30.0));
        dto.setCostos(List.of(costoDTO));
        return dto;
    }

    public static EntrenadorResponseDTO createEntrenadorResponseDTOTwo() {
        EntrenadorResponseDTO dto = new EntrenadorResponseDTO();
        dto.setNombreCompleto("Jhon Doe");
        dto.setBiografia("Bio");
        dto.setAniosExperiencia(3);

        ModalidadCostoResponseDTO costoDTO = new ModalidadCostoResponseDTO();
        costoDTO.setModalidad("presencial");
        costoDTO.setCosto(BigDecimal.valueOf(30.0));
        dto.setCostos(List.of(costoDTO));
        return dto;
    }


    public static Entrenador createEntrenadorEntityWithId(UUID id) {
        return new Entrenador(id, "Jane Doe", "email@test.com", "Profesion",
                "Especialidad", "Universidad", "Consultorio",
                "Biografia", 5, new ArrayList<>(List.of(createModalidadCosto())));
    }


    public static EntrenadorResponseDTO createEntrenadorResponseDTOWithId(UUID id) {
        EntrenadorResponseDTO dto = new EntrenadorResponseDTO();
        dto.setId(id);
        dto.setNombreCompleto("Jane Doe");
        dto.setBiografia("Biografia");
        dto.setAniosExperiencia(5);
        dto.setEmail("email@test.com");
        dto.setProfesion("Profesion");
        dto.setEspecialidad("Especialidad");
        dto.setUniversidad("Universidad");
        dto.setDatosConsultorio("Consultorio");
        dto.setCostos(Collections.emptyList());
        return dto;
    }

    public static UpdateEntrenadorDTO updateEntrenadorDTO(){
        UpdateEntrenadorDTO dto = new UpdateEntrenadorDTO();
        dto.setNombreCompleto("Nombre Actualizado");
        dto.setAniosExperiencia(10);
        return dto;
    }

    public static ModalidadCosto createModalidadCosto() {
        ModalidadCosto costo = new ModalidadCosto();
        costo.setModalidad("presencial");
        costo.setCosto(BigDecimal.valueOf(30.0));
        return costo;
    }

    public static ModalidadCosto createModalidadCosto(String modalidad, BigDecimal costo) {
        ModalidadCosto modalidadCosto = new ModalidadCosto();
        modalidadCosto.setModalidad(modalidad);
        modalidadCosto.setCosto(costo);
        return modalidadCosto;
    }

    public static ModalidadCostoDTO createModalidadCostoDTO(String modalidad, BigDecimal costo) {
        ModalidadCostoDTO dto = new ModalidadCostoDTO();
        dto.setModalidad(modalidad);
        dto.setCosto(costo);
        return dto;
    }

    public static ModalidadCostoResponseDTO createModalidadCostoResponseDTO(String modalidad, BigDecimal costo) {
        ModalidadCostoResponseDTO dto = new ModalidadCostoResponseDTO();
        dto.setModalidad(modalidad);
        dto.setCosto(costo);
        return dto;
    }

    public static Entrenador createEntrenadorEntityWithIdAndCostos(UUID id, List<ModalidadCosto> costos) {
        return new Entrenador(
                id,
                "Jane Doe",
                "email@test.com",
                "Profesion",
                "Especialidad",
                "Universidad",
                "Consultorio",
                "Biografia",
                5,
                costos
        );
    }

    public static EntrenadorResponseDTO createEntrenadorResponseDTOWithIdAndCostos(
            UUID id,
            List<ModalidadCostoResponseDTO> costos
    ) {
        EntrenadorResponseDTO dto = new EntrenadorResponseDTO();
        dto.setId(id);
        dto.setNombreCompleto("Jane Doe");
        dto.setBiografia("Biografia");
        dto.setAniosExperiencia(5);
        dto.setEmail("email@test.com");
        dto.setProfesion("Profesion");
        dto.setEspecialidad("Especialidad");
        dto.setUniversidad("Universidad");
        dto.setDatosConsultorio("Consultorio");
        dto.setCostos(costos);
        return dto;
    }

}
