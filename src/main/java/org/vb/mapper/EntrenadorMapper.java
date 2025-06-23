package org.vb.mapper;

import org.mapstruct.*;
import org.vb.dto.request.CreateEntrenadorDTO;
import org.vb.dto.request.ModalidadCostoDTO;
import org.vb.dto.request.UpdateEntrenadorDTO;
import org.vb.dto.response.EntrenadorResponseDTO;
import org.vb.dto.response.ModalidadCostoResponseDTO;
import org.vb.model.entity.Entrenador;
import org.vb.model.entity.ModalidadCosto;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class EntrenadorMapper {

    @Mapping(target = "costos", source = "costos")
    public abstract Entrenador toEntity(CreateEntrenadorDTO entrenadorDTO);
    public abstract ModalidadCosto toModalidadCosto(ModalidadCostoDTO dto);

    @Mapping(target = "rol", ignore = true)
    public abstract void updateEntrenadorFromDto(UpdateEntrenadorDTO dto, @MappingTarget Entrenador entity);

    public abstract List<ModalidadCosto> toModalidadCostoList(List<ModalidadCostoDTO> dtos);

    public abstract EntrenadorResponseDTO toResponseDTO(Entrenador entrenador);
    public abstract ModalidadCostoResponseDTO toModalidadCostoResponseDTO(ModalidadCosto entity);

    @AfterMapping
    protected void linkEntrenador(@MappingTarget Entrenador entrenador) {
        if (entrenador.getCostos() != null) {
            entrenador.getCostos().forEach(costo -> costo.setEntrenador(entrenador));
        }
    }
}