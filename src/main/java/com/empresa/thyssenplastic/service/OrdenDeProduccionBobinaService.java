/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.dto.OrdenDepositoDto;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface OrdenDeProduccionBobinaService {
    
    void save(OrdenDeProduccionBobinaModel ordenDeProduccionModel);
    
    void delete(OrdenDeProduccionBobinaModel ordenDeProduccionModel);
    
    List<OrdenDeProduccionBobinaModel> getAll();
    
    OrdenDeProduccionBobinaModel getByPk(Integer pk);
    
    OrdenDeProduccionBobinaModel getByCode(String codigo);
    
    List<OrdenDeProduccionBobinaModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionBobinaModel> getAllAvailableByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionBobinaModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDeProduccionBobinaModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion);
    
    List<OrdenDepositoDto> getAllByDeposito();
}
