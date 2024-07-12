/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletBultoModel;
import java.util.List;
import java.util.Date;

/**
 *
 * @author gusta
 */
public interface OrdenDeProduccionPalletBultoDao extends GenericDao {
    
    List<OrdenDeProduccionPalletBultoModel> getAll();
    
    OrdenDeProduccionPalletBultoModel getByPk(Integer pk);
    
    OrdenDeProduccionPalletBultoModel getByCode(String codigo);
    
    List<OrdenDeProduccionPalletBultoModel> getAllByOrdenDeProduccionPallet(Integer idOrdenDeProduccionPallet);
    
    List<OrdenDeProduccionPalletBultoModel> getAllByOrdenDeProduccionPalletAndNotRemito(Integer idOrdenDeProduccionPallet);
    
    List<OrdenDeProduccionPalletBultoModel> getByOrdenDeProduccionBulto(Integer idOrdenDeProduccionBulto);
    
    List<OrdenDeProduccionPalletBultoModel> getAllByOrdenDeProduccionPalletAndFecha(Integer idOrdenDeProduccionPallet, Date fechaInicio, Date fechaFin);
    
}
