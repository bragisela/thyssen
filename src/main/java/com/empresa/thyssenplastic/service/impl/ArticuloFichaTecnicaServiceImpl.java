/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ArticuloFichaTecnicaDao;
import com.empresa.thyssenplastic.dao.impl.ArticuloFichaTecnicaDaoImpl;
import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import com.empresa.thyssenplastic.service.ArticuloFichaTecnicaService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ArticuloFichaTecnicaServiceImpl implements ArticuloFichaTecnicaService {
    
    public void save(ArticuloFichaTecnicaModel articuloFichaTecnicaModel) {
        try {
            ArticuloFichaTecnicaDao articuloFichaTecnicaDao = new ArticuloFichaTecnicaDaoImpl();
            articuloFichaTecnicaDao.save(articuloFichaTecnicaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ArticuloFichaTecnicaModel articuloFichaTecnicaModel) {
        try {
            ArticuloFichaTecnicaDao articuloFichaTecnicaDao = new ArticuloFichaTecnicaDaoImpl();
            articuloFichaTecnicaDao.delete(articuloFichaTecnicaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ArticuloFichaTecnicaModel> getAll() {
        List<ArticuloFichaTecnicaModel> articuloFichasTecnica = new ArrayList<ArticuloFichaTecnicaModel>();
        try {
            ArticuloFichaTecnicaDao articuloFichaTecnicaDao = new ArticuloFichaTecnicaDaoImpl();
            articuloFichasTecnica = articuloFichaTecnicaDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articuloFichasTecnica;
    }

    public List<ArticuloFichaTecnicaModel> getAllByArticulo(Integer idArticulo) {
        List<ArticuloFichaTecnicaModel> articuloFichasTecnica = new ArrayList<ArticuloFichaTecnicaModel>();
        try {
            ArticuloFichaTecnicaDao articuloFichaTecnicaDao = new ArticuloFichaTecnicaDaoImpl();
            articuloFichasTecnica = articuloFichaTecnicaDao.getAllByArticulo(idArticulo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articuloFichasTecnica;
    }
    
    public ArticuloFichaTecnicaModel getByPk(Integer pk) {
        ArticuloFichaTecnicaModel articuloFichaTecnica = null;
        try {
            ArticuloFichaTecnicaDao articuloFichaTecnicaDao = new ArticuloFichaTecnicaDaoImpl();
            articuloFichaTecnica = articuloFichaTecnicaDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articuloFichaTecnica;
    }
    
}
