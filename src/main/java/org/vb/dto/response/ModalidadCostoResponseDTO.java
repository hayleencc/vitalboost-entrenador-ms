package org.vb.dto.response;

import java.math.BigDecimal;

public class ModalidadCostoResponseDTO {
    private String modalidad;
    private BigDecimal costo;

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
}
