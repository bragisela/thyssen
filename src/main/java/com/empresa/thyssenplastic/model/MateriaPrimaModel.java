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
@Table(name = "materiaprima")
public class MateriaPrimaModel { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "idTipo")
    private Integer idTipo;

    @Column(name = "idDenominacion")
    private Integer idDenominacion;

    @Column(name = "idPetroquimica")
    private Integer idPetroquimica;

    @Column(name = "idProveedor")
    private Integer idProveedor;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "urlFichaTecnica")
    private String urlFichaTecnica;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "stockPeso")
    private Double stockPeso;
    
    public MateriaPrimaModel() {
    }

    public MateriaPrimaModel(String descripcion, Integer idTipo, Integer idDenominacion, Integer idPetroquimica, Integer idProveedor) {
        this.descripcion = descripcion;
        this.idProveedor = idProveedor;
        this.idTipo = idTipo;
        this.idDenominacion = idDenominacion;
        this.idPetroquimica = idPetroquimica;
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

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdDenominacion() {
        return idDenominacion;
    }

    public void setIdDenominacion(Integer idDenominacion) {
        this.idDenominacion = idDenominacion;
    }

    public Integer getIdPetroquimica() {
        return idPetroquimica;
    }

    public void setIdPetroquimica(Integer idPetroquimica) {
        this.idPetroquimica = idPetroquimica;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUrlFichaTecnica() {
        return urlFichaTecnica;
    }

    public void setUrlFichaTecnica(String urlFichaTecnica) {
        this.urlFichaTecnica = urlFichaTecnica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        return "User [id=" + id + ", descripcion=" + descripcion + ", idProveedor=" + idProveedor + ", idTipo=" + idTipo + ", idDenominacion=" + idDenominacion + ", ididPetroquimica=" + idPetroquimica + "]";
    }
    
}