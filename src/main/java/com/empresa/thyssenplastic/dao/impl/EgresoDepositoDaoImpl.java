/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.EgresoDepositoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.EgresoDepositoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class EgresoDepositoDaoImpl extends GenericDaoImpl implements EgresoDepositoDao {
    
        public List<EgresoDepositoModel> getAll() {
        List<EgresoDepositoModel> egresoDepositos = new ArrayList<EgresoDepositoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            egresoDepositos = session.createQuery("from EgresoDepositoModel", EgresoDepositoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return egresoDepositos;
    }

    public EgresoDepositoModel getByPk(Integer pk) {
        EgresoDepositoModel egresoDeposito = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from IngresarDepositoModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<EgresoDepositoModel> ingresarDepositos = query.list();
                if(ingresarDepositos != null && !ingresarDepositos.isEmpty()) {
                    egresoDeposito = ingresarDepositos.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return egresoDeposito;
    }

}
