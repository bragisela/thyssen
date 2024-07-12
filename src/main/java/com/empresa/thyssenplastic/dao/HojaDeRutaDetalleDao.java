/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.HojaDeRutaDetalleModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface HojaDeRutaDetalleDao extends GenericDao {
    
    List<HojaDeRutaDetalleModel> getAll();
    
    HojaDeRutaDetalleModel getByPk(Integer pk);
    
    List<HojaDeRutaDetalleModel> getAllByHojaDeRuta(Integer idHojaDeRuta);
    
    HojaDeRutaDetalleModel getByRemito(Integer idRemito);
}
