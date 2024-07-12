/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "domicilio")
public class DomicilioModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "idTipo")
    private Integer idTipo;

    @Column(name = "idPais")
    private Integer idPais;

    @Column(name = "idProvincia")
    private Integer idProvincia;

    @Column(name = "idLocalidad")
    private Integer idLocalidad;

    @Column(name = "idContacto")
    private Integer idContacto;
    
    @Column(name = "nombreContacto")
    private String nombreContacto;

    @Column(name = "horarioContacto")
    private String horarioContacto;

    @Column(name = "telefonoContacto")
    private String telefonoContacto;

    @Column(name = "observacionesContacto")
    private String observacionesContacto;
    
    @Column(name = "puntoGps")
    private String puntoGps;

    
    public DomicilioModel() {
    }

    public DomicilioModel(String nombre, String ubicacion) {
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }

    public String getPuntoGps() {
        return puntoGps;
    }

    public void setPuntoGps(String puntoGps) {
        this.puntoGps = puntoGps;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }
    
    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getHorarioContacto() {
        return horarioContacto;
    }

    public void setHorarioContacto(String horarioContacto) {
        this.horarioContacto = horarioContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getObservacionesContacto() {
        return observacionesContacto;
    }

    public void setObservacionesContacto(String observacionesContacto) {
        this.observacionesContacto = observacionesContacto;
    }

    
    @Override
    public String toString() {
        return "User [id=" + id + ", ubicacion=" + ubicacion + ", nombre=" + nombre + "]";
    }
    
}