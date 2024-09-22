/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoDetalleScrapModel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gusta
 */
public interface RemitoDetalleScrapService {
    
    void save(RemitoDetalleScrapModel remitoDetalleModel);
    
    void delete(RemitoDetalleScrapModel remitoDetalleModel);
    
    List<RemitoDetalleScrapModel> getAll();
    
    RemitoDetalleScrapModel getByPk(Integer pk);
    
    List<RemitoDetalleScrapModel> getAllByIdOrdenDeProduccionScrap(Integer idOrdenDeProduccionScrap);
    
    List<RemitoDetalleScrapModel> getAllByRemito(Integer idRemito);
    
    RemitoDetalleScrapModel getByCodigoAndRemito(String codigo, Integer idRemito);
    
    Double getSumaCantidadUtilizadaByCodigo(String codigo);
    
    Map<String, Double> getMapSumaCantidadUtilizadaByCodigo(String codigo);

    
}
