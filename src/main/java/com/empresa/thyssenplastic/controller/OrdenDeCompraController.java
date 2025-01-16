/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.OrdenDeCompraForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.OrdenDeCompraDto;
import com.empresa.thyssenplastic.dto.OrdenDeCompraItemDto;
import com.empresa.thyssenplastic.dto.OrdenDeCompraItemRecepcionDto;
import com.empresa.thyssenplastic.model.InsumoModel;
import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemModel;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemRecepcionModel;
import com.empresa.thyssenplastic.model.OrdenDeCompraModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.InsumoService;
import com.empresa.thyssenplastic.service.MateriaPrimaService;
import com.empresa.thyssenplastic.service.OrdenDeCompraItemRecepcionService;
import com.empresa.thyssenplastic.service.OrdenDeCompraItemService;
import com.empresa.thyssenplastic.service.OrdenDeCompraService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.InsumoServiceImpl;
import com.empresa.thyssenplastic.service.impl.MateriaPrimaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeCompraItemRecepcionServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeCompraItemServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.OrdenDeCompraServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author gusta
 */
@Controller
public class OrdenDeCompraController {
    

    @RequestMapping(value = "/ordenDeCompra", method = RequestMethod.GET)
    public String getHomeOrdenDeCompra(HttpServletRequest req, ModelMap model) {

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
        
        OrdenDeCompraForm ordenDeCompraForm = new OrdenDeCompraForm();
        ordenDeCompraForm.setPk("-1");
        ordenDeCompraForm.setAction("add");
        ordenDeCompraForm.setEstado("Nuevo");

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        ordenDeCompraForm.setFechaAlta(fecha);

        if(user.getRol() == Utils.ROL_OFICINA) {
            ordenDeCompraForm.setEstadoLabel("Nuevo");            
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            ordenDeCompraForm.setEstadoLabel("Abierto");
            operacion = "recepcion";
            displayActionButton = "none";            
            rol = "deposito";
        }
        
        ordenDeCompraForm.setEditObservaciones("false");
        ordenDeCompraForm.setOperacion(operacion);
        
        model.addAttribute("ordenDeCompraForm", ordenDeCompraForm);  
        model.addAttribute("titleOrdenDeCompra", "Nueva Order De Compra");  
        model.addAttribute("buttonLabel", "Guardar");
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();   
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        List<OrdenDeCompraModel> ordenDeCompras = ordenDeCompraService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
        
        List<OrdenDeCompraDto> ordenDeComprasDtos = new ArrayList<OrdenDeCompraDto>();
        for(OrdenDeCompraModel ordenDeCompra: ordenDeCompras) {
            OrdenDeCompraDto ordenDeCompraDto = new OrdenDeCompraDto();
            
            if ( !(user.getRol() == Utils.ROL_OFICINA) && ordenDeCompra.getEstado().equalsIgnoreCase("Cerrado")) {
                continue;
            }
            
            ordenDeCompraDto.setPk(ordenDeCompra.getId().toString());
            ordenDeCompraDto.setFechaAlta(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(ordenDeCompra.getFechaEntrega() != null) {
                ordenDeCompraDto.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
            }
            if(ordenDeCompra.getReferenciaAdministrativa() != null) {
                ordenDeCompraDto.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
            }            
            ordenDeCompraDto.setEstado(ordenDeCompra.getEstado());
            ordenDeCompraDto.setProveedor(proveedores.get(ordenDeCompra.getIdProveedor().toString()));
            
            List<OrdenDeCompraItemModel> items = ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompra.getId());
            if(ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo") && items != null && !items.isEmpty()) {
                ordenDeCompraDto.setSePuedeCambiarEstadoAbierto("true");    
            } else {
                ordenDeCompraDto.setSePuedeCambiarEstadoAbierto("false");    
            }
            
            for(OrdenDeCompraItemModel item: items) {
                if(item.getSuperaCantidad() != null && item.getSuperaCantidad()) {
                    ordenDeCompraDto.setSuperaCantidad("true");
                }
                if(item.getFaltaCantidad() != null && item.getFaltaCantidad()) {
                    ordenDeCompraDto.setFaltaCantidad("true");
                }                
            }
            
            ordenDeComprasDtos.add(ordenDeCompraDto);
            
            
        }
                       
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("ordenDeCompras", ordenDeComprasDtos);        
                
        return "/ordendecompra/ordendecompra";
    }
    
    @RequestMapping(value = "/ordenDeCompra/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveOrdenDeCompra(@ModelAttribute("ordenDeCompraForm")OrdenDeCompraForm ordenDeCompraForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

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
        
        if(ordenDeCompraForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        String operacion = ordenDeCompraForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta") && !operacion.equalsIgnoreCase("recepcion") && !operacion.equalsIgnoreCase("cierre") && !operacion.equalsIgnoreCase("completar"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }        
        if(ordenDeCompraForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = ordenDeCompraForm.getPk();
            
        OrdenDeCompraModel ordenDeCompraModel = null;
        if(id.equalsIgnoreCase("-1")) {
            ordenDeCompraModel = new OrdenDeCompraModel();
            ordenDeCompraModel.setFechaAltaImpresion(new Date());
        } else {
            ordenDeCompraModel = ordenDeCompraService.getByPk(Integer.valueOf(id));
            if(ordenDeCompraModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de ordenDeCompra inválido.");
                return modelAndView;                
            } 
            if(!ordenDeCompraModel.getEstado().equalsIgnoreCase("Nuevo") && (user.getRol() != Utils.ROL_OFICINA || (!ordenDeCompraModel.getEstado().equalsIgnoreCase("Completado") && !ordenDeCompraModel.getEstado().equalsIgnoreCase("Abierto")) || ordenDeCompraForm.getEditObservaciones() == null || !ordenDeCompraForm.getEditObservaciones().equalsIgnoreCase("true"))) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: no es posible editar una orden en estado "+ordenDeCompraModel.getEstado());
                return modelAndView;                        
            }            
        }        
        
        if(operacion.equalsIgnoreCase("alta")) {
            if(ordenDeCompraForm.getEditObservaciones().isEmpty() || ordenDeCompraForm.getEditObservaciones().equalsIgnoreCase("false")) {
                if(ordenDeCompraForm.getFechaAlta() != null && !ordenDeCompraForm.getFechaAlta().trim().equals("")) {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                    Date fecha = formato.parse(ordenDeCompraForm.getFechaAlta());
                    ordenDeCompraModel.setFechaAlta(fecha);
                } else {
                    ordenDeCompraModel.setFechaAlta(null);
                }                
                if(ordenDeCompraForm.getFechaEntrega() != null && !ordenDeCompraForm.getFechaEntrega().trim().equals("")) {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                    Date fecha = formato.parse(ordenDeCompraForm.getFechaEntrega());
                    ordenDeCompraModel.setFechaEntrega(fecha);
                } else {
                    ordenDeCompraModel.setFechaEntrega(null);
                }                                
                if(ordenDeCompraForm.getIdProveedor() != null && !ordenDeCompraForm.getIdProveedor().isEmpty()) {
                    ordenDeCompraModel.setIdProveedor(Integer.valueOf(ordenDeCompraForm.getIdProveedor()));
                } else {
                    ordenDeCompraModel.setIdProveedor(null);
                }
                if(ordenDeCompraForm.getReferenciaAdministrativa() != null && !ordenDeCompraForm.getReferenciaAdministrativa().isEmpty()) {
                    ordenDeCompraModel.setReferenciaAdministrativa(ordenDeCompraForm.getReferenciaAdministrativa());
                } else {
                    ordenDeCompraModel.setReferenciaAdministrativa(null);
                }
                ordenDeCompraModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));
                ordenDeCompraModel.setEstado("Nuevo");                
            }
            if(ordenDeCompraForm.getObservaciones() != null && !ordenDeCompraForm.getObservaciones().isEmpty()) {
                ordenDeCompraModel.setObservaciones(ordenDeCompraForm.getObservaciones());
            } else {
                ordenDeCompraModel.setObservaciones(null);
            }
        }

        if(operacion.equalsIgnoreCase("cierre")) {
            if(ordenDeCompraForm.getFechaEntrega() != null && !ordenDeCompraForm.getFechaEntrega().trim().equals("")) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(ordenDeCompraForm.getFechaEntrega());
                ordenDeCompraModel.setFechaEntrega(fecha);
            } else {
                ordenDeCompraModel.setFechaEntrega(null);
            }                

            ordenDeCompraModel.setIdUsuarioCierre(Integer.valueOf(Utils.getUserLoggedId(req)));
            ordenDeCompraModel.setEstado("Cerrado");
        }
        
        if(operacion.equalsIgnoreCase("recepcion")) {
            ordenDeCompraModel.setEstado("Completado");
        }

        if(operacion.equalsIgnoreCase("completar")) {
            if(ordenDeCompraForm.getObservaciones() != null && !ordenDeCompraForm.getObservaciones().isEmpty()) {
                ordenDeCompraModel.setObservaciones(ordenDeCompraForm.getObservaciones());
            } else {
                ordenDeCompraModel.setObservaciones(null);
            }

            ordenDeCompraModel.setEstado("Completado");
            
            OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();   
            List<OrdenDeCompraItemModel> ordenesDeCompraItem = ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompraModel.getId());
            if(!ordenesDeCompraItem.isEmpty()) {
                for(OrdenDeCompraItemModel item: ordenesDeCompraItem) {
                    if(!item.getEstaCompletado()) {
                        item.setEstaCompletado(Boolean.TRUE);
                        item.setFaltaCantidad(Boolean.TRUE);                    
                        item.setFechaCompletado(new Date());

                        ordenDeCompraItemService.save(item);
                    }
                }
            }
        }
        
        if(ordenDeCompraForm.getAction().equalsIgnoreCase("add") || ordenDeCompraForm.getAction().equalsIgnoreCase("edit")) {
            ordenDeCompraService.save(ordenDeCompraModel);
        } else {
            if(ordenDeCompraForm.getAction().equalsIgnoreCase("remove")) {
                if(ordenDeCompraModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de ordenDeCompra inválido.");
                    return modelAndView;                                    
                }
                
                ordenDeCompraService.delete(ordenDeCompraModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/ordenDeCompra");

        return modelAndView; 
    }

    @RequestMapping(value = "/ordenDeCompra/edit/{ordenDeComprapk}", method = RequestMethod.GET)
    public String editOrdenDeCompra(@PathVariable String ordenDeComprapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeComprapk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeComprapk));
        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo")) {
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
        
        OrdenDeCompraForm ordenDeCompraForm = new OrdenDeCompraForm();
        ordenDeCompraForm.setPk(ordenDeCompra.getId().toString());
        if(ordenDeCompra.getFechaAlta() != null) {
            ordenDeCompraForm.setFechaAlta(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeCompra.getFechaEntrega() != null) {
            ordenDeCompraForm.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompra.getIdProveedor() != null) {
            ordenDeCompraForm.setIdProveedor(ordenDeCompra.getIdProveedor().toString());
        }        
        if(ordenDeCompra.getObservaciones() != null && !ordenDeCompra.getObservaciones().isEmpty()) {
            ordenDeCompraForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompra.getReferenciaAdministrativa() != null && !ordenDeCompra.getReferenciaAdministrativa().isEmpty()) {
            ordenDeCompraForm.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
        }                
        if(ordenDeCompra.getEstado() != null && !ordenDeCompra.getEstado().isEmpty()) {
            ordenDeCompraForm.setEstado(ordenDeCompra.getEstado());
        }        
        
        ordenDeCompraForm.setOperacion(operacion);
        
        ordenDeCompraForm.setAction("edit");
        model.addAttribute("ordenDeCompraForm", ordenDeCompraForm);  
        model.addAttribute("titleOrdenDeCompra", "Editar Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeCompraName", ordenDeCompra.getId() + " - " + ordenDeCompra.getIdProveedor());        
        
        List<OrdenDeCompraModel> ordenDeCompras = ordenDeCompraService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<OrdenDeCompraDto> ordenDeComprasDtos = new ArrayList<OrdenDeCompraDto>();
        for(OrdenDeCompraModel ordenDeCompraModel: ordenDeCompras) {
            OrdenDeCompraDto ordenDeCompraDto = new OrdenDeCompraDto();
            ordenDeCompraDto.setPk(ordenDeCompraModel.getId().toString());
            ordenDeCompraDto.setFechaAlta(ordenDeCompraModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(ordenDeCompra.getFechaEntrega() != null) {
                ordenDeCompraDto.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
            }
            if(ordenDeCompra.getReferenciaAdministrativa() != null) {
                ordenDeCompraDto.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
            }                        
            ordenDeCompraDto.setEstado(ordenDeCompraModel.getEstado());
            ordenDeCompraDto.setProveedor(proveedores.get(ordenDeCompraModel.getIdProveedor().toString()));
            
            ordenDeComprasDtos.add(ordenDeCompraDto);
        }
                               
        model.addAttribute("displayUser", "none");
        
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("ordenDeCompras", ordenDeComprasDtos);        
                                                        
        return "/ordendecompra/ordendecompra";        
    }
    
    @RequestMapping(value = "/ordenDeCompra/editObservaciones/{ordenDeComprapk}", method = RequestMethod.GET)
    public String editObservacionesOrdenDeCompra(@PathVariable String ordenDeComprapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeComprapk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeComprapk));
        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Completado")) {
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
        
        OrdenDeCompraForm ordenDeCompraForm = new OrdenDeCompraForm();
        ordenDeCompraForm.setPk(ordenDeCompra.getId().toString());
        if(ordenDeCompra.getFechaAlta() != null) {
            ordenDeCompraForm.setFechaAlta(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeCompra.getFechaEntrega() != null) {
            ordenDeCompraForm.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }                
        if(ordenDeCompra.getIdProveedor() != null) {
            ordenDeCompraForm.setIdProveedor(ordenDeCompra.getIdProveedor().toString());
        }        
        if(ordenDeCompra.getObservaciones() != null && !ordenDeCompra.getObservaciones().isEmpty()) {
            ordenDeCompraForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompra.getReferenciaAdministrativa() != null && !ordenDeCompra.getReferenciaAdministrativa().isEmpty()) {
            ordenDeCompraForm.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
        }                        
        if(ordenDeCompra.getEstado() != null && !ordenDeCompra.getEstado().isEmpty()) {
            ordenDeCompraForm.setEstado(ordenDeCompra.getEstado());
        }        
        
        ordenDeCompraForm.setOperacion(operacion);
        ordenDeCompraForm.setEditObservaciones("true");
                
        ordenDeCompraForm.setAction("edit");
        model.addAttribute("ordenDeCompraForm", ordenDeCompraForm);  
        model.addAttribute("titleOrdenDeCompra", "Editar Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeCompraName", ordenDeCompra.getId() + " - " + ordenDeCompra.getIdProveedor());        
        
        List<OrdenDeCompraModel> ordenDeCompras = ordenDeCompraService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<OrdenDeCompraDto> ordenDeComprasDtos = new ArrayList<OrdenDeCompraDto>();
        for(OrdenDeCompraModel ordenDeCompraModel: ordenDeCompras) {
            OrdenDeCompraDto ordenDeCompraDto = new OrdenDeCompraDto();
            ordenDeCompraDto.setPk(ordenDeCompraModel.getId().toString());
            ordenDeCompraDto.setFechaAlta(ordenDeCompraModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(ordenDeCompra.getFechaEntrega() != null) {
                ordenDeCompraDto.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
            }
            if(ordenDeCompra.getReferenciaAdministrativa() != null) {
                ordenDeCompraDto.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
            }                        
            ordenDeCompraDto.setEstado(ordenDeCompraModel.getEstado());
            ordenDeCompraDto.setProveedor(proveedores.get(ordenDeCompraModel.getIdProveedor().toString()));
            
            ordenDeComprasDtos.add(ordenDeCompraDto);
        }
                               
        model.addAttribute("displayUser", "none");
                
        model.addAttribute("action", "editObservaciones");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("ordenDeCompras", ordenDeComprasDtos);        
                                                        
        return "/ordendecompra/ordendecompra";        
    }
    
    @RequestMapping(value = "/ordenDeCompra/remove/{ordenDeComprapk}", method = RequestMethod.GET)
    public String removeOrdenDeCompra(@PathVariable String ordenDeComprapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeComprapk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeComprapk));
        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo")) {
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
        
        OrdenDeCompraForm ordenDeCompraForm = new OrdenDeCompraForm();
        ordenDeCompraForm.setPk(ordenDeCompra.getId().toString());
        if(ordenDeCompra.getFechaAlta() != null) {
            ordenDeCompraForm.setFechaAlta(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeCompra.getFechaEntrega() != null) {
            ordenDeCompraForm.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }                
        if(ordenDeCompra.getIdProveedor() != null) {
            ordenDeCompraForm.setIdProveedor(ordenDeCompra.getIdProveedor().toString());
        }        
        if(ordenDeCompra.getObservaciones() != null && !ordenDeCompra.getObservaciones().isEmpty()) {
            ordenDeCompraForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompra.getReferenciaAdministrativa() != null && !ordenDeCompra.getReferenciaAdministrativa().isEmpty()) {
            ordenDeCompraForm.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
        }                        
        if(ordenDeCompra.getEstado() != null && !ordenDeCompra.getEstado().isEmpty()) {
            ordenDeCompraForm.setEstado(ordenDeCompra.getEstado());
        }        
        
        ordenDeCompraForm.setOperacion(operacion);
        
        ordenDeCompraForm.setAction("remove");
        model.addAttribute("ordenDeCompraForm", ordenDeCompraForm);  
        model.addAttribute("titleOrdenDeCompra", "Eliminar Orden de Compra");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("ordenDeCompraName", ordenDeCompra.getId() + " - " + ordenDeCompra.getIdProveedor());        
        
        List<OrdenDeCompraModel> ordenDeCompras = ordenDeCompraService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<OrdenDeCompraDto> ordenDeComprasDtos = new ArrayList<OrdenDeCompraDto>();
        for(OrdenDeCompraModel ordenDeCompraModel: ordenDeCompras) {
            OrdenDeCompraDto ordenDeCompraDto = new OrdenDeCompraDto();
            ordenDeCompraDto.setPk(ordenDeCompraModel.getId().toString());
            ordenDeCompraDto.setFechaAlta(ordenDeCompraModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(ordenDeCompra.getFechaEntrega() != null) {
                ordenDeCompraDto.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
            }
            if(ordenDeCompra.getReferenciaAdministrativa() != null) {
                ordenDeCompraDto.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
            }                        
            ordenDeCompraDto.setEstado(ordenDeCompraModel.getEstado());
            ordenDeCompraDto.setProveedor(proveedores.get(ordenDeCompraModel.getIdProveedor().toString()));
            
            ordenDeComprasDtos.add(ordenDeCompraDto);
        }
                               
        model.addAttribute("displayUser", "none");
        
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("ordenDeCompras", ordenDeComprasDtos);        
                                                        
        return "/ordendecompra/ordendecompra";    
             
    }    
    
    @RequestMapping(value = "/ordenDeCompra/view/{ordenDeComprapk}", method = RequestMethod.GET)
    public String viewOrdenDeCompra(@PathVariable String ordenDeComprapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeComprapk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido");         
            return "/error";                
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeComprapk));
        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeCompraForm ordenDeCompraForm = new OrdenDeCompraForm();
        ordenDeCompraForm.setPk(ordenDeCompra.getId().toString());
        if(ordenDeCompra.getFechaAlta() != null) {
            ordenDeCompraForm.setFechaAlta(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeCompra.getFechaEntrega() != null) {
            ordenDeCompraForm.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }                
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraForm.setFechaCierreOrden(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompra.getIdProveedor() != null) {
            ordenDeCompraForm.setIdProveedor(ordenDeCompra.getIdProveedor().toString());
        }        
        if(ordenDeCompra.getObservaciones() != null && !ordenDeCompra.getObservaciones().isEmpty()) {
            ordenDeCompraForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompra.getReferenciaAdministrativa() != null && !ordenDeCompra.getReferenciaAdministrativa().isEmpty()) {
            ordenDeCompraForm.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
        }                        
        if(ordenDeCompra.getEstado() != null && !ordenDeCompra.getEstado().isEmpty()) {
            ordenDeCompraForm.setEstado(ordenDeCompra.getEstado());
        }        
        
        ordenDeCompraForm.setIdUsuarioAlta(ordenDeCompra.getIdUsuarioAlta().toString());
        UserService userService = new UserServiceImpl(); 
        UserModel userAlta = userService.getUserById(ordenDeCompra.getIdUsuarioAlta());
        ordenDeCompraForm.setUsuarioAlta(userAlta.getUsername());        

        if(ordenDeCompra.getIdUsuarioAlta() != null) {
            ordenDeCompraForm.setIdUsuarioCierre(ordenDeCompra.getIdUsuarioAlta().toString());
            UserModel userFin = userService.getUserById(ordenDeCompra.getIdUsuarioCierre());
            if(userFin != null) {
                ordenDeCompraForm.setUsuarioCierre(userFin.getUsername());
            }
        }
        
        ordenDeCompraForm.setAction("view");
        model.addAttribute("ordenDeCompraForm", ordenDeCompraForm);  
        model.addAttribute("titleOrdenDeCompra", "Ver Orden De Compra " + ordenDeCompra.getId());
        model.addAttribute("buttonLabel", "View");
        model.addAttribute("ordenDeCompraName", ordenDeCompra.getId() + " - " + ordenDeCompra.getIdProveedor());
        
        List<OrdenDeCompraModel> ordenDeCompras = ordenDeCompraService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<OrdenDeCompraDto> ordenDeComprasDtos = new ArrayList<OrdenDeCompraDto>();
        for(OrdenDeCompraModel ordenDeCompraModel: ordenDeCompras) {
            OrdenDeCompraDto ordenDeCompraDto = new OrdenDeCompraDto();
            ordenDeCompraDto.setPk(ordenDeCompraModel.getId().toString());
            ordenDeCompraDto.setFechaAlta(ordenDeCompraModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(ordenDeCompra.getFechaEntrega() != null) {
                ordenDeCompraDto.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
            }
            if(ordenDeCompra.getReferenciaAdministrativa() != null) {
                ordenDeCompraDto.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
            }                        
            ordenDeCompraDto.setEstado(ordenDeCompraModel.getEstado());
            ordenDeCompraDto.setProveedor(proveedores.get(ordenDeCompraModel.getIdProveedor().toString()));
            
            ordenDeComprasDtos.add(ordenDeCompraDto);
        }
        
                                        
        String operacion = "view";

        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", "none");
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("ordenDeCompras", ordenDeComprasDtos);        
        
                
        return "/ordendecompra/ordendecompra";        
    }    

    @RequestMapping(value = "/ordenDeCompra/setStatusOpenOrdenCompra/{ordenDeCompraPk}", method = RequestMethod.GET)
    public String setStatusOpenOrdenCompra(@PathVariable String ordenDeCompraPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraPk == null || ordenDeCompraPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();        
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeCompraPk));

        if(ordenDeCompra == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeCompra.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();        
        List<OrdenDeCompraItemModel> items = ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompra.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        ordenDeCompra.setEstado("Abierto");
        ordenDeCompraService.save(ordenDeCompra);
        
        return "redirect:/ordenDeCompra";                         
        
    }       
    
    @RequestMapping(value = "/ordenDeCompra/setStatusCloseOrdenCompra/{ordenDeCompraPk}", method = RequestMethod.GET)
    public String setStatusCoseOrdenCompra(@PathVariable String ordenDeCompraPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraPk == null || ordenDeCompraPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();        
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeCompraPk));

        if(ordenDeCompra == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Completado")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeCompra.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();        
        List<OrdenDeCompraItemModel> items = ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompra.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        Date today = new Date();
        
        ordenDeCompra.setFechaCierreOrden(today);
        ordenDeCompra.setIdUsuarioCierre(user.getId());
        ordenDeCompra.setEstado("Cerrado");
        ordenDeCompraService.save(ordenDeCompra);
        
        return "redirect:/ordenDeCompra";                         
        
    }    
    
    @RequestMapping(value = "/ordenDeCompra/completarOrden/{ordenDeComprapk}", method = RequestMethod.GET)
    public String completarOrdenObservacionesOrdenDeCompra(@PathVariable String ordenDeComprapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeComprapk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido");         
            return "/error";                
        }
        
        String operacion = "completar";        
        String displayActionButton = "block";
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeComprapk));
        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Abierto")) {
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
        
        OrdenDeCompraForm ordenDeCompraForm = new OrdenDeCompraForm();
        ordenDeCompraForm.setPk(ordenDeCompra.getId().toString());
        if(ordenDeCompra.getFechaAlta() != null) {
            ordenDeCompraForm.setFechaAlta(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeCompra.getIdProveedor() != null) {
            ordenDeCompraForm.setIdProveedor(ordenDeCompra.getIdProveedor().toString());
        }        
        if(ordenDeCompra.getObservaciones() != null && !ordenDeCompra.getObservaciones().isEmpty()) {
            ordenDeCompraForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompra.getReferenciaAdministrativa() != null && !ordenDeCompra.getReferenciaAdministrativa().isEmpty()) {
            ordenDeCompraForm.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
        }                        
        if(ordenDeCompra.getEstado() != null && !ordenDeCompra.getEstado().isEmpty()) {
            ordenDeCompraForm.setEstado(ordenDeCompra.getEstado());
        }        
        
        ordenDeCompraForm.setOperacion(operacion);
        ordenDeCompraForm.setEditObservaciones("true");
                
        ordenDeCompraForm.setAction("edit");
        model.addAttribute("ordenDeCompraForm", ordenDeCompraForm);  
        model.addAttribute("titleOrdenDeCompra", "Completar Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeCompraName", ordenDeCompra.getId() + " - " + ordenDeCompra.getIdProveedor());        
        
        List<OrdenDeCompraModel> ordenDeCompras = ordenDeCompraService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<OrdenDeCompraDto> ordenDeComprasDtos = new ArrayList<OrdenDeCompraDto>();
        for(OrdenDeCompraModel ordenDeCompraModel: ordenDeCompras) {
            OrdenDeCompraDto ordenDeCompraDto = new OrdenDeCompraDto();
            ordenDeCompraDto.setPk(ordenDeCompraModel.getId().toString());
            ordenDeCompraDto.setFechaAlta(ordenDeCompraModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(ordenDeCompra.getFechaEntrega() != null) {
                ordenDeCompraDto.setFechaEntrega(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
            }
            if(ordenDeCompra.getReferenciaAdministrativa() != null) {
                ordenDeCompraDto.setReferenciaAdministrativa(ordenDeCompra.getReferenciaAdministrativa());
            }                        
            ordenDeCompraDto.setEstado(ordenDeCompraModel.getEstado());
            ordenDeCompraDto.setProveedor(proveedores.get(ordenDeCompraModel.getIdProveedor().toString()));
            
            ordenDeComprasDtos.add(ordenDeCompraDto);
        }
                               
        model.addAttribute("displayUser", "none");
                
        model.addAttribute("action", "editObservaciones");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("ordenDeCompras", ordenDeComprasDtos);        
                                                        
        return "/ordendecompra/ordendecompra";        
    }    
    
    @RequestMapping(value = "/ordenDeCompra/print/{ordendecomprapk}", method = RequestMethod.GET)
    public String printOrdenDeCompra(@PathVariable String ordendecomprapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordendecomprapk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido");         
            return "/error";                
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordendecomprapk));
        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompra inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        String refAdministrativa = ordenDeCompra.getReferenciaAdministrativa();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        String fechaHoy = sdf.format(today);
        String fechaEntrega = ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", "");
        String estado = ordenDeCompra.getEstado();
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(ordenDeCompra.getIdProveedor());
        String proveedorRazonSocial = proveedor.getRazonSocial();
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();   
        List<OrdenDeCompraItemModel> ordenDeCompraItems = ordenDeCompraItemService.getAllByOrdenDeCompra(Integer.valueOf(ordendecomprapk));

        TipoService tipoService = new TipoServiceImpl();
        
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        
        InsumoService insumoService = new InsumoServiceImpl();   
        
        List<OrdenDeCompraItemDto> ordenDeCompraItemsDtos = new ArrayList<OrdenDeCompraItemDto>();
        List<OrdenDeCompraItemRecepcionDto> ordenDeCompraItemsRecepcionDtos = new ArrayList<OrdenDeCompraItemRecepcionDto>();
        String unidad = "";
        String suministro = "";
        
        for(OrdenDeCompraItemModel ordenDeCompraItem: ordenDeCompraItems) {
            OrdenDeCompraItemDto ordenDeCompraItemDto = new OrdenDeCompraItemDto();
            ordenDeCompraItemDto.setPk(ordenDeCompraItem.getId().toString());
            ordenDeCompraItemDto.setFechaAlta(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeCompraItemDto.setCantidadSolicitada(ordenDeCompraItem.getCantidad().toString());

            OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl(); 
            Integer cantidadRecepcionadaTotal = 0;
            List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
            
            if(ordenDeCompra.getReferenciaAdministrativa() != null) {
                
                ordenDeCompraItemDto.setReferenciaAdministrativa( ordenDeCompra.getReferenciaAdministrativa());
            }
            
            ordenDeCompraItemDto.setCantidadRecepcionada(cantidadRecepcionadaTotal.toString());            

            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
                MateriaPrimaModel materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());            
                ordenDeCompraItemDto.setMateriaPrima(materiaPrima.getDescripcion());
                suministro = materiaPrima.getDescripcion();
                ordenDeCompraItemDto.setTipo("Materia Prima");
                ordenDeCompraItemDto.setUnidad("kilos");
                unidad = "kilos";
                ordenDeCompraItemDto.setStock(materiaPrima.getStock().toString());            
            }                        
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
                InsumoModel insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());                            
                ordenDeCompraItemDto.setMateriaPrima(insumo.getDescripcion());
                suministro = insumo.getDescripcion();
                ordenDeCompraItemDto.setTipo("Insumo");
                unidad = "unidades";
                ordenDeCompraItemDto.setUnidad("unidades");
                ordenDeCompraItemDto.setStock(insumo.getStock().toString());            
            }                                    
            if(ordenDeCompraItem.getEstaCompletado() == null) {
                ordenDeCompraItemDto.setEstaCompletado("0");
            } else {
                ordenDeCompraItemDto.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
            }

            if(ordenDeCompraItemsRecepcion != null && !ordenDeCompraItemsRecepcion.isEmpty()) {
                for(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcion: ordenDeCompraItemsRecepcion) {
                    OrdenDeCompraItemRecepcionDto ordenDeCompraItemRecepcionDto = new OrdenDeCompraItemRecepcionDto();
                    ordenDeCompraItemRecepcionDto.setPk(ordenDeCompraItemRecepcion.getId().toString());
                    ordenDeCompraItemRecepcionDto.setItem(ordenDeCompraItem.getId().toString());
                    ordenDeCompraItemRecepcionDto.setFechaRecepcion(ordenDeCompraItemRecepcion.getFechaRecepcion().toString().replace(".0",""));
                    ordenDeCompraItemRecepcionDto.setCantidadRecepcionada(ordenDeCompraItemRecepcion.getCantidadRecepcionada().toString());
                    ordenDeCompraItemRecepcionDto.setUnidad(unidad);
                    ordenDeCompraItemRecepcionDto.setSuministro(suministro);
                    if ( unidad.equalsIgnoreCase("unidades") ){
                        ordenDeCompraItemRecepcionDto.setTipo("Insumo");
                    }
                    if ( unidad.equalsIgnoreCase("kilos")) {
                        ordenDeCompraItemRecepcionDto.setTipo("Materia Prima");
                    }
                    
                    if(ordenDeCompraItemRecepcion.getLote() != null) {
                        ordenDeCompraItemRecepcionDto.setLote(ordenDeCompraItemRecepcion.getLote());
                    }
                    if(ordenDeCompraItemRecepcion.getRefenciaAdministrativa() != null) {
                        ordenDeCompraItemRecepcionDto.setReferenciaAdministrativa(ordenDeCompraItemRecepcion.getRefenciaAdministrativa());
                    }
                                
                    ordenDeCompraItemsRecepcionDtos.add(ordenDeCompraItemRecepcionDto);

                    cantidadRecepcionadaTotal += ordenDeCompraItemRecepcion.getCantidadRecepcionada();
                }
            }                        

            
            ordenDeCompraItemsDtos.add(ordenDeCompraItemDto);
        }

               
        model.addAttribute("ordenDeCompraNro", ordenDeCompra.getId());
        model.addAttribute("ordenDeCompraRefAdministrativa", refAdministrativa);
        model.addAttribute("fechaHoy", fechaHoy);
        model.addAttribute("fechaEntrega", fechaEntrega);
        model.addAttribute("estado", estado);
        model.addAttribute("proveedorRazonSocial", proveedorRazonSocial);
        model.addAttribute("ordenDeCompraItems", ordenDeCompraItemsDtos);
        model.addAttribute("ordenDeCompraItemsRecepcion", ordenDeCompraItemsRecepcionDtos);        
        
        return "/ordendecompra/ordendecompraprint"; 
    }        

}
