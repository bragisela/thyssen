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
@Table(name = "articuloetiqueta")
public class ArticuloEtiquetaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "linea1")
    private String linea1;

    @Column(name = "linea2")
    private String linea2;

    @Column(name = "linea3")
    private String linea3;

    @Column(name = "linea4")
    private String linea4;

    @Column(name = "linea5")
    private String linea5;

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

    public String getLinea1() {
        return linea1;
    }

    public void setLinea1(String linea1) {
        this.linea1 = linea1;
    }

    public String getLinea2() {
        return linea2;
    }

    public void setLinea2(String linea2) {
        this.linea2 = linea2;
    }

    public String getLinea3() {
        return linea3;
    }

    public void setLinea3(String linea3) {
        this.linea3 = linea3;
    }

    public String getLinea4() {
        return linea4;
    }

    public void setLinea4(String linea4) {
        this.linea4 = linea4;
    }

    public String getLinea5() {
        return linea5;
    }

    public void setLinea5(String linea5) {
        this.linea5 = linea5;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }
    
    

    @Override
    public String toString() {
        return "User [id=" + id + ", linea1=" + linea1 + ",linea2=" + linea2 + ",linea3=" + linea3 + ",linea4=" + linea4 + ",linea5=" + linea5 + "]";
    }

}
