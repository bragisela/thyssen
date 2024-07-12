/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface RemitoDetalleService {
    
    void save(RemitoDetalleModel remitoDetalleModel);
    
    void delete(RemitoDetalleModel remitoDetalleModel);
    
    List<RemitoDetalleModel> getAll();
    
    RemitoDetalleModel getByPk(Integer pk);
    
    List<RemitoDetalleModel> getAllByRemito(Integer idRemito);
    
    RemitoDetalleModel getByCompositeIds(int idRemito, int idDeposito, int idOrdenDeProduccion);
    
//    RemitoDetalleModel getByBobina(Integer idBobina);
//    
//    RemitoDetalleModel getByBulto(Integer idBulto);
//    
//    RemitoDetalleModel getByPallet(Integer idPallet);    
    
}
