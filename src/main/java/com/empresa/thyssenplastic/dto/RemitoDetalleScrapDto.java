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
public class RemitoDetalleScrapDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String idSrap;
    private String codigo;
    private String articulo;
    private String lote;
    private String deposito;
    private Double peso;
    private Double cantidadUtilizadaRemito;
    private Double porcentajeDeUso;
    private Boolean dadoDeBaja;

    public String getIdSrap() {
        return idSrap;
    }

    public void setIdSrap(String idSrap) {
        this.idSrap = idSrap;
    }
   

    public Boolean getDadoDeBaja() {
        return dadoDeBaja;
    }

    public void setDadoDeBaja(Boolean dadoDeBaja) {
        this.dadoDeBaja = dadoDeBaja;
    }
    
   
    
    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getCantidadUtilizadaRemito() {
        return cantidadUtilizadaRemito;
    }

    public void setCantidadUtilizadaRemito(Double cantidadUtilizadaRemito) {
        this.cantidadUtilizadaRemito = cantidadUtilizadaRemito;
    }

    public Double getPorcentajeDeUso() {
        return porcentajeDeUso;
    }

    public void setPorcentajeDeUso(Double porcentajeDeUso) {
        this.porcentajeDeUso = porcentajeDeUso;
    }
    
    

    


    
}
