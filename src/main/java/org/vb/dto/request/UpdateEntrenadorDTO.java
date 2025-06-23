package org.vb.dto.request;


import java.util.List;

public class UpdateEntrenadorDTO {
    private String nombreCompleto;
    private String email;
    private String profesion;
    private String especialidad;
    private String universidad;
    private int aniosExperiencia;
    private List<ModalidadCostoDTO> costos;
    private String biografia;
    private String datosConsultorio;
    private List<String> modalidades;

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getProfesion() { return profesion; }
    public void setProfesion(String profesion) { this.profesion = profesion; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getUniversidad() { return universidad; }
    public void setUniversidad(String universidad) { this.universidad = universidad; }

    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }

    public List<ModalidadCostoDTO> getCostos() {
        return costos;
    }

    public void setCostos(List<ModalidadCostoDTO> costos) {
        this.costos = costos;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getDatosConsultorio() {
        return datosConsultorio;
    }

    public void setDatosConsultorio(String datosConsultorio) {
        this.datosConsultorio = datosConsultorio;
    }

    public List<String> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<String> modalidades) {
        this.modalidades = modalidades;
    }
}
