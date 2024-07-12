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
public class ArticuloPrevisionDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String disponible;
    private String produccion;
    private String puntoDePedido;

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

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getProduccion() {
        return produccion;
    }

    public void setProduccion(String produccion) {
        this.produccion = produccion;
    }

    public String getPuntoDePedido() {
        return puntoDePedido;
    }

    public void setPuntoDePedido(String puntoDePedido) {
        this.puntoDePedido = puntoDePedido;
    }


    
    
}
