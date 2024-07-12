/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.impl.ArticuloPrevisionDaoImpl;
import com.empresa.thyssenplastic.model.ArticuloPrevisionModel;
import java.util.ArrayList;
import java.util.List;
import com.empresa.thyssenplastic.dao.ArticuloPrevisionDao;
import com.empresa.thyssenplastic.service.ArticuloPrevisionService;

/**
 *
 * @author gusta
 */
public class ArticuloPrevisionServiceImpl implements ArticuloPrevisionService {
    
    public void save(ArticuloPrevisionModel articuloPrevisionModel) {
        try {
            ArticuloPrevisionDao articuloPrevisionDao = new ArticuloPrevisionDaoImpl();
            articuloPrevisionDao.save(articuloPrevisionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ArticuloPrevisionModel articuloPrevisionModel) {
        try {
            ArticuloPrevisionDao articuloPrevisionDao = new ArticuloPrevisionDaoImpl();
            articuloPrevisionDao.delete(articuloPrevisionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ArticuloPrevisionModel> getAll() {
        List<ArticuloPrevisionModel> articulosetiqueta = new ArrayList<ArticuloPrevisionModel>();
        try {
            ArticuloPrevisionDao articuloPrevisionDao = new ArticuloPrevisionDaoImpl();
            articulosetiqueta = articuloPrevisionDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulosetiqueta;
    }

        public List<ArticuloPrevisionModel> getAllByArticulo(Integer articuloPk) {
        List<ArticuloPrevisionModel> articulosetiqueta = new ArrayList<ArticuloPrevisionModel>();
        try {
            ArticuloPrevisionDao articuloPrevisionDao = new ArticuloPrevisionDaoImpl();
            articulosetiqueta = articuloPrevisionDao.getAllByArticulo(articuloPk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulosetiqueta;
    }
    
    public ArticuloPrevisionModel getByPk(Integer pk) {
        ArticuloPrevisionModel articuloetiqueta = null;
        try {
            ArticuloPrevisionDao articuloPrevisionDao = new ArticuloPrevisionDaoImpl();
            articuloetiqueta = articuloPrevisionDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articuloetiqueta;
    }
    
}
