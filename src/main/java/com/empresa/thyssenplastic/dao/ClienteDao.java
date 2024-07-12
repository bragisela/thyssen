/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ClienteModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ClienteDao extends GenericDao {
    
    List<ClienteModel> getAll();
    
    ClienteModel getByPk(Integer pk);
}
