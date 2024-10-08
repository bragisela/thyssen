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
public class OrdenDeCompraItemRecepcionDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaRecepcion;
    private String cantidadRecepcionada;
    private String lote;
    private String referenciaAdministrativa;
    private String idUsuario;
    private String usuario;
    private String unidad;
    private String tipo;
    private String suministro;
    private String item;


    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getCantidadRecepcionada() {
        return cantidadRecepcionada;
    }

    public void setCantidadRecepcionada(String cantidadRecepcionada) {
        this.cantidadRecepcionada = cantidadRecepcionada;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getReferenciaAdministrativa() {
        return referenciaAdministrativa;
    }

    public void setReferenciaAdministrativa(String referenciaAdministrativa) {
        this.referenciaAdministrativa = referenciaAdministrativa;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSuministro() {
        return suministro;
    }

    public void setSuministro(String suministro) {
        this.suministro = suministro;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
