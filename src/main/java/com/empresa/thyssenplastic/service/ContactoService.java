/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ContactoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ContactoService {
    
    void save(ContactoModel contactoModel);
    
    void delete(ContactoModel contactoModel);
    
    ContactoModel getByPk(Integer pk);
    
    List<ContactoModel> getAll();
    
    List<ContactoModel> getByPkList(List<Integer> pkList);
}
