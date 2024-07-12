/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.GraficoBobinaDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.GraficoBobinaModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class GraficoBobinaDaoImpl extends GenericDaoImpl implements GraficoBobinaDao {
    
    public List<GraficoBobinaModel> getByIdOrdenDeProduccionBobina(Integer idOrdenDeProduccionBobina) {
        
        List<GraficoBobinaModel> graficosBobina = new ArrayList<GraficoBobinaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from GraficoBobinaModel WHERE idOrdenDeProduccionBobina=:idOrdenDeProduccionBobina");
            query.setInteger("idOrdenDeProduccionBobina", idOrdenDeProduccionBobina);

            graficosBobina = query.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosBobina;
    }

    public GraficoBobinaModel getByPk(Integer pk) {
        
        GraficoBobinaModel graficoBobina = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from GraficoBobinaModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<GraficoBobinaModel> graficosBobina = query.list();
                if(graficosBobina != null && !graficosBobina.isEmpty()) {
                    graficoBobina = graficosBobina.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficoBobina;
    }

    public List<GraficoBobinaModel> getAll() {
        List<GraficoBobinaModel> graficosBobina = new ArrayList<GraficoBobinaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            graficosBobina = session.createQuery("from GraficoBobinaModel ", GraficoBobinaModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosBobina;
    }    
}
