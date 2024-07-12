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
