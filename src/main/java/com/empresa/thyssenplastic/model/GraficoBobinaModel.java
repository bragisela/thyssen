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
@Table(name = "graficobobina")
public class GraficoBobinaModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "idOrdenDeProduccionBobina")
    private Integer idOrdenDeProduccionBobina;

    @Column(name = "espesorNominal")
    private Double espesorNominal;

    @Column(name = "espesorNominalMas")
    private Double espesorNominalMas;

    @Column(name = "espesorNominalMenos")
    private Double espesorNominalMenos;
       
    @Column(name = "idUsuario")
    private Integer idUsuario;

    
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

    public Integer getIdOrdenDeProduccionBobina() {
        return idOrdenDeProduccionBobina;
    }

    public void setIdOrdenDeProduccionBobina(Integer idOrdenDeProduccionBobina) {
        this.idOrdenDeProduccionBobina = idOrdenDeProduccionBobina;
    }

    public Double getEspesorNominal() {
        return espesorNominal;
    }

    public void setEspesorNominal(Double espesorNominal) {
        this.espesorNominal = espesorNominal;
    }

    public Double getEspesorNominalMas() {
        return espesorNominalMas;
    }

    public void setEspesorNominalMas(Double espesorNominalMas) {
        this.espesorNominalMas = espesorNominalMas;
    }

    public Double getEspesorNominalMenos() {
        return espesorNominalMenos;
    }

    public void setEspesorNominalMenos(Double espesorNominalMenos) {
        this.espesorNominalMenos = espesorNominalMenos;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
  
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", idOrdenDeProduccionBobina=" + idOrdenDeProduccionBobina + "]";
    }
    
}