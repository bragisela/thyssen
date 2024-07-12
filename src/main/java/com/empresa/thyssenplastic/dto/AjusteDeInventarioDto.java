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
public class AjusteDeInventarioDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String tipo;
    private String descripcion;
    private String existentePeso;
    private String existenteCantidad;
    private String ajustePeso;
    private String ajusteCantidad;
    private String resultadoPeso;
    private String resultadoCantidad;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExistentePeso() {
        return existentePeso;
    }

    public void setExistentePeso(String existentePeso) {
        this.existentePeso = existentePeso;
    }

    public String getExistenteCantidad() {
        return existenteCantidad;
    }

    public void setExistenteCantidad(String existenteCantidad) {
        this.existenteCantidad = existenteCantidad;
    }

    public String getAjustePeso() {
        return ajustePeso;
    }

    public void setAjustePeso(String ajustePeso) {
        this.ajustePeso = ajustePeso;
    }

    public String getAjusteCantidad() {
        return ajusteCantidad;
    }

    public void setAjusteCantidad(String ajusteCantidad) {
        this.ajusteCantidad = ajusteCantidad;
    }

    public String getResultadoPeso() {
        return resultadoPeso;
    }

    public void setResultadoPeso(String resultadoPeso) {
        this.resultadoPeso = resultadoPeso;
    }

    public String getResultadoCantidad() {
        return resultadoCantidad;
    }

    public void setResultadoCantidad(String resultadoCantidad) {
        this.resultadoCantidad = resultadoCantidad;
    }

    
    
}
