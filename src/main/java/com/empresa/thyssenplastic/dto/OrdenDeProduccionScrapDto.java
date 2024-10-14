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
public class OrdenDeProduccionScrapDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private String fechaAlta;
    private String codigo;
    private String origen;
    private String tipoMaterial;
    private String motivo;
    private String formato;
    private String esRecuperable;
    private String materialImpreso;
    private String pesoTotal;
    private String estado;
    private String estadoLabel;
    
    private Boolean puedoBorrarlo;

    public Boolean getPuedoBorrarlo() {
        return puedoBorrarlo;
    }

    public void setPuedoBorrarlo(Boolean puedoBorrarlo) {
        this.puedoBorrarlo = puedoBorrarlo;
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

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getEsRecuperable() {
        return esRecuperable;
    }

    public void setEsRecuperable(String esRecuperable) {
        this.esRecuperable = esRecuperable;
    }

    public String getMaterialImpreso() {
        return materialImpreso;
    }

    public void setMaterialImpreso(String materialImpreso) {
        this.materialImpreso = materialImpreso;
    }



    
}
