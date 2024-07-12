/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.DomicilioModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface DomicilioDao extends GenericDao {
    
    List<DomicilioModel> getAll();
    
    DomicilioModel getByPk(Integer pk);
    
    List<DomicilioModel> getByPkList(List<Integer> pkList);
}
