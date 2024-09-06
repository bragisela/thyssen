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
@Table(name = "remito")
public class RemitoModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "fechaRemito")
    private Date fechaRemito;
    
    @Column(name = "tipoRemito")
    private String tipoRemito;

    @Column(name = "estado")
    private String estado;

    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "idDomicilio")
    private Integer idDomicilio;

    @Column(name = "idContacto")
    private Integer idContacto;

    @Column(name = "idTransporte")
    private Integer idTransporte;
    
    @Column(name = "idChofer")
    private Integer idChofer;

    @Column(name = "referenciaAdministrativa")
    private String referenciaAdministrativa;

    @Column(name = "fechaCierre")
    private Date fechaCierre;
    
    @Column(name = "idUsuarioAlta")
    private Integer idUsuarioAlta;

    @Column(name = "idUsuarioCierre")
    private Integer idUsuarioCierre;

    @Column(name = "idUsuarioAbierto")
    private Integer idUsuarioAbierto;

    @Column(name = "idUsuarioCompletado")
    private Integer idUsuarioCompletado;

    @Column(name = "fechaAbierto")
    private Date fechaAbierto;

    @Column(name = "fechaCompletado")
    private Date fechaCompletado;

    @Column(name = "estaEnHojaDeRuta")
    private Boolean estaEnHojaDeRuta;
    
    @Column(name = "cantidadTotal")
    private Integer cantidadTotal;
    
    @Column(name = "cantidadTotalBaja")
    private Integer cantidadTotalBaja;
    
    @Column(name = "observaciones")
    private String observaciones;
    
    @Column(name = "isScrap")
    private Boolean isScrap;

    public Boolean getIsScrap() {
        return isScrap;
    }

    public void setIsScrap(Boolean isScrap) {
        this.isScrap = isScrap;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    

    public Integer getCantidadTotalBaja() {
        return cantidadTotalBaja;
    }

    public void setCantidadTotalBaja(Integer cantidadTotalBaja) {
        this.cantidadTotalBaja = cantidadTotalBaja;
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

    public String getTipoRemito() {
        return tipoRemito;
    }

    public void setTipoRemito(String tipoRemito) {
        this.tipoRemito = tipoRemito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Integer getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(Integer idTransporte) {
        this.idTransporte = idTransporte;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Integer getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public Integer getIdUsuarioCierre() {
        return idUsuarioCierre;
    }

    public void setIdUsuarioCierre(Integer idUsuarioCierre) {
        this.idUsuarioCierre = idUsuarioCierre;
    }

    public Date getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(Date fechaRemito) {
        this.fechaRemito = fechaRemito;
    }

    public String getReferenciaAdministrativa() {
        return referenciaAdministrativa;
    }

    public void setReferenciaAdministrativa(String referenciaAdministrativa) {
        this.referenciaAdministrativa = referenciaAdministrativa;
    }

    public Integer getIdUsuarioAbierto() {
        return idUsuarioAbierto;
    }

    public void setIdUsuarioAbierto(Integer idUsuarioAbierto) {
        this.idUsuarioAbierto = idUsuarioAbierto;
    }

    public Integer getIdUsuarioCompletado() {
        return idUsuarioCompletado;
    }

    public void setIdUsuarioCompletado(Integer idUsuarioCompletado) {
        this.idUsuarioCompletado = idUsuarioCompletado;
    }

    public Date getFechaAbierto() {
        return fechaAbierto;
    }

    public void setFechaAbierto(Date fechaAbierto) {
        this.fechaAbierto = fechaAbierto;
    }

    public Date getFechaCompletado() {
        return fechaCompletado;
    }

    public void setFechaCompletado(Date fechaCompletado) {
        this.fechaCompletado = fechaCompletado;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public Boolean getEstaEnHojaDeRuta() {
        return estaEnHojaDeRuta;
    }

    public void setEstaEnHojaDeRuta(Boolean estaEnHojaDeRuta) {
        this.estaEnHojaDeRuta = estaEnHojaDeRuta;
    }
    
     public Integer getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(Integer idChofer) {
        this.idChofer = idChofer;
    }
    

      
    @Override
    public String toString() {
        return "User [id=" + id + ", fechaAlta=" + fechaAlta + ", idCliente=" + idCliente + "]";
    }
    
}