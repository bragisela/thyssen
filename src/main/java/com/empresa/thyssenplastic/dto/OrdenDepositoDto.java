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
public class OrdenDepositoDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer idOrdenDeProduccion;
    private Integer idArticulo;
    private String nombreDeposito;
    private double sumapeso;
    private Integer unidad;
    private Integer remitoDetalle;
    private double pesoConsumido;
    
    public OrdenDepositoDto(int idOrdenDeProduccion, Integer idArticulo, String nombreDeposito, double sumapeso, Integer unidad, Integer remitoDetalle, double pesoConsumido) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
        this.idArticulo = idArticulo;
        this.nombreDeposito = nombreDeposito;
        this.sumapeso = sumapeso;
        this.unidad = unidad;
        this.remitoDetalle = remitoDetalle;
        this.pesoConsumido = pesoConsumido;
    }

    public double getPesoConsumido() {
        return pesoConsumido;
    }

    public void setPesoConsumido(double pesoConsumido) {
        this.pesoConsumido = pesoConsumido;
    }

    public Integer getRemitoDetalle() {
        return remitoDetalle;
    }

    public void setRemitoDetalle(Integer remitoDetalle) {
        this.remitoDetalle = remitoDetalle;
    }

    public int getIdOrdenDeProduccion() {
        return idOrdenDeProduccion;
    }

    public void setIdOrdenDeProduccion(int idOrdenDeProduccion) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
    }

    public String getNombreDeposito() {
        return nombreDeposito;
    }

    public void setNombreDeposito(String nombreDeposito) {
        this.nombreDeposito = nombreDeposito;
    }

    public double getSumapeso() {
        return sumapeso;
    }

    public void setSumapeso(double sumapeso) {
        this.sumapeso = sumapeso;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }
    
    

    
    
}
