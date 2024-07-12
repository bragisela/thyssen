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
public class OrdenDeProduccionDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String cliente;
    private String articulo;
    private String versionFichaTecnica;
    private String SePuedeCambiarEstadoAbierto;
    private String cantidadAProducir;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getVersionFichaTecnica() {
        return versionFichaTecnica;
    }

    public void setVersionFichaTecnica(String versionFichaTecnica) {
        this.versionFichaTecnica = versionFichaTecnica;
    }

    public String getSePuedeCambiarEstadoAbierto() {
        return SePuedeCambiarEstadoAbierto;
    }

    public void setSePuedeCambiarEstadoAbierto(String SePuedeCambiarEstadoAbierto) {
        this.SePuedeCambiarEstadoAbierto = SePuedeCambiarEstadoAbierto;
    }

    public String getCantidadAProducir() {
        return cantidadAProducir;
    }

    public void setCantidadAProducir(String cantidadAProducir) {
        this.cantidadAProducir = cantidadAProducir;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    
}
