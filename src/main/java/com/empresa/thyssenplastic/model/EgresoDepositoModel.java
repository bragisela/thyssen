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
@Table(name = "egresodeposito")
public class EgresoDepositoModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "fechaBaja")
    private Date fechaBaja;

    @Column(name = "idBobina")
    private Integer idBobina;

    @Column(name = "idBulto")
    private Integer idBulto;

    @Column(name = "idPallet")
    private Integer idPallet;

    @Column(name = "idDeposito")
    private Integer idDeposito;
   
    @Column(name = "idRemito")
    private Integer idRemito;

    @Column(name = "idUsuarioBaja")
    private Integer idUsuarioBaja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdUsuarioBaja() {
        return idUsuarioBaja;
    }

    public void setIdUsuarioBaja(Integer idUsuarioBaja) {
        this.idUsuarioBaja = idUsuarioBaja;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

 
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaBaja=" + fechaBaja + ", remito=" + idRemito + "]";
    }
    
}