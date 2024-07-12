/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller.form;

/**
 *
 * @author gusta
 */
public class RemitoDetalleForm {
    
    private String pk;
    private String codigo;
    private String fechaAlta;    
    private String articulo;
    private String idArticulo;
    private String idRemito;    
    private String codigoRemito;
    private String idBobina;  
    private String idBulto;  
    private String idPallet;
    private String idOrdenDeProduccion;
    private String cantidadItem;
    private String unidadesProducidas;
    private String stockActual;
    private String stock;
    private String codigoOrdenDeProduccion;
    private String codigoBobina;
    private String estadoBobina;
    private String codigoBulto;
    private String estadoBulto;
    private String depositoBulto;
    private String codigoPallet;
    private String estadoPallet;
    private String depositoPallet;
    private String tipo;
    private String idUsuarioAlta;
    private String operacion;
    private String action;
    private String tieneBultoOPallet;
    private String idOrdenDeProduccionEdit;
    private String idArticuloEdit;
    private String tipoEdit;
    private String idOrdenDeProduccionRemove;
    private String idDeposito;
    private String cantidad;
    private String lote;

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }


    public String getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(String idDeposito) {
        this.idDeposito = idDeposito;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    //REMITO
    private String fechaRemito;     
    private String tipoRemito;  
    private String estadoRemito;
    private String cliente;
    private String domicilio;
    private String localidad;
    private String provincia;
    private String idChofer;
    private String nombreContacto;
    private String telefonoContacto;
    private String puntoGps;
    private String observaciones;

    public String getPuntoGps() {
        return puntoGps;
    }

    public void setPuntoGps(String puntoGps) {
        this.puntoGps = puntoGps;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(String idChofer) {
        this.idChofer = idChofer;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    private String transporte;
    private String referenciaAdministrativa;

    //REMOVE
    private String viewArticulo; 
    private String viewLote;     
    private String viewCodigo;     
    private String viewCodigoHdn;     
    private String viewTipo;     
    private String viewTipoHdn;     
    private String viewCantidad;     
    
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

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(String idRemito) {
        this.idRemito = idRemito;
    }

    public String getIdBobina() {
        return idBobina;
    }

    public void setIdBobina(String idBobina) {
        this.idBobina = idBobina;
    }

    public String getIdBulto() {
        return idBulto;
    }

    public void setIdBulto(String idBulto) {
        this.idBulto = idBulto;
    }

    public String getIdPallet() {
        return idPallet;
    }

    public void setIdPallet(String idPallet) {
        this.idPallet = idPallet;
    }

    public String getIdOrdenDeProduccion() {
        return idOrdenDeProduccion;
    }

    public void setIdOrdenDeProduccion(String idOrdenDeProduccion) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
    }

    public String getUnidadesProducidas() {
        return unidadesProducidas;
    }

    public void setUnidadesProducidas(String unidadesProducidas) {
        this.unidadesProducidas = unidadesProducidas;
    }

    public String getCodigoOrdenDeProduccion() {
        return codigoOrdenDeProduccion;
    }

    public void setCodigoOrdenDeProduccion(String codigoOrdenDeProduccion) {
        this.codigoOrdenDeProduccion = codigoOrdenDeProduccion;
    }

    public String getCodigoBobina() {
        return codigoBobina;
    }

    public void setCodigoBobina(String codigoBobina) {
        this.codigoBobina = codigoBobina;
    }

    public String getEstadoBobina() {
        return estadoBobina;
    }

    public void setEstadoBobina(String estadoBobina) {
        this.estadoBobina = estadoBobina;
    }

    public String getCodigoBulto() {
        return codigoBulto;
    }

    public void setCodigoBulto(String codigoBulto) {
        this.codigoBulto = codigoBulto;
    }

    public String getEstadoBulto() {
        return estadoBulto;
    }

    public void setEstadoBulto(String estadoBulto) {
        this.estadoBulto = estadoBulto;
    }

    public String getCodigoPallet() {
        return codigoPallet;
    }

    public void setCodigoPallet(String codigoPallet) {
        this.codigoPallet = codigoPallet;
    }

    public String getEstadoPallet() {
        return estadoPallet;
    }

    public void setEstadoPallet(String estadoPallet) {
        this.estadoPallet = estadoPallet;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(String idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(String fechaRemito) {
        this.fechaRemito = fechaRemito;
    }

    public String getTipoRemito() {
        return tipoRemito;
    }

    public void setTipoRemito(String tipoRemito) {
        this.tipoRemito = tipoRemito;
    }

    public String getEstadoRemito() {
        return estadoRemito;
    }

    public void setEstadoRemito(String estadoRemito) {
        this.estadoRemito = estadoRemito;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getReferenciaAdministrativa() {
        return referenciaAdministrativa;
    }

    public void setReferenciaAdministrativa(String referenciaAdministrativa) {
        this.referenciaAdministrativa = referenciaAdministrativa;
    }

    public String getCantidadItem() {
        return cantidadItem;
    }

    public void setCantidadItem(String cantidadItem) {
        this.cantidadItem = cantidadItem;
    }

    public String getStockActual() {
        return stockActual;
    }

    public void setStockActual(String stockActual) {
        this.stockActual = stockActual;
    }

    public String getCodigoRemito() {
        return codigoRemito;
    }

    public void setCodigoRemito(String codigoRemito) {
        this.codigoRemito = codigoRemito;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getViewArticulo() {
        return viewArticulo;
    }

    public void setViewArticulo(String viewArticulo) {
        this.viewArticulo = viewArticulo;
    }

    public String getViewLote() {
        return viewLote;
    }

    public void setViewLote(String viewLote) {
        this.viewLote = viewLote;
    }

    public String getViewCodigo() {
        return viewCodigo;
    }

    public void setViewCodigo(String viewCodigo) {
        this.viewCodigo = viewCodigo;
    }

    public String getViewTipo() {
        return viewTipo;
    }

    public void setViewTipo(String viewTipo) {
        this.viewTipo = viewTipo;
    }

    public String getViewCantidad() {
        return viewCantidad;
    }

    public void setViewCantidad(String viewCantidad) {
        this.viewCantidad = viewCantidad;
    }

    public String getViewTipoHdn() {
        return viewTipoHdn;
    }

    public void setViewTipoHdn(String viewTipoHdn) {
        this.viewTipoHdn = viewTipoHdn;
    }

    public String getViewCodigoHdn() {
        return viewCodigoHdn;
    }

    public void setViewCodigoHdn(String viewCodigoHdn) {
        this.viewCodigoHdn = viewCodigoHdn;
    }

    public String getDepositoBulto() {
        return depositoBulto;
    }

    public void setDepositoBulto(String depositoBulto) {
        this.depositoBulto = depositoBulto;
    }

    public String getDepositoPallet() {
        return depositoPallet;
    }

    public void setDepositoPallet(String depositoPallet) {
        this.depositoPallet = depositoPallet;
    }

    public String getTieneBultoOPallet() {
        return tieneBultoOPallet;
    }

    public void setTieneBultoOPallet(String tieneBultoOPallet) {
        this.tieneBultoOPallet = tieneBultoOPallet;
    }

    public String getIdOrdenDeProduccionEdit() {
        return idOrdenDeProduccionEdit;
    }

    public void setIdOrdenDeProduccionEdit(String idOrdenDeProduccionEdit) {
        this.idOrdenDeProduccionEdit = idOrdenDeProduccionEdit;
    }

    public String getIdArticuloEdit() {
        return idArticuloEdit;
    }

    public void setIdArticuloEdit(String idArticuloEdit) {
        this.idArticuloEdit = idArticuloEdit;
    }

    public String getTipoEdit() {
        return tipoEdit;
    }

    public void setTipoEdit(String tipoEdit) {
        this.tipoEdit = tipoEdit;
    }

    public String getIdOrdenDeProduccionRemove() {
        return idOrdenDeProduccionRemove;
    }

    public void setIdOrdenDeProduccionRemove(String idOrdenDeProduccionRemove) {
        this.idOrdenDeProduccionRemove = idOrdenDeProduccionRemove;
    }


    
}
