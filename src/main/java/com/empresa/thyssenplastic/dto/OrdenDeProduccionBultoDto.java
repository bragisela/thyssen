/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dto;

import java.io.Serializable;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionBultoDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String codigo;
    private String idBobina;
    private String pesoTotal;
    private String pesoNeto;
    private String estado;
    private String estadoLabel;    
    private String estaEnPallet;    
    private String estaEnPalletLabel;    
    private String plegadora;
    private String observaciones;
    private String idOrdenDeProduccion;
    private Boolean estaEnDeposito;
    
    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(String pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public String getEstadoLabel() {
        return estadoLabel;
    }

    public void setEstadoLabel(String estadoLabel) {
        this.estadoLabel = estadoLabel;
    }

    public String getEstaEnPallet() {
        return estaEnPallet;
    }

    public void setEstaEnPallet(String estaEnPallet) {
        this.estaEnPallet = estaEnPallet;
    }

    public String getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(String pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public String getPlegadora() {
        return plegadora;
    }

    public void setPlegadora(String plegadora) {
        this.plegadora = plegadora;
    }

    public String getEstaEnPalletLabel() {
        return estaEnPalletLabel;
    }

    public void setEstaEnPalletLabel(String estaEnPalletLabel) {
        this.estaEnPalletLabel = estaEnPalletLabel;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }
    
    public void setIdBobina(String idBobina) {
        this.idBobina = idBobina;
    }

    public String getIdBobina() {
        return idBobina;
    }
    
    public String getIdOrdenDeProduccion() {
        return idOrdenDeProduccion;
    }

    public void setIdOrdenDeProduccion(String idOrdenDeProduccion) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
    }
    
    public Boolean getEstaEnDeposito() {
        return estaEnDeposito;
    }

    public void setEstaEnDeposito(Boolean estaEnDeposito) {
        this.estaEnDeposito = estaEnDeposito;
    }

    
}
