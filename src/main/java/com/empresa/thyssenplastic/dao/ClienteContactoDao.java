/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ClienteContactoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ClienteContactoDao extends GenericDao {
    
    List<ClienteContactoModel> getAll();
    
    List<ClienteContactoModel> getByPkCliente(Integer pk);
    
    ClienteContactoModel getByPkClienteAndContacto(Integer pkCliente, Integer pkContacto);

}
