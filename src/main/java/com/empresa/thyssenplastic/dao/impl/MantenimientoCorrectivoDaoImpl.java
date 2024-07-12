/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.MantenimientoCorrectivoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.MantenimientoCorrectivoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class MantenimientoCorrectivoDaoImpl extends GenericDaoImpl implements MantenimientoCorrectivoDao {
    
        public List<MantenimientoCorrectivoModel> getAll() {
        List<MantenimientoCorrectivoModel> mantenimientosCorrectivo = new ArrayList<MantenimientoCorrectivoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            mantenimientosCorrectivo = session.createQuery("from MantenimientoCorrectivoModel", MantenimientoCorrectivoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mantenimientosCorrectivo;
    }

    public MantenimientoCorrectivoModel getByPk(Integer pk) {
        MantenimientoCorrectivoModel mantenimientoCorrectivo = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from MantenimientoCorrectivoModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<MantenimientoCorrectivoModel> mantenimientosCorrectivo = query.list();
                if(mantenimientosCorrectivo != null && !mantenimientosCorrectivo.isEmpty()) {
                    mantenimientoCorrectivo = mantenimientosCorrectivo.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mantenimientoCorrectivo;
    }

}
