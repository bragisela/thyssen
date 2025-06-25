/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.GraficoBobinaDetalleForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.GraficoBobinaDetalleDto;
import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.GraficoBobinaDetalleModel;
import com.empresa.thyssenplastic.model.GraficoBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ArticuloFichaTecnicaService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.GraficoBobinaDetalleService;
import com.empresa.thyssenplastic.service.GraficoBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloFichaTecnicaServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.GraficoBobinaDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.GraficoBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author gusta
 */
@Controller
public class GraficoBobinaDetalleController {


    @RequestMapping(value = "/graficoBobinaDetalle/{idGraficoBobina}/tipoIngreso/{tipoIngreso}", method = RequestMethod.GET)
    public String getHomeGraficoBobinaDetalle(@PathVariable String idGraficoBobina, @PathVariable String tipoIngreso, HttpServletRequest req, ModelMap model) {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(idGraficoBobina == null && idGraficoBobina.isEmpty()) {
            model.addAttribute("errorMessage", "Error:  grafico bobina inválido");         
            return "/error";                
        }

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();   
        GraficoBobinaModel graficoBobina = graficoBobinaService.getByPk(Integer.valueOf(idGraficoBobina));
        
        if(graficoBobina == null) {
            model.addAttribute("errorMessage", "Error:  grafico bobina no encontrado");         
            return "/error";                            
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(graficoBobina.getIdOrdenDeProduccionBobina());

        if(bobina == null) {
            model.addAttribute("errorMessage", "Error: bobina no encontrada");         
            return "/error";                
        }

        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
        
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: bobina no encontrada");         
            return "/error";                            
        }

        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        String rol = "";    
        String operacion = "";        
        String displayActionButton = "none";
        
        GraficoBobinaDetalleForm graficoBobinaDetalleForm = new GraficoBobinaDetalleForm();
        graficoBobinaDetalleForm.setPk("-1");
        graficoBobinaDetalleForm.setAction("add");
        graficoBobinaDetalleForm.setIdBobina(bobina.getId().toString());
        graficoBobinaDetalleForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
        graficoBobinaDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        graficoBobinaDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        graficoBobinaDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());        
        graficoBobinaDetalleForm.setPesoCono(bobina.getPesoCono().toString());
        graficoBobinaDetalleForm.setPesoTotal(bobina.getPesoTotal().toString());
        graficoBobinaDetalleForm.setPesoNeto(bobina.getPesoNeto().toString());
        graficoBobinaDetalleForm.setIdGraficoBobina(graficoBobina.getId().toString());

        if(tipoIngreso != null) {
            graficoBobinaDetalleForm.setFormato(tipoIngreso);
        }
        
        if(user.getRol() == Utils.ROL_OFICINA || user.getRol() == Utils.ROL_PLANTA) {
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
                
        graficoBobinaDetalleForm.setOperacion(operacion);
        
        model.addAttribute("graficoBobinaDetalleForm", graficoBobinaDetalleForm);  
        model.addAttribute("titleGraficoBobinaDetalle", "Nuevo Gráfico Bobina");  
        model.addAttribute("buttonLabel", "Guardar");
        
        GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();   
        List<GraficoBobinaDetalleModel> graficoBobinaDetalles = graficoBobinaDetalleService.getByIdGraficoBobina(graficoBobina.getId());
    
        
        List<GraficoBobinaDetalleDto> graficoBobinaDetallesDtos = new ArrayList<GraficoBobinaDetalleDto>();
        for(GraficoBobinaDetalleModel graficoBobinaDetalle: graficoBobinaDetalles) {
            GraficoBobinaDetalleDto graficoBobinaDetalleDto = new GraficoBobinaDetalleDto();
            graficoBobinaDetalleDto.setPk(graficoBobinaDetalle.getId().toString());
            graficoBobinaDetalleDto.setFechaAlta(graficoBobinaDetalle.getFechaAlta().toString().replace(".0", ""));
            graficoBobinaDetalleDto.setValor(graficoBobinaDetalle.getValor().toString());
            graficoBobinaDetalleDto.setAngulo(graficoBobinaDetalle.getAngulo().toString());
            graficoBobinaDetalleDto.setMedicion(graficoBobinaDetalle.getMedicion().toString());
                            
            graficoBobinaDetallesDtos.add(graficoBobinaDetalleDto);
        }
        
        Collections.reverse(graficoBobinaDetallesDtos);
        
        model.addAttribute("idGraficoBobina", graficoBobina.getId().toString());               
        model.addAttribute("graficoBobinaFecha", graficoBobina.getFechaAlta().toString().replace(".0", ""));               
        model.addAttribute("idBobina", bobina.getCodigo());                
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());                
        model.addAttribute("idOrdenDeProduccionBobina", bobina.getId().toString());                        
        model.addAttribute("idArticulo", articulo.getId());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());        
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
                       
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("operacion", operacion);
        model.addAttribute("displayActionButton", displayActionButton);        
        model.addAttribute("graficoBobinaDetalles", graficoBobinaDetallesDtos);        
                
        return "/ordendeproduccion/ordendeproduccionbobinagraficodetalle";
    }
    
    @RequestMapping(value = "/graficoBobinaDetalle/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveGraficoBobinaDetalle(@ModelAttribute("graficoBobinaDetalleForm")GraficoBobinaDetalleForm graficoBobinaDetalleForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(graficoBobinaDetalleForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: usuario no puede realizar esta operacion");
            return modelAndView;            
        }
        
        String operacion = graficoBobinaDetalleForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta") && !operacion.equalsIgnoreCase("remove"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }        
        if(graficoBobinaDetalleForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }
        
        if(graficoBobinaDetalleForm.getIdBobina() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error bobina null");
            return modelAndView;                                    
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();        
        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(graficoBobinaDetalleForm.getIdBobina()));
        if(bobina == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error bobina no encontrada");
            return modelAndView;                                                
        }

        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();              
        GraficoBobinaModel graficoBobina = graficoBobinaService.getByPk(Integer.valueOf(graficoBobinaDetalleForm.getIdGraficoBobina()));
        if(graficoBobina == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error grafico bobina no encontrado");
            return modelAndView;                                                
        }
                
        GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();        
        GraficoBobinaDetalleModel graficoBobinaDetalleAngulo = graficoBobinaDetalleService.getMaximoAnguloGraficoBobinaDetalle(graficoBobina.getId());
        GraficoBobinaDetalleModel graficoBobinaDetalleMedicion = graficoBobinaDetalleService.obtenerUltimaMedicion(graficoBobina.getId());
        Double anguloMaximo = 0.0;
        Integer medicion = 1;
        if(graficoBobinaDetalleAngulo != null && graficoBobinaDetalleAngulo.getAngulo() != null) {
            anguloMaximo = graficoBobinaDetalleAngulo.getAngulo() + 1;
        }
        if(graficoBobinaDetalleMedicion != null && graficoBobinaDetalleMedicion.getMedicion() != null) {
            medicion = graficoBobinaDetalleMedicion.getMedicion() + 1;
        }
        String id = graficoBobinaDetalleForm.getPk();
            
        GraficoBobinaDetalleModel graficoBobinaDetalleModel = null;
        if(id.equalsIgnoreCase("-1")) {
            graficoBobinaDetalleModel = new GraficoBobinaDetalleModel();
            graficoBobinaDetalleModel.setIdGraficoBobina(graficoBobina.getId());
            graficoBobinaDetalleModel.setMedicion(medicion);
            graficoBobinaDetalleModel.setFechaAlta(new Date());
            graficoBobinaDetalleModel.setIdUsuario(user.getId());
            graficoBobinaDetalleModel.setAngulo(anguloMaximo);
        } else {
            graficoBobinaDetalleModel = graficoBobinaDetalleService.getByPk(Integer.valueOf(id));
            if(graficoBobinaDetalleModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de graficoBobinaDetalle inválido.");
                return modelAndView;                
            } 
        }        
        if(graficoBobinaDetalleForm.getValor() != null) {
            if(graficoBobinaDetalleForm.getFormato() != null && graficoBobinaDetalleForm.getFormato().equalsIgnoreCase("scanner")) {
                graficoBobinaDetalleModel.setValor(Double.valueOf(graficoBobinaDetalleForm.getValor()) * 1000);
            } else {
                graficoBobinaDetalleModel.setValor(Double.valueOf(graficoBobinaDetalleForm.getValor()));
            }
        } else {
            graficoBobinaDetalleModel.setValor(null);
        }
        /*
        if(graficoBobinaDetalleForm.getAngulo() != null) {
            graficoBobinaDetalleModel.setAngulo(Double.valueOf(graficoBobinaDetalleForm.getAngulo()));
        } else {
            graficoBobinaDetalleModel.setAngulo(null);
        }
        */
        if(graficoBobinaDetalleForm.getAction().equalsIgnoreCase("add") || graficoBobinaDetalleForm.getAction().equalsIgnoreCase("edit")) {
            graficoBobinaDetalleService.save(graficoBobinaDetalleModel);
        } else {
            if(graficoBobinaDetalleForm.getAction().equalsIgnoreCase("remove")) {
                if(graficoBobinaDetalleModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de graficoBobinaDetalle inválido.");
                    return modelAndView;                                    
                }                
                graficoBobinaDetalleService.delete(graficoBobinaDetalleModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
            
        String tipoIngreso = "manual";
        if(graficoBobinaDetalleForm.getFormato() != null) {
            tipoIngreso = graficoBobinaDetalleForm.getFormato();
        }
        modelAndView.setViewName("redirect:/graficoBobinaDetalle/"+graficoBobina.getId()+"/tipoIngreso/"+tipoIngreso);

        return modelAndView; 
    }

    @RequestMapping(value = "/graficoBobinaDetalle/edit/{graficoBobinaDetallepk}", method = RequestMethod.GET)
    public String editGraficoBobinaDetalle(@PathVariable String graficoBobinaDetallepk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(graficoBobinaDetallepk == null) {
            model.addAttribute("errorMessage", "Error: GraficoBobinaDetalle inválido");         
            return "/error/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no puede realizar esta operacion");         
            return "/error/error";                
        }
        
        GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();   
        GraficoBobinaDetalleModel graficoBobinaDetalle = graficoBobinaDetalleService.getByPk(Integer.valueOf(graficoBobinaDetallepk));
        if(graficoBobinaDetalle == null) {
            model.addAttribute("errorMessage", "Error: GraficoBobinaDetalle inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();              
        GraficoBobinaModel graficoBobina = graficoBobinaService.getByPk(graficoBobinaDetalle.getIdGraficoBobina());
        if(graficoBobina == null) {
            model.addAttribute("errorMessage", "Error: Grafico Bobina inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(graficoBobina.getIdOrdenDeProduccionBobina());

        if(bobina == null) {
            model.addAttribute("errorMessage", "Error: bobina no encontrada");         
            return "/error";                
        }

        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
        
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: bobina no encontrada");         
            return "/error";                            
        }

        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        String rol = "";    
        String operacion = "";        
        String displayActionButton = "none";
        
        GraficoBobinaDetalleForm graficoBobinaDetalleForm = new GraficoBobinaDetalleForm();        
        graficoBobinaDetalleForm.setPk(graficoBobinaDetalle.getId().toString());
        graficoBobinaDetalleForm.setAction("add");
        graficoBobinaDetalleForm.setIdBobina(bobina.getId().toString());
        graficoBobinaDetalleForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
        graficoBobinaDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        graficoBobinaDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        graficoBobinaDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());        
        graficoBobinaDetalleForm.setPesoCono(bobina.getPesoCono().toString());
        graficoBobinaDetalleForm.setPesoTotal(bobina.getPesoTotal().toString());
        graficoBobinaDetalleForm.setPesoNeto(bobina.getPesoNeto().toString());
        graficoBobinaDetalleForm.setIdGraficoBobina(graficoBobina.getId().toString());
        
        if(graficoBobinaDetalle.getValor()!= null) {
            graficoBobinaDetalleForm.setValor(graficoBobinaDetalle.getValor().toString());
        }
        if(graficoBobinaDetalle.getAngulo() != null) {
            graficoBobinaDetalleForm.setAngulo(graficoBobinaDetalle.getAngulo().toString());
        }
        
        if(user.getRol() == Utils.ROL_OFICINA || user.getRol() == Utils.ROL_PLANTA) {
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
                
        graficoBobinaDetalleForm.setOperacion(operacion);
        
        model.addAttribute("graficoBobinaDetalleForm", graficoBobinaDetalleForm);  
        model.addAttribute("titleGraficoBobinaDetalle", "Editar Gráfico Bobina");  
        model.addAttribute("buttonLabel", "Guardar");
        
        List<GraficoBobinaDetalleModel> graficoBobinaDetalles = graficoBobinaDetalleService.getByIdGraficoBobina(graficoBobina.getId());
        
        List<GraficoBobinaDetalleDto> graficoBobinaDetallesDtos = new ArrayList<GraficoBobinaDetalleDto>();
        for(GraficoBobinaDetalleModel item: graficoBobinaDetalles) {
            GraficoBobinaDetalleDto graficoBobinaDetalleDto = new GraficoBobinaDetalleDto();
            graficoBobinaDetalleDto.setPk(item.getId().toString());
            graficoBobinaDetalleDto.setFechaAlta(item.getFechaAlta().toString().replace(".0", ""));
            graficoBobinaDetalleDto.setValor(item.getValor().toString());
            graficoBobinaDetalleDto.setAngulo(item.getAngulo().toString());
            graficoBobinaDetalleDto.setMedicion(graficoBobinaDetalle.getMedicion().toString());
            
                
            graficoBobinaDetallesDtos.add(graficoBobinaDetalleDto);
        }
                
        model.addAttribute("idGraficoBobina", graficoBobina.getId().toString());        
        model.addAttribute("idBobina", bobina.getCodigo());                
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());                
        model.addAttribute("idOrdenDeProduccionBobina", bobina.getId().toString());                        
        model.addAttribute("idArticulo", articulo.getId());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());        
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
                       
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("operacion", operacion);
        model.addAttribute("displayActionButton", displayActionButton);        
        model.addAttribute("graficoBobinaDetalles", graficoBobinaDetallesDtos);        
                
        return "/ordendeproduccion/ordendeproduccionbobinagraficodetalle";
                
    }
    
    @RequestMapping(value = "/graficoBobinaDetalle/deleteSelected", method = RequestMethod.POST)
    public void deleteSelected(@RequestBody Map<String, List<Integer>> payload, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!Utils.isAutenticated(req)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Sesión no válida");
            return;
        }

        List<Integer> ids = payload.get("ids");
        if (ids == null || ids.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("No se proporcionaron IDs");
            return;
        }

        try {
            GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();

            for (Integer id : ids) {
                GraficoBobinaDetalleModel detalle = new GraficoBobinaDetalleModel();
                detalle.setId(id);  
                graficoBobinaDetalleService.delete(detalle);
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Registros eliminados correctamente");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error al eliminar los registros");
        }
    }

    
    
    @RequestMapping(value = "/graficoBobinaDetalle/remove/{graficoBobinaDetallepk}", method = RequestMethod.GET)
    public String removeGraficoBobinaDetalle(@PathVariable String graficoBobinaDetallepk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(graficoBobinaDetallepk == null) {
            model.addAttribute("errorMessage", "Error: GraficoBobinaDetalle inválido");         
            return "/error/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no puede realizar esta operacion");         
            return "/error/error";                
        }
        
        GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();   
        GraficoBobinaDetalleModel graficoBobinaDetalle = graficoBobinaDetalleService.getByPk(Integer.valueOf(graficoBobinaDetallepk));
        if(graficoBobinaDetalle == null) {
            model.addAttribute("errorMessage", "Error: GraficoBobinaDetalle inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();              
        GraficoBobinaModel graficoBobina = graficoBobinaService.getByPk(graficoBobinaDetalle.getIdGraficoBobina());
        if(graficoBobina == null) {
            model.addAttribute("errorMessage", "Error: Grafico Bobina inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(graficoBobina.getIdOrdenDeProduccionBobina());

        if(bobina == null) {
            model.addAttribute("errorMessage", "Error: bobina no encontrada");         
            return "/error";                
        }

        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
        
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: bobina no encontrada");         
            return "/error";                            
        }

        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        String rol = "";    
        String operacion = "";        
        String displayActionButton = "none";
        
        GraficoBobinaDetalleForm graficoBobinaDetalleForm = new GraficoBobinaDetalleForm();        
        graficoBobinaDetalleForm.setPk(graficoBobinaDetalle.getId().toString());
        graficoBobinaDetalleForm.setAction("remove");
        graficoBobinaDetalleForm.setIdBobina(bobina.getId().toString());
        graficoBobinaDetalleForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
        graficoBobinaDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        graficoBobinaDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        graficoBobinaDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());        
        graficoBobinaDetalleForm.setPesoCono(bobina.getPesoCono().toString());
        graficoBobinaDetalleForm.setPesoTotal(bobina.getPesoTotal().toString());
        graficoBobinaDetalleForm.setPesoNeto(bobina.getPesoNeto().toString());
        graficoBobinaDetalleForm.setIdGraficoBobina(graficoBobina.getId().toString());
        
        if(graficoBobinaDetalle.getValor()!= null) {
            graficoBobinaDetalleForm.setValor(graficoBobinaDetalle.getValor().toString());
        }
        if(graficoBobinaDetalle.getAngulo() != null) {
            graficoBobinaDetalleForm.setAngulo(graficoBobinaDetalle.getAngulo().toString());
        }
        
        if(user.getRol() == Utils.ROL_OFICINA || user.getRol() == Utils.ROL_PLANTA) {
            operacion = "remove";
            displayActionButton = "block";
            rol = "oficina";
        }
                
        graficoBobinaDetalleForm.setOperacion(operacion);
        
        model.addAttribute("graficoBobinaDetalleForm", graficoBobinaDetalleForm);  
        model.addAttribute("titleGraficoBobinaDetalle", "Eliminar Gráfico Bobina");  
        model.addAttribute("buttonLabel", "Eliminar");
        
        List<GraficoBobinaDetalleModel> graficoBobinaDetalles = graficoBobinaDetalleService.getByIdGraficoBobina(graficoBobina.getId());
        
        List<GraficoBobinaDetalleDto> graficoBobinaDetallesDtos = new ArrayList<GraficoBobinaDetalleDto>();
        for(GraficoBobinaDetalleModel item: graficoBobinaDetalles) {
            GraficoBobinaDetalleDto graficoBobinaDetalleDto = new GraficoBobinaDetalleDto();
            graficoBobinaDetalleDto.setPk(item.getId().toString());
            graficoBobinaDetalleDto.setFechaAlta(item.getFechaAlta().toString().replace(".0", ""));
            graficoBobinaDetalleDto.setValor(item.getValor().toString());
            graficoBobinaDetalleDto.setAngulo(item.getAngulo().toString());
            graficoBobinaDetalleDto.setMedicion(graficoBobinaDetalle.getMedicion().toString());
            
                
            graficoBobinaDetallesDtos.add(graficoBobinaDetalleDto);
        }
                
        model.addAttribute("idGraficoBobina", graficoBobina.getId().toString());        
        model.addAttribute("idBobina", bobina.getCodigo());                
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());                
        model.addAttribute("idOrdenDeProduccionBobina", bobina.getId().toString());                        
        model.addAttribute("idArticulo", articulo.getId());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());        
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
                       
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("operacion", operacion);
        model.addAttribute("displayActionButton", displayActionButton);        
        model.addAttribute("graficoBobinaDetalles", graficoBobinaDetallesDtos);        
                
        return "/ordendeproduccion/ordendeproduccionbobinagraficodetalle";
                
    }

    
}
