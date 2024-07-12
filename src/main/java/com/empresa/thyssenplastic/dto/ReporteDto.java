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
public class ReporteDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String problema;
    private String maquina;
    private String horaParada;
    private String horaAranque;
    private String tiempoReparacion;
    private String repuesto;
    private String estado;

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

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getHoraParada() {
        return horaParada;
    }

    public void setHoraParada(String horaParada) {
        this.horaParada = horaParada;
    }

    public String getHoraAranque() {
        return horaAranque;
    }

    public void setHoraAranque(String horaAranque) {
        this.horaAranque = horaAranque;
    }

    public String getTiempoReparacion() {
        return tiempoReparacion;
    }

    public void setTiempoReparacion(String tiempoReparacion) {
        this.tiempoReparacion = tiempoReparacion;
    }

    public String getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(String repuesto) {
        this.repuesto = repuesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
