/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface MateriaPrimaService {
    
    void save(MateriaPrimaModel materiaprimaModel);
    
    void delete(MateriaPrimaModel materiaprimaModel);
    
    List<MateriaPrimaModel> getAll();
    
    MateriaPrimaModel getByPk(Integer pk);
    
    List<MateriaPrimaModel> getAllByIdProveedor(Integer idProveedor);
}
