/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.HojaDeRutaDao;
import com.empresa.thyssenplastic.dao.impl.HojaDeRutaDaoImpl;
import com.empresa.thyssenplastic.model.HojaDeRutaModel;
import com.empresa.thyssenplastic.service.HojaDeRutaService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class HojaDeRutaServiceImpl implements HojaDeRutaService {
    
    public void save(HojaDeRutaModel hojaDeRutaModel) {
        try {
            HojaDeRutaDao hojaDeRutaDao = new HojaDeRutaDaoImpl();
            hojaDeRutaDao.save(hojaDeRutaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(HojaDeRutaModel hojaDeRutaModel) {
        try {
            HojaDeRutaDao hojaDeRutaDao = new HojaDeRutaDaoImpl();
            hojaDeRutaDao.delete(hojaDeRutaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<HojaDeRutaModel> getAll() {
        List<HojaDeRutaModel> hojasDeRuta = new ArrayList<HojaDeRutaModel>();
        try {
            HojaDeRutaDao hojaDeRutaDao = new HojaDeRutaDaoImpl();
            hojasDeRuta = hojaDeRutaDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojasDeRuta;
    }
    
    public HojaDeRutaModel getByPk(Integer pk) {
        HojaDeRutaModel hojaDeRuta = null;
        try {
            HojaDeRutaDao hojaDeRutaDao = new HojaDeRutaDaoImpl();
            hojaDeRuta = hojaDeRutaDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojaDeRuta;
    }
    
}
