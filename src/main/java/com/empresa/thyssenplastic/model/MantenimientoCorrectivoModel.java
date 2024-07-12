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
@Table(name = "mantenimientocorrectivo")
public class MantenimientoCorrectivoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "idMaquina")
    private Integer idMaquina;

    @Column(name = "componente")
    private String componente;

    @Column(name = "horaParada")
    private String horaParada;

    @Column(name = "prioridad")
    private String prioridad;

    @Column(name = "problema")
    private String problema;

    @Column(name = "horaArranque")
    private String horaArranque;

    @Column(name = "idRepuesto")
    private Integer idRepuesto;

    @Column(name = "fechaFin")
    private Date fechaFin;

    @Column(name = "actividadRealizada")
    private String actividadRealizada;

    @Column(name = "idTipoReparacion")
    private Integer idTipoReparacion;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "idUserAlta")
    private Integer idUserAlta;

    @Column(name = "idUserFin")
    private Integer idUserFin;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fechaDeReparacionDesde")
    private String fechaDeReparacionDesde;

    @Column(name = "fechaDeReparacionHasta")
    private String fechaDeReparacionHasta;

    @Column(name = "intervaloReparacion")
    private Integer intervaloReparacion;
    
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

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getHoraParada() {
        return horaParada;
    }

    public void setHoraParada(String horaParada) {
        this.horaParada = horaParada;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getHoraArranque() {
        return horaArranque;
    }

    public void setHoraArranque(String horaArranque) {
        this.horaArranque = horaArranque;
    }

    public Integer getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(Integer idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getActividadRealizada() {
        return actividadRealizada;
    }

    public void setActividadRealizada(String actividadRealizada) {
        this.actividadRealizada = actividadRealizada;
    }

    public Integer getIdTipoReparacion() {
        return idTipoReparacion;
    }

    public void setIdTipoReparacion(Integer idTipoReparacion) {
        this.idTipoReparacion = idTipoReparacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getIdUserAlta() {
        return idUserAlta;
    }

    public void setIdUserAlta(Integer idUserAlta) {
        this.idUserAlta = idUserAlta;
    }

    public Integer getIdUserFin() {
        return idUserFin;
    }

    public void setIdUserFin(Integer idUserFin) {
        this.idUserFin = idUserFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaDeReparacionDesde() {
        return fechaDeReparacionDesde;
    }

    public void setFechaDeReparacionDesde(String fechaDeReparacionDesde) {
        this.fechaDeReparacionDesde = fechaDeReparacionDesde;
    }

    public String getFechaDeReparacionHasta() {
        return fechaDeReparacionHasta;
    }

    public void setFechaDeReparacionHasta(String fechaDeReparacionHasta) {
        this.fechaDeReparacionHasta = fechaDeReparacionHasta;
    }

    public Integer getIntervaloReparacion() {
        return intervaloReparacion;
    }

    public void setIntervaloReparacion(Integer intervaloReparacion) {
        this.intervaloReparacion = intervaloReparacion;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ",idMaquina=" + idMaquina + ",idUserAltaa=" + idUserAlta + "]";
    }

}
