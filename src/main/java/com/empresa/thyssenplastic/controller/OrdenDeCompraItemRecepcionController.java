/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.OrdenDeCompraItemForm;
import com.empresa.thyssenplastic.controller.form.OrdenDeCompraItemRecepcionForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
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
import com.empresa.thyssenplastic.service.OrdenDeCompraService;
import com.empresa.thyssenplastic.service.OrdenDeCompraItemService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.InsumoServiceImpl;
import com.empresa.thyssenplastic.service.impl.MateriaPrimaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeCompraItemRecepcionServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.OrdenDeCompraItemServiceImpl;
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
public class OrdenDeCompraItemRecepcionController {
    

    @RequestMapping(value = "/ordenDeCompraItemRecepcion/{ordenDeCompraItemPk}", method = RequestMethod.GET)   
    public String getHomeOrdenDeCompraItem(@PathVariable String ordenDeCompraItemPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraItemPk == null && ordenDeCompraItemPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: orden de compra inválida");         
            return "/error";                
        }

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }

        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();   
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(Integer.valueOf(ordenDeCompraItemPk));

        if(ordenDeCompraItem == null) {
            model.addAttribute("errorMessage", "Error: item orden de compra no encontrada");         
            return "/error";                
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeCompraItem.getIdOrdenDeCompra()));

        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");         
            return "/error";                
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(ordenDeCompra.getIdProveedor());

        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: orden de compra sin proveedor");         
            return "/error";                
        }
        
        String rol = "";    
        String operacion = "";        
        String displayActionButton = "none";

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiaPrimasModel = materiaPrimaService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());

        if(materiaPrimasModel != null && !materiaPrimasModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiaPrimasModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }

        MateriaPrimaModel materiaPrima = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
            materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());
        }    
        
        InsumoService insumoService = new InsumoServiceImpl();   
        InsumoModel insumo = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
            insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());
        }    
        
        OrdenDeCompraItemRecepcionForm ordenDeCompraItemRecepcionForm = new OrdenDeCompraItemRecepcionForm();
        ordenDeCompraItemRecepcionForm.setPk("-1");
        ordenDeCompraItemRecepcionForm.setAction("add");
        
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String fecha = formato.format(c.getTime());
        
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompra(ordenDeCompra.getId().toString());
        ordenDeCompraItemRecepcionForm.setProveedor(proveedor.getRazonSocial());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompra(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraItemRecepcionForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemRecepcionForm.setEstado(ordenDeCompra.getEstado());
        if(ordenDeCompra.getObservaciones() != null) {
            ordenDeCompraItemRecepcionForm.setObservaciones(ordenDeCompra.getObservaciones());
        }
                              
        ordenDeCompraItemRecepcionForm.setFechaRecepcion(fecha);
        
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompraItem(ordenDeCompraItem.getId().toString());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompraItem(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(materiaPrima != null) {
            ordenDeCompraItemRecepcionForm.setMateriaPrima(materiaPrima.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(materiaPrima.getStock().toString());
        }
        if(insumo != null) {
            ordenDeCompraItemRecepcionForm.setInsumo(insumo.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(insumo.getStock().toString());
        }        
        ordenDeCompraItemRecepcionForm.setCantidad(ordenDeCompraItem.getCantidad().toString());        
        ordenDeCompraItemRecepcionForm.setTipo(ordenDeCompraItem.getTipo());
                
        if(user.getRol() == Utils.ROL_DEPOSITO) {            
            operacion = "recepcion";
            if(ordenDeCompra.getEstado().equalsIgnoreCase("Abierto")) {
                displayActionButton = "block";
            } else {
                displayActionButton = "none";
            }
            rol = "deposito";
        }
        if(user.getRol() == Utils.ROL_OFICINA) {
            operacion = "alta";
            displayActionButton = "none";            
            rol = "oficina";
        }

        ordenDeCompraItemRecepcionForm.setOperacion(operacion);
        
        model.addAttribute("ordenDeCompraItemRecepcionForm", ordenDeCompraItemRecepcionForm);  
        model.addAttribute("titleOrdenDeCompraItemRecepcion", "Nueva Recepción de Item en Order De Compra");  
        model.addAttribute("buttonLabel", "Guardar");
        
        OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl(); 
        List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
                                
        List<OrdenDeCompraItemRecepcionDto> ordenDeCompraItemsRecepcionDtos = new ArrayList<OrdenDeCompraItemRecepcionDto>();
        for(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcion: ordenDeCompraItemsRecepcion) {
            OrdenDeCompraItemRecepcionDto ordenDeCompraItemRecepcionDto = new OrdenDeCompraItemRecepcionDto();
            ordenDeCompraItemRecepcionDto.setPk(ordenDeCompraItemRecepcion.getId().toString());
            ordenDeCompraItemRecepcionDto.setFechaRecepcion(ordenDeCompraItemRecepcion.getFechaRecepcion().toString().replace(".0",""));
            ordenDeCompraItemRecepcionDto.setCantidadRecepcionada(ordenDeCompraItemRecepcion.getCantidadRecepcionada().toString());
            if(ordenDeCompraItemRecepcion.getLote() != null) {
                ordenDeCompraItemRecepcionDto.setLote(ordenDeCompraItemRecepcion.getLote());
            }
            if(ordenDeCompraItemRecepcion.getRefenciaAdministrativa() != null) {
                ordenDeCompraItemRecepcionDto.setReferenciaAdministrativa(ordenDeCompraItemRecepcion.getRefenciaAdministrativa());
            }
                                
            ordenDeCompraItemsRecepcionDtos.add(ordenDeCompraItemRecepcionDto);
        }
        
        Integer cantidadSolicitada = ordenDeCompraItem.getCantidad();
        Integer cantidadRecepcionada = 0;
        if(ordenDeCompraItemsRecepcion != null && !ordenDeCompraItemsRecepcion.isEmpty()) {
            for(OrdenDeCompraItemRecepcionModel recepcion: ordenDeCompraItemsRecepcion){
                cantidadRecepcionada += recepcion.getCantidadRecepcionada();
            }
        }
        Integer maxCantidadARecepcionar = cantidadSolicitada - cantidadRecepcionada;        
        
        String displayButtonCambiarEstadoCompletado = "none";
        
        Boolean itemCompletado = false;
        if(maxCantidadARecepcionar <= 0 && rol.equalsIgnoreCase("deposito") && ordenDeCompra.getEstado().equalsIgnoreCase("Abierto") && !ordenDeCompraItemsRecepcion.isEmpty()) {
            itemCompletado = true;
            displayActionButton = "none";
        }
                
        Boolean ordenEstaCompletada = true;
        List<OrdenDeCompraItemModel> items = ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompra.getId());
        if(!items.isEmpty()) {
            for(OrdenDeCompraItemModel item: items) {
                if(item.getEstaCompletado() == null || !item.getEstaCompletado()) {
                    ordenEstaCompletada = false;
                    break;
                }
            }
        } else {
            ordenEstaCompletada = false;
        }
        
        if(rol.equalsIgnoreCase("deposito") && ordenEstaCompletada && ordenDeCompra.getEstado().equalsIgnoreCase("Abierto")) {
            displayButtonCambiarEstadoCompletado = "block";            
        }
        
        model.addAttribute("tipo", ordenDeCompraItem.getTipo());
        model.addAttribute("viewQr", "false");
        model.addAttribute("itemCompletado", itemCompletado.toString());
        model.addAttribute("maxCantidadARecepcionar", maxCantidadARecepcionar.toString());
        model.addAttribute("ordenDeCompraStatus", ordenDeCompra.getEstado());
        model.addAttribute("displayButtonCambiarEstadoCompletado", displayButtonCambiarEstadoCompletado);
        model.addAttribute("idOrdenDeCompra", ordenDeCompra.getId());        
        model.addAttribute("idOrdenDeCompraItem", ordenDeCompraItem.getId());        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("ordenDeCompraItemsRecepcion", ordenDeCompraItemsRecepcionDtos);        
                
        return "/ordendecompra/ordendecompraitemrecepcion";
    }
    
    @RequestMapping(value = "/ordenDeCompraItemRecepcion/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveOrdenDeCompraItemRecepcion(@ModelAttribute("ordenDeCompraItemRecepcionForm")OrdenDeCompraItemRecepcionForm ordenDeCompraItemRecepcionForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

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
        
        if(ordenDeCompraItemRecepcionForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        String operacion = ordenDeCompraItemRecepcionForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("recepcion"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error Recepcion Item Orden de Compra: operación inválida");
            return modelAndView;                        
        }        
        if(ordenDeCompraItemRecepcionForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("recepcion")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error Recepcion Item Orden de Compra: operación no coincide");
            return modelAndView;                        
        }        
        if(ordenDeCompraItemRecepcionForm.getIdOrdenDeCompra() == null || ordenDeCompraItemRecepcionForm.getIdOrdenDeCompra().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error Recepcion Item Orden de Compra: orden de compra inválida");
            return modelAndView;                                    
        }

        if(ordenDeCompraItemRecepcionForm.getIdOrdenDeCompraItem() == null || ordenDeCompraItemRecepcionForm.getIdOrdenDeCompraItem().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error Recepcion Item Orden de Compra: item orden de compra inválida");
            return modelAndView;                                    
        }

        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();        
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(Integer.valueOf(ordenDeCompraItemRecepcionForm.getIdOrdenDeCompraItem()));

        if(ordenDeCompraItem == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: item de orden de compra no encontrada");
            return modelAndView;                
        }
        
        MateriaPrimaModel materiaPrima = null;
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();        
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {            
            materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());

            if(materiaPrima == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: materia prima no encontrada");
                return modelAndView;                            
            }
        }
        InsumoModel insumo = null;
        InsumoService insumoService = new InsumoServiceImpl();
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {                    
            insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());

            if(insumo == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: insumo no encontrada");
                return modelAndView;                            
            }
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();        
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeCompraItemRecepcionForm.getIdOrdenDeCompra()));

        if(ordenDeCompra == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: orden de compra no encontrada");
            return modelAndView;                
        }
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Abierto")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: no es posible editar un item de una orden de compra en estado "+ordenDeCompra.getEstado());
            return modelAndView;                        
        }            
        
        if(user.getRol() != Utils.ROL_DEPOSITO) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return modelAndView;                
        }
        
        OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = ordenDeCompraItemRecepcionForm.getPk();
            
        OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcionModel = null;
        if(id.equalsIgnoreCase("-1")) {
            ordenDeCompraItemRecepcionModel = new OrdenDeCompraItemRecepcionModel();
            ordenDeCompraItemRecepcionModel.setIdOrdenDeCompraItem(ordenDeCompraItem.getId());
        } else {
            ordenDeCompraItemRecepcionModel = ordenDeCompraItemRecepcionService.getByPk(Integer.valueOf(id));
            if(ordenDeCompraItemRecepcionModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de recepcion Item de Orden de Compra inválido.");
                return modelAndView;                
            } 
            
            Integer cantidadSolicitada = ordenDeCompraItem.getCantidad();
            Integer cantidadRecepcionada = 0;
            List<OrdenDeCompraItemRecepcionModel> recepciones = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
            if(recepciones != null && !recepciones.isEmpty()) {
                for(OrdenDeCompraItemRecepcionModel recepcion: recepciones){
                    cantidadRecepcionada += recepcion.getCantidadRecepcionada();
                }
            }
            /*
            if(cantidadSolicitada < cantidadRecepcionada) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: La cantidad recepcionada no puede superar la cantidad pedida.");
                return modelAndView;                                
            }
            */
        }        
        
        if(ordenDeCompraItemRecepcionForm.getFechaRecepcion() != null && !ordenDeCompraItemRecepcionForm.getFechaRecepcion().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); 
            Date fecha = formato.parse(ordenDeCompraItemRecepcionForm.getFechaRecepcion());
            ordenDeCompraItemRecepcionModel.setFechaRecepcion(fecha);
        } else {
            ordenDeCompraItemRecepcionModel.setFechaRecepcion(null);
        }                
        if(ordenDeCompraItemRecepcionForm.getLote() != null && !ordenDeCompraItemRecepcionForm.getLote().isEmpty()) {
            ordenDeCompraItemRecepcionModel.setLote(ordenDeCompraItemRecepcionForm.getLote());
        } else {
            ordenDeCompraItemRecepcionModel.setLote(null);
        }
        if(ordenDeCompraItemRecepcionForm.getReferenciaAdministrativa() != null && !ordenDeCompraItemRecepcionForm.getReferenciaAdministrativa().isEmpty()) {
            ordenDeCompraItemRecepcionModel.setRefenciaAdministrativa(ordenDeCompraItemRecepcionForm.getReferenciaAdministrativa());
        } else {
            ordenDeCompraItemRecepcionModel.setRefenciaAdministrativa(null);
        }
        if(ordenDeCompraItemRecepcionForm.getCantidadRecepcionada() != null && !ordenDeCompraItemRecepcionForm.getCantidadRecepcionada().isEmpty()) {
            ordenDeCompraItemRecepcionModel.setCantidadRecepcionada(Integer.valueOf(ordenDeCompraItemRecepcionForm.getCantidadRecepcionada()));
        } else {
            ordenDeCompraItemRecepcionModel.setCantidadRecepcionada(null);
        }

        ordenDeCompraItemRecepcionModel.setIdUsuarioRecepcion(Integer.valueOf(Utils.getUserLoggedId(req)));

        if(ordenDeCompraItemRecepcionForm.getAction().equalsIgnoreCase("add") || ordenDeCompraItemRecepcionForm.getAction().equalsIgnoreCase("edit")) {            
            ordenDeCompraItemRecepcionService.save(ordenDeCompraItemRecepcionModel);
            
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
                Integer stockMateriaPrima = materiaPrima.getStock();
                if(stockMateriaPrima == null) {
                    stockMateriaPrima = 0;
                }
                materiaPrima.setStock(stockMateriaPrima + Integer.valueOf(ordenDeCompraItemRecepcionForm.getCantidadRecepcionada()));
                materiaPrimaService.save(materiaPrima);
            }                    
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
                Integer stockInsumo = insumo.getStock();
                if(stockInsumo == null) {
                    stockInsumo = 0;
                }
                insumo.setStock(stockInsumo + Integer.valueOf(ordenDeCompraItemRecepcionForm.getCantidadRecepcionada()));
                insumoService.save(insumo);
            }                                
            Integer cantidadRecepcionadaTotal = 0;
            List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
            for(OrdenDeCompraItemRecepcionModel recepcion: ordenDeCompraItemsRecepcion) {
                cantidadRecepcionadaTotal += recepcion.getCantidadRecepcionada();
            }
            if(ordenDeCompraItem.getCantidad() <= cantidadRecepcionadaTotal) {
                Calendar c = Calendar.getInstance();        
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                String fechaStr = formato.format(c.getTime());
                Date fecha = formato.parse(fechaStr);
                
                ordenDeCompraItem.setFechaCompletado(c.getTime());
                ordenDeCompraItem.setEstaCompletado(Boolean.TRUE);
                
                if(ordenDeCompraItem.getCantidad() < cantidadRecepcionadaTotal) {
                    ordenDeCompraItem.setSuperaCantidad(Boolean.TRUE);
                }
                ordenDeCompraItemService.save(ordenDeCompraItem);                                
            }
        } else {
            if(ordenDeCompraItemRecepcionForm.getAction().equalsIgnoreCase("remove")) {
                if(ordenDeCompraItemRecepcionModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de recepcion ordenDeCompraItem inválido.");
                    return modelAndView;                                    
                }
                
                ordenDeCompraItemRecepcionService.delete(ordenDeCompraItemRecepcionModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/ordenDeCompraItemRecepcion/"+ordenDeCompraItem.getId());

        return modelAndView; 
    }

    @RequestMapping(value = "/ordenDeCompraItemRecepcion/edit/{ordenDeCompraItemRecepcionpk}", method = RequestMethod.GET)
    public String editOrdenDeCompraItem(@PathVariable String ordenDeCompraItemRecepcionpk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraItemRecepcionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItemRecepcion pk inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }

        OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl();           
        OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcion = ordenDeCompraItemRecepcionService.getByPk(Integer.valueOf(ordenDeCompraItemRecepcionpk));

        if(ordenDeCompraItemRecepcion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();           
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(ordenDeCompraItemRecepcion.getIdOrdenDeCompraItem());

        if(ordenDeCompraItem == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(ordenDeCompraItem.getIdOrdenDeCompra());

        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");         
            return "/error";                
        }
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Abierto")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(ordenDeCompra.getIdProveedor());

        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: orden de compra sin proveedor");         
            return "/error";                
        }
        
        String rol = "deposito";
        String operacion = "alta";        
        String displayActionButton = "block";

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiaPrimasModel = materiaPrimaService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());

        if(materiaPrimasModel != null && !materiaPrimasModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiaPrimasModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }
        
        MateriaPrimaModel materiaPrima = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
            materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());
        }    
        
        InsumoService insumoService = new InsumoServiceImpl();   
        InsumoModel insumo = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
            insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());
        }    
        
        OrdenDeCompraItemRecepcionForm ordenDeCompraItemRecepcionForm = new OrdenDeCompraItemRecepcionForm();
        
        ordenDeCompraItemRecepcionForm.setPk(ordenDeCompraItem.getId().toString());
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompra(ordenDeCompraItem.getIdOrdenDeCompra().toString());
        if(ordenDeCompraItem.getIdMateriaPrima() != null) {
            ordenDeCompraItemRecepcionForm.setIdMateriaPrima(ordenDeCompraItem.getIdMateriaPrima().toString());
        }
        if(ordenDeCompraItem.getIdInsumo() != null) {
            ordenDeCompraItemRecepcionForm.setIdInsumo(ordenDeCompraItem.getIdInsumo().toString());
        }        
        ordenDeCompraItemRecepcionForm.setIdUsuario(ordenDeCompraItem.getIdUsuario().toString());
        ordenDeCompraItemRecepcionForm.setProveedor(proveedor.getRazonSocial());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompra(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraItemRecepcionForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemRecepcionForm.setEstado(ordenDeCompra.getEstado());
        if(ordenDeCompra.getObservaciones() != null) {
            ordenDeCompraItemRecepcionForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompraItem.getCantidad() != null) {
            ordenDeCompraItemRecepcionForm.setCantidad(ordenDeCompraItem.getCantidad().toString());
        }        
        if(ordenDeCompraItem.getFechaCompletado() != null) {
            ordenDeCompraItemRecepcionForm.setFechaCompletado(ordenDeCompraItem.getFechaCompletado().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompraItem.getEstaCompletado() != null) {
            ordenDeCompraItemRecepcionForm.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
        }        
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompraItem(ordenDeCompraItem.getId().toString());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompraItem(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(materiaPrima != null) {
            ordenDeCompraItemRecepcionForm.setMateriaPrima(materiaPrima.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(materiaPrima.getStock().toString());
        }
        if(insumo != null) {
            ordenDeCompraItemRecepcionForm.setInsumo(insumo.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(insumo.getStock().toString());
        }        
        ordenDeCompraItemRecepcionForm.setCantidad(ordenDeCompraItem.getCantidad().toString());     
        
        ordenDeCompraItemRecepcionForm.setOperacion(operacion);
        
        ordenDeCompraItemRecepcionForm.setAction("edit");
        model.addAttribute("ordenDeCompraItemRecepcionForm", ordenDeCompraItemRecepcionForm);  
        model.addAttribute("titleOrdenDeCompraItemRecepcion", "Editar Recepcion Item de Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeCompraItemName", ordenDeCompraItemRecepcion.getId() + " - " + ordenDeCompraItemRecepcion.getIdOrdenDeCompraItem());        
        
        List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
                                
        List<OrdenDeCompraItemRecepcionDto> ordenDeCompraItemsRecepcionDtos = new ArrayList<OrdenDeCompraItemRecepcionDto>();
        for(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcionModel: ordenDeCompraItemsRecepcion) {
            OrdenDeCompraItemRecepcionDto ordenDeCompraItemRecepcionDto = new OrdenDeCompraItemRecepcionDto();
            ordenDeCompraItemRecepcionDto.setPk(ordenDeCompraItemRecepcionModel.getId().toString());
            ordenDeCompraItemRecepcionDto.setFechaRecepcion(ordenDeCompraItemRecepcionModel.getFechaRecepcion().toString().replace(".0",""));
            ordenDeCompraItemRecepcionDto.setCantidadRecepcionada(ordenDeCompraItemRecepcionModel.getCantidadRecepcionada().toString());
            if(ordenDeCompraItemRecepcionModel.getLote() != null) {
                ordenDeCompraItemRecepcionDto.setLote(ordenDeCompraItemRecepcionModel.getLote());
            }
            if(ordenDeCompraItemRecepcionModel.getRefenciaAdministrativa() != null) {
                ordenDeCompraItemRecepcionDto.setReferenciaAdministrativa(ordenDeCompraItemRecepcionModel.getRefenciaAdministrativa());
            }
                                
            ordenDeCompraItemsRecepcionDtos.add(ordenDeCompraItemRecepcionDto);
        }

        model.addAttribute("tipo", ordenDeCompraItem.getTipo());
        model.addAttribute("viewQr", "false");
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");        
        model.addAttribute("idOrdenDeCompra", ordenDeCompra.getId());
        model.addAttribute("idOrdenDeCompraItem", ordenDeCompraItem.getId());
        model.addAttribute("displayUser", "none");                
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("ordenDeCompraItemsRecepcion", ordenDeCompraItemsRecepcionDtos);        
        
        return "/ordendecompra/ordendecompraitemrecepcion";        
    }
    
    @RequestMapping(value = "/ordenDeCompraItemRecepcion/remove/{ordenDeCompraItempk}", method = RequestMethod.GET)
    public String removeOrdenDeCompraItemRecepcion(@PathVariable String ordenDeCompraItemRecepcionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraItemRecepcionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItemRecepcion pk inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }

        OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl();           
        OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcion = ordenDeCompraItemRecepcionService.getByPk(Integer.valueOf(ordenDeCompraItemRecepcionpk));

        if(ordenDeCompraItemRecepcion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();           
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(ordenDeCompraItemRecepcion.getIdOrdenDeCompraItem());

        if(ordenDeCompraItem == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(ordenDeCompraItem.getIdOrdenDeCompra());

        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");         
            return "/error";                
        }
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Abierto")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(ordenDeCompra.getIdProveedor());

        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: orden de compra sin proveedor");         
            return "/error";                
        }
        
        String rol = "deposito";
        String operacion = "alta";        
        String displayActionButton = "block";

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiaPrimasModel = materiaPrimaService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());

        if(materiaPrimasModel != null && !materiaPrimasModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiaPrimasModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }
        MateriaPrimaModel materiaPrima = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
            materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());
        }    
        
        InsumoService insumoService = new InsumoServiceImpl();   
        InsumoModel insumo = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
            insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());
        }    
        
        OrdenDeCompraItemRecepcionForm ordenDeCompraItemRecepcionForm = new OrdenDeCompraItemRecepcionForm();
        
        ordenDeCompraItemRecepcionForm.setPk(ordenDeCompraItem.getId().toString());
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompra(ordenDeCompraItem.getIdOrdenDeCompra().toString());
        if(ordenDeCompraItem.getIdMateriaPrima() != null) {
            ordenDeCompraItemRecepcionForm.setIdMateriaPrima(ordenDeCompraItem.getIdMateriaPrima().toString());
        }
        if(ordenDeCompraItem.getIdInsumo() != null) {
            ordenDeCompraItemRecepcionForm.setIdInsumo(ordenDeCompraItem.getIdInsumo().toString());
        }        
        ordenDeCompraItemRecepcionForm.setIdUsuario(ordenDeCompraItem.getIdUsuario().toString());
        ordenDeCompraItemRecepcionForm.setProveedor(proveedor.getRazonSocial());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompra(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraItemRecepcionForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemRecepcionForm.setEstado(ordenDeCompra.getEstado());
        if(ordenDeCompra.getObservaciones() != null) {
            ordenDeCompraItemRecepcionForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompraItem.getCantidad() != null) {
            ordenDeCompraItemRecepcionForm.setCantidad(ordenDeCompraItem.getCantidad().toString());
        }        
        if(ordenDeCompraItem.getFechaCompletado() != null) {
            ordenDeCompraItemRecepcionForm.setFechaCompletado(ordenDeCompraItem.getFechaCompletado().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompraItem.getEstaCompletado() != null) {
            ordenDeCompraItemRecepcionForm.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
        }        
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompraItem(ordenDeCompraItem.getId().toString());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompraItem(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(materiaPrima != null) {
            ordenDeCompraItemRecepcionForm.setMateriaPrima(materiaPrima.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(materiaPrima.getStock().toString());
        }
        if(insumo != null) {
            ordenDeCompraItemRecepcionForm.setInsumo(insumo.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(insumo.getStock().toString());
        }        
        ordenDeCompraItemRecepcionForm.setCantidad(ordenDeCompraItem.getCantidad().toString());        
        
        ordenDeCompraItemRecepcionForm.setOperacion(operacion);
        
        ordenDeCompraItemRecepcionForm.setAction("remove");
        model.addAttribute("ordenDeCompraItemRecepcionForm", ordenDeCompraItemRecepcionForm);  
        model.addAttribute("titleOrdenDeCompraItemRecepcion", "Eliminar Recepcion Item de Orden de Compra");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("ordenDeCompraItemName", ordenDeCompraItemRecepcion.getId() + " - " + ordenDeCompraItemRecepcion.getIdOrdenDeCompraItem());        
        
        List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
                                
        List<OrdenDeCompraItemRecepcionDto> ordenDeCompraItemsRecepcionDtos = new ArrayList<OrdenDeCompraItemRecepcionDto>();
        for(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcionModel: ordenDeCompraItemsRecepcion) {
            OrdenDeCompraItemRecepcionDto ordenDeCompraItemRecepcionDto = new OrdenDeCompraItemRecepcionDto();
            ordenDeCompraItemRecepcionDto.setPk(ordenDeCompraItemRecepcionModel.getId().toString());
            ordenDeCompraItemRecepcionDto.setFechaRecepcion(ordenDeCompraItemRecepcionModel.getFechaRecepcion().toString().replace(".0",""));
            ordenDeCompraItemRecepcionDto.setCantidadRecepcionada(ordenDeCompraItemRecepcionModel.getCantidadRecepcionada().toString());
            if(ordenDeCompraItemRecepcionModel.getLote() != null) {
                ordenDeCompraItemRecepcionDto.setLote(ordenDeCompraItemRecepcionModel.getLote());
            }
            if(ordenDeCompraItemRecepcionModel.getRefenciaAdministrativa() != null) {
                ordenDeCompraItemRecepcionDto.setReferenciaAdministrativa(ordenDeCompraItemRecepcionModel.getRefenciaAdministrativa());
            }
                                
            ordenDeCompraItemsRecepcionDtos.add(ordenDeCompraItemRecepcionDto);
        }

        model.addAttribute("tipo", ordenDeCompraItem.getTipo());
        model.addAttribute("viewQr", "false");
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");        
        model.addAttribute("idOrdenDeCompra", ordenDeCompra.getId());
        model.addAttribute("idOrdenDeCompraItem", ordenDeCompraItem.getId());
        model.addAttribute("displayUser", "none");                
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("ordenDeCompraItemsRecepcion", ordenDeCompraItemsRecepcionDtos);        
        
        return "/ordendecompra/ordendecompraitemrecepcion";  
             
    }    
    
    @RequestMapping(value = "/ordenDeCompraItemRecepcion/view/{ordenDeCompraItemRecepcionpk}", method = RequestMethod.GET)
    public String viewOrdenDeCompraItemRecepcion(@PathVariable String ordenDeCompraItemRecepcionpk, HttpServletRequest req, ModelMap model) throws Exception {
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraItemRecepcionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItemRecepcion pk inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }

        OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl();           
        OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcion = ordenDeCompraItemRecepcionService.getByPk(Integer.valueOf(ordenDeCompraItemRecepcionpk));

        if(ordenDeCompraItemRecepcion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();           
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(ordenDeCompraItemRecepcion.getIdOrdenDeCompraItem());

        if(ordenDeCompraItem == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(ordenDeCompraItem.getIdOrdenDeCompra());

        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");         
            return "/error";                
        }
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Abierto")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(ordenDeCompra.getIdProveedor());

        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: orden de compra sin proveedor");         
            return "/error";                
        }
        
        String rol = "deposito";
        String operacion = "alta";        
        String displayActionButton = "block";

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiaPrimasModel = materiaPrimaService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());

        if(materiaPrimasModel != null && !materiaPrimasModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiaPrimasModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }
        MateriaPrimaModel materiaPrima = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
            materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());
        }    
        
        InsumoService insumoService = new InsumoServiceImpl();   
        InsumoModel insumo = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
            insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());
        }    
                
        OrdenDeCompraItemRecepcionForm ordenDeCompraItemRecepcionForm = new OrdenDeCompraItemRecepcionForm();
        
        ordenDeCompraItemRecepcionForm.setPk(ordenDeCompraItem.getId().toString());
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompra(ordenDeCompraItem.getIdOrdenDeCompra().toString());
        if(ordenDeCompraItem.getIdMateriaPrima() != null) {
            ordenDeCompraItemRecepcionForm.setIdMateriaPrima(ordenDeCompraItem.getIdMateriaPrima().toString());
        }
        if(ordenDeCompraItem.getIdInsumo() != null) {
            ordenDeCompraItemRecepcionForm.setIdInsumo(ordenDeCompraItem.getIdInsumo().toString());
        }        
        ordenDeCompraItemRecepcionForm.setIdUsuario(ordenDeCompraItem.getIdUsuario().toString());
        ordenDeCompraItemRecepcionForm.setProveedor(proveedor.getRazonSocial());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompra(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraItemRecepcionForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemRecepcionForm.setEstado(ordenDeCompra.getEstado());
        if(ordenDeCompra.getObservaciones() != null) {
            ordenDeCompraItemRecepcionForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompraItem.getCantidad() != null) {
            ordenDeCompraItemRecepcionForm.setCantidad(ordenDeCompraItem.getCantidad().toString());
        }        
        if(ordenDeCompraItem.getFechaCompletado() != null) {
            ordenDeCompraItemRecepcionForm.setFechaCompletado(ordenDeCompraItem.getFechaCompletado().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompraItem.getEstaCompletado() != null) {
            ordenDeCompraItemRecepcionForm.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
        }        
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompraItem(ordenDeCompraItem.getId().toString());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompraItem(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(materiaPrima != null) {
            ordenDeCompraItemRecepcionForm.setMateriaPrima(materiaPrima.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(materiaPrima.getStock().toString());
        }
        if(insumo != null) {
            ordenDeCompraItemRecepcionForm.setInsumo(insumo.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(insumo.getStock().toString());
        }        
        ordenDeCompraItemRecepcionForm.setCantidad(ordenDeCompraItem.getCantidad().toString());        
        
        ordenDeCompraItemRecepcionForm.setOperacion(operacion);
        
        ordenDeCompraItemRecepcionForm.setFechaRecepcion(ordenDeCompraItemRecepcion.getFechaRecepcion().toString().replace(".0",""));
        ordenDeCompraItemRecepcionForm.setCantidadRecepcionada(ordenDeCompraItemRecepcion.getCantidadRecepcionada().toString());
        if(ordenDeCompraItemRecepcion.getLote() != null) {
            ordenDeCompraItemRecepcionForm.setLote(ordenDeCompraItemRecepcion.getLote());
        }
        if(ordenDeCompraItemRecepcion.getRefenciaAdministrativa() != null) {
            ordenDeCompraItemRecepcionForm.setReferenciaAdministrativa(ordenDeCompraItemRecepcion.getRefenciaAdministrativa());
        }
        
        ordenDeCompraItemRecepcionForm.setAction("view");
        model.addAttribute("ordenDeCompraItemRecepcionForm", ordenDeCompraItemRecepcionForm);  
        model.addAttribute("titleOrdenDeCompraItemRecepcion", "Ver Recepcion Item de Orden de Compra");
        model.addAttribute("buttonLabel", "Ver");
        model.addAttribute("ordenDeCompraItemName", ordenDeCompraItemRecepcion.getId() + " - " + ordenDeCompraItemRecepcion.getIdOrdenDeCompraItem());        
        
        List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
                                
        List<OrdenDeCompraItemRecepcionDto> ordenDeCompraItemsRecepcionDtos = new ArrayList<OrdenDeCompraItemRecepcionDto>();
        for(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcionModel: ordenDeCompraItemsRecepcion) {
            OrdenDeCompraItemRecepcionDto ordenDeCompraItemRecepcionDto = new OrdenDeCompraItemRecepcionDto();
            ordenDeCompraItemRecepcionDto.setPk(ordenDeCompraItemRecepcionModel.getId().toString());
            ordenDeCompraItemRecepcionDto.setFechaRecepcion(ordenDeCompraItemRecepcionModel.getFechaRecepcion().toString().replace(".0",""));
            ordenDeCompraItemRecepcionDto.setCantidadRecepcionada(ordenDeCompraItemRecepcionModel.getCantidadRecepcionada().toString());
            if(ordenDeCompraItemRecepcionModel.getLote() != null) {
                ordenDeCompraItemRecepcionDto.setLote(ordenDeCompraItemRecepcionModel.getLote());
            }
            if(ordenDeCompraItemRecepcionModel.getRefenciaAdministrativa() != null) {
                ordenDeCompraItemRecepcionDto.setReferenciaAdministrativa(ordenDeCompraItemRecepcionModel.getRefenciaAdministrativa());
            }
                                
            ordenDeCompraItemsRecepcionDtos.add(ordenDeCompraItemRecepcionDto);
        }

        model.addAttribute("tipo", ordenDeCompraItem.getTipo());
        model.addAttribute("viewQr", "false");
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");        
        model.addAttribute("idOrdenDeCompra", ordenDeCompra.getId());
        model.addAttribute("idOrdenDeCompraItem", ordenDeCompraItem.getId());
        model.addAttribute("displayUser", "none");                
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", "none");
        model.addAttribute("operacion", operacion);        
        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("ordenDeCompraItemsRecepcion", ordenDeCompraItemsRecepcionDtos);        

        return "/ordendecompra/ordendecompraitemrecepcion"; 
    }
    
    @RequestMapping(value = "/ordenDeCompraItemRecepcion/viewQr/{ordenDeCompraItemRecepcionpk}", method = RequestMethod.GET)
    public String viewOrdenDeCompraItemRecepcionQr(@PathVariable String ordenDeCompraItemRecepcionpk, HttpServletRequest req, ModelMap model) throws Exception {
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraItemRecepcionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItemRecepcion pk inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_DEPOSITO && user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }

        OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl();           
        OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcion = ordenDeCompraItemRecepcionService.getByPk(Integer.valueOf(ordenDeCompraItemRecepcionpk));

        if(ordenDeCompraItemRecepcion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();           
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(ordenDeCompraItemRecepcion.getIdOrdenDeCompraItem());

        if(ordenDeCompraItem == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(ordenDeCompraItem.getIdOrdenDeCompra());

        if(ordenDeCompra == null) {
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");         
            return "/error";                
        }
        
        if(ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(ordenDeCompra.getIdProveedor());

        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: orden de compra sin proveedor");         
            return "/error";                
        }
        
        String rol = "undefined";        
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            rol = "deposito";        
        }
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";        
        }
        
        String operacion = "viewqr";        
        String displayActionButton = "block";

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiaPrimasModel = materiaPrimaService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());

        if(materiaPrimasModel != null && !materiaPrimasModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiaPrimasModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }
        MateriaPrimaModel materiaPrima = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
            materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());
        }    
        
        InsumoService insumoService = new InsumoServiceImpl();   
        InsumoModel insumo = null;
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
            insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());
        }                    
        OrdenDeCompraItemRecepcionForm ordenDeCompraItemRecepcionForm = new OrdenDeCompraItemRecepcionForm();
        
        ordenDeCompraItemRecepcionForm.setPk(ordenDeCompraItem.getId().toString());
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompra(ordenDeCompraItem.getIdOrdenDeCompra().toString());
        if(ordenDeCompraItem.getIdMateriaPrima() != null) {
            ordenDeCompraItemRecepcionForm.setIdMateriaPrima(ordenDeCompraItem.getIdMateriaPrima().toString());
        }
        if(ordenDeCompraItem.getIdInsumo() != null) {
            ordenDeCompraItemRecepcionForm.setIdInsumo(ordenDeCompraItem.getIdInsumo().toString());
        }                
        ordenDeCompraItemRecepcionForm.setIdUsuario(ordenDeCompraItem.getIdUsuario().toString());
        ordenDeCompraItemRecepcionForm.setProveedor(proveedor.getRazonSocial());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompra(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraItemRecepcionForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemRecepcionForm.setEstado(ordenDeCompra.getEstado());
        if(ordenDeCompra.getObservaciones() != null) {
            ordenDeCompraItemRecepcionForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompraItem.getCantidad() != null) {
            ordenDeCompraItemRecepcionForm.setCantidad(ordenDeCompraItem.getCantidad().toString());
        }        
        if(ordenDeCompraItem.getFechaCompletado() != null) {
            ordenDeCompraItemRecepcionForm.setFechaCompletado(ordenDeCompraItem.getFechaCompletado().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompraItem.getEstaCompletado() != null) {
            ordenDeCompraItemRecepcionForm.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
        }        
        ordenDeCompraItemRecepcionForm.setIdOrdenDeCompraItem(ordenDeCompraItem.getId().toString());
        ordenDeCompraItemRecepcionForm.setFechaAltaOrdenDeCompraItem(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(materiaPrima != null) {
            ordenDeCompraItemRecepcionForm.setMateriaPrima(materiaPrima.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(materiaPrima.getStock().toString());
        }
        if(insumo != null) {
            ordenDeCompraItemRecepcionForm.setInsumo(insumo.getDescripcion());
            ordenDeCompraItemRecepcionForm.setStock(insumo.getStock().toString());
        }        
        ordenDeCompraItemRecepcionForm.setCantidad(ordenDeCompraItem.getCantidad().toString());
        
        ordenDeCompraItemRecepcionForm.setOperacion(operacion);
        
        ordenDeCompraItemRecepcionForm.setFechaRecepcion(ordenDeCompraItemRecepcion.getFechaRecepcion().toString().replace(".0",""));
        ordenDeCompraItemRecepcionForm.setCantidadRecepcionada(ordenDeCompraItemRecepcion.getCantidadRecepcionada().toString());
        if(ordenDeCompraItemRecepcion.getLote() != null) {
            ordenDeCompraItemRecepcionForm.setLote(ordenDeCompraItemRecepcion.getLote());
        }
        if(ordenDeCompraItemRecepcion.getRefenciaAdministrativa() != null) {
            ordenDeCompraItemRecepcionForm.setReferenciaAdministrativa(ordenDeCompraItemRecepcion.getRefenciaAdministrativa());
        }
        
        ordenDeCompraItemRecepcionForm.setAction("view");
        model.addAttribute("ordenDeCompraItemRecepcionForm", ordenDeCompraItemRecepcionForm);  
        model.addAttribute("titleOrdenDeCompraItemRecepcion", "Ver Recepcion Item de Orden de Compra QR");
        model.addAttribute("buttonLabel", "Ver");
        model.addAttribute("ordenDeCompraItemName", ordenDeCompraItemRecepcion.getId() + " - " + ordenDeCompraItemRecepcion.getIdOrdenDeCompraItem());        
        
        List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
                                
        List<OrdenDeCompraItemRecepcionDto> ordenDeCompraItemsRecepcionDtos = new ArrayList<OrdenDeCompraItemRecepcionDto>();
        for(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcionModel: ordenDeCompraItemsRecepcion) {
            OrdenDeCompraItemRecepcionDto ordenDeCompraItemRecepcionDto = new OrdenDeCompraItemRecepcionDto();
            ordenDeCompraItemRecepcionDto.setPk(ordenDeCompraItemRecepcionModel.getId().toString());
            ordenDeCompraItemRecepcionDto.setFechaRecepcion(ordenDeCompraItemRecepcionModel.getFechaRecepcion().toString().replace(".0",""));
            ordenDeCompraItemRecepcionDto.setCantidadRecepcionada(ordenDeCompraItemRecepcionModel.getCantidadRecepcionada().toString());
            if(ordenDeCompraItemRecepcionModel.getLote() != null) {
                ordenDeCompraItemRecepcionDto.setLote(ordenDeCompraItemRecepcionModel.getLote());
            }
            if(ordenDeCompraItemRecepcionModel.getRefenciaAdministrativa() != null) {
                ordenDeCompraItemRecepcionDto.setReferenciaAdministrativa(ordenDeCompraItemRecepcionModel.getRefenciaAdministrativa());
            }
                                
            ordenDeCompraItemsRecepcionDtos.add(ordenDeCompraItemRecepcionDto);
        }

        model.addAttribute("tipo", ordenDeCompraItem.getTipo());
        model.addAttribute("viewQr", "true");
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");        
        model.addAttribute("idOrdenDeCompra", ordenDeCompra.getId());
        model.addAttribute("idOrdenDeCompraItem", ordenDeCompraItem.getId());
        model.addAttribute("displayUser", "none");                
        model.addAttribute("rol", rol);
        model.addAttribute("action", "viewqr");
        model.addAttribute("displayActionButton", "none");
        model.addAttribute("operacion", operacion);        
        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("ordenDeCompraItemsRecepcion", ordenDeCompraItemsRecepcionDtos);        

        return "/ordendecompra/ordendecompraitemrecepcion"; 
    }
    
    @RequestMapping(value = "/ordenDeCompraItemRecepcion/print/{ordenDeCompraItemRecepcionpk}", method = RequestMethod.GET)
    public String printOrdenDeCompraItemRecepcion(@PathVariable String ordenDeCompraItemRecepcionpk, HttpServletRequest req, ModelMap model) throws Exception {
        
        String codigo = "Sin infirmación";
        String descripcion = "Sin infirmación";
        String fechaActual = "Sin infirmación";
        String fechaLote = "Sin infirmación";
        String fechaRecepcion = "Sin infirmación";
        String lote = "Sin infirmación";
        String materiaPrimaStr = "Sin infirmación";
        String tipo = "Sin infirmación";
        String url = "http://localhost:8080/thyssenplastic/ordenDeCompraItemRecepcion/xxxx";
        
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        fechaActual = formato.format(c.getTime());

        if(ordenDeCompraItemRecepcionpk != null && !ordenDeCompraItemRecepcionpk.isEmpty()) {
            OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl();        
            OrdenDeCompraItemRecepcionModel recepcion = ordenDeCompraItemRecepcionService.getByPk(Integer.valueOf(ordenDeCompraItemRecepcionpk));

            if(recepcion != null) {                
                fechaRecepcion = formato.format(recepcion.getFechaRecepcion());
                /*
                if(recepcion.getLote() != null) {
                    lote = recepcion.getLote();
                }
                */

                OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();        
                OrdenDeCompraItemModel item = ordenDeCompraItemService.getByPk(recepcion.getIdOrdenDeCompraItem());

                if(item != null) {
                    if(item.getTipo().equalsIgnoreCase("materiaPrima")) {
                        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();        
                        MateriaPrimaModel materiaPrima = materiaPrimaService.getByPk(item.getIdMateriaPrima());
                        if(materiaPrima != null) {
                            //materiaPrimaStr = materiaPrima.getId() + " - " + materiaPrima.getDescripcion();
                            materiaPrimaStr = materiaPrima.getDescripcion();
                            descripcion = materiaPrima.getDescripcion();
                            codigo = "M"+materiaPrima.getId().toString();
                                    
                            url = "http://localhost:8080/thyssenplastic/ordenDeCompraItemRecepcion/viewQr/"+ordenDeCompraItemRecepcionpk;
                        }
                        tipo = "MATERIA PRIMA";
                    }
                    if(item.getTipo().equalsIgnoreCase("insumo")) {
                        InsumoService insumoService = new InsumoServiceImpl();        
                        InsumoModel insumo = insumoService.getByPk(item.getIdInsumo());
                        if(insumo != null) {
                            //materiaPrimaStr = insumo.getId() + " - " + insumo.getDescripcion();
                            materiaPrimaStr = insumo.getDescripcion();
                            descripcion = insumo.getDescripcion();
                            codigo = "I"+insumo.getId().toString();
                            
                            url = "http://localhost:8080/thyssenplastic/ordenDeCompraItemRecepcion/viewQr/"+ordenDeCompraItemRecepcionpk;
                        }
                        tipo = "MATERIA INSUMO";
                    }                    
                }   
                
                OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();        
                OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(item.getIdOrdenDeCompra());
                if(ordenDeCompra != null) {
                    fechaLote = formato.format(ordenDeCompra.getFechaAltaImpresion());
                    lote = "L"+ordenDeCompra.getId();
                }
            } 
        } 
        
        model.addAttribute("codigo", codigo);        
        model.addAttribute("fechaActual", fechaActual);
        model.addAttribute("tipo", tipo);
        model.addAttribute("lote", lote);
        model.addAttribute("fechaLote", fechaLote);
        model.addAttribute("fechaRecepcion", fechaRecepcion);
        model.addAttribute("lote", lote);
        model.addAttribute("materiaPrima", materiaPrimaStr);                
        model.addAttribute("descripcion", descripcion);                
        model.addAttribute("url", url);
        
        return "/ordendecompra/ordendecompraitemrecepcionprint"; 
    }    
    
    @RequestMapping(value = "/ordenDeCompraItemRecepcion/setStatusCompletedOrdenCompra/{ordenDeCompraPk}/ordenDeCompraItem/{ordenDeCompraItemPk}", method = RequestMethod.GET)
    public String setStatusCompletedOrdenCompra(@PathVariable String ordenDeCompraPk, @PathVariable String ordenDeCompraItemPk, HttpServletRequest req, ModelMap model) throws Exception {

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

        if(ordenDeCompraItemPk == null || ordenDeCompraItemPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido");         
            return "/error";                
        }
                        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();        
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeCompraPk));

        if(ordenDeCompra == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Abierto")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeCompra.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_DEPOSITO) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();        
        List<OrdenDeCompraItemModel> items = ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompra.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        Boolean ordenEstaCompletada = true;
        if(!items.isEmpty()) {
            for(OrdenDeCompraItemModel item: items) {
                if(item.getEstaCompletado() == null || !item.getEstaCompletado()) {
                    ordenEstaCompletada = false;
                    break;
                }
            }
        }

        if(!ordenEstaCompletada) {
            model.addAttribute("errorMessage", "Error: no es posible completar una orden a abierta cuando tiene items sin completar");
            return "/error";                          
        }
        
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(Integer.valueOf(ordenDeCompraItemPk));
        if(ordenDeCompraItem == null) {
            model.addAttribute("errorMessage", "Error: ordern de compra item no encontrada");
            return "/error";                                      
        }
        
        Boolean coindiceOrdenDeCompraItem = false;
        if(!items.isEmpty()) {
            for(OrdenDeCompraItemModel item: items) {
                if(item.getId() == Integer.valueOf(ordenDeCompraItemPk)) {
                    coindiceOrdenDeCompraItem = true;
                    break;
                }
            }
        }
        if(!coindiceOrdenDeCompraItem) {
            model.addAttribute("errorMessage", "Error: no coincide ordern de compra item");
            return "/error";                                      
        }

        ordenDeCompra.setEstado("Completado");
        ordenDeCompraService.save(ordenDeCompra);
        
        return "redirect:/ordenDeCompraItemRecepcion/"+ordenDeCompraItem.getId();                         
        
    }   
    
}
