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
public class RemitoDetalleDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String articulo;
    private String lote;
    private String codigo;
    private String cantidad;
    private String ingresoDeposito;
    private String deposito;
    private String tieneBultoOPallet;
    private String cantidadBaja;

    public String getCantidadBaja() {
        return cantidadBaja;
    }

    public void setCantidadBaja(String cantidadBaja) {
        this.cantidadBaja = cantidadBaja;
    }

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

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getIngresoDeposito() {
        return ingresoDeposito;
    }

    public void setIngresoDeposito(String ingresoDeposito) {
        this.ingresoDeposito = ingresoDeposito;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getTieneBultoOPallet() {
        return tieneBultoOPallet;
    }

    public void setTieneBultoOPallet(String tieneBultoOPallet) {
        this.tieneBultoOPallet = tieneBultoOPallet;
    }



    
}
