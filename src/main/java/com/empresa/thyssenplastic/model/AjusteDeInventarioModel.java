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
@Table(name = "inventario")
public class AjusteDeInventarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "idArticulo")
    private Integer idArticulo;

    @Column(name = "idMateriaPrima")
    private Integer idMateriaPrima;

    @Column(name = "idInsumo")
    private Integer idInsumo;

    @Column(name = "lote")
    private String lote;

    @Column(name = "idDeposito")
    private Integer idDeposito;

    @Column(name = "existentePeso")
    private Double existentePeso;

    @Column(name = "existenteCantidad")
    private Double existenteCantidad;

    @Column(name = "ajustePeso")
    private Double ajustePeso;

    @Column(name = "ajusteCantidad")
    private Double ajusteCantidad;

    @Column(name = "resultadoPeso")
    private Double resultadoPeso;

    @Column(name = "resultadoCantidad")
    private Double resultadoCantidad;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "idUser")
    private Integer idUser;
    
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


    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(Integer idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public Integer getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Integer idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Double getExistentePeso() {
        return existentePeso;
    }

    public void setExistentePeso(Double existentePeso) {
        this.existentePeso = existentePeso;
    }

    public Double getExistenteCantidad() {
        return existenteCantidad;
    }

    public void setExistenteCantidad(Double existenteCantidad) {
        this.existenteCantidad = existenteCantidad;
    }

    public Double getAjustePeso() {
        return ajustePeso;
    }

    public void setAjustePeso(Double ajustePeso) {
        this.ajustePeso = ajustePeso;
    }

    public Double getAjusteCantidad() {
        return ajusteCantidad;
    }

    public void setAjusteCantidad(Double ajusteCantidad) {
        this.ajusteCantidad = ajusteCantidad;
    }

    public Double getResultadoPeso() {
        return resultadoPeso;
    }

    public void setResultadoPeso(Double resultadoPeso) {
        this.resultadoPeso = resultadoPeso;
    }

    public Double getResultadoCantidad() {
        return resultadoCantidad;
    }

    public void setResultadoCantidad(Double resultadoCantidad) {
        this.resultadoCantidad = resultadoCantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", tipo=" + tipo + ",idArticulo=" + idArticulo + ",idMateriaPrima=" + idArticulo + ",idInsumo=" + idInsumo + "]";
    }

}
