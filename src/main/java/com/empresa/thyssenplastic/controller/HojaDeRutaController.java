/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.ContactoBean;
import com.empresa.thyssenplastic.controller.form.HojaDeRutaDetalleForm;
import com.empresa.thyssenplastic.controller.form.HojaDeRutaForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.HojaDeRutaDetalleDto;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.dto.HojaDeRutaDto;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.model.HojaDeRutaDetalleModel;
import com.empresa.thyssenplastic.model.HojaDeRutaModel;
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
import com.empresa.thyssenplastic.service.ClienteDomicilioService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.DomicilioService;
import com.empresa.thyssenplastic.service.HojaDeRutaDetalleService;
import com.empresa.thyssenplastic.service.HojaDeRutaService;
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
import com.empresa.thyssenplastic.service.impl.ClienteDomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.DomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.HojaDeRutaDetalleServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.empresa.thyssenplastic.service.impl.HojaDeRutaServiceImpl;
import com.empresa.thyssenplastic.service.impl.LocalidadServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoServiceImpl;
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
public class HojaDeRutaController {
    

    @RequestMapping(value = "/hojaDeRuta", method = RequestMethod.GET)
    public String getHomeHojaDeRuta(HttpServletRequest req, ModelMap model) {

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
        
        HojaDeRutaForm hojaDeRutaForm = new HojaDeRutaForm();
        hojaDeRutaForm.setPk("-1");
        hojaDeRutaForm.setAction("add");        
        hojaDeRutaForm.setEstadoLabel("Nuevo");
        hojaDeRutaForm.setIdContacto("-1");
        hojaDeRutaForm.setIdProveedor("-1");

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        hojaDeRutaForm.setFecha(fecha);

        if(user.getRol() == Utils.ROL_OFICINA) {
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
                
        hojaDeRutaForm.setOperacion(operacion);
        
        model.addAttribute("hojaDeRutaForm", hojaDeRutaForm);  
        model.addAttribute("titleHojaDeRuta", "Nuevo Hoja De Ruta");  
        model.addAttribute("buttonLabel", "Guardar");

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
        
        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();   
        List<HojaDeRutaModel> hojasDeRuta = hojaDeRutaService.getAll();


        List<HojaDeRutaDto> hojasDeRutaDtos = new ArrayList<HojaDeRutaDto>();
        for(HojaDeRutaModel hojaDeRuta: hojasDeRuta) {
            
            HojaDeRutaDto hojaDeRutaDto = new HojaDeRutaDto();
            hojaDeRutaDto.setPk(hojaDeRuta.getId().toString());
            hojaDeRutaDto.setFecha(hojaDeRuta.getFecha().toString().replace(" 00:00:00.0", ""));
            if(hojaDeRuta.getEstado()!= null) {
                hojaDeRutaDto.setEstado(hojaDeRuta.getEstado());
            }
            
            HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();   
            List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = hojaDeRutaDetalleService.getAllByHojaDeRuta(hojaDeRuta.getId());
            
            boolean puedeDarDeBaja = false;
            
            for(HojaDeRutaDetalleModel hojaDeRutaDetalle: hojasDeRutaDetalle) {
                RemitoService remitoService = new RemitoServiceImpl(); 
                RemitoModel remito = remitoService.getByPk(hojaDeRutaDetalle.getIdRemito());
                if (remito != null && !remito.getEstado().equalsIgnoreCase("Cerrado")){
                    puedeDarDeBaja = false;
                }else{
                    puedeDarDeBaja = true;
                    break;
                }
            }
            
            hojaDeRutaDto.setPuedeDarBaja(puedeDarDeBaja);
            

            hojasDeRutaDtos.add(hojaDeRutaDto);            
        }
        
        
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);                    
        model.addAttribute("transportesList", transportes);        
        model.addAttribute("hojasDeRuta", hojasDeRutaDtos);        
                
        return "/hojaderuta/hojaderuta";
    }
    
    @RequestMapping(value = "/hojaDeRuta/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveHojaDeRuta(@ModelAttribute("hojaDeRutaForm")HojaDeRutaForm hojaDeRutaForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

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
        
        if(hojaDeRutaForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 02");
            return modelAndView;            
        }
        
        String operacion = hojaDeRutaForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta") && !operacion.equalsIgnoreCase("edit") && !operacion.equalsIgnoreCase("remove"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: operación inválida.");
            return modelAndView;                        
        }        
        if(hojaDeRutaForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 03");
            return modelAndView;                        
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {        
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: no puede realizar esta operación.");
            return modelAndView;                                    
        }
        
        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = hojaDeRutaForm.getPk();
            
        HojaDeRutaModel hojaDeRutaModel = null;
        if(id.equalsIgnoreCase("-1")) {
            hojaDeRutaModel = new HojaDeRutaModel();
            hojaDeRutaModel.setFechaAlta(new Date());
            hojaDeRutaModel.setEstado("Nuevo");
            hojaDeRutaModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));            
        } else {
            hojaDeRutaModel = hojaDeRutaService.getByPk(Integer.valueOf(id));
            if(hojaDeRutaModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de hojaDeRuta inválido.");
                return modelAndView;                
            } 
            if(!hojaDeRutaModel.getEstado().equalsIgnoreCase("Nuevo") && user.getRol() != Utils.ROL_OFICINA) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: no es posible editar una orden en estado "+hojaDeRutaModel.getEstado());
                return modelAndView;                        
            }            
        }        
        
        if(operacion.equalsIgnoreCase("alta")) {
            if(hojaDeRutaForm.getFecha() != null && !hojaDeRutaForm.getFecha().trim().equals("")) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(hojaDeRutaForm.getFecha());
                hojaDeRutaModel.setFecha(fecha);
            } else {
                hojaDeRutaModel.setFecha(new Date());
            }                           
            if(hojaDeRutaForm.getFechaCarga() != null && !hojaDeRutaForm.getFechaCarga().isEmpty()) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(hojaDeRutaForm.getFechaCarga());
                hojaDeRutaModel.setFechaCarga(fecha);                
            } else {
                hojaDeRutaModel.setFechaCarga(null);
            }            
            if(hojaDeRutaForm.getHoraCarga() != null && !hojaDeRutaForm.getHoraCarga().isEmpty()) {
                hojaDeRutaModel.setHoraCarga(hojaDeRutaForm.getHoraCarga());                                
            } else {
                hojaDeRutaModel.setHoraCarga(null);
            }            
            if(hojaDeRutaForm.getFechaSalida() != null && !hojaDeRutaForm.getFechaSalida().isEmpty()) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(hojaDeRutaForm.getFechaSalida());
                hojaDeRutaModel.setFechaSalida(fecha);                                
            } else {
                hojaDeRutaModel.setFechaSalida(null);
            }            
            if(hojaDeRutaForm.getHoraSalida() != null && !hojaDeRutaForm.getHoraSalida().isEmpty()) {
                hojaDeRutaModel.setHoraSalida(hojaDeRutaForm.getHoraSalida());                                
            } else {
                hojaDeRutaModel.setHoraSalida(null);
            }                        
            if(hojaDeRutaForm.getIdChofer() != null && !hojaDeRutaForm.getIdChofer().isEmpty() && !hojaDeRutaForm.getIdChofer().equalsIgnoreCase("-1")) {
                hojaDeRutaModel.setIdChofer(Integer.valueOf(hojaDeRutaForm.getIdChofer()));
            } else {
                hojaDeRutaModel.setIdChofer(null);
            }
            if(hojaDeRutaForm.getObservaciones() != null && !hojaDeRutaForm.getObservaciones().isEmpty()) {
                hojaDeRutaModel.setObservaciones(hojaDeRutaForm.getObservaciones());
            } else {
                hojaDeRutaModel.setObservaciones(null);
            }            
        }
        
        if(hojaDeRutaForm.getAction().equalsIgnoreCase("add") || hojaDeRutaForm.getAction().equalsIgnoreCase("edit")) {
            hojaDeRutaService.save(hojaDeRutaModel);
        } else {
            if(hojaDeRutaForm.getAction().equalsIgnoreCase("remove")) {
                if(hojaDeRutaModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de hojaDeRuta inválido.");
                    return modelAndView;                                    
                }
                
                hojaDeRutaService.delete(hojaDeRutaModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/hojaDeRuta");

        return modelAndView; 
    }
    
    @RequestMapping(value = "/hojaDeRuta/edit/{hojaDeRutapk}", method = RequestMethod.GET)
    public String editHojaDeRuta(@PathVariable String hojaDeRutapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutapk == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();   
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(Integer.valueOf(hojaDeRutapk));
        if(hojaDeRuta == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        if(!hojaDeRuta.getEstado().equalsIgnoreCase("Nuevo")) {
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
        
        HojaDeRutaForm hojaDeRutaForm = new HojaDeRutaForm();
        hojaDeRutaForm.setPk(hojaDeRuta.getId().toString());
        if(hojaDeRuta.getFechaAlta() != null) {
            hojaDeRutaForm.setFechaAlta(hojaDeRuta.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getFecha() != null) {
            hojaDeRutaForm.setFecha(hojaDeRuta.getFecha().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getFechaCarga() != null) {
            hojaDeRutaForm.setFechaCarga(hojaDeRuta.getFechaCarga().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getHoraCarga() != null) {
            hojaDeRutaForm.setHoraCarga(hojaDeRuta.getHoraCarga());
        }
        if(hojaDeRuta.getFechaSalida()!= null) {
            hojaDeRutaForm.setFechaSalida(hojaDeRuta.getFechaSalida().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getHoraSalida() != null) {
            hojaDeRutaForm.setHoraSalida(hojaDeRuta.getHoraSalida());
        }
        
        if(hojaDeRuta.getIdChofer() != null) {
            hojaDeRutaForm.setIdChofer(hojaDeRuta.getIdChofer().toString());
            hojaDeRutaForm.setIdContacto(hojaDeRuta.getIdChofer().toString());
            ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
            ProveedorContactoModel proveedorContacto = proveedorContactoService.getByPkContacto(hojaDeRuta.getIdChofer());
            if(proveedorContacto != null) {
                hojaDeRutaForm.setIdProveedor(proveedorContacto.getIdProveedor().toString());
            }
        }                
        if(hojaDeRuta.getObservaciones() != null && !hojaDeRuta.getObservaciones().isEmpty()) {
            hojaDeRutaForm.setObservaciones(hojaDeRuta.getObservaciones());
        }                
        if(hojaDeRuta.getEstado() != null && !hojaDeRuta.getEstado().isEmpty()) {
            hojaDeRutaForm.setEstado(hojaDeRuta.getEstado());
            hojaDeRutaForm.setEstadoLabel(hojaDeRuta.getEstado());
        }        
        
        hojaDeRutaForm.setOperacion(operacion);        
        
        
        hojaDeRutaForm.setAction("edit");
        model.addAttribute("hojaDeRutaForm", hojaDeRutaForm);  
        model.addAttribute("titleHojaDeRuta", "Editar Hoja De Ruta");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("hojaDeRutaName", "Hoja De Ruta: " + hojaDeRuta.getId());        
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }

        List<HojaDeRutaModel> hojasDeRuta = hojaDeRutaService.getAll();

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
        
        List<HojaDeRutaDto> hojasDeRutaDtos = new ArrayList<HojaDeRutaDto>();
        for(HojaDeRutaModel hojaDeRutaModel: hojasDeRuta) {
            
            HojaDeRutaDto hojaDeRutaDto = new HojaDeRutaDto();
            hojaDeRutaDto.setPk(hojaDeRutaModel.getId().toString());
            hojaDeRutaDto.setFecha(hojaDeRutaModel.getFecha().toString().replace(" 00:00:00.0", ""));
            if(hojaDeRutaModel.getEstado()!= null) {
                hojaDeRutaDto.setEstado(hojaDeRutaModel.getEstado());
            }

            hojasDeRutaDtos.add(hojaDeRutaDto);            
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("transportesList", transportes);        
        model.addAttribute("hojasDeRuta", hojasDeRutaDtos);   
                                                                
        return "/hojaderuta/hojaderuta";        
        
    }

    @RequestMapping(value = "/hojaDeRuta/remove/{hojaDeRutapk}", method = RequestMethod.GET)
    public String removeHojaDeRuta(@PathVariable String hojaDeRutapk, HttpServletRequest req, ModelMap model) throws Exception {
    
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutapk == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido");         
            return "/error";                
        }
        
        String operacion = "remove";        
        String displayActionButton = "block";
        
        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();   
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(Integer.valueOf(hojaDeRutapk));
        if(hojaDeRuta == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        if(!hojaDeRuta.getEstado().equalsIgnoreCase("Nuevo")) {
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
        
        HojaDeRutaForm hojaDeRutaForm = new HojaDeRutaForm();
        hojaDeRutaForm.setPk(hojaDeRuta.getId().toString());
        if(hojaDeRuta.getFechaAlta() != null) {
            hojaDeRutaForm.setFechaAlta(hojaDeRuta.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getFecha() != null) {
            hojaDeRutaForm.setFecha(hojaDeRuta.getFecha().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getFechaCarga() != null) {
            hojaDeRutaForm.setFechaCarga(hojaDeRuta.getFechaCarga().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getHoraCarga() != null) {
            hojaDeRutaForm.setHoraCarga(hojaDeRuta.getHoraCarga());
        }
        if(hojaDeRuta.getFechaSalida()!= null) {
            hojaDeRutaForm.setFechaSalida(hojaDeRuta.getFechaSalida().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getHoraSalida() != null) {
            hojaDeRutaForm.setHoraSalida(hojaDeRuta.getHoraSalida());
        }
        
        if(hojaDeRuta.getIdChofer() != null) {
            hojaDeRutaForm.setIdChofer(hojaDeRuta.getIdChofer().toString());
            hojaDeRutaForm.setIdContacto(hojaDeRuta.getIdChofer().toString());
            ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
            ProveedorContactoModel proveedorContacto = proveedorContactoService.getByPkContacto(hojaDeRuta.getIdChofer());
            if(proveedorContacto != null) {
                hojaDeRutaForm.setIdProveedor(proveedorContacto.getIdProveedor().toString());
            }            
        }                
        if(hojaDeRuta.getObservaciones() != null && !hojaDeRuta.getObservaciones().isEmpty()) {
            hojaDeRutaForm.setObservaciones(hojaDeRuta.getObservaciones());
        }                
        if(hojaDeRuta.getEstado() != null && !hojaDeRuta.getEstado().isEmpty()) {
            hojaDeRutaForm.setEstado(hojaDeRuta.getEstado());
            hojaDeRutaForm.setEstadoLabel(hojaDeRuta.getEstado());
        }        
        
        hojaDeRutaForm.setOperacion(operacion);        
        
        
        hojaDeRutaForm.setAction("remove");
        model.addAttribute("hojaDeRutaForm", hojaDeRutaForm);  
        model.addAttribute("titleHojaDeRuta", "Eliminar Hoja De Ruta");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("hojaDeRutaName", "Hoja De Ruta: " + hojaDeRuta.getId());        
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }

        List<HojaDeRutaModel> hojasDeRuta = hojaDeRutaService.getAll();

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
        List<HojaDeRutaDto> hojasDeRutaDtos = new ArrayList<HojaDeRutaDto>();
        for(HojaDeRutaModel hojaDeRutaModel: hojasDeRuta) {
            
            HojaDeRutaDto hojaDeRutaDto = new HojaDeRutaDto();
            hojaDeRutaDto.setPk(hojaDeRutaModel.getId().toString());
            hojaDeRutaDto.setFecha(hojaDeRutaModel.getFecha().toString().replace(" 00:00:00.0", ""));
            if(hojaDeRutaModel.getEstado()!= null) {
                hojaDeRutaDto.setEstado(hojaDeRutaModel.getEstado());
            }

            hojasDeRutaDtos.add(hojaDeRutaDto);            
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("transportesList", transportes);        
        model.addAttribute("hojasDeRuta", hojasDeRutaDtos);   
                                                                
        return "/hojaderuta/hojaderuta";  
                        
    }    
        
    @RequestMapping(value = "/hojaDeRuta/view/{hojaDeRutapk}", method = RequestMethod.GET)
    public String viewHojaDeRuta(@PathVariable String hojaDeRutapk, HttpServletRequest req, ModelMap model) throws Exception {
                
    
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutapk == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido");         
            return "/error";                
        }
        
        String operacion = "view";        
        String displayActionButton = "none";
        
        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();   
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(Integer.valueOf(hojaDeRutapk));
        if(hojaDeRuta == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        HojaDeRutaForm hojaDeRutaForm = new HojaDeRutaForm();
        hojaDeRutaForm.setPk(hojaDeRuta.getId().toString());
        if(hojaDeRuta.getFechaAlta() != null) {
            hojaDeRutaForm.setFechaAlta(hojaDeRuta.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getFecha() != null) {
            hojaDeRutaForm.setFecha(hojaDeRuta.getFecha().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getFechaCarga() != null) {
            hojaDeRutaForm.setFechaCarga(hojaDeRuta.getFechaCarga().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getHoraCarga() != null) {
            hojaDeRutaForm.setHoraCarga(hojaDeRuta.getHoraCarga());
        }
        if(hojaDeRuta.getFechaSalida()!= null) {
            hojaDeRutaForm.setFechaSalida(hojaDeRuta.getFechaSalida().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRuta.getHoraSalida() != null) {
            hojaDeRutaForm.setHoraSalida(hojaDeRuta.getHoraSalida());
        }
        
        if(hojaDeRuta.getIdChofer() != null) {
            hojaDeRutaForm.setIdChofer(hojaDeRuta.getIdChofer().toString());
            hojaDeRutaForm.setIdContacto(hojaDeRuta.getIdChofer().toString());
            ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
            ProveedorContactoModel proveedorContacto = proveedorContactoService.getByPkContacto(hojaDeRuta.getIdChofer());
            if(proveedorContacto != null) {
                hojaDeRutaForm.setIdProveedor(proveedorContacto.getIdProveedor().toString());
            }            
        }                
        if(hojaDeRuta.getObservaciones() != null && !hojaDeRuta.getObservaciones().isEmpty()) {
            hojaDeRutaForm.setObservaciones(hojaDeRuta.getObservaciones());
        }                
        if(hojaDeRuta.getEstado() != null && !hojaDeRuta.getEstado().isEmpty()) {
            hojaDeRutaForm.setEstado(hojaDeRuta.getEstado());
            hojaDeRutaForm.setEstadoLabel(hojaDeRuta.getEstado());
        }        
        
        hojaDeRutaForm.setOperacion(operacion);        
        
        
        hojaDeRutaForm.setAction("view");
        model.addAttribute("hojaDeRutaForm", hojaDeRutaForm);  
        model.addAttribute("titleHojaDeRuta", "Ver Hoja De Ruta");
        model.addAttribute("buttonLabel", "Ver");
        model.addAttribute("hojaDeRutaName", "Hoja De Ruta: " + hojaDeRuta.getId());        
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }

        List<HojaDeRutaModel> hojasDeRuta = hojaDeRutaService.getAll();

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
        
        List<HojaDeRutaDto> hojasDeRutaDtos = new ArrayList<HojaDeRutaDto>();
        for(HojaDeRutaModel hojaDeRutaModel: hojasDeRuta) {
            
            HojaDeRutaDto hojaDeRutaDto = new HojaDeRutaDto();
            hojaDeRutaDto.setPk(hojaDeRutaModel.getId().toString());
            hojaDeRutaDto.setFecha(hojaDeRutaModel.getFecha().toString().replace(" 00:00:00.0", ""));
            if(hojaDeRutaModel.getEstado()!= null) {
                hojaDeRutaDto.setEstado(hojaDeRutaModel.getEstado());
            }

            hojasDeRutaDtos.add(hojaDeRutaDto);            
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("transportesList", transportes);        
        model.addAttribute("hojasDeRuta", hojasDeRutaDtos);   
                                                                
        return "/hojaderuta/hojaderuta";  
                        
    }    

    @ResponseBody
    @RequestMapping(value = "/hojaDeRuta/getChofer/{idTransporte}", method = RequestMethod.GET)
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
     
    @RequestMapping(value = "/hojaDeRuta/setStatusOpenHojaDeRuta/{hojaDeRutaPk}", method = RequestMethod.GET)
    public String setStatusOpenHojaDeRuta(@PathVariable String hojaDeRutaPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaPk == null || hojaDeRutaPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();        
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(Integer.valueOf(hojaDeRutaPk));

        if(hojaDeRuta == null) {            
            model.addAttribute("errorMessage", "Error: hoja de ruta no encontrada");
            return "/error";
        }
        
        if(!hojaDeRuta.getEstado().equalsIgnoreCase("Nuevo")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+hojaDeRuta.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();        
        List<HojaDeRutaDetalleModel> items = hojaDeRutaDetalleService.getAllByHojaDeRuta(hojaDeRuta.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una hoja de ruta a abierta cuando no tiene items");
            return "/error";              
        }
        
        hojaDeRuta.setEstado("Abierto");
        hojaDeRuta.setFechaEstadoAbierto(new Date());
        hojaDeRuta.setIdUsuarioEstadoAbierto(user.getId());
        hojaDeRutaService.save(hojaDeRuta);
        
        return "redirect:/hojaDeRuta";                         
        
    }       
    
    @RequestMapping(value = "/hojaDeRuta/setStatusCloseHojaDeRuta/{hojaDeRutaPk}", method = RequestMethod.GET)
    public String setStatusCloseHojaDeRuta(@PathVariable String hojaDeRutaPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaPk == null || hojaDeRutaPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();        
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(Integer.valueOf(hojaDeRutaPk));

        if(hojaDeRuta == null) {            
            model.addAttribute("errorMessage", "Error: hoja de ruta no encontrada");
            return "/error";
        }
        
        if(!hojaDeRuta.getEstado().equalsIgnoreCase("Abierto")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+hojaDeRuta.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();        
        List<HojaDeRutaDetalleModel> items = hojaDeRutaDetalleService.getAllByHojaDeRuta(hojaDeRuta.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una hoja de ruta a abierta cuando no tiene items");
            return "/error";              
        }
        
        hojaDeRuta.setEstado("Cerrado");
        hojaDeRuta.setFechaEstadoCerrado(new Date());
        hojaDeRuta.setIdUsuarioEstadoCerrado(user.getId());
        hojaDeRutaService.save(hojaDeRuta);
        
        return "redirect:/hojaDeRuta";                         
        
    }         
    
    @RequestMapping(value = "/hojaDeRuta/print/{hojaDeRutapk}", method = RequestMethod.GET)
    public String printHojaDeRuta(@PathVariable String hojaDeRutapk, HttpServletRequest req, ModelMap model) throws Exception {
        

        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();   

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();   
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(Integer.valueOf(hojaDeRutapk));
        if(hojaDeRuta == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido. No ha sido encontrado.");         
            return "/error";    
        }

        //if(hojaDeRuta.getEstado().equalsIgnoreCase("Nuevo")) {
            //model.addAttribute("errorMessage", "Error: HojaDeRuta con estado inválido. No es posible realizar este operación.");         
            //return "/error";    
        //}
                
        ClienteService clienteService = new ClienteServiceImpl();   
        ContactoService contactoService = new ContactoServiceImpl();           
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        RemitoService remitoService = new RemitoServiceImpl();   
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        LocalidadService localidadService = new LocalidadServiceImpl();           
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
        
        HojaDeRutaDetalleForm hojaDeRutaDetalleForm = new HojaDeRutaDetalleForm();
        hojaDeRutaDetalleForm.setIdHojaDeRuta(hojaDeRuta.getId().toString());
        hojaDeRutaDetalleForm.setCodigoHojaDeRuta(hojaDeRuta.getId().toString());        
        hojaDeRutaDetalleForm.setFechaHojaDeRuta(hojaDeRuta.getFecha().toString().replace("00:00:00.0", ""));
        hojaDeRutaDetalleForm.setFechaCargaHojaDeRuta(hojaDeRuta.getFechaCarga().toString().replace("00:00:00.0", ""));
        hojaDeRutaDetalleForm.setFechaSalidaHojaDeRuta(hojaDeRuta.getFechaSalida().toString().replace("00:00:00.0", ""));
        hojaDeRutaDetalleForm.setEstadoHojaDeRuta(hojaDeRuta.getEstado());
         hojaDeRutaDetalleForm.setObservaciones(hojaDeRuta.getObservaciones());
        
        if(hojaDeRuta.getIdChofer() != null) {
           ContactoModel c = contactoService.getByPk(hojaDeRuta.getIdChofer());
           hojaDeRutaDetalleForm.setIdChofer(c.getNombre());
        }
        //hojaDeRutaDetalleForm.setIdChofer(hojaDeRuta.getIdChofer().toString());
                        
        ProveedorContactoModel proveedorContacto = proveedorContactoService.getByPkContacto(hojaDeRuta.getIdChofer());
        if(proveedorContacto != null) {            
            ProveedorModel proveedor = proveedorService.getByPk(proveedorContacto.getIdProveedor());
            if(proveedor != null) {
                hojaDeRutaDetalleForm.setTransporteHojaDeRuta(proveedor.getRazonSocial());        
            }
        }
        
        model.addAttribute("hojaDeRutaDetalleForm", hojaDeRutaDetalleForm);  
                
        Double kmAcumulado = 0.0;
        List<HojaDeRutaDetalleDto> hojasDeRutaDetalleDtos = new ArrayList<HojaDeRutaDetalleDto>();
        List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = hojaDeRutaDetalleService.getAllByHojaDeRuta(hojaDeRuta.getId());
        System.out.println("*** hojasDeRutaDetalle size:"+hojasDeRutaDetalle.size());
        for(HojaDeRutaDetalleModel hojaDeRutaDetalle: hojasDeRutaDetalle) {
            
            List<RemitoDetalleModel> remitosDetalle = remitoDetalleService.getAllByRemito(hojaDeRutaDetalle.getIdRemito());
            Integer cantidadBultos = 0;
            Integer cantidadPallets = 0;
//            for(RemitoDetalleModel remitoDetalle :remitosDetalle) {
//                if(remitoDetalle.getIdBulto() != null) {
//                    cantidadBultos += 1;
//                }
//                if(remitoDetalle.getIdPallet() != null) {
//                    cantidadPallets += 1;
//                }                
//            }
            
            String clienteStr = "No Disponible";
            String contactoStr = "No Disponible";
            String domicilioStr = "No Disponible";
            String telefono = "No Disponible";
            String horario = "No Disponible";
            String cantidad = "0";
            String obs = "";
          
            TipoModel localidaad = null;
            TipoModel provincia = null;
            
            
            RemitoModel remito = remitoService.getByPk(hojaDeRutaDetalle.getIdRemito());
            
            if(remito != null) {
                cantidad = remito.getCantidadTotal().toString();
                obs = remito.getObservaciones();
                DomicilioModel domicilio = domicilioService.getByPk(remito.getIdDomicilio());
                if(domicilio != null) {
                    TipoService tipoServicee = new TipoServiceImpl();
                    
                    if (domicilio.getIdLocalidad() != null){
                        localidaad = tipoServicee.getByPk(domicilio.getIdLocalidad());
                    }
                    
                     if (domicilio.getIdProvincia() != null){
                        provincia = tipoServicee.getByPk(domicilio.getIdProvincia());
                        
                     }
                    //LocalidadModel localidad = localidadService.getByPk(domicilio.getIdLocalidad());
                    //if(localidad != null) {
                        //domicilioStr = domicilio.getUbicacion() + " " + localidad.getNombre();
                    //}
                    domicilioStr = domicilio.getUbicacion();
                    contactoStr = domicilio.getNombreContacto();
                    horario = domicilio.getHorarioContacto();
                    telefono = domicilio.getTelefonoContacto();
                   
                   
                    
                }

                ClienteModel cliente = clienteService.getByPk(remito.getIdCliente());
                if(cliente != null) {
                    //telefono = cliente.getTelefono();
                    //horario = cliente.getHorario();                                                            
                    clienteStr = cliente.getRazonSocial();
                   
                }

                
                
            }
           
                                                        
            HojaDeRutaDetalleDto hojaDeRutaDetalleDto = new HojaDeRutaDetalleDto();
            hojaDeRutaDetalleDto.setPk(hojaDeRutaDetalle.getId().toString());
            hojaDeRutaDetalleDto.setIdRemito(hojaDeRutaDetalle.getIdRemito().toString());
            hojaDeRutaDetalleDto.setOrden(hojaDeRutaDetalle.getOrden().toString());
            hojaDeRutaDetalleDto.setCliente(clienteStr);
            hojaDeRutaDetalleDto.setObservacionesRemito(obs);
            hojaDeRutaDetalleDto.setCantidadPallets(cantidadPallets.toString());
            hojaDeRutaDetalleDto.setCantidadBultos(cantidadBultos.toString());
            hojaDeRutaDetalleDto.setDomicilio(domicilioStr);
            if (localidaad != null){
               hojaDeRutaDetalleDto.setLocalidad(localidaad.getValor());
            }
            if (provincia != null){
               hojaDeRutaDetalleDto.setProvincia(provincia.getValor());
            }
            hojaDeRutaDetalleDto.setTelefono(telefono);
            hojaDeRutaDetalleDto.setContacto(contactoStr);
            hojaDeRutaDetalleDto.setHorario(horario);
            hojaDeRutaDetalleDto.setKm(hojaDeRutaDetalle.getKm().toString());
            kmAcumulado += hojaDeRutaDetalle.getKm();
            hojaDeRutaDetalleDto.setKmAcumulado(kmAcumulado.toString());
            hojaDeRutaDetalleDto.setEstadoHojaDeRuta(hojaDeRuta.getEstado());
            hojaDeRutaDetalleDto.setCantidadTotal(cantidad);
            
            hojasDeRutaDetalleDtos.add(hojaDeRutaDetalleDto);            
        }
         
        Date fechaActual = new Date();        
        model.addAttribute("nombreHojaDeRuta", "HOJA DE RUTA NRO. "+hojaDeRuta.getId());                                   
        model.addAttribute("hojasDeRutaDetalle", hojasDeRutaDetalleDtos);                                   
        model.addAttribute("fechaActual", fechaActual);
        
        
        return "/hojaderuta/hojaderutaprint"; 
    }            
}
