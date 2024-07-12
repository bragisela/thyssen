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
@Table(name = "proveedorcontacto")
public class ProveedorContactoModel { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "idProveedor")
    private Integer idProveedor;

    @Column(name = "idContacto")
    private Integer idContacto;

    
    public ProveedorContactoModel() {
    }

    public ProveedorContactoModel(Integer idProveedor, Integer idContacto) {
        this.idProveedor = idProveedor;
        this.idContacto = idContacto;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }
           
    @Override
    public String toString() {
        return "User [id=" + id + ", idProveedor=" + idProveedor + ", idContacto=" + idContacto + "]";
    }
    
}