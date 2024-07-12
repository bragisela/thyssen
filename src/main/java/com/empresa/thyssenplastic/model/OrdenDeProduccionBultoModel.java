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
@Table(name = "ordendeproduccionbulto")
public class OrdenDeProduccionBultoModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "idOrdenDeProduccion")
    private Integer idOrdenDeProduccion;

    @Column(name = "idOrdenDeProduccionBobina")
    private Integer idOrdenDeProduccionBobina;

    @Column(name = "idPlegadora")
    private Integer idPlegadora;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "estado")
    private String estado;
    
    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "idUsuarioAlta")
    private Integer idUsuarioAlta;
    
    @Column(name = "estaEnPallet")
    private Boolean estaEnPallet;
    
    @Column(name = "estaDisponibleParaRemito")
    private Boolean estaDisponibleParaRemito;
    
    @Column(name = "idDeposito")
    private Integer idDeposito;
    
    @Column(name = "idRemito")
    private Integer idRemito;

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }
    
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

    public Integer getIdOrdenDeProduccion() {
        return idOrdenDeProduccion;
    }

    public void setIdOrdenDeProduccion(Integer idOrdenDeProduccion) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Integer getIdOrdenDeProduccionBobina() {
        return idOrdenDeProduccionBobina;
    }

    public void setIdOrdenDeProduccionBobina(Integer idOrdenDeProduccionBobina) {
        this.idOrdenDeProduccionBobina = idOrdenDeProduccionBobina;
    }

    public Integer getIdPlegadora() {
        return idPlegadora;
    }

    public void setIdPlegadora(Integer idPlegadora) {
        this.idPlegadora = idPlegadora;
    }

    public Boolean getEstaEnPallet() {
        return estaEnPallet;
    }

    public void setEstaEnPallet(Boolean estaEnPallet) {
        this.estaEnPallet = estaEnPallet;
    }

    public Boolean getEstaDisponibleParaRemito() {
        return estaDisponibleParaRemito;
    }

    public void setEstaDisponibleParaRemito(Boolean estaDisponibleParaRemito) {
        this.estaDisponibleParaRemito = estaDisponibleParaRemito;
    }

    public Integer getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Integer idDeposito) {
        this.idDeposito = idDeposito;
    }

 
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", idOrdenDeProduccion=" + idOrdenDeProduccion + "]";
    }
    
}