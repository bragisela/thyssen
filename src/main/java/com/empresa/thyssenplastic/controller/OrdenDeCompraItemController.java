/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.OrdenDeCompraItemForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.OrdenDeCompraItemDto;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.InsumoModel;
import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemModel;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemRecepcionModel;
import com.empresa.thyssenplastic.model.OrdenDeCompraModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.InsumoService;
import com.empresa.thyssenplastic.service.MateriaPrimaService;
import com.empresa.thyssenplastic.service.OrdenDeCompraItemRecepcionService;
import com.empresa.thyssenplastic.service.OrdenDeCompraService;
import com.empresa.thyssenplastic.service.OrdenDeCompraItemService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
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
public class OrdenDeCompraItemController {
    

    @RequestMapping(value = "/ordenDeCompraItem/{ordenDeCompraPk}", method = RequestMethod.GET)   
    public String getHomeOrdenDeCompraItem(@PathVariable String ordenDeCompraPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraPk == null && ordenDeCompraPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: orden de compra inválida");         
            return "/error";                
        }

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();   
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeCompraPk));

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
        String referenciaAdministrativa = "";
                
        OrdenDeCompraItemForm ordenDeCompraItemForm = new OrdenDeCompraItemForm();
        ordenDeCompraItemForm.setPk("-1");
        ordenDeCompraItemForm.setAction("add");
        
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        ordenDeCompraItemForm.setFechaAlta(fecha);
        ordenDeCompraItemForm.setIdOrdenDeCompra(ordenDeCompraPk);
        ordenDeCompraItemForm.setProveedor(proveedor.getRazonSocial());
        ordenDeCompraItemForm.setFechaAltaOrdenDeCompra(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeCompra.getFechaEntrega() != null) {
            ordenDeCompraItemForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraItemForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemForm.setEstado(ordenDeCompra.getEstado());
        if(ordenDeCompra.getObservaciones() != null) {
            ordenDeCompraItemForm.setObservaciones(ordenDeCompra.getObservaciones());
        }
        
        if(user.getRol() == Utils.ROL_OFICINA) {            
            operacion = "alta";
            if(ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo")) {
                displayActionButton = "block";
            } else {
                displayActionButton = "none";
            }
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            operacion = "recepcion";
            displayActionButton = "none";            
            rol = "deposito";
        }

        ordenDeCompraItemForm.setOperacion(operacion);
        
        model.addAttribute("ordenDeCompraItemForm", ordenDeCompraItemForm);  
        model.addAttribute("titleOrdenDeCompraItem", "Nueva Item en Order De Compra");  
        model.addAttribute("buttonLabel", "Guardar");
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();   
        List<OrdenDeCompraItemModel> ordenDeCompraItems = ordenDeCompraItemService.getAllByOrdenDeCompra(Integer.valueOf(ordenDeCompraPk));
        
        TipoService tipoService = new TipoServiceImpl();
        
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiaPrimasModel = materiaPrimaService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());

        if(materiaPrimasModel != null && !materiaPrimasModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiaPrimasModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }

        InsumoService insumoService = new InsumoServiceImpl();   
        Map<String,String> insumos = new LinkedHashMap<String,String>();
        List<InsumoModel> insumosModel = insumoService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());
        
        if(insumosModel != null && !insumosModel.isEmpty()){
            for(InsumoModel insumoModel :insumosModel) {
                insumos.put(insumoModel.getId().toString(), insumoModel.getDescripcion());
            }
        }
        
        List<OrdenDeCompraItemDto> ordenDeCompraItemsDtos = new ArrayList<OrdenDeCompraItemDto>();
        for(OrdenDeCompraItemModel ordenDeCompraItem: ordenDeCompraItems) {
            OrdenDeCompraItemDto ordenDeCompraItemDto = new OrdenDeCompraItemDto();
            ordenDeCompraItemDto.setPk(ordenDeCompraItem.getId().toString());
            ordenDeCompraItemDto.setFechaAlta(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeCompraItemDto.setCantidadSolicitada(ordenDeCompraItem.getCantidad().toString());

            OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl(); 
            Integer cantidadRecepcionadaTotal = 0;
            List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
            if(ordenDeCompraItemsRecepcion != null && !ordenDeCompraItemsRecepcion.isEmpty()) {
                for(OrdenDeCompraItemRecepcionModel recepcion: ordenDeCompraItemsRecepcion) {
                    cantidadRecepcionadaTotal += recepcion.getCantidadRecepcionada();
                }
            }                        
            
            if(ordenDeCompra.getReferenciaAdministrativa() != null) {
                
                ordenDeCompraItemDto.setReferenciaAdministrativa( ordenDeCompra.getReferenciaAdministrativa());
            }
            
            ordenDeCompraItemDto.setCantidadRecepcionada(cantidadRecepcionadaTotal.toString());            

            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
                MateriaPrimaModel materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());            
                TipoModel tipo = tipoService.getByPk(materiaPrima.getIdTipo());
                ordenDeCompraItemDto.setMateriaPrima(materiaPrima.getDescripcion());
                ordenDeCompraItemDto.setTipo(tipo.getValor());
                ordenDeCompraItemDto.setStock(materiaPrima.getStock().toString());            
            }                        
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
                InsumoModel insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());                            
                ordenDeCompraItemDto.setMateriaPrima(insumo.getDescripcion());                
                ordenDeCompraItemDto.setStock(insumo.getStock().toString());            
            }                                    
            if(ordenDeCompraItem.getEstaCompletado() == null) {
                ordenDeCompraItemDto.setEstaCompletado("0");
            } else {
                ordenDeCompraItemDto.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
            }
                    
            ordenDeCompraItemsDtos.add(ordenDeCompraItemDto);
        }
               
        String displayButtonCambiarEstadoAbierto = "none";
        if(rol.equalsIgnoreCase("oficina") && ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo") && ordenDeCompraItems != null && !ordenDeCompraItems.isEmpty()) {
            displayButtonCambiarEstadoAbierto = "block";
        }
                
        String displayButtonCambiarEstadoCompletado = "none";        
        Boolean ordenEstaCompletada = true;
        List<OrdenDeCompraItemModel> items =ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompra.getId());
        /*
        if(!items.isEmpty()) {
            for(OrdenDeCompraItemModel item: items) {
                if(item.getEstaCompletado() == null || !item.getEstaCompletado()) {
                    ordenEstaCompletada = false;
                    break;
                }
            }
        }
        */
        if(items == null || items.isEmpty()) {
            ordenEstaCompletada = false;
        }
        if(rol.equalsIgnoreCase("deposito") && ordenEstaCompletada && ordenDeCompra.getEstado().equalsIgnoreCase("Abierto")) {
            displayButtonCambiarEstadoCompletado = "block";
        }
        
        model.addAttribute("displayButtonCambiarEstadoCompletado", displayButtonCambiarEstadoCompletado);
        model.addAttribute("ordenDeCompraStatus", ordenDeCompra.getEstado());
        model.addAttribute("displayButtonCambiarEstadoAbierto", displayButtonCambiarEstadoAbierto);
        model.addAttribute("idOrdenDeCompra", ordenDeCompra.getId());        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("insumoList", insumos); 
        model.addAttribute("ordenDeCompraItems", ordenDeCompraItemsDtos);        
                
        return "/ordendecompra/ordendecompraitem";
    }
    
    @RequestMapping(value = "/ordenDeCompraItem/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveOrdenDeCompraItem(@ModelAttribute("ordenDeCompraItemForm")OrdenDeCompraItemForm ordenDeCompraItemForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

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
        
        if(ordenDeCompraItemForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        String operacion = ordenDeCompraItemForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error Item Orden de Compra: operación inválida");
            return modelAndView;                        
        }        
        if(ordenDeCompraItemForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error Item Orden de Compra: operación no coincide");
            return modelAndView;                        
        }        
        if(ordenDeCompraItemForm.getIdOrdenDeCompra() == null || ordenDeCompraItemForm.getIdOrdenDeCompra().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error Item Orden de Compra: orden de compra inválida");
            return modelAndView;                                    
        }
        
        OrdenDeCompraService ordenDeCompraService = new OrdenDeCompraServiceImpl();        
        OrdenDeCompraModel ordenDeCompra = ordenDeCompraService.getByPk(Integer.valueOf(ordenDeCompraItemForm.getIdOrdenDeCompra()));

        if(ordenDeCompra == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: orden de compra no encontrada");
            return modelAndView;                
        }
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: no es posible editar un item de una orden de compra en estado "+ordenDeCompra.getEstado());
            return modelAndView;                        
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return modelAndView;                
        }
                
        if(ordenDeCompraItemForm.getIdMateriaPrima().equalsIgnoreCase("-1") && ordenDeCompraItemForm.getIdInsumo().equalsIgnoreCase("-1")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: materia prima o insumo incorrecto");
            return modelAndView;                
        }
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = ordenDeCompraItemForm.getPk();
            
        OrdenDeCompraItemModel ordenDeCompraItemModel = null;
        if(id.equalsIgnoreCase("-1")) {
            ordenDeCompraItemModel = new OrdenDeCompraItemModel();
            ordenDeCompraItemModel.setIdOrdenDeCompra(ordenDeCompra.getId());
            ordenDeCompraItemModel.setEstaCompletado(Boolean.FALSE);
        } else {
            ordenDeCompraItemModel = ordenDeCompraItemService.getByPk(Integer.valueOf(id));
            if(ordenDeCompraItemModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de ordenDeCompraItem inválido.");
                return modelAndView;                
            } 
        }        
        
        if(ordenDeCompraItemForm.getFechaAlta() != null && !ordenDeCompraItemForm.getFechaAlta().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fecha = formato.parse(ordenDeCompraItemForm.getFechaAlta());
            ordenDeCompraItemModel.setFechaAlta(fecha);
        } else {
            ordenDeCompraItemModel.setFechaAlta(null);
        }                
        if(ordenDeCompraItemForm.getTipo().equalsIgnoreCase("materiaPrima")) {
            if(ordenDeCompraItemForm.getIdMateriaPrima() != null && !ordenDeCompraItemForm.getIdMateriaPrima().isEmpty()) {
                ordenDeCompraItemModel.setIdMateriaPrima(Integer.valueOf(ordenDeCompraItemForm.getIdMateriaPrima()));
            } else {
                ordenDeCompraItemModel.setIdMateriaPrima(null);
            }
        }
        if(ordenDeCompraItemForm.getTipo().equalsIgnoreCase("insumo")) {
            if(ordenDeCompraItemForm.getIdInsumo() != null && !ordenDeCompraItemForm.getIdInsumo().isEmpty()) {
                ordenDeCompraItemModel.setIdInsumo(Integer.valueOf(ordenDeCompraItemForm.getIdInsumo()));
            } else {
                ordenDeCompraItemModel.setIdInsumo(null);
            }
        }                
        if(ordenDeCompraItemForm.getCantidad() != null && !ordenDeCompraItemForm.getCantidad().isEmpty()) {
            ordenDeCompraItemModel.setCantidad(Integer.valueOf(ordenDeCompraItemForm.getCantidad()));
        } else {
            ordenDeCompraItemModel.setCantidad(null);
        }

        ordenDeCompraItemModel.setTipo(ordenDeCompraItemForm.getTipo());
        ordenDeCompraItemModel.setIdUsuario(Integer.valueOf(Utils.getUserLoggedId(req)));

        if(ordenDeCompraItemForm.getAction().equalsIgnoreCase("add") || ordenDeCompraItemForm.getAction().equalsIgnoreCase("edit")) {
            ordenDeCompraItemService.save(ordenDeCompraItemModel);
        } else {
            if(ordenDeCompraItemForm.getAction().equalsIgnoreCase("remove")) {
                if(ordenDeCompraItemModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de ordenDeCompraItem inválido.");
                    return modelAndView;                                    
                }
                
                ordenDeCompraItemService.delete(ordenDeCompraItemModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/ordenDeCompraItem/"+ordenDeCompra.getId());

        return modelAndView; 
    }

    @RequestMapping(value = "/ordenDeCompraItem/edit/{ordenDeCompraItempk}", method = RequestMethod.GET)
    public String editOrdenDeCompraItem(@PathVariable String ordenDeCompraItempk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraItempk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();   
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(Integer.valueOf(ordenDeCompraItempk));

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
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(ordenDeCompra.getIdProveedor());

        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: orden de compra sin proveedor");         
            return "/error";                
        }
        
        String rol = "oficina";
        String operacion = "alta";        
        String displayActionButton = "block";
                
        OrdenDeCompraItemForm ordenDeCompraItemForm = new OrdenDeCompraItemForm();
        
        ordenDeCompraItemForm.setPk(ordenDeCompraItem.getId().toString());
        if(ordenDeCompraItem.getFechaAlta() != null) {
            ordenDeCompraItemForm.setFechaAlta(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemForm.setIdOrdenDeCompra(ordenDeCompraItem.getIdOrdenDeCompra().toString());
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
            ordenDeCompraItemForm.setIdMateriaPrima(ordenDeCompraItem.getIdMateriaPrima().toString());
        } else {
            ordenDeCompraItemForm.setIdMateriaPrima("-1");
        }
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
            ordenDeCompraItemForm.setIdInsumo(ordenDeCompraItem.getIdInsumo().toString());
        } else {
            ordenDeCompraItemForm.setIdInsumo("-1");
        }        
        ordenDeCompraItemForm.setTipo(ordenDeCompraItem.getTipo());
        ordenDeCompraItemForm.setIdUsuario(ordenDeCompraItem.getIdUsuario().toString());
        ordenDeCompraItemForm.setProveedor(proveedor.getRazonSocial());
        ordenDeCompraItemForm.setFechaAltaOrdenDeCompra(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeCompra.getFechaEntrega() != null) {
            ordenDeCompraItemForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraItemForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemForm.setEstado(ordenDeCompra.getEstado());
        if(ordenDeCompra.getObservaciones() != null) {
            ordenDeCompraItemForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompraItem.getCantidad() != null) {
            ordenDeCompraItemForm.setCantidad(ordenDeCompraItem.getCantidad().toString());
        }        
        if(ordenDeCompraItem.getFechaCompletado() != null) {
            ordenDeCompraItemForm.setFechaCompletado(ordenDeCompraItem.getFechaCompletado().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompraItem.getEstaCompletado() != null) {
            ordenDeCompraItemForm.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
        } else {
            ordenDeCompraItemForm.setEstaCompletado("0");
        }        
        
        ordenDeCompraItemForm.setOperacion(operacion);
        
        ordenDeCompraItemForm.setAction("edit");
        model.addAttribute("ordenDeCompraItemForm", ordenDeCompraItemForm);  
        model.addAttribute("titleOrdenDeCompraItem", "Editar Item de Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeCompraItemName", ordenDeCompraItem.getId() + " - " + ordenDeCompraItem.getIdOrdenDeCompra());        
        
        List<OrdenDeCompraItemModel> ordenDeCompraItems = ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompra.getId());
        TipoService tipoService = new TipoServiceImpl();
        
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiaPrimasModel = materiaPrimaService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());

        if(materiaPrimasModel != null && !materiaPrimasModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiaPrimasModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }

        InsumoService insumoService = new InsumoServiceImpl();   
        Map<String,String> insumos = new LinkedHashMap<String,String>();
        List<InsumoModel> insumosModel = insumoService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());
        
        if(insumosModel != null && !insumosModel.isEmpty()){
            for(InsumoModel insumoModel :insumosModel) {
                insumos.put(insumoModel.getId().toString(), insumoModel.getDescripcion());
            }
        }
        
        List<OrdenDeCompraItemDto> ordenDeCompraItemsDtos = new ArrayList<OrdenDeCompraItemDto>();
        for(OrdenDeCompraItemModel ordenDeCompraItemModel: ordenDeCompraItems) {
            OrdenDeCompraItemDto ordenDeCompraItemDto = new OrdenDeCompraItemDto();
            ordenDeCompraItemDto.setPk(ordenDeCompraItemModel.getId().toString());
            ordenDeCompraItemDto.setFechaAlta(ordenDeCompraItemModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeCompraItemDto.setCantidadSolicitada(ordenDeCompraItemModel.getCantidad().toString());

            OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl(); 
            Integer cantidadRecepcionadaTotal = 0;
            List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
            if(ordenDeCompraItemsRecepcion != null && !ordenDeCompraItemsRecepcion.isEmpty()) {
                for(OrdenDeCompraItemRecepcionModel recepcion: ordenDeCompraItemsRecepcion) {
                    cantidadRecepcionadaTotal += recepcion.getCantidadRecepcionada();
                }
            }                        
            ordenDeCompraItemDto.setCantidadRecepcionada(cantidadRecepcionadaTotal.toString());            
            
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
                MateriaPrimaModel materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());            
                TipoModel tipo = tipoService.getByPk(materiaPrima.getIdTipo());
                ordenDeCompraItemDto.setMateriaPrima(materiaPrima.getDescripcion());
                ordenDeCompraItemDto.setTipo(tipo.getValor());
                ordenDeCompraItemDto.setStock(materiaPrima.getStock().toString());            
            }                        
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
                InsumoModel insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());                            
                ordenDeCompraItemDto.setMateriaPrima(insumo.getDescripcion());                
                ordenDeCompraItemDto.setStock(insumo.getStock().toString());            
            }
            if(ordenDeCompraItem.getEstaCompletado() != null) {
                ordenDeCompraItemDto.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
            } else {
                ordenDeCompraItemDto.setEstaCompletado("0");
            }
                    
            ordenDeCompraItemsDtos.add(ordenDeCompraItemDto);
        }

        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");
        model.addAttribute("displayButtonCambiarEstadoAbierto", "none");        
        model.addAttribute("idOrdenDeCompra", ordenDeCompra.getId());
        model.addAttribute("displayUser", "none");                
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("insumoList", insumos);        
        model.addAttribute("ordenDeCompraItems", ordenDeCompraItemsDtos); 
        model.addAttribute("ordenDeCompraStatus", ordenDeCompra.getEstado());
        
        return "/ordendecompra/ordendecompraitem";        
    }
    
    @RequestMapping(value = "/ordenDeCompraItem/remove/{ordenDeCompraItempk}", method = RequestMethod.GET)
    public String removeOrdenDeCompraItem(@PathVariable String ordenDeCompraItempk, HttpServletRequest req, ModelMap model) throws Exception {
                
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraItempk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();   
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(Integer.valueOf(ordenDeCompraItempk));

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
        
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(ordenDeCompra.getIdProveedor());

        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: orden de compra sin proveedor");         
            return "/error";                
        }
        
        String rol = "oficina";
        String operacion = "alta";        
        String displayActionButton = "block";
                
        OrdenDeCompraItemForm ordenDeCompraItemForm = new OrdenDeCompraItemForm();
        
        ordenDeCompraItemForm.setPk(ordenDeCompraItem.getId().toString());
        if(ordenDeCompraItem.getFechaAlta() != null) {
            ordenDeCompraItemForm.setFechaAlta(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemForm.setIdOrdenDeCompra(ordenDeCompraItem.getIdOrdenDeCompra().toString());
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
            ordenDeCompraItemForm.setIdMateriaPrima(ordenDeCompraItem.getIdMateriaPrima().toString());
        } else {
            ordenDeCompraItemForm.setIdMateriaPrima("-1");
        }
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
            ordenDeCompraItemForm.setIdInsumo(ordenDeCompraItem.getIdInsumo().toString());
        } else {
            ordenDeCompraItemForm.setIdInsumo("-1");
        }        
        ordenDeCompraItemForm.setTipo(ordenDeCompraItem.getTipo());
        ordenDeCompraItemForm.setIdUsuario(ordenDeCompraItem.getIdUsuario().toString());
        ordenDeCompraItemForm.setProveedor(proveedor.getRazonSocial());
        ordenDeCompraItemForm.setFechaAltaOrdenDeCompra(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeCompra.getFechaEntrega() != null) {
            ordenDeCompraItemForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraItemForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemForm.setEstado(ordenDeCompra.getEstado());
        if(ordenDeCompra.getObservaciones() != null) {
            ordenDeCompraItemForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompraItem.getCantidad() != null) {
            ordenDeCompraItemForm.setCantidad(ordenDeCompraItem.getCantidad().toString());
        }        
        if(ordenDeCompraItem.getFechaCompletado() != null) {
            ordenDeCompraItemForm.setFechaCompletado(ordenDeCompraItem.getFechaCompletado().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompraItem.getEstaCompletado() != null) {
            ordenDeCompraItemForm.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
        } else {
            ordenDeCompraItemForm.setEstaCompletado("0");
        }        
        
        ordenDeCompraItemForm.setOperacion(operacion);
        
        ordenDeCompraItemForm.setAction("remove");
        model.addAttribute("ordenDeCompraItemForm", ordenDeCompraItemForm);  
        model.addAttribute("titleOrdenDeCompraItem", "Eliminar Item de Orden de Compra");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("ordenDeCompraItemName", ordenDeCompraItem.getId() + " - " + ordenDeCompraItem.getIdOrdenDeCompra());        
        
        List<OrdenDeCompraItemModel> ordenDeCompraItems = ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompra.getId());
        TipoService tipoService = new TipoServiceImpl();
        
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiaPrimasModel = materiaPrimaService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());

        if(materiaPrimasModel != null && !materiaPrimasModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiaPrimasModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }

        InsumoService insumoService = new InsumoServiceImpl();   
        Map<String,String> insumos = new LinkedHashMap<String,String>();
        List<InsumoModel> insumosModel = insumoService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());
        
        if(insumosModel != null && !insumosModel.isEmpty()){
            for(InsumoModel insumoModel :insumosModel) {
                insumos.put(insumoModel.getId().toString(), insumoModel.getDescripcion());
            }
        }
        
        List<OrdenDeCompraItemDto> ordenDeCompraItemsDtos = new ArrayList<OrdenDeCompraItemDto>();
        for(OrdenDeCompraItemModel ordenDeCompraItemModel: ordenDeCompraItems) {
            OrdenDeCompraItemDto ordenDeCompraItemDto = new OrdenDeCompraItemDto();
            ordenDeCompraItemDto.setPk(ordenDeCompraItemModel.getId().toString());
            ordenDeCompraItemDto.setFechaAlta(ordenDeCompraItemModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeCompraItemDto.setCantidadSolicitada(ordenDeCompraItemModel.getCantidad().toString());
            
            OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl(); 
            Integer cantidadRecepcionadaTotal = 0;
            List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItem.getId());
            if(ordenDeCompraItemsRecepcion != null && !ordenDeCompraItemsRecepcion.isEmpty()) {
                for(OrdenDeCompraItemRecepcionModel recepcion: ordenDeCompraItemsRecepcion) {
                    cantidadRecepcionadaTotal += recepcion.getCantidadRecepcionada();
                }
            }                        
            ordenDeCompraItemDto.setCantidadRecepcionada(cantidadRecepcionadaTotal.toString());            
            
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
                MateriaPrimaModel materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());            
                TipoModel tipo = tipoService.getByPk(materiaPrima.getIdTipo());
                ordenDeCompraItemDto.setMateriaPrima(materiaPrima.getDescripcion());
                ordenDeCompraItemDto.setTipo(tipo.getValor());
                ordenDeCompraItemDto.setStock(materiaPrima.getStock().toString());            
            }                        
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
                InsumoModel insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());                            
                ordenDeCompraItemDto.setMateriaPrima(insumo.getDescripcion());                
                ordenDeCompraItemDto.setStock(insumo.getStock().toString());            
            }
            if(ordenDeCompraItem.getEstaCompletado() != null) {
                ordenDeCompraItemDto.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
            } else {
                ordenDeCompraItemDto.setEstaCompletado("0");
            }
            
            ordenDeCompraItemsDtos.add(ordenDeCompraItemDto);
        }

        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");
        model.addAttribute("displayButtonCambiarEstadoAbierto", "none");        
        model.addAttribute("idOrdenDeCompra", ordenDeCompra.getId());
        model.addAttribute("displayUser", "none");                
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("insumoList", insumos);
        model.addAttribute("ordenDeCompraItems", ordenDeCompraItemsDtos);        
        
        return "/ordendecompra/ordendecompraitem";   
             
    }    
    
    @RequestMapping(value = "/ordenDeCompraItem/view/{ordenDeCompraItempk}", method = RequestMethod.GET)
    public String viewOrdenDeCompraItem(@PathVariable String ordenDeCompraItempk, HttpServletRequest req, ModelMap model) throws Exception {
                        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeCompraItempk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeCompraItem inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        OrdenDeCompraItemService ordenDeCompraItemService = new OrdenDeCompraItemServiceImpl();   
        OrdenDeCompraItemModel ordenDeCompraItem = ordenDeCompraItemService.getByPk(Integer.valueOf(ordenDeCompraItempk));

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
        
        /*
        if(!ordenDeCompra.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        */
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(ordenDeCompra.getIdProveedor());

        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: orden de compra sin proveedor");         
            return "/error";                
        }
        
        String rol = "oficina";
        String operacion = "view";        
        String displayActionButton = "block";
                
        OrdenDeCompraItemForm ordenDeCompraItemForm = new OrdenDeCompraItemForm();
        
        ordenDeCompraItemForm.setPk(ordenDeCompraItem.getId().toString());
        if(ordenDeCompraItem.getFechaAlta() != null) {
            ordenDeCompraItemForm.setFechaAlta(ordenDeCompraItem.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemForm.setIdOrdenDeCompra(ordenDeCompraItem.getIdOrdenDeCompra().toString());
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
            ordenDeCompraItemForm.setIdMateriaPrima(ordenDeCompraItem.getIdMateriaPrima().toString());
        } else {
            ordenDeCompraItemForm.setIdMateriaPrima("-1");
        }
        if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
            ordenDeCompraItemForm.setIdInsumo(ordenDeCompraItem.getIdInsumo().toString());
        } else {
            ordenDeCompraItemForm.setIdInsumo("-1");
        }        
        ordenDeCompraItemForm.setTipo(ordenDeCompraItem.getTipo());
        ordenDeCompraItemForm.setIdUsuario(ordenDeCompraItem.getIdUsuario().toString());
        ordenDeCompraItemForm.setProveedor(proveedor.getRazonSocial());
        ordenDeCompraItemForm.setFechaAltaOrdenDeCompra(ordenDeCompra.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeCompra.getFechaEntrega() != null) {
            ordenDeCompraItemForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompra.getFechaCierreOrden() != null) {
            ordenDeCompraItemForm.setFechaEntregaOrdenDeCompra(ordenDeCompra.getFechaCierreOrden().toString().replace(" 00:00:00.0", ""));
        }
        ordenDeCompraItemForm.setEstado(ordenDeCompra.getEstado());
        if(ordenDeCompra.getObservaciones() != null) {
            ordenDeCompraItemForm.setObservaciones(ordenDeCompra.getObservaciones());
        }        
        if(ordenDeCompraItem.getCantidad() != null) {
            ordenDeCompraItemForm.setCantidad(ordenDeCompraItem.getCantidad().toString());
        }        
        if(ordenDeCompraItem.getFechaCompletado() != null) {
            ordenDeCompraItemForm.setFechaCompletado(ordenDeCompraItem.getFechaCompletado().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeCompraItem.getEstaCompletado() != null) {
            ordenDeCompraItemForm.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
        } else {
            ordenDeCompraItemForm.setEstaCompletado("0");
        }         
        
        ordenDeCompraItemForm.setOperacion(operacion);
        
        ordenDeCompraItemForm.setAction("view");
        model.addAttribute("ordenDeCompraItemForm", ordenDeCompraItemForm);  
        model.addAttribute("titleOrdenDeCompraItem", "Ver Item de Orden de Compra");
        model.addAttribute("buttonLabel", "Ver");
        model.addAttribute("ordenDeCompraItemName", ordenDeCompraItem.getId() + " - " + ordenDeCompraItem.getIdOrdenDeCompra());        
        
        List<OrdenDeCompraItemModel> ordenDeCompraItems = ordenDeCompraItemService.getAllByOrdenDeCompra(ordenDeCompra.getId());
        TipoService tipoService = new TipoServiceImpl();
                
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiaPrimasModel = materiaPrimaService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());

        if(materiaPrimasModel != null && !materiaPrimasModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiaPrimasModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }

        InsumoService insumoService = new InsumoServiceImpl();   
        Map<String,String> insumos = new LinkedHashMap<String,String>();
        List<InsumoModel> insumosModel = insumoService.getAllByIdProveedor(ordenDeCompra.getIdProveedor());
        
        if(insumosModel != null && !insumosModel.isEmpty()){
            for(InsumoModel insumoModel :insumosModel) {
                insumos.put(insumoModel.getId().toString(), insumoModel.getDescripcion());
            }
        }
        
        List<OrdenDeCompraItemDto> ordenDeCompraItemsDtos = new ArrayList<OrdenDeCompraItemDto>();
        for(OrdenDeCompraItemModel ordenDeCompraItemModel: ordenDeCompraItems) {
            OrdenDeCompraItemDto ordenDeCompraItemDto = new OrdenDeCompraItemDto();
            ordenDeCompraItemDto.setPk(ordenDeCompraItemModel.getId().toString());
            ordenDeCompraItemDto.setFechaAlta(ordenDeCompraItemModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeCompraItemDto.setCantidadSolicitada(ordenDeCompraItemModel.getCantidad().toString());

            OrdenDeCompraItemRecepcionService ordenDeCompraItemRecepcionService = new OrdenDeCompraItemRecepcionServiceImpl(); 
            Integer cantidadRecepcionadaTotal = 0;
            List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionService.getAllByOrdenDeCompraItem(ordenDeCompraItemModel.getId());
            if(ordenDeCompraItemsRecepcion != null && !ordenDeCompraItemsRecepcion.isEmpty()) {
                for(OrdenDeCompraItemRecepcionModel recepcion: ordenDeCompraItemsRecepcion) {
                    cantidadRecepcionadaTotal += recepcion.getCantidadRecepcionada();
                }
            }                        
            ordenDeCompraItemDto.setCantidadRecepcionada(cantidadRecepcionadaTotal.toString());            
            
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("materiaPrima")) {
                MateriaPrimaModel materiaPrima = materiaPrimaService.getByPk(ordenDeCompraItem.getIdMateriaPrima());            
                TipoModel tipo = tipoService.getByPk(materiaPrima.getIdTipo());
                ordenDeCompraItemDto.setMateriaPrima(materiaPrima.getDescripcion());
                ordenDeCompraItemDto.setTipo(tipo.getValor());
                ordenDeCompraItemDto.setStock(materiaPrima.getStock().toString());            
            }                        
            if(ordenDeCompraItem.getTipo().equalsIgnoreCase("insumo")) {
                InsumoModel insumo = insumoService.getByPk(ordenDeCompraItem.getIdInsumo());                            
                ordenDeCompraItemDto.setMateriaPrima(insumo.getDescripcion());                
                ordenDeCompraItemDto.setStock(insumo.getStock().toString());            
            }
            if(ordenDeCompraItem.getEstaCompletado() != null) {
                ordenDeCompraItemDto.setEstaCompletado(ordenDeCompraItem.getEstaCompletado().toString());
            } else {
                ordenDeCompraItemDto.setEstaCompletado("0");
            }
            
            ordenDeCompraItemsDtos.add(ordenDeCompraItemDto);
        }

        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");
        model.addAttribute("displayButtonCambiarEstadoAbierto", "none");
        model.addAttribute("idOrdenDeCompra", ordenDeCompra.getId());        
        model.addAttribute("displayUser", "none");                
        model.addAttribute("rol", rol);
        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", "none");
        model.addAttribute("operacion", operacion);        
        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("insumoList", insumos);        
        model.addAttribute("ordenDeCompraItems", ordenDeCompraItemsDtos);        
        
        return "/ordendecompra/ordendecompraitem";           
    }    
    
    @RequestMapping(value = "/ordenDeCompraItem/setStatusOpenOrdenCompra/{ordenDeCompraPk}", method = RequestMethod.GET)
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
        
        return "redirect:/ordenDeCompraItem/"+ordenDeCompra.getId();                         
        
    }    
    
    @RequestMapping(value = "/ordenDeCompraItem/setStatusCompletedOrdenCompra/{ordenDeCompraPk}", method = RequestMethod.GET)
    public String setStatusCompletedOrdenCompra(@PathVariable String ordenDeCompraPk, HttpServletRequest req, ModelMap model) throws Exception {

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
        if(items == null || items.isEmpty()) {
            ordenEstaCompletada = false;
        }
        /*
        if(!items.isEmpty()) {
            for(OrdenDeCompraItemModel item: items) {
                if(item.getEstaCompletado() == null || !item.getEstaCompletado()) {
                    ordenEstaCompletada = false;
                    break;
                }
            }
        }
        */
        if(!ordenEstaCompletada) {
            model.addAttribute("errorMessage", "Error: no es posible completar una orden a abierta cuando tiene items sin completar");
            return "/error";                          
        }
        
        if(!items.isEmpty()) {                
            for(OrdenDeCompraItemModel item: items) {
                if(!item.getEstaCompletado()) {
                    item.setEstaCompletado(Boolean.TRUE);
                    item.setFaltaCantidad(Boolean.TRUE);                    
                    item.setFechaCompletado(new Date());

                    ordenDeCompraItemService.save(item);
                }
            }
        }

        ordenDeCompra.setEstado("Completado");
        ordenDeCompraService.save(ordenDeCompra);
        
        return "redirect:/ordenDeCompraItem/"+ordenDeCompra.getId();                         
        
    }   
    
}
