package org.vb.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "modalidad_costos")
public class ModalidadCosto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String modalidad;
    private BigDecimal costo;

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public UUID getId() {
        return id;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    @ManyToOne
    @JoinColumn(name = "entrenador_id")
    private Entrenador entrenador;

    public ModalidadCosto(){}


}
