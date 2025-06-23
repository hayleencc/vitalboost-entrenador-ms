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

public class TestDataFactory {
    public static final String ENTRENADOR_ID = "XyZ123abcDEF456ghiJKL789mnoPQR012stuVWX";

    public static CreateEntrenadorDTO createEntrenadorDTO() {
        CreateEntrenadorDTO dto = new CreateEntrenadorDTO();
        dto.setId(ENTRENADOR_ID);
        dto.setNombreCompleto("Jane Doe");
        dto.setBiografia("Bio");
        dto.setAniosExperiencia(5);
        dto.setRol("entrenador");
        ModalidadCostoDTO costoDTO = new ModalidadCostoDTO();
        costoDTO.setModalidad("online");
        costoDTO.setCosto(BigDecimal.valueOf(30.0));
        dto.setCostos(List.of(costoDTO));
        return dto;
    }

    public static Entrenador createEntrenadorEntity() {
        Entrenador entrenador = new Entrenador();
        entrenador.setId(ENTRENADOR_ID);
        entrenador.setNombreCompleto("Jane Doe");
        entrenador.setBiografia("Bio");
        entrenador.setAniosExperiencia(5);
        entrenador.setRol("entrenador");
        ModalidadCosto costo = new ModalidadCosto();
        costo.setModalidad("online");
        costo.setCosto(BigDecimal.valueOf(30.0));
        entrenador.setCostos(List.of(costo));
        return entrenador;
    }

    public static Entrenador createEntrenadorEntityTwo() {
        Entrenador entrenador = new Entrenador();
        entrenador.setId(ENTRENADOR_ID);
        entrenador.setNombreCompleto("Jhon Doe");
        entrenador.setBiografia("Bio");
        entrenador.setAniosExperiencia(3);
        entrenador.setRol("entrenador");
        ModalidadCosto costo = new ModalidadCosto();
        costo.setModalidad("presencial");
        costo.setCosto(BigDecimal.valueOf(30.0));
        entrenador.setCostos(List.of(costo));
        return entrenador;
    }

    public static EntrenadorResponseDTO createEntrenadorResponseDTO() {
        EntrenadorResponseDTO dto = new EntrenadorResponseDTO();
        dto.setId(ENTRENADOR_ID);
        dto.setNombreCompleto("Jane Doe");
        dto.setBiografia("Bio");
        dto.setAniosExperiencia(5);
        dto.setRol("entrenador");
        ModalidadCostoResponseDTO costoDTO = new ModalidadCostoResponseDTO();
        costoDTO.setModalidad("online");
        costoDTO.setCosto(BigDecimal.valueOf(30.0));
        dto.setCostos(List.of(costoDTO));
        return dto;
    }

    public static EntrenadorResponseDTO createEntrenadorResponseDTOTwo() {
        EntrenadorResponseDTO dto = new EntrenadorResponseDTO();
        dto.setId(ENTRENADOR_ID);
        dto.setNombreCompleto("Jhon Doe");
        dto.setBiografia("Bio");
        dto.setAniosExperiencia(3);
        dto.setRol("entrenador");
        ModalidadCostoResponseDTO costoDTO = new ModalidadCostoResponseDTO();
        costoDTO.setModalidad("presencial");
        costoDTO.setCosto(BigDecimal.valueOf(30.0));
        dto.setCostos(List.of(costoDTO));
        return dto;
    }


    public static Entrenador createEntrenadorEntityWithId(String id) {
        return new Entrenador(ENTRENADOR_ID, "Jane Doe", "email@test.com", "Profesion",
                "Especialidad", "Universidad", "Consultorio",
                "Biografia", 5, new ArrayList<>(List.of(createModalidadCosto())), "entrenador");
    }


    public static EntrenadorResponseDTO createEntrenadorResponseDTOWithId(String id) {
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

    public static EntrenadorResponseDTO createEntrenadorResponseDTOWithIdAndCostos(
            List<ModalidadCostoResponseDTO> costos
    ) {
        EntrenadorResponseDTO dto = new EntrenadorResponseDTO();
        dto.setId(ENTRENADOR_ID);
        dto.setNombreCompleto("Jane Doe");
        dto.setBiografia("Biografia");
        dto.setAniosExperiencia(5);
        dto.setEmail("email@test.com");
        dto.setProfesion("Profesion");
        dto.setEspecialidad("Especialidad");
        dto.setUniversidad("Universidad");
        dto.setDatosConsultorio("Consultorio");
        dto.setCostos(costos);
        dto.setRol("entrenador");
        return dto;
    }

}
