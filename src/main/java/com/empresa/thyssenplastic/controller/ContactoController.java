/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.ContactoForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.ContactoDto;
import com.empresa.thyssenplastic.model.ClienteContactoModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.service.ClienteContactoService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.service.ProveedorContactoService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.impl.ClienteContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author gusta
 */
@Controller
public class ContactoController {

    @RequestMapping(value = "/contacto/proveedorpk/{proveedorpk}", method = RequestMethod.GET)
    public String getHomeContactoProveedor(@PathVariable String proveedorpk, HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(proveedorpk == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido");         
            return "/error/error";                
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(Integer.valueOf(proveedorpk));
        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = proveedor.getId() + " - " + proveedor.getRazonSocial();
        
        ContactoForm contactoForm = new ContactoForm();
        contactoForm.setPk("-1");
        contactoForm.setAction("add");
        
        model.addAttribute("contactoForm", contactoForm);         
        model.addAttribute("pkExternal", proveedorpk);
        model.addAttribute("prefixUrl", "proveedor");
        model.addAttribute("labelBase", "Proveedores");
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoContacto", "proveedor");
        model.addAttribute("titleContacto", "Nuevo Contacto");  
        model.addAttribute("buttonLabel", "Guardar");
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("rol");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        List<ContactoDto> contactosDtos = new ArrayList<ContactoDto>();
        ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
        List<ProveedorContactoModel> proveedorContactos = proveedorContactoService.getByPkProveedor(Integer.valueOf(proveedorpk));
        if(proveedorContactos != null && !proveedorContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ProveedorContactoModel provedorContactos :proveedorContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }
            ContactoService contactoService = new ContactoServiceImpl();
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                ContactoDto contactoDto = new ContactoDto();
                contactoDto.setId(c.getId().toString());
                contactoDto.setCuit(c.getCuit());
                contactoDto.setNombre(c.getNombre());
                contactoDto.setTelefono(c.getTelefono());
                contactoDto.setMail(c.getMail());

                TipoModel t = tiposModelMap.get(c.getIdRol());
                if(t != null) {
                    contactoDto.setRol(t.getValor());
                }
                
                contactosDtos.add(contactoDto);
            }
        }         
        Map<String,String> roles = new LinkedHashMap<String,String>();      
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                roles.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        model.addAttribute("rolList", roles);                
        model.addAttribute("contactos", contactosDtos);       
        
        System.out.println("*** getHomeContacto proveedorpk:"+proveedorpk);
        return "/contacto/contacto";
    }
    
    @RequestMapping(value = "/contacto/clientepk/{clientepk}", method = RequestMethod.GET)
    public String getHomeContactoCliente(@PathVariable String clientepk, HttpServletRequest req, ModelMap model) {

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
        
        String externalName = cliente.getId() + " - " + cliente.getRazonSocial();
        
        ContactoForm contactoForm = new ContactoForm();
        contactoForm.setPk("-1");
        contactoForm.setAction("add");
        
        model.addAttribute("contactoForm", contactoForm);         
        model.addAttribute("pkExternal", clientepk);        
        model.addAttribute("prefixUrl", "cliente");
        model.addAttribute("labelBase", "Clientes");
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoContacto", "cliente");
        model.addAttribute("titleContacto", "Nuevo Contacto");  
        model.addAttribute("buttonLabel", "Guardar");
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("rol");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        List<ContactoDto> contactosDtos = new ArrayList<ContactoDto>();
        ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
        List<ClienteContactoModel> clienteContactos = clienteContactoService.getByPkCliente(Integer.valueOf(clientepk));
        if(clienteContactos != null && !clienteContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ClienteContactoModel provedorContactos :clienteContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }
            ContactoService contactoService = new ContactoServiceImpl();
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                ContactoDto contactoDto = new ContactoDto();
                contactoDto.setId(c.getId().toString());
                contactoDto.setCuit(c.getCuit());
                contactoDto.setNombre(c.getNombre());
                contactoDto.setTelefono(c.getTelefono());
                contactoDto.setMail(c.getMail());

                TipoModel t = tiposModelMap.get(c.getIdRol());
                if(t != null) {
                    contactoDto.setRol(t.getValor());
                }
                
                contactosDtos.add(contactoDto);
            }
        }         
        Map<String,String> roles = new LinkedHashMap<String,String>();      
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                roles.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        model.addAttribute("rolList", roles);                
        model.addAttribute("contactos", contactosDtos);       
        
        System.out.println("*** getHomeContacto clientepk:"+clientepk);
        return "/contacto/contacto";
    }
    
    @RequestMapping(value = "/contacto/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditContacto(@ModelAttribute("contactoForm")ContactoForm contactoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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

        if(contactoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        ContactoService contactoService = new ContactoServiceImpl();        
        
        String cuit = contactoForm.getCuit();
        String nombre = contactoForm.getNombre();
        String pkExternal = contactoForm.getPkExternal();
        String tipoContacto = contactoForm.getTipoContacto();
        String sessionId = req.getSession().getId();
        String id = contactoForm.getPk();
                
        ContactoModel contactoModel = null;
        if(id.equalsIgnoreCase("-1")) {
            contactoModel = new ContactoModel();
        } else {
            contactoModel = contactoService.getByPk(Integer.valueOf(id));
            if(contactoModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de contacto inválido.");
                return modelAndView;                
            } 
        }        
        
        contactoModel.setCuit(cuit);
        contactoModel.setTipoDocumento(contactoForm.getTipoDocumento());
        contactoModel.setNombre(nombre);
        if(contactoForm.getIdRol() != null && !contactoForm.getIdRol().equals("-1")) {
            contactoModel.setIdRol(Integer.valueOf(contactoForm.getIdRol()));
        } else {
            contactoModel.setIdRol(null);
        }
        if(contactoForm.getMail() != null) {
            contactoModel.setMail(contactoForm.getMail());
        } else {
            contactoModel.setMail(null);
        }
        if(contactoForm.getTelefono() != null) {
            contactoModel.setTelefono(contactoForm.getTelefono());
        } else {
            contactoModel.setTelefono(null);
        }
        
        if(contactoForm.getAction().equalsIgnoreCase("add") || contactoForm.getAction().equalsIgnoreCase("edit")) {
            contactoService.save(contactoModel);
            
            if(tipoContacto.equalsIgnoreCase("proveedor") && contactoForm.getAction().equalsIgnoreCase("add")) {

                ProveedorContactoModel proveedorContactoModel = new ProveedorContactoModel();
                proveedorContactoModel.setIdContacto(contactoModel.getId());
                proveedorContactoModel.setIdProveedor(Integer.valueOf(pkExternal));

                ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
                proveedorContactoService.save(proveedorContactoModel);
            }            
            if(tipoContacto.equalsIgnoreCase("cliente") && contactoForm.getAction().equalsIgnoreCase("add")) {

                ClienteContactoModel clienteContactoModel = new ClienteContactoModel();
                clienteContactoModel.setIdContacto(contactoModel.getId());
                clienteContactoModel.setIdCliente(Integer.valueOf(pkExternal));

                ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
                clienteContactoService.save(clienteContactoModel);
            }            
            
        } else if(contactoForm.getAction().equalsIgnoreCase("remove")) {
            if(tipoContacto.equalsIgnoreCase("proveedor")) {
                ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
                ProveedorContactoModel proveedorContacto = proveedorContactoService.getByPkProveedorAndContacto(Integer.valueOf(pkExternal), contactoModel.getId());
                if(proveedorContacto != null) {                    
                    proveedorContactoService.delete(proveedorContacto);
                }
                contactoService.delete(contactoModel);
            }
            if(tipoContacto.equalsIgnoreCase("cliente")) {
                ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
                ClienteContactoModel clienteContacto = clienteContactoService.getByPkClienteAndContacto(Integer.valueOf(pkExternal), contactoModel.getId());
                if(clienteContacto != null) {                    
                    clienteContactoService.delete(clienteContacto);
                }
                contactoService.delete(contactoModel);
            }            
        } else {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: operación inválida.");
            return modelAndView;                                
        }
        
        if(tipoContacto.equalsIgnoreCase("proveedor")) {            
            modelAndView.setViewName("redirect:/contacto/proveedorpk/"+pkExternal);
        }
        if(tipoContacto.equalsIgnoreCase("cliente")) {            
            modelAndView.setViewName("redirect:/contacto/clientepk/"+pkExternal);
        }

        return modelAndView; 
    }
    
    @RequestMapping(value = "/contacto/proveedorpk/{proveedorpk}/edit/{contactopk}", method = RequestMethod.GET)
    public String editProveedorContacto(@PathVariable String proveedorpk, @PathVariable String contactopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(contactopk == null) {
            model.addAttribute("errorMessage", "Error: Contacto inválido");         
            return "/error/error";                
        }

        if(proveedorpk == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido");         
            return "/error/error";                
        }
        
        ContactoService contactoService = new ContactoServiceImpl();   
        ContactoModel contacto = contactoService.getByPk(Integer.valueOf(contactopk));
        if(contacto == null) {
            model.addAttribute("errorMessage", "Error: Contacto inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(Integer.valueOf(proveedorpk));
        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = proveedor.getId() + " - " + proveedor.getRazonSocial();
        
        ContactoForm contactoForm = new ContactoForm();
        contactoForm.setPk(contacto.getId().toString());
        contactoForm.setCuit(contacto.getCuit());
        contactoForm.setTipoDocumento(contacto.getTipoDocumento());
        contactoForm.setNombre(contacto.getNombre());
        if(contacto.getTelefono() != null) {
            contactoForm.setTelefono(contacto.getTelefono());
        }
        if(contacto.getMail() != null) {
            contactoForm.setMail(contacto.getMail());
        }
        if(contacto.getIdRol() != null) {
            contactoForm.setIdRol(contacto.getIdRol().toString());
        }
        contactoForm.setAction("edit");        
        model.addAttribute("contactoForm", contactoForm);  
        model.addAttribute("pkExternal", proveedorpk);
        model.addAttribute("prefixUrl", "proveedor");
        model.addAttribute("labelBase", "Proveedores");
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoContacto", "proveedor");
        model.addAttribute("titleContacto", "Editar Contacto");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("contactoName", contacto.getId() + " - " + contacto.getNombre());

        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("rol");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        List<ContactoDto> contactosDtos = new ArrayList<ContactoDto>();
        ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
        List<ProveedorContactoModel> proveedorContactos = proveedorContactoService.getByPkProveedor(Integer.valueOf(proveedorpk));
        if(proveedorContactos != null && !proveedorContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ProveedorContactoModel provedorContactos :proveedorContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }            
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                ContactoDto contactoDto = new ContactoDto();
                contactoDto.setId(c.getId().toString());
                contactoDto.setCuit(c.getCuit());
                contactoDto.setNombre(c.getNombre());
                contactoDto.setTelefono(c.getTelefono());
                contactoDto.setMail(c.getMail());

                TipoModel t = tiposModelMap.get(c.getIdRol());
                if(t != null) {
                    contactoDto.setRol(t.getValor());
                }
                
                contactosDtos.add(contactoDto);
            }
        }             
        Map<String,String> roles = new LinkedHashMap<String,String>();        
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                roles.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        model.addAttribute("rolList", roles);                
        model.addAttribute("contactos", contactosDtos);       
                
        return "/contacto/contacto";        
    }

    @RequestMapping(value = "/contacto/clientepk/{clientepk}/edit/{contactopk}", method = RequestMethod.GET)
    public String editClienteContacto(@PathVariable String clientepk, @PathVariable String contactopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(contactopk == null) {
            model.addAttribute("errorMessage", "Error: Contacto inválido");         
            return "/error/error";                
        }

        if(clientepk == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido");         
            return "/error/error";                
        }
        
        ContactoService contactoService = new ContactoServiceImpl();   
        ContactoModel contacto = contactoService.getByPk(Integer.valueOf(contactopk));
        if(contacto == null) {
            model.addAttribute("errorMessage", "Error: Contacto inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(Integer.valueOf(clientepk));
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = cliente.getId() + " - " + cliente.getRazonSocial();
        
        ContactoForm contactoForm = new ContactoForm();
        contactoForm.setPk(contacto.getId().toString());
        contactoForm.setCuit(contacto.getCuit());
        contactoForm.setTipoDocumento(contacto.getTipoDocumento());
        contactoForm.setNombre(contacto.getNombre());
        if(contacto.getTelefono() != null) {
            contactoForm.setTelefono(contacto.getTelefono());
        }
        if(contacto.getMail() != null) {
            contactoForm.setMail(contacto.getMail());
        }
        if(contacto.getIdRol() != null) {
            contactoForm.setIdRol(contacto.getIdRol().toString());
        }
        contactoForm.setAction("edit");        
        model.addAttribute("contactoForm", contactoForm);  
        model.addAttribute("pkExternal", clientepk);
        model.addAttribute("prefixUrl", "cliente");
        model.addAttribute("labelBase", "Clientes");
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoContacto", "cliente");
        model.addAttribute("titleContacto", "Editar Contacto");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("contactoName", contacto.getId() + " - " + contacto.getNombre());

        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("rol");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        List<ContactoDto> contactosDtos = new ArrayList<ContactoDto>();
        ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
        List<ClienteContactoModel> clienteContactos = clienteContactoService.getByPkCliente(Integer.valueOf(clientepk));
        if(clienteContactos != null && !clienteContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ClienteContactoModel provedorContactos :clienteContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }            
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                ContactoDto contactoDto = new ContactoDto();
                contactoDto.setId(c.getId().toString());
                contactoDto.setCuit(c.getCuit());
                contactoDto.setNombre(c.getNombre());
                contactoDto.setTelefono(c.getTelefono());
                contactoDto.setMail(c.getMail());

                TipoModel t = tiposModelMap.get(c.getIdRol());
                if(t != null) {
                    contactoDto.setRol(t.getValor());
                }
                
                contactosDtos.add(contactoDto);
            }
        }             
        Map<String,String> roles = new LinkedHashMap<String,String>();        
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                roles.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        model.addAttribute("rolList", roles);                
        model.addAttribute("contactos", contactosDtos);       
                
        return "/contacto/contacto";        
    }
    
    @RequestMapping(value = "/contacto/proveedorpk/{proveedorpk}/remove/{contactopk}", method = RequestMethod.GET)
    public String removeProveedorContacto(@PathVariable String proveedorpk, @PathVariable String contactopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(contactopk == null) {
            model.addAttribute("errorMessage", "Error: Contacto inválido");         
            return "/error/error";                
        }

        if(proveedorpk == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido");         
            return "/error/error";                
        }
        
        ContactoService contactoService = new ContactoServiceImpl();   
        ContactoModel contacto = contactoService.getByPk(Integer.valueOf(contactopk));
        if(contacto == null) {
            model.addAttribute("errorMessage", "Error: Contacto inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(Integer.valueOf(proveedorpk));
        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = proveedor.getId() + " - " + proveedor.getRazonSocial();
        
        ContactoForm contactoForm = new ContactoForm();
        contactoForm.setPk(contacto.getId().toString());
        contactoForm.setCuit(contacto.getCuit());
        contactoForm.setTipoDocumento(contacto.getTipoDocumento());
        contactoForm.setNombre(contacto.getNombre());
        if(contacto.getTelefono() != null) {
            contactoForm.setTelefono(contacto.getTelefono());
        }
        if(contacto.getMail() != null) {
            contactoForm.setMail(contacto.getMail());
        }
        if(contacto.getIdRol() != null) {
            contactoForm.setIdRol(contacto.getIdRol().toString());
        }        
        contactoForm.setAction("remove");        
        model.addAttribute("contactoForm", contactoForm);  
        model.addAttribute("pkExternal", proveedorpk);
        model.addAttribute("prefixUrl", "proveedor");
        model.addAttribute("labelBase", "Proveedores");
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoContacto", "proveedor");
        model.addAttribute("titleContacto", "Eliminar Contacto");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("contactoName", contacto.getId() + " - " + contacto.getNombre());
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("rol");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        List<ContactoDto> contactosDtos = new ArrayList<ContactoDto>();
        ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
        List<ProveedorContactoModel> proveedorContactos = proveedorContactoService.getByPkProveedor(Integer.valueOf(proveedorpk));
        if(proveedorContactos != null && !proveedorContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ProveedorContactoModel provedorContactos :proveedorContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }            
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                ContactoDto contactoDto = new ContactoDto();
                contactoDto.setId(c.getId().toString());
                contactoDto.setCuit(c.getCuit());
                contactoDto.setNombre(c.getNombre());
                contactoDto.setTelefono(c.getTelefono());
                contactoDto.setMail(c.getMail());

                TipoModel t = tiposModelMap.get(c.getIdRol());
                if(t != null) {
                    contactoDto.setRol(t.getValor());
                }
                
                contactosDtos.add(contactoDto);
            }
        }       
        Map<String,String> roles = new LinkedHashMap<String,String>();
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                roles.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        model.addAttribute("rolList", roles);                
        model.addAttribute("contactos", contactosDtos);       
                
        return "/contacto/contacto";        
    }    
    
    @RequestMapping(value = "/contacto/clientepk/{clientepk}/remove/{contactopk}", method = RequestMethod.GET)
    public String removeClienteContacto(@PathVariable String clientepk, @PathVariable String contactopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(contactopk == null) {
            model.addAttribute("errorMessage", "Error: Contacto inválido");         
            return "/error/error";                
        }

        if(clientepk == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido");         
            return "/error/error";                
        }
        
        ContactoService contactoService = new ContactoServiceImpl();   
        ContactoModel contacto = contactoService.getByPk(Integer.valueOf(contactopk));
        if(contacto == null) {
            model.addAttribute("errorMessage", "Error: Contacto inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(Integer.valueOf(clientepk));
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = cliente.getId() + " - " + cliente.getRazonSocial();
        
        ContactoForm contactoForm = new ContactoForm();
        contactoForm.setPk(contacto.getId().toString());
        contactoForm.setCuit(contacto.getCuit());
        contactoForm.setTipoDocumento(contacto.getTipoDocumento());
        contactoForm.setNombre(contacto.getNombre());
        if(contacto.getTelefono() != null) {
            contactoForm.setTelefono(contacto.getTelefono());
        }
        if(contacto.getMail() != null) {
            contactoForm.setMail(contacto.getMail());
        }
        if(contacto.getIdRol() != null) {
            contactoForm.setIdRol(contacto.getIdRol().toString());
        }        
        contactoForm.setAction("remove");        
        model.addAttribute("contactoForm", contactoForm);  
        model.addAttribute("pkExternal", clientepk);
        model.addAttribute("prefixUrl", "cliente");
        model.addAttribute("labelBase", "Clientes");
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoContacto", "cliente");
        model.addAttribute("titleContacto", "Eliminar Contacto");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("contactoName", contacto.getId() + " - " + contacto.getNombre());
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("rol");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        List<ContactoDto> contactosDtos = new ArrayList<ContactoDto>();
        ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
        List<ClienteContactoModel> clienteContactos = clienteContactoService.getByPkCliente(Integer.valueOf(clientepk));
        if(clienteContactos != null && !clienteContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ClienteContactoModel provedorContactos :clienteContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }            
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                ContactoDto contactoDto = new ContactoDto();
                contactoDto.setId(c.getId().toString());
                contactoDto.setCuit(c.getCuit());
                contactoDto.setNombre(c.getNombre());
                contactoDto.setTelefono(c.getTelefono());
                contactoDto.setMail(c.getMail());

                TipoModel t = tiposModelMap.get(c.getIdRol());
                if(t != null) {
                    contactoDto.setRol(t.getValor());
                }
                
                contactosDtos.add(contactoDto);
            }
        }       
        Map<String,String> roles = new LinkedHashMap<String,String>();
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                roles.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        model.addAttribute("rolList", roles);                
        model.addAttribute("contactos", contactosDtos);       
                
        return "/contacto/contacto";        
    } 
    
}
