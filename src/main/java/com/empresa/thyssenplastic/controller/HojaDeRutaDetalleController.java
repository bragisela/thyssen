/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.HojaDeRutaDetalleForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.HojaDeRutaDetalleDto;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.model.HojaDeRutaDetalleModel;
import com.empresa.thyssenplastic.model.HojaDeRutaModel;
import com.empresa.thyssenplastic.model.LocalidadModel;
import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ClienteContactoService;
import com.empresa.thyssenplastic.service.ClienteDomicilioService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.DomicilioService;
import com.empresa.thyssenplastic.service.HojaDeRutaDetalleService;
import com.empresa.thyssenplastic.service.HojaDeRutaService;
import com.empresa.thyssenplastic.service.LocalidadService;
import com.empresa.thyssenplastic.service.ProveedorContactoService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.RemitoDetalleService;
import com.empresa.thyssenplastic.service.RemitoService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ClienteContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteDomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.DomicilioServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.empresa.thyssenplastic.service.impl.HojaDeRutaDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.HojaDeRutaServiceImpl;
import com.empresa.thyssenplastic.service.impl.LocalidadServiceImpl;
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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class HojaDeRutaDetalleController {
    

    @RequestMapping(value = "/hojaDeRutaDetalle/{idHojaDeRuta}", method = RequestMethod.GET)
    public String getHomeHojaDeRutaDetalle(@PathVariable String idHojaDeRuta, HttpServletRequest req, ModelMap model) {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);
        

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(idHojaDeRuta == null) {
            model.addAttribute("errorMessage", "Error: id idHojaDeRuta inválido");         
            return "/error";                            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();   
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(Integer.valueOf(idHojaDeRuta));
        if(hojaDeRuta == null) {
            model.addAttribute("errorMessage", "Error: hojaDeRuta no encontrado");         
            return "/error";                
        }
        
        String rol = "";    
        String operacion = "";        
        String displayActionButton = "block";
        
        HojaDeRutaDetalleForm hojaDeRutaDetalleForm = new HojaDeRutaDetalleForm();
        hojaDeRutaDetalleForm.setPk("-1");
        hojaDeRutaDetalleForm.setAction("add");                
        hojaDeRutaDetalleForm.setIdHojaDeRuta(hojaDeRuta.getId().toString());
        hojaDeRutaDetalleForm.setIdHojaDeRutaHdn(hojaDeRuta.getId().toString());

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        hojaDeRutaDetalleForm.setFechaHojaDeRuta(hojaDeRuta.getFecha().toString().replace(" 00:00:00.0", ""));
        hojaDeRutaDetalleForm.setEstadoHojaDeRuta(hojaDeRuta.getEstado().replace(" 00:00:00.0", ""));
        hojaDeRutaDetalleForm.setFechaCargaHojaDeRuta(hojaDeRuta.getFechaCarga().toString().replace(" 00:00:00.0", ""));
        hojaDeRutaDetalleForm.setFechaSalidaHojaDeRuta(hojaDeRuta.getFechaSalida().toString().replace(" 00:00:00.0", ""));
        
        if(user.getRol() == Utils.ROL_OFICINA) {
            operacion = "alta";
            if(hojaDeRuta.getEstado().equalsIgnoreCase("Nuevo")) {
                displayActionButton = "block";
            } else {
                displayActionButton = "none";
            }
            rol = "oficina";
        }
                
        hojaDeRutaDetalleForm.setOperacion(operacion);
        
        model.addAttribute("hojaDeRutaDetalleForm", hojaDeRutaDetalleForm);  
        model.addAttribute("titleHojaDeRutaDetalle", "Nuevo Hoja De Ruta Detalle");  
        model.addAttribute("buttonLabel", "Guardar");

        ClienteService clienteService = new ClienteServiceImpl();
        RemitoService remitoService = new RemitoServiceImpl();
        Map<String,String> remitos = new LinkedHashMap<String,String>();
        List<RemitoModel> remitosModel = remitoService.getAllAvailable();     
        
        
        if(remitosModel != null && !remitosModel.isEmpty()){
            for(RemitoModel remitoModel :remitosModel) {   
                ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
                ProveedorContactoModel proveedorContacto = proveedorContactoService.getByPkContacto(hojaDeRuta.getIdChofer());
                String remitoInfo = "";
                if(proveedorContacto != null) {

                    ProveedorService proveedorService = new ProveedorServiceImpl();
                    ProveedorModel proveedor = proveedorService.getByPk(proveedorContacto.getIdProveedor());
                    String fechaRemito = formato.format(remitoModel.getFechaAlta());
                    String nombreCliente = "No Disponible";
                    
       
                    //if(proveedor != null && proveedor.getId() == remitoModel.getIdTransporte()) {
                    if(proveedor != null) {
                        ClienteModel cliente = clienteService.getByPk(remitoModel.getIdCliente());
                        if(cliente != null) {
                            nombreCliente = cliente.getRazonSocial();
                        }                
                     
                    }
                    remitoInfo = "Nro " + remitoModel.getId() + " | fecha " + fechaRemito + " | cliente " + nombreCliente + " | estado " + remitoModel.getEstado();
                        
                }
                remitos.put(remitoModel.getId().toString(), remitoInfo);
            }
        
        }
        
       
        
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();                   
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
        ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
        DomicilioService domicilioService = new DomicilioServiceImpl();
        LocalidadService localidadService = new LocalidadServiceImpl();

        Double kmAcumulado = 0.0;
        List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = hojaDeRutaDetalleService.getAllByHojaDeRuta(hojaDeRuta.getId());
        List<HojaDeRutaDetalleDto> hojasDeRutaDetalleDtos = new ArrayList<HojaDeRutaDetalleDto>();
        for(HojaDeRutaDetalleModel hojaDeRutaDetalle: hojasDeRutaDetalle) {
            
            List<RemitoDetalleModel> remitosDetalle = remitoDetalleService.getAllByRemito(hojaDeRutaDetalle.getIdRemito());
            Integer cantidadBultos = 0;
            Integer cantidadPallets = 0;
            for(RemitoDetalleModel remitoDetalle :remitosDetalle) {
//                if(remitoDetalle.getIdBulto() != null) {
//                    cantidadBultos += 1;
//                }
//                if(remitoDetalle.getIdPallet() != null) {
//                    cantidadPallets += 1;
//                }                
            }
            
            String domicilioStr = "No Disponible";
            String contactoStr = "No Disponible";
            String telefono = "No Disponible";
            String horario = "No Disponible";
            String cantidad = "0";
            String estado = "";
        
            TipoModel localidaad = null;
            TipoModel provincia = null;
            
            RemitoModel remito = remitoService.getByPk(hojaDeRutaDetalle.getIdRemito());
            if(remito != null) {
                cantidad = remito.getCantidadTotal().toString();
                estado = remito.getEstado();
                DomicilioModel domicilio = domicilioService.getByPk(remito.getIdDomicilio());
                if(domicilio != null) {
                     TipoService tipoServicee = new TipoServiceImpl();
                     if (domicilio.getIdLocalidad() != null){
                        localidaad = tipoServicee.getByPk(domicilio.getIdLocalidad());
                    }
                    
                     if (domicilio.getIdProvincia() != null){
                        provincia = tipoServicee.getByPk(domicilio.getIdProvincia());
                        
                     }

                    domicilioStr = domicilio.getUbicacion();
                    contactoStr = domicilio.getNombreContacto();
                    horario = domicilio.getHorarioContacto();
                    telefono = domicilio.getTelefonoContacto();
                  
                    //LocalidadModel localidad = localidadService.getByPk(domicilio.getIdLocalidad());
                    //if(localidad != null) {
                        
                    //}
                }

                //ClienteModel cliente = clienteService.getByPk(remito.getIdCliente());
                //if(cliente != null) {
                    //telefono = cliente.getTelefono();
                    //horario = cliente.getHorario();
                //}
                                
            }
                                                        
            HojaDeRutaDetalleDto hojaDeRutaDetalleDto = new HojaDeRutaDetalleDto();
            hojaDeRutaDetalleDto.setPk(hojaDeRutaDetalle.getId().toString());
            hojaDeRutaDetalleDto.setIdRemito(hojaDeRutaDetalle.getIdRemito().toString());
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
            hojaDeRutaDetalleDto.setOrden(hojaDeRutaDetalle.getOrden().toString());
            kmAcumulado += hojaDeRutaDetalle.getKm();
            hojaDeRutaDetalleDto.setKmAcumulado(kmAcumulado.toString());
            hojaDeRutaDetalleDto.setEstadoHojaDeRuta(hojaDeRuta.getEstado());
            hojaDeRutaDetalleDto.setCantidadTotal(cantidad);
            hojaDeRutaDetalleDto.setEstado(estado);
            
            
            hojasDeRutaDetalleDtos.add(hojaDeRutaDetalleDto);            
        }
                
        model.addAttribute("estadoHojaDeRuta", hojaDeRuta.getEstado());
        model.addAttribute("idHojaDeRuta", hojaDeRuta.getId().toString());
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("remitosList", remitos);        
        model.addAttribute("hojasDeRutaDetalle", hojasDeRutaDetalleDtos);        
                
        return "/hojaderuta/hojaderutadetalle";
    }
    

    @RequestMapping(value = "/hojaDeRutaDetalle/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveHojaDeRutaDetalle(@ModelAttribute("hojaDeRutaDetalleForm")HojaDeRutaDetalleForm hojaDeRutaDetalleForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

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
        
        if(hojaDeRutaDetalleForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 02");
            return modelAndView;            
        }
        
        String operacion = hojaDeRutaDetalleForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta") && !operacion.equalsIgnoreCase("edit") && !operacion.equalsIgnoreCase("remove"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: operación inválida.");
            return modelAndView;                        
        }        
        if(hojaDeRutaDetalleForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
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
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(Integer.valueOf(hojaDeRutaDetalleForm.getIdHojaDeRutaHdn()));
        if(hojaDeRuta == null) {        
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: no puede encontrar la hoja de ruta.");
            return modelAndView;                                    
        }
         
        RemitoService remitoService = new RemitoServiceImpl();
        RemitoModel remito = null;
        if(hojaDeRutaDetalleForm.getAction().equalsIgnoreCase("remove")) {
            remito = remitoService.getByPk(Integer.valueOf(hojaDeRutaDetalleForm.getIdRemitoHdn()));            
        } else {
            remito = remitoService.getByPk(Integer.valueOf(hojaDeRutaDetalleForm.getIdRemito()));
        }
        
        if(remito == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: no se puede encontrar el remito.");
            return modelAndView;                                                
        }
        
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = hojaDeRutaDetalleForm.getPk();
            
        HojaDeRutaDetalleModel hojaDeRutaDetalleModel = null;
        if(id.equalsIgnoreCase("-1")) {
            hojaDeRutaDetalleModel = new HojaDeRutaDetalleModel();
            hojaDeRutaDetalleModel.setFechaAlta(new Date());
            hojaDeRutaDetalleModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));            
            hojaDeRutaDetalleModel.setIdHojaDeRuta(hojaDeRuta.getId());
        } else {
            hojaDeRutaDetalleModel = hojaDeRutaDetalleService.getByPk(Integer.valueOf(id));
            if(hojaDeRutaDetalleModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de hojaDeRutaDetalle inválido.");
                return modelAndView;                
            } 
            if(!hojaDeRuta.getEstado().equalsIgnoreCase("Nuevo")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: no es posible editar una orden en estado "+hojaDeRuta.getEstado());
                return modelAndView;                        
            }            
        }        
        
        if(operacion.equalsIgnoreCase("alta")) {
            if(hojaDeRutaDetalleForm.getIdRemito() != null && !hojaDeRutaDetalleForm.getIdRemito().isEmpty() && !hojaDeRutaDetalleForm.getIdRemito().equalsIgnoreCase("-1")) {
                hojaDeRutaDetalleModel.setIdRemito(Integer.valueOf(hojaDeRutaDetalleForm.getIdRemito()));                                
            } else {
                hojaDeRutaDetalleModel.setIdRemito(null);
            }             
            if(hojaDeRutaDetalleForm.getKm() != null && !hojaDeRutaDetalleForm.getKm().isEmpty()) {
                hojaDeRutaDetalleModel.setKm(Double.valueOf(hojaDeRutaDetalleForm.getKm()));
            } else {
                hojaDeRutaDetalleModel.setKm(null);
            }       
            if(hojaDeRutaDetalleForm.getOrden() != null && !hojaDeRutaDetalleForm.getOrden().isEmpty()) {
                hojaDeRutaDetalleModel.setOrden(Integer.valueOf(hojaDeRutaDetalleForm.getOrden()));
            } else {
                hojaDeRutaDetalleModel.setOrden(1);
            }
        }
        
        if(hojaDeRutaDetalleForm.getAction().equalsIgnoreCase("add") || hojaDeRutaDetalleForm.getAction().equalsIgnoreCase("edit")) {
            hojaDeRutaDetalleService.save(hojaDeRutaDetalleModel);
            
            remito.setEstaEnHojaDeRuta(Boolean.TRUE);
            remitoService.save(remito);
        } else {
            if(hojaDeRutaDetalleForm.getAction().equalsIgnoreCase("remove")) {
                if(hojaDeRutaDetalleModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de hojaDeRutaDetalle inválido.");
                    return modelAndView;                                    
                }
                
                hojaDeRutaDetalleService.delete(hojaDeRutaDetalleModel);
                
                remito.setEstaEnHojaDeRuta(Boolean.FALSE);
                remitoService.save(remito);                
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/hojaDeRutaDetalle/"+hojaDeRuta.getId().toString());

        return modelAndView; 
    }
    
    @RequestMapping(value = "/hojaDeRutaDetalle/edit/{hojaDeRutaDetallepk}", method = RequestMethod.GET)
    public String editHojaDeRutaDetalle(@PathVariable String hojaDeRutaDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaDetallepk == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();   
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallepk));
        if(hojaDeRutaDetalle == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }

        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();   
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(hojaDeRutaDetalle.getIdHojaDeRuta());
        if(hojaDeRutaDetalle == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRuta inválido. No ha sido encontrado.");         
            return "/error";    
        }

        if(hojaDeRuta.getEstado().equalsIgnoreCase("Cerrado")) {
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
        
        HojaDeRutaDetalleForm hojaDeRutaDetalleForm = new HojaDeRutaDetalleForm();
        hojaDeRutaDetalleForm.setPk(hojaDeRutaDetalle.getId().toString());
        hojaDeRutaDetalleForm.setAction("edit");                
        hojaDeRutaDetalleForm.setIdHojaDeRuta(hojaDeRuta.getId().toString());
        hojaDeRutaDetalleForm.setIdHojaDeRutaHdn(hojaDeRuta.getId().toString());

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        hojaDeRutaDetalleForm.setFechaHojaDeRuta(hojaDeRuta.getFecha().toString().replace(" 00:00:00.0", ""));
        hojaDeRutaDetalleForm.setEstadoHojaDeRuta(hojaDeRuta.getEstado().replace(" 00:00:00.0", ""));
        hojaDeRutaDetalleForm.setFechaCargaHojaDeRuta(hojaDeRuta.getFechaCarga().toString().replace(" 00:00:00.0", ""));
        hojaDeRutaDetalleForm.setFechaSalidaHojaDeRuta(hojaDeRuta.getFechaSalida().toString().replace(" 00:00:00.0", ""));

        hojaDeRutaDetalleForm.setIdRemito(hojaDeRutaDetalle.getIdRemito().toString());
        hojaDeRutaDetalleForm.setIdRemitoHdn(hojaDeRutaDetalle.getIdRemito().toString());
        hojaDeRutaDetalleForm.setKm(hojaDeRutaDetalle.getKm().toString());
        hojaDeRutaDetalleForm.setOrden(hojaDeRutaDetalle.getOrden().toString());

        hojaDeRutaDetalleForm.setOperacion(operacion);        
        
        
        hojaDeRutaDetalleForm.setAction("edit");
        model.addAttribute("hojaDeRutaDetalleForm", hojaDeRutaDetalleForm);  
        model.addAttribute("titleHojaDeRutaDetalle", "Editar Hoja De Ruta Detalle");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("hojaDeRutaDetalleName", "Hoja De Ruta Detalle: " + hojaDeRutaDetalle.getId());        
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }

        ClienteService clienteService = new ClienteServiceImpl();
        RemitoService remitoService = new RemitoServiceImpl();
        Map<String,String> remitos = new LinkedHashMap<String,String>();
        
        RemitoModel remitoModel = remitoService.getByPk(hojaDeRutaDetalle.getIdRemito());                        
        
        if(remitoModel != null) {
            String nombreCliente = "No Disponible";
            ClienteModel cliente = clienteService.getByPk(remitoModel.getIdCliente());
            if(cliente != null) {
                nombreCliente = cliente.getRazonSocial();
            }                
            String fechaRemito = formato.format(remitoModel.getFechaAlta());


            remitos.put(remitoModel.getId().toString(), "Nro " + remitoModel.getId() + " | fecha " + fechaRemito + " | cliente " + nombreCliente);
        }        

        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
        ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
        DomicilioService domicilioService = new DomicilioServiceImpl();
        LocalidadService localidadService = new LocalidadServiceImpl();

        Double kmAcumulado = 0.0;
        List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = hojaDeRutaDetalleService.getAllByHojaDeRuta(hojaDeRuta.getId());
        List<HojaDeRutaDetalleDto> hojasDeRutaDetalleDtos = new ArrayList<HojaDeRutaDetalleDto>();
        for(HojaDeRutaDetalleModel hojaDeRutaDetalleModel: hojasDeRutaDetalle) {
            
            List<RemitoDetalleModel> remitosDetalle = remitoDetalleService.getAllByRemito(hojaDeRutaDetalleModel.getIdRemito());
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
            
            String domicilioStr = "No Disponible";
            String telefono = "No Disponible";
            String horario = "No Disponible";
            
            RemitoModel remito = remitoService.getByPk(hojaDeRutaDetalleModel.getIdRemito());
            if(remito != null) {
                DomicilioModel domicilio = domicilioService.getByPk(remito.getIdDomicilio());
                if(domicilio != null) {
                    LocalidadModel localidad = localidadService.getByPk(domicilio.getIdLocalidad());
                    if(localidad != null) {
                        domicilioStr = domicilio.getUbicacion() + " " + localidad.getNombre();
                    }
                }

                ClienteModel cliente = clienteService.getByPk(remito.getIdCliente());
                if(cliente != null) {
                    telefono = cliente.getTelefono();
                    horario = cliente.getHorario();
                }
                                
            }
                                                        
            HojaDeRutaDetalleDto hojaDeRutaDetalleDto = new HojaDeRutaDetalleDto();
            hojaDeRutaDetalleDto.setPk(hojaDeRutaDetalleModel.getId().toString());
            hojaDeRutaDetalleDto.setIdRemito(hojaDeRutaDetalleModel.getIdRemito().toString());
            hojaDeRutaDetalleDto.setCantidadPallets(cantidadPallets.toString());
            hojaDeRutaDetalleDto.setCantidadBultos(cantidadBultos.toString());
            hojaDeRutaDetalleDto.setDomicilio(domicilioStr);
            hojaDeRutaDetalleDto.setTelefono(telefono);
            //hojaDeRutaDetalleDto.setContacto(contacto);
            hojaDeRutaDetalleDto.setHorario(horario);
            hojaDeRutaDetalleDto.setKm(hojaDeRutaDetalleModel.getKm().toString());
            hojaDeRutaDetalleDto.setOrden(hojaDeRutaDetalleModel.getOrden().toString());
            kmAcumulado += hojaDeRutaDetalleModel.getKm();
            hojaDeRutaDetalleDto.setKmAcumulado(kmAcumulado.toString());
            hojaDeRutaDetalleDto.setEstadoHojaDeRuta(hojaDeRuta.getEstado());
            
            hojasDeRutaDetalleDtos.add(hojaDeRutaDetalleDto);            
        }
                
        model.addAttribute("estadoHojaDeRuta", hojaDeRuta.getEstado());
        model.addAttribute("idHojaDeRuta", hojaDeRuta.getId().toString());
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("remitosList", remitos);        
        model.addAttribute("hojasDeRutaDetalle", hojasDeRutaDetalleDtos);        
                                                                        
        return "/hojaderuta/hojaderutadetalle";
        
    }

    /*
    @RequestMapping(value = "/hojaDeRutaDetalle/remove/{hojaDeRutaDetallepk}", method = RequestMethod.GET)
    public String removeHojaDeRutaDetalle(@PathVariable String hojaDeRutaDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
    
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaDetallepk == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido");         
            return "/error";                
        }
        
        String operacion = "remove";        
        String displayActionButton = "block";
        
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();   
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallepk));
        if(hojaDeRutaDetalle == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }

        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();   
        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(hojaDeRutaDetalle.getIdHojaDeRuta());
        if(hojaDeRuta == null) {
            model.addAttribute("errorMessage", "Error: hojaDeRuta no encontrado");         
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
        
        HojaDeRutaDetalleForm hojaDeRutaDetalleForm = new HojaDeRutaDetalleForm();
        hojaDeRutaDetalleForm.setPk(hojaDeRutaDetalle.getId().toString());
        hojaDeRutaDetalleForm.setIdRemito(hojaDeRutaDetalle.getIdRemito().toString());
        hojaDeRutaDetalleForm.setIdRemitoHdn(hojaDeRutaDetalle.getIdRemito().toString());
        hojaDeRutaDetalleForm.setKm(hojaDeRutaDetalle.getKm().toString());
        
        hojaDeRutaDetalleForm.setIdHojaDeRuta(hojaDeRuta.getId().toString());
        hojaDeRutaDetalleForm.setIdHojaDeRutaHdn(hojaDeRuta.getId().toString());

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        hojaDeRutaDetalleForm.setFechaHojaDeRuta(hojaDeRuta.getFecha().toString().replace(" 00:00:00.0", ""));
        hojaDeRutaDetalleForm.setEstadoHojaDeRuta(hojaDeRuta.getEstado().replace(" 00:00:00.0", ""));
        hojaDeRutaDetalleForm.setFechaCargaHojaDeRuta(hojaDeRuta.getFechaCarga().toString().replace(" 00:00:00.0", ""));
        hojaDeRutaDetalleForm.setFechaSalidaHojaDeRuta(hojaDeRuta.getFechaSalida().toString().replace(" 00:00:00.0", ""));

        hojaDeRutaDetalleForm.setOperacion(operacion);        
        
        
        hojaDeRutaDetalleForm.setAction("remove");
        model.addAttribute("hojaDeRutaDetalleForm", hojaDeRutaDetalleForm);  
        model.addAttribute("titleHojaDeRutaDetalle", "Eliminar Hoja De Ruta");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("hojaDeRutaDetalleName", "Hoja De Ruta Detalle: " + hojaDeRutaDetalle.getId());        
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }

        ClienteService clienteService = new ClienteServiceImpl();
        RemitoService remitoService = new RemitoServiceImpl();
        Map<String,String> remitos = new LinkedHashMap<String,String>();
        List<RemitoModel> remitosModel = remitoService.getAllCompletadoAvailable();                
        if(remitosModel != null && !remitosModel.isEmpty()){
            for(RemitoModel remitoModel :remitosModel) {                
                String nombreCliente = "No Disponible";
                ClienteModel cliente = clienteService.getByPk(remitoModel.getIdCliente());
                if(cliente != null) {
                    nombreCliente = cliente.getRazonSocial();
                }                
                String fechaRemito = formato.format(remitoModel.getFechaAlta());
                
                remitos.put(remitoModel.getId().toString(), "Nro " + remitoModel.getId() + " | fecha " + fechaRemito + " | cliente " + nombreCliente);
            }
        }
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
        ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
        DomicilioService domicilioService = new DomicilioServiceImpl();
        LocalidadService localidadService = new LocalidadServiceImpl();

        Double kmAcumulado = 0.0;
        List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = hojaDeRutaDetalleService.getAll();
        List<HojaDeRutaDetalleDto> hojasDeRutaDetalleDtos = new ArrayList<HojaDeRutaDetalleDto>();
        for(HojaDeRutaDetalleModel hojaDeRutaDetalleModel: hojasDeRutaDetalle) {
            
            List<RemitoDetalleModel> remitosDetalle = remitoDetalleService.getAllByRemito(hojaDeRutaDetalleModel.getIdRemito());
            Integer cantidadBultos = 0;
            Integer cantidadPallets = 0;
            for(RemitoDetalleModel remitoDetalle :remitosDetalle) {
                if(remitoDetalle.getIdBulto() != null) {
                    cantidadBultos += 1;
                }
                if(remitoDetalle.getIdPallet() != null) {
                    cantidadPallets += 1;
                }                
            }
            
            String domicilioStr = "No Disponible";
            String telefono = "No Disponible";
            String horario = "No Disponible";
            
            RemitoModel remito = remitoService.getByPk(hojaDeRutaDetalleModel.getIdRemito());
            if(remito != null) {
                DomicilioModel domicilio = domicilioService.getByPk(remito.getIdDomicilio());
                if(domicilio != null) {
                    LocalidadModel localidad = localidadService.getByPk(domicilio.getIdLocalidad());
                    if(localidad != null) {
                        domicilioStr = domicilio.getUbicacion() + " " + localidad.getNombre();
                    }
                }

                ClienteModel cliente = clienteService.getByPk(remito.getIdCliente());
                if(cliente != null) {
                    telefono = cliente.getTelefono();
                    horario = cliente.getHorario();
                }
                                
            }
                                                        
            HojaDeRutaDetalleDto hojaDeRutaDetalleDto = new HojaDeRutaDetalleDto();
            hojaDeRutaDetalleDto.setPk(hojaDeRutaDetalleModel.getId().toString());
            hojaDeRutaDetalleDto.setIdRemito(hojaDeRutaDetalleModel.getIdRemito().toString());
            hojaDeRutaDetalleDto.setCantidadPallets(cantidadPallets.toString());
            hojaDeRutaDetalleDto.setCantidadBultos(cantidadBultos.toString());
            hojaDeRutaDetalleDto.setDomicilio(domicilioStr);
            hojaDeRutaDetalleDto.setTelefono(telefono);
            //hojaDeRutaDetalleDto.setContacto(contacto);
            hojaDeRutaDetalleDto.setHorario(horario);
            hojaDeRutaDetalleDto.setKm(hojaDeRutaDetalleModel.getKm().toString());
            hojaDeRutaDetalleDto.setOrden(hojaDeRutaDetalle.getOrden().toString());
            kmAcumulado += hojaDeRutaDetalleModel.getKm();
            hojaDeRutaDetalleDto.setKmAcumulado(kmAcumulado.toString());
            hojaDeRutaDetalleDto.setEstadoHojaDeRuta(hojaDeRuta.getEstado());
            
            hojasDeRutaDetalleDtos.add(hojaDeRutaDetalleDto);            
        }
        
        model.addAttribute("estadoHojaDeRuta", hojaDeRuta.getEstado());
        model.addAttribute("idHojaDeRuta", hojaDeRuta.getId().toString());
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("remitosList", remitos);        
        model.addAttribute("hojasDeRutaDetalle", hojasDeRutaDetalleDtos);    
                                                                
        return "/hojaderuta/hojaderutadetalle";  
                        
    }    
    
/*    
    @RequestMapping(value = "/hojaDeRutaDetalle/view/{hojaDeRutaDetallepk}", method = RequestMethod.GET)
    public String viewHojaDeRutaDetalle(@PathVariable String hojaDeRutaDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
                
    
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaDetallepk == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido");         
            return "/error";                
        }
        
        String operacion = "view";        
        String displayActionButton = "none";
        
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();   
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallepk));
        if(hojaDeRutaDetalle == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        if(!hojaDeRutaDetalle.getEstado().equalsIgnoreCase("Nuevo")) {
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
        
        HojaDeRutaDetalleForm hojaDeRutaDetalleForm = new HojaDeRutaDetalleForm();
        hojaDeRutaDetalleForm.setPk(hojaDeRutaDetalle.getId().toString());
        if(hojaDeRutaDetalle.getFechaAlta() != null) {
            hojaDeRutaDetalleForm.setFechaAlta(hojaDeRutaDetalle.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRutaDetalle.getFecha() != null) {
            hojaDeRutaDetalleForm.setFecha(hojaDeRutaDetalle.getFecha().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRutaDetalle.getFechaCarga() != null) {
            hojaDeRutaDetalleForm.setFechaCarga(hojaDeRutaDetalle.getFechaCarga().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRutaDetalle.getHoraCarga() != null) {
            hojaDeRutaDetalleForm.setHoraCarga(hojaDeRutaDetalle.getHoraCarga());
        }
        if(hojaDeRutaDetalle.getFechaSalida()!= null) {
            hojaDeRutaDetalleForm.setFechaSalida(hojaDeRutaDetalle.getFechaSalida().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRutaDetalle.getHoraSalida() != null) {
            hojaDeRutaDetalleForm.setHoraSalida(hojaDeRutaDetalle.getHoraSalida());
        }
        
        if(hojaDeRutaDetalle.getIdChofer() != null) {
            hojaDeRutaDetalleForm.setIdChofer(hojaDeRutaDetalle.getIdChofer().toString());
        }                
        if(hojaDeRutaDetalle.getObservaciones() != null && !hojaDeRutaDetalle.getObservaciones().isEmpty()) {
            hojaDeRutaDetalleForm.setObservaciones(hojaDeRutaDetalle.getObservaciones());
        }                
        if(hojaDeRutaDetalle.getEstado() != null && !hojaDeRutaDetalle.getEstado().isEmpty()) {
            hojaDeRutaDetalleForm.setEstado(hojaDeRutaDetalle.getEstado());
            hojaDeRutaDetalleForm.setEstadoLabel(hojaDeRutaDetalle.getEstado());
        }        
        
        hojaDeRutaDetalleForm.setOperacion(operacion);        
        
        
        hojaDeRutaDetalleForm.setAction("view");
        model.addAttribute("hojaDeRutaDetalleForm", hojaDeRutaDetalleForm);  
        model.addAttribute("titleHojaDeRutaDetalle", "Ver Hoja De Ruta");
        model.addAttribute("buttonLabel", "Ver");
        model.addAttribute("hojaDeRutaDetalleName", "Hoja De Ruta: " + hojaDeRutaDetalle.getId());        
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }

        List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = hojaDeRutaDetalleService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> choferes = new LinkedHashMap<String,String>();
        List<TipoModel> choferesModel = tipoService.getByType("chofer");

        if(choferesModel != null && !choferesModel.isEmpty()){
            for(TipoModel tipoModel :choferesModel) {
                choferes.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        List<HojaDeRutaDetalleDto> hojasDeRutaDetalleDtos = new ArrayList<HojaDeRutaDetalleDto>();
        for(HojaDeRutaDetalleModel hojaDeRutaDetalleModel: hojasDeRutaDetalle) {
            
            HojaDeRutaDetalleDto hojaDeRutaDetalleDto = new HojaDeRutaDetalleDto();
            hojaDeRutaDetalleDto.setPk(hojaDeRutaDetalleModel.getId().toString());
            hojaDeRutaDetalleDto.setFecha(hojaDeRutaDetalleModel.getFecha().toString().replace(" 00:00:00.0", ""));
            if(hojaDeRutaDetalleModel.getEstado()!= null) {
                hojaDeRutaDetalleDto.setEstado(hojaDeRutaDetalleModel.getEstado());
            }

            hojasDeRutaDetalleDtos.add(hojaDeRutaDetalleDto);            
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("choferesList", choferes);        
        model.addAttribute("hojasDeRutaDetalle", hojasDeRutaDetalleDtos);   
                                                                
        return "/hojaderuta/hojaderuta";  
                        
    }    

    /*
    @ResponseBody
    @RequestMapping(value = "/hojaDeRutaDetalle/getDomcilio/{idCliente}", method = RequestMethod.GET)
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
                       
                        ClienteDomicilioBean bean = new ClienteDomicilioBean();
                        bean.setId(domicilio.getId().toString());
                        if(localidad != null) {
                            bean.setValor(domicilio.getUbicacion() + " (" + localidad.getNombre() + ")");
                        } else {
                            bean.setValor(domicilio.getUbicacion());
                        }

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
    @RequestMapping(value = "/hojaDeRutaDetalle/getContacto/{idCliente}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/hojaDeRutaDetalle/setStatusOpenOrdenCompra/{hojaDeRutaDetallePk}", method = RequestMethod.GET)
    public String setStatusOpenOrdenCompra(@PathVariable String hojaDeRutaDetallePk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaDetallePk == null || hojaDeRutaDetallePk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();        
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallePk));

        if(hojaDeRutaDetalle == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!hojaDeRutaDetalle.getEstado().equalsIgnoreCase("Nuevo")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+hojaDeRutaDetalle.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        HojaDeRutaDetalleItemService hojaDeRutaDetalleItemService = new HojaDeRutaDetalleItemServiceImpl();        
        List<HojaDeRutaDetalleItemModel> items = hojaDeRutaDetalleItemService.getAllByHojaDeRutaDetalle(hojaDeRutaDetalle.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        hojaDeRutaDetalle.setEstado("Abierto");
        hojaDeRutaDetalleService.save(hojaDeRutaDetalle);
        
        return "redirect:/hojaDeRutaDetalle";                         
        
    }       
    
    @RequestMapping(value = "/hojaDeRutaDetalle/setStatusCloseOrdenCompra/{hojaDeRutaDetallePk}", method = RequestMethod.GET)
    public String setStatusCoseOrdenCompra(@PathVariable String hojaDeRutaDetallePk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaDetallePk == null || hojaDeRutaDetallePk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();        
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallePk));

        if(hojaDeRutaDetalle == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!hojaDeRutaDetalle.getEstado().equalsIgnoreCase("Completado")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+hojaDeRutaDetalle.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        HojaDeRutaDetalleItemService hojaDeRutaDetalleItemService = new HojaDeRutaDetalleItemServiceImpl();        
        List<HojaDeRutaDetalleItemModel> items = hojaDeRutaDetalleItemService.getAllByHojaDeRutaDetalle(hojaDeRutaDetalle.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        Date today = new Date();
        
        hojaDeRutaDetalle.setFechaCierreOrden(today);
        hojaDeRutaDetalle.setIdUsuarioCierre(user.getId());
        hojaDeRutaDetalle.setEstado("Cerrado");
        hojaDeRutaDetalleService.save(hojaDeRutaDetalle);
        
        return "redirect:/hojaDeRutaDetalle";                         
        
    }    
    
    @RequestMapping(value = "/hojaDeRutaDetalle/completarOrden/{hojaDeRutaDetallepk}", method = RequestMethod.GET)
    public String completarOrdenObservacionesHojaDeRutaDetalle(@PathVariable String hojaDeRutaDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaDetallepk == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido");         
            return "/error";                
        }
        
        String operacion = "completar";        
        String displayActionButton = "block";
        
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();   
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallepk));
        if(hojaDeRutaDetalle == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!hojaDeRutaDetalle.getEstado().equalsIgnoreCase("Abierto")) {
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
        
        HojaDeRutaDetalleForm hojaDeRutaDetalleForm = new HojaDeRutaDetalleForm();
        hojaDeRutaDetalleForm.setPk(hojaDeRutaDetalle.getId().toString());
        if(hojaDeRutaDetalle.getFechaAlta() != null) {
            hojaDeRutaDetalleForm.setFechaAlta(hojaDeRutaDetalle.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(hojaDeRutaDetalle.getIdProveedor() != null) {
            hojaDeRutaDetalleForm.setIdProveedor(hojaDeRutaDetalle.getIdProveedor().toString());
        }        
        if(hojaDeRutaDetalle.getObservaciones() != null && !hojaDeRutaDetalle.getObservaciones().isEmpty()) {
            hojaDeRutaDetalleForm.setObservaciones(hojaDeRutaDetalle.getObservaciones());
        }        
        if(hojaDeRutaDetalle.getReferenciaAdministrativa() != null && !hojaDeRutaDetalle.getReferenciaAdministrativa().isEmpty()) {
            hojaDeRutaDetalleForm.setReferenciaAdministrativa(hojaDeRutaDetalle.getReferenciaAdministrativa());
        }                        
        if(hojaDeRutaDetalle.getEstado() != null && !hojaDeRutaDetalle.getEstado().isEmpty()) {
            hojaDeRutaDetalleForm.setEstado(hojaDeRutaDetalle.getEstado());
        }        
        
        hojaDeRutaDetalleForm.setOperacion(operacion);
        hojaDeRutaDetalleForm.setEditObservaciones("true");
                
        hojaDeRutaDetalleForm.setAction("edit");
        model.addAttribute("hojaDeRutaDetalleForm", hojaDeRutaDetalleForm);  
        model.addAttribute("titleHojaDeRutaDetalle", "Completar Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("hojaDeRutaDetalleName", hojaDeRutaDetalle.getId() + " - " + hojaDeRutaDetalle.getIdProveedor());        
        
        List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = hojaDeRutaDetalleService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<HojaDeRutaDetalleDto> hojasDeRutaDetalleDtos = new ArrayList<HojaDeRutaDetalleDto>();
        for(HojaDeRutaDetalleModel hojaDeRutaDetalleModel: hojasDeRutaDetalle) {
            HojaDeRutaDetalleDto hojaDeRutaDetalleDto = new HojaDeRutaDetalleDto();
            hojaDeRutaDetalleDto.setPk(hojaDeRutaDetalleModel.getId().toString());
            hojaDeRutaDetalleDto.setFechaAlta(hojaDeRutaDetalleModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(hojaDeRutaDetalle.getFechaEntrega() != null) {
                hojaDeRutaDetalleDto.setFechaEntrega(hojaDeRutaDetalle.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
            }
            if(hojaDeRutaDetalle.getReferenciaAdministrativa() != null) {
                hojaDeRutaDetalleDto.setReferenciaAdministrativa(hojaDeRutaDetalle.getReferenciaAdministrativa());
            }                        
            hojaDeRutaDetalleDto.setEstado(hojaDeRutaDetalleModel.getEstado());
            hojaDeRutaDetalleDto.setProveedor(proveedores.get(hojaDeRutaDetalleModel.getIdProveedor().toString()));
            
            hojasDeRutaDetalleDtos.add(hojaDeRutaDetalleDto);
        }
                               
        model.addAttribute("displayUser", "none");
                
        model.addAttribute("action", "editObservaciones");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("hojasDeRutaDetalle", hojasDeRutaDetalleDtos);        
                                                        
        return "/ordendecompra/ordendecompra";        
    }    
    */
    
    /*
    @RequestMapping(value = "/hojaDeRutaDetalle/setStatusOpenHojaDeRutaDetalle/{hojaDeRutaDetallePk}", method = RequestMethod.GET)
    public String setStatusOpenHojaDeRutaDetalle(@PathVariable String hojaDeRutaDetallePk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaDetallePk == null || hojaDeRutaDetallePk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();        
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallePk));

        if(hojaDeRutaDetalle == null) {            
            model.addAttribute("errorMessage", "Error:hojaDeRutaDetalle no encontrada");
            return "/error";
        }
        
        if(!hojaDeRutaDetalle.getEstado().equalsIgnoreCase("Nuevo")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto con estado "+hojaDeRutaDetalle.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        HojaDeRutaDetalleDetalleService hojaDeRutaDetalleDetalleService = new HojaDeRutaDetalleDetalleServiceImpl();        
        List<HojaDeRutaDetalleDetalleModel> items = hojaDeRutaDetalleDetalleService.getAllByHojaDeRutaDetalle(hojaDeRutaDetalle.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar un hojaDeRutaDetalle a abierta cuando no tiene items");
            return "/error";              
        }
        
        hojaDeRutaDetalle.setEstado("Abierto");
        hojaDeRutaDetalle.setFechaAbierto(new Date());
        hojaDeRutaDetalle.setIdUsuarioAbierto(user.getId());        
        
        hojaDeRutaDetalleService.save(hojaDeRutaDetalle);
        
        return "redirect:/hojaDeRutaDetalle";                         
        
    }        
    
    @RequestMapping(value = "/hojaDeRutaDetalle/setStatusCompletedHojaDeRutaDetalle/{hojaDeRutaDetallePk}", method = RequestMethod.GET)
    public String setStatusCompletedHojaDeRutaDetalle(@PathVariable String hojaDeRutaDetallePk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaDetallePk == null || hojaDeRutaDetallePk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();        
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallePk));

        if(hojaDeRutaDetalle == null) {            
            model.addAttribute("errorMessage", "Error:hojaDeRutaDetalle no encontrada");
            return "/error";
        }
        
        if(!hojaDeRutaDetalle.getEstado().equalsIgnoreCase("Abierto")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado completada con estado "+hojaDeRutaDetalle.getEstado());
            return "/error";
        }            
                
        HojaDeRutaDetalleDetalleService hojaDeRutaDetalleDetalleService = new HojaDeRutaDetalleDetalleServiceImpl();        
        List<HojaDeRutaDetalleDetalleModel> items = hojaDeRutaDetalleDetalleService.getAllByHojaDeRutaDetalle(hojaDeRutaDetalle.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar un hojaDeRutaDetalle a completada cuando no tiene items");
            return "/error";              
        }
        
        Boolean allItemsRecebidos = true;
        if(items != null && !items.isEmpty()) {            
            for(HojaDeRutaDetalleDetalleModel item :items) {
                if(!item.getIngresoDeposito()) {
                    allItemsRecebidos = false;
                }
            }
        }
        if(!allItemsRecebidos) {
            model.addAttribute("errorMessage", "Error: no es posible pasar un hojaDeRutaDetalle a completada cuando tiene items sin recibir en el depósito");
            return "/error";                          
        }
        
        hojaDeRutaDetalle.setEstado("Completado");
        hojaDeRutaDetalle.setFechaCompletado(new Date());
        hojaDeRutaDetalle.setIdUsuarioCompletado(user.getId());        
        hojaDeRutaDetalleService.save(hojaDeRutaDetalle);
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        ArticuloService articuloService = new ArticuloServiceImpl();
        
        for(HojaDeRutaDetalleDetalleModel item :items) {
            if(item.getIdBulto() != null) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdBulto());
                if(bulto != null) {
                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
                    if(ordenDeProduccion != null) {
                        ArticuloModel articulo =  articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                        if(articulo != null && ordenDeProduccion.getCantidadAProducir() != null) {
                            Double actualStock = articulo.getStock();
                            Double actualStockPeso = articulo.getStockPeso();
                            articulo.setStock(actualStock - item.getCantidad() );
                            articulo.setStockPeso(actualStockPeso - item.getCantidad());
                            
                            articuloService.save(articulo);
                        }                        
                    }
                }
            }
            if(item.getIdPallet() != null) {
                OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(item.getIdPallet());
                if(pallet != null) {
                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
                    if(ordenDeProduccion != null) {
                        ArticuloModel articulo =  articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                        if(articulo != null && ordenDeProduccion.getCantidadAProducir() != null) {
                            Double actualStock = articulo.getStock();
                            Double actualStockPeso = articulo.getStockPeso();
                            articulo.setStock(actualStock - item.getCantidad() );
                            articulo.setStockPeso(actualStockPeso - item.getCantidad());
                            
                            articuloService.save(articulo);
                        }                        
                    }
                }
            }            
        }
                
        return "redirect:/hojaDeRutaDetalle";                         
        
    }            
    
    @RequestMapping(value = "/hojaDeRutaDetalle/setStatusCloseHojaDeRutaDetalle/{hojaDeRutaDetallePk}", method = RequestMethod.GET)
    public String setStatusCloseHojaDeRutaDetalle(@PathVariable String hojaDeRutaDetallePk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(hojaDeRutaDetallePk == null || hojaDeRutaDetallePk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();        
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallePk));

        if(hojaDeRutaDetalle == null) {            
            model.addAttribute("errorMessage", "Error:hojaDeRutaDetalle no encontrada");
            return "/error";
        }
        
        if(!hojaDeRutaDetalle.getEstado().equalsIgnoreCase("Completado")) {            
            model.addAttribute("errorMessage", "Error: no es posible cerrar el hojaDeRutaDetalle con estado "+hojaDeRutaDetalle.getEstado());
            return "/error";
        }            
                
        HojaDeRutaDetalleDetalleService hojaDeRutaDetalleDetalleService = new HojaDeRutaDetalleDetalleServiceImpl();        
        List<HojaDeRutaDetalleDetalleModel> items = hojaDeRutaDetalleDetalleService.getAllByHojaDeRutaDetalle(hojaDeRutaDetalle.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible cerrar un hojaDeRutaDetalle cuando no tiene items");
            return "/error";              
        }
        
        Boolean allItemsRecebidos = true;
        if(items != null && !items.isEmpty()) {            
            for(HojaDeRutaDetalleDetalleModel item :items) {
                if(!item.getIngresoDeposito()) {
                    allItemsRecebidos = false;
                }
            }
        }
        if(!allItemsRecebidos) {
            model.addAttribute("errorMessage", "Error: no es posible cerrar un hojaDeRutaDetalle cuando tiene items sin recibir en el depósito");
            return "/error";                          
        }
        
        hojaDeRutaDetalle.setEstado("Cerrado");
        hojaDeRutaDetalle.setFechaCierre(new Date());
        hojaDeRutaDetalle.setIdUsuarioCierre(user.getId());
        hojaDeRutaDetalleService.save(hojaDeRutaDetalle);
        
        return "redirect:/hojaDeRutaDetalle";                         
        
    }            
    
    @RequestMapping(value = "/hojaDeRutaDetalle/print/{hojaDeRutaDetallepk}", method = RequestMethod.GET)
    public String printHojaDeRutaDetalle(@PathVariable String hojaDeRutaDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
        

        HojaDeRutaDetalleDetalleService hojaDeRutaDetalleDetalleService = new HojaDeRutaDetalleDetalleServiceImpl();   

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();   
        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByPk(Integer.valueOf(hojaDeRutaDetallepk));
        if(hojaDeRutaDetalle == null) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }

        if(hojaDeRutaDetalle.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: HojaDeRutaDetalle con estado inválido. No es posible realizar este operación.");         
            return "/error";    
        }
                
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();   
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        LocalidadService localidadService = new LocalidadServiceImpl();   
        TipoService tipoService = new TipoServiceImpl();   
        
                
        HojaDeRutaDetalleDetalleForm hojaDeRutaDetalleDetalleForm = new HojaDeRutaDetalleDetalleForm();
        hojaDeRutaDetalleDetalleForm.setIdHojaDeRutaDetalle(hojaDeRutaDetalle.getId().toString());
        hojaDeRutaDetalleDetalleForm.setCodigoHojaDeRutaDetalle(hojaDeRutaDetalle.getId().toString());
        hojaDeRutaDetalleDetalleForm.setFechaHojaDeRutaDetalle(hojaDeRutaDetalle.getFechaHojaDeRutaDetalle().toString().replace("00:00:00.0", ""));
        hojaDeRutaDetalleDetalleForm.setTipoHojaDeRutaDetalle(hojaDeRutaDetalle.getTipoHojaDeRutaDetalle());
        hojaDeRutaDetalleDetalleForm.setEstadoHojaDeRutaDetalle(hojaDeRutaDetalle.getEstado());        
        if(hojaDeRutaDetalle.getReferenciaAdministrativa() != null) {
            hojaDeRutaDetalleDetalleForm.setReferenciaAdministrativa(hojaDeRutaDetalle.getReferenciaAdministrativa());
        }
        ClienteModel cliente = null;
        if(hojaDeRutaDetalle.getIdCliente() != null) {
            cliente = clienteService.getByPk(hojaDeRutaDetalle.getIdCliente());
            if(cliente != null) {
                hojaDeRutaDetalleDetalleForm.setCliente("(" + cliente.getId() + ") " + cliente.getRazonSocial());
            }
        }
        if(hojaDeRutaDetalle.getIdClienteDomicilio() != null) {
            ClienteDomicilioModel clienteDomicilio = clienteDomicilioService.getByPk(hojaDeRutaDetalle.getIdClienteDomicilio());
            if(clienteDomicilio != null) {
                DomicilioModel domicilioModel = domicilioService.getByPk(clienteDomicilio.getIdDomicilio());
                if(domicilioModel != null) {
                    String localidad = "";
                    String provincia = "";
                    LocalidadModel localidadModel = localidadService.getByPk(domicilioModel.getIdLocalidad());
                    if(localidadModel != null) {
                        localidad = localidadModel.getNombre();
                        
                        TipoModel tipo = tipoService.getByPk(localidadModel.getIdProvincia());
                        if(tipo != null) {
                            provincia = tipo.getValor();
                        }
                    }
                    hojaDeRutaDetalleDetalleForm.setDomicilio(domicilioModel.getUbicacion() + ", " + localidad + ", " + provincia);
                }
            }
        }        
        if(hojaDeRutaDetalle.getIdTransporte() != null) {
            TipoModel tipo = tipoService.getByPk(hojaDeRutaDetalle.getIdTransporte());
            if(tipo != null) {
                hojaDeRutaDetalleDetalleForm.setTransporte(tipo.getValor());
            }
        }
        model.addAttribute("hojaDeRutaDetalleDetalleForm", hojaDeRutaDetalleDetalleForm);  
                
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        ArticuloService articuloService = new ArticuloServiceImpl();
        
        
        OrdenDeProduccionBobinaService bobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBultoService bultoService = new OrdenDeProduccionBultoServiceImpl();   
        OrdenDeProduccionPalletService palletService = new OrdenDeProduccionPalletServiceImpl();                   
        List<HojaDeRutaDetalleDetalleModel> hojaDeRutaDetalleDetalles = hojaDeRutaDetalleDetalleService.getAllByHojaDeRutaDetalle(hojaDeRutaDetalle.getId());
        
        List<HojaDeRutaDetalleDetalleDto> hojaDeRutaDetalleDetallesDtos = new ArrayList<HojaDeRutaDetalleDetalleDto>();
        for(HojaDeRutaDetalleDetalleModel hojaDeRutaDetalleDetalleModel: hojaDeRutaDetalleDetalles) {
            HojaDeRutaDetalleDetalleDto hojaDeRutaDetalleDetalleDto = new HojaDeRutaDetalleDetalleDto();
            hojaDeRutaDetalleDetalleDto.setPk(hojaDeRutaDetalleDetalleModel.getId().toString());
            hojaDeRutaDetalleDetalleDto.setFechaAlta(hojaDeRutaDetalleDetalleModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            hojaDeRutaDetalleDetalleDto.setCantidad(hojaDeRutaDetalleDetalleModel.getCantidad().toString());
            
            if(hojaDeRutaDetalleDetalleModel.getIngresoDeposito()) {
                hojaDeRutaDetalleDetalleDto.setIngresoDeposito("Si");
            } else {
                hojaDeRutaDetalleDetalleDto.setIngresoDeposito("No");
            }
            
            if(hojaDeRutaDetalleDetalleModel.getIdBobina() != null) {
                
                OrdenDeProduccionBobinaModel bobina = bobinaService.getByPk(hojaDeRutaDetalleDetalleModel.getIdBobina());
                if(bobina != null) {
                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
                    if(ordenDeProduccion != null) {
                        hojaDeRutaDetalleDetalleDto.setLote("L"+ordenDeProduccion.getId());                        
                        
                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                        if(articulo != null) {
                            hojaDeRutaDetalleDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
                        }
                    }
                    hojaDeRutaDetalleDetalleDto.setCodigo(bobina.getCodigo());
                }
            }
            if(hojaDeRutaDetalleDetalleModel.getIdBulto() != null) {
                
                OrdenDeProduccionBultoModel bulto = bultoService.getByPk(hojaDeRutaDetalleDetalleModel.getIdBulto());
                if(bulto != null) {
                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
                    if(ordenDeProduccion != null) {
                        hojaDeRutaDetalleDetalleDto.setLote("L"+ordenDeProduccion.getId());
                        
                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                        if(articulo != null) {
                            hojaDeRutaDetalleDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
                        }                        
                    }
                    hojaDeRutaDetalleDetalleDto.setCodigo(bulto.getCodigo());
                }
            }
            if(hojaDeRutaDetalleDetalleModel.getIdPallet() != null) {
                
                OrdenDeProduccionPalletModel pallet = palletService.getByPk(hojaDeRutaDetalleDetalleModel.getIdPallet());
                if(pallet != null) {
                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
                    if(ordenDeProduccion != null) {
                        hojaDeRutaDetalleDetalleDto.setLote("L"+ordenDeProduccion.getId());
                        
                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                        if(articulo != null) {
                            hojaDeRutaDetalleDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
                        }                        
                    }
                    hojaDeRutaDetalleDetalleDto.setCodigo(pallet.getCodigo());
                }
            }

            
            hojaDeRutaDetalleDetallesDtos.add(hojaDeRutaDetalleDetalleDto);
            
            
        }
        
        Date fechaActual = new Date();
        String nombreHojaDeRutaDetalle = "REMITO DE ";
        if(hojaDeRutaDetalle.getTipoHojaDeRutaDetalle().equalsIgnoreCase("Salida")) {
            nombreHojaDeRutaDetalle = nombreHojaDeRutaDetalle + "SALIDA Nro. ";
        }
        if(hojaDeRutaDetalle.getTipoHojaDeRutaDetalle().equalsIgnoreCase("Entrada")) {
            nombreHojaDeRutaDetalle = nombreHojaDeRutaDetalle + "ENTRADA Nro. ";
        }
        nombreHojaDeRutaDetalle = nombreHojaDeRutaDetalle + hojaDeRutaDetalle.getId();
                
        model.addAttribute("nombreHojaDeRutaDetalle", nombreHojaDeRutaDetalle);        
        model.addAttribute("hojaDeRutaDetalleDetalles", hojaDeRutaDetalleDetallesDtos);                   
        model.addAttribute("fechaActual", fechaActual);
        
        
        return "/hojaDeRutaDetalle/hojaDeRutaDetalleprint"; 
    }        
*/
}
