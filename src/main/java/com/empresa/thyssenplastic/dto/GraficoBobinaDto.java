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
public class GraficoBobinaDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String mediciones;
    private String espesorNominal;
    private String tieneMediciones;
    private String codigo;

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

    public String getMediciones() {
        return mediciones;
    }

    public void setMediciones(String mediciones) {
        this.mediciones = mediciones;
    }

    public String getEspesorNominal() {
        return espesorNominal;
    }

    public void setEspesorNominal(String espesorNominal) {
        this.espesorNominal = espesorNominal;
    }

    public String getTieneMediciones() {
        return tieneMediciones;
    }

    public void setTieneMediciones(String tieneMediciones) {
        this.tieneMediciones = tieneMediciones;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
