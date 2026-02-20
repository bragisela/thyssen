/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dto;

import java.io.Serializable;

public class EtiquetaDisponibleDto implements Serializable {

    private static final long serialVersionUID = 1L;

   private String tipo;
    private String codigo;
    private String nombreDeposito;
    private Double peso;

    public EtiquetaDisponibleDto() {
    }

    public EtiquetaDisponibleDto(String tipo, String codigo, String nombreDeposito, Double peso) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.nombreDeposito = nombreDeposito;
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreDeposito() {
        return nombreDeposito;
    }

    public void setNombreDeposito(String nombreDeposito) {
        this.nombreDeposito = nombreDeposito;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
