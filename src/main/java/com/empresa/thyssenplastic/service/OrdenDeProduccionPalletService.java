/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface OrdenDeProduccionPalletService {
    
    void save(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel);
    
    void delete(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel);
    
    List<OrdenDeProduccionPalletModel> getAll();
    
    OrdenDeProduccionPalletModel getByPk(Integer pk);
    
    OrdenDeProduccionPalletModel getByCode(String codigo);
    
    List<OrdenDeProduccionPalletModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionPalletModel> getAllByOrdenDeProduccionAndFecha(Integer idOrdenDeProduccion, Date fechaInicio, Date fechaFin);
      
    List<OrdenDeProduccionPalletModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionPalletModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion);
}
