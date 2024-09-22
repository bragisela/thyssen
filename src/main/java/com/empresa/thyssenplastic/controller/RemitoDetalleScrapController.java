/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.ItemBean;
import com.empresa.thyssenplastic.controller.beans.OrdenDeCompraBean;
import com.empresa.thyssenplastic.controller.beans.StockOrdenDeCompraBean;
import com.empresa.thyssenplastic.controller.form.EgresoScrapForm;
import com.empresa.thyssenplastic.controller.form.RemitoDetalleForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.DepositoScrapDto;
import com.empresa.thyssenplastic.dto.OrdenDepositoDto;
import com.empresa.thyssenplastic.dto.RemitoDetalleDto;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import com.empresa.thyssenplastic.dto.RemitoDetalleScrapDto;
import com.empresa.thyssenplastic.service.impl.ActivacionManualServiceImpl;
import com.empresa.thyssenplastic.model.ActivacionManualModel;
import com.empresa.thyssenplastic.service.ActivacionManualService;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.model.LocalidadModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoDetalleScrapModel;
import com.empresa.thyssenplastic.model.RemitoModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteDomicilioService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.service.DomicilioService;
import com.empresa.thyssenplastic.service.LocalidadService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionScrapService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.RemitoDetalleScrapService;
import com.empresa.thyssenplastic.service.RemitoDetalleService;
import com.empresa.thyssenplastic.service.RemitoService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteDomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.DomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.LocalidadServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionScrapServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleScrapServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gusta
 */
@Controller
public class RemitoDetalleScrapController {
    

    @RequestMapping(value = "/remitoDetalleScrap/{idRemito}", method = RequestMethod.GET)
    public String getHomeRemitoDetalleScrap(@PathVariable String idRemito, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
    @RequestParam(value = "size", defaultValue = "5") int pageSize, HttpServletRequest req, ModelMap model) {
      
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);
        
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(idRemito == null) {
            model.addAttribute("errorMessage", "Error: id remito inválido");         
            return "/error";                            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(idRemito));

        if(remito == null) {
            model.addAttribute("errorMessage", "Error: remito no encontrado");         
            return "/error";                
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        
        DomicilioService domicilioService = new DomicilioServiceImpl();   
       
        String rol = "";    
        String operacion = "";        
        String displayActionButton = "block";
       
        
        EgresoScrapForm egresoScrapForm = new EgresoScrapForm();
       
                        
        RemitoDetalleForm remitoDetalleForm = new RemitoDetalleForm();
        remitoDetalleForm.setPk("-1");
        remitoDetalleForm.setAction("add");
        remitoDetalleForm.setIdRemito(remito.getId().toString());
        remitoDetalleForm.setCodigoRemito(remito.getId().toString());
        remitoDetalleForm.setFechaRemito(remito.getFechaRemito().toString().replace("00:00:00.0", ""));
        remitoDetalleForm.setTipoRemito(remito.getTipoRemito());
        remitoDetalleForm.setEstadoRemito(remito.getEstado());        
        if(remito.getReferenciaAdministrativa() != null) {
            remitoDetalleForm.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
        }
        ClienteModel cliente = null;
        if(remito.getIdCliente() != null) {
            cliente = clienteService.getByPk(remito.getIdCliente());
            if(cliente != null) {
                remitoDetalleForm.setCliente("(" + cliente.getId() + ") " + cliente.getRazonSocial());
            }
        }
        if(remito.getIdDomicilio() != null) {
            DomicilioModel domicilioModel = domicilioService.getByPk(remito.getIdDomicilio());
            if(domicilioModel != null) {
                String loc = "SN";
                String prov = "SN";
                        
                        TipoService tipoServicee = new TipoServiceImpl();
                        TipoModel localidaad = null;
                        TipoModel provincia = null;
                        
                        if (domicilioModel.getIdLocalidad() != null){
                            localidaad = tipoServicee.getByPk(domicilioModel.getIdLocalidad());
                         }
                        
                        if (domicilioModel.getIdProvincia() != null){
                            provincia = tipoServicee.getByPk(domicilioModel.getIdProvincia());
                         }
                        
                       if (localidaad != null){
                           loc =localidaad.getValor(); 
                       }
                       
                       if (provincia != null){
                           prov = provincia.getValor(); 
                       }

                remitoDetalleForm.setDomicilio(domicilioModel.getUbicacion() + " (" + loc + ", " + prov + ")");
            }
        }        
        
        if(remito.getIdTransporte() != null) {
            ProveedorService proveedorService = new ProveedorServiceImpl();
            ProveedorModel proveedor = proveedorService.getByPk(remito.getIdTransporte());
            remitoDetalleForm.setTransporte(proveedor.getRazonSocial());
            
        }
        if(remito.getIdChofer() != null) {
            ContactoService contactoService = new ContactoServiceImpl();
            ContactoModel c = contactoService.getByPk(remito.getIdChofer());
           remitoDetalleForm.setIdChofer(c.getNombre());
        }
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        remitoDetalleForm.setFechaAlta(fecha);

        if(user.getRol() == Utils.ROL_OFICINA) {
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
            
            if(!remito.getEstado().equalsIgnoreCase("Nuevo")) {
                displayActionButton = "none";
            }
            
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            operacion = "recepcion";
            displayActionButton = "none";            
            rol = "deposito";
        }
                
        remitoDetalleForm.setOperacion(operacion);
        
        model.addAttribute("remitoDetalleForm", remitoDetalleForm);  
        model.addAttribute("titleRemitoDetalle", "Nuevo Remito Detalle");  
        model.addAttribute("buttonLabel", "Guardar");
        

        ActivacionManualService activacionManualService = new ActivacionManualServiceImpl();
        ActivacionManualModel activacionManual = activacionManualService.getByPk(1);
       
 
        
        model.addAttribute("statusAct", activacionManual.getActivacionManual() || rol.equalsIgnoreCase("oficina"));
                
        model.addAttribute("remitoPk", remito.getId().toString());
        model.addAttribute("estadoRemito", remito.getEstado().toLowerCase());
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("remito", remitoDetalleForm);
        model.addAttribute("egresoScrapForm", egresoScrapForm); 
        
        ////////SCRAP
        OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();   
        List<DepositoScrapDto> listadoScrap = ordenDeProduccionScrapService.getResumenPorOrdenDeProduccion(pageNumber, pageSize);
        
      
        model.addAttribute("depositoList", listadoScrap);  
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("idRemito", idRemito);
        
        RemitoDetalleScrapService remitoDetalleScrapService = new RemitoDetalleScrapServiceImpl(); 
        List<RemitoDetalleScrapModel> listadoRemitoDetalleScrap = remitoDetalleScrapService.getAllByRemito(Integer.parseInt(idRemito));
        
        List<RemitoDetalleScrapDto> listadoRemitoDetalleScrapDto = new ArrayList<RemitoDetalleScrapDto>();
        
        ArticuloService articuloService = new ArticuloServiceImpl();
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
            
        
        for (RemitoDetalleScrapModel remitoDetalleScrap: listadoRemitoDetalleScrap) {
            RemitoDetalleScrapDto listRemitoDetalledto = new RemitoDetalleScrapDto();
            
            listRemitoDetalledto.setPk(remitoDetalleScrap.getId().toString());
            
            
            OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(remitoDetalleScrap.getIdOrdenDeProduccion());
            ArticuloModel articulo = null;
            if(ordenDeProduccion != null){
               articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
            }
            if(articulo != null) {
                listRemitoDetalledto.setArticulo(articulo.getDenominacion());
            }
            
            
            listRemitoDetalledto.setDadoDeBaja(remitoDetalleScrap.getDadoDeBaja());
            
             listRemitoDetalledto.setIdSrap(remitoDetalleScrap.getCodigo().substring(1));
            
            OrdenDeProduccionScrapModel scrapnn = ordenDeProduccionScrapService.getByCode(remitoDetalleScrap.getCodigo());
            
            Double cantidadTotal = scrapnn.getPesoTotal();
            Double cantidadUtilizadaRemito = remitoDetalleScrap.getCantidadUtilizadaRemito();
            
            // Asegúrate de que cantidadTotal no sea cero para evitar división por cero
            Double porcentajeDeUso = 0.0;
            if (cantidadTotal != null && cantidadTotal > 0) {
                porcentajeDeUso = (cantidadUtilizadaRemito / cantidadTotal) * 100;
            }

            // Puedes redondear el porcentaje a dos decimales si lo deseas
            porcentajeDeUso = Math.round(porcentajeDeUso * 100.0) / 100.0;

            
            listRemitoDetalledto.setCantidadUtilizadaRemito(cantidadUtilizadaRemito);
            
            listRemitoDetalledto.setDeposito("Scrap");
            
            listRemitoDetalledto.setLote(remitoDetalleScrap.getIdOrdenDeProduccion().toString());
            
            listRemitoDetalledto.setCodigo(remitoDetalleScrap.getCodigo());
            
            listRemitoDetalledto.setPorcentajeDeUso(porcentajeDeUso);
            
            listadoRemitoDetalleScrapDto.add(listRemitoDetalledto);
        }
        
        model.addAttribute("remitoDetallesScrap", listadoRemitoDetalleScrapDto);
        
        
        return "/remito/remitodetallescrap";
    }  
 
}
