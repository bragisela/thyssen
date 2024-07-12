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
@Table(name = "ordendeproduccionbobina")
public class OrdenDeProduccionBobinaModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "idOrdenDeProduccion")
    private Integer idOrdenDeProduccion;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "pesoCono")
    private Double pesoCono;

    @Column(name = "pesoTotal")
    private Double pesoTotal;

    @Column(name = "pesoNeto")
    private Double pesoNeto;
        
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "idUsuarioAlta")
    private Integer idUsuarioAlta;

    @Column(name = "estaEnBulto")
    private Boolean estaEnBulto;

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

    public Double getPesoCono() {
        return pesoCono;
    }

    public void setPesoCono(Double pesoCono) {
        this.pesoCono = pesoCono;
    }

    public Double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Double getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(Double pesoNeto) {
        this.pesoNeto = pesoNeto;
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

    public Boolean getEstaEnBulto() {
        return estaEnBulto;
    }

    public void setEstaEnBulto(Boolean estaEnBulto) {
        this.estaEnBulto = estaEnBulto;
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