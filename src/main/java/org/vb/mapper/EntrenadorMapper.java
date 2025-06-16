package org.vb.mapper;

import org.mapstruct.*;
import org.vb.dto.request.CreateEntrenadorDTO;
import org.vb.dto.request.ModalidadCostoDTO;
import org.vb.dto.request.UpdateEntrenadorDTO;
import org.vb.model.entity.Entrenador;
import org.vb.model.entity.ModalidadCosto;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class EntrenadorMapper {

    public abstract Entrenador toEntity(CreateEntrenadorDTO entrenadorDTO);
    public abstract void updateEntrenadorFromDto(UpdateEntrenadorDTO dto, @MappingTarget Entrenador entity);

    public abstract List<ModalidadCosto> toModalidadCostoList(List<ModalidadCostoDTO> dtos);

    @AfterMapping
    protected void linkEntrenador(@MappingTarget Entrenador entrenador) {
        if (entrenador.getCostos() != null) {
            entrenador.getCostos().forEach(costo -> costo.setEntrenador(entrenador));
        }
    }
}