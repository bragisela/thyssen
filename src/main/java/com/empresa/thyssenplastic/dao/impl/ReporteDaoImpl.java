/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ReporteDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.MantenimientoCorrectivoModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ReporteDaoImpl extends GenericDaoImpl implements ReporteDao {
    
    public List<MantenimientoCorrectivoModel> getAllByFilter(Date fechaDesde, Date fechaHasta, Integer idMaquina) {
        List<MantenimientoCorrectivoModel> mantenimientos = new ArrayList<MantenimientoCorrectivoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            
            String sqlQuery = "from MantenimientoCorrectivoModel WHERE  fechaAlta >= :fechaDesde ";

            if(fechaHasta != null) {
                sqlQuery += " AND fechaAlta <= :fechaHasta ";
            }
            if(idMaquina != null && idMaquina != -1) {
                sqlQuery += " AND idMaquina <= :idMaquina ";
            }
            
            Query query = session.createQuery(sqlQuery);
            query.setDate("fechaDesde", fechaDesde);
            
            if(fechaHasta != null) {
                query.setDate("fechaHasta", fechaHasta);
            }
            if(idMaquina != null && idMaquina != -1) {
                query.setInteger("idMaquina", idMaquina);
            }
            
            List<MantenimientoCorrectivoModel> list = query.list();
            if(list != null && !list.isEmpty()) {
                mantenimientos = list;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mantenimientos;
    }

}
