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
@Table(name = "orderdecompra")
public class OrdenDeCompraModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "fechaAltaImpresion")
    private Date fechaAltaImpresion;

    @Column(name = "fechaEntrega")
    private Date fechaEntrega;

    @Column(name = "estado")
    private String estado;

    @Column(name = "idProveedor")
    private Integer idProveedor;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "fechaCierreOrden")
    private Date fechaCierreOrden;
    
    @Column(name = "idUsuarioAlta")
    private Integer idUsuarioAlta;

    @Column(name = "idUsuarioCierre")
    private Integer idUsuarioCierre;

    @Column(name = "referenciaAdministrativa")
    private String referenciaAdministrativa;
    
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

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaCierreOrden() {
        return fechaCierreOrden;
    }

    public void setFechaCierreOrden(Date fechaCierreOrden) {
        this.fechaCierreOrden = fechaCierreOrden;
    }

    public Integer getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public Integer getIdUsuarioCierre() {
        return idUsuarioCierre;
    }

    public void setIdUsuarioCierre(Integer idUsuarioCierre) {
        this.idUsuarioCierre = idUsuarioCierre;
    }

    public String getReferenciaAdministrativa() {
        return referenciaAdministrativa;
    }

    public void setReferenciaAdministrativa(String referenciaAdministrativa) {
        this.referenciaAdministrativa = referenciaAdministrativa;
    }

    public Date getFechaAltaImpresion() {
        return fechaAltaImpresion;
    }

    public void setFechaAltaImpresion(Date fechaAltaImpresion) {
        this.fechaAltaImpresion = fechaAltaImpresion;
    }

   
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", idProveedor=" + idProveedor + "]";
    }
    
}