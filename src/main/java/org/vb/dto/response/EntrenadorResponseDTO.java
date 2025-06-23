package org.vb.dto.response;

import java.util.List;

public class EntrenadorResponseDTO {

    private String id;
    private String nombreCompleto;
    private String email;
    private String profesion;
    private String especialidad;
    private String universidad;
    private int aniosExperiencia;
    private List<ModalidadCostoResponseDTO> costos;
    private String biografia;
    private String datosConsultorio;
    private String rol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public List<ModalidadCostoResponseDTO> getCostos() {
        return costos;
    }

    public void setCostos(List<ModalidadCostoResponseDTO> costos) {
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
