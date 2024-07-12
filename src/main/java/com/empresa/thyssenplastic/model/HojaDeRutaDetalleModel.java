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
@Table(name = "hojaderutadetalle")
public class HojaDeRutaDetalleModel { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "idHojaDeRuta")
    private Integer idHojaDeRuta;

    @Column(name = "idRemito")
    private Integer idRemito;
    
    @Column(name = "km")
    private Double km;

    @Column(name = "orden")
    private Integer orden;
    
    @Column(name = "idUsuarioAlta")
    private Integer idUsuarioAlta;

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

    public Integer getIdHojaDeRuta() {
        return idHojaDeRuta;
    }

    public void setIdHojaDeRuta(Integer idHojaDeRuta) {
        this.idHojaDeRuta = idHojaDeRuta;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Integer getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

  
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + "]";
    }
    
}