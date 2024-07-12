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
@Table(name = "ordendeproduccionpalletbulto")
public class OrdenDeProduccionPalletBultoModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "idOrdenDeProduccionPallet")
    private Integer idOrdenDeProduccionPallet;

    @Column(name = "idOrdenDeProduccionBulto")
    private Integer idOrdenDeProduccionBulto;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

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

    public Integer getIdOrdenDeProduccionPallet() {
        return idOrdenDeProduccionPallet;
    }

    public void setIdOrdenDeProduccionPallet(Integer idOrdenDeProduccionPallet) {
        this.idOrdenDeProduccionPallet = idOrdenDeProduccionPallet;
    }

    public Integer getIdOrdenDeProduccionBulto() {
        return idOrdenDeProduccionBulto;
    }

    public void setIdOrdenDeProduccionBulto(Integer idOrdenDeProduccionBulto) {
        this.idOrdenDeProduccionBulto = idOrdenDeProduccionBulto;
    }

    public Integer getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", idOrdenDeProduccionPallet=" + idOrdenDeProduccionPallet + ", idOrdenDeProduccionBulto=" + idOrdenDeProduccionBulto + "]";
    }
    
}