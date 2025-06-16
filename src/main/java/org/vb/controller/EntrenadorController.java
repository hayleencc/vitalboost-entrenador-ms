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
import org.vb.model.entity.Entrenador;
import org.vb.service.EntrenadorService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coaches")
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
    public ResponseEntity<Entrenador> createCoach(@Valid @RequestBody CreateEntrenadorDTO entrenador) {
        Entrenador nuevoEntrenador = entrenadorService.createEntrenador(entrenador);
        return new ResponseEntity<>(nuevoEntrenador, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar coaches registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coaches obtenidos correctamente")
    })
    @GetMapping
    public List<Entrenador> getAllCoaches(
            @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) String modalidad
    ) {
        return entrenadorService.searchEntrenadores(especialidad, modalidad);
    }

    @Operation(summary = "Listar un coach por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Coach no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> getCoachById(@PathVariable UUID id) {
        Entrenador entrenador = entrenadorService.findCoachById(id);
        return ResponseEntity.ok(entrenador);
    }

    @Operation(summary = "Actualizar parcialmente un coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Coach no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Entrenador> updateCoachPartial(@PathVariable UUID id, @RequestBody UpdateEntrenadorDTO coachDetails) throws JsonPatchException, IOException {
        Entrenador updatedEntrenador = entrenadorService.patchEntrenador(id, coachDetails);
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
