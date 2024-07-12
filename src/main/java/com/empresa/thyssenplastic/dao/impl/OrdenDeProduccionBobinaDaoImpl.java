/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionBobinaDao;
import com.empresa.thyssenplastic.dto.OrdenDepositoDto;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionBobinaDaoImpl extends GenericDaoImpl implements OrdenDeProduccionBobinaDao {
    
        public List<OrdenDeProduccionBobinaModel> getAll() {
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinaBobinas = new ArrayList<OrdenDeProduccionBobinaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProduccionBobinaBobinas = session.createQuery("from OrdenDeProduccionBobinaModel ", OrdenDeProduccionBobinaModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBobinaBobinas;
    }

    public OrdenDeProduccionBobinaModel getByPk(Integer pk) {
        OrdenDeProduccionBobinaModel ordenDeProduccionBobina = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBobinaModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinaBobinas = query.list();
                if(ordenDeProduccionBobinaBobinas != null && !ordenDeProduccionBobinaBobinas.isEmpty()) {
                    ordenDeProduccionBobina = ordenDeProduccionBobinaBobinas.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBobina;
    }

    public OrdenDeProduccionBobinaModel getByCode(String codigo) {
        OrdenDeProduccionBobinaModel ordenDeProduccionBobina = null;        
        try {
            if(codigo != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBobinaModel WHERE codigo=:codigo");
                query.setString("codigo", codigo);
                
                List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinaBobinas = query.list();
                if(ordenDeProduccionBobinaBobinas != null && !ordenDeProduccionBobinaBobinas.isEmpty()) {
                    ordenDeProduccionBobina = ordenDeProduccionBobinaBobinas.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBobina;
    }

    public List<OrdenDeProduccionBobinaModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinaBobinas = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBobinaModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionBobinaBobinas = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBobinaBobinas;
    }

    public List<OrdenDeProduccionBobinaModel> getAllAvailableByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinaBobinas = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBobinaModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaEnBulto = 0");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionBobinaBobinas = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBobinaBobinas;
    }
 
    public List<OrdenDeProduccionBobinaModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinas = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBobinaModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaDisponibleParaRemito = 1");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionBobinas = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBobinas;
    }

    public List<OrdenDeProduccionBobinaModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinas = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBobinaModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaDisponibleParaRemito = 0");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionBobinas = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

            return ordenDeProduccionBobinas;
        }
        
        @Override
        public List<OrdenDepositoDto> getAllByDeposito() {
            List<OrdenDepositoDto> resultList = new ArrayList<OrdenDepositoDto>();

            try {
        Session session = HibernateUtil.getSessionFactory().openSession();
      
        String hql = "SELECT opb.idOrdenDeProduccion, " +
             "op.idArticulo, " +
             "t.valor AS nombreDeposito, " +
             "SUM(opb.pesoNeto) AS sumapeso, " +
             "SUM(CASE WHEN opb.idRemito IS NULL THEN 1 ELSE 0 END) AS unidad, " +
             "(SELECT SUM(m.cantidad) FROM RemitoDetalleModel m WHERE m.idDeposito = opb.idDeposito AND m.idOrdenDeProduccion = opb.idOrdenDeProduccion AND m.idRemito IS NOT NULL " +
             "AND NOT EXISTS (SELECT 1 FROM OrdenDeProduccionBobinaModel ob WHERE ob.idOrdenDeProduccion = opb.idOrdenDeProduccion AND ob.idRemito IS NOT NULL)) AS sumaCantidadTablaRemitoDetalle, " +
             "SUM(CASE WHEN opb.idRemito IS NOT NULL THEN opb.pesoNeto ELSE 0 END) AS pesoConsumido " +
             "FROM OrdenDeProduccionBobinaModel opb " +
             "JOIN TipoModel t ON opb.idDeposito = t.id " +
             "JOIN OrdenDeProduccionModel op ON opb.idOrdenDeProduccion = op.id " +
             "WHERE opb.idDeposito IS NOT NULL " +
             "GROUP BY opb.idOrdenDeProduccion, opb.idDeposito, op.idArticulo, t.valor";


                Query query = session.createQuery(hql);

                List<Object[]> resultListFromQuery = query.list();

                for (Object[] result : resultListFromQuery) {
                    Integer idOrdenDeProduccion = (Integer) result[0];
                    Integer idArticulo = (Integer) result[1];
                    String nombreDeposito = (String) result[2];
                    double sumapeso = (Double) result[3];
                    Integer unidad = result[4] != null ? ((Long) result[4]).intValue() : 0;  // Manejar nulos de manera segura
                    Integer remitoDetalle = result[5] != null ? ((Long) result[5]).intValue() : 0;
                    double pesoConsumido = (Double) result[6];  // Acceder al peso consumido

                    OrdenDepositoDto dto = new OrdenDepositoDto(idOrdenDeProduccion, idArticulo, nombreDeposito, sumapeso, unidad, remitoDetalle, pesoConsumido);
                    resultList.add(dto);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return resultList;
        }

    
}
