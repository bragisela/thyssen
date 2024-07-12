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
public class MantenimientoCorrectivoDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String fechaFin;
    private String horaParada;
    private String horaArranque;
    private String fechaDeReparacionDesde;
    private String fechaDeReparacionHasta;
    private String intervaloReparacion;
    private String maquina;
    private String estado;
    private String prioridad;

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

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getHoraParada() {
        return horaParada;
    }

    public void setHoraParada(String horaParada) {
        this.horaParada = horaParada;
    }

    public String getHoraArranque() {
        return horaArranque;
    }

    public void setHoraArranque(String horaArranque) {
        this.horaArranque = horaArranque;
    }

    public String getFechaDeReparacionDesde() {
        return fechaDeReparacionDesde;
    }

    public void setFechaDeReparacionDesde(String fechaDeReparacionDesde) {
        this.fechaDeReparacionDesde = fechaDeReparacionDesde;
    }

    public String getFechaDeReparacionHasta() {
        return fechaDeReparacionHasta;
    }

    public void setFechaDeReparacionHasta(String fechaDeReparacionHasta) {
        this.fechaDeReparacionHasta = fechaDeReparacionHasta;
    }

    public String getIntervaloReparacion() {
        return intervaloReparacion;
    }

    public void setIntervaloReparacion(String intervaloReparacion) {
        this.intervaloReparacion = intervaloReparacion;
    }

    
}
