package org.vb.controller;

import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vb.dto.request.CreateEntrenadorDTO;
import org.vb.dto.request.UpdateEntrenadorDTO;
import org.vb.dto.response.EntrenadorResponseDTO;
import org.vb.model.entity.Entrenador;
import org.vb.service.EntrenadorService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/entrenadores", "/entrenadores/"})
public class EntrenadorController{

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @Operation(summary = "Crear un coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Ocurrión un error")
    })
    @PostMapping
    public ResponseEntity<?> createEntrenador(@Valid @RequestBody CreateEntrenadorDTO entrenador) {
        try {
            EntrenadorResponseDTO nuevoEntrenador = entrenadorService.createEntrenador(entrenador);
            return new ResponseEntity<>(nuevoEntrenador, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear entrenador: " + e.getMessage());
        }
    }

    @Operation(summary = "Listar coaches registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coaches obtenidos correctamente")
    })
    @GetMapping
    public List<EntrenadorResponseDTO> getAllCoaches(
            @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) String modalidad
    ) {
        return entrenadorService.getEntrenadores(especialidad, modalidad);
    }

    @Operation(summary = "Listar un coach por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Coach no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorResponseDTO> getCoachById(@PathVariable String id) {
        EntrenadorResponseDTO entrenador = entrenadorService.getEntrenadorById(id);
        return ResponseEntity.ok(entrenador);
    }

    @Operation(summary = "Actualizar parcialmente un coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Coach no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<EntrenadorResponseDTO> updateCoachPartial(@PathVariable String id, @RequestBody UpdateEntrenadorDTO coachDetails) throws JsonPatchException, IOException {
        EntrenadorResponseDTO updatedEntrenador = entrenadorService.patchEntrenador(id, coachDetails);
        return ResponseEntity.ok(updatedEntrenador);
    }
/*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable UUID id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }

 */
}
