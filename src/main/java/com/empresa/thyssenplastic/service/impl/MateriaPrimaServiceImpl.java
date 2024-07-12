/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.MateriaPrimaDao;
import com.empresa.thyssenplastic.dao.impl.MateriaPrimaDaoImpl;
import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import com.empresa.thyssenplastic.service.MateriaPrimaService;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author gusta
 */
public class MateriaPrimaServiceImpl implements MateriaPrimaService {
    
    public void save(MateriaPrimaModel materiaprimaModel) {
        try {
            MateriaPrimaDao materiaprimaDao = new MateriaPrimaDaoImpl();
            materiaprimaDao.save(materiaprimaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(MateriaPrimaModel materiaprimaModel) {
        try {
            MateriaPrimaDao materiaprimaDao = new MateriaPrimaDaoImpl();
            materiaprimaDao.delete(materiaprimaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<MateriaPrimaModel> getAll() {
        List<MateriaPrimaModel> materiasprima = new ArrayList<MateriaPrimaModel>();
        try {
            MateriaPrimaDao materiaprimaDao = new MateriaPrimaDaoImpl();
            materiasprima = materiaprimaDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return materiasprima;
    }

    public List<MateriaPrimaModel> getAllByIdProveedor(Integer idProveedor) {
        List<MateriaPrimaModel> materiasprima = new ArrayList<MateriaPrimaModel>();
        try {
            MateriaPrimaDao materiaprimaDao = new MateriaPrimaDaoImpl();
            materiasprima = materiaprimaDao.getAllByIdProveedor(idProveedor);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return materiasprima;
    }
    
    public MateriaPrimaModel getByPk(Integer pk) {
        MateriaPrimaModel materiaprima = null;
        try {
            MateriaPrimaDao materiaprimaDao = new MateriaPrimaDaoImpl();
            materiaprima = materiaprimaDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return materiaprima;
    }
    
}
