/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import java.util.List;
import java.util.Date;

/**
 *
 * @author gusta
 */
public interface OrdenDeProduccionBultoDao extends GenericDao {
    
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
