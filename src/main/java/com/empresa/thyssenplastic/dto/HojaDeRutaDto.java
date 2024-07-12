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
public class HojaDeRutaDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String chofer;
    private String fecha;
    private String estado;
    private Boolean puedeDarBaja;

    public Boolean getPuedeDarBaja() {
        return puedeDarBaja;
    }

    public void setPuedeDarBaja(Boolean puedeDarBaja) {
        this.puedeDarBaja = puedeDarBaja;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    
}
