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
@Table(name = "remitodetallescrap")
public class RemitoDetalleScrapModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;
    
    @Column(name = "codigo")
    private String codigo;
    
    @Column(name = "idOrdenDeProduccionScrap")
    private Integer idOrdenDeProduccionScrap;
    
    @Column(name = "idOrdenDeProduccion")
    private Integer idOrdenDeProduccion;
    
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "idRemito")
    private Integer idRemito;
       
    @Column(name = "idUsuarioAlta")
    private Integer idUsuarioAlta;

    @Column(name = "cantidadUtilizadaRemito")
    private Double cantidadUtilizadaRemito;
    
    @Column(name = "utilizadoAlCien")
    private Boolean utilizadoAlCien;
    
     @Column(name = "dadoDeBaja")
    private Boolean dadoDeBaja;

    public Boolean getDadoDeBaja() {
        return dadoDeBaja;
    }

    public void setDadoDeBaja(Boolean dadoDeBaja) {
        this.dadoDeBaja = dadoDeBaja;
    }
     

    public Boolean getUtilizadoAlCien() {
        return utilizadoAlCien;
    }

    public void setUtilizadoAlCien(Boolean utilizadoAlCien) {
        this.utilizadoAlCien = utilizadoAlCien;
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
    
     public Integer getIdOrdenDeProduccionScrap() {
        return idOrdenDeProduccionScrap;
    }

    public void setIdOrdenDeProduccionScrap(Integer idOrdenDeProduccionScrap) {
        this.idOrdenDeProduccionScrap = idOrdenDeProduccionScrap;
    }

    public Integer getIdOrdenDeProduccion() {
        return idOrdenDeProduccion;
    }

    public void setIdOrdenDeProduccion(Integer idOrdenDeProduccion) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Integer getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public Double getCantidadUtilizadaRemito() {
        return cantidadUtilizadaRemito;
    }

    public void setCantidadUtilizadaRemito(Double cantidadUtilizadaRemito) {
        this.cantidadUtilizadaRemito = cantidadUtilizadaRemito;
    }
   
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fecha + ", idRemito=" + idRemito + "]";
    }
    
}