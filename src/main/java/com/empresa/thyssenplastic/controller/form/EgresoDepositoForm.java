/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller.form;

import java.util.List;

/**
 *
 * @author gusta
 */
public class EgresoDepositoForm {
    
    private String pk;
    private String codigo;
    private String fechaBaja;
    private String idBobina;
    private String idBulto;
    private String idPallet;
    private String idDeposito;    
    private String tipo;
    private String depositoActual;  
    private String idRemito;
    private String operacion;
    private String action;
    private String idOrdenDeProduccionE;
    private List<String> codigos;

    public String getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(String idRemito) {
        this.idRemito = idRemito;
    }
    
    public List<String> getCodigos() {
        return codigos;
    }

    public void setCodigos(List<String> codigos) {
        this.codigos = codigos;
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

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    

    public String getIdBobina() {
        return idBobina;
    }

    public void setIdBobina(String idBobina) {
        this.idBobina = idBobina;
    }

    public String getIdBulto() {
        return idBulto;
    }

    public void setIdBulto(String idBulto) {
        this.idBulto = idBulto;
    }

    public String getIdPallet() {
        return idPallet;
    }

    public void setIdPallet(String idPallet) {
        this.idPallet = idPallet;
    }

    public String getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(String idDeposito) {
        this.idDeposito = idDeposito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDepositoActual() {
        return depositoActual;
    }

    public void setDepositoActual(String depositoActual) {
        this.depositoActual = depositoActual;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public String getIdOrdenDeProduccionE() {
        return idOrdenDeProduccionE;
    }

    public void setIdOrdenDeProduccionE(String idOrdenDeProduccionE) {
        this.idOrdenDeProduccionE = idOrdenDeProduccionE;
    }



    
}
