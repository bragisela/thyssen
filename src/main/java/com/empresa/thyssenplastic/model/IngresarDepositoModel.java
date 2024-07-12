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
@Table(name = "ingresardeposito")
public class IngresarDepositoModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "idBobina")
    private Integer idBobina;

    @Column(name = "idBulto")
    private Integer idBulto;

    @Column(name = "idPallet")
    private Integer idPallet;

    @Column(name = "idDeposito")
    private Integer idDeposito;
    
    @Column(name = "tipo")
    private String tipo;

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

    public Integer getIdBobina() {
        return idBobina;
    }

    public void setIdBobina(Integer idBobina) {
        this.idBobina = idBobina;
    }

    public Integer getIdBulto() {
        return idBulto;
    }

    public void setIdBulto(Integer idBulto) {
        this.idBulto = idBulto;
    }

    public Integer getIdPallet() {
        return idPallet;
    }

    public void setIdPallet(Integer idPallet) {
        this.idPallet = idPallet;
    }

    public Integer getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Integer idDeposito) {
        this.idDeposito = idDeposito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

 
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", tipo=" + tipo + "]";
    }
    
}