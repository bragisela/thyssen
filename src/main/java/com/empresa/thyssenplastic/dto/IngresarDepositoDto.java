/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gusta
 */
public class IngresarDepositoDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fecha;
    private String deposito;
    private String codigo;
    private String idOrdenDeProduccion;
    private String estado;
    private Integer unidades; 
    private  String listaBultos;
    private Map<String, String> mapaBultos;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getListaBultos() {
        return listaBultos;
    }

    public void setListaBultos(String listaBultos) {
        this.listaBultos = listaBultos;
    }

    public Map<String, String> getMapaBultos() {
        return mapaBultos;
    }

    public void setMapaBultos(Map<String, String> mapaBultos) {
        this.mapaBultos = mapaBultos;
    }
    
    

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getIdOrdenDeProduccion() {
        return idOrdenDeProduccion;
    }

    public void setIdOrdenDeProduccion(String idOrdenDeProduccion) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    

    
}
