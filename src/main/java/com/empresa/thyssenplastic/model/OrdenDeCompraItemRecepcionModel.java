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
@Table(name = "orderdecompraitemrecepcion")
public class OrdenDeCompraItemRecepcionModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "idOrdenDeCompraItem")
    private Integer idOrdenDeCompraItem;

    @Column(name = "fechaRecepcion")
    private Date fechaRecepcion;

    @Column(name = "cantidadRecepcionada")
    private Integer cantidadRecepcionada;

    @Column(name = "lote")
    private String lote;

    @Column(name = "refenciaAdministrativa")
    private String refenciaAdministrativa;

    @Column(name = "idUsuarioRecepcion")
    private Integer idUsuarioRecepcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOrdenDeCompraItem() {
        return idOrdenDeCompraItem;
    }

    public void setIdOrdenDeCompraItem(Integer idOrdenDeCompraItem) {
        this.idOrdenDeCompraItem = idOrdenDeCompraItem;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Integer getCantidadRecepcionada() {
        return cantidadRecepcionada;
    }

    public void setCantidadRecepcionada(Integer cantidadRecepcionada) {
        this.cantidadRecepcionada = cantidadRecepcionada;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getIdUsuarioRecepcion() {
        return idUsuarioRecepcion;
    }

    public void setIdUsuarioRecepcion(Integer idUsuarioRecepcion) {
        this.idUsuarioRecepcion = idUsuarioRecepcion;
    }

    public String getRefenciaAdministrativa() {
        return refenciaAdministrativa;
    }

    public void setRefenciaAdministrativa(String refenciaAdministrativa) {
        this.refenciaAdministrativa = refenciaAdministrativa;
    }

 
    @Override
    public String toString() {
        return "User [id=" + id + ", idOrdenDeCompraItem=" + idOrdenDeCompraItem + ", fechaRecepcion=" + fechaRecepcion + ", lote=" + lote + "]";
    }
    
}