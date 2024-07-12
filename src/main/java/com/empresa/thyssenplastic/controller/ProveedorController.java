/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.ProveedorForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.ProveedorDto;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.model.InsumoModel;
import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import com.empresa.thyssenplastic.model.ProveedorDomicilioModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.service.DomicilioService;
import com.empresa.thyssenplastic.service.InsumoService;
import com.empresa.thyssenplastic.service.ProveedorContactoService;
import com.empresa.thyssenplastic.service.ProveedorDomicilioService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.DomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.InsumoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorDomicilioServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
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
public class ProveedorController {
    

    @RequestMapping(value = "/proveedor", method = RequestMethod.GET)
    public String getHomeProveedor(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        ProveedorForm proveedorForm = new ProveedorForm();
        proveedorForm.setPk("-1");
        proveedorForm.setAction("add");
        model.addAttribute("proveedorForm", proveedorForm);  
        model.addAttribute("titleProveedor", "Nuevo Proveedor");  
        model.addAttribute("buttonLabel", "Guardar");
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        List<ProveedorModel> proveedores = proveedorService.getAll();
        
        List<ProveedorDto> proveedoresDtos = new ArrayList<ProveedorDto>();
        for(ProveedorModel proveedor: proveedores) {
            ProveedorDto proveedorDto = new ProveedorDto();
            proveedorDto.setPk(proveedor.getId().toString());
            proveedorDto.setCuit(proveedor.getCuit());
            proveedorDto.setRazonSocial(proveedor.getRazonSocial());
            if(proveedor.getMail() != null) {
                proveedorDto.setMail(proveedor.getMail());
            }
            if(proveedor.getTelefono() != null) {
                proveedorDto.setTelefono(proveedor.getTelefono());
            }
            proveedoresDtos.add(proveedorDto);
        }
                       
        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> rubros = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("rubroProveedor");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                rubros.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        model.addAttribute("rubroList", rubros);        
        model.addAttribute("proveedores", proveedoresDtos);        
                
        return "/proveedor/proveedor";
    }
    
    @RequestMapping(value = "/proveedor/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveProveedor(@ModelAttribute("proveedorForm")ProveedorForm proveedorForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(proveedorForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();        
        
        String cuit = proveedorForm.getCuit();
        String razonSocial = proveedorForm.getRazonSocial();
        String sessionId = req.getSession().getId();
        String id = proveedorForm.getPk();
            
        ProveedorModel proveedorModel = null;
        if(id.equalsIgnoreCase("-1")) {
            proveedorModel = new ProveedorModel();
        } else {
            proveedorModel = proveedorService.getByPk(Integer.valueOf(id));
            if(proveedorModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de proveedor inválido.");
                return modelAndView;                
            } 
        }        
        proveedorModel.setCuit(cuit);
        proveedorModel.setTipoDocumento(proveedorForm.getTipoDocumento());
        proveedorModel.setRazonSocial(razonSocial);
        if(proveedorForm.getIdRubro() != null && !proveedorForm.getIdRubro().equals("-1")) {
            proveedorModel.setIdRubro(Integer.valueOf(proveedorForm.getIdRubro()));
        } else {
            proveedorModel.setIdRubro(null);
        }
        if(proveedorForm.getFechaAlta() != null && !proveedorForm.getFechaAlta().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fecha = formato.parse(proveedorForm.getFechaAlta());
            proveedorModel.setFechaAlta(fecha);
        } else {
            proveedorModel.setFechaAlta(null);
        }        
        if(proveedorForm.getHorario() != null) {
            proveedorModel.setHorario(proveedorForm.getHorario());
        } else {
            proveedorModel.setHorario(null);
        }
        if(proveedorForm.getMail() != null) {
            proveedorModel.setMail(proveedorForm.getMail());
        } else {
            proveedorModel.setMail(null);
        }
        if(proveedorForm.getTelefono() != null) {
            proveedorModel.setTelefono(proveedorForm.getTelefono());
        } else {
            proveedorModel.setTelefono(null);
        }
        if(proveedorForm.getObservaciones() != null) {
            proveedorModel.setObservaciones(proveedorForm.getObservaciones());
        } else {
            proveedorModel.setObservaciones(null);
        }
        
        if(proveedorForm.getAction().equalsIgnoreCase("add") || proveedorForm.getAction().equalsIgnoreCase("edit")) {
            proveedorService.save(proveedorModel);
        } else {
            if(proveedorForm.getAction().equalsIgnoreCase("remove")) {
                if(proveedorModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de proveedor inválido.");
                    return modelAndView;                                    
                }
                
                InsumoService insumoService = new InsumoServiceImpl();
                List<InsumoModel> insumos = insumoService.getAllByIdProveedor(proveedorModel.getId());
                if(insumos != null && !insumos.isEmpty()) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: no es posible eliminar el proveedor. Posee insumnos asociados.");
                    return modelAndView;                                                        
                }
                
                //Elimino todos los contactos del proveedor
                ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
                ContactoService contactoService = new ContactoServiceImpl();
                List<ProveedorContactoModel> proveedorContactos = proveedorContactoService.getByPkProveedor(proveedorModel.getId());
                if(proveedorContactos != null && !proveedorContactos.isEmpty()) {                    
                    List<Integer> contactosPk = new ArrayList<Integer>();
                    for(ProveedorContactoModel provedorContactos :proveedorContactos) {
                        contactosPk.add(provedorContactos.getIdContacto());
                    }                    
                    List<ContactoModel> contactos = contactoService.getByPkList(contactosPk);
                    if(contactos != null && !contactos.isEmpty()) {
                        for(ContactoModel contacto: contactos) {
                            contactoService.delete(contacto);
                        }
                    }
                    for(ProveedorContactoModel provedorContactos :proveedorContactos) {
                        proveedorContactoService.delete(provedorContactos);
                    }
                }                
                
                //Elimino todos los domicilios del proveedor
                ProveedorDomicilioService proveedorDomicilioService = new ProveedorDomicilioServiceImpl();
                DomicilioService domicilioService = new DomicilioServiceImpl();
                List<ProveedorDomicilioModel> proveedorDomicilios = proveedorDomicilioService.getByPkProveedor(proveedorModel.getId());
                if(proveedorDomicilios != null && !proveedorDomicilios.isEmpty()) {                    
                    List<Integer> domiciliosPk = new ArrayList<Integer>();
                    for(ProveedorDomicilioModel provedorDomicilios :proveedorDomicilios) {
                        domiciliosPk.add(provedorDomicilios.getIdDomicilio());
                    }                    
                    List<DomicilioModel> domicilios = domicilioService.getByPkList(domiciliosPk);
                    if(domicilios != null && !domicilios.isEmpty()) {
                        for(DomicilioModel domicilio: domicilios) {
                            domicilioService.delete(domicilio);
                        }
                    }
                    for(ProveedorContactoModel provedorContactos :proveedorContactos) {
                        proveedorContactoService.delete(provedorContactos);
                    }
                }                
                
                proveedorService.delete(proveedorModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/proveedor");

        return modelAndView; 
    }

    @RequestMapping(value = "/proveedor/edit/{proveedorpk}", method = RequestMethod.GET)
    public String editProveedor(@PathVariable String proveedorpk, HttpServletRequest req, ModelMap model) throws Exception {
                
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
        
        ProveedorForm proveedorForm = new ProveedorForm();
        proveedorForm.setPk(proveedor.getId().toString());
        proveedorForm.setCuit(proveedor.getCuit());
        proveedorForm.setTipoDocumento(proveedor.getTipoDocumento());
        proveedorForm.setRazonSocial(proveedor.getRazonSocial());
        if(proveedor.getFechaAlta() != null) {
            proveedorForm.setFechaAlta(proveedor.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(proveedor.getTelefono() != null) {
            proveedorForm.setTelefono(proveedor.getTelefono());
        }
        if(proveedor.getMail() != null) {
            proveedorForm.setMail(proveedor.getMail());
        }
        if(proveedor.getHorario() != null) {
            proveedorForm.setHorario(proveedor.getHorario());
        }
        if(proveedor.getIdRubro() != null) {
            proveedorForm.setIdRubro(proveedor.getIdRubro().toString());
        }
        if(proveedor.getObservaciones() != null) {
            proveedorForm.setObservaciones(proveedor.getObservaciones());
        }
        
        proveedorForm.setAction("edit");
        model.addAttribute("proveedorForm", proveedorForm);  
        model.addAttribute("titleProveedor", "Editar Proveedor");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("proveedorName", proveedor.getId() + " - " + proveedor.getRazonSocial());        
        
        List<ProveedorModel> proveedores = proveedorService.getAll();
        
        List<ProveedorDto> proveedoresDtos = new ArrayList<ProveedorDto>();
        for(ProveedorModel p: proveedores) {
            ProveedorDto proveedorDto = new ProveedorDto();
            proveedorDto.setPk(p.getId().toString());
            proveedorDto.setCuit(p.getCuit());
            proveedorDto.setRazonSocial(p.getRazonSocial());
            if(p.getMail() != null) {
                proveedorDto.setMail(p.getMail());
            }
            if(p.getTelefono() != null) {
                proveedorDto.setTelefono(p.getTelefono());
            }
            
            proveedoresDtos.add(proveedorDto);
        }
        
        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> rubros = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("rubroProveedor");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                rubros.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        model.addAttribute("rubroList", rubros);        
        model.addAttribute("proveedores", proveedoresDtos);
                
        return "/proveedor/proveedor";        
    }
    
    @RequestMapping(value = "/proveedor/remove/{proveedorpk}", method = RequestMethod.GET)
    public String removeProveedor(@PathVariable String proveedorpk, HttpServletRequest req, ModelMap model) throws Exception {
                
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
        
        ProveedorForm proveedorForm = new ProveedorForm();
        proveedorForm.setPk(proveedor.getId().toString());
        proveedorForm.setCuit(proveedor.getCuit());
        proveedorForm.setTipoDocumento(proveedor.getTipoDocumento());
        proveedorForm.setRazonSocial(proveedor.getRazonSocial());
        if(proveedor.getFechaAlta() != null) {
            proveedorForm.setFechaAlta(proveedor.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(proveedor.getTelefono() != null) {
            proveedorForm.setTelefono(proveedor.getTelefono());
        }
        if(proveedor.getMail() != null) {
            proveedorForm.setMail(proveedor.getMail());
        }
        if(proveedor.getHorario() != null) {
            proveedorForm.setHorario(proveedor.getHorario());
        }
        if(proveedor.getIdRubro() != null) {
            proveedorForm.setIdRubro(proveedor.getIdRubro().toString());
        }
        if(proveedor.getObservaciones() != null) {
            proveedorForm.setObservaciones(proveedor.getObservaciones());
        }        
        proveedorForm.setAction("remove");
        model.addAttribute("proveedorForm", proveedorForm);  
        model.addAttribute("titleProveedor", "Eliminar Proveedor");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("proveedorName", proveedor.getId() + " - " + proveedor.getRazonSocial());
        
        List<ProveedorModel> proveedores = proveedorService.getAll();
        
        List<ProveedorDto> proveedoresDtos = new ArrayList<ProveedorDto>();
        for(ProveedorModel p: proveedores) {
            ProveedorDto proveedorDto = new ProveedorDto();
            proveedorDto.setPk(p.getId().toString());
            proveedorDto.setCuit(p.getCuit());
            proveedorDto.setRazonSocial(p.getRazonSocial());
            if(p.getMail() != null) {
                proveedorDto.setMail(p.getMail());
            }
            if(p.getTelefono() != null) {
                proveedorDto.setTelefono(p.getTelefono());
            }
            
            proveedoresDtos.add(proveedorDto);
        }

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> rubros = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("rubroProveedor");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                rubros.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        model.addAttribute("rubroList", rubros);                
        model.addAttribute("proveedores", proveedoresDtos);
                
        return "/proveedor/proveedor";        
    }    
}
