/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.IngresarDepositoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.IngresarDepositoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class IngresarDepositoDaoImpl extends GenericDaoImpl implements IngresarDepositoDao {
    
        public List<IngresarDepositoModel> getAll() {
        List<IngresarDepositoModel> ingresarDepositos = new ArrayList<IngresarDepositoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ingresarDepositos = session.createQuery("from IngresarDepositoModel", IngresarDepositoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ingresarDepositos;
    }

    public IngresarDepositoModel getByPk(Integer pk) {
        IngresarDepositoModel ingresarDeposito = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from IngresarDepositoModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<IngresarDepositoModel> ingresarDepositos = query.list();
                if(ingresarDepositos != null && !ingresarDepositos.isEmpty()) {
                    ingresarDeposito = ingresarDepositos.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ingresarDeposito;
    }

}
