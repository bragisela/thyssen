/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.impl.ArticuloEtiquetaDaoImpl;
import com.empresa.thyssenplastic.model.ArticuloEtiquetaModel;
import java.util.ArrayList;
import java.util.List;
import com.empresa.thyssenplastic.dao.ArticuloEtiquetaDao;
import com.empresa.thyssenplastic.service.ArticuloEtiquetaService;

/**
 *
 * @author gusta
 */
public class ArticuloEtiquetaServiceImpl implements ArticuloEtiquetaService {
    
    public void save(ArticuloEtiquetaModel articuloEtiquetaModel) {
        try {
            ArticuloEtiquetaDao articuloEtiquetaDao = new ArticuloEtiquetaDaoImpl();
            articuloEtiquetaDao.save(articuloEtiquetaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ArticuloEtiquetaModel articuloEtiquetaModel) {
        try {
            ArticuloEtiquetaDao articuloEtiquetaDao = new ArticuloEtiquetaDaoImpl();
            articuloEtiquetaDao.delete(articuloEtiquetaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ArticuloEtiquetaModel> getAll() {
        List<ArticuloEtiquetaModel> articulosetiqueta = new ArrayList<ArticuloEtiquetaModel>();
        try {
            ArticuloEtiquetaDao articuloEtiquetaDao = new ArticuloEtiquetaDaoImpl();
            articulosetiqueta = articuloEtiquetaDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulosetiqueta;
    }

        public List<ArticuloEtiquetaModel> getAllByArticulo(Integer articuloPk) {
        List<ArticuloEtiquetaModel> articulosetiqueta = new ArrayList<ArticuloEtiquetaModel>();
        try {
            ArticuloEtiquetaDao articuloEtiquetaDao = new ArticuloEtiquetaDaoImpl();
            articulosetiqueta = articuloEtiquetaDao.getAllByArticulo(articuloPk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulosetiqueta;
    }
    
    public ArticuloEtiquetaModel getByPk(Integer pk) {
        ArticuloEtiquetaModel articuloetiqueta = null;
        try {
            ArticuloEtiquetaDao articuloEtiquetaDao = new ArticuloEtiquetaDaoImpl();
            articuloetiqueta = articuloEtiquetaDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articuloetiqueta;
    }
    
}
