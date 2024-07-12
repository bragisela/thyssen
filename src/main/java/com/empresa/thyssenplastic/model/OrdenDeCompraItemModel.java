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
@Table(name = "orderdecompraitem")
public class OrdenDeCompraItemModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "idOrdenDeCompra")
    private Integer idOrdenDeCompra;

    @Column(name = "idMateriaPrima")
    private Integer idMateriaPrima;

    @Column(name = "idInsumo")
    private Integer idInsumo;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "fechaCompletado")
    private Date fechaCompletado;

    @Column(name = "estaCompletado")
    private Boolean estaCompletado;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "superaCantidad")
    private Boolean superaCantidad;

    @Column(name = "faltaCantidad")
    private Boolean faltaCantidad;

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

    public Integer getIdOrdenDeCompra() {
        return idOrdenDeCompra;
    }

    public void setIdOrdenDeCompra(Integer idOrdenDeCompra) {
        this.idOrdenDeCompra = idOrdenDeCompra;
    }

    public Integer getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(Integer idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaCompletado() {
        return fechaCompletado;
    }

    public void setFechaCompletado(Date fechaCompletado) {
        this.fechaCompletado = fechaCompletado;
    }

    public Boolean getEstaCompletado() {
        return estaCompletado;
    }

    public void setEstaCompletado(Boolean estaCompletado) {
        this.estaCompletado = estaCompletado;
    }

    public Integer getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getSuperaCantidad() {
        return superaCantidad;
    }

    public void setSuperaCantidad(Boolean superaCantidad) {
        this.superaCantidad = superaCantidad;
    }

    public Boolean getFaltaCantidad() {
        return faltaCantidad;
    }

    public void setFaltaCantidad(Boolean faltaCantidad) {
        this.faltaCantidad = faltaCantidad;
    }
  
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", idOrderDeCompra=" + idOrdenDeCompra + ", idMateriaPrima=" + idMateriaPrima + "]";
    }
    
}