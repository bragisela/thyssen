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
@Table(name = "ordendeproduccionscrap")
public class OrdenDeProduccionScrapModel { 

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

    @Column(name = "idOrigen")
    private Integer idOrigen;
    
    @Column(name = "idTipoMaterial")
    private Integer idTipoMaterial;

    @Column(name = "idMotivo")
    private Integer idMotivo;

    @Column(name = "idFormato")
    private Integer idFormato;
    
    @Column(name = "esRecuperable")
    private Boolean esRecuperable;

    @Column(name = "materialImpreso")
    private Boolean materialImpreso;

    @Column(name = "pesoTotal")
    private Double pesoTotal;

    @Column(name = "estado")
    private String estado;
    
    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "idUsuarioAlta")
    private Integer idUsuarioAlta;
    
    @Column(name = "cantidadUtilizada")
    private Double cantidadUtilizada;

    public Double getCantidadUtilizada() {
        return cantidadUtilizada;
    }

    public void setCantidadUtilizada(Double cantidadUtilizada) {
        this.cantidadUtilizada = cantidadUtilizada;
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

    public Double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
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

    public Integer getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Integer idOrigen) {
        this.idOrigen = idOrigen;
    }

    public Integer getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(Integer idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
    }

    public Integer getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Integer idMotivo) {
        this.idMotivo = idMotivo;
    }

    public Integer getIdFormato() {
        return idFormato;
    }

    public void setIdFormato(Integer idFormato) {
        this.idFormato = idFormato;
    }

    public Boolean getEsRecuperable() {
        return esRecuperable;
    }

    public void setEsRecuperable(Boolean esRecuperable) {
        this.esRecuperable = esRecuperable;
    }

    public Boolean getMaterialImpreso() {
        return materialImpreso;
    }

    public void setMaterialImpreso(Boolean materialImpreso) {
        this.materialImpreso = materialImpreso;
    }

 
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", idOrdenDeProduccion=" + idOrdenDeProduccion + "]";
    }
    
}