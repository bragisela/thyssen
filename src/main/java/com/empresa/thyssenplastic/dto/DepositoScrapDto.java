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
public class DepositoScrapDto implements Serializable {
    
    
    private Integer idOrdenDeProduccion;
    private Double pesoSuma;

    public DepositoScrapDto(Integer idOrdenDeProduccion, Double pesoSuma) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
        this.pesoSuma = pesoSuma;
    }
    

    public Integer getIdOrdenDeProduccion() {
        return idOrdenDeProduccion;
    }

    public void setIdOrdenDeProduccion(Integer idOrdenDeProduccion) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
    }

    public Double getPesoSuma() {
        return pesoSuma;
    }

    public void setPesoSuma(Double pesoSuma) {
        this.pesoSuma = pesoSuma;
    }
    
    

    
}
