/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.MantenimientoCorrectivoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface MantenimientoCorrectivoService {
    
    void save(MantenimientoCorrectivoModel mantenimientoCorrectivoModel);
    
    void delete(MantenimientoCorrectivoModel mantenimientoCorrectivoModel);
    
    List<MantenimientoCorrectivoModel> getAll();
    
    MantenimientoCorrectivoModel getByPk(Integer pk);
}
