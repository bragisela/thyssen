/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.model;

import java.util.Date;
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
@Table(name = "hojaderuta")
public class HojaDeRutaModel { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "fechaCarga")
    private Date fechaCarga;
    
    @Column(name = "fechaSalida")
    private Date fechaSalida;

    @Column(name = "horaCarga")
    private String horaCarga;
    
    @Column(name = "horaSalida")
    private String horaSalida;
    
    @Column(name = "idChofer")
    private Integer idChofer;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observaciones")
    private String observaciones;
    
    @Column(name = "idUsuarioAlta")
    private Integer idUsuarioAlta;

    @Column(name = "fechaEstadoAbierto")
    private Date fechaEstadoAbierto;

    @Column(name = "idUsuarioEstadoAbierto")
    private Integer idUsuarioEstadoAbierto;

    @Column(name = "fechaEstadoCerrado")
    private Date fechaEstadoCerrado;

    @Column(name = "idUsuarioEstadoCerrado")
    private Integer idUsuarioEstadoCerrado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(Integer idChofer) {
        this.idChofer = idChofer;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public String getHoraCarga() {
        return horaCarga;
    }

    public void setHoraCarga(String horaCarga) {
        this.horaCarga = horaCarga;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getFechaEstadoAbierto() {
        return fechaEstadoAbierto;
    }

    public void setFechaEstadoAbierto(Date fechaEstadoAbierto) {
        this.fechaEstadoAbierto = fechaEstadoAbierto;
    }

    public Integer getIdUsuarioEstadoAbierto() {
        return idUsuarioEstadoAbierto;
    }

    public void setIdUsuarioEstadoAbierto(Integer idUsuarioEstadoAbierto) {
        this.idUsuarioEstadoAbierto = idUsuarioEstadoAbierto;
    }

    public Date getFechaEstadoCerrado() {
        return fechaEstadoCerrado;
    }

    public void setFechaEstadoCerrado(Date fechaEstadoCerrado) {
        this.fechaEstadoCerrado = fechaEstadoCerrado;
    }

    public Integer getIdUsuarioEstadoCerrado() {
        return idUsuarioEstadoCerrado;
    }

    public void setIdUsuarioEstadoCerrado(Integer idUsuarioEstadoCerrado) {
        this.idUsuarioEstadoCerrado = idUsuarioEstadoCerrado;
    }

   
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", fecha=" + fecha + "]";
    }
    
}