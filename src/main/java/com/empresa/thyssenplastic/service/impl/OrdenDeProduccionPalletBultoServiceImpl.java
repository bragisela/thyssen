/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionPalletBultoDao;
import com.empresa.thyssenplastic.dao.impl.OrdenDeProduccionPalletBultoDaoImpl;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletBultoModel;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletBultoService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionPalletBultoServiceImpl implements OrdenDeProduccionPalletBultoService {
    
    public void save(OrdenDeProduccionPalletBultoModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionPalletBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionPalletBultoDaoImpl();
            ordenDeProduccionPalletBultoDao.save(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(OrdenDeProduccionPalletBultoModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionPalletBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionPalletBultoDaoImpl();
            ordenDeProduccionPalletBultoDao.delete(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<OrdenDeProduccionPalletBultoModel> getAll() {
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletBultoModel>();
        try {
            OrdenDeProduccionPalletBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionPalletBultoDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletBultoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }

    public List<OrdenDeProduccionPalletBultoModel> getAllByOrdenDeProduccionPallet(Integer idOrdenDeProduccionPallet) {
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletBultoModel>();
        try {
            OrdenDeProduccionPalletBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionPalletBultoDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletBultoDao.getAllByOrdenDeProduccionPallet(idOrdenDeProduccionPallet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }

    public List<OrdenDeProduccionPalletBultoModel> getByOrdenDeProduccionBulto(Integer idOrdenDeProduccionBulto) {
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletBultoModel>();
        try {
            OrdenDeProduccionPalletBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionPalletBultoDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletBultoDao.getByOrdenDeProduccionBulto(idOrdenDeProduccionBulto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
    public OrdenDeProduccionPalletBultoModel getByPk(Integer pk) {
        OrdenDeProduccionPalletBultoModel ordenDeProduccionPallet = null;
        try {
            OrdenDeProduccionPalletBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionPalletBultoDaoImpl();
            ordenDeProduccionPallet = ordenDeProduccionPalletBultoDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallet;
    }

    public OrdenDeProduccionPalletBultoModel getByCode(String codigo) {
        OrdenDeProduccionPalletBultoModel ordenDeProduccionPallet = null;
        try {
            OrdenDeProduccionPalletBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionPalletBultoDaoImpl();
            ordenDeProduccionPallet = ordenDeProduccionPalletBultoDao.getByCode(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallet;
    }
    
    public List<OrdenDeProduccionPalletBultoModel> getAllByOrdenDeProduccionPalletAndFecha(Integer idOrdenDeProduccionPallet, Date fechaInicio, Date fechaFin) {
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletBultoModel>();
        try {
            OrdenDeProduccionPalletBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionPalletBultoDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletBultoDao.getAllByOrdenDeProduccionPalletAndFecha(idOrdenDeProduccionPallet, fechaInicio, fechaFin);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
    public List<OrdenDeProduccionPalletBultoModel> getAllByOrdenDeProduccionPalletAndNotRemito(Integer idOrdenDeProduccionPallet) {
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletBultoModel>();
        try {
            OrdenDeProduccionPalletBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionPalletBultoDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletBultoDao.getAllByOrdenDeProduccionPalletAndNotRemito(idOrdenDeProduccionPallet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
}
