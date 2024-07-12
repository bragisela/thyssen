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
@Table(name = "ordendeproduccion")
public class OrdenDeProduccionModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "fechaAltaImpresion")
    private Date fechaAltaImpresion;
    
    @Column(name = "fechaCierre")
    private Date fechaCierre;

    @Column(name = "estado")
    private String estado;

    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "idArticulo")
    private Integer idArticulo;

    @Column(name = "idFichaTecnica")
    private Integer idFichaTecnica;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "fechaPedido")
    private Date fechaPedido;

    @Column(name = "idUnidadVenta")
    private Integer idUnidadVenta;

    @Column(name = "cantidadAProducir")
    private Integer cantidadAProducir;
    
    @Column(name = "idUsuarioAlta")
    private Integer idUsuarioAlta;

    @Column(name = "idUsuarioCierre")
    private Integer idUsuarioCierre;

    @Column(name = "fechaAbierta")
    private Date fechaAbierta;

    @Column(name = "idUsuarioAbierta")
    private Integer idUsuarioAbierta;

    @Column(name = "fechaFabricacion")
    private Date fechaFabricacion;

    @Column(name = "idUsuarioFabricacion")
    private Integer idUsuarioFabricacion;

    @Column(name = "fechaEmpaque")
    private Date fechaEmpaque;

    @Column(name = "idUsuarioEmpaque")
    private Integer idUsuarioEmpaque;

    @Column(name = "fechaPallet")
    private Date fechaPallet;

    @Column(name = "idUsuarioPallet")
    private Integer idUsuarioPallet;

    @Column(name = "stockActual")
    private Integer stockActual;
    
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

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Integer getIdUnidadVenta() {
        return idUnidadVenta;
    }

    public void setIdUnidadVenta(Integer idUnidadVenta) {
        this.idUnidadVenta = idUnidadVenta;
    }

    public Integer getCantidadAProducir() {
        return cantidadAProducir;
    }

    public void setCantidadAProducir(Integer cantidadAProducir) {
        this.cantidadAProducir = cantidadAProducir;
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

    public Integer getIdFichaTecnica() {
        return idFichaTecnica;
    }

    public void setIdFichaTecnica(Integer idFichaTecnica) {
        this.idFichaTecnica = idFichaTecnica;
    }

    public Date getFechaAltaImpresion() {
        return fechaAltaImpresion;
    }

    public void setFechaAltaImpresion(Date fechaAltaImpresion) {
        this.fechaAltaImpresion = fechaAltaImpresion;
    }

    public Date getFechaAbierta() {
        return fechaAbierta;
    }

    public void setFechaAbierta(Date fechaAbierta) {
        this.fechaAbierta = fechaAbierta;
    }

    public Integer getIdUsuarioAbierta() {
        return idUsuarioAbierta;
    }

    public void setIdUsuarioAbierta(Integer idUsuarioAbierta) {
        this.idUsuarioAbierta = idUsuarioAbierta;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Integer getIdUsuarioFabricacion() {
        return idUsuarioFabricacion;
    }

    public void setIdUsuarioFabricacion(Integer idUsuarioFabricacion) {
        this.idUsuarioFabricacion = idUsuarioFabricacion;
    }

    public Date getFechaEmpaque() {
        return fechaEmpaque;
    }

    public void setFechaEmpaque(Date fechaEmpaque) {
        this.fechaEmpaque = fechaEmpaque;
    }

    public Integer getIdUsuarioEmpaque() {
        return idUsuarioEmpaque;
    }

    public void setIdUsuarioEmpaque(Integer idUsuarioEmpaque) {
        this.idUsuarioEmpaque = idUsuarioEmpaque;
    }

    public Date getFechaPallet() {
        return fechaPallet;
    }

    public void setFechaPallet(Date fechaPallet) {
        this.fechaPallet = fechaPallet;
    }

    public Integer getIdUsuarioPallet() {
        return idUsuarioPallet;
    }

    public void setIdUsuarioPallet(Integer idUsuarioPallet) {
        this.idUsuarioPallet = idUsuarioPallet;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

  
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", idCliente=" + idCliente + "]";
    }
    
}