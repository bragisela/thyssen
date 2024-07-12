/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.ArticuloBean;
import com.empresa.thyssenplastic.controller.beans.ArticuloFichaTecnicaBean;
import com.empresa.thyssenplastic.controller.form.OrdenDeProduccionForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionDto;
import com.empresa.thyssenplastic.dto.OrdenDepositoDto;
import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ArticuloFichaTecnicaService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloFichaTecnicaServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class OrdenDeProduccionController {
    

    @RequestMapping(value = "/ordenDeProduccion", method = RequestMethod.GET)
    public String getHomeOrdenDeProduccion(HttpServletRequest req, ModelMap model) {

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
        
        OrdenDeProduccionForm ordenDeProduccionForm = new OrdenDeProduccionForm();
        ordenDeProduccionForm.setPk("-1");
        ordenDeProduccionForm.setAction("add");
        ordenDeProduccionForm.setEstado("Nuevo");

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        ordenDeProduccionForm.setFechaAlta(fecha);

        if(user.getRol() == Utils.ROL_OFICINA) {
            ordenDeProduccionForm.setEstadoLabel("Nuevo");            
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_PLANTA) {
            ordenDeProduccionForm.setEstadoLabel("Abierto");
            operacion = "alta";
            displayActionButton = "none";            
            rol = "planta";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            ordenDeProduccionForm.setEstadoLabel("Abierto");
            operacion = "alta";
            displayActionButton = "none";            
            rol = "deposito";
        }
        
        ordenDeProduccionForm.setEditObservaciones("false");
        ordenDeProduccionForm.setOperacion(operacion);
        
        model.addAttribute("ordenDeProduccionForm", ordenDeProduccionForm);  
        model.addAttribute("titleOrdenDeProduccion", "Nueva Order De Producción");  
        model.addAttribute("buttonLabel", "Guardar");
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> unidadVentas = new LinkedHashMap<String,String>();
        List<TipoModel> unidadVentasModel = tipoService.getByType("unidadVentaArticulo");

        if(unidadVentasModel != null && !unidadVentasModel.isEmpty()){
            for(TipoModel tipoModel :unidadVentasModel) {
                unidadVentas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }

        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        Map<String,String> articulosFichaTecnica = new LinkedHashMap<String,String>();
        List<ArticuloFichaTecnicaModel> articulosFichaTecnicaModel = articuloFichaTecnicaService.getAll();

        if(articulosFichaTecnicaModel != null && !articulosFichaTecnicaModel.isEmpty()){
            for(ArticuloFichaTecnicaModel articuloFichaTecnicaModel :articulosFichaTecnicaModel) {
                articulosFichaTecnica.put(articuloFichaTecnicaModel.getId().toString(), articuloFichaTecnicaModel.getVersion().toString());
            }
        }
        
        List<OrdenDeProduccionDto> ordenDeProduccionesDtos = new ArrayList<OrdenDeProduccionDto>();
        for(OrdenDeProduccionModel ordenDeProduccion: ordenDeProducciones) {
            OrdenDeProduccionDto ordenDeProduccionDto = new OrdenDeProduccionDto();
            ordenDeProduccionDto.setPk(ordenDeProduccion.getId().toString());
            ordenDeProduccionDto.setFechaAlta(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeProduccionDto.setEstado(ordenDeProduccion.getEstado());
            if(ordenDeProduccion.getIdCliente() != null) {
                ordenDeProduccionDto.setCliente(clientes.get(ordenDeProduccion.getIdCliente().toString()));
            }
            if(ordenDeProduccion.getIdArticulo() != null) {
                ordenDeProduccionDto.setArticulo(articulos.get(ordenDeProduccion.getIdArticulo().toString()));
            }
            if(ordenDeProduccion.getIdFichaTecnica() != null) {
                ordenDeProduccionDto.setVersionFichaTecnica(articulosFichaTecnica.get(ordenDeProduccion.getIdFichaTecnica().toString()));
            }
            if(ordenDeProduccion.getCantidadAProducir() != null) {
                ordenDeProduccionDto.setCantidadAProducir(ordenDeProduccion.getCantidadAProducir().toString());
            }
            if(ordenDeProduccion.getEstado().equalsIgnoreCase("Nuevo")) {
                ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("true");    
            } else {
                ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("false");    
            }
            
            ordenDeProduccionesDtos.add(ordenDeProduccionDto);
            
            
        }
                       
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("articuloList", articulos); 
        model.addAttribute("unidadVentaList", unidadVentas);        
        model.addAttribute("ordenDeProducciones", ordenDeProduccionesDtos);        
                
        return "/ordendeproduccion/ordendeproduccion";
    }
    
    @RequestMapping(value = "/ordenDeProduccion/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveOrdenDeProduccion(@ModelAttribute("ordenDeProduccionForm")OrdenDeProduccionForm ordenDeProduccionForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

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
        
        if(ordenDeProduccionForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        String operacion = ordenDeProduccionForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta") && !operacion.equalsIgnoreCase("completar"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }        
        if(ordenDeProduccionForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }
        System.out.println("*** ordenDeProduccionForm.getIdCliente():"+ordenDeProduccionForm.getIdClienteAux());
        System.out.println("*** ordenDeProduccionForm.getIdArticulo():"+ordenDeProduccionForm.getIdArticuloAux());
        System.out.println("*** ordenDeProduccionForm.getIdFichaTecnica():"+ordenDeProduccionForm.getIdFichaTecnicaAux());
        System.out.println("*** ordenDeProduccionForm.getAction():"+ordenDeProduccionForm.getAction());
        
        if(ordenDeProduccionForm.getAction().equalsIgnoreCase("add")) {
            /*
            if(ordenDeProduccionForm.getIdCliente() == null || ordenDeProduccionForm.getIdCliente().equalsIgnoreCase("-1")) {

                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: cliente incorrecto");
                return modelAndView;                                    
            } */       
            if(ordenDeProduccionForm.getIdArticulo() == null || ordenDeProduccionForm.getIdArticulo().equalsIgnoreCase("-1")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: articulo incorrecto");
                return modelAndView;                                    
            }
            if(ordenDeProduccionForm.getIdFichaTecnica() == null || ordenDeProduccionForm.getIdFichaTecnica().equalsIgnoreCase("-1")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: ficha tecnica incorrecta");
                return modelAndView;                                    
            }
        }
        if(ordenDeProduccionForm.getAction().equalsIgnoreCase("remove")) {
            /*
            if(ordenDeProduccionForm.getIdClienteAux() == null || ordenDeProduccionForm.getIdClienteAux().equalsIgnoreCase("-1")) {

                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: cliente incorrecto");
                return modelAndView;                                    
            } */       
            if(ordenDeProduccionForm.getIdArticuloAux() == null || ordenDeProduccionForm.getIdArticuloAux().equalsIgnoreCase("-1")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: articulo incorrecto");
                return modelAndView;                                    
            }
            if(ordenDeProduccionForm.getIdFichaTecnicaAux() == null || ordenDeProduccionForm.getIdFichaTecnicaAux().equalsIgnoreCase("-1")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: ficha tecnica incorrecta");
                return modelAndView;                                    
            }
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = ordenDeProduccionForm.getPk();
            
        OrdenDeProduccionModel ordenDeProduccionModel = null;
        if(id.equalsIgnoreCase("-1")) {            
            if(user.getRol() != Utils.ROL_OFICINA ) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: no es posible editar una orden en estado "+ordenDeProduccionModel.getEstado());
                return modelAndView;                        
            }             
            ordenDeProduccionModel = new OrdenDeProduccionModel();
            ordenDeProduccionModel.setFechaAltaImpresion(new Date());
            ordenDeProduccionModel.setStockActual(0);
            ordenDeProduccionModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));
            ordenDeProduccionModel.setEstado("Nuevo");                            
        } else {
            ordenDeProduccionModel = ordenDeProduccionService.getByPk(Integer.valueOf(id));
            if(ordenDeProduccionModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de ordenDeProduccion inválido.");
                return modelAndView;                
            } 
            if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: no es posible editar una orden en estado "+ordenDeProduccionModel.getEstado());
                return modelAndView;                        
            }            
        }        
        
        if(operacion.equalsIgnoreCase("alta")) {
            if(ordenDeProduccionForm.getFechaAlta() != null && !ordenDeProduccionForm.getFechaAlta().trim().equals("")) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(ordenDeProduccionForm.getFechaAlta());
                ordenDeProduccionModel.setFechaAlta(fecha);
            } else {
                ordenDeProduccionModel.setFechaAlta(new Date());
            }                
            if(ordenDeProduccionForm.getFechaPedido() != null && !ordenDeProduccionForm.getFechaPedido().trim().equals("")) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(ordenDeProduccionForm.getFechaPedido());
                ordenDeProduccionModel.setFechaPedido(fecha);
            } else {
                ordenDeProduccionModel.setFechaPedido(null);
            }                                
            if(ordenDeProduccionForm.getIdCliente() != null && !ordenDeProduccionForm.getIdCliente().isEmpty() && !ordenDeProduccionForm.getIdCliente().equalsIgnoreCase("-1")) {
                ordenDeProduccionModel.setIdCliente(Integer.valueOf(ordenDeProduccionForm.getIdCliente()));
            } else {
                if(ordenDeProduccionForm.getIdArticulo() != null && !ordenDeProduccionForm.getIdArticulo().isEmpty()) {
                    ArticuloService articuloService = new ArticuloServiceImpl();
                    ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(ordenDeProduccionForm.getIdArticulo()));
                    ordenDeProduccionModel.setIdCliente(articulo.getIdCliente());
                } else {
                    ordenDeProduccionModel.setIdCliente(null);
                }
            }
            if(ordenDeProduccionForm.getIdArticulo() != null && !ordenDeProduccionForm.getIdArticulo().isEmpty()) {
                ordenDeProduccionModel.setIdArticulo(Integer.valueOf(ordenDeProduccionForm.getIdArticulo()));
            } else {
                ordenDeProduccionModel.setIdArticulo(null);
            }
            if(ordenDeProduccionForm.getIdFichaTecnica() != null && !ordenDeProduccionForm.getIdFichaTecnica().isEmpty()) {
                ordenDeProduccionModel.setIdFichaTecnica(Integer.valueOf(ordenDeProduccionForm.getIdFichaTecnica()));
            } else {
                ordenDeProduccionModel.setIdFichaTecnica(null);
            }
            if(ordenDeProduccionForm.getIdUnidadVenta() != null && !ordenDeProduccionForm.getIdUnidadVenta().isEmpty()) {
                ordenDeProduccionModel.setIdUnidadVenta(Integer.valueOf(ordenDeProduccionForm.getIdUnidadVenta()));
            } else {
                ordenDeProduccionModel.setIdUnidadVenta(null);
            }
            if(ordenDeProduccionForm.getCantidadAProducir() != null && !ordenDeProduccionForm.getCantidadAProducir().isEmpty()) {
                ordenDeProduccionModel.setCantidadAProducir(Integer.valueOf(ordenDeProduccionForm.getCantidadAProducir()));
                ordenDeProduccionModel.setStockActual(Integer.valueOf(ordenDeProduccionForm.getCantidadAProducir()));
            } else {
                ordenDeProduccionModel.setCantidadAProducir(null);
                ordenDeProduccionModel.setStockActual(0);
            }
            if(ordenDeProduccionForm.getObservaciones() != null && !ordenDeProduccionForm.getObservaciones().isEmpty()) {
                ordenDeProduccionModel.setObservaciones(ordenDeProduccionForm.getObservaciones());
            } else {
                ordenDeProduccionModel.setObservaciones(null);
            }
        }

        
        if(ordenDeProduccionForm.getAction().equalsIgnoreCase("add") || ordenDeProduccionForm.getAction().equalsIgnoreCase("edit")) {
            ordenDeProduccionService.save(ordenDeProduccionModel);
        } else {
            if(ordenDeProduccionForm.getAction().equalsIgnoreCase("remove")) {
                if(ordenDeProduccionModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de ordenDeProduccion inválido.");
                    return modelAndView;                                    
                }
                
                ordenDeProduccionService.delete(ordenDeProduccionModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/ordenDeProduccion");

        return modelAndView; 
    }

    @RequestMapping(value = "/ordenDeProduccion/edit/{ordenDeProduccionpk}", method = RequestMethod.GET)
    public String editOrdenDeProduccion(@PathVariable String ordenDeProduccionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionpk));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Nuevo")) {
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
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }

        
        OrdenDeProduccionForm ordenDeProduccionForm = new OrdenDeProduccionForm();
        ordenDeProduccionForm.setPk(ordenDeProduccion.getId().toString());
        if(ordenDeProduccion.getFechaAlta() != null) {
            ordenDeProduccionForm.setFechaAlta(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeProduccion.getFechaPedido() != null) {
            ordenDeProduccionForm.setFechaPedido(ordenDeProduccion.getFechaPedido().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeProduccion.getFechaCierre() != null) {
            ordenDeProduccionForm.setFechaCierre(ordenDeProduccion.getFechaCierre().toString().replace(" 00:00:00.0", ""));
        }                
        if(ordenDeProduccion.getIdCliente() != null) {
            ordenDeProduccionForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
            ordenDeProduccionForm.setIdClienteAux(ordenDeProduccion.getIdCliente().toString());
        }        
        if(ordenDeProduccion.getIdArticulo() != null) {
            ordenDeProduccionForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
            ordenDeProduccionForm.setIdArticuloAux(ordenDeProduccion.getIdArticulo().toString());
        }        
        if(ordenDeProduccion.getIdFichaTecnica() != null) {
            ordenDeProduccionForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
            ordenDeProduccionForm.setIdFichaTecnicaAux(ordenDeProduccion.getIdFichaTecnica().toString());
        }                
        if(ordenDeProduccion.getIdUnidadVenta() != null) {
            ordenDeProduccionForm.setIdUnidadVenta(ordenDeProduccion.getIdUnidadVenta().toString());
        }                
        if(ordenDeProduccion.getIdUsuarioAlta() != null) {
            ordenDeProduccionForm.setIdUsuarioAlta(ordenDeProduccion.getIdUsuarioAlta().toString());
        }                
        if(ordenDeProduccion.getIdUsuarioCierre() != null) {
            ordenDeProduccionForm.setIdUsuarioCierre(ordenDeProduccion.getIdUsuarioCierre().toString());
        }                        
        if(ordenDeProduccion.getObservaciones() != null && !ordenDeProduccion.getObservaciones().isEmpty()) {
            ordenDeProduccionForm.setObservaciones(ordenDeProduccion.getObservaciones());
        }        
        if(ordenDeProduccion.getEstado() != null && !ordenDeProduccion.getEstado().isEmpty()) {
            ordenDeProduccionForm.setEstado(ordenDeProduccion.getEstado());
        }        
        if(ordenDeProduccion.getCantidadAProducir() != null) {
            ordenDeProduccionForm.setCantidadAProducir(ordenDeProduccion.getCantidadAProducir().toString());
        }        
        
        ordenDeProduccionForm.setOperacion(operacion);
        
        ordenDeProduccionForm.setAction("edit");
        model.addAttribute("ordenDeProduccionForm", ordenDeProduccionForm);  
        model.addAttribute("titleOrdenDeProduccion", "Editar Orden de Producción");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeProduccionName", ordenDeProduccion.getId() + " - " + ordenDeProduccion.getIdCliente() + " - " + ordenDeProduccion.getIdArticulo());        
        
        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> unidadVentas = new LinkedHashMap<String,String>();
        List<TipoModel> unidadVentasModel = tipoService.getByType("unidadVentaArticulo");

        if(unidadVentasModel != null && !unidadVentasModel.isEmpty()){
            for(TipoModel tipoModel :unidadVentasModel) {
                unidadVentas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }

        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        Map<String,String> articulosFichaTecnica = new LinkedHashMap<String,String>();
        List<ArticuloFichaTecnicaModel> articulosFichaTecnicaModel = articuloFichaTecnicaService.getAll();

        if(articulosFichaTecnicaModel != null && !articulosFichaTecnicaModel.isEmpty()){
            for(ArticuloFichaTecnicaModel articuloFichaTecnicaModel :articulosFichaTecnicaModel) {
                articulosFichaTecnica.put(articuloFichaTecnicaModel.getId().toString(), articuloFichaTecnicaModel.getVersion().toString());
            }
        }
        
        List<OrdenDeProduccionDto> ordenDeProduccionesDtos = new ArrayList<OrdenDeProduccionDto>();
        for(OrdenDeProduccionModel ordenDeProduccionModel: ordenDeProducciones) {
            if(!rol.equals("planta") || !ordenDeProduccion.getEstado().equalsIgnoreCase("Completado")) {
                OrdenDeProduccionDto ordenDeProduccionDto = new OrdenDeProduccionDto();
                ordenDeProduccionDto.setPk(ordenDeProduccionModel.getId().toString());
                ordenDeProduccionDto.setFechaAlta(ordenDeProduccionModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
                ordenDeProduccionDto.setEstado(ordenDeProduccionModel.getEstado());
                if(ordenDeProduccionModel.getIdCliente() != null) {
                    ordenDeProduccionDto.setCliente(clientes.get(ordenDeProduccionModel.getIdCliente().toString()));
                }
                if(ordenDeProduccionModel.getIdArticulo() != null) {
                    ordenDeProduccionDto.setArticulo(articulos.get(ordenDeProduccionModel.getIdArticulo().toString()));
                }
                if(ordenDeProduccionModel.getIdFichaTecnica() != null) {
                    ordenDeProduccionDto.setVersionFichaTecnica(articulosFichaTecnica.get(ordenDeProduccionModel.getIdFichaTecnica().toString()));
                }
                if(ordenDeProduccionModel.getCantidadAProducir() != null) {
                    ordenDeProduccionDto.setCantidadAProducir(ordenDeProduccionModel.getCantidadAProducir().toString());
                }
                if(ordenDeProduccionModel.getEstado().equalsIgnoreCase("Nuevo")) {
                    ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("true");    
                } else {
                    ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("false");    
                }

                ordenDeProduccionesDtos.add(ordenDeProduccionDto);
            }
            
        }
                       
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("unidadVentaList", unidadVentas);        
        model.addAttribute("ordenDeProducciones", ordenDeProduccionesDtos);
                                                                                       
        return "/ordendeproduccion/ordendeproduccion";        
    }
    
    /*
    @RequestMapping(value = "/ordenDeProduccion/editObservaciones/{ordenDeProduccionpk}", method = RequestMethod.GET)
    public String editObservacionesOrdenDeProduccion(@PathVariable String ordenDeProduccionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionpk));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Completado")) {
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
        
        OrdenDeProduccionForm ordenDeProduccionForm = new OrdenDeProduccionForm();
        ordenDeProduccionForm.setPk(ordenDeProduccion.getId().toString());
        if(ordenDeProduccion.getFechaAlta() != null) {
            ordenDeProduccionForm.setFechaAlta(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeProduccion.getFechaEntrega() != null) {
            ordenDeProduccionForm.setFechaEntrega(ordenDeProduccion.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }                
        if(ordenDeProduccion.getIdProveedor() != null) {
            ordenDeProduccionForm.setIdProveedor(ordenDeProduccion.getIdProveedor().toString());
        }        
        if(ordenDeProduccion.getObservaciones() != null && !ordenDeProduccion.getObservaciones().isEmpty()) {
            ordenDeProduccionForm.setObservaciones(ordenDeProduccion.getObservaciones());
        }        
        if(ordenDeProduccion.getReferenciaAdministrativa() != null && !ordenDeProduccion.getReferenciaAdministrativa().isEmpty()) {
            ordenDeProduccionForm.setReferenciaAdministrativa(ordenDeProduccion.getReferenciaAdministrativa());
        }                        
        if(ordenDeProduccion.getEstado() != null && !ordenDeProduccion.getEstado().isEmpty()) {
            ordenDeProduccionForm.setEstado(ordenDeProduccion.getEstado());
        }        
        
        ordenDeProduccionForm.setOperacion(operacion);
        ordenDeProduccionForm.setEditObservaciones("true");
                
        ordenDeProduccionForm.setAction("edit");
        model.addAttribute("ordenDeProduccionForm", ordenDeProduccionForm);  
        model.addAttribute("titleOrdenDeProduccion", "Editar Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeProduccionName", ordenDeProduccion.getId() + " - " + ordenDeProduccion.getIdProveedor());        
        
        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<OrdenDeProduccionDto> ordenDeProduccionesDtos = new ArrayList<OrdenDeProduccionDto>();
        for(OrdenDeProduccionModel ordenDeProduccionModel: ordenDeProducciones) {
            OrdenDeProduccionDto ordenDeProduccionDto = new OrdenDeProduccionDto();
            ordenDeProduccionDto.setPk(ordenDeProduccionModel.getId().toString());
            ordenDeProduccionDto.setFechaAlta(ordenDeProduccionModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeProduccionDto.setEstado(ordenDeProduccionModel.getEstado());
            ordenDeProduccionDto.setProveedor(proveedores.get(ordenDeProduccionModel.getIdProveedor().toString()));
            
            ordenDeProduccionesDtos.add(ordenDeProduccionDto);
        }
                               
        model.addAttribute("displayUser", "none");
                
        model.addAttribute("action", "editObservaciones");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("ordenDeProducciones", ordenDeProduccionesDtos);        
                                                        
        return "/ordendecompra/ordendecompra";        
    }
    */
    
    @RequestMapping(value = "/ordenDeProduccion/remove/{ordenDeProduccionpk}", method = RequestMethod.GET)
    public String removeOrdenDeProduccion(@PathVariable String ordenDeProduccionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionpk));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Nuevo")) {
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
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }

        
        OrdenDeProduccionForm ordenDeProduccionForm = new OrdenDeProduccionForm();
        ordenDeProduccionForm.setPk(ordenDeProduccion.getId().toString());
        if(ordenDeProduccion.getFechaAlta() != null) {
            ordenDeProduccionForm.setFechaAlta(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeProduccion.getFechaPedido() != null) {
            ordenDeProduccionForm.setFechaPedido(ordenDeProduccion.getFechaPedido().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeProduccion.getFechaCierre() != null) {
            ordenDeProduccionForm.setFechaCierre(ordenDeProduccion.getFechaCierre().toString().replace(" 00:00:00.0", ""));
        }                
        if(ordenDeProduccion.getIdCliente() != null) {
            ordenDeProduccionForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
            ordenDeProduccionForm.setIdClienteAux(ordenDeProduccion.getIdCliente().toString());
        }        
        if(ordenDeProduccion.getIdArticulo() != null) {
            ordenDeProduccionForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
            ordenDeProduccionForm.setIdArticuloAux(ordenDeProduccion.getIdArticulo().toString());
        }        
        if(ordenDeProduccion.getIdFichaTecnica() != null) {
            ordenDeProduccionForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
            ordenDeProduccionForm.setIdFichaTecnicaAux(ordenDeProduccion.getIdFichaTecnica().toString());
        }                
        if(ordenDeProduccion.getIdUnidadVenta() != null) {
            ordenDeProduccionForm.setIdUnidadVenta(ordenDeProduccion.getIdUnidadVenta().toString());
        }                
        if(ordenDeProduccion.getIdUsuarioAlta() != null) {
            ordenDeProduccionForm.setIdUsuarioAlta(ordenDeProduccion.getIdUsuarioAlta().toString());
        }                
        if(ordenDeProduccion.getIdUsuarioCierre() != null) {
            ordenDeProduccionForm.setIdUsuarioCierre(ordenDeProduccion.getIdUsuarioCierre().toString());
        }                        
        if(ordenDeProduccion.getObservaciones() != null && !ordenDeProduccion.getObservaciones().isEmpty()) {
            ordenDeProduccionForm.setObservaciones(ordenDeProduccion.getObservaciones());
        }        
        if(ordenDeProduccion.getEstado() != null && !ordenDeProduccion.getEstado().isEmpty()) {
            ordenDeProduccionForm.setEstado(ordenDeProduccion.getEstado());
        }        
        if(ordenDeProduccion.getCantidadAProducir() != null) {
            ordenDeProduccionForm.setCantidadAProducir(ordenDeProduccion.getCantidadAProducir().toString());
        }        
        
        ordenDeProduccionForm.setOperacion(operacion);
        
        ordenDeProduccionForm.setAction("remove");
        model.addAttribute("ordenDeProduccionForm", ordenDeProduccionForm);  
        model.addAttribute("titleOrdenDeProduccion", "Eliminar Orden de Producción");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("ordenDeProduccionName", ordenDeProduccion.getId() + " - " + ordenDeProduccion.getIdCliente() + " - " + ordenDeProduccion.getIdArticulo());        
        
        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> unidadVentas = new LinkedHashMap<String,String>();
        List<TipoModel> unidadVentasModel = tipoService.getByType("unidadVentaArticulo");

        if(unidadVentasModel != null && !unidadVentasModel.isEmpty()){
            for(TipoModel tipoModel :unidadVentasModel) {
                unidadVentas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }

        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        Map<String,String> articulosFichaTecnica = new LinkedHashMap<String,String>();
        List<ArticuloFichaTecnicaModel> articulosFichaTecnicaModel = articuloFichaTecnicaService.getAll();

        if(articulosFichaTecnicaModel != null && !articulosFichaTecnicaModel.isEmpty()){
            for(ArticuloFichaTecnicaModel articuloFichaTecnicaModel :articulosFichaTecnicaModel) {
                articulosFichaTecnica.put(articuloFichaTecnicaModel.getId().toString(), articuloFichaTecnicaModel.getVersion().toString());
            }
        }
        
        List<OrdenDeProduccionDto> ordenDeProduccionesDtos = new ArrayList<OrdenDeProduccionDto>();
        for(OrdenDeProduccionModel ordenDeProduccionModel: ordenDeProducciones) {
            OrdenDeProduccionDto ordenDeProduccionDto = new OrdenDeProduccionDto();
            ordenDeProduccionDto.setPk(ordenDeProduccionModel.getId().toString());
            ordenDeProduccionDto.setFechaAlta(ordenDeProduccionModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeProduccionDto.setEstado(ordenDeProduccionModel.getEstado());
            if(ordenDeProduccionModel.getIdCliente() != null) {
                ordenDeProduccionDto.setCliente(clientes.get(ordenDeProduccionModel.getIdCliente().toString()));
            }
            if(ordenDeProduccionModel.getIdArticulo() != null) {
                ordenDeProduccionDto.setArticulo(articulos.get(ordenDeProduccionModel.getIdArticulo().toString()));
            }
            if(ordenDeProduccionModel.getIdFichaTecnica() != null) {
                ordenDeProduccionDto.setVersionFichaTecnica(articulosFichaTecnica.get(ordenDeProduccionModel.getIdFichaTecnica().toString()));
            }
            if(ordenDeProduccionModel.getCantidadAProducir() != null) {
                ordenDeProduccionDto.setCantidadAProducir(ordenDeProduccionModel.getCantidadAProducir().toString());
            }
            if(ordenDeProduccionModel.getEstado().equalsIgnoreCase("Nuevo")) {
                ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("true");    
            } else {
                ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("false");    
            }
            
            ordenDeProduccionesDtos.add(ordenDeProduccionDto);
            
            
        }
                       
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("unidadVentaList", unidadVentas);        
        model.addAttribute("ordenDeProducciones", ordenDeProduccionesDtos);
                                                                                       
        return "/ordendeproduccion/ordendeproduccion";               
             
    }    
    
    @RequestMapping(value = "/ordenDeProduccion/view/{ordenDeProduccionpk}", method = RequestMethod.GET)
    public String viewOrdenDeProduccion(@PathVariable String ordenDeProduccionpk, HttpServletRequest req, ModelMap model) throws Exception {
        
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "view";        
        String displayActionButton = "none";
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionpk));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
                         
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            rol = "deposito";
        }

        
        OrdenDeProduccionForm ordenDeProduccionForm = new OrdenDeProduccionForm();
        ordenDeProduccionForm.setPk(ordenDeProduccion.getId().toString());
        if(ordenDeProduccion.getFechaAlta() != null) {
            ordenDeProduccionForm.setFechaAlta(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeProduccion.getFechaPedido() != null) {
            ordenDeProduccionForm.setFechaPedido(ordenDeProduccion.getFechaPedido().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeProduccion.getFechaCierre() != null) {
            ordenDeProduccionForm.setFechaCierre(ordenDeProduccion.getFechaCierre().toString().replace(" 00:00:00.0", ""));
        }                
        if(ordenDeProduccion.getIdCliente() != null) {
            ordenDeProduccionForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
            ordenDeProduccionForm.setIdClienteAux(ordenDeProduccion.getIdCliente().toString());
        }        
        if(ordenDeProduccion.getIdArticulo() != null) {
            ordenDeProduccionForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
            ordenDeProduccionForm.setIdArticuloAux(ordenDeProduccion.getIdArticulo().toString());
        }        
        if(ordenDeProduccion.getIdFichaTecnica() != null) {
            ordenDeProduccionForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
            ordenDeProduccionForm.setIdFichaTecnicaAux(ordenDeProduccion.getIdFichaTecnica().toString());
        }                
        if(ordenDeProduccion.getIdUnidadVenta() != null) {
            ordenDeProduccionForm.setIdUnidadVenta(ordenDeProduccion.getIdUnidadVenta().toString());
        }                
        if(ordenDeProduccion.getIdUsuarioAlta() != null) {
            ordenDeProduccionForm.setIdUsuarioAlta(ordenDeProduccion.getIdUsuarioAlta().toString());
        }                
        if(ordenDeProduccion.getIdUsuarioCierre() != null) {
            ordenDeProduccionForm.setIdUsuarioCierre(ordenDeProduccion.getIdUsuarioCierre().toString());
        }                        
        if(ordenDeProduccion.getObservaciones() != null && !ordenDeProduccion.getObservaciones().isEmpty()) {
            ordenDeProduccionForm.setObservaciones(ordenDeProduccion.getObservaciones());
        }        
        if(ordenDeProduccion.getEstado() != null && !ordenDeProduccion.getEstado().isEmpty()) {
            ordenDeProduccionForm.setEstado(ordenDeProduccion.getEstado());
        }        
        if(ordenDeProduccion.getCantidadAProducir() != null) {
            ordenDeProduccionForm.setCantidadAProducir(ordenDeProduccion.getCantidadAProducir().toString());
        }        
        
        ordenDeProduccionForm.setOperacion(operacion);
        
        ordenDeProduccionForm.setAction("view");
        model.addAttribute("ordenDeProduccionForm", ordenDeProduccionForm);  
        model.addAttribute("titleOrdenDeProduccion", "Ver Orden de Producción");
        model.addAttribute("buttonLabel", "Ver");
        model.addAttribute("ordenDeProduccionName", ordenDeProduccion.getId() + " - " + ordenDeProduccion.getIdCliente() + " - " + ordenDeProduccion.getIdArticulo());        
        
        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> unidadVentas = new LinkedHashMap<String,String>();
        List<TipoModel> unidadVentasModel = tipoService.getByType("unidadVentaArticulo");

        if(unidadVentasModel != null && !unidadVentasModel.isEmpty()){
            for(TipoModel tipoModel :unidadVentasModel) {
                unidadVentas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }

        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        Map<String,String> articulosFichaTecnica = new LinkedHashMap<String,String>();
        List<ArticuloFichaTecnicaModel> articulosFichaTecnicaModel = articuloFichaTecnicaService.getAll();

        if(articulosFichaTecnicaModel != null && !articulosFichaTecnicaModel.isEmpty()){
            for(ArticuloFichaTecnicaModel articuloFichaTecnicaModel :articulosFichaTecnicaModel) {
                articulosFichaTecnica.put(articuloFichaTecnicaModel.getId().toString(), articuloFichaTecnicaModel.getVersion().toString());
            }
        }
        
        List<OrdenDeProduccionDto> ordenDeProduccionesDtos = new ArrayList<OrdenDeProduccionDto>();
        for(OrdenDeProduccionModel ordenDeProduccionModel: ordenDeProducciones) {
            OrdenDeProduccionDto ordenDeProduccionDto = new OrdenDeProduccionDto();
            ordenDeProduccionDto.setPk(ordenDeProduccionModel.getId().toString());
            ordenDeProduccionDto.setFechaAlta(ordenDeProduccionModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeProduccionDto.setEstado(ordenDeProduccionModel.getEstado());
            if(ordenDeProduccionModel.getIdCliente() != null) {
                ordenDeProduccionDto.setCliente(clientes.get(ordenDeProduccionModel.getIdCliente().toString()));
            }
            if(ordenDeProduccionModel.getIdArticulo() != null) {
                ordenDeProduccionDto.setArticulo(articulos.get(ordenDeProduccionModel.getIdArticulo().toString()));
            }
            if(ordenDeProduccionModel.getIdFichaTecnica() != null) {
                ordenDeProduccionDto.setVersionFichaTecnica(articulosFichaTecnica.get(ordenDeProduccionModel.getIdFichaTecnica().toString()));
            }
            if(ordenDeProduccionModel.getCantidadAProducir() != null) {
                ordenDeProduccionDto.setCantidadAProducir(ordenDeProduccionModel.getCantidadAProducir().toString());
            }
            if(ordenDeProduccionModel.getEstado().equalsIgnoreCase("Nuevo")) {
                ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("true");    
            } else {
                ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("false");    
            }
            
            ordenDeProduccionesDtos.add(ordenDeProduccionDto);
            
            
        }
                       
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("unidadVentaList", unidadVentas);        
        model.addAttribute("ordenDeProducciones", ordenDeProduccionesDtos);
                                                                                       
        return "/ordendeproduccion/ordendeproduccion";            
    }
            
    @RequestMapping(value = "/ordenDeProduccion/setStatusOpenOrdenProduccion/{ordenDeProduccionPk}", method = RequestMethod.GET)
    public String setStatusOpenOrdenCompra(@PathVariable String ordenDeProduccionPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null || ordenDeProduccionPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(ordenDeProduccion.getIdCliente() == null) {
            model.addAttribute("errorMessage", "Error: debe seleccionar cliente antes de pasar a estado Abierto");
            return "/error";            
        }
        
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Nuevo")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeProduccion.getEstado());
            return "/error";
        }            
                
        ordenDeProduccion.setEstado("Abierto");
        ordenDeProduccion.setFechaAbierta(new Date());
        ordenDeProduccion.setIdUsuarioAbierta(user.getId());
        
        ordenDeProduccionService.save(ordenDeProduccion);
        
        return "redirect:/ordenDeProduccion";                         
        
    }       
    
    /*
    @RequestMapping(value = "/ordenDeProduccion/setStatusCloseOrdenCompra/{ordenDeProduccionPk}", method = RequestMethod.GET)
    public String setStatusCoseOrdenCompra(@PathVariable String ordenDeProduccionPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null || ordenDeProduccionPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Completado")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeProduccion.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        OrdenDeProduccionItemService ordenDeProduccionItemService = new OrdenDeProduccionItemServiceImpl();        
        List<OrdenDeProduccionItemModel> items = ordenDeProduccionItemService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        Date today = new Date();
        
        ordenDeProduccion.setFechaCierreOrden(today);
        ordenDeProduccion.setIdUsuarioCierre(user.getId());
        ordenDeProduccion.setEstado("Cerrado");
        ordenDeProduccionService.save(ordenDeProduccion);
        
        return "redirect:/ordenDeProduccion";                         
        
    }    
    
    @RequestMapping(value = "/ordenDeProduccion/completarOrden/{ordenDeProduccionpk}", method = RequestMethod.GET)
    public String completarOrdenObservacionesOrdenDeProduccion(@PathVariable String ordenDeProduccionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "completar";        
        String displayActionButton = "block";
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionpk));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto")) {
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
        
        OrdenDeProduccionForm ordenDeProduccionForm = new OrdenDeProduccionForm();
        ordenDeProduccionForm.setPk(ordenDeProduccion.getId().toString());
        if(ordenDeProduccion.getFechaAlta() != null) {
            ordenDeProduccionForm.setFechaAlta(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeProduccion.getIdProveedor() != null) {
            ordenDeProduccionForm.setIdProveedor(ordenDeProduccion.getIdProveedor().toString());
        }        
        if(ordenDeProduccion.getObservaciones() != null && !ordenDeProduccion.getObservaciones().isEmpty()) {
            ordenDeProduccionForm.setObservaciones(ordenDeProduccion.getObservaciones());
        }        
        if(ordenDeProduccion.getReferenciaAdministrativa() != null && !ordenDeProduccion.getReferenciaAdministrativa().isEmpty()) {
            ordenDeProduccionForm.setReferenciaAdministrativa(ordenDeProduccion.getReferenciaAdministrativa());
        }                        
        if(ordenDeProduccion.getEstado() != null && !ordenDeProduccion.getEstado().isEmpty()) {
            ordenDeProduccionForm.setEstado(ordenDeProduccion.getEstado());
        }        
        
        ordenDeProduccionForm.setOperacion(operacion);
        ordenDeProduccionForm.setEditObservaciones("true");
                
        ordenDeProduccionForm.setAction("edit");
        model.addAttribute("ordenDeProduccionForm", ordenDeProduccionForm);  
        model.addAttribute("titleOrdenDeProduccion", "Completar Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeProduccionName", ordenDeProduccion.getId() + " - " + ordenDeProduccion.getIdProveedor());        
        
        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<OrdenDeProduccionDto> ordenDeProduccionesDtos = new ArrayList<OrdenDeProduccionDto>();
        for(OrdenDeProduccionModel ordenDeProduccionModel: ordenDeProducciones) {
            OrdenDeProduccionDto ordenDeProduccionDto = new OrdenDeProduccionDto();
            ordenDeProduccionDto.setPk(ordenDeProduccionModel.getId().toString());
            ordenDeProduccionDto.setFechaAlta(ordenDeProduccionModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeProduccionDto.setEstado(ordenDeProduccionModel.getEstado());
            ordenDeProduccionDto.setProveedor(proveedores.get(ordenDeProduccionModel.getIdProveedor().toString()));
            
            ordenDeProduccionesDtos.add(ordenDeProduccionDto);
        }
                               
        model.addAttribute("displayUser", "none");
                
        model.addAttribute("action", "editObservaciones");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("ordenDeProducciones", ordenDeProduccionesDtos);        
                                                        
        return "/ordendecompra/ordendecompra";        
    }    
*/
    
    @ResponseBody
    @RequestMapping(value = "/ordenDeProduccion/getArticulosByCliente/{idCliente}", method = RequestMethod.GET)
    public List<ArticuloBean> getMateriaPrimaByProveedor(@PathVariable String idCliente, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ArticuloBean> result = new ArrayList<ArticuloBean>();
        if(idCliente != null && !idCliente.isEmpty()) {
            ArticuloService articuloService = new ArticuloServiceImpl();
            List<ArticuloModel> articulos = articuloService.getAllByCliente(Integer.valueOf(idCliente));
            if(!articulos.isEmpty()) {
                for(ArticuloModel articulo: articulos) {
                    ArticuloBean bean = new ArticuloBean();
                    bean.setId(articulo.getId().toString());
                    bean.setNombre(articulo.getDenominacion());
                    
                    result.add(bean);
                }
            }
        }
        
        return result;
    }    
    
    @ResponseBody
    @RequestMapping(value = "/ordenDeProduccion/getFichaTecnicaByArticulo/{idArticulo}", method = RequestMethod.GET)
    public List<ArticuloFichaTecnicaBean> getFichaTecnicaByArticulo(@PathVariable String idArticulo, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ArticuloFichaTecnicaBean> result = new ArrayList<ArticuloFichaTecnicaBean>();
        if(idArticulo != null && !idArticulo.isEmpty()) {
            ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
            List<ArticuloFichaTecnicaModel> fichasTecnicas = articuloFichaTecnicaService.getAllByArticulo(Integer.valueOf(idArticulo));
            if(!fichasTecnicas.isEmpty()) {
                for(ArticuloFichaTecnicaModel fichaTecnica: fichasTecnicas) {
                    ArticuloFichaTecnicaBean bean = new ArticuloFichaTecnicaBean();
                    bean.setId(fichaTecnica.getId().toString());
                    bean.setNombre(fichaTecnica.getVersion().toString());
                    
                    result.add(bean);
                }
            }
        }
        
        return result;
    }
    
    @RequestMapping(value = "/deposito", method = RequestMethod.GET)
    public String getHomeDeposito(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        List<OrdenDepositoDto> ordenDepositoDto = ordenDeProduccionBobinaService.getAllByDeposito();
        System.out.println(ordenDepositoDto.size());
        
        ArticuloService articuloService = new ArticuloServiceImpl(); 
        List<ArticuloModel> articulosModel = articuloService.getAll();
        Map<String, String> articulos = new LinkedHashMap<String, String>();

        if (articulosModel != null && !articulosModel.isEmpty()) {
            for (ArticuloModel articuloModel : articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }
        model.addAttribute("articuloList", articulos);
        model.addAttribute("depositos", ordenDepositoDto);
       
   
        return "/ingresaradeposito/deposito";
    }
}
