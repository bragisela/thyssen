/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class EtiquetaDisponibleDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipo;
    private String codigo;
    private String nombreDeposito;
    private Double peso;
    private List<String> bultosEnPallet;
    private boolean completo;

    public EtiquetaDisponibleDto() {
        this.bultosEnPallet = new ArrayList<String>();
        this.completo = false;
    }

    public EtiquetaDisponibleDto(String tipo, String codigo, String nombreDeposito, Double peso) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.nombreDeposito = nombreDeposito;
        this.peso = peso;
        this.bultosEnPallet = new ArrayList<String>();
        this.completo = false;
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
    public List<String> getBultosEnPallet() {
        return bultosEnPallet;
    }
    public void setBultosEnPallet(List<String> bultosEnPallet) {
        this.bultosEnPallet = bultosEnPallet;
    }
    public boolean isCompleto() {
        return completo;
    }
    public void setCompleto(boolean completo) {
        this.completo = completo;
    }
}