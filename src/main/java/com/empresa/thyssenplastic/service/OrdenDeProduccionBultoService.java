/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface OrdenDeProduccionBultoService {
    
    void save(OrdenDeProduccionBultoModel ordenDeProduccionBultoModel);
    
    void delete(OrdenDeProduccionBultoModel ordenDeProduccionBultoModel);
    
    List<OrdenDeProduccionBultoModel> getAll();
    
    OrdenDeProduccionBultoModel getByPk(Integer pk);
    
    OrdenDeProduccionBultoModel getByCode(String codigo);
    
    List<OrdenDeProduccionBultoModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionBultoModel> getAllAvailableByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    OrdenDeProduccionBultoModel getByOrdenDeProduccionBobina(Integer idOrdenDeProduccionBobina);
 
    List<OrdenDeProduccionBultoModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionBultoModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionBultoModel> getByOrdenDeProduccionAndFechaAlta(Integer idOrdenDeProduccion, Date fechaDesde);
    
    List<OrdenDeProduccionBultoModel> getByFechaAlta(Date fechaDesde);
}
