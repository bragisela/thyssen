/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.EgresoDepositoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface EgresoDepositoDao extends GenericDao {
    
    List<EgresoDepositoModel> getAll();
    
    EgresoDepositoModel getByPk(Integer pk);
    
    EgresoDepositoModel getByIdBobina(Integer idBobina);
    
    EgresoDepositoModel getByIdBulto(Integer idBulto);
    
    EgresoDepositoModel getByIdPallet(Integer idPallet);
}
