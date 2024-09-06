/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.ItemBean;
import com.empresa.thyssenplastic.controller.form.IngresarDepositoForm;
import com.empresa.thyssenplastic.controller.form.EgresoDepositoForm;
import com.empresa.thyssenplastic.controller.form.EgresoScrapForm;
import com.empresa.thyssenplastic.controller.form.TrazabilidadForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.IngresarDepositoDto;
import com.empresa.thyssenplastic.dto.MovimientoScrapDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBobinaDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBultoDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionScrapDto;
import com.empresa.thyssenplastic.model.ActivacionManualModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.EgresoDepositoModel;
import com.empresa.thyssenplastic.model.HojaDeRutaDetalleModel;
import com.empresa.thyssenplastic.model.HojaDeRutaModel;
import com.empresa.thyssenplastic.model.IngresarDepositoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoDetalleScrapModel;
import com.empresa.thyssenplastic.model.RemitoModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ActivacionManualService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.service.EgresoDepositoService;
import com.empresa.thyssenplastic.service.HojaDeRutaDetalleService;
import com.empresa.thyssenplastic.service.HojaDeRutaService;
import com.empresa.thyssenplastic.service.IngresarDepositoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionScrapService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.RemitoDetalleScrapService;
import com.empresa.thyssenplastic.service.RemitoDetalleService;
import com.empresa.thyssenplastic.service.RemitoService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ActivacionManualServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.EgresoDepositoServiceImpl;
import com.empresa.thyssenplastic.service.impl.HojaDeRutaDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.HojaDeRutaServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.IngresarDepositoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionScrapServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleScrapServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
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
public class IngresarDepositoScrapController {
    

    @RequestMapping(value = "/ingresarDepositoScrap", method = RequestMethod.GET)
    public String getHomeIngresarDepositoScrap(
    @RequestParam(value = "page", defaultValue = "1") int pageNumber,
    @RequestParam(value = "size", defaultValue = "5") int pageSize,
    HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        IngresarDepositoForm ingresarDepositoForm = new IngresarDepositoForm();
        ingresarDepositoForm.setPk("-1");
        ingresarDepositoForm.setAction("add");
        ingresarDepositoForm.setIdBobina("-1");
        ingresarDepositoForm.setIdBulto("-1");
        ingresarDepositoForm.setIdPallet("-1");
        ingresarDepositoForm.setTipo("-1");
        ingresarDepositoForm.setIdOrdenDeProduccion("-1");
        
       
        OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();   
        List<OrdenDeProduccionScrapModel> listadoScrap = ordenDeProduccionScrapService.getAllPaginated(pageNumber, pageSize);
       
        
   
        // Pasa los datos al modelo
        model.addAttribute("depositoList", listadoScrap);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageSize", pageSize);
       
            
                
        return "/ingresaradeposito/ingresaradepositoscrap";
    }
    
    @RequestMapping(value = "/verMovimientos/{id}", method = RequestMethod.GET)
    public String getHomeMovimientoScrap(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }
        
        UserService userService = new UserServiceImpl();
        
        //RemitoDetalleModel remitoDetalle = remitoDetalleService.getAllByOrdenDeProduccionScrap(74); //TRAER ID REMITO
        RemitoDetalleScrapService remitoDetalleScrap = new RemitoDetalleScrapServiceImpl();
        List<RemitoDetalleScrapModel> movimientoScrapList = new ArrayList<RemitoDetalleScrapModel>();
        movimientoScrapList = remitoDetalleScrap.getAllByIdOrdenDeProduccionScrap(30);
        
        RemitoService remitoService = new RemitoServiceImpl();
        List<MovimientoScrapDto> movimientoScrapDtos = new ArrayList<MovimientoScrapDto>();
       

        if (movimientoScrapList != null && !movimientoScrapList.isEmpty()) {
            
        // Lista para almacenar los resultados
        List<RemitoModel> remitos = new ArrayList<RemitoModel>();

        for (RemitoDetalleScrapModel detalleScrap : movimientoScrapList) {
            // Llamar al servicio para obtener el RemitoModel usando el ID del remito
            RemitoModel remito = remitoService.getByPk(detalleScrap.getIdRemito());
            // Agregar el RemitoModel a la lista de resultados
            if (remito != null) {
                MovimientoScrapDto movimientoScrapDto = new MovimientoScrapDto();
                movimientoScrapDto.setCodigoRemito(remito.getId().toString());
                movimientoScrapDto.setEstadoRemito(remito.getEstado());
                movimientoScrapDto.setFechaAltaRemito(remito.getFechaAlta().toString());
                
                 if(remito.getIdUsuarioAlta() != null) {
                    UserModel user = userService.getUserById(remito.getIdUsuarioAlta());
                    if(user != null) {
                        movimientoScrapDto.setUsuarioAltaRemito(user.getUsername());
                    }        
                }
                 
                 movimientoScrapDto.setFechaAltaDetalle(detalleScrap.getFecha().toString());
                 movimientoScrapDto.setCantidadUtilizadaRemito(detalleScrap.getCantidadUtilizadaRemito());
                 
                
                
                movimientoScrapDtos.add(movimientoScrapDto);
            }
        }

        // Agregar la lista de remitos al modelo
        model.addAttribute("remitos", movimientoScrapDtos);
          
        } else {
            model.addAttribute("movimientos", false);
        }
        
  
        return "/ingresaradeposito/movimientoscrap";
    }
    
    @RequestMapping(value = "/egresoDepositoScrap/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveEgresoDeposito(@ModelAttribute("egresoScrapForm")EgresoScrapForm egresoScrapForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        ModelAndView modelAndView = new ModelAndView();
        
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;
        }
        
        if(!Utils.isAutenticated(req)) {
            modelAndView.setViewName("/login/login");    
            model.addAttribute("userForm", new UserForm());         
            return modelAndView;
        }

             
        RemitoDetalleScrapService remitoDetalleScrap = new RemitoDetalleScrapServiceImpl();
        

        TipoService tipoService = new TipoServiceImpl();

        String sessionId = req.getSession().getId();
        //String id = egresoScrapForm.getPk();
            
        RemitoDetalleScrapModel remitoDetalleScrapModel = new RemitoDetalleScrapModel();
        
//        if(id.equalsIgnoreCase("-1")) {
//            //RemitoDetalleScrapModel remitoDetalleScrapModel = new RemitoDetalleScrapModel();
//            remitoDetalleScrapModel.setFecha(new Date());
//            remitoDetalleScrapModel.setIdUsuarioAlta(user.getId());
//        } else {
//            remitoDetalleScrapModel = remitoDetalleScrap.getByPk(Integer.valueOf(id));
//            if(remitoDetalleScrapModel == null) {
//                modelAndView.setViewName("error");
//                modelAndView.addObject("errorMessage", "Error: id de ingresarDeposito invalido.");
//                return modelAndView;                
//            } 
//        } 
        
           
        //int numeroEntero = Integer.parseInt(egresoScrapForm.getIdRemito());
        
        //remitoDetalleScrapModel.setIdRemito(numeroEntero);
        egresoScrapForm.setAction("add");
        
        remitoDetalleScrapModel.setIdOrdenDeProduccion(27);
        
        remitoDetalleScrapModel.setIdUsuarioAlta(1);
        
        remitoDetalleScrapModel.setCodigo("S30");
        
        remitoDetalleScrapModel.setIdRemito(74);
        
        remitoDetalleScrapModel.setCantidadUtilizadaRemito(200.0);
       
        
        //RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();  
        //RemitoDetalleModel remitoAmodificar = remitoDetalleService.getByPk(numeroEntero);
        
        RemitoService remitoService = new RemitoServiceImpl();  
        //RemitoModel remitoAmodificarNoDetalle = remitoService.getByPk(remitoAmodificar.getIdRemito());
        

        if(egresoScrapForm.getAction().equalsIgnoreCase("add") || egresoScrapForm.getAction().equalsIgnoreCase("edit")) {
            remitoDetalleScrap.save(remitoDetalleScrapModel);

        } else {
            if(egresoScrapForm.getAction().equalsIgnoreCase("remove")) {
                if(remitoDetalleScrapModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de ingresarDeposito invalido.");
                    return modelAndView;                                    
                }
                                            
                remitoDetalleScrap.delete(remitoDetalleScrapModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operacion invalida.");
                return modelAndView;                                
            }
        }
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        RemitoDetalleModel remito = remitoDetalleService.getByPk(76);
                        
        modelAndView.setViewName("redirect:/remitoDetalleScrap/76");
    

        return modelAndView; 
    }
    
   
    
    @ResponseBody
    @RequestMapping(value = "/depositoScrap/findScrap/{codigo}/{orden}", method = RequestMethod.GET)
    public List<ItemBean> getScrap(@PathVariable String codigo,@PathVariable String orden, HttpServletRequest req, ModelMap model) throws Exception {
        
        OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl(); 
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        
        List<OrdenDeProduccionBultoDto> ordenDeProduccionBultosDtos = new ArrayList<OrdenDeProduccionBultoDto>();

        TipoService tipoService = new TipoServiceImpl();
       
        List<ItemBean> result = new ArrayList<ItemBean>();
        if(codigo != null && !codigo.isEmpty()) {
            String tipo = codigo.substring(0,1);
           
            if(tipo.equalsIgnoreCase("S")){
                ItemBean bean = new ItemBean();        
                bean.setTipo("scrap");                
                OrdenDeProduccionScrapModel scrap = ordenDeProduccionScrapService.getByCode(codigo);
                
                OrdenDeProduccionScrapDto ordenDeProduccionScrapDto = new OrdenDeProduccionScrapDto();

                if (scrap != null && !scrap.getIdOrdenDeProduccion().equals(Integer.valueOf(orden))) {
                   bean.setId("-2");
                   bean.setTipo("-2");
                } else {
                if(scrap != null) {
                    OrdenDeProduccionScrapModel scrapCompleto = ordenDeProduccionScrapService.getByPk(scrap.getId());
                    bean.setCodigo(scrapCompleto.getCodigo());
                    if (scrapCompleto.getMaterialImpreso()){
                        bean.setMaterialImpreso("Si");
                    }else{
                        bean.setMaterialImpreso("No");
                    }
                    
                    if (scrapCompleto.getEsRecuperable()){
                        bean.setRecuperable("Si");
                    }else{
                        bean.setRecuperable("No");
                    }
                    
                    List<TipoModel> rubrosModel = tipoService.getByType("formatoScrap");
                    if(scrapCompleto.getIdFormato() != null) {
                    String rubro = "";
                    if(rubrosModel != null && !rubrosModel.isEmpty()){
                        for(TipoModel tipoModel :rubrosModel) {
                            if (scrapCompleto.getIdFormato().equals(tipoModel.getId())) {
                            rubro = tipoModel.getValor();
                        }

                        }
                    }                
                        bean.setFormato(rubro);
                    }
                    
                    List<TipoModel> tipoMaterialModel = tipoService.getByType("tipoMaterialScrap");
                    if(scrapCompleto.getIdTipoMaterial() != null) {
                    String tipoMaterial = "";
                    if(tipoMaterialModel != null && !tipoMaterialModel.isEmpty()){
                        for(TipoModel tipoModel :tipoMaterialModel) {
                            if (scrapCompleto.getIdTipoMaterial().equals(tipoModel.getId())) {
                            tipoMaterial = tipoModel.getValor();
                        }

                        }
                    }                
                        bean.setTipoMaterial(tipoMaterial);
                    }
                    
                    List<TipoModel> origenModel = tipoService.getByType("origenScrap");
                    if(scrapCompleto.getIdOrigen()!= null) {
                    String origen = "";
                    if(origenModel != null && !origenModel.isEmpty()){
                        for(TipoModel tipoModel :origenModel) {
                            if (scrapCompleto.getIdOrigen().equals(tipoModel.getId())) {
                            origen = tipoModel.getValor();
                        }

                        }
                    }                
                        bean.setOrigen(origen);
                    }
                    
                    List<TipoModel> motivoModel = tipoService.getByType("motivoScrap");
                    if(scrapCompleto.getIdMotivo()!= null) {
                    String motivo = "";
                    if(motivoModel != null && !motivoModel.isEmpty()){
                        for(TipoModel tipoModel :motivoModel) {
                            if (scrapCompleto.getIdMotivo().equals(tipoModel.getId())) {
                            motivo = tipoModel.getValor();
                        }

                        }
                    }                
                        bean.setMotivo(motivo);
                    }
                    
                    
                    bean.setPesoTotal(scrapCompleto.getPesoTotal());
               
                    bean.setId(scrapCompleto.getId().toString());
                    bean.setTipo("scrap");
            
                    ordenDeProduccionScrapDto.setPk(scrapCompleto.getId().toString());
                   
                    ordenDeProduccionScrapDto.setCodigo(scrapCompleto.getCodigo());
              
                } else {
                    bean.setId("-1");
                    bean.setTipo("-1");
                }
                }
                
                result.add(bean);
                
            } else {
                ItemBean bean = new ItemBean();        
                bean.setTipo("-1");
                bean.setId("-1");
                result.add(bean);
            }
        }
        
        return result;
    }
    
    private void setDatosRemito(TrazabilidadForm trazabilidadForm, RemitoModel remito, UserService userService) {
        trazabilidadForm.setCodigoRemito(remito.getId().toString());
        if(remito.getFechaAlta() != null) {
            trazabilidadForm.setFechaAltaRemito(remito.getFechaAlta().toString());
        }                
        if(remito.getEstado() != null) {
            trazabilidadForm.setEstadoRemito(remito.getEstado());
        }
        if(remito.getIdUsuarioAlta() != null) {
            UserModel user = userService.getUserById(remito.getIdUsuarioAlta());
            if(user != null) {
                trazabilidadForm.setUsuarioAltaRemito(user.getUsername());
            }        
        }
        if(remito.getTipoRemito() != null) {
            trazabilidadForm.setTipoRemito(remito.getTipoRemito());
        }
        if(remito.getIdTransporte() != null) {
            ProveedorService proveedorService = new ProveedorServiceImpl();
            ProveedorModel proveedor = proveedorService.getByPk(remito.getIdTransporte());
            if(proveedor != null) {
                trazabilidadForm.setTransporteRemito(proveedor.getRazonSocial());
            }
        }
        
    }
    
    private void setDatosHojaDeRuta(TrazabilidadForm trazabilidadForm, HojaDeRutaModel hojaDeRuta, UserService userService) {
        trazabilidadForm.setCodigoHojaDeRuta(hojaDeRuta.getId().toString());
        if(hojaDeRuta.getFecha() != null) {
            trazabilidadForm.setFechaAltaHojaDeRuta(hojaDeRuta.getFecha().toString().replace("00:00:00.0", ""));
        }                
        if(hojaDeRuta.getEstado() != null) {
            trazabilidadForm.setEstadoHojaDeRuta(hojaDeRuta.getEstado());
        }
        if(hojaDeRuta.getIdUsuarioAlta() != null) {
            UserModel user = userService.getUserById(hojaDeRuta.getIdUsuarioAlta());
            if(user != null) {
                trazabilidadForm.setUsuarioAltaHojaDeRuta(user.getUsername());
            }        
        }
        if(hojaDeRuta.getFechaCarga() != null) {
            trazabilidadForm.setFechaCarga(hojaDeRuta.getFechaCarga().toString().replace("00:00:00.0", "") + " " + hojaDeRuta.getHoraCarga());
        }                
        if(hojaDeRuta.getFechaSalida() != null) {
            trazabilidadForm.setFechaSalida(hojaDeRuta.getFechaSalida().toString().replace("00:00:00.0", "") + " " + hojaDeRuta.getHoraSalida());
        }                
        if(hojaDeRuta.getIdChofer() != null) {
            ContactoService contactoService = new ContactoServiceImpl();
            ContactoModel chofer = contactoService.getByPk(hojaDeRuta.getIdChofer());
            if(chofer != null) {
                trazabilidadForm.setChofer(chofer.getNombre());
            }
        }
        
    }
}
