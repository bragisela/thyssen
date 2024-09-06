/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller.beans;

import com.empresa.thyssenplastic.dto.OrdenDeProduccionBobinaDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBultoDto;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ItemBean {
    
    private String id;
    private String codigo;
    private String estado;
    private String depositoActual;
    private String tipo;
    private List<OrdenDeProduccionBultoDto> bultos;
    private OrdenDeProduccionBobinaDto bobina;
    private String idDeposito;
    
    
    //Scrap
    
    private String origen;
    private String tipoMaterial;
    private String formato;
    private String recuperable;
    private String materialImpreso;
    private String motivo;
    private Double pesoTotal;

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getRecuperable() {
        return recuperable;
    }

    public void setRecuperable(String recuperable) {
        this.recuperable = recuperable;
    }

    public String getMaterialImpreso() {
        return materialImpreso;
    }

    public void setMaterialImpreso(String materialImpreso) {
        this.materialImpreso = materialImpreso;
    }

    public Double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDepositoActual() {
        return depositoActual;
    }

    public void setDepositoActual(String depositoActual) {
        this.depositoActual = depositoActual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public List<OrdenDeProduccionBultoDto> getBultos() {
        return bultos;
    }

    public void setBultos(List<OrdenDeProduccionBultoDto> bultos) {
        this.bultos = bultos;
    }
    
    public OrdenDeProduccionBobinaDto getBobobina() {
        return bobina;
    }

    public void setBobina(OrdenDeProduccionBobinaDto bobina) {
        this.bobina = bobina;
    }

    public String getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(String idDeposito) {
        this.idDeposito = idDeposito;
    }
    
    
}
