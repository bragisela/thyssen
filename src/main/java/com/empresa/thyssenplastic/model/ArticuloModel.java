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
@Table(name = "articulo")
public class ArticuloModel { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "denominacion")
    private String denominacion;

    @Column(name = "idRubro")
    private Integer idRubro;

    @Column(name = "idUnidadesVenta")
    private Integer idUnidadesVenta;

    @Column(name = "idTipoMaterial")
    private Integer idTipoMaterial;

    @Column(name = "scrap")
    private Double scrap;

    @Column(name = "espesor")
    private Double espesor;

    @Column(name = "ancho")
    private Double ancho;

    @Column(name = "alto")
    private Double alto;

    @Column(name = "tubo")
    private Boolean tubo;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "referenciaAdministrativa")
    private String referenciaAdministrativa;
    
    @Column(name = "observaciones")
    private String observaciones;
    
    @Column(name = "stock")
    private Integer stock;

    @Column(name = "stockPeso")
    private Double stockPeso;
    
    public ArticuloModel() {
    }

    public ArticuloModel(Integer idCliente, String denominacion) {
        this.idCliente = idCliente;
        this.denominacion = denominacion;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
       
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public Integer getIdUnidadesVenta() {
        return idUnidadesVenta;
    }

    public void setIdUnidadesVenta(Integer idUnidadesVenta) {
        this.idUnidadesVenta = idUnidadesVenta;
    }

    public Integer getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(Integer idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
    }

    public Double getScrap() {
        return scrap;
    }

    public void setScrap(Double scrap) {
        this.scrap = scrap;
    }

    public Double getEspesor() {
        return espesor;
    }

    public void setEspesor(Double espesor) {
        this.espesor = espesor;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Boolean getTubo() {
        return tubo;
    }

    public void setTubo(Boolean tubo) {
        this.tubo = tubo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getReferenciaAdministrativa() {
        return referenciaAdministrativa;
    }

    public void setReferenciaAdministrativa(String referenciaAdministrativa) {
        this.referenciaAdministrativa = referenciaAdministrativa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getStockPeso() {
        return stockPeso;
    }

    public void setStockPeso(Double stockPeso) {
        this.stockPeso = stockPeso;
    }
    
    
    @Override
    public String toString() {
        return "Articulo [id=" + id + ", idCliente=" + idCliente + ", denominacion=" + denominacion + "]";
    }
    
}