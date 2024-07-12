/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface OrdenDeProduccionScrapDao extends GenericDao {
    
    List<OrdenDeProduccionScrapModel> getAll();
    
    OrdenDeProduccionScrapModel getByPk(Integer pk);
    
    OrdenDeProduccionScrapModel getByCode(String codigo);
    
    List<OrdenDeProduccionScrapModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    /*
    List<OrdenDeProduccionScrapModel> getAllAvailableByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionScrapModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionScrapModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion);
    */
}
