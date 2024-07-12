/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.model;

import java.util.Date;
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
@Table(name = "articuloprevision")
public class ArticuloPrevisionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "disponible")
    private Integer disponible;

    @Column(name = "produccion")
    private Integer produccion;

    @Column(name = "puntoDePedido")
    private Integer puntoDePedido;

    @Column(name = "idArticulo")
    private Integer idArticulo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getDisponible() {
        return disponible;
    }

    public void setDisponible(Integer disponible) {
        this.disponible = disponible;
    }

    public Integer getProduccion() {
        return produccion;
    }

    public void setProduccion(Integer produccion) {
        this.produccion = produccion;
    }

    public Integer getPuntoDePedido() {
        return puntoDePedido;
    }

    public void setPuntoDePedido(Integer puntoDePedido) {
        this.puntoDePedido = puntoDePedido;
    }
    
    

    @Override
    public String toString() {
        return "User [id=" + id + ", disponible=" + disponible + ",produccion=" + produccion + "]";
    }

}
