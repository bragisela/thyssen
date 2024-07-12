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
@Table(name = "clientedomicilio")
public class ClienteDomicilioModel { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "idDomicilio")
    private Integer idDomicilio;

    
    public ClienteDomicilioModel() {
    }

    public ClienteDomicilioModel(Integer idCliente, Integer idDomicilio) {
        this.idCliente = idCliente;
        this.idDomicilio = idDomicilio;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }
           
    @Override
    public String toString() {
        return "User [id=" + id + ", idCliente=" + idCliente + ", idDomicilio=" + idDomicilio + "]";
    }
    
}