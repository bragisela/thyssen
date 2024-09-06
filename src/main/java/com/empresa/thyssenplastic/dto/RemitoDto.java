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
public class RemitoDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String fechaRemito;
    private String cliente;
    private String estado;
    private String referenciaAdministrativa;
    private String canDelete;
    private String itemsRecepcionados;
    private String tipoRemito;
    private Integer cantidadTotal;
    private Integer cantidadTotalBaja;
    private String localidad;
    private String provincia;
    private Boolean isScrap;

    public Boolean getIsScrap() {
        return isScrap;
    }

    public void setIsScrap(Boolean isScrap) {
        this.isScrap = isScrap;
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
    

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Integer getCantidadTotalBaja() {
        return cantidadTotalBaja;
    }

    public void setCantidadTotalBaja(Integer cantidadTotalBaja) {
        this.cantidadTotalBaja = cantidadTotalBaja;
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

    public String getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(String fechaRemito) {
        this.fechaRemito = fechaRemito;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferenciaAdministrativa() {
        return referenciaAdministrativa;
    }

    public void setReferenciaAdministrativa(String referenciaAdministrativa) {
        this.referenciaAdministrativa = referenciaAdministrativa;
    }

    public String getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(String canDelete) {
        this.canDelete = canDelete;
    }

    public String getItemsRecepcionados() {
        return itemsRecepcionados;
    }

    public void setItemsRecepcionados(String itemsRecepcionados) {
        this.itemsRecepcionados = itemsRecepcionados;
    }

    public String getTipoRemito() {
        return tipoRemito;
    }

    public void setTipoRemito(String tipoRemito) {
        this.tipoRemito = tipoRemito;
    }


    
}
