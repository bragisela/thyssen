/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.HojaDeRutaDetalleDao;
import com.empresa.thyssenplastic.dao.impl.HojaDeRutaDetalleDaoImpl;
import com.empresa.thyssenplastic.model.HojaDeRutaDetalleModel;
import com.empresa.thyssenplastic.service.HojaDeRutaDetalleService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class HojaDeRutaDetalleServiceImpl implements HojaDeRutaDetalleService {
    
    public void save(HojaDeRutaDetalleModel hojaDeRutaModel) {
        try {
            HojaDeRutaDetalleDao hojaDeRutaDetalleDao = new HojaDeRutaDetalleDaoImpl();
            hojaDeRutaDetalleDao.save(hojaDeRutaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(HojaDeRutaDetalleModel hojaDeRutaModel) {
        try {
            HojaDeRutaDetalleDao hojaDeRutaDetalleDao = new HojaDeRutaDetalleDaoImpl();
            hojaDeRutaDetalleDao.delete(hojaDeRutaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<HojaDeRutaDetalleModel> getAll() {
        List<HojaDeRutaDetalleModel> hojasDeRuta = new ArrayList<HojaDeRutaDetalleModel>();
        try {
            HojaDeRutaDetalleDao hojaDeRutaDetalleDao = new HojaDeRutaDetalleDaoImpl();
            hojasDeRuta = hojaDeRutaDetalleDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojasDeRuta;
    }
    
    public HojaDeRutaDetalleModel getByPk(Integer pk) {
        HojaDeRutaDetalleModel hojaDeRuta = null;
        try {
            HojaDeRutaDetalleDao hojaDeRutaDetalleDao = new HojaDeRutaDetalleDaoImpl();
            hojaDeRuta = hojaDeRutaDetalleDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojaDeRuta;
    }
    
    public List<HojaDeRutaDetalleModel> getAllByHojaDeRuta(Integer idHojaDeRuta) {
        List<HojaDeRutaDetalleModel> hojasDeRuta = new ArrayList<HojaDeRutaDetalleModel>();
        try {
            HojaDeRutaDetalleDao hojaDeRutaDetalleDao = new HojaDeRutaDetalleDaoImpl();
            hojasDeRuta = hojaDeRutaDetalleDao.getAllByHojaDeRuta(idHojaDeRuta);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojasDeRuta;
    }

    public HojaDeRutaDetalleModel getByRemito(Integer idRemito) {
        HojaDeRutaDetalleModel hojaDeRuta = null;
        try {
            HojaDeRutaDetalleDao hojaDeRutaDetalleDao = new HojaDeRutaDetalleDaoImpl();
            hojaDeRuta = hojaDeRutaDetalleDao.getByRemito(idRemito);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojaDeRuta;
    }
    
}
