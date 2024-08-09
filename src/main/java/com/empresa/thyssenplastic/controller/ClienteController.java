/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.ClienteForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.ClienteDto;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.model.ClienteContactoModel;
import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.service.DomicilioService;
import com.empresa.thyssenplastic.service.ClienteContactoService;
import com.empresa.thyssenplastic.service.ClienteDomicilioService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.DomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteDomicilioServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import java.text.SimpleDateFormat;
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
public class ClienteController {
    

    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public String getHomeCliente(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        ClienteForm clienteForm = new ClienteForm();
        clienteForm.setPk("-1");
        clienteForm.setAction("add");
        model.addAttribute("clienteForm", clienteForm);  
        model.addAttribute("titleCliente", "Nuevo Cliente");  
        model.addAttribute("buttonLabel", "Guardar");
        
        ClienteService clienteService = new ClienteServiceImpl();   
        List<ClienteModel> clientes = clienteService.getAll();
        
        List<ClienteDto> clientesDtos = new ArrayList<ClienteDto>();
        for(ClienteModel cliente: clientes) {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setPk(cliente.getId().toString());
            clienteDto.setCuit(cliente.getCuit());
            clienteDto.setRazonSocial(cliente.getRazonSocial());
            if(cliente.getTelefono() != null) {
                clienteDto.setTelefono(cliente.getTelefono());
            }
            if(cliente.getMail() != null) {
                clienteDto.setMail(cliente.getMail());
            }
            clientesDtos.add(clienteDto);
        }
                       
        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> rubros = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("rubroCliente");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                rubros.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        model.addAttribute("rubroList", rubros);        
        model.addAttribute("clientes", clientesDtos);        
                
        return "/cliente/cliente";
    }
    
    @RequestMapping(value = "/cliente/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveCliente(@ModelAttribute("clienteForm")ClienteForm clienteForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(clienteForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        ClienteService clienteService = new ClienteServiceImpl();        
        
        String cuit = clienteForm.getCuit();
        String razonSocial = clienteForm.getRazonSocial();
        String sessionId = req.getSession().getId();
        String id = clienteForm.getPk();
            
        ClienteModel clienteModel = null;
        if(id.equalsIgnoreCase("-1")) {
            clienteModel = new ClienteModel();
        } else {
            clienteModel = clienteService.getByPk(Integer.valueOf(id));
            if(clienteModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de cliente inválido.");
                return modelAndView;                
            } 
        }        
        clienteModel.setCuit(cuit);
        clienteModel.setTipoDocumento(clienteForm.getTipoDocumento());
        clienteModel.setRazonSocial(razonSocial);
        if(clienteForm.getIdRubro() != null && !clienteForm.getIdRubro().equals("-1")) {
            clienteModel.setIdRubro(Integer.valueOf(clienteForm.getIdRubro()));
        } else {
            clienteModel.setIdRubro(null);
        }
        if(clienteForm.getFechaAlta() != null && !clienteForm.getFechaAlta().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fecha = formato.parse(clienteForm.getFechaAlta());
            clienteModel.setFechaAlta(fecha);
        } else {
            clienteModel.setFechaAlta(null);
        }        
        if(clienteForm.getHorario() != null) {
            clienteModel.setHorario(clienteForm.getHorario());
        } else {
            clienteModel.setHorario(null);
        }
        if(clienteForm.getMail() != null) {
            clienteModel.setMail(clienteForm.getMail());
        } else {
            clienteModel.setMail(null);
        }
        if(clienteForm.getTelefono() != null) {
            clienteModel.setTelefono(clienteForm.getTelefono());
        } else {
            clienteModel.setTelefono(null);
        }
        if(clienteForm.getObservaciones() != null) {
            clienteModel.setObservaciones(clienteForm.getObservaciones());
        } else {
            clienteModel.setObservaciones(null);
        }
        
        if(clienteForm.getAction().equalsIgnoreCase("add") || clienteForm.getAction().equalsIgnoreCase("edit")) {
            clienteService.save(clienteModel);
        } else {
            if(clienteForm.getAction().equalsIgnoreCase("remove")) {
                if(clienteModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de cliente inválido.");
                    return modelAndView;                                    
                }
                
                //Elimino todos los contactos del cliente
                ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
                ContactoService contactoService = new ContactoServiceImpl();
                List<ClienteContactoModel> clienteContactos = clienteContactoService.getByPkCliente(clienteModel.getId());
                if(clienteContactos != null && !clienteContactos.isEmpty()) {                    
                    List<Integer> contactosPk = new ArrayList<Integer>();
                    for(ClienteContactoModel provedorContactos :clienteContactos) {
                        contactosPk.add(provedorContactos.getIdContacto());
                    }                    
                    List<ContactoModel> contactos = contactoService.getByPkList(contactosPk);
                    if(contactos != null && !contactos.isEmpty()) {
                        for(ContactoModel contacto: contactos) {
                            contactoService.delete(contacto);
                        }
                    }
                    for(ClienteContactoModel provedorContactos :clienteContactos) {
                        clienteContactoService.delete(provedorContactos);
                    }
                }                
                //@TODO: hacer lo mismo con los domicilios
                //Elimino todos los domicilios del cliente
                ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
                DomicilioService domicilioService = new DomicilioServiceImpl();
                List<ClienteDomicilioModel> clienteDomicilios = clienteDomicilioService.getByPkCliente(clienteModel.getId());
                if(clienteDomicilios != null && !clienteDomicilios.isEmpty()) {                    
                    List<Integer> domiciliosPk = new ArrayList<Integer>();
                    for(ClienteDomicilioModel provedorDomicilios :clienteDomicilios) {
                        domiciliosPk.add(provedorDomicilios.getIdDomicilio());
                    }                    
                    List<DomicilioModel> domicilios = domicilioService.getByPkList(domiciliosPk);
                    if(domicilios != null && !domicilios.isEmpty()) {
                        for(DomicilioModel domicilio: domicilios) {
                            domicilioService.delete(domicilio);
                        }
                    }
                    for(ClienteContactoModel provedorContactos :clienteContactos) {
                        clienteContactoService.delete(provedorContactos);
                    }
                }                
                
                clienteService.delete(clienteModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/cliente");

        return modelAndView; 
    }

    @RequestMapping(value = "/cliente/edit/{clientepk}", method = RequestMethod.GET)
    public String editCliente(@PathVariable String clientepk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(clientepk == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido");         
            return "/error/error";                
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(Integer.valueOf(clientepk));
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ClienteForm clienteForm = new ClienteForm();
        clienteForm.setPk(cliente.getId().toString());
        clienteForm.setCuit(cliente.getCuit());
        clienteForm.setTipoDocumento(cliente.getTipoDocumento());
        clienteForm.setRazonSocial(cliente.getRazonSocial());
        if(cliente.getFechaAlta() != null) {
            clienteForm.setFechaAlta(cliente.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(cliente.getTelefono() != null) {
            clienteForm.setTelefono(cliente.getTelefono());
        }
        if(cliente.getMail() != null) {
            clienteForm.setMail(cliente.getMail());
        }
        if(cliente.getHorario() != null) {
            clienteForm.setHorario(cliente.getHorario());
        }
        if(cliente.getIdRubro() != null) {
            clienteForm.setIdRubro(cliente.getIdRubro().toString());
        }
        if(cliente.getObservaciones() != null) {
            clienteForm.setObservaciones(cliente.getObservaciones());
        }
        
        clienteForm.setAction("edit");
        model.addAttribute("clienteForm", clienteForm);  
        model.addAttribute("titleCliente", "Editar Cliente");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("clienteName", cliente.getId() + " - " + cliente.getRazonSocial());        
        
        List<ClienteModel> clientes = clienteService.getAll();
        
        List<ClienteDto> clientesDtos = new ArrayList<ClienteDto>();
        for(ClienteModel p: clientes) {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setPk(p.getId().toString());
            clienteDto.setCuit(p.getCuit());
            clienteDto.setRazonSocial(p.getRazonSocial());
            if(p.getTelefono() != null) {
                clienteDto.setTelefono(p.getTelefono());
            }
            if(p.getMail() != null) {
                clienteDto.setMail(p.getMail());
            }
            
            clientesDtos.add(clienteDto);
            
        }
        
        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> rubros = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("rubroCliente");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                rubros.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        model.addAttribute("rubroList", rubros);        
        model.addAttribute("clientes", clientesDtos);
                
        return "/cliente/cliente";        
    }
    
    @RequestMapping(value = "/cliente/remove/{clientepk}", method = RequestMethod.GET)
    public String removeCliente(@PathVariable String clientepk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(clientepk == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido");         
            return "/error/error";                
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(Integer.valueOf(clientepk));
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        //Chequeo que no tenga ordenes de producción antes de borrar al cliente
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAll();

        for(OrdenDeProduccionModel ordenDeProduccion: ordenDeProducciones) {
            if(ordenDeProduccion.getIdCliente() == Integer.parseInt(clientepk)){
                model.addAttribute("haveProductionOrders", "disabled");
                break;
            }
        }

        ClienteForm clienteForm = new ClienteForm();
        clienteForm.setPk(cliente.getId().toString());
        clienteForm.setCuit(cliente.getCuit());
        clienteForm.setTipoDocumento(cliente.getTipoDocumento());
        clienteForm.setRazonSocial(cliente.getRazonSocial());
        if(cliente.getFechaAlta() != null) {
            clienteForm.setFechaAlta(cliente.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(cliente.getTelefono() != null) {
            clienteForm.setTelefono(cliente.getTelefono());
        }
        if(cliente.getMail() != null) {
            clienteForm.setMail(cliente.getMail());
        }
        if(cliente.getHorario() != null) {
            clienteForm.setHorario(cliente.getHorario());
        }
        if(cliente.getIdRubro() != null) {
            clienteForm.setIdRubro(cliente.getIdRubro().toString());
        }
        if(cliente.getObservaciones() != null) {
            clienteForm.setObservaciones(cliente.getObservaciones());
        }        
        clienteForm.setAction("remove");
        model.addAttribute("clienteForm", clienteForm);  
        model.addAttribute("titleCliente", "Eliminar Cliente");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("clienteName", cliente.getId() + " - " + cliente.getRazonSocial());
        
        List<ClienteModel> clientes = clienteService.getAll();
        
        List<ClienteDto> clientesDtos = new ArrayList<ClienteDto>();
        for(ClienteModel p: clientes) {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setPk(p.getId().toString());
            clienteDto.setCuit(p.getCuit());
            clienteDto.setRazonSocial(p.getRazonSocial());
            if(p.getTelefono() != null) {
                clienteDto.setTelefono(p.getTelefono());
            }
            if(p.getMail() != null) {
                clienteDto.setMail(p.getMail());
            }
            
            clientesDtos.add(clienteDto);
        }

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> rubros = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("rubroCliente");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                rubros.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        model.addAttribute("rubroList", rubros);                
        model.addAttribute("clientes", clientesDtos);
                
        return "/cliente/cliente";        
    }    
}
