/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.TipoDao;
import com.empresa.thyssenplastic.dao.impl.TipoDaoImpl;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.service.TipoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class TipoServiceImpl implements TipoService {
    
    public void save(TipoModel tipoModel) {
        try {
            TipoDao tipoDao = new TipoDaoImpl();
            tipoDao.save(tipoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(TipoModel tipoModel) {
        try {
            TipoDao tipoDao = new TipoDaoImpl();
            tipoDao.delete(tipoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<TipoModel> getAll() {
        List<TipoModel> tipos = new ArrayList<TipoModel>();
        try {
            TipoDao tipoDao = new TipoDaoImpl();
            tipos = tipoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tipos;
    }

    public List<TipoModel> getAllByOrder() {
        List<TipoModel> tipos = new ArrayList<TipoModel>();
        try {
            TipoDao tipoDao = new TipoDaoImpl();
            tipos = tipoDao.getAllByOrder();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tipos;
    }
    
    public TipoModel getByPk(Integer pk) {
        TipoModel tipo = null;
        try {
            TipoDao tipoDao = new TipoDaoImpl();
            tipo = tipoDao.getByPk(pk);            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tipo;
    }
    
    public List<TipoModel> getByType(String type) {
        List<TipoModel> tipos = new ArrayList<TipoModel>();
        try {
            TipoDao tipoDao = new TipoDaoImpl();
            tipos = tipoDao.getByType(type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tipos;        
    }
    
    public TipoModel getByValor(String valor) {
        TipoModel tipo = null;
        try {
            TipoDao tipoDao = new TipoDaoImpl();
            tipo = tipoDao.getByValor(valor);            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tipo;
    }
}
