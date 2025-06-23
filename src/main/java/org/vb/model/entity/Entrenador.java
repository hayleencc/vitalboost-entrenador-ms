package org.vb.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entrenadores")
public class Entrenador {

    @Id
    private String id;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    private String profesion;
    private String especialidad;
    private String universidad;
    private String datosConsultorio;
    private String biografia;
    private String rol;

    @Column(name = "anios_experiencia")
    private int aniosExperiencia;

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ModalidadCosto> costos = new ArrayList<>();

    public Entrenador(){
    }

    public Entrenador(String id, String nombreCompleto, String email, String profesion, String especialidad,
                         String universidad, String datosConsultorio, String biografia,
                         int aniosExperiencia, List<ModalidadCosto> costos, String rol) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.profesion = profesion;
        this.especialidad = especialidad;
        this.universidad = universidad;
        this.datosConsultorio = datosConsultorio;
        this.biografia = biografia;
        this.aniosExperiencia = aniosExperiencia;
        this.costos = costos;
        this.rol=rol;
    }

    public String getId() { return id; }

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

    public String getDatosConsultorio() {
        return datosConsultorio;
    }

    public void setDatosConsultorio(String datosConsultorio) {
        this.datosConsultorio = datosConsultorio;
    }

    public List<ModalidadCosto> getCostos() {
        return costos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setCostos(List<ModalidadCosto> costos) {
        this.costos = costos;
    }

    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
