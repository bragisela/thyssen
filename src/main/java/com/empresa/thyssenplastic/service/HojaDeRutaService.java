/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.HojaDeRutaModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface HojaDeRutaService {
    
    void save(HojaDeRutaModel hojaDeRutaModel);
    
    void delete(HojaDeRutaModel hojaDeRutaModel);
    
    List<HojaDeRutaModel> getAll();
    
    HojaDeRutaModel getByPk(Integer pk);
}
