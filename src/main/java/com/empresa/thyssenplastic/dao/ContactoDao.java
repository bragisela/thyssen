/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ContactoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ContactoDao extends GenericDao {
    
    List<ContactoModel> getAll();
    
    ContactoModel getByPk(Integer pk);
    
    List<ContactoModel> getByPkList(List<Integer> pkList);
}
