package org.vb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vb.model.entity.Entrenador;

import java.util.List;
import java.util.UUID;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, UUID> {

    @Query("SELECT DISTINCT e FROM Entrenador e LEFT JOIN e.costos c " +
            "WHERE (:especialidad IS NULL OR LOWER(e.especialidad) LIKE LOWER(CONCAT('%', :especialidad, '%'))) " +
            "AND (:modalidad IS NULL OR LOWER(c.modalidad) LIKE LOWER(CONCAT('%', :modalidad, '%')))"
    )
    List<Entrenador> searchEntrenadores(
            @Param("especialidad") String especialidad,
            @Param("modalidad") String modalidad
    );
}
