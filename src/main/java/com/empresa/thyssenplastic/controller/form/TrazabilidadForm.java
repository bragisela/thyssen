/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller.form;

/**
 *
 * @author gusta
 */
public class TrazabilidadForm {
    
    private String codigoSearch;
    private String idOrdenDeProduccion;
    private String fechaPedido;
    private String fechaAltaOrdenDeProduccion;
    private String estadoOrdenDeProduccion;
    private String idCliente;
    private String cliente;
    private String idArticulo;
    private String articulo;
    private String version;
    private String cantidadAProducir;
    private String usuarioAltaOrdenDeProduccion;
    private String usuarioAbiertaOrdenDeProduccion;
    private String usuarioFabricacionOrdenDeProduccion;
    private String usuarioEmpaqueOrdenDeProduccion;
    private String usuarioPalletOrdenDeProduccion;
    private String usuarioCierreOrdenDeProduccion;
    private String fechaAltaImpresionOrdenDeProduccion;
    private String fechaAbiertaOrdenDeProduccion;
    private String fechaFabricacionOrdenDeProduccion;    
    private String fechaEmpaqueOrdenDeProduccion;
    private String fechaPalletOrdenDeProduccion;
    private String fechaCierreOrdenDeProduccion;
    
    //BOBINA
    private String codigoBobina;
    private String pesoCono;
    private String pesoTotal;
    private String pesoNeto;    
    private String estadoBobina;
    private String fechaAltaBobina;
    private String usuarioAltaBobina;
    
    //BULTO
    private String codigoBulto;
    private String plegadora;
    private String estadoBulto;
    private String fechaAltaBulto;
    private String usuarioAltaBulto;

    //PALLET
    private String codigoPallet;
    private String estadoPallet;
    private String cantidadBultos;
    private String listaCodigoBultos;
    private String fechaAltaPallet;
    private String usuarioAltaPallet;
    
    //REMITO
    private String codigoRemito;
    private String estadoRemito;
    private String fechaAltaRemito;
    private String usuarioAltaRemito;
    private String tipoRemito;
    private String transporteRemito;

    //HOJA DE RUTA
    private String codigoHojaDeRuta;
    private String estadoHojaDeRuta;
    private String fechaAltaHojaDeRuta;
    private String fechaCarga;
    private String fechaSalida;    
    private String usuarioAltaHojaDeRuta;
    private String chofer;
    
    public String getCodigoSearch() {
        return codigoSearch;
    }

    public void setCodigoSearch(String codigoSearch) {
        this.codigoSearch = codigoSearch;
    }

    public String getIdOrdenDeProduccion() {
        return idOrdenDeProduccion;
    }

    public void setIdOrdenDeProduccion(String idOrdenDeProduccion) {
        this.idOrdenDeProduccion = idOrdenDeProduccion;
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

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCantidadAProducir() {
        return cantidadAProducir;
    }

    public void setCantidadAProducir(String cantidadAProducir) {
        this.cantidadAProducir = cantidadAProducir;
    }

    public String getUsuarioAltaOrdenDeProduccion() {
        return usuarioAltaOrdenDeProduccion;
    }

    public void setUsuarioAltaOrdenDeProduccion(String usuarioAltaOrdenDeProduccion) {
        this.usuarioAltaOrdenDeProduccion = usuarioAltaOrdenDeProduccion;
    }

    public String getUsuarioAbiertaOrdenDeProduccion() {
        return usuarioAbiertaOrdenDeProduccion;
    }

    public void setUsuarioAbiertaOrdenDeProduccion(String usuarioAbiertaOrdenDeProduccion) {
        this.usuarioAbiertaOrdenDeProduccion = usuarioAbiertaOrdenDeProduccion;
    }

    public String getUsuarioFabricacionOrdenDeProduccion() {
        return usuarioFabricacionOrdenDeProduccion;
    }

    public void setUsuarioFabricacionOrdenDeProduccion(String usuarioFabricacionOrdenDeProduccion) {
        this.usuarioFabricacionOrdenDeProduccion = usuarioFabricacionOrdenDeProduccion;
    }

    public String getUsuarioEmpaqueOrdenDeProduccion() {
        return usuarioEmpaqueOrdenDeProduccion;
    }

    public void setUsuarioEmpaqueOrdenDeProduccion(String usuarioEmpaqueOrdenDeProduccion) {
        this.usuarioEmpaqueOrdenDeProduccion = usuarioEmpaqueOrdenDeProduccion;
    }

    public String getUsuarioPalletOrdenDeProduccion() {
        return usuarioPalletOrdenDeProduccion;
    }

    public void setUsuarioPalletOrdenDeProduccion(String usuarioPalletOrdenDeProduccion) {
        this.usuarioPalletOrdenDeProduccion = usuarioPalletOrdenDeProduccion;
    }

    public String getUsuarioCierreOrdenDeProduccion() {
        return usuarioCierreOrdenDeProduccion;
    }

    public void setUsuarioCierreOrdenDeProduccion(String usuarioCierreOrdenDeProduccion) {
        this.usuarioCierreOrdenDeProduccion = usuarioCierreOrdenDeProduccion;
    }

    public String getFechaAltaImpresionOrdenDeProduccion() {
        return fechaAltaImpresionOrdenDeProduccion;
    }

    public void setFechaAltaImpresionOrdenDeProduccion(String fechaAltaImpresionOrdenDeProduccion) {
        this.fechaAltaImpresionOrdenDeProduccion = fechaAltaImpresionOrdenDeProduccion;
    }

    public String getFechaAbiertaOrdenDeProduccion() {
        return fechaAbiertaOrdenDeProduccion;
    }

    public void setFechaAbiertaOrdenDeProduccion(String fechaAbiertaOrdenDeProduccion) {
        this.fechaAbiertaOrdenDeProduccion = fechaAbiertaOrdenDeProduccion;
    }

    public String getFechaFabricacionOrdenDeProduccion() {
        return fechaFabricacionOrdenDeProduccion;
    }

    public void setFechaFabricacionOrdenDeProduccion(String fechaFabricacionOrdenDeProduccion) {
        this.fechaFabricacionOrdenDeProduccion = fechaFabricacionOrdenDeProduccion;
    }

    public String getFechaEmpaqueOrdenDeProduccion() {
        return fechaEmpaqueOrdenDeProduccion;
    }

    public void setFechaEmpaqueOrdenDeProduccion(String fechaEmpaqueOrdenDeProduccion) {
        this.fechaEmpaqueOrdenDeProduccion = fechaEmpaqueOrdenDeProduccion;
    }

    public String getFechaPalletOrdenDeProduccion() {
        return fechaPalletOrdenDeProduccion;
    }

    public void setFechaPalletOrdenDeProduccion(String fechaPalletOrdenDeProduccion) {
        this.fechaPalletOrdenDeProduccion = fechaPalletOrdenDeProduccion;
    }

    public String getFechaCierreOrdenDeProduccion() {
        return fechaCierreOrdenDeProduccion;
    }

    public void setFechaCierreOrdenDeProduccion(String fechaCierreOrdenDeProduccion) {
        this.fechaCierreOrdenDeProduccion = fechaCierreOrdenDeProduccion;
    }

    public String getCodigoBobina() {
        return codigoBobina;
    }

    public void setCodigoBobina(String codigoBobina) {
        this.codigoBobina = codigoBobina;
    }

    public String getPesoCono() {
        return pesoCono;
    }

    public void setPesoCono(String pesoCono) {
        this.pesoCono = pesoCono;
    }

    public String getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(String pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public String getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(String pesoNeto) {
        this.pesoNeto = pesoNeto;
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

    public String getPlegadora() {
        return plegadora;
    }

    public void setPlegadora(String plegadora) {
        this.plegadora = plegadora;
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

    public String getCantidadBultos() {
        return cantidadBultos;
    }

    public void setCantidadBultos(String cantidadBultos) {
        this.cantidadBultos = cantidadBultos;
    }

    public String getListaCodigoBultos() {
        return listaCodigoBultos;
    }

    public void setListaCodigoBultos(String listaCodigoBultos) {
        this.listaCodigoBultos = listaCodigoBultos;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getFechaAltaBobina() {
        return fechaAltaBobina;
    }

    public void setFechaAltaBobina(String fechaAltaBobina) {
        this.fechaAltaBobina = fechaAltaBobina;
    }

    public String getUsuarioAltaBobina() {
        return usuarioAltaBobina;
    }

    public void setUsuarioAltaBobina(String usuarioAltaBobina) {
        this.usuarioAltaBobina = usuarioAltaBobina;
    }

    public String getFechaAltaBulto() {
        return fechaAltaBulto;
    }

    public void setFechaAltaBulto(String fechaAltaBulto) {
        this.fechaAltaBulto = fechaAltaBulto;
    }

    public String getUsuarioAltaBulto() {
        return usuarioAltaBulto;
    }

    public void setUsuarioAltaBulto(String usuarioAltaBulto) {
        this.usuarioAltaBulto = usuarioAltaBulto;
    }

    public String getFechaAltaPallet() {
        return fechaAltaPallet;
    }

    public void setFechaAltaPallet(String fechaAltaPallet) {
        this.fechaAltaPallet = fechaAltaPallet;
    }

    public String getUsuarioAltaPallet() {
        return usuarioAltaPallet;
    }

    public void setUsuarioAltaPallet(String usuarioAltaPallet) {
        this.usuarioAltaPallet = usuarioAltaPallet;
    }

    public String getCodigoRemito() {
        return codigoRemito;
    }

    public void setCodigoRemito(String codigoRemito) {
        this.codigoRemito = codigoRemito;
    }

    public String getEstadoRemito() {
        return estadoRemito;
    }

    public void setEstadoRemito(String estadoRemito) {
        this.estadoRemito = estadoRemito;
    }

    public String getFechaAltaRemito() {
        return fechaAltaRemito;
    }

    public void setFechaAltaRemito(String fechaAltaRemito) {
        this.fechaAltaRemito = fechaAltaRemito;
    }

    public String getUsuarioAltaRemito() {
        return usuarioAltaRemito;
    }

    public void setUsuarioAltaRemito(String usuarioAltaRemito) {
        this.usuarioAltaRemito = usuarioAltaRemito;
    }

    public String getTipoRemito() {
        return tipoRemito;
    }

    public void setTipoRemito(String tipoRemito) {
        this.tipoRemito = tipoRemito;
    }

    public String getTransporteRemito() {
        return transporteRemito;
    }

    public void setTransporteRemito(String transporteRemito) {
        this.transporteRemito = transporteRemito;
    }

    public String getCodigoHojaDeRuta() {
        return codigoHojaDeRuta;
    }

    public void setCodigoHojaDeRuta(String codigoHojaDeRuta) {
        this.codigoHojaDeRuta = codigoHojaDeRuta;
    }

    public String getEstadoHojaDeRuta() {
        return estadoHojaDeRuta;
    }

    public void setEstadoHojaDeRuta(String estadoHojaDeRuta) {
        this.estadoHojaDeRuta = estadoHojaDeRuta;
    }

    public String getFechaAltaHojaDeRuta() {
        return fechaAltaHojaDeRuta;
    }

    public void setFechaAltaHojaDeRuta(String fechaAltaHojaDeRuta) {
        this.fechaAltaHojaDeRuta = fechaAltaHojaDeRuta;
    }

    public String getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getUsuarioAltaHojaDeRuta() {
        return usuarioAltaHojaDeRuta;
    }

    public void setUsuarioAltaHojaDeRuta(String usuarioAltaHojaDeRuta) {
        this.usuarioAltaHojaDeRuta = usuarioAltaHojaDeRuta;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

   

    
}
