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
public class OrdenDeCompraDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String proveedor;
    private String SePuedeCambiarEstadoAbierto;
    private String superaCantidad;
    private String faltaCantidad;
    private String estado;
    private String referenciaAdministrativa;
    private String fechaEntrega;

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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSePuedeCambiarEstadoAbierto() {
        return SePuedeCambiarEstadoAbierto;
    }

    public void setSePuedeCambiarEstadoAbierto(String SePuedeCambiarEstadoAbierto) {
        this.SePuedeCambiarEstadoAbierto = SePuedeCambiarEstadoAbierto;
    }

    public String getSuperaCantidad() {
        return superaCantidad;
    }

    public void setSuperaCantidad(String superaCantidad) {
        this.superaCantidad = superaCantidad;
    }

    public String getReferenciaAdministrativa() {
        return referenciaAdministrativa;
    }

    public void setReferenciaAdministrativa(String referenciaAdministrativa) {
        this.referenciaAdministrativa = referenciaAdministrativa;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFaltaCantidad() {
        return faltaCantidad;
    }

    public void setFaltaCantidad(String faltaCantidad) {
        this.faltaCantidad = faltaCantidad;
    }


    
}
