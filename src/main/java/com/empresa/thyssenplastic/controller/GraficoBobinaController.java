/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.GraficoBobinaValorBean;
import com.empresa.thyssenplastic.controller.form.GraficoBobinaForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.GraficoBobinaDto;
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
import com.empresa.thyssenplastic.service.impl.GraficoBobinaDetalleServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.GraficoBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gusta
 */
@Controller
public class GraficoBobinaController {
    

    @RequestMapping(value = "/graficoBobina/{idOrdenDeProduccionBobina}", method = RequestMethod.GET)
    public String getHomeGraficoBobina(@PathVariable String idOrdenDeProduccionBobina, HttpServletRequest req, ModelMap model) {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(idOrdenDeProduccionBobina == null && idOrdenDeProduccionBobina.isEmpty()) {
            model.addAttribute("errorMessage", "Error:  bobina inválida");         
            return "/error";                
        }

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(idOrdenDeProduccionBobina));

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
        
        GraficoBobinaForm graficoBobinaForm = new GraficoBobinaForm();
        graficoBobinaForm.setPk("-1");
        graficoBobinaForm.setAction("add");
        graficoBobinaForm.setIdBobina(bobina.getId().toString());
        graficoBobinaForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
        graficoBobinaForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        graficoBobinaForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        graficoBobinaForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());        
        graficoBobinaForm.setPesoCono(bobina.getPesoCono().toString());
        graficoBobinaForm.setPesoTotal(bobina.getPesoTotal().toString());
        graficoBobinaForm.setPesoNeto(bobina.getPesoNeto().toString());
        
        if(user.getRol() == Utils.ROL_OFICINA || user.getRol() == Utils.ROL_PLANTA) {
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
                
        graficoBobinaForm.setOperacion(operacion);
        
        model.addAttribute("graficoBobinaForm", graficoBobinaForm);  
        model.addAttribute("titleGraficoBobina", "Nuevo Gráfico Bobina");  
        model.addAttribute("buttonLabel", "Guardar");
        
        GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();   
        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();   
        List<GraficoBobinaModel> graficoBobinas = graficoBobinaService.getByIdOrdenDeProduccionBobina(bobina.getId());
        
        List<GraficoBobinaDto> graficoBobinasDtos = new ArrayList<GraficoBobinaDto>();
        for(GraficoBobinaModel graficoBobina: graficoBobinas) {
            GraficoBobinaDto graficoBobinaDto = new GraficoBobinaDto();
            graficoBobinaDto.setPk(graficoBobina.getId().toString());
            graficoBobinaDto.setFechaAlta(graficoBobina.getFechaAlta().toString().replace(".0", ""));
            graficoBobinaDto.setEspesorNominal(graficoBobina.getEspesorNominal().toString());
            
            List<GraficoBobinaDetalleModel> detalle = graficoBobinaDetalleService.getByIdGraficoBobina(graficoBobina.getId());
            graficoBobinaDto.setMediciones(String.valueOf(detalle.size()));
            
            if(detalle.size() >= 5) {
                graficoBobinaDto.setTieneMediciones("true");
            } else {
                graficoBobinaDto.setTieneMediciones("false");
            }
                
            graficoBobinasDtos.add(graficoBobinaDto);
        }
                
        model.addAttribute("idBobina", bobina.getCodigo());                
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());                
        model.addAttribute("idArticulo", articulo.getId());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());        
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
                       
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("operacion", operacion);
        model.addAttribute("displayActionButton", displayActionButton);        
        model.addAttribute("graficoBobinas", graficoBobinasDtos);        
                
        return "/ordendeproduccion/ordendeproduccionbobinagrafico";
    }
    
    @RequestMapping(value = "/graficoBobina/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveGraficoBobina(@ModelAttribute("graficoBobinaForm")GraficoBobinaForm graficoBobinaForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(graficoBobinaForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: usuario no puede realizar esta operacion");
            return modelAndView;            
        }
        
        String operacion = graficoBobinaForm.getOperacion();
        
        if(operacion == null || !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }        
        if(graficoBobinaForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }
        
        if(graficoBobinaForm.getIdBobina() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error bobina null");
            return modelAndView;                                    
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();        
        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(graficoBobinaForm.getIdBobina()));
        if(bobina == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error bobina no encontrada");
            return modelAndView;                                                
        }
        
        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();        
        String id = graficoBobinaForm.getPk();
            
        GraficoBobinaModel graficoBobinaModel = null;
        if(id.equalsIgnoreCase("-1")) {
            graficoBobinaModel = new GraficoBobinaModel();
            graficoBobinaModel.setIdOrdenDeProduccionBobina(bobina.getId());
            graficoBobinaModel.setFechaAlta(new Date());
            graficoBobinaModel.setIdUsuario(user.getId());
        } else {
            graficoBobinaModel = graficoBobinaService.getByPk(Integer.valueOf(id));
            if(graficoBobinaModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de graficoBobina inválido.");
                return modelAndView;                
            } 
        }        
        if(graficoBobinaForm.getEspesorNominal() != null) {
            graficoBobinaModel.setEspesorNominal(Double.valueOf(graficoBobinaForm.getEspesorNominal()));
        } else {
            graficoBobinaModel.setEspesorNominal(null);
        }
        if(graficoBobinaForm.getEspesorNominalMas() != null) {
            graficoBobinaModel.setEspesorNominalMas(Double.valueOf(graficoBobinaForm.getEspesorNominalMas()));
        } else {
            graficoBobinaModel.setEspesorNominalMas(null);
        }
        if(graficoBobinaForm.getEspesorNominalMenos() != null) {
            graficoBobinaModel.setEspesorNominalMenos(Double.valueOf(graficoBobinaForm.getEspesorNominalMenos()));
        } else {
            graficoBobinaModel.setEspesorNominalMenos(null);
        }
        
        if(graficoBobinaForm.getAction().equalsIgnoreCase("add") || graficoBobinaForm.getAction().equalsIgnoreCase("edit")) {
            graficoBobinaService.save(graficoBobinaModel);
        } else {
            if(graficoBobinaForm.getAction().equalsIgnoreCase("remove")) {
                if(graficoBobinaModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de graficoBobina inválido.");
                    return modelAndView;                                    
                }                
                graficoBobinaService.delete(graficoBobinaModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
               
        modelAndView.setViewName("redirect:/graficoBobina/"+bobina.getId());

        return modelAndView; 
    }

    @RequestMapping(value = "/graficoBobina/edit/{graficoBobinapk}", method = RequestMethod.GET)
    public String editGraficoBobina(@PathVariable String graficoBobinapk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(graficoBobinapk == null) {
            model.addAttribute("errorMessage", "Error: GraficoBobina inválido");         
            return "/error/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no puede realizar esta operacion");         
            return "/error/error";                
        }
        
        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();   
        GraficoBobinaModel graficoBobina = graficoBobinaService.getByPk(Integer.valueOf(graficoBobinapk));
        if(graficoBobina == null) {
            model.addAttribute("errorMessage", "Error: GraficoBobina inválido. No ha sido encontrado.");         
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
        
        GraficoBobinaForm graficoBobinaForm = new GraficoBobinaForm();        
        graficoBobinaForm.setPk(graficoBobina.getId().toString());
        graficoBobinaForm.setAction("add");
        graficoBobinaForm.setIdBobina(bobina.getId().toString());
        graficoBobinaForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
        graficoBobinaForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        graficoBobinaForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        graficoBobinaForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());        
        graficoBobinaForm.setPesoCono(bobina.getPesoCono().toString());
        graficoBobinaForm.setPesoTotal(bobina.getPesoTotal().toString());
        graficoBobinaForm.setPesoNeto(bobina.getPesoNeto().toString());
        
        if(graficoBobina.getEspesorNominal() != null) {
            graficoBobinaForm.setEspesorNominal(graficoBobina.getEspesorNominal().toString());
        }
        if(graficoBobina.getEspesorNominalMas() != null) {
            graficoBobinaForm.setEspesorNominalMas(graficoBobina.getEspesorNominalMas().toString());
        }
        if(graficoBobina.getEspesorNominalMenos() != null) {
            graficoBobinaForm.setEspesorNominalMenos(graficoBobina.getEspesorNominalMenos().toString());
        }
        
        if(user.getRol() == Utils.ROL_OFICINA || user.getRol() == Utils.ROL_PLANTA) {
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
                
        graficoBobinaForm.setOperacion(operacion);
        
        model.addAttribute("graficoBobinaForm", graficoBobinaForm);  
        model.addAttribute("titleGraficoBobina", "Editar Gráfico Bobina");  
        model.addAttribute("buttonLabel", "Guardar");
        
        GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();   
        List<GraficoBobinaModel> graficoBobinas = graficoBobinaService.getByIdOrdenDeProduccionBobina(bobina.getId());
        
        List<GraficoBobinaDto> graficoBobinasDtos = new ArrayList<GraficoBobinaDto>();
        for(GraficoBobinaModel item: graficoBobinas) {
            GraficoBobinaDto graficoBobinaDto = new GraficoBobinaDto();
            graficoBobinaDto.setPk(item.getId().toString());
            graficoBobinaDto.setFechaAlta(item.getFechaAlta().toString().replace(".0", ""));
            graficoBobinaDto.setEspesorNominal(item.getEspesorNominal().toString());
            
            List<GraficoBobinaDetalleModel> detalle = graficoBobinaDetalleService.getByIdGraficoBobina(item.getId());
            graficoBobinaDto.setMediciones(String.valueOf(detalle.size()));
            
            if(detalle.size() >= 5) {
                graficoBobinaDto.setTieneMediciones("true");
            } else {
                graficoBobinaDto.setTieneMediciones("false");
            }
                
            graficoBobinasDtos.add(graficoBobinaDto);
        }
                
        model.addAttribute("idBobina", bobina.getCodigo());                
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());                
        model.addAttribute("idArticulo", articulo.getId());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());        
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
                       
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("operacion", operacion);
        model.addAttribute("displayActionButton", displayActionButton);        
        model.addAttribute("graficoBobinas", graficoBobinasDtos);        
        model.addAttribute("idOrdenDeProduccionBobina", graficoBobina.getIdOrdenDeProduccionBobina());        
                
        return "/ordendeproduccion/ordendeproduccionbobinagrafico";
                
    }
    
    @RequestMapping(value = "/graficoBobina/remove/{graficoBobinapk}", method = RequestMethod.GET)
    public String removeGraficoBobina(@PathVariable String graficoBobinapk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(graficoBobinapk == null) {
            model.addAttribute("errorMessage", "Error: GraficoBobina inválido");         
            return "/error/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no puede realizar esta operacion");         
            return "/error/error";                
        }
        
        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();   
        GraficoBobinaModel graficoBobina = graficoBobinaService.getByPk(Integer.valueOf(graficoBobinapk));
        if(graficoBobina == null) {
            model.addAttribute("errorMessage", "Error: GraficoBobina inválido. No ha sido encontrado.");         
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
        
        GraficoBobinaForm graficoBobinaForm = new GraficoBobinaForm();        
        graficoBobinaForm.setPk(graficoBobina.getId().toString());
        graficoBobinaForm.setAction("remove");
        graficoBobinaForm.setIdBobina(bobina.getId().toString());
        graficoBobinaForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
        graficoBobinaForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        graficoBobinaForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        graficoBobinaForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());        
        graficoBobinaForm.setPesoCono(bobina.getPesoCono().toString());
        graficoBobinaForm.setPesoTotal(bobina.getPesoTotal().toString());
        graficoBobinaForm.setPesoNeto(bobina.getPesoNeto().toString());
        
        if(graficoBobina.getEspesorNominal() != null) {
            graficoBobinaForm.setEspesorNominal(graficoBobina.getEspesorNominal().toString());
        }
        if(graficoBobina.getEspesorNominalMas() != null) {
            graficoBobinaForm.setEspesorNominalMas(graficoBobina.getEspesorNominalMas().toString());
        }
        if(graficoBobina.getEspesorNominalMenos() != null) {
            graficoBobinaForm.setEspesorNominalMenos(graficoBobina.getEspesorNominalMenos().toString());
        }
        
        if(user.getRol() == Utils.ROL_OFICINA || user.getRol() == Utils.ROL_PLANTA) {
            operacion = "remove";
            displayActionButton = "block";
            rol = "oficina";
        }
                
        graficoBobinaForm.setOperacion(operacion);
        
        model.addAttribute("graficoBobinaForm", graficoBobinaForm);  
        model.addAttribute("titleGraficoBobina", "Eliminar Gráfico Bobina");  
        model.addAttribute("buttonLabel", "Eliminar");
        
        GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();   
        List<GraficoBobinaModel> graficoBobinas = graficoBobinaService.getByIdOrdenDeProduccionBobina(bobina.getId());
        
        List<GraficoBobinaDto> graficoBobinasDtos = new ArrayList<GraficoBobinaDto>();
        for(GraficoBobinaModel item: graficoBobinas) {
            GraficoBobinaDto graficoBobinaDto = new GraficoBobinaDto();
            graficoBobinaDto.setPk(item.getId().toString());
            graficoBobinaDto.setFechaAlta(item.getFechaAlta().toString().replace(".0", ""));
            graficoBobinaDto.setEspesorNominal(item.getEspesorNominal().toString());
            
            List<GraficoBobinaDetalleModel> detalle = graficoBobinaDetalleService.getByIdGraficoBobina(item.getId());
            graficoBobinaDto.setMediciones(String.valueOf(detalle.size()));
            
            if(detalle.size() >= 5) {
                graficoBobinaDto.setTieneMediciones("true");
            } else {
                graficoBobinaDto.setTieneMediciones("false");
            }
                
            graficoBobinasDtos.add(graficoBobinaDto);
        }
                
        model.addAttribute("idBobina", bobina.getCodigo());                
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());                
        model.addAttribute("idArticulo", articulo.getId());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());        
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
                       
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("operacion", operacion);
        model.addAttribute("displayActionButton", displayActionButton);        
        model.addAttribute("graficoBobinas", graficoBobinasDtos);        
        model.addAttribute("idOrdenDeProduccionBobina", graficoBobina.getIdOrdenDeProduccionBobina());        
                
        return "/ordendeproduccion/ordendeproduccionbobinagrafico";
                
    }
    
    @RequestMapping(value = "/graficoBobinaGrafico/{idGraficoBobina}", method = RequestMethod.GET)
    public String getGraficoBobinaGrafico(@PathVariable String idGraficoBobina, HttpServletRequest req, ModelMap model) {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(idGraficoBobina == null && idGraficoBobina.isEmpty()) {
            model.addAttribute("errorMessage", "Error:  bobina inválida");         
            return "/error";                
        }

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }

        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();   
        GraficoBobinaModel graficoBobina = graficoBobinaService.getByPk(Integer.valueOf(idGraficoBobina));

        if(graficoBobina == null) {
            model.addAttribute("errorMessage", "Error: bobina no encontrada");         
            return "/error";                
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(graficoBobina.getIdOrdenDeProduccionBobina()));

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
        
        GraficoBobinaForm graficoBobinaForm = new GraficoBobinaForm();
        graficoBobinaForm.setPk(graficoBobina.getId().toString());
        graficoBobinaForm.setAction("none");
        graficoBobinaForm.setIdBobina(bobina.getId().toString());
        graficoBobinaForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
        graficoBobinaForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        graficoBobinaForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        graficoBobinaForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());        
        graficoBobinaForm.setPesoCono(bobina.getPesoCono().toString());
        graficoBobinaForm.setPesoTotal(bobina.getPesoTotal().toString());
        graficoBobinaForm.setPesoNeto(bobina.getPesoNeto().toString());
        
        if(user.getRol() == Utils.ROL_OFICINA || user.getRol() == Utils.ROL_PLANTA) {
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
        
        GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();
        List<GraficoBobinaDetalleModel> detalles = graficoBobinaDetalleService.getByIdGraficoBobina(graficoBobina.getId());
        if(detalles.size() < 5) {
            model.addAttribute("errorMessage", "Error: no existen los suficientes valores para generar el gráfico");         
            return "/error";                                        
        }
        
        Double maximo = 0.0;
        Double minimo = 999999999.0;
        String promedio = "0.0";
        
        Double valor = 0.0;
        
        for(GraficoBobinaDetalleModel detalle :detalles) {                        
            if(maximo < detalle.getValor()) {
                maximo = detalle.getValor();
            }
            if(minimo > detalle.getValor()) {
                minimo = detalle.getValor();
            }  
            valor += detalle.getValor();
        }
        
        DecimalFormat df = new DecimalFormat("0.00");
        promedio = df.format(valor / detalles.size());
                
        graficoBobinaForm.setCantidadMuestras(String.valueOf(detalles.size()));
        graficoBobinaForm.setValorNominal(graficoBobina.getEspesorNominal().toString());
        graficoBobinaForm.setValorNominalMas(graficoBobina.getEspesorNominalMas().toString());
        graficoBobinaForm.setValorNominalMenos(graficoBobina.getEspesorNominalMenos().toString());
        graficoBobinaForm.setValorMaximo(maximo.toString());
        graficoBobinaForm.setValorMinimo(minimo.toString());
        graficoBobinaForm.setValorPromedio(promedio.toString());
        graficoBobinaForm.setOperacion(operacion);
                                 
        
        model.addAttribute("idGraficoBobina", graficoBobina.getId().toString());               
        model.addAttribute("graficoBobinaFecha", graficoBobina.getFechaAlta().toString().replace(".0", ""));               

        model.addAttribute("graficoBobinaForm", graficoBobinaForm);  
        model.addAttribute("titleGraficoBobina", "Ver Gráfico Polar Bobina");  
        model.addAttribute("buttonLabel", "Guardar");
                
        model.addAttribute("idBobina", bobina.getCodigo());                
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());                
        model.addAttribute("idOrdenDeProduccionBobina", bobina.getId().toString());                
        model.addAttribute("idArticulo", articulo.getId());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());        
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        model.addAttribute("cantidadMuestras", String.valueOf(detalles.size()));
        model.addAttribute("promedio", promedio.toString());
        model.addAttribute("valorMaximo", maximo.toString());
        model.addAttribute("valorMinimo", minimo.toString());
                       
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("operacion", operacion);
        model.addAttribute("displayActionButton", displayActionButton);                       
                
        return "/ordendeproduccion/ordendeproduccionbobinagraficografico";
    }
 
    @ResponseBody
    @RequestMapping(value = "/graficoBobina/getValues/{idGraficoBobina}", method = RequestMethod.GET)
    public List<GraficoBobinaValorBean> getValues(@PathVariable String idGraficoBobina, HttpServletRequest req, ModelMap model) throws Exception {
    
        System.out.println("**** GET VALUES "+idGraficoBobina+"***");
        
        List<GraficoBobinaValorBean> result = new ArrayList<GraficoBobinaValorBean>();
                
        if(idGraficoBobina != null && !idGraficoBobina.isEmpty()) {
            
            GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();   
            GraficoBobinaModel graficoBobina = graficoBobinaService.getByPk(Integer.valueOf(idGraficoBobina));

            if(graficoBobina != null) {
                
                Double maximo = 0.0;
                Double minimo = 999999999.0;
                
                GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();
                List<GraficoBobinaDetalleModel> detalles = graficoBobinaDetalleService.getByIdGraficoBobina(graficoBobina.getId());
                
                for(GraficoBobinaDetalleModel detalle :detalles) {
                    GraficoBobinaValorBean bean = new GraficoBobinaValorBean();
                    bean.setValorReal(detalle.getValor());
                    if(maximo < detalle.getValor()) {
                        maximo = detalle.getValor();
                    }
                    if(minimo > detalle.getValor()) {
                        minimo = detalle.getValor();
                    }                                        
                    result.add(bean);
                }
                
                for(GraficoBobinaValorBean bean :result) {
                    System.out.println("**** Maximo:"+graficoBobina.getEspesorNominalMas().toString()+" ***");
                    System.out.println("**** Minimo:"+graficoBobina.getEspesorNominalMenos()+" ***");
                    bean.setValorMaximo(graficoBobina.getEspesorNominalMas());
                    bean.setValorMinimo(graficoBobina.getEspesorNominalMenos());
                    bean.setValorTeorico(graficoBobina.getEspesorNominal());
                }
            }
        }
        
        
        
        System.out.println("**** SIZE:"+result.size()+" ***");
        
        return result;
    }    
    
}
