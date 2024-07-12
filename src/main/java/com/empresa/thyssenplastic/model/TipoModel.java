/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "tipos")
public class TipoModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "valor")
    private String valor;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "indiceDeFluidez")
    private Double indiceDeFluidez;

    @Column(name = "densidad")
    private Double densidad;
    
    @Column(name = "idPais")
    protected Integer idPais;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getIndiceDeFluidez() {
        return indiceDeFluidez;
    }

    public void setIndiceDeFluidez(Double indiceDeFluidez) {
        this.indiceDeFluidez = indiceDeFluidez;
    }

    public Double getDensidad() {
        return densidad;
    }

    public void setDensidad(Double densidad) {
        this.densidad = densidad;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }
   
    
    @Override
    public String toString() {
        return "User [id=" + id + ", valor=" + valor + ", tipo=" + tipo + "]";
    }
    
}