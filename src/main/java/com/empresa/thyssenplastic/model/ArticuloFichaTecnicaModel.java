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
@Table(name = "articulofichatecnica")
public class ArticuloFichaTecnicaModel { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "idArticulo")
    private Integer idArticulo;

    @Column(name = "version")
    private Double version;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "especificacion")
    private String especificacion;

    @Column(name = "urlEspecificacion")
    private String urlEspecificacion;

    @Column(name = "idUsuarioCreacion")
    private Integer idUsuarioCreacion;

    @Column(name = "idUsuarioModificacion")
    private Integer idUsuarioModificacion;

    @Column(name = "fechaModificacion")
    private Date fechaModificacion;

    @Column(name = "idMaterial")
    private Integer idMaterial;

    @Column(name = "lamina")
    private Boolean lamina;

    @Column(name = "bobina")
    private Boolean bobina;
    
    @Column(name = "idColorA")
    private Integer idColorA;

    @Column(name = "idColorB")
    private Integer idColorB;

    @Column(name = "idColorC")
    private Integer idColorC;

    @Column(name = "idColorD")
    private Integer idColorD;

    @Column(name = "idColorE")
    private Integer idColorE;

    @Column(name = "idColorF")
    private Integer idColorF;

    @Column(name = "idColorG")
    private Integer idColorG;

    @Column(name = "ancho")
    private Double ancho;

    @Column(name = "fuelleIzquierdo")
    private Double fuelleIzquierdo;
    
    @Column(name = "fuelleDerecho")
    private Double fuelleDerecho;

    @Column(name = "espesorNominal")
    private Double espesorNominal;

    @Column(name = "espesorPromedio")
    private Double espesorPromedio;

    @Column(name = "espesorArticulo")
    private Double espesorArticulo;

    @Column(name = "cantidadCapas")
    private Integer cantidadCapas;

    @Column(name = "pesoEspecifico")
    private Double pesoEspecifico;

    @Column(name = "anchoBruto")
    private Double anchoBruto;

    @Column(name = "bobinadoBarras")
    private Double bobinadoBarras;

    @Column(name = "metros")
    private Double metros;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "diametro")
    private Double diametro;

    @Column(name = "empalmes")
    private Double empalmes;

    @Column(name = "idTipoBobina")
    private Integer idTipoBobina;

    @Column(name = "diametro2")
    private Double diametro2;

    @Column(name = "largo")
    private Double largo;

    @Column(name = "espesor")
    private Double espesor;

    @Column(name = "peso2")
    private Double peso2;

    @Column(name = "idLineaTintas")
    private Integer idLineaTintas;

    @Column(name = "idLineaSolventes")
    private Integer idLineaSolventes;
  
    @Column(name = "idBultosEn")
    private Integer idBultosEn;

    @Column(name = "bultosPorPalet")
    private Double bultosPorPalet;

    @Column(name = "idPalet")
    private Integer idPalet;

    @Column(name = "posicionPalet")
    private String posicionPalet;
    
    @Column(name = "idMateriaPrima1CapaA")
    private Integer idMateriaPrima1CapaA;    
    
    @Column(name = "materiaPrimaPorcentage1CapaA")
    private Double materiaPrimaPorcentage1CapaA;
    
    @Column(name = "idMateriaPrima2CapaA")
    private Integer idMateriaPrima2CapaA;    
    
    @Column(name = "materiaPrimaPorcentage2CapaA")
    private Double materiaPrimaPorcentage2CapaA;
    
    @Column(name = "idMateriaPrima3CapaA")
    private Integer idMateriaPrima3CapaA;    
    
    @Column(name = "materiaPrimaPorcentage3CapaA")
    private Double materiaPrimaPorcentage3CapaA;
    
    @Column(name = "idMateriaPrima4CapaA")
    private Integer idMateriaPrima4CapaA;    
    
    @Column(name = "materiaPrimaPorcentage4CapaA")
    private Double materiaPrimaPorcentage4CapaA;
    
    @Column(name = "idMateriaPrima5CapaA")
    private Integer idMateriaPrima5CapaA;    
    
    @Column(name = "materiaPrimaPorcentage5CapaA")
    private Double materiaPrimaPorcentage5CapaA;
    
    @Column(name = "idMateriaPrima6CapaA")
    private Integer idMateriaPrima6CapaA;    
    
    @Column(name = "materiaPrimaPorcentage6CapaA")
    private Double materiaPrimaPorcentage6CapaA;
    
    @Column(name = "idMateriaPrima1CapaB")
    private Integer idMateriaPrima1CapaB;
    
    @Column(name = "materiaPrimaPorcentage1CapaB")
    private Double materiaPrimaPorcentage1CapaB;
    
    @Column(name = "idMateriaPrima2CapaB")
    private Integer idMateriaPrima2CapaB;  
    
    @Column(name = "materiaPrimaPorcentage2CapaB")
    private Double materiaPrimaPorcentage2CapaB;
    
    @Column(name = "idMateriaPrima3CapaB")
    private Integer idMateriaPrima3CapaB;
    
    @Column(name = "materiaPrimaPorcentage3CapaB")
    private Double materiaPrimaPorcentage3CapaB;
    
    @Column(name = "idMateriaPrima4CapaB")
    private Integer idMateriaPrima4CapaB;    
    
    @Column(name = "materiaPrimaPorcentage4CapaB")
    private Double materiaPrimaPorcentage4CapaB;
    
    @Column(name = "idMateriaPrima5CapaB")
    private Integer idMateriaPrima5CapaB;   
    
    @Column(name = "materiaPrimaPorcentage5CapaB")
    private Double materiaPrimaPorcentage5CapaB;
    
    @Column(name = "idMateriaPrima6CapaB")
    private Integer idMateriaPrima6CapaB;    
    
    @Column(name = "materiaPrimaPorcentage6CapaB")
    private Double materiaPrimaPorcentage6CapaB;

    @Column(name = "idMateriaPrima1CapaC")
    private Integer idMateriaPrima1CapaC;
    
    @Column(name = "materiaPrimaPorcentage1CapaC")
    private Double materiaPrimaPorcentage1CapaC;
    
    @Column(name = "idMateriaPrima2CapaC")
    private Integer idMateriaPrima2CapaC;    
    
    @Column(name = "materiaPrimaPorcentage2CapaC")
    private Double materiaPrimaPorcentage2CapaC;
    
    @Column(name = "idMateriaPrima3CapaC")
    private Integer idMateriaPrima3CapaC;    
    
    @Column(name = "materiaPrimaPorcentage3CapaC")
    private Double materiaPrimaPorcentage3CapaC;
    
    @Column(name = "idMateriaPrima4CapaC")
    private Integer idMateriaPrima4CapaC;    
    
    @Column(name = "materiaPrimaPorcentage4CapaC")
    private Double materiaPrimaPorcentage4CapaC;
    
    @Column(name = "idMateriaPrima5CapaC")
    private Integer idMateriaPrima5CapaC;    
    
    @Column(name = "materiaPrimaPorcentage5CapaC")
    private Double materiaPrimaPorcentage5CapaC;
    
    @Column(name = "idMateriaPrima6CapaC")
    private Integer idMateriaPrima6CapaC;
    
    @Column(name = "materiaPrimaPorcentage6CapaC")
    private Double materiaPrimaPorcentage6CapaC;

    @Column(name = "idMateriaPrima1CapaD")
    private Integer idMateriaPrima1CapaD;
    
    @Column(name = "materiaPrimaPorcentage1CapaD")
    private Double materiaPrimaPorcentage1CapaD;
    
    @Column(name = "idMateriaPrima2CapaD")    
    private Integer idMateriaPrima2CapaD;
    
    @Column(name = "materiaPrimaPorcentage2CapaD")
    private Double materiaPrimaPorcentage2CapaD;
    
    @Column(name = "idMateriaPrima3CapaD")
    private Integer idMateriaPrima3CapaD;    
    
    @Column(name = "materiaPrimaPorcentage3CapaD")
    private Double materiaPrimaPorcentage3CapaD;
    
    @Column(name = "idMateriaPrima4CapaD")
    private Integer idMateriaPrima4CapaD;
    
    @Column(name = "materiaPrimaPorcentage4CapaD")
    private Double materiaPrimaPorcentage4CapaD;
    
    @Column(name = "idMateriaPrima5CapaD")
    private Integer idMateriaPrima5CapaD;   
    
    @Column(name = "materiaPrimaPorcentage5CapaD")
    private Double materiaPrimaPorcentage5CapaD;
    
    @Column(name = "idMateriaPrima6CapaD")
    private Integer idMateriaPrima6CapaD;
    
    @Column(name = "materiaPrimaPorcentage6CapaD")
    private Double materiaPrimaPorcentage6CapaD;

    @Column(name = "idMateriaPrima1CapaE")
    private Integer idMateriaPrima1CapaE;
    
    @Column(name = "materiaPrimaPorcentage1CapaE")
    private Double materiaPrimaPorcentage1CapaE;
    
    @Column(name = "idMateriaPrima2CapaE")
    private Integer idMateriaPrima2CapaE;
    
    @Column(name = "materiaPrimaPorcentage2CapaE")
    private Double materiaPrimaPorcentage2CapaE;
    
    @Column(name = "idMateriaPrima3CapaE")
    private Integer idMateriaPrima3CapaE;
    
    @Column(name = "materiaPrimaPorcentage3CapaE")
    private Double materiaPrimaPorcentage3CapaE;
    
    @Column(name = "idMateriaPrima4CapaE")
    private Integer idMateriaPrima4CapaE;
    
    @Column(name = "materiaPrimaPorcentage4CapaE")
    private Double materiaPrimaPorcentage4CapaE;
    
    @Column(name = "idMateriaPrima5CapaE")
    private Integer idMateriaPrima5CapaE;
    
    @Column(name = "materiaPrimaPorcentage5CapaE")
    private Double materiaPrimaPorcentage5CapaE;
    
    @Column(name = "idMateriaPrima6CapaE")
    private Integer idMateriaPrima6CapaE;
    
    @Column(name = "materiaPrimaPorcentage6CapaE")
    private Double materiaPrimaPorcentage6CapaE;

    @Column(name = "idMateriaPrima1CapaF")
    private Integer idMateriaPrima1CapaF;
    
    @Column(name = "materiaPrimaPorcentage1CapaF")
    private Double materiaPrimaPorcentage1CapaF;
    
    @Column(name = "idMateriaPrima2CapaF")
    private Integer idMateriaPrima2CapaF;
    
    @Column(name = "materiaPrimaPorcentage2CapaF")
    private Double materiaPrimaPorcentage2CapaF;
    
    @Column(name = "idMateriaPrima3CapaF")
    private Integer idMateriaPrima3CapaF;
    
    @Column(name = "materiaPrimaPorcentage3CapaF")
    private Double materiaPrimaPorcentage3CapaF;
    
    @Column(name = "idMateriaPrima4CapaF")
    private Integer idMateriaPrima4CapaF;
    
    @Column(name = "materiaPrimaPorcentage4CapaF")
    private Double materiaPrimaPorcentage4CapaF;
    
    @Column(name = "idMateriaPrima5CapaF")
    private Integer idMateriaPrima5CapaF;
    
    @Column(name = "materiaPrimaPorcentage5CapaF")
    private Double materiaPrimaPorcentage5CapaF;
    
    @Column(name = "idMateriaPrima6CapaF")
    private Integer idMateriaPrima6CapaF;
    
    @Column(name = "materiaPrimaPorcentage6CapaF")
    private Double materiaPrimaPorcentage6CapaF;

    @Column(name = "idMateriaPrima1CapaG")
    private Integer idMateriaPrima1CapaG;
    
    @Column(name = "materiaPrimaPorcentage1CapaG")
    private Double materiaPrimaPorcentage1CapaG;
    
    @Column(name = "idMateriaPrima2CapaG")
    private Integer idMateriaPrima2CapaG;
    
    @Column(name = "materiaPrimaPorcentage2CapaG")
    private Double materiaPrimaPorcentage2CapaG;
    
    @Column(name = "idMateriaPrima3CapaG")
    private Integer idMateriaPrima3CapaG;
    
    @Column(name = "materiaPrimaPorcentage3CapaG")
    private Double materiaPrimaPorcentage3CapaG;
    
    @Column(name = "idMateriaPrima4CapaG")
    private Integer idMateriaPrima4CapaG;
    
    @Column(name = "materiaPrimaPorcentage4CapaG")
    private Double materiaPrimaPorcentage4CapaG;
    
    @Column(name = "idMateriaPrima5CapaG")
    private Integer idMateriaPrima5CapaG;
    
    @Column(name = "materiaPrimaPorcentage5CapaG")
    private Double materiaPrimaPorcentage5CapaG;
    
    @Column(name = "idMateriaPrima6CapaG")
    private Integer idMateriaPrima6CapaG;
    
    @Column(name = "materiaPrimaPorcentage6CapaG")
    private Double materiaPrimaPorcentage6CapaG;

    @Column(name = "resumenCapaA")
    private Double resumenCapaA;

    @Column(name = "resumenCapaB")
    private Double resumenCapaB;

    @Column(name = "resumenCapaC")
    private Double resumenCapaC;

    @Column(name = "resumenCapaD")
    private Double resumenCapaD;

    @Column(name = "resumenCapaE")
    private Double resumenCapaE;

    @Column(name = "resumenCapaF")
    private Double resumenCapaF;
    
    @Column(name = "resumenCapaG")
    private Double resumenCapaG;
    
    public ArticuloFichaTecnicaModel() {
    }

    public ArticuloFichaTecnicaModel(Integer idArticulo, Date fechaAlta) {
        this.idArticulo = idArticulo;
        this.fechaAlta = fechaAlta;
    }
    
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

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public String getUrlEspecificacion() {
        return urlEspecificacion;
    }

    public void setUrlEspecificacion(String urlEspecificacion) {
        this.urlEspecificacion = urlEspecificacion;
    }

    public Integer getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Integer getIdUsuarioModificacion() {
        return idUsuarioModificacion;
    }

    public void setIdUsuarioModificacion(Integer idUsuarioModificacion) {
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Boolean getLamina() {
        return lamina;
    }

    public void setLamina(Boolean lamina) {
        this.lamina = lamina;
    }

    public Boolean getBobina() {
        return bobina;
    }

    public void setBobina(Boolean bobina) {
        this.bobina = bobina;
    }

    public Integer getIdColorA() {
        return idColorA;
    }

    public void setIdColorA(Integer idColorA) {
        this.idColorA = idColorA;
    }

    public Integer getIdColorB() {
        return idColorB;
    }

    public void setIdColorB(Integer idColorB) {
        this.idColorB = idColorB;
    }

    public Integer getIdColorC() {
        return idColorC;
    }

    public void setIdColorC(Integer idColorC) {
        this.idColorC = idColorC;
    }

    public Integer getIdColorD() {
        return idColorD;
    }

    public void setIdColorD(Integer idColorD) {
        this.idColorD = idColorD;
    }

    public Integer getIdColorE() {
        return idColorE;
    }

    public void setIdColorE(Integer idColorE) {
        this.idColorE = idColorE;
    }

    public Integer getIdColorF() {
        return idColorF;
    }

    public void setIdColorF(Integer idColorF) {
        this.idColorF = idColorF;
    }

    public Integer getIdColorG() {
        return idColorG;
    }

    public void setIdColorG(Integer idColorG) {
        this.idColorG = idColorG;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getFuelleIzquierdo() {
        return fuelleIzquierdo;
    }

    public void setFuelleIzquierdo(Double fuelleIzquierdo) {
        this.fuelleIzquierdo = fuelleIzquierdo;
    }

    public Double getFuelleDerecho() {
        return fuelleDerecho;
    }

    public void setFuelleDerecho(Double fuelleDerecho) {
        this.fuelleDerecho = fuelleDerecho;
    }

    public Double getEspesorNominal() {
        return espesorNominal;
    }

    public void setEspesorNominal(Double espesorNominal) {
        this.espesorNominal = espesorNominal;
    }

    public Double getEspesorPromedio() {
        return espesorPromedio;
    }

    public void setEspesorPromedio(Double espesorPromedio) {
        this.espesorPromedio = espesorPromedio;
    }

    public Double getEspesorArticulo() {
        return espesorArticulo;
    }

    public void setEspesorArticulo(Double espesorArticulo) {
        this.espesorArticulo = espesorArticulo;
    }

    public Integer getCantidadCapas() {
        return cantidadCapas;
    }

    public void setCantidadCapas(Integer cantidadCapas) {
        this.cantidadCapas = cantidadCapas;
    }

    public Double getPesoEspecifico() {
        return pesoEspecifico;
    }

    public void setPesoEspecifico(Double pesoEspecifico) {
        this.pesoEspecifico = pesoEspecifico;
    }

    public Double getAnchoBruto() {
        return anchoBruto;
    }

    public void setAnchoBruto(Double anchoBruto) {
        this.anchoBruto = anchoBruto;
    }

    public Double getBobinadoBarras() {
        return bobinadoBarras;
    }

    public void setBobinadoBarras(Double bobinadoBarras) {
        this.bobinadoBarras = bobinadoBarras;
    }

    public Double getMetros() {
        return metros;
    }

    public void setMetros(Double metros) {
        this.metros = metros;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getDiametro() {
        return diametro;
    }

    public void setDiametro(Double diametro) {
        this.diametro = diametro;
    }

    public Double getEmpalmes() {
        return empalmes;
    }

    public void setEmpalmes(Double empalmes) {
        this.empalmes = empalmes;
    }

    public Integer getIdTipoBobina() {
        return idTipoBobina;
    }

    public void setIdTipoBobina(Integer idTipoBobina) {
        this.idTipoBobina = idTipoBobina;
    }

    public Double getDiametro2() {
        return diametro2;
    }

    public void setDiametro2(Double diametro2) {
        this.diametro2 = diametro2;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Double getEspesor() {
        return espesor;
    }

    public void setEspesor(Double espesor) {
        this.espesor = espesor;
    }

    public Double getPeso2() {
        return peso2;
    }

    public void setPeso2(Double peso2) {
        this.peso2 = peso2;
    }

    public Integer getIdLineaTintas() {
        return idLineaTintas;
    }

    public void setIdLineaTintas(Integer idLineaTintas) {
        this.idLineaTintas = idLineaTintas;
    }

    public Integer getIdLineaSolventes() {
        return idLineaSolventes;
    }

    public void setIdLineaSolventes(Integer idLineaSolventes) {
        this.idLineaSolventes = idLineaSolventes;
    }

    public Integer getIdBultosEn() {
        return idBultosEn;
    }

    public void setIdBultosEn(Integer idBultosEn) {
        this.idBultosEn = idBultosEn;
    }

    public Double getBultosPorPalet() {
        return bultosPorPalet;
    }

    public void setBultosPorPalet(Double bultosPorPalet) {
        this.bultosPorPalet = bultosPorPalet;
    }

    public Integer getIdPalet() {
        return idPalet;
    }

    public void setIdPalet(Integer idPalet) {
        this.idPalet = idPalet;
    }

    public String getPosicionPalet() {
        return posicionPalet;
    }

    public void setPosicionPalet(String posicionPalet) {
        this.posicionPalet = posicionPalet;
    }

    public Integer getIdMateriaPrima1CapaA() {
        return idMateriaPrima1CapaA;
    }

    public void setIdMateriaPrima1CapaA(Integer idMateriaPrima1CapaA) {
        this.idMateriaPrima1CapaA = idMateriaPrima1CapaA;
    }

    public Double getMateriaPrimaPorcentage1CapaA() {
        return materiaPrimaPorcentage1CapaA;
    }

    public void setMateriaPrimaPorcentage1CapaA(Double materiaPrimaPorcentage1CapaA) {
        this.materiaPrimaPorcentage1CapaA = materiaPrimaPorcentage1CapaA;
    }

    public Integer getIdMateriaPrima2CapaA() {
        return idMateriaPrima2CapaA;
    }

    public void setIdMateriaPrima2CapaA(Integer idMateriaPrima2CapaA) {
        this.idMateriaPrima2CapaA = idMateriaPrima2CapaA;
    }

    public Double getMateriaPrimaPorcentage2CapaA() {
        return materiaPrimaPorcentage2CapaA;
    }

    public void setMateriaPrimaPorcentage2CapaA(Double materiaPrimaPorcentage2CapaA) {
        this.materiaPrimaPorcentage2CapaA = materiaPrimaPorcentage2CapaA;
    }

    public Integer getIdMateriaPrima3CapaA() {
        return idMateriaPrima3CapaA;
    }

    public void setIdMateriaPrima3CapaA(Integer idMateriaPrima3CapaA) {
        this.idMateriaPrima3CapaA = idMateriaPrima3CapaA;
    }

    public Double getMateriaPrimaPorcentage3CapaA() {
        return materiaPrimaPorcentage3CapaA;
    }

    public void setMateriaPrimaPorcentage3CapaA(Double materiaPrimaPorcentage3CapaA) {
        this.materiaPrimaPorcentage3CapaA = materiaPrimaPorcentage3CapaA;
    }

    public Integer getIdMateriaPrima4CapaA() {
        return idMateriaPrima4CapaA;
    }

    public void setIdMateriaPrima4CapaA(Integer idMateriaPrima4CapaA) {
        this.idMateriaPrima4CapaA = idMateriaPrima4CapaA;
    }

    public Double getMateriaPrimaPorcentage4CapaA() {
        return materiaPrimaPorcentage4CapaA;
    }

    public void setMateriaPrimaPorcentage4CapaA(Double materiaPrimaPorcentage4CapaA) {
        this.materiaPrimaPorcentage4CapaA = materiaPrimaPorcentage4CapaA;
    }

    public Integer getIdMateriaPrima5CapaA() {
        return idMateriaPrima5CapaA;
    }

    public void setIdMateriaPrima5CapaA(Integer idMateriaPrima5CapaA) {
        this.idMateriaPrima5CapaA = idMateriaPrima5CapaA;
    }

    public Double getMateriaPrimaPorcentage5CapaA() {
        return materiaPrimaPorcentage5CapaA;
    }

    public void setMateriaPrimaPorcentage5CapaA(Double materiaPrimaPorcentage5CapaA) {
        this.materiaPrimaPorcentage5CapaA = materiaPrimaPorcentage5CapaA;
    }

    public Integer getIdMateriaPrima6CapaA() {
        return idMateriaPrima6CapaA;
    }

    public void setIdMateriaPrima6CapaA(Integer idMateriaPrima6CapaA) {
        this.idMateriaPrima6CapaA = idMateriaPrima6CapaA;
    }

    public Double getMateriaPrimaPorcentage6CapaA() {
        return materiaPrimaPorcentage6CapaA;
    }

    public void setMateriaPrimaPorcentage6CapaA(Double materiaPrimaPorcentage6CapaA) {
        this.materiaPrimaPorcentage6CapaA = materiaPrimaPorcentage6CapaA;
    }

    public Integer getIdMateriaPrima1CapaB() {
        return idMateriaPrima1CapaB;
    }

    public void setIdMateriaPrima1CapaB(Integer idMateriaPrima1CapaB) {
        this.idMateriaPrima1CapaB = idMateriaPrima1CapaB;
    }

    public Double getMateriaPrimaPorcentage1CapaB() {
        return materiaPrimaPorcentage1CapaB;
    }

    public void setMateriaPrimaPorcentage1CapaB(Double materiaPrimaPorcentage1CapaB) {
        this.materiaPrimaPorcentage1CapaB = materiaPrimaPorcentage1CapaB;
    }

    public Integer getIdMateriaPrima2CapaB() {
        return idMateriaPrima2CapaB;
    }

    public void setIdMateriaPrima2CapaB(Integer idMateriaPrima2CapaB) {
        this.idMateriaPrima2CapaB = idMateriaPrima2CapaB;
    }

    public Double getMateriaPrimaPorcentage2CapaB() {
        return materiaPrimaPorcentage2CapaB;
    }

    public void setMateriaPrimaPorcentage2CapaB(Double materiaPrimaPorcentage2CapaB) {
        this.materiaPrimaPorcentage2CapaB = materiaPrimaPorcentage2CapaB;
    }

    public Integer getIdMateriaPrima3CapaB() {
        return idMateriaPrima3CapaB;
    }

    public void setIdMateriaPrima3CapaB(Integer idMateriaPrima3CapaB) {
        this.idMateriaPrima3CapaB = idMateriaPrima3CapaB;
    }

    public Double getMateriaPrimaPorcentage3CapaB() {
        return materiaPrimaPorcentage3CapaB;
    }

    public void setMateriaPrimaPorcentage3CapaB(Double materiaPrimaPorcentage3CapaB) {
        this.materiaPrimaPorcentage3CapaB = materiaPrimaPorcentage3CapaB;
    }

    public Integer getIdMateriaPrima4CapaB() {
        return idMateriaPrima4CapaB;
    }

    public void setIdMateriaPrima4CapaB(Integer idMateriaPrima4CapaB) {
        this.idMateriaPrima4CapaB = idMateriaPrima4CapaB;
    }

    public Double getMateriaPrimaPorcentage4CapaB() {
        return materiaPrimaPorcentage4CapaB;
    }

    public void setMateriaPrimaPorcentage4CapaB(Double materiaPrimaPorcentage4CapaB) {
        this.materiaPrimaPorcentage4CapaB = materiaPrimaPorcentage4CapaB;
    }

    public Integer getIdMateriaPrima5CapaB() {
        return idMateriaPrima5CapaB;
    }

    public void setIdMateriaPrima5CapaB(Integer idMateriaPrima5CapaB) {
        this.idMateriaPrima5CapaB = idMateriaPrima5CapaB;
    }

    public Double getMateriaPrimaPorcentage5CapaB() {
        return materiaPrimaPorcentage5CapaB;
    }

    public void setMateriaPrimaPorcentage5CapaB(Double materiaPrimaPorcentage5CapaB) {
        this.materiaPrimaPorcentage5CapaB = materiaPrimaPorcentage5CapaB;
    }

    public Integer getIdMateriaPrima6CapaB() {
        return idMateriaPrima6CapaB;
    }

    public void setIdMateriaPrima6CapaB(Integer idMateriaPrima6CapaB) {
        this.idMateriaPrima6CapaB = idMateriaPrima6CapaB;
    }

    public Double getMateriaPrimaPorcentage6CapaB() {
        return materiaPrimaPorcentage6CapaB;
    }

    public void setMateriaPrimaPorcentage6CapaB(Double materiaPrimaPorcentage6CapaB) {
        this.materiaPrimaPorcentage6CapaB = materiaPrimaPorcentage6CapaB;
    }

    public Integer getIdMateriaPrima1CapaC() {
        return idMateriaPrima1CapaC;
    }

    public void setIdMateriaPrima1CapaC(Integer idMateriaPrima1CapaC) {
        this.idMateriaPrima1CapaC = idMateriaPrima1CapaC;
    }

    public Double getMateriaPrimaPorcentage1CapaC() {
        return materiaPrimaPorcentage1CapaC;
    }

    public void setMateriaPrimaPorcentage1CapaC(Double materiaPrimaPorcentage1CapaC) {
        this.materiaPrimaPorcentage1CapaC = materiaPrimaPorcentage1CapaC;
    }

    public Integer getIdMateriaPrima2CapaC() {
        return idMateriaPrima2CapaC;
    }

    public void setIdMateriaPrima2CapaC(Integer idMateriaPrima2CapaC) {
        this.idMateriaPrima2CapaC = idMateriaPrima2CapaC;
    }

    public Double getMateriaPrimaPorcentage2CapaC() {
        return materiaPrimaPorcentage2CapaC;
    }

    public void setMateriaPrimaPorcentage2CapaC(Double materiaPrimaPorcentage2CapaC) {
        this.materiaPrimaPorcentage2CapaC = materiaPrimaPorcentage2CapaC;
    }

    public Integer getIdMateriaPrima3CapaC() {
        return idMateriaPrima3CapaC;
    }

    public void setIdMateriaPrima3CapaC(Integer idMateriaPrima3CapaC) {
        this.idMateriaPrima3CapaC = idMateriaPrima3CapaC;
    }

    public Double getMateriaPrimaPorcentage3CapaC() {
        return materiaPrimaPorcentage3CapaC;
    }

    public void setMateriaPrimaPorcentage3CapaC(Double materiaPrimaPorcentage3CapaC) {
        this.materiaPrimaPorcentage3CapaC = materiaPrimaPorcentage3CapaC;
    }

    public Integer getIdMateriaPrima4CapaC() {
        return idMateriaPrima4CapaC;
    }

    public void setIdMateriaPrima4CapaC(Integer idMateriaPrima4CapaC) {
        this.idMateriaPrima4CapaC = idMateriaPrima4CapaC;
    }

    public Double getMateriaPrimaPorcentage4CapaC() {
        return materiaPrimaPorcentage4CapaC;
    }

    public void setMateriaPrimaPorcentage4CapaC(Double materiaPrimaPorcentage4CapaC) {
        this.materiaPrimaPorcentage4CapaC = materiaPrimaPorcentage4CapaC;
    }

    public Integer getIdMateriaPrima5CapaC() {
        return idMateriaPrima5CapaC;
    }

    public void setIdMateriaPrima5CapaC(Integer idMateriaPrima5CapaC) {
        this.idMateriaPrima5CapaC = idMateriaPrima5CapaC;
    }

    public Double getMateriaPrimaPorcentage5CapaC() {
        return materiaPrimaPorcentage5CapaC;
    }

    public void setMateriaPrimaPorcentage5CapaC(Double materiaPrimaPorcentage5CapaC) {
        this.materiaPrimaPorcentage5CapaC = materiaPrimaPorcentage5CapaC;
    }

    public Integer getIdMateriaPrima6CapaC() {
        return idMateriaPrima6CapaC;
    }

    public void setIdMateriaPrima6CapaC(Integer idMateriaPrima6CapaC) {
        this.idMateriaPrima6CapaC = idMateriaPrima6CapaC;
    }

    public Double getMateriaPrimaPorcentage6CapaC() {
        return materiaPrimaPorcentage6CapaC;
    }

    public void setMateriaPrimaPorcentage6CapaC(Double materiaPrimaPorcentage6CapaC) {
        this.materiaPrimaPorcentage6CapaC = materiaPrimaPorcentage6CapaC;
    }

    public Integer getIdMateriaPrima1CapaD() {
        return idMateriaPrima1CapaD;
    }

    public void setIdMateriaPrima1CapaD(Integer idMateriaPrima1CapaD) {
        this.idMateriaPrima1CapaD = idMateriaPrima1CapaD;
    }

    public Double getMateriaPrimaPorcentage1CapaD() {
        return materiaPrimaPorcentage1CapaD;
    }

    public void setMateriaPrimaPorcentage1CapaD(Double materiaPrimaPorcentage1CapaD) {
        this.materiaPrimaPorcentage1CapaD = materiaPrimaPorcentage1CapaD;
    }

    public Integer getIdMateriaPrima2CapaD() {
        return idMateriaPrima2CapaD;
    }

    public void setIdMateriaPrima2CapaD(Integer idMateriaPrima2CapaD) {
        this.idMateriaPrima2CapaD = idMateriaPrima2CapaD;
    }

    public Double getMateriaPrimaPorcentage2CapaD() {
        return materiaPrimaPorcentage2CapaD;
    }

    public void setMateriaPrimaPorcentage2CapaD(Double materiaPrimaPorcentage2CapaD) {
        this.materiaPrimaPorcentage2CapaD = materiaPrimaPorcentage2CapaD;
    }

    public Integer getIdMateriaPrima3CapaD() {
        return idMateriaPrima3CapaD;
    }

    public void setIdMateriaPrima3CapaD(Integer idMateriaPrima3CapaD) {
        this.idMateriaPrima3CapaD = idMateriaPrima3CapaD;
    }

    public Double getMateriaPrimaPorcentage3CapaD() {
        return materiaPrimaPorcentage3CapaD;
    }

    public void setMateriaPrimaPorcentage3CapaD(Double materiaPrimaPorcentage3CapaD) {
        this.materiaPrimaPorcentage3CapaD = materiaPrimaPorcentage3CapaD;
    }

    public Integer getIdMateriaPrima4CapaD() {
        return idMateriaPrima4CapaD;
    }

    public void setIdMateriaPrima4CapaD(Integer idMateriaPrima4CapaD) {
        this.idMateriaPrima4CapaD = idMateriaPrima4CapaD;
    }

    public Double getMateriaPrimaPorcentage4CapaD() {
        return materiaPrimaPorcentage4CapaD;
    }

    public void setMateriaPrimaPorcentage4CapaD(Double materiaPrimaPorcentage4CapaD) {
        this.materiaPrimaPorcentage4CapaD = materiaPrimaPorcentage4CapaD;
    }

    public Integer getIdMateriaPrima5CapaD() {
        return idMateriaPrima5CapaD;
    }

    public void setIdMateriaPrima5CapaD(Integer idMateriaPrima5CapaD) {
        this.idMateriaPrima5CapaD = idMateriaPrima5CapaD;
    }

    public Double getMateriaPrimaPorcentage5CapaD() {
        return materiaPrimaPorcentage5CapaD;
    }

    public void setMateriaPrimaPorcentage5CapaD(Double materiaPrimaPorcentage5CapaD) {
        this.materiaPrimaPorcentage5CapaD = materiaPrimaPorcentage5CapaD;
    }

    public Integer getIdMateriaPrima6CapaD() {
        return idMateriaPrima6CapaD;
    }

    public void setIdMateriaPrima6CapaD(Integer idMateriaPrima6CapaD) {
        this.idMateriaPrima6CapaD = idMateriaPrima6CapaD;
    }

    public Double getMateriaPrimaPorcentage6CapaD() {
        return materiaPrimaPorcentage6CapaD;
    }

    public void setMateriaPrimaPorcentage6CapaD(Double materiaPrimaPorcentage6CapaD) {
        this.materiaPrimaPorcentage6CapaD = materiaPrimaPorcentage6CapaD;
    }

    public Integer getIdMateriaPrima1CapaE() {
        return idMateriaPrima1CapaE;
    }

    public void setIdMateriaPrima1CapaE(Integer idMateriaPrima1CapaE) {
        this.idMateriaPrima1CapaE = idMateriaPrima1CapaE;
    }

    public Double getMateriaPrimaPorcentage1CapaE() {
        return materiaPrimaPorcentage1CapaE;
    }

    public void setMateriaPrimaPorcentage1CapaE(Double materiaPrimaPorcentage1CapaE) {
        this.materiaPrimaPorcentage1CapaE = materiaPrimaPorcentage1CapaE;
    }

    public Integer getIdMateriaPrima2CapaE() {
        return idMateriaPrima2CapaE;
    }

    public void setIdMateriaPrima2CapaE(Integer idMateriaPrima2CapaE) {
        this.idMateriaPrima2CapaE = idMateriaPrima2CapaE;
    }

    public Double getMateriaPrimaPorcentage2CapaE() {
        return materiaPrimaPorcentage2CapaE;
    }

    public void setMateriaPrimaPorcentage2CapaE(Double materiaPrimaPorcentage2CapaE) {
        this.materiaPrimaPorcentage2CapaE = materiaPrimaPorcentage2CapaE;
    }

    public Integer getIdMateriaPrima3CapaE() {
        return idMateriaPrima3CapaE;
    }

    public void setIdMateriaPrima3CapaE(Integer idMateriaPrima3CapaE) {
        this.idMateriaPrima3CapaE = idMateriaPrima3CapaE;
    }

    public Double getMateriaPrimaPorcentage3CapaE() {
        return materiaPrimaPorcentage3CapaE;
    }

    public void setMateriaPrimaPorcentage3CapaE(Double materiaPrimaPorcentage3CapaE) {
        this.materiaPrimaPorcentage3CapaE = materiaPrimaPorcentage3CapaE;
    }

    public Integer getIdMateriaPrima4CapaE() {
        return idMateriaPrima4CapaE;
    }

    public void setIdMateriaPrima4CapaE(Integer idMateriaPrima4CapaE) {
        this.idMateriaPrima4CapaE = idMateriaPrima4CapaE;
    }

    public Double getMateriaPrimaPorcentage4CapaE() {
        return materiaPrimaPorcentage4CapaE;
    }

    public void setMateriaPrimaPorcentage4CapaE(Double materiaPrimaPorcentage4CapaE) {
        this.materiaPrimaPorcentage4CapaE = materiaPrimaPorcentage4CapaE;
    }

    public Integer getIdMateriaPrima5CapaE() {
        return idMateriaPrima5CapaE;
    }

    public void setIdMateriaPrima5CapaE(Integer idMateriaPrima5CapaE) {
        this.idMateriaPrima5CapaE = idMateriaPrima5CapaE;
    }

    public Double getMateriaPrimaPorcentage5CapaE() {
        return materiaPrimaPorcentage5CapaE;
    }

    public void setMateriaPrimaPorcentage5CapaE(Double materiaPrimaPorcentage5CapaE) {
        this.materiaPrimaPorcentage5CapaE = materiaPrimaPorcentage5CapaE;
    }

    public Integer getIdMateriaPrima6CapaE() {
        return idMateriaPrima6CapaE;
    }

    public void setIdMateriaPrima6CapaE(Integer idMateriaPrima6CapaE) {
        this.idMateriaPrima6CapaE = idMateriaPrima6CapaE;
    }

    public Double getMateriaPrimaPorcentage6CapaE() {
        return materiaPrimaPorcentage6CapaE;
    }

    public void setMateriaPrimaPorcentage6CapaE(Double materiaPrimaPorcentage6CapaE) {
        this.materiaPrimaPorcentage6CapaE = materiaPrimaPorcentage6CapaE;
    }

    public Integer getIdMateriaPrima1CapaF() {
        return idMateriaPrima1CapaF;
    }

    public void setIdMateriaPrima1CapaF(Integer idMateriaPrima1CapaF) {
        this.idMateriaPrima1CapaF = idMateriaPrima1CapaF;
    }

    public Double getMateriaPrimaPorcentage1CapaF() {
        return materiaPrimaPorcentage1CapaF;
    }

    public void setMateriaPrimaPorcentage1CapaF(Double materiaPrimaPorcentage1CapaF) {
        this.materiaPrimaPorcentage1CapaF = materiaPrimaPorcentage1CapaF;
    }

    public Integer getIdMateriaPrima2CapaF() {
        return idMateriaPrima2CapaF;
    }

    public void setIdMateriaPrima2CapaF(Integer idMateriaPrima2CapaF) {
        this.idMateriaPrima2CapaF = idMateriaPrima2CapaF;
    }

    public Double getMateriaPrimaPorcentage2CapaF() {
        return materiaPrimaPorcentage2CapaF;
    }

    public void setMateriaPrimaPorcentage2CapaF(Double materiaPrimaPorcentage2CapaF) {
        this.materiaPrimaPorcentage2CapaF = materiaPrimaPorcentage2CapaF;
    }

    public Integer getIdMateriaPrima3CapaF() {
        return idMateriaPrima3CapaF;
    }

    public void setIdMateriaPrima3CapaF(Integer idMateriaPrima3CapaF) {
        this.idMateriaPrima3CapaF = idMateriaPrima3CapaF;
    }

    public Double getMateriaPrimaPorcentage3CapaF() {
        return materiaPrimaPorcentage3CapaF;
    }

    public void setMateriaPrimaPorcentage3CapaF(Double materiaPrimaPorcentage3CapaF) {
        this.materiaPrimaPorcentage3CapaF = materiaPrimaPorcentage3CapaF;
    }

    public Integer getIdMateriaPrima4CapaF() {
        return idMateriaPrima4CapaF;
    }

    public void setIdMateriaPrima4CapaF(Integer idMateriaPrima4CapaF) {
        this.idMateriaPrima4CapaF = idMateriaPrima4CapaF;
    }

    public Double getMateriaPrimaPorcentage4CapaF() {
        return materiaPrimaPorcentage4CapaF;
    }

    public void setMateriaPrimaPorcentage4CapaF(Double materiaPrimaPorcentage4CapaF) {
        this.materiaPrimaPorcentage4CapaF = materiaPrimaPorcentage4CapaF;
    }

    public Integer getIdMateriaPrima5CapaF() {
        return idMateriaPrima5CapaF;
    }

    public void setIdMateriaPrima5CapaF(Integer idMateriaPrima5CapaF) {
        this.idMateriaPrima5CapaF = idMateriaPrima5CapaF;
    }

    public Double getMateriaPrimaPorcentage5CapaF() {
        return materiaPrimaPorcentage5CapaF;
    }

    public void setMateriaPrimaPorcentage5CapaF(Double materiaPrimaPorcentage5CapaF) {
        this.materiaPrimaPorcentage5CapaF = materiaPrimaPorcentage5CapaF;
    }

    public Integer getIdMateriaPrima6CapaF() {
        return idMateriaPrima6CapaF;
    }

    public void setIdMateriaPrima6CapaF(Integer idMateriaPrima6CapaF) {
        this.idMateriaPrima6CapaF = idMateriaPrima6CapaF;
    }

    public Double getMateriaPrimaPorcentage6CapaF() {
        return materiaPrimaPorcentage6CapaF;
    }

    public void setMateriaPrimaPorcentage6CapaF(Double materiaPrimaPorcentage6CapaF) {
        this.materiaPrimaPorcentage6CapaF = materiaPrimaPorcentage6CapaF;
    }

    public Integer getIdMateriaPrima1CapaG() {
        return idMateriaPrima1CapaG;
    }

    public void setIdMateriaPrima1CapaG(Integer idMateriaPrima1CapaG) {
        this.idMateriaPrima1CapaG = idMateriaPrima1CapaG;
    }

    public Double getMateriaPrimaPorcentage1CapaG() {
        return materiaPrimaPorcentage1CapaG;
    }

    public void setMateriaPrimaPorcentage1CapaG(Double materiaPrimaPorcentage1CapaG) {
        this.materiaPrimaPorcentage1CapaG = materiaPrimaPorcentage1CapaG;
    }

    public Integer getIdMateriaPrima2CapaG() {
        return idMateriaPrima2CapaG;
    }

    public void setIdMateriaPrima2CapaG(Integer idMateriaPrima2CapaG) {
        this.idMateriaPrima2CapaG = idMateriaPrima2CapaG;
    }

    public Double getMateriaPrimaPorcentage2CapaG() {
        return materiaPrimaPorcentage2CapaG;
    }

    public void setMateriaPrimaPorcentage2CapaG(Double materiaPrimaPorcentage2CapaG) {
        this.materiaPrimaPorcentage2CapaG = materiaPrimaPorcentage2CapaG;
    }

    public Integer getIdMateriaPrima3CapaG() {
        return idMateriaPrima3CapaG;
    }

    public void setIdMateriaPrima3CapaG(Integer idMateriaPrima3CapaG) {
        this.idMateriaPrima3CapaG = idMateriaPrima3CapaG;
    }

    public Double getMateriaPrimaPorcentage3CapaG() {
        return materiaPrimaPorcentage3CapaG;
    }

    public void setMateriaPrimaPorcentage3CapaG(Double materiaPrimaPorcentage3CapaG) {
        this.materiaPrimaPorcentage3CapaG = materiaPrimaPorcentage3CapaG;
    }

    public Integer getIdMateriaPrima4CapaG() {
        return idMateriaPrima4CapaG;
    }

    public void setIdMateriaPrima4CapaG(Integer idMateriaPrima4CapaG) {
        this.idMateriaPrima4CapaG = idMateriaPrima4CapaG;
    }

    public Double getMateriaPrimaPorcentage4CapaG() {
        return materiaPrimaPorcentage4CapaG;
    }

    public void setMateriaPrimaPorcentage4CapaG(Double materiaPrimaPorcentage4CapaG) {
        this.materiaPrimaPorcentage4CapaG = materiaPrimaPorcentage4CapaG;
    }

    public Integer getIdMateriaPrima5CapaG() {
        return idMateriaPrima5CapaG;
    }

    public void setIdMateriaPrima5CapaG(Integer idMateriaPrima5CapaG) {
        this.idMateriaPrima5CapaG = idMateriaPrima5CapaG;
    }

    public Double getMateriaPrimaPorcentage5CapaG() {
        return materiaPrimaPorcentage5CapaG;
    }

    public void setMateriaPrimaPorcentage5CapaG(Double materiaPrimaPorcentage5CapaG) {
        this.materiaPrimaPorcentage5CapaG = materiaPrimaPorcentage5CapaG;
    }

    public Integer getIdMateriaPrima6CapaG() {
        return idMateriaPrima6CapaG;
    }

    public void setIdMateriaPrima6CapaG(Integer idMateriaPrima6CapaG) {
        this.idMateriaPrima6CapaG = idMateriaPrima6CapaG;
    }

    public Double getMateriaPrimaPorcentage6CapaG() {
        return materiaPrimaPorcentage6CapaG;
    }

    public void setMateriaPrimaPorcentage6CapaG(Double materiaPrimaPorcentage6CapaG) {
        this.materiaPrimaPorcentage6CapaG = materiaPrimaPorcentage6CapaG;
    }

    public Double getResumenCapaA() {
        return resumenCapaA;
    }

    public void setResumenCapaA(Double resumenCapaA) {
        this.resumenCapaA = resumenCapaA;
    }

    public Double getResumenCapaB() {
        return resumenCapaB;
    }

    public void setResumenCapaB(Double resumenCapaB) {
        this.resumenCapaB = resumenCapaB;
    }

    public Double getResumenCapaC() {
        return resumenCapaC;
    }

    public void setResumenCapaC(Double resumenCapaC) {
        this.resumenCapaC = resumenCapaC;
    }

    public Double getResumenCapaD() {
        return resumenCapaD;
    }

    public void setResumenCapaD(Double resumenCapaD) {
        this.resumenCapaD = resumenCapaD;
    }

    public Double getResumenCapaE() {
        return resumenCapaE;
    }

    public void setResumenCapaE(Double resumenCapaE) {
        this.resumenCapaE = resumenCapaE;
    }

    public Double getResumenCapaF() {
        return resumenCapaF;
    }

    public void setResumenCapaF(Double resumenCapaF) {
        this.resumenCapaF = resumenCapaF;
    }

    public Double getResumenCapaG() {
        return resumenCapaG;
    }

    public void setResumenCapaG(Double resumenCapaG) {
        this.resumenCapaG = resumenCapaG;
    }
           
    
    @Override
    public String toString() {
        return "Articulo [id=" + id + ", idArticulo=" + idArticulo + ", fechaAlta=" + fechaAlta + "]";
    }
    
}