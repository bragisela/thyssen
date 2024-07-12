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
public class OrdenDeProduccionBobinaDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String codigo;
    private String pesoTotal;
    private String pesoNeto;
    private String espesor;
    private String estado;
    private String estadoLabel;
    private String estaEnBulto;
    private String estaEnBultoLabel;
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

    public String getEstaEnBulto() {
        return estaEnBulto;
    }

    public void setEstaEnBulto(String estaEnBulto) {
        this.estaEnBulto = estaEnBulto;
    }

    public String getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(String pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public String getEspesor() {
        return espesor;
    }

    public void setEspesor(String espesor) {
        this.espesor = espesor;
    }

    public String getEstaEnBultoLabel() {
        return estaEnBultoLabel;
    }

    public void setEstaEnBultoLabel(String estaEnBultoLabel) {
        this.estaEnBultoLabel = estaEnBultoLabel;
    }

    public Boolean getEstaEnDeposito() {
        return estaEnDeposito;
    }

    public void setEstaEnDeposito(Boolean estaEnDeposito) {
        this.estaEnDeposito = estaEnDeposito;
    }
    
    
}
