/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.ClienteDomicilioBean;
import com.empresa.thyssenplastic.controller.beans.ContactoBean;
import com.empresa.thyssenplastic.controller.form.RemitoDetalleForm;
import com.empresa.thyssenplastic.controller.form.RemitoForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.RemitoDetalleDto;
import com.empresa.thyssenplastic.dto.RemitoDto;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteContactoModel;
import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.model.LocalidadModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteContactoService;
import com.empresa.thyssenplastic.service.ClienteDomicilioService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.service.DomicilioService;
import com.empresa.thyssenplastic.service.LocalidadService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.ProveedorContactoService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.RemitoDetalleService;
import com.empresa.thyssenplastic.service.RemitoService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteDomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.DomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.LocalidadServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.RemitoServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gusta
 */
@Controller
public class RemitoController {
    

    @RequestMapping(value = "/remito", method = RequestMethod.GET)
    public String getHomeRemito(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        String rol = "";    
        String operacion = "";        
        String displayActionButton = "block";
        
        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);
        
        RemitoForm remitoForm = new RemitoForm();
        remitoForm.setPk("-1");
        remitoForm.setAction("add");
        remitoForm.setEstado("Nuevo");
        remitoForm.setExistingDomicilio("-1");
        remitoForm.setExistingContacto("-1");
        

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        remitoForm.setFechaAlta(fecha);
        remitoForm.setFechaRemito(fecha);
        remitoForm.setLocalidad("");
        remitoForm.setProvincia("");
        
        if(user.getRol() == Utils.ROL_OFICINA) {
            //remitoForm.setEstadoLabel("Nuevo");            
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            //remitoForm.setEstadoLabel("Abierto");
            operacion = "recepcion";
            displayActionButton = "none";            
            rol = "deposito";
        }
                
        remitoForm.setOperacion(operacion);
        
        model.addAttribute("remitoForm", remitoForm);  
        model.addAttribute("titleRemito", "Nuevo Remito");  
        model.addAttribute("buttonLabel", "Guardar");
        
        //RemitoItemService remitoItemService = new RemitoItemServiceImpl();   
        RemitoService remitoService = new RemitoServiceImpl();   
        List<RemitoModel> remitos = remitoService.getAll();

        ClienteService clienteService = new ClienteServiceImpl();   
        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        Integer idRubro = -1;
        TipoService tipoService = new TipoServiceImpl();   
        List<TipoModel> tipos = tipoService.getByType("rubroProveedor");
        if(tipos != null && !tipos.isEmpty()) {
            for(TipoModel tipo : tipos) {
                if(tipo.getValor().equalsIgnoreCase("Transporte")) {
                    idRubro = tipo.getId();
                    break;
                }
            }
        }              
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> transportes = new LinkedHashMap<String,String>();
        List<ProveedorModel> transportesModel = proveedorService.getAllByRubro(idRubro);

        if(transportesModel != null && !transportesModel.isEmpty()){
            for(ProveedorModel proveedorModel :transportesModel) {
                transportes.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }

        List<RemitoDto> remitosDtos = new ArrayList<RemitoDto>();
        for(RemitoModel remito: remitos) {
            
            if(rol.equalsIgnoreCase("oficina") || (rol.equalsIgnoreCase("deposito") && remito.getEstado().equalsIgnoreCase("Abierto"))) {
                RemitoDto remitoDto = new RemitoDto();
                remitoDto.setPk(remito.getId().toString());
                remitoDto.setFechaAlta(remito.getFechaAlta().toString().replace(" 00:00:00.0", ""));
                if(remito.getFechaRemito() != null) {
                    remitoDto.setFechaRemito(remito.getFechaRemito().toString().replace(" 00:00:00.0", ""));
                }
                if(remito.getReferenciaAdministrativa() != null) {
                    remitoDto.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
                }            
                remitoDto.setEstado(remito.getEstado());
                remitoDto.setCliente(clientes.get(remito.getIdCliente().toString()));
                remitoDto.setTipoRemito(remito.getTipoRemito());
                remitoDto.setCantidadTotal(remito.getCantidadTotal());
                remitoDto.setCantidadTotalBaja(remito.getCantidadTotalBaja());

                RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();
                List<RemitoDetalleModel> remitosDetalle = remitoDetalleService.getAllByRemito(remito.getId());
                if(remitosDetalle == null || remitosDetalle.isEmpty()) {
                    remitoDto.setCanDelete("true");
                } else {
                    remitoDto.setCanDelete("false");
                }
                remitoDto.setLocalidad("hola");
                remitoDto.setProvincia("hola");
                //Boolean itemsRecepcionados = true;
                //if(remitosDetalle != null && !remitosDetalle.isEmpty()) {                    
                  //  for(RemitoDetalleModel remitoDetalle: remitosDetalle) {
                    //    if(!remitoDetalle.getIngresoDeposito()) {
                      //      itemsRecepcionados = false;
                      //  }
                    //}
                //} else {
                  //  itemsRecepcionados = false;
                //}
                //if(itemsRecepcionados) {
                    //remitoDto.setItemsRecepcionados("true");
                //} else {
                  //  remitoDto.setItemsRecepcionados("false");
                //}

                remitosDtos.add(remitoDto);
            }            
            
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("transporteList", transportes);        
        model.addAttribute("remitos", remitosDtos);        
                
        return "/remito/remito";
    }
    
    @RequestMapping(value = "/remito/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveRemito(@ModelAttribute("remitoForm")RemitoForm remitoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        ModelAndView modelAndView = new ModelAndView();
        
        if (result.hasErrors()) {            
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 01");
            return modelAndView;
        }
        
        if(!Utils.isAutenticated(req)) {            
            modelAndView.setViewName("/login/login");    
            model.addAttribute("userForm", new UserForm());         
            return modelAndView;
        }
        
        if(remitoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 02");
            return modelAndView;            
        }
        
        String operacion = remitoForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta") && !operacion.equalsIgnoreCase("edit"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: operación inválida.");
            return modelAndView;                        
        }        
        if(remitoForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 03");
            return modelAndView;                        
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {        
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: no puede realizar esta operación.");
            return modelAndView;                                    
        }
        
        RemitoService remitoService = new RemitoServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = remitoForm.getPk();
            
        RemitoModel remitoModel = null;
        if(id.equalsIgnoreCase("-1")) {
            remitoModel = new RemitoModel();
            remitoModel.setFechaAlta(new Date());
            remitoModel.setEstado("Nuevo");
            remitoModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));            
            remitoModel.setEstaEnHojaDeRuta(Boolean.FALSE);
        } else {
            remitoModel = remitoService.getByPk(Integer.valueOf(id));
            if(remitoModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de remito inválido.");
                return modelAndView;                
            } 
            if(!remitoModel.getEstado().equalsIgnoreCase("Nuevo") && user.getRol() != Utils.ROL_OFICINA) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: no es posible editar una orden en estado "+remitoModel.getEstado());
                return modelAndView;                        
            }            
        }        
        
        if(operacion.equalsIgnoreCase("alta")) {
            if(remitoForm.getFechaRemito() != null && !remitoForm.getFechaRemito().trim().equals("")) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(remitoForm.getFechaRemito());
                remitoModel.setFechaRemito(fecha);
            } else {
                remitoModel.setFechaRemito(null);
            }                           
            if(remitoForm.getTipoRemito() != null && !remitoForm.getTipoRemito().isEmpty()) {
                remitoModel.setTipoRemito(remitoForm.getTipoRemito());
            } else {
                remitoModel.setTipoRemito(null);
            }            
            if(remitoForm.getIdCliente() != null && !remitoForm.getIdCliente().isEmpty() && !remitoForm.getIdCliente().equalsIgnoreCase("-1")) {
                remitoModel.setIdCliente(Integer.valueOf(remitoForm.getIdCliente()));
            } else {
                remitoModel.setIdCliente(null);
            }
            if(remitoForm.getIdClienteDomicilio() != null && !remitoForm.getIdClienteDomicilio().isEmpty() && !remitoForm.getIdClienteDomicilio().equalsIgnoreCase("-1")) {
                remitoModel.setIdDomicilio(Integer.valueOf(remitoForm.getIdClienteDomicilio()));
            } else {
                remitoModel.setIdDomicilio(null);
            }            
            if(remitoForm.getIdContacto() != null && !remitoForm.getIdContacto().isEmpty() && !remitoForm.getIdContacto().equalsIgnoreCase("-1")) {
                remitoModel.setIdContacto(Integer.valueOf(remitoForm.getIdContacto()));
            } else {
                remitoModel.setIdContacto(null);
            }                        
            if(remitoForm.getIdTransporte() != null && !remitoForm.getIdTransporte().isEmpty() && !remitoForm.getIdTransporte().equalsIgnoreCase("-1")) {
                remitoModel.setIdTransporte(Integer.valueOf(remitoForm.getIdTransporte()));
            } else {
                remitoModel.setIdTransporte(null);
            }                        
            if(remitoForm.getReferenciaAdministrativa() != null && !remitoForm.getReferenciaAdministrativa().isEmpty()) {
                remitoModel.setReferenciaAdministrativa(remitoForm.getReferenciaAdministrativa());
            } else {
                remitoModel.setReferenciaAdministrativa(null);
            }            
        }
        if(remitoForm.getIdChofer() != null && !remitoForm.getIdChofer().isEmpty() && !remitoForm.getIdChofer().equalsIgnoreCase("-1")) {
                remitoModel.setIdChofer(Integer.valueOf(remitoForm.getIdChofer()));
            } else {
                remitoModel.setIdChofer(null);
            }
         if(remitoForm.getObservaciones() != null && !remitoForm.getObservaciones().isEmpty()) {
                remitoModel.setObservaciones(remitoForm.getObservaciones());
            } else {
                remitoModel.setObservaciones(null);
            }  
        remitoModel.setCantidadTotal(0);
        remitoModel.setCantidadTotalBaja(0);

        
        if(remitoForm.getAction().equalsIgnoreCase("add") || remitoForm.getAction().equalsIgnoreCase("edit")) {
            remitoService.save(remitoModel);
            
        } else {
            if(remitoForm.getAction().equalsIgnoreCase("remove")) {
                if(remitoModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de remito inválido.");
                    return modelAndView;                                    
                }
                
                remitoService.delete(remitoModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        //modelAndView.setViewName("redirect:/remito");
        modelAndView.setViewName("redirect:/remitoDetalle/"+remitoModel.getId().toString());

        return modelAndView; 
    }

    @RequestMapping(value = "/remito/edit/{remitopk}", method = RequestMethod.GET)
    public String editRemito(@PathVariable String remitopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitopk == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitopk));
        if(remito == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No ha sido encontrado.");         
            return "/error";    
        }

        if(remito.getIdCliente() == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No posee cliente.");         
            return "/error";    
        }
        
        if(!remito.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        RemitoForm remitoForm = new RemitoForm();
        remitoForm.setPk(remito.getId().toString());
        if(remito.getFechaAlta() != null) {
            remitoForm.setFechaAlta(remito.getFechaAlta().toString().replace(".0", ""));
        }
        if(remito.getFechaRemito() != null) {
            remitoForm.setFechaRemito(remito.getFechaRemito().toString().replace(" 00:00:00.0", ""));
        }        
        if(remito.getTipoRemito() != null) {
            remitoForm.setTipoRemito(remito.getTipoRemito());
        }                
        if(remito.getIdCliente() != null) {
            remitoForm.setIdCliente(remito.getIdCliente().toString());
        }        
        if(remito.getIdDomicilio() != null) {
            remitoForm.setIdClienteDomicilio(remito.getIdDomicilio().toString());
            remitoForm.setExistingDomicilio(remito.getIdDomicilio().toString());
        }        
        //if(remito.getIdContacto() != null) {
            //remitoForm.setIdContacto(remito.getIdContacto().toString());
            //remitoForm.setExistingContacto(remito.getIdContacto().toString());
        //}                
        if(remito.getIdTransporte() != null) {
            remitoForm.setIdTransporte(remito.getIdTransporte().toString());
        }   
        if(remito.getIdChofer() != null) {
            remitoForm.setIdChofer(remito.getIdChofer().toString());
            //remitoForm.setIdContacto(remito.getIdChofer().toString());
            //ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
            //ProveedorContactoModel proveedorContacto = proveedorContactoService.getByPkContacto(hojaDeRuta.getIdChofer());
            //if(proveedorContacto != null) {
                //remitoForm.setIdProveedor(proveedorContacto.getIdProveedor().toString());
            //}
        }   
        if(remito.getReferenciaAdministrativa() != null && !remito.getReferenciaAdministrativa().isEmpty()) {
            remitoForm.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
        }                
        if(remito.getEstado() != null && !remito.getEstado().isEmpty()) {
            remitoForm.setEstado(remito.getEstado());
        }
        if(remito.getObservaciones() != null && !remito.getObservaciones().isEmpty()) {
            remitoForm.setObservaciones(remito.getObservaciones());
        }  
 
        remitoForm.setOperacion(operacion);        
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(remito.getIdCliente());
        
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: No se encuentra el cliente.");         
            return "/error";                                                                
        }
        
        

        
        remitoForm.setAction("edit");
        model.addAttribute("remitoForm", remitoForm);  
        model.addAttribute("titleRemito", "Editar Remito");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("remitoName", "Remito: " + remito.getId() + " - Cliente :" + cliente.getRazonSocial());        
        
        List<RemitoModel> remitos = remitoService.getAll();

        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        Integer idRubro = -1;
        TipoService tipoService = new TipoServiceImpl();   
        List<TipoModel> tipos = tipoService.getByType("rubroProveedor");
        if(tipos != null && !tipos.isEmpty()) {
            for(TipoModel tipo : tipos) {
                if(tipo.getValor().equalsIgnoreCase("Transporte")) {
                    idRubro = tipo.getId();
                }
            }
        }                
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> transportes = new LinkedHashMap<String,String>();
        List<ProveedorModel> transportesModel = proveedorService.getAllByRubro(idRubro);

        if(transportesModel != null && !transportesModel.isEmpty()){
            for(ProveedorModel proveedorModel :transportesModel) {
                transportes.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }

        List<RemitoDto> remitosDtos = new ArrayList<RemitoDto>();
        for(RemitoModel remitoModel: remitos) {
            RemitoDto remitoDto = new RemitoDto();
            remitoDto.setPk(remitoModel.getId().toString());
            remitoDto.setFechaAlta(remitoModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(remitoModel.getFechaRemito() != null) {
                remitoDto.setFechaRemito(remitoModel.getFechaRemito().toString().replace(" 00:00:00.0", ""));
            }
            if(remitoModel.getReferenciaAdministrativa() != null) {
                remitoDto.setReferenciaAdministrativa(remitoModel.getReferenciaAdministrativa());
            }            
            remitoDto.setTipoRemito(remito.getTipoRemito());
            remitoDto.setEstado(remitoModel.getEstado());
            remitoDto.setCliente(clientes.get(remitoModel.getIdCliente().toString()));
            
            remitosDtos.add(remitoDto);
            
            
        }

        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("transporteList", transportes);        
        model.addAttribute("remitos", remitosDtos);
                                                        
        return "/remito/remito";        
    }
    
    @RequestMapping(value = "/remito/remove/{remitopk}", method = RequestMethod.GET)
    public String removeRemito(@PathVariable String remitopk, HttpServletRequest req, ModelMap model) throws Exception {
    
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitopk == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitopk));
        if(remito == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No ha sido encontrado.");         
            return "/error";    
        }

        if(remito.getIdCliente() == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No posee cliente.");         
            return "/error";    
        }
        
        if(!remito.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        RemitoForm remitoForm = new RemitoForm();
        remitoForm.setPk(remito.getId().toString());
        if(remito.getFechaAlta() != null) {
            remitoForm.setFechaAlta(remito.getFechaAlta().toString().replace(".0", ""));
        }
        if(remito.getFechaRemito() != null) {
            remitoForm.setFechaRemito(remito.getFechaRemito().toString().replace(" 00:00:00.0", ""));
        }        
        if(remito.getTipoRemito() != null) {
            remitoForm.setTipoRemito(remito.getTipoRemito());
        }                
        if(remito.getIdCliente() != null) {
            remitoForm.setIdCliente(remito.getIdCliente().toString());
        }        
        if(remito.getIdDomicilio() != null) {
            remitoForm.setIdClienteDomicilio(remito.getIdDomicilio().toString());
            remitoForm.setExistingDomicilio(remito.getIdDomicilio().toString());
        }        
        if(remito.getIdContacto() != null) {
            remitoForm.setIdContacto(remito.getIdContacto().toString());
            remitoForm.setExistingContacto(remito.getIdContacto().toString());
        }                        
        if(remito.getIdTransporte() != null) {
            remitoForm.setIdTransporte(remito.getIdTransporte().toString());
        }                
        if(remito.getReferenciaAdministrativa() != null && !remito.getReferenciaAdministrativa().isEmpty()) {
            remitoForm.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
        }                
        if(remito.getEstado() != null && !remito.getEstado().isEmpty()) {
            remitoForm.setEstado(remito.getEstado());
        }
        if(remito.getObservaciones() != null && !remito.getObservaciones().isEmpty()) {
            remitoForm.setObservaciones(remito.getObservaciones());
        }         
        
        remitoForm.setOperacion(operacion);
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(remito.getIdCliente());
        
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: No se encuentra el cliente.");         
            return "/error";                                                                
        }

        
        remitoForm.setAction("remove");
        model.addAttribute("remitoForm", remitoForm);  
        model.addAttribute("titleRemito", "Eliminar Remito");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("remitoName", "Remito: " + remito.getId() + " - Cliente :" + cliente.getRazonSocial());        
        
        List<RemitoModel> remitos = remitoService.getAll();

        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        Integer idRubro = -1;
        TipoService tipoService = new TipoServiceImpl();   
        List<TipoModel> tipos = tipoService.getByType("rubroProveedor");
        if(tipos != null && !tipos.isEmpty()) {
            for(TipoModel tipo : tipos) {
                if(tipo.getValor().equalsIgnoreCase("Transporte")) {
                    idRubro = tipo.getId();
                }
            }
        }                
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> transportes = new LinkedHashMap<String,String>();
        List<ProveedorModel> transportesModel = proveedorService.getAllByRubro(idRubro);

        if(transportesModel != null && !transportesModel.isEmpty()){
            for(ProveedorModel proveedorModel :transportesModel) {
                transportes.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }

        List<RemitoDto> remitosDtos = new ArrayList<RemitoDto>();
        for(RemitoModel remitoModel: remitos) {
            RemitoDto remitoDto = new RemitoDto();
            remitoDto.setPk(remitoModel.getId().toString());
            remitoDto.setFechaAlta(remitoModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(remitoModel.getFechaRemito() != null) {
                remitoDto.setFechaRemito(remitoModel.getFechaRemito().toString().replace(" 00:00:00.0", ""));
            }
            if(remitoModel.getReferenciaAdministrativa() != null) {
                remitoDto.setReferenciaAdministrativa(remitoModel.getReferenciaAdministrativa());
            }            
            remitoDto.setTipoRemito(remito.getTipoRemito());
            remitoDto.setEstado(remitoModel.getEstado());
            remitoDto.setCliente(clientes.get(remitoModel.getIdCliente().toString()));
            
            remitosDtos.add(remitoDto);
            
            
        }

        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("transporteList", transportes);        
        model.addAttribute("remitos", remitosDtos);
                                                        
        return "/remito/remito"; 
        
    }    
    
    @RequestMapping(value = "/remito/view/{remitopk}", method = RequestMethod.GET)
    public String viewRemito(@PathVariable String remitopk, HttpServletRequest req, ModelMap model) throws Exception {
                
    
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitopk == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido");         
            return "/error";                
        }
        
        String operacion = "view";        
        String displayActionButton = "none";
        
        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitopk));
        if(remito == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No ha sido encontrado.");         
            return "/error";    
        }

        if(remito.getIdCliente() == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No posee cliente.");         
            return "/error";    
        }
                
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        RemitoForm remitoForm = new RemitoForm();
        remitoForm.setPk(remito.getId().toString());
        if(remito.getFechaAlta() != null) {
            remitoForm.setFechaAlta(remito.getFechaAlta().toString().replace(".0", ""));
        }
        if(remito.getFechaRemito() != null) {
            remitoForm.setFechaRemito(remito.getFechaRemito().toString().replace(" 00:00:00.0", ""));
        }        
        if(remito.getTipoRemito() != null) {
            remitoForm.setTipoRemito(remito.getTipoRemito());
        }                
        if(remito.getIdCliente() != null) {
            remitoForm.setIdCliente(remito.getIdCliente().toString());
        }        
        if(remito.getIdDomicilio() != null) {
            remitoForm.setIdClienteDomicilio(remito.getIdDomicilio().toString());
            remitoForm.setExistingDomicilio(remito.getIdDomicilio().toString());
        }        
        if(remito.getIdContacto() != null) {
            remitoForm.setIdContacto(remito.getIdContacto().toString());
            remitoForm.setExistingContacto(remito.getIdContacto().toString());
        }                        
        if(remito.getIdTransporte() != null) {
            remitoForm.setIdTransporte(remito.getIdTransporte().toString());
        }                
        if(remito.getReferenciaAdministrativa() != null && !remito.getReferenciaAdministrativa().isEmpty()) {
            remitoForm.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
        }                
        if(remito.getEstado() != null && !remito.getEstado().isEmpty()) {
            remitoForm.setEstado(remito.getEstado());
        }        
        
        remitoForm.setOperacion(operacion);
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(remito.getIdCliente());
        
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: No se encuentra el cliente.");         
            return "/error";                                                                
        }

        
        remitoForm.setAction("view");
        model.addAttribute("remitoForm", remitoForm);  
        model.addAttribute("titleRemito", "Ver Remito");
        model.addAttribute("buttonLabel", "Ver");
        model.addAttribute("remitoName", "Remito: " + remito.getId() + " - Cliente :" + cliente.getRazonSocial());        
        
        List<RemitoModel> remitos = remitoService.getAll();

        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        Integer idRubro = -1;
        TipoService tipoService = new TipoServiceImpl();   
        List<TipoModel> tipos = tipoService.getByType("rubroProveedor");
        if(tipos != null && !tipos.isEmpty()) {
            for(TipoModel tipo : tipos) {
                if(tipo.getValor().equalsIgnoreCase("Transporte")) {
                    idRubro = tipo.getId();
                }
            }
        }                
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> transportes = new LinkedHashMap<String,String>();
        List<ProveedorModel> transportesModel = proveedorService.getAllByRubro(idRubro);

        if(transportesModel != null && !transportesModel.isEmpty()){
            for(ProveedorModel proveedorModel :transportesModel) {
                transportes.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }

        List<RemitoDto> remitosDtos = new ArrayList<RemitoDto>();
        for(RemitoModel remitoModel: remitos) {
            RemitoDto remitoDto = new RemitoDto();
            remitoDto.setPk(remitoModel.getId().toString());
            remitoDto.setFechaAlta(remitoModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(remitoModel.getFechaRemito() != null) {
                remitoDto.setFechaRemito(remitoModel.getFechaRemito().toString().replace(" 00:00:00.0", ""));
            }
            if(remitoModel.getReferenciaAdministrativa() != null) {
                remitoDto.setReferenciaAdministrativa(remitoModel.getReferenciaAdministrativa());
            }            
            remitoDto.setTipoRemito(remito.getTipoRemito());
            remitoDto.setEstado(remitoModel.getEstado());
            remitoDto.setCliente(clientes.get(remitoModel.getIdCliente().toString()));
            
            remitosDtos.add(remitoDto);
            
            
        }

        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("transporteList", transportes);        
        model.addAttribute("remitos", remitosDtos);
                                                        
        return "/remito/remito"; 
            
    }    

    @ResponseBody
    @RequestMapping(value = "/remito/getDomcilio/{idCliente}", method = RequestMethod.GET)
    public List<ClienteDomicilioBean> getDomicilio(@PathVariable String idCliente, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ClienteDomicilioBean> result = new ArrayList<ClienteDomicilioBean>();
        
        List<ClienteDomicilioBean> resultLegal = new ArrayList<ClienteDomicilioBean>();
        List<ClienteDomicilioBean> resultComun = new ArrayList<ClienteDomicilioBean>();
        
        if(idCliente != null && !idCliente.isEmpty()) {
            DomicilioService domicilioService = new DomicilioServiceImpl();
            TipoService tipoService = new TipoServiceImpl();
            LocalidadService localidadService = new LocalidadServiceImpl();
            ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
            List<ClienteDomicilioModel> domiciliosCliente = clienteDomicilioService.getByPkCliente(Integer.valueOf(idCliente));
            if(!domiciliosCliente.isEmpty()) {
                for(ClienteDomicilioModel domicilioCliente: domiciliosCliente) {
                    
                    DomicilioModel domicilio = domicilioService.getByPk(domicilioCliente.getIdDomicilio());
                    if(domicilio != null) {
                        
                        LocalidadModel localidad = localidadService.getByPk(domicilio.getIdLocalidad());
                        TipoModel tipoDomicilio = tipoService.getByPk(domicilio.getIdTipo());  
                        String loc = "SN";
                        String prov = "SN";
                        
                        TipoService tipoServicee = new TipoServiceImpl();
                        TipoModel localidaad = null;
                        TipoModel provincia = null;
                        
                        if (domicilio.getIdLocalidad() != null){
                            localidaad = tipoServicee.getByPk(domicilio.getIdLocalidad());
                         }
                        
                        if (domicilio.getIdProvincia() != null){
                            provincia = tipoServicee.getByPk(domicilio.getIdProvincia());
                         }
                        
                       if (localidaad != null){
                           loc =localidaad.getValor(); 
                       }
                       
                       if (provincia != null){
                           prov = provincia.getValor(); 
                       }

                        ClienteDomicilioBean bean = new ClienteDomicilioBean();
                        bean.setId(domicilio.getId().toString());
                        //bean.setValor(domicilio.getUbicacion()+ loc + domicilio.getIdProvincia());
                        bean.setValor(domicilio.getUbicacion() + " (" + loc + ", " + prov + ")");
                        //if(localidad != null) {
                          //  bean.setValor(domicilio.getUbicacion() + " (" + localidad.getNombre() + ")");
                        //} else {
                           // bean.setValor(domicilio.getUbicacion()+ loc + domicilio.getIdProvincia());
                        //}

                        if(tipoDomicilio.getValor().equalsIgnoreCase("Legal")) {
                            resultLegal.add(bean);    
                        } else {
                            resultComun.add(bean);    
                        } 
                    }
                }
                result.addAll(resultComun);
                result.addAll(0, resultLegal);
            }
        }
        
        return result;
    }
    
    @ResponseBody
    @RequestMapping(value = "/remito/getContacto/{idCliente}", method = RequestMethod.GET)
    public List<ContactoBean> getContacto(@PathVariable String idCliente, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ContactoBean> result = new ArrayList<ContactoBean>();
        
        
        if(idCliente != null && !idCliente.isEmpty()) {
            ContactoService contactoService = new ContactoServiceImpl();
            ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
            List<ClienteContactoModel> contactosCliente = clienteContactoService.getByPkCliente(Integer.valueOf(idCliente));
            if(!contactosCliente.isEmpty()) {
                for(ClienteContactoModel contactoCliente: contactosCliente) {
                    
                    ContactoModel contacto = contactoService.getByPk(contactoCliente.getIdContacto());
                    if(contacto != null) {
                                               
                        ContactoBean bean = new ContactoBean();
                        bean.setId(contacto.getId().toString());
                        bean.setNombre(contacto.getNombre());
                        bean.setTelefono(contacto.getTelefono());
                        
                        result.add(bean);
                    }
                }
            }
        }
        
        return result;
    }
    
    /*
    @RequestMapping(value = "/remito/setStatusOpenOrdenCompra/{remitoPk}", method = RequestMethod.GET)
    public String setStatusOpenOrdenCompra(@PathVariable String remitoPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoPk == null || remitoPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: Remito inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        RemitoService remitoService = new RemitoServiceImpl();        
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoPk));

        if(remito == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!remito.getEstado().equalsIgnoreCase("Nuevo")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+remito.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        RemitoItemService remitoItemService = new RemitoItemServiceImpl();        
        List<RemitoItemModel> items = remitoItemService.getAllByRemito(remito.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        remito.setEstado("Abierto");
        remitoService.save(remito);
        
        return "redirect:/remito";                         
        
    }       
    
    @RequestMapping(value = "/remito/setStatusCloseOrdenCompra/{remitoPk}", method = RequestMethod.GET)
    public String setStatusCoseOrdenCompra(@PathVariable String remitoPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoPk == null || remitoPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: Remito inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        RemitoService remitoService = new RemitoServiceImpl();        
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoPk));

        if(remito == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!remito.getEstado().equalsIgnoreCase("Completado")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+remito.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        RemitoItemService remitoItemService = new RemitoItemServiceImpl();        
        List<RemitoItemModel> items = remitoItemService.getAllByRemito(remito.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        Date today = new Date();
        
        remito.setFechaCierreOrden(today);
        remito.setIdUsuarioCierre(user.getId());
        remito.setEstado("Cerrado");
        remitoService.save(remito);
        
        return "redirect:/remito";                         
        
    }    
    
    @RequestMapping(value = "/remito/completarOrden/{remitopk}", method = RequestMethod.GET)
    public String completarOrdenObservacionesRemito(@PathVariable String remitopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitopk == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido");         
            return "/error";                
        }
        
        String operacion = "completar";        
        String displayActionButton = "block";
        
        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitopk));
        if(remito == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!remito.getEstado().equalsIgnoreCase("Abierto")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        RemitoForm remitoForm = new RemitoForm();
        remitoForm.setPk(remito.getId().toString());
        if(remito.getFechaAlta() != null) {
            remitoForm.setFechaAlta(remito.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(remito.getIdProveedor() != null) {
            remitoForm.setIdProveedor(remito.getIdProveedor().toString());
        }        
        if(remito.getObservaciones() != null && !remito.getObservaciones().isEmpty()) {
            remitoForm.setObservaciones(remito.getObservaciones());
        }        
        if(remito.getReferenciaAdministrativa() != null && !remito.getReferenciaAdministrativa().isEmpty()) {
            remitoForm.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
        }                        
        if(remito.getEstado() != null && !remito.getEstado().isEmpty()) {
            remitoForm.setEstado(remito.getEstado());
        }        
        
        remitoForm.setOperacion(operacion);
        remitoForm.setEditObservaciones("true");
                
        remitoForm.setAction("edit");
        model.addAttribute("remitoForm", remitoForm);  
        model.addAttribute("titleRemito", "Completar Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("remitoName", remito.getId() + " - " + remito.getIdProveedor());        
        
        List<RemitoModel> remitos = remitoService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<RemitoDto> remitosDtos = new ArrayList<RemitoDto>();
        for(RemitoModel remitoModel: remitos) {
            RemitoDto remitoDto = new RemitoDto();
            remitoDto.setPk(remitoModel.getId().toString());
            remitoDto.setFechaAlta(remitoModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(remito.getFechaEntrega() != null) {
                remitoDto.setFechaEntrega(remito.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
            }
            if(remito.getReferenciaAdministrativa() != null) {
                remitoDto.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
            }                        
            remitoDto.setEstado(remitoModel.getEstado());
            remitoDto.setProveedor(proveedores.get(remitoModel.getIdProveedor().toString()));
            
            remitosDtos.add(remitoDto);
        }
                               
        model.addAttribute("displayUser", "none");
                
        model.addAttribute("action", "editObservaciones");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("remitos", remitosDtos);        
                                                        
        return "/ordendecompra/ordendecompra";        
    }    
    */
    
    @RequestMapping(value = "/remito/setStatusOpenRemito/{remitoPk}", method = RequestMethod.GET)
    public String setStatusOpenRemito(@PathVariable String remitoPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoPk == null || remitoPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: Remito inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        RemitoService remitoService = new RemitoServiceImpl();        
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoPk));

        if(remito == null) {            
            model.addAttribute("errorMessage", "Error:remito no encontrada");
            return "/error";
        }
        
        if(!remito.getEstado().equalsIgnoreCase("Nuevo")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto con estado "+remito.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();        
        List<RemitoDetalleModel> items = remitoDetalleService.getAllByRemito(remito.getId());
        
//        if(items == null || items.isEmpty()) {            
//            model.addAttribute("errorMessage", "Error: no es posible pasar un remito a abierta cuando no tiene items");
//            return "/error";              
//        }
        
        int totalCantidad = 0;

        for (RemitoDetalleModel item : items) {
            totalCantidad += item.getCantidad();
        }
        
        remito.setCantidadTotal(totalCantidad);
        
        
        remito.setEstado("Abierto");
        remito.setFechaAbierto(new Date());
        remito.setIdUsuarioAbierto(user.getId());        
        
        remitoService.save(remito);
        
        return "redirect:/remito";                         
        
    }        
    
    @RequestMapping(value = "/remito/setStatusCompletedRemito/{remitoPk}", method = RequestMethod.GET)
    public String setStatusCompletedRemito(@PathVariable String remitoPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoPk == null || remitoPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: Remito inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        RemitoService remitoService = new RemitoServiceImpl();        
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoPk));

        if(remito == null) {            
            model.addAttribute("errorMessage", "Error:remito no encontrada");
            return "/error";
        }
        
        if(!remito.getEstado().equalsIgnoreCase("Abierto")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado completada con estado "+remito.getEstado());
            return "/error";
        }            
                
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();        
        List<RemitoDetalleModel> items = remitoDetalleService.getAllByRemito(remito.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar un remito a completada cuando no tiene items");
            return "/error";              
        }
        
        Boolean allItemsRecebidos = true;
        if(items != null && !items.isEmpty()) {            
            for(RemitoDetalleModel item :items) {
                if(!item.getIngresoDeposito()) {
                    allItemsRecebidos = false;
                }
            }
        }
        if(!allItemsRecebidos) {
            model.addAttribute("errorMessage", "Error: no es posible pasar un remito a completada cuando tiene items sin recibir en el depósito");
            return "/error";                          
        }
        
        remito.setEstado("Completado");
        remito.setFechaCompletado(new Date());
        remito.setIdUsuarioCompletado(user.getId());        
        remitoService.save(remito);
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        ArticuloService articuloService = new ArticuloServiceImpl();
        
        for(RemitoDetalleModel item :items) {
//            if(item.getIdBulto() != null) {
//                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdBulto());
//                if(bulto != null) {
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        ArticuloModel articulo =  articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null && ordenDeProduccion.getCantidadAProducir() != null) {
//                            Integer actualStock = articulo.getStock();
//                            Double actualStockPeso = articulo.getStockPeso();
//                            articulo.setStock(actualStock - item.getCantidad() );
//                            articulo.setStockPeso(actualStockPeso - item.getCantidad());
//                            
//                            articuloService.save(articulo);
//                        }                        
//                    }
//                }
//            }
//            if(item.getIdPallet() != null) {
//                OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(item.getIdPallet());
//                if(pallet != null) {
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        ArticuloModel articulo =  articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null && ordenDeProduccion.getCantidadAProducir() != null) {
//                            Integer actualStock = articulo.getStock();
//                            Double actualStockPeso = articulo.getStockPeso();
//                            articulo.setStock(actualStock - item.getCantidad() );
//                            articulo.setStockPeso(actualStockPeso - item.getCantidad());
//                            
//                            articuloService.save(articulo);
//                        }                        
//                    }
//                }
//            }            
        }
                
        return "redirect:/remito";                         
        
    }            
    
    @RequestMapping(value = "/remito/setStatusCloseRemito/{remitoPk}", method = RequestMethod.GET)
    public String setStatusCloseRemito(@PathVariable String remitoPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoPk == null || remitoPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: Remito inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        RemitoService remitoService = new RemitoServiceImpl();        
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoPk));

        if(remito == null) {            
            model.addAttribute("errorMessage", "Error:remito no encontrada");
            return "/error";
        }
        
//        if(!remito.getEstado().equalsIgnoreCase("Completado")) {            
//            model.addAttribute("errorMessage", "Error: no es posible cerrar el remito con estado "+remito.getEstado());
//            return "/error";
//        }            
                
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();        
        List<RemitoDetalleModel> items = remitoDetalleService.getAllByRemito(remito.getId());
        
//        if(items == null || items.isEmpty()) {            
//            model.addAttribute("errorMessage", "Error: no es posible cerrar un remito cuando no tiene items");
//            return "/error";              
//        }
        
        Boolean allItemsRecebidos = true;
//        if(items != null && !items.isEmpty()) {            
//            for(RemitoDetalleModel item :items) {
//                if(!item.getIngresoDeposito()) {
//                    allItemsRecebidos = false;
//                }
//            }
//        }
        if(!allItemsRecebidos) {
            model.addAttribute("errorMessage", "Error: no es posible cerrar un remito cuando tiene items sin recibir en el depósito");
            return "/error";                          
        }
        
        remito.setEstado("Cerrado");
        remito.setFechaCierre(new Date());
        remito.setIdUsuarioCierre(user.getId());
        remitoService.save(remito);
        
        return "redirect:/remito";                         
        
    }
    
    @ResponseBody
    @RequestMapping(value = "/remito/getChofer/{idTransporte}", method = RequestMethod.GET)
    public List<ContactoBean> getChofer(@PathVariable String idTransporte, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ContactoBean> result = new ArrayList<ContactoBean>();
        
        if(idTransporte != null && !idTransporte.isEmpty()) {
            
            Integer idTipoChofer = -1;
            TipoService tipoService = new TipoServiceImpl();
            List<TipoModel> roles = tipoService.getByType("rol");
            if(roles != null && !roles.isEmpty()) {
                for(TipoModel rol: roles) {
                    if(rol.getValor().equalsIgnoreCase("Chofer")) {
                        idTipoChofer = rol.getId();
                        break;
                    }
                }
            }
            System.out.println("*** idTipoChofer:"+idTipoChofer);
            System.out.println("*** idTransporte:"+idTransporte);
            ContactoService contactoService = new ContactoServiceImpl();
            ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
            List<ProveedorContactoModel> proveedorContactos = proveedorContactoService.getByPkProveedor(Integer.valueOf(idTransporte));
            if(proveedorContactos != null && !proveedorContactos.isEmpty()) {
                for(ProveedorContactoModel proveedorContacto :proveedorContactos) {
                    ContactoModel contacto = contactoService.getByPk(proveedorContacto.getIdContacto());
                    if(contacto != null && contacto.getIdRol() == idTipoChofer) {
                        ContactoBean bean = new ContactoBean();
                        bean.setId(contacto.getId().toString());                        
                        bean.setNombre(contacto.getNombre());
                        
                        result.add(bean);
                    }
                }
            }            
        }
        
        return result;
    }
    
    @RequestMapping(value = "/remito/print/{remitopk}", method = RequestMethod.GET)
    public String printRemito(@PathVariable String remitopk, HttpServletRequest req, ModelMap model) throws Exception {
        

        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitopk));
        if(remito == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No ha sido encontrado.");         
            return "/error";    
        }

        /*
        if(remito.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: Remito con estado inválido. No es posible realizar este operación.");         
            return "/error";    
        }
        */
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();   
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        LocalidadService localidadService = new LocalidadServiceImpl();   
        TipoService tipoService = new TipoServiceImpl();   
        
                
        RemitoDetalleForm remitoDetalleForm = new RemitoDetalleForm();
        remitoDetalleForm.setIdRemito(remito.getId().toString());
        remitoDetalleForm.setCodigoRemito(remito.getId().toString());
        remitoDetalleForm.setFechaRemito(remito.getFechaRemito().toString().replace("00:00:00.0", ""));
        remitoDetalleForm.setTipoRemito(remito.getTipoRemito());
        remitoDetalleForm.setEstadoRemito(remito.getEstado()); 
        remitoDetalleForm.setObservaciones(remito.getObservaciones());
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
        if(remito.getIdChofer() != null) {
            ContactoService contactoService = new ContactoServiceImpl();
            ContactoModel c = contactoService.getByPk(remito.getIdChofer());
           remitoDetalleForm.setIdChofer(c.getNombre());
        }
        if(remito.getIdDomicilio() != null) {
            DomicilioModel domicilioModel = domicilioService.getByPk(remito.getIdDomicilio());
            if(domicilioModel != null) {
                 TipoService tipoServicee = new TipoServiceImpl();
                 TipoModel localidaad = null;
                 TipoModel provincia = null;
                
                if (domicilioModel.getIdLocalidad() != null){
                    localidaad = tipoServicee.getByPk(domicilioModel.getIdLocalidad());
                    remitoDetalleForm.setLocalidad(localidaad.getValor());
                }
                
                if (domicilioModel.getIdProvincia() != null){
                    provincia = tipoServicee.getByPk(domicilioModel.getIdProvincia());
                    remitoDetalleForm.setProvincia(provincia.getValor());
                }
                
                remitoDetalleForm.setDomicilio(domicilioModel.getUbicacion());
                remitoDetalleForm.setNombreContacto(domicilioModel.getNombreContacto());
                remitoDetalleForm.setTelefonoContacto(domicilioModel.getTelefonoContacto());
                remitoDetalleForm.setPuntoGps(domicilioModel.getPuntoGps());
            }
            
        }        
        if(remito.getIdTransporte() != null) {
            ProveedorService proveedorService = new ProveedorServiceImpl();
            ProveedorModel proveedor = proveedorService.getByPk(remito.getIdTransporte());
            remitoDetalleForm.setTransporte(proveedor.getRazonSocial());
            //TipoModel tipo = tipoService.getByPk(remito.getIdTransporte());
            //if(tipo != null) {
                //remitoDetalleForm.setTransporte(tipo.getValor());
            //}
        }
        
        model.addAttribute("remitoDetalleForm", remitoDetalleForm);  
                
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        ArticuloService articuloService = new ArticuloServiceImpl();
        
        
        OrdenDeProduccionBobinaService bobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBultoService bultoService = new OrdenDeProduccionBultoServiceImpl();   
        OrdenDeProduccionPalletService palletService = new OrdenDeProduccionPalletServiceImpl();                   
        List<RemitoDetalleModel> remitoDetalles = remitoDetalleService.getAllByRemito(remito.getId());
        
        List<RemitoDetalleDto> remitoDetallesDtos = new ArrayList<RemitoDetalleDto>();
        for(RemitoDetalleModel remitoDetalleModel: remitoDetalles) {
            RemitoDetalleDto remitoDetalleDto = new RemitoDetalleDto();
            remitoDetalleDto.setPk(remitoDetalleModel.getId().toString());
            remitoDetalleDto.setFechaAlta(remitoDetalleModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            remitoDetalleDto.setCantidad(remitoDetalleModel.getCantidad().toString());
            remitoDetalleDto.setLote(remitoDetalleModel.getIdOrdenDeProduccion().toString());
            TipoModel deposito = tipoService.getByPk(remitoDetalleModel.getIdDeposito());
                if(deposito != null) {
                    remitoDetalleDto.setDeposito(deposito.getValor());
                } 
            OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(remitoDetalleModel.getIdOrdenDeProduccion());
            ArticuloModel articulo = null;
            if(ordenDeProduccion != null){
               articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
            }
            if(articulo != null) {
                remitoDetalleDto.setArticulo(articulo.getDenominacion());
            }
            //remitoDetalleDto.setDeposito(remitoDetalleModel.getIdDeposito().toString());
            
            //if(remitoDetalleModel.getIngresoDeposito()) {
                //remitoDetalleDto.setIngresoDeposito("Si");
            //} else {
                //remitoDetalleDto.setIngresoDeposito("No");
            //}
            
//            if(remitoDetalleModel.getIdBobina() != null) {
//                
//                OrdenDeProduccionBobinaModel bobina = bobinaService.getByPk(remitoDetalleModel.getIdBobina());
//                if(bobina != null) {
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());                        
//                        
//                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null) {
//                            remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                        }
//                    }
//                    remitoDetalleDto.setCodigo(bobina.getCodigo());
//                }
//            }
//            if(remitoDetalleModel.getIdBulto() != null) {
//                
//                OrdenDeProduccionBultoModel bulto = bultoService.getByPk(remitoDetalleModel.getIdBulto());
//                if(bulto != null) {
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
//                        
//                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null) {
//                            remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                        }                        
//                    }
//                    remitoDetalleDto.setCodigo(bulto.getCodigo());
//                }
//            }
//            if(remitoDetalleModel.getIdPallet() != null) {
//                
//                OrdenDeProduccionPalletModel pallet = palletService.getByPk(remitoDetalleModel.getIdPallet());
//                if(pallet != null) {
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
//                        
//                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null) {
//                            remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                        }                        
//                    }
//                    remitoDetalleDto.setCodigo(pallet.getCodigo());
//                }
//            }

            
            remitoDetallesDtos.add(remitoDetalleDto);
            
            
        }
        
        Date fechaActual = new Date();
        String nombreRemito = "REMITO DE ";
        if(remito.getTipoRemito().equalsIgnoreCase("Salida")) {
            nombreRemito = nombreRemito + "SALIDA Nro. ";
        }
        if(remito.getTipoRemito().equalsIgnoreCase("Entrada")) {
            nombreRemito = nombreRemito + "ENTRADA Nro. ";
        }
        nombreRemito = nombreRemito + remito.getId();
                
        model.addAttribute("nombreRemito", nombreRemito);        
        model.addAttribute("remitoDetalles", remitoDetallesDtos);                   
        model.addAttribute("fechaActual", fechaActual);
        
        
        return "/remito/remitoprint"; 
    }        
}
