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
public class OrdenDeProduccionDetalleForm {
    
    private String pk;
    private String codigo;
    private String operacion;
    
    private String idCliente;
    private String idArticulo;
    private String idFichaTecnica;
    
    private String idOrdenProduccion;
    private String tipoDetalle;
    private String pesoTecnicoArticulo;
    private String espesorArticulo;
    private String anchoArticulo;            
    private String altoArticulo;
    private String metrosArticulo;
    private String fechaAltaOrdenDeProduccion;
    private String estadoOrdenDeProduccion;
    
    //BOBINA
    private String pesoConoBobina;
    private String pesoTotalBobina;
    private String pesoTotalBobinaBulto;

    private String pesoNetoBobina;
    private String espesorBobina;
    private String estadoBobina;
    private String observacionesBobina;
    private String estaEnBultoBobina;
    
    //BULTO
    private String searchCodigoBobina;
    private String bobinasDisponibles;
    private String estadoBulto;
    private String observacionesBulto;
    private String bobinaSelectedLabel;
    private String estadoBobinaSelectedLabel;
    private String idBobinaSelected;
    private String codigoBultoLabel;
    private String idPlegadora;

    //PALLET
    private String searchCodigoBulto;
    private String bultosDisponibles;
    private String estadoPallet;
    private String observacionesPallet;
    private List<String> bultosSelected;        
    private String codigoPalletLabel;
    
    //SCRAP    
    private String idOrigenScrap;
    private String idTipoMaterialScrap;
    private String idMotivoScrap;
    private String idFormatoScrap;
    private String esRecuperableScrap;
    private String materialImpresoScrap;
    private String pesoTotalScrap;
    private String observacionesScrap;
    
    private String imprimir;
    private String imprimirTipo;
    private String imprimirPk;
    
    private String action;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTipoDetalle() {
        return tipoDetalle;
    }

    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }

    public String getPesoTecnicoArticulo() {
        return pesoTecnicoArticulo;
    }

    public void setPesoTecnicoArticulo(String pesoTecnicoArticulo) {
        this.pesoTecnicoArticulo = pesoTecnicoArticulo;
    }

    public String getEspesorArticulo() {
        return espesorArticulo;
    }

    public void setEspesorArticulo(String espesorArticulo) {
        this.espesorArticulo = espesorArticulo;
    }

    public String getAnchoArticulo() {
        return anchoArticulo;
    }

    public void setAnchoArticulo(String anchoArticulo) {
        this.anchoArticulo = anchoArticulo;
    }

    public String getAltoArticulo() {
        return altoArticulo;
    }

    public void setAltoArticulo(String altoArticulo) {
        this.altoArticulo = altoArticulo;
    }

    public String getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(String idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public String getFechaAltaOrdenDeProduccion() {
        return fechaAltaOrdenDeProduccion;
    }

    public void setFechaAltaOrdenDeProduccion(String fechaAltaOrdenDeProduccion) {
        this.fechaAltaOrdenDeProduccion = fechaAltaOrdenDeProduccion;
    }

    public String getEstadoOrdenDeProduccion() {
        return estadoOrdenDeProduccion;
    }

    public void setEstadoOrdenDeProduccion(String estadoOrdenDeProduccion) {
        this.estadoOrdenDeProduccion = estadoOrdenDeProduccion;
    }

    public String getMetrosArticulo() {
        return metrosArticulo;
    }

    public void setMetrosArticulo(String metrosArticulo) {
        this.metrosArticulo = metrosArticulo;
    }

    public String getPesoConoBobina() {
        return pesoConoBobina;
    }

    public void setPesoConoBobina(String pesoConoBobina) {
        this.pesoConoBobina = pesoConoBobina;
    }
    
    public String getPesoTotalBobinaBulto() {
        return pesoTotalBobinaBulto;
    }

    public void setPesoTotalBobinaBulto(String pesoTotalBobinaBulto) {
        this.pesoTotalBobinaBulto = pesoTotalBobinaBulto;
    }

    public String getPesoTotalBobina() {
        return pesoTotalBobina;
    }

    public void setPesoTotalBobina(String pesoTotalBobina) {
        this.pesoTotalBobina = pesoTotalBobina;
    }

    public String getPesoNetoBobina() {
        return pesoNetoBobina;
    }

    public void setPesoNetoBobina(String pesoNetoBobina) {
        this.pesoNetoBobina = pesoNetoBobina;
    }

    public String getEstadoBobina() {
        return estadoBobina;
    }

    public void setEstadoBobina(String estadoBobina) {
        this.estadoBobina = estadoBobina;
    }

    public String getObservacionesBobina() {
        return observacionesBobina;
    }

    public void setObservacionesBobina(String observacionesBobina) {
        this.observacionesBobina = observacionesBobina;
    }

    public String getEstaEnBultoBobina() {
        return estaEnBultoBobina;
    }

    public void setEstaEnBultoBobina(String estaEnBultoBobina) {
        this.estaEnBultoBobina = estaEnBultoBobina;
    }

    public String getSearchCodigoBobina() {
        return searchCodigoBobina;
    }

    public void setSearchCodigoBobina(String searchCodigoBobina) {
        this.searchCodigoBobina = searchCodigoBobina;
    }

    public String getBobinasDisponibles() {
        return bobinasDisponibles;
    }

    public void setBobinasDisponibles(String bobinasDisponibles) {
        this.bobinasDisponibles = bobinasDisponibles;
    }

    public String getEstadoBulto() {
        return estadoBulto;
    }

    public void setEstadoBulto(String estadoBulto) {
        this.estadoBulto = estadoBulto;
    }

    public String getObservacionesBulto() {
        return observacionesBulto;
    }

    public void setObservacionesBulto(String observacionesBulto) {
        this.observacionesBulto = observacionesBulto;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getIdFichaTecnica() {
        return idFichaTecnica;
    }

    public void setIdFichaTecnica(String idFichaTecnica) {
        this.idFichaTecnica = idFichaTecnica;
    }

    public String getBobinaSelectedLabel() {
        return bobinaSelectedLabel;
    }

    public void setBobinaSelectedLabel(String bobinaSelectedLabel) {
        this.bobinaSelectedLabel = bobinaSelectedLabel;
    }

    public String getIdBobinaSelected() {
        return idBobinaSelected;
    }

    public void setIdBobinaSelected(String idBobinaSelected) {
        this.idBobinaSelected = idBobinaSelected;
    }

    public String getCodigoBultoLabel() {
        return codigoBultoLabel;
    }

    public void setCodigoBultoLabel(String codigoBultoLabel) {
        this.codigoBultoLabel = codigoBultoLabel;
    }

    public String getIdPlegadora() {
        return idPlegadora;
    }

    public void setIdPlegadora(String idPlegadora) {
        this.idPlegadora = idPlegadora;
    }

    public String getEstadoBobinaSelectedLabel() {
        return estadoBobinaSelectedLabel;
    }

    public void setEstadoBobinaSelectedLabel(String estadoBobinaSelectedLabel) {
        this.estadoBobinaSelectedLabel = estadoBobinaSelectedLabel;
    }

    public String getSearchCodigoBulto() {
        return searchCodigoBulto;
    }

    public void setSearchCodigoBulto(String searchCodigoBulto) {
        this.searchCodigoBulto = searchCodigoBulto;
    }

    public String getBultosDisponibles() {
        return bultosDisponibles;
    }

    public void setBultosDisponibles(String bultosDisponibles) {
        this.bultosDisponibles = bultosDisponibles;
    }

    public String getEstadoPallet() {
        return estadoPallet;
    }

    public void setEstadoPallet(String estadoPallet) {
        this.estadoPallet = estadoPallet;
    }

    public String getObservacionesPallet() {
        return observacionesPallet;
    }

    public void setObservacionesPallet(String observacionesPallet) {
        this.observacionesPallet = observacionesPallet;
    }

    public String getCodigoPalletLabel() {
        return codigoPalletLabel;
    }

    public void setCodigoPalletLabel(String codigoPalletLabel) {
        this.codigoPalletLabel = codigoPalletLabel;
    }

    public List<String> getBultosSelected() {
        return bultosSelected;
    }

    public void setBultosSelected(List<String> bultosSelected) {
        this.bultosSelected = bultosSelected;
    }

    public String getEspesorBobina() {
        return espesorBobina;
    }

    public void setEspesorBobina(String espesorBobina) {
        this.espesorBobina = espesorBobina;
    }

    public String getImprimir() {
        return imprimir;
    }

    public void setImprimir(String imprimir) {
        this.imprimir = imprimir;
    }

    public String getImprimirTipo() {
        return imprimirTipo;
    }

    public void setImprimirTipo(String imprimirTipo) {
        this.imprimirTipo = imprimirTipo;
    }

    public String getImprimirPk() {
        return imprimirPk;
    }

    public void setImprimirPk(String imprimirPk) {
        this.imprimirPk = imprimirPk;
    }

    public String getIdOrigenScrap() {
        return idOrigenScrap;
    }

    public void setIdOrigenScrap(String idOrigenScrap) {
        this.idOrigenScrap = idOrigenScrap;
    }

    public String getIdTipoMaterialScrap() {
        return idTipoMaterialScrap;
    }

    public void setIdTipoMaterialScrap(String idTipoMaterialScrap) {
        this.idTipoMaterialScrap = idTipoMaterialScrap;
    }

    public String getIdMotivoScrap() {
        return idMotivoScrap;
    }

    public void setIdMotivoScrap(String idMotivoScrap) {
        this.idMotivoScrap = idMotivoScrap;
    }

    public String getIdFormatoScrap() {
        return idFormatoScrap;
    }

    public void setIdFormatoScrap(String idFormatoScrap) {
        this.idFormatoScrap = idFormatoScrap;
    }

    public String getEsRecuperableScrap() {
        return esRecuperableScrap;
    }

    public void setEsRecuperableScrap(String esRecuperableScrap) {
        this.esRecuperableScrap = esRecuperableScrap;
    }

    public String getMaterialImpresoScrap() {
        return materialImpresoScrap;
    }

    public void setMaterialImpresoScrap(String materialImpresoScrap) {
        this.materialImpresoScrap = materialImpresoScrap;
    }

    public String getPesoTotalScrap() {
        return pesoTotalScrap;
    }

    public void setPesoTotalScrap(String pesoTotalScrap) {
        this.pesoTotalScrap = pesoTotalScrap;
    }

    public String getObservacionesScrap() {
        return observacionesScrap;
    }

    public void setObservacionesScrap(String observacionesScrap) {
        this.observacionesScrap = observacionesScrap;
    }

    
}
