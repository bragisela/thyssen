/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dto;
import java.util.List;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionPalletDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String codigo;
    private String pesoTotal;
    private String pesoNeto;
    private String cantidadBultos;
    private String estado;
    private String estadoLabel;   
    private String listaCodigoBultos;
    private  List<String> listaBultos;
    private Map<String, String> mapaBultos;
    private Boolean estaEnDeposito;

    public String getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(String pesoNeto) {
        this.pesoNeto = pesoNeto;
    }
    
    

    public Map<String, String> getMapaBultos() {
        return mapaBultos;
    }

    public void setMapaBultos(Map<String, String> mapaBultos) {
        this.mapaBultos = mapaBultos;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(String pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public String getEstadoLabel() {
        return estadoLabel;
    }

    public void setEstadoLabel(String estadoLabel) {
        this.estadoLabel = estadoLabel;
    }

    public String getCantidadBultos() {
        return cantidadBultos;
    }

    public void setCantidadBultos(String cantidadBultos) {
        this.cantidadBultos = cantidadBultos;
    }
    
    public String getListaCodigoBultos() {
        return listaCodigoBultos;
    }

    public void setListaCodigoBultos(String listaCodigoBultos) {
        this.listaCodigoBultos = listaCodigoBultos;
    }
    
    public List<String> getListaBultos() {
            return listaBultos;
        }

        public void setListaBultos(List<String> listaBultos) {
            this.listaBultos = listaBultos;
        }
        
    public Boolean getEstaEnDeposito() {
        return estaEnDeposito;
    }

    public void setEstaEnDeposito(Boolean estaEnDeposito) {
        this.estaEnDeposito = estaEnDeposito;
    }

    

    
}
