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
import org.vb.mapper.EntrenadorMapper;
import org.vb.model.entity.Entrenador;
import org.vb.model.entity.ModalidadCosto;
import org.vb.repository.EntrenadorRepository;
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
        CreateEntrenadorDTO dto = new CreateEntrenadorDTO();
        dto.setNombreCompleto("Jhon Doe");
        dto.setBiografia("Biografia del entrenador");
        dto.setAniosExperiencia(2);

        Entrenador entrenador = new Entrenador();
        entrenador.setNombreCompleto("Jhon Doe");
        entrenador.setBiografia("Biografia del entrenador");
        entrenador.setAniosExperiencia(2);


        when(entrenadorMapper.toEntity(dto)).thenReturn(entrenador);
        when(entrenadorRepository.save(entrenador)).thenReturn(entrenador);

        Entrenador respuesta = entrenadorService.createEntrenador(dto);

        assertNotNull(respuesta);

        verify(entrenadorMapper).toEntity(dto);
        verify(entrenadorRepository).save(entrenador);

    }

    @Test
    void createEntrenador_withCostos_shouldMapAndSaveCorrectly() {
        CreateEntrenadorDTO dto = new CreateEntrenadorDTO();
        dto.setNombreCompleto("Jane Doe");
        dto.setBiografia("Bio");
        dto.setAniosExperiencia(5);

        Entrenador entrenador = new Entrenador();
        entrenador.setNombreCompleto("Jane Doe");
        entrenador.setBiografia("Bio");
        entrenador.setAniosExperiencia(5);

        ModalidadCostoDTO costoDTO = new ModalidadCostoDTO();
        costoDTO.setModalidad("online");
        costoDTO.setCosto(BigDecimal.valueOf(30.0));

        ModalidadCosto costo = new ModalidadCosto();
        costo.setModalidad("online");
        costo.setCosto(BigDecimal.valueOf(30.0));

        dto.setCostos(List.of(costoDTO));
        entrenador.setCostos(List.of(costo));


        when(entrenadorMapper.toEntity(dto)).thenReturn(entrenador);
        when(entrenadorRepository.save(entrenador)).thenReturn(entrenador);

        Entrenador result = entrenadorService.createEntrenador(dto);

        assertNotNull(result);
        assertEquals(1, result.getCostos().size());
        assertEquals("online", result.getCostos().get(0).getModalidad());
        assertEquals(BigDecimal.valueOf(30.0), result.getCostos().get(0).getCosto());

        verify(entrenadorMapper).toEntity(dto);
        verify(entrenadorRepository).save(entrenador);
    }
    @Test
    void getEntrenadores_withoutParameters_shouldReturnList() {
        Entrenador entrenador1 = new Entrenador();
        entrenador1.setNombreCompleto("Entrenador 1");
        entrenador1.setBiografia("Bio 1");
        entrenador1.setAniosExperiencia(3);

        Entrenador entrenador2 = new Entrenador();
        entrenador2.setNombreCompleto("Entrenador 2");
        entrenador2.setBiografia("Bio 2");
        entrenador2.setAniosExperiencia(5);

        List<Entrenador> entrenadores = List.of(entrenador1, entrenador2);

        when(entrenadorRepository.searchEntrenadores(null, null)).thenReturn(entrenadores);

       List<Entrenador> result = entrenadorService.getEntrenadores(null, null);

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(entrenadorRepository).searchEntrenadores(null, null);
    }
    @Test
    void getEntrenadores_withModalidad_shouldReturnFilteredList() {

        Entrenador entrenador1 = new Entrenador();
        entrenador1.setNombreCompleto("Entrenador 1");
        entrenador1.setBiografia("Bio 1");
        entrenador1.setAniosExperiencia(3);
        ModalidadCosto costo = new ModalidadCosto();
        costo.setModalidad("online");
        costo.setCosto(BigDecimal.valueOf(30.0));
        entrenador1.setCostos(List.of(costo));

        List<Entrenador> entrenadoresFiltrados = List.of(entrenador1);
        when(entrenadorRepository.searchEntrenadores(null, "online")).thenReturn(entrenadoresFiltrados);

        List<Entrenador> result = entrenadorService.getEntrenadores(null, "online");

        assertEquals(1, result.size());
        assertEquals("Entrenador 1", result.get(0).getNombreCompleto());
        verify(entrenadorRepository).searchEntrenadores(null, "online");
    }

    @Test
    void getEntrenadores_sinFiltros_shouldReturnEmptyListIfThereIsNoEntrenadores() {

        List<Entrenador> entrenadoresFiltrados = new ArrayList<>();
        when(entrenadorRepository.searchEntrenadores(null, null)).thenReturn(entrenadoresFiltrados);

        List<Entrenador> result = entrenadorService.getEntrenadores(null, null);

        assertEquals(0, result.size());
        verify(entrenadorRepository).searchEntrenadores(null, null);
    }

    @Test
    void getEntrenadorById_existingId_shouldReturnEntrenador() {
        UUID id = UUID.randomUUID();
        Entrenador entrenador = new Entrenador(id, "Entrenador Test", "email@test.com", "Profesion",
                "Especialidad", "Universidad", "Consultorio",
                "Biografia", 5, List.of());

        when(entrenadorRepository.findById(id)).thenReturn(Optional.of(entrenador));

        Entrenador result = entrenadorService.getEntrenadorById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Entrenador Test", result.getNombreCompleto());
        verify(entrenadorRepository).findById(id);
    }

    @Test
    void getEntrenadorById_nonExistingId_shouldThrowException() {
        UUID id = UUID.randomUUID();

        when(entrenadorRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            entrenadorService.getEntrenadorById(id);
        });

        assertTrue(exception.getMessage().contains(id.toString()));
        verify(entrenadorRepository).findById(id);
    }

    @Test
    void patchEntrenador_existingId_shouldUpdateAndReturnEntrenador() {
        UUID id = UUID.randomUUID();
        Entrenador entrenador = new Entrenador(id, "Entrenador Test", "email@test.com", "Profesion",
                "Especialidad", "Universidad", "Consultorio",
                "Biografia", 5, List.of());


        UpdateEntrenadorDTO dto = new UpdateEntrenadorDTO();
        dto.setNombreCompleto("Nombre Actualizado");
        dto.setAniosExperiencia(10);

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

        Entrenador result = entrenadorService.patchEntrenador(id, dto);

        assertNotNull(result);
        assertEquals("Nombre Actualizado", result.getNombreCompleto());
        assertEquals(10, result.getAniosExperiencia());

        verify(entrenadorRepository).findById(id);
        verify(entrenadorMapper).updateEntrenadorFromDto(eq(dto), any(Entrenador.class));
        verify(entrenadorRepository).save(any(Entrenador.class));
    }

    @Test
    void patchEntrenador_givingCostosDTO_shouldReplaceAllCostosSaved() {
        UUID id = UUID.randomUUID();

        ModalidadCosto costoPrevio = new ModalidadCosto();
        costoPrevio.setModalidad("presencial");
        costoPrevio.setCosto(BigDecimal.valueOf(50));

        Entrenador entrenador = new Entrenador(id, "Nombre", "email@test.com", "Profesion",
                "Especialidad", "Universidad", "Consultorio",
                "Biografia", 5, new ArrayList<>(List.of(costoPrevio)));

        ModalidadCostoDTO costoNuevo1 = new ModalidadCostoDTO();
        costoNuevo1.setModalidad("online");
        costoNuevo1.setCosto(BigDecimal.valueOf(30));
        ModalidadCostoDTO costoNuevo2 = new ModalidadCostoDTO();
        costoNuevo2.setModalidad("presencial");
        costoNuevo2.setCosto(BigDecimal.valueOf(45));

        UpdateEntrenadorDTO dto = new UpdateEntrenadorDTO();
        dto.setCostos(List.of(costoNuevo1, costoNuevo2));

        when(entrenadorRepository.findById(id)).thenReturn(Optional.of(entrenador));
        when(entrenadorRepository.save(any(Entrenador.class))).thenAnswer(inv -> inv.getArgument(0));

        ModalidadCosto costo1 = new ModalidadCosto();
        costo1.setModalidad("online");
        costo1.setCosto(BigDecimal.valueOf(30));

        ModalidadCosto costo2 = new ModalidadCosto();
        costo2.setModalidad("presencial");
        costo2.setCosto(BigDecimal.valueOf(45));

        List<ModalidadCosto> costosEntidad = List.of(costo1,costo2);

        when(entrenadorMapper.toModalidadCostoList(dto.getCostos())).thenReturn(costosEntidad);
        doNothing().when(entrenadorMapper).updateEntrenadorFromDto(any(), any());

        Entrenador result = entrenadorService.patchEntrenador(id, dto);

        assertEquals(2, result.getCostos().size());
        assertTrue(result.getCostos().stream().anyMatch(c -> c.getModalidad().equals("online") && c.getCosto().equals(BigDecimal.valueOf(30))));
        assertTrue(result.getCostos().stream().anyMatch(c -> c.getModalidad().equals("presencial") && c.getCosto().equals(BigDecimal.valueOf(45))));

        verify(entrenadorRepository).save(any());
    }

}

