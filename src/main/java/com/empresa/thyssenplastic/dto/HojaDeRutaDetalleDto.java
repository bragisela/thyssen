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
public class HojaDeRutaDetalleDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String idRemito;
    private String cliente;  
    private String idHojaDeRuta;    
    private String cantidadPallets;
    private String cantidadBultos;
    private String domicilio;
    private String localidad;  
    private String provincia;
    private String telefono;  
    private String contacto;  
    private String horario;
    private String km;
    private String kmAcumulado;
    private String orden;
    private String operacion;
    private String usuarioAlta;
    private String estadoHojaDeRuta;
    private String observacionesRemito;
    private String action;
    private String cantidadTotal;
    private String estado;
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
 

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(String idRemito) {
        this.idRemito = idRemito;
    }

    public String getIdHojaDeRuta() {
        return idHojaDeRuta;
    }

    public void setIdHojaDeRuta(String idHojaDeRuta) {
        this.idHojaDeRuta = idHojaDeRuta;
    }

    public String getCantidadPallets() {
        return cantidadPallets;
    }

    public void setCantidadPallets(String cantidadPallets) {
        this.cantidadPallets = cantidadPallets;
    }

    public String getCantidadBultos() {
        return cantidadBultos;
    }

    public void setCantidadBultos(String cantidadBultos) {
        this.cantidadBultos = cantidadBultos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getKmAcumulado() {
        return kmAcumulado;
    }

    public void setKmAcumulado(String kmAcumulado) {
        this.kmAcumulado = kmAcumulado;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEstadoHojaDeRuta() {
        return estadoHojaDeRuta;
    }

    public void setEstadoHojaDeRuta(String estadoHojaDeRuta) {
        this.estadoHojaDeRuta = estadoHojaDeRuta;
    }

    public String getObservacionesRemito() {
        return observacionesRemito;
    }

    public void setObservacionesRemito(String observacionesRemito) {
        this.observacionesRemito = observacionesRemito;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }
    
    public String getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(String cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    
}
