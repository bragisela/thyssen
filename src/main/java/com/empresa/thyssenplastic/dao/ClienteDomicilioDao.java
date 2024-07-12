/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ClienteDomicilioDao extends GenericDao {
    
    List<ClienteDomicilioModel> getAll();
    
    List<ClienteDomicilioModel> getByPkCliente(Integer pk);
    
    ClienteDomicilioModel getByPkClienteAndDomicilio(Integer pkCliente, Integer pkDomicilio);

    ClienteDomicilioModel getByPk(Integer pk);
}
