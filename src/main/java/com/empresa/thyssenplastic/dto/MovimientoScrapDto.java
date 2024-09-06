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
public class MovimientoScrapDto implements Serializable {
    
    //REMITO
    private String codigoRemito;
    private String estadoRemito;
    private String fechaAltaRemito;
    private String usuarioAltaRemito;
    private String tipoRemito;
    private String transporteRemito;
    
    //DATOS DEL REMITO DETAÑÑE
    private Double cantidadUtilizadaRemito;
    private String fechaAltaDetalle;
    

    //HOJA DE RUTA
    private String codigoHojaDeRuta;
    private String estadoHojaDeRuta;
    private String fechaAltaHojaDeRuta;
    private String fechaCarga;
    private String fechaSalida;    
    private String usuarioAltaHojaDeRuta;
    private String chofer;

    public Double getCantidadUtilizadaRemito() {
        return cantidadUtilizadaRemito;
    }

    public void setCantidadUtilizadaRemito(Double cantidadUtilizadaRemito) {
        this.cantidadUtilizadaRemito = cantidadUtilizadaRemito;
    }

    public String getFechaAltaDetalle() {
        return fechaAltaDetalle;
    }

    public void setFechaAltaDetalle(String fechaAltaDetalle) {
        this.fechaAltaDetalle = fechaAltaDetalle;
    }
    
    

    public String getCodigoRemito() {
        return codigoRemito;
    }

    public void setCodigoRemito(String codigoRemito) {
        this.codigoRemito = codigoRemito;
    }

    public String getEstadoRemito() {
        return estadoRemito;
    }

    public void setEstadoRemito(String estadoRemito) {
        this.estadoRemito = estadoRemito;
    }

    public String getFechaAltaRemito() {
        return fechaAltaRemito;
    }

    public void setFechaAltaRemito(String fechaAltaRemito) {
        this.fechaAltaRemito = fechaAltaRemito;
    }

    public String getUsuarioAltaRemito() {
        return usuarioAltaRemito;
    }

    public void setUsuarioAltaRemito(String usuarioAltaRemito) {
        this.usuarioAltaRemito = usuarioAltaRemito;
    }

    public String getTipoRemito() {
        return tipoRemito;
    }

    public void setTipoRemito(String tipoRemito) {
        this.tipoRemito = tipoRemito;
    }

    public String getTransporteRemito() {
        return transporteRemito;
    }

    public void setTransporteRemito(String transporteRemito) {
        this.transporteRemito = transporteRemito;
    }

    public String getCodigoHojaDeRuta() {
        return codigoHojaDeRuta;
    }

    public void setCodigoHojaDeRuta(String codigoHojaDeRuta) {
        this.codigoHojaDeRuta = codigoHojaDeRuta;
    }

    public String getEstadoHojaDeRuta() {
        return estadoHojaDeRuta;
    }

    public void setEstadoHojaDeRuta(String estadoHojaDeRuta) {
        this.estadoHojaDeRuta = estadoHojaDeRuta;
    }

    public String getFechaAltaHojaDeRuta() {
        return fechaAltaHojaDeRuta;
    }

    public void setFechaAltaHojaDeRuta(String fechaAltaHojaDeRuta) {
        this.fechaAltaHojaDeRuta = fechaAltaHojaDeRuta;
    }

    public String getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getUsuarioAltaHojaDeRuta() {
        return usuarioAltaHojaDeRuta;
    }

    public void setUsuarioAltaHojaDeRuta(String usuarioAltaHojaDeRuta) {
        this.usuarioAltaHojaDeRuta = usuarioAltaHojaDeRuta;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    
}
