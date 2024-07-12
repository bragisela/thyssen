/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.HojaDeRutaDetalleModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface HojaDeRutaDetalleService {
    
    void save(HojaDeRutaDetalleModel hojaDeRutaModel);
    
    void delete(HojaDeRutaDetalleModel hojaDeRutaModel);
    
    List<HojaDeRutaDetalleModel> getAll();
    
    HojaDeRutaDetalleModel getByPk(Integer pk);
    
    List<HojaDeRutaDetalleModel> getAllByHojaDeRuta(Integer idHojaDeRuta);
    
    HojaDeRutaDetalleModel getByRemito(Integer idRemito);
    
}
