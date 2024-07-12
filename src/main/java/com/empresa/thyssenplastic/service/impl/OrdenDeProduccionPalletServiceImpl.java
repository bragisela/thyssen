/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionPalletDao;
import com.empresa.thyssenplastic.dao.impl.OrdenDeProduccionPalletDaoImpl;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionPalletServiceImpl implements OrdenDeProduccionPalletService {
    
    public void save(OrdenDeProduccionPalletModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionPalletDao ordenDeProduccionPalletDao = new OrdenDeProduccionPalletDaoImpl();
            ordenDeProduccionPalletDao.save(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(OrdenDeProduccionPalletModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionPalletDao ordenDeProduccionPalletDao = new OrdenDeProduccionPalletDaoImpl();
            ordenDeProduccionPalletDao.delete(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<OrdenDeProduccionPalletModel> getAll() {
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletModel>();
        try {
            OrdenDeProduccionPalletDao ordenDeProduccionPalletDao = new OrdenDeProduccionPalletDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }

    public List<OrdenDeProduccionPalletModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletModel>();
        try {
            OrdenDeProduccionPalletDao ordenDeProduccionPalletDao = new OrdenDeProduccionPalletDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletDao.getAllByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
    public List<OrdenDeProduccionPalletModel> getAllByOrdenDeProduccionAndFecha(Integer idOrdenDeProduccion, Date fechaInicio, Date fechaFin) {
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletModel>();
        try {
            OrdenDeProduccionPalletDao ordenDeProduccionPalletDao = new OrdenDeProduccionPalletDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletDao.getAllByOrdenDeProduccionAndFecha(idOrdenDeProduccion, fechaInicio, fechaFin );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
    public OrdenDeProduccionPalletModel getByPk(Integer pk) {
        OrdenDeProduccionPalletModel ordenDeProduccionPallet = null;
        try {
            OrdenDeProduccionPalletDao ordenDeProduccionPalletDao = new OrdenDeProduccionPalletDaoImpl();
            ordenDeProduccionPallet = ordenDeProduccionPalletDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallet;
    }

    public OrdenDeProduccionPalletModel getByCode(String codigo) {
        OrdenDeProduccionPalletModel ordenDeProduccionPallet = null;
        try {
            OrdenDeProduccionPalletDao ordenDeProduccionPalletDao = new OrdenDeProduccionPalletDaoImpl();
            ordenDeProduccionPallet = ordenDeProduccionPalletDao.getByCode(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallet;
    }
    
    public List<OrdenDeProduccionPalletModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletModel>();
        try {
            OrdenDeProduccionPalletDao ordenDeProduccionPalletDao = new OrdenDeProduccionPalletDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletDao.getAllAvailableForRemitoByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }

    public List<OrdenDeProduccionPalletModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletModel>();
        try {
            OrdenDeProduccionPalletDao ordenDeProduccionPalletDao = new OrdenDeProduccionPalletDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletDao.getAllNotAvailableForRemitoByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
}
