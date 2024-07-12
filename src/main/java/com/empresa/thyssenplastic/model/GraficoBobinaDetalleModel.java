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
@Table(name = "graficobobinadetalle")
public class GraficoBobinaDetalleModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "idGraficoBobina")
    private Integer idGraficoBobina;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "angulo")
    private Double angulo;
       
    @Column(name = "idUsuario")
    private Integer idUsuario;
    
    @Column(name = "medicion")
    private Integer medicion;

    
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

   public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdGraficoBobina() {
        return idGraficoBobina;
    }

    public void setIdGraficoBobina(Integer idGraficoBobina) {
        this.idGraficoBobina = idGraficoBobina;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getAngulo() {
        return angulo;
    }

    public void setAngulo(Double angulo) {
        this.angulo = angulo;
    }

    public Integer getMedicion() {
        return medicion;
    }

    public void setMedicion(Integer medicion) {
        this.medicion = medicion;
    }
    
  
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", idGraficoBobina=" + idGraficoBobina + "]";
    }
    
}