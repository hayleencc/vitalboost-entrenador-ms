package org.vb.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vb.dto.request.CreateEntrenadorDTO;
import org.vb.dto.request.ModalidadCostoDTO;
import org.vb.dto.request.UpdateEntrenadorDTO;
import org.vb.dto.response.EntrenadorResponseDTO;
import org.vb.mapper.EntrenadorMapper;
import org.vb.model.entity.Entrenador;
import org.vb.model.entity.ModalidadCosto;
import org.vb.repository.EntrenadorRepository;
import org.vb.service.utils.TestDataFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntrenadorServiceTest{

    @Mock
    private EntrenadorRepository entrenadorRepository;

    @Mock
    private EntrenadorMapper entrenadorMapper;

    @InjectMocks
    private EntrenadorService entrenadorService;

    @Test
    void createEntrenador_shouldSaveAndReturnEntity(){
        CreateEntrenadorDTO dto = TestDataFactory.createEntrenadorDTO();
        Entrenador entrenador = TestDataFactory.createEntrenadorEntity();
        EntrenadorResponseDTO responseDTO = TestDataFactory.createEntrenadorResponseDTO();

        when(entrenadorMapper.toEntity(dto)).thenReturn(entrenador);
        when(entrenadorRepository.save(entrenador)).thenReturn(entrenador);
        when(entrenadorMapper.toResponseDTO(entrenador)).thenReturn(responseDTO);


        EntrenadorResponseDTO respuesta = entrenadorService.createEntrenador(dto);

        assertNotNull(respuesta);
        assertEquals("Jane Doe", respuesta.getNombreCompleto());
        assertEquals("Bio", respuesta.getBiografia());
        assertEquals(5, respuesta.getAniosExperiencia());
        verify(entrenadorMapper).toEntity(dto);
        verify(entrenadorRepository).save(entrenador);
        verify(entrenadorMapper).toResponseDTO(entrenador);
    }

    @Test
    void createEntrenador_withCostos_shouldMapAndSaveCorrectly() {
        CreateEntrenadorDTO dto = TestDataFactory.createEntrenadorDTO();
        Entrenador entrenador = TestDataFactory.createEntrenadorEntity();
        EntrenadorResponseDTO responseDTO = TestDataFactory.createEntrenadorResponseDTO();

        when(entrenadorMapper.toEntity(dto)).thenReturn(entrenador);
        when(entrenadorRepository.save(entrenador)).thenReturn(entrenador);
        when(entrenadorMapper.toResponseDTO(entrenador)).thenReturn(responseDTO);


        EntrenadorResponseDTO result = entrenadorService.createEntrenador(dto);


        assertNotNull(result);
        assertEquals("Jane Doe", result.getNombreCompleto());
        assertEquals(1, result.getCostos().size());
        assertEquals("online", result.getCostos().get(0).getModalidad());
        assertEquals(BigDecimal.valueOf(30.0), result.getCostos().get(0).getCosto());

        verify(entrenadorMapper).toEntity(dto);
        verify(entrenadorRepository).save(entrenador);
        verify(entrenadorMapper).toResponseDTO(entrenador);

    }
    @Test
    void getEntrenadores_withoutParameters_shouldReturnList() {
        Entrenador entrenador1 = TestDataFactory.createEntrenadorEntity();
        Entrenador entrenador2 = TestDataFactory.createEntrenadorEntityTwo();
        EntrenadorResponseDTO responseDTO1 = TestDataFactory.createEntrenadorResponseDTO();
        EntrenadorResponseDTO responseDTO2 = TestDataFactory.createEntrenadorResponseDTOTwo();

        List<Entrenador> entrenadores = List.of(entrenador1, entrenador2);

        when(entrenadorRepository.searchEntrenadores(null, null)).thenReturn(entrenadores);

        when(entrenadorMapper.toResponseDTO(entrenador1)).thenReturn(responseDTO1);
        when(entrenadorMapper.toResponseDTO(entrenador2)).thenReturn(responseDTO2);


       List<EntrenadorResponseDTO> result = entrenadorService.getEntrenadores(null, null);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(responseDTO1.getNombreCompleto(), result.get(0).getNombreCompleto());
        assertEquals(responseDTO2.getNombreCompleto(), result.get(1).getNombreCompleto());

        verify(entrenadorRepository).searchEntrenadores(null, null);
        verify(entrenadorMapper).toResponseDTO(entrenador1);
        verify(entrenadorMapper).toResponseDTO(entrenador2);
    }
    @Test
    void getEntrenadores_withModalidad_shouldReturnFilteredList() {
        Entrenador entrenador1 = TestDataFactory.createEntrenadorEntity();
        EntrenadorResponseDTO responseDTO1 = TestDataFactory.createEntrenadorResponseDTO();


        List<Entrenador> entrenadoresFiltrados = List.of(entrenador1);
        when(entrenadorRepository.searchEntrenadores(null, "online")).thenReturn(entrenadoresFiltrados);
        when(entrenadorMapper.toResponseDTO(entrenador1)).thenReturn(responseDTO1);

        List<EntrenadorResponseDTO> result = entrenadorService.getEntrenadores(null, "online");

        assertEquals(1, result.size());
        assertEquals("Jane Doe", result.get(0).getNombreCompleto());
        verify(entrenadorRepository).searchEntrenadores(null, "online");
    }

    @Test
    void getEntrenadores_sinFiltros_shouldReturnEmptyListIfThereIsNoEntrenadores() {
        List<Entrenador> entrenadoresFiltrados = new ArrayList<>();
        when(entrenadorRepository.searchEntrenadores(null, null)).thenReturn(entrenadoresFiltrados);

        List<EntrenadorResponseDTO> result = entrenadorService.getEntrenadores(null, null);

        assertEquals(0, result.size());
        verify(entrenadorRepository).searchEntrenadores(null, null);
    }

    @Test
    void getEntrenadorById_existingId_shouldReturnEntrenador() {
        String id = TestDataFactory.ENTRENADOR_ID;
        Entrenador entrenador = TestDataFactory.createEntrenadorEntityWithId(id);
        EntrenadorResponseDTO responseDTO = TestDataFactory.createEntrenadorResponseDTOWithId(id);

        when(entrenadorRepository.findById(id)).thenReturn(Optional.of(entrenador));
        when(entrenadorMapper.toResponseDTO(entrenador)).thenReturn(responseDTO);


        EntrenadorResponseDTO result = entrenadorService.getEntrenadorById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Jane Doe", result.getNombreCompleto());
        verify(entrenadorRepository).findById(id);
        verify(entrenadorMapper).toResponseDTO(entrenador);

    }

    @Test
    void getEntrenadorById_nonExistingId_shouldThrowException() {
        String id = TestDataFactory.ENTRENADOR_ID;

        when(entrenadorRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            entrenadorService.getEntrenadorById(id);
        });

        assertTrue(exception.getMessage().contains(id));
        verify(entrenadorRepository).findById(id);
    }

    @Test
    void patchEntrenador_existingId_shouldUpdateAndReturnEntrenador() {
        String id = TestDataFactory.ENTRENADOR_ID;
        Entrenador entrenador = TestDataFactory.createEntrenadorEntityWithId(id);
        UpdateEntrenadorDTO dto = TestDataFactory.updateEntrenadorDTO();

        when(entrenadorRepository.findById(id)).thenReturn(Optional.of(entrenador));
        when(entrenadorRepository.save(any(Entrenador.class))).thenAnswer(invocation -> invocation.getArgument(0));

        doAnswer(invocation -> {
            UpdateEntrenadorDTO dtoArg = invocation.getArgument(0);
            Entrenador entrenadorArg = invocation.getArgument(1);

            if (dtoArg.getNombreCompleto() != null) {
                entrenadorArg.setNombreCompleto(dtoArg.getNombreCompleto());
            }
            if (dtoArg.getAniosExperiencia() != 0) {
                entrenadorArg.setAniosExperiencia(dtoArg.getAniosExperiencia());
            }

            return null;
        }).when(entrenadorMapper).updateEntrenadorFromDto(any(UpdateEntrenadorDTO.class), any(Entrenador.class));

        EntrenadorResponseDTO responseDTO = TestDataFactory.createEntrenadorResponseDTOWithId(id);
        responseDTO.setNombreCompleto(dto.getNombreCompleto());
        responseDTO.setAniosExperiencia(dto.getAniosExperiencia());

        when(entrenadorMapper.toResponseDTO(any(Entrenador.class))).thenReturn(responseDTO);

        EntrenadorResponseDTO result = entrenadorService.patchEntrenador(id, dto);

        assertNotNull(result);
        assertEquals("Nombre Actualizado", result.getNombreCompleto());
        assertEquals(10, result.getAniosExperiencia());

        verify(entrenadorRepository).findById(id);
        verify(entrenadorMapper).updateEntrenadorFromDto(eq(dto), any(Entrenador.class));
        verify(entrenadorRepository).save(any(Entrenador.class));
        verify(entrenadorMapper).toResponseDTO(any(Entrenador.class));
    }

    @Test
    void patchEntrenador_givingCostosDTO_shouldReplaceAllCostosSaved() {
        String id = TestDataFactory.ENTRENADOR_ID;

        Entrenador entrenador = TestDataFactory.createEntrenadorEntityWithId(id);

        ModalidadCostoDTO costoNuevo1 = TestDataFactory.createModalidadCostoDTO("online", BigDecimal.valueOf(30));
        ModalidadCostoDTO costoNuevo2 = TestDataFactory.createModalidadCostoDTO("presencial", BigDecimal.valueOf(45));

        UpdateEntrenadorDTO dto = new UpdateEntrenadorDTO();
        dto.setCostos(List.of(costoNuevo1, costoNuevo2));

        when(entrenadorRepository.findById(id)).thenReturn(Optional.of(entrenador));
        when(entrenadorRepository.save(any(Entrenador.class))).thenAnswer(inv -> inv.getArgument(0));

        List<ModalidadCosto> costosEntidad = List.of(
                TestDataFactory.createModalidadCosto("online", BigDecimal.valueOf(30)),
                TestDataFactory.createModalidadCosto("presencial", BigDecimal.valueOf(45))
        );

        when(entrenadorMapper.toModalidadCostoList(dto.getCostos())).thenReturn(costosEntidad);
        doNothing().when(entrenadorMapper).updateEntrenadorFromDto(any(), any());


        EntrenadorResponseDTO responseDTO = TestDataFactory.createEntrenadorResponseDTOWithIdAndCostos(
                id,
                List.of(
                        TestDataFactory.createModalidadCostoResponseDTO("online", BigDecimal.valueOf(30)),
                        TestDataFactory.createModalidadCostoResponseDTO("presencial", BigDecimal.valueOf(45))
                )
        );

        when(entrenadorMapper.toResponseDTO(any(Entrenador.class))).thenReturn(responseDTO);


        EntrenadorResponseDTO result = entrenadorService.patchEntrenador(id, dto);

        assertEquals(2, result.getCostos().size());
        assertTrue(result.getCostos().stream()
                .anyMatch(c -> c.getModalidad().equals("online") && c.getCosto().equals(BigDecimal.valueOf(30))));
        assertTrue(result.getCostos().stream()
                .anyMatch(c -> c.getModalidad().equals("presencial") && c.getCosto().equals(BigDecimal.valueOf(45))));

        verify(entrenadorRepository).save(any());
    }

}

