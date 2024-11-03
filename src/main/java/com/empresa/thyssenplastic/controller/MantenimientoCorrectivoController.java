/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.MantenimientoCorrectivoForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.MantenimientoCorrectivoDto;
import com.empresa.thyssenplastic.model.MantenimientoCorrectivoModel;
import com.empresa.thyssenplastic.model.RepuestoModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.MantenimientoCorrectivoService;
import com.empresa.thyssenplastic.service.RepuestoService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.MantenimientoCorrectivoServiceImpl;
import com.empresa.thyssenplastic.service.impl.RepuestoServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class MantenimientoCorrectivoController {
    
    
    @RequestMapping(value = "/mantenimientoCorrectivo", method = RequestMethod.GET)
    public String getHomeMantenimientoCorrectivo(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        String operacion = "";        
        String displayActionButton = "block";
        
        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);
        
        MantenimientoCorrectivoForm mantenimientoCorrectivoForm = new MantenimientoCorrectivoForm();
        mantenimientoCorrectivoForm.setPk("-1");
        mantenimientoCorrectivoForm.setAction("add");

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        mantenimientoCorrectivoForm.setFechaAlta(fecha);

        if(user.getRol() == Utils.ROL_PLANTA) {
            mantenimientoCorrectivoForm.setEstadoLabel("Abierto");            
            operacion = "alta";
            displayActionButton = "block";            
        }
        if(user.getRol() == Utils.ROL_MANTENIMIENTO) {
            mantenimientoCorrectivoForm.setEstadoLabel("Cerrado");
            operacion = "cierre";
            displayActionButton = "none";            
        }
        
        mantenimientoCorrectivoForm.setOperacion(operacion);
        
        model.addAttribute("mantenimientoCorrectivoForm", mantenimientoCorrectivoForm);  
        model.addAttribute("titleMantenimientoCorrectivo", "Nuevo Mantenimiento Correctivo");  
        model.addAttribute("buttonLabel", "Guardar");
        
        MantenimientoCorrectivoService mantenimientoCorrectivoService = new MantenimientoCorrectivoServiceImpl();   
        List<MantenimientoCorrectivoModel> mantenimientosCorrectivo = mantenimientoCorrectivoService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> maquinas = new LinkedHashMap<String,String>();
        List<TipoModel> maquinasModel = tipoService.getByType("maquinaMantenimiento");

        Map<String,String> tiposReparacion = new LinkedHashMap<String,String>();
        List<TipoModel> tiposReparacionModel = tipoService.getByType("tipoReparacionMantenimiento");

        RepuestoService repuestoService = new RepuestoServiceImpl();   
        Map<String,String> repuestos = new LinkedHashMap<String,String>();
        List<RepuestoModel> repuestosModel = repuestoService.getAll();
          
                        
        List<MantenimientoCorrectivoDto> mantenimientosCorrectivoDtos = new ArrayList<MantenimientoCorrectivoDto>();
        for(MantenimientoCorrectivoModel mantenimientoCorrectivo: mantenimientosCorrectivo) {
            MantenimientoCorrectivoDto mantenimientoCorrectivoDto = new MantenimientoCorrectivoDto();
            mantenimientoCorrectivoDto.setPk(mantenimientoCorrectivo.getId().toString());
            mantenimientoCorrectivoDto.setFechaAlta(mantenimientoCorrectivo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(mantenimientoCorrectivo.getFechaFin() != null) {
                mantenimientoCorrectivoDto.setFechaFin(mantenimientoCorrectivo.getFechaFin().toString().replace(" 00:00:00.0", ""));
            }
            if(mantenimientoCorrectivo.getIdMaquina() != null) {
                TipoModel maquina = tipoService.getByPk(mantenimientoCorrectivo.getIdMaquina());
                if(maquina != null) {
                    mantenimientoCorrectivoDto.setMaquina(maquina.getValor());
                }
            }
            if(mantenimientoCorrectivo.getPrioridad() != null) {
                mantenimientoCorrectivoDto.setPrioridad(mantenimientoCorrectivo.getPrioridad());
            }
            if(mantenimientoCorrectivo.getHoraParada() != null) {
                mantenimientoCorrectivoDto.setHoraParada(mantenimientoCorrectivo.getHoraParada());
            }
            if(mantenimientoCorrectivo.getHoraArranque() != null) {
                mantenimientoCorrectivoDto.setHoraArranque(mantenimientoCorrectivo.getHoraArranque());
            }
            if(mantenimientoCorrectivo.getEstado() != null) {
                mantenimientoCorrectivoDto.setEstado(mantenimientoCorrectivo.getEstado());
            }
            if(mantenimientoCorrectivo.getFechaDeReparacionDesde() != null) {
                mantenimientoCorrectivoDto.setFechaDeReparacionDesde(mantenimientoCorrectivo.getFechaDeReparacionDesde());
            }
            if(mantenimientoCorrectivo.getFechaDeReparacionHasta() != null) {
                mantenimientoCorrectivoDto.setFechaDeReparacionHasta(mantenimientoCorrectivo.getFechaDeReparacionHasta());
            }
            if(mantenimientoCorrectivo.getIntervaloReparacion() != null) {
                mantenimientoCorrectivoDto.setIntervaloReparacion(mantenimientoCorrectivo.getIntervaloReparacion().toString());
            }
            
            mantenimientosCorrectivoDtos.add(mantenimientoCorrectivoDto);
        }
                       
        if(maquinasModel != null && !maquinasModel.isEmpty()){
            for(TipoModel tipoModel :maquinasModel) {
                maquinas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        if(tiposReparacionModel != null && !tiposReparacionModel.isEmpty()){
            for(TipoModel tipoModel :tiposReparacionModel) {
                tiposReparacion.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        if(repuestosModel != null && !repuestosModel.isEmpty()){
            for(RepuestoModel repuestoModel :repuestosModel) {
                repuestos.put(repuestoModel.getId().toString(), repuestoModel.getDescripcion());
            }
        }
                                
                
        model.addAttribute("displayUser", "none");
        
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("maquinaList", maquinas);        
        model.addAttribute("tipoReparacionList", tiposReparacion);        
        model.addAttribute("repuestoList", repuestos);        
        model.addAttribute("mantenimientosCorrectivo", mantenimientosCorrectivoDtos);        
                
        return "/mantenimientocorrectivo/mantenimientocorrectivo";
    }
    
    @RequestMapping(value = "/mantenimientoCorrectivo/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveMantenimientoCorrectivo(@ModelAttribute("mantenimientoCorrectivoForm")MantenimientoCorrectivoForm mantenimientoCorrectivoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
                
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
        
        if(mantenimientoCorrectivoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        String operacion = mantenimientoCorrectivoForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta") && !operacion.equalsIgnoreCase("cierre") && !operacion.equalsIgnoreCase("edit") )) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }        
        if(mantenimientoCorrectivoForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }
        if(!mantenimientoCorrectivoForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("cierre") && !operacion.equalsIgnoreCase("edit") ) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }
        
        MantenimientoCorrectivoService mantenimientoCorrectivoService = new MantenimientoCorrectivoServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = mantenimientoCorrectivoForm.getPk();
            
        MantenimientoCorrectivoModel mantenimientoCorrectivoModel = null;
        if(id.equalsIgnoreCase("-1")) {
            mantenimientoCorrectivoModel = new MantenimientoCorrectivoModel();
        } else {
            mantenimientoCorrectivoModel = mantenimientoCorrectivoService.getByPk(Integer.valueOf(id));
            if(mantenimientoCorrectivoModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de mantenimientoCorrectivo inválido.");
                return modelAndView;                
            } 
        }        
        
        if (operacion.equalsIgnoreCase("edit")) {
            if(mantenimientoCorrectivoForm.getHoraArranque() != null && !mantenimientoCorrectivoForm.getHoraArranque().isEmpty()) {
                mantenimientoCorrectivoModel.setHoraArranque(mantenimientoCorrectivoForm.getHoraArranque());
            } else {
                mantenimientoCorrectivoModel.setHoraArranque(null);
            }
            if(mantenimientoCorrectivoForm.getIntervaloReparacion() != null && !(mantenimientoCorrectivoForm.getIntervaloReparacion() == null)) {
                mantenimientoCorrectivoModel.setIntervaloReparacion(mantenimientoCorrectivoForm.getIntervaloReparacion());
            } else {
                mantenimientoCorrectivoModel.setIntervaloReparacion(null);
            }
            
            //Aca getIntervaloReparacion
            
            
        }
        
        if(operacion.equalsIgnoreCase("alta") ) {
            if(mantenimientoCorrectivoForm.getFechaAlta() != null && !mantenimientoCorrectivoForm.getFechaAlta().trim().equals("")) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(mantenimientoCorrectivoForm.getFechaAlta());
                mantenimientoCorrectivoModel.setFechaAlta(fecha);
            } else {
                mantenimientoCorrectivoModel.setFechaAlta(null);
            }                
            if(mantenimientoCorrectivoForm.getIdMaquina() != null && !mantenimientoCorrectivoForm.getIdMaquina().isEmpty()) {
                mantenimientoCorrectivoModel.setIdMaquina(Integer.valueOf(mantenimientoCorrectivoForm.getIdMaquina()));
            } else {
                mantenimientoCorrectivoModel.setIdMaquina(null);
            }
            if(mantenimientoCorrectivoForm.getComponente() != null && !mantenimientoCorrectivoForm.getComponente().isEmpty()) {
                mantenimientoCorrectivoModel.setComponente(mantenimientoCorrectivoForm.getComponente());
            } else {
                mantenimientoCorrectivoModel.setComponente(null);
            }
            if(mantenimientoCorrectivoForm.getHoraParada() != null && !mantenimientoCorrectivoForm.getHoraParada().isEmpty()) {
                mantenimientoCorrectivoModel.setHoraParada(mantenimientoCorrectivoForm.getHoraParada());
            } else {
                mantenimientoCorrectivoModel.setHoraParada(null);
            }
            if(mantenimientoCorrectivoForm.getPrioridad() != null && !mantenimientoCorrectivoForm.getPrioridad().isEmpty()) {
                mantenimientoCorrectivoModel.setPrioridad(mantenimientoCorrectivoForm.getPrioridad());
            } else {
                mantenimientoCorrectivoModel.setPrioridad(null);
            }
            if(mantenimientoCorrectivoForm.getProblema() != null && !mantenimientoCorrectivoForm.getProblema().isEmpty()) {
                mantenimientoCorrectivoModel.setProblema(mantenimientoCorrectivoForm.getProblema());
            } else {
                mantenimientoCorrectivoModel.setProblema(null);
            }
            if(mantenimientoCorrectivoForm.getHoraArranque() != null && !mantenimientoCorrectivoForm.getHoraArranque().isEmpty()) {
                mantenimientoCorrectivoModel.setHoraArranque(mantenimientoCorrectivoForm.getHoraArranque());
            } else {
                mantenimientoCorrectivoModel.setHoraArranque(null);
            }
            if(mantenimientoCorrectivoForm.getIntervaloReparacion() != null && !(mantenimientoCorrectivoForm.getIntervaloReparacion() == null)) {
                mantenimientoCorrectivoModel.setIntervaloReparacion(mantenimientoCorrectivoForm.getIntervaloReparacion());
            } else {
                mantenimientoCorrectivoModel.setPrioridad(null);
            }
            
            
            mantenimientoCorrectivoModel.setIdUserAlta(Integer.valueOf(Utils.getUserLoggedId(req)));
            mantenimientoCorrectivoModel.setEstado("Abierto");
        }

        if(operacion.equalsIgnoreCase("cierre")) {
            if(mantenimientoCorrectivoForm.getFechaFin() != null && !mantenimientoCorrectivoForm.getFechaFin().trim().equals("")) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(mantenimientoCorrectivoForm.getFechaFin());
                mantenimientoCorrectivoModel.setFechaFin(fecha);
            } else {
                mantenimientoCorrectivoModel.setFechaFin(null);
            }                
            if(mantenimientoCorrectivoForm.getIdRepuesto() != null && !mantenimientoCorrectivoForm.getIdRepuesto().isEmpty()) {
                mantenimientoCorrectivoModel.setIdRepuesto(Integer.valueOf(mantenimientoCorrectivoForm.getIdRepuesto()));
            } else {
                mantenimientoCorrectivoModel.setIdRepuesto(null);
            }
            if(mantenimientoCorrectivoForm.getActividadRealizada() != null && !mantenimientoCorrectivoForm.getActividadRealizada().isEmpty()) {
                mantenimientoCorrectivoModel.setActividadRealizada(mantenimientoCorrectivoForm.getActividadRealizada());
            } else {
                mantenimientoCorrectivoModel.setActividadRealizada(null);
            }
            if(mantenimientoCorrectivoForm.getHoraArranque() != null && !mantenimientoCorrectivoForm.getHoraArranque().isEmpty()) {
                mantenimientoCorrectivoModel.setHoraArranque(mantenimientoCorrectivoForm.getHoraArranque());
            } else {
                mantenimientoCorrectivoModel.setHoraArranque(null);
            }
            if(mantenimientoCorrectivoForm.getIdTipoReparacion() != null && !mantenimientoCorrectivoForm.getIdTipoReparacion().isEmpty()) {
                mantenimientoCorrectivoModel.setIdTipoReparacion(Integer.valueOf(mantenimientoCorrectivoForm.getIdTipoReparacion()));
            } else {
                mantenimientoCorrectivoModel.setIdTipoReparacion(null);
            }
            if(mantenimientoCorrectivoForm.getObservacion() != null && !mantenimientoCorrectivoForm.getObservacion().isEmpty()) {
                mantenimientoCorrectivoModel.setObservacion(mantenimientoCorrectivoForm.getObservacion());
            } else {
                mantenimientoCorrectivoModel.setObservacion(null);
            }
            if(mantenimientoCorrectivoForm.getFechaDeReparacionDesde() != null && !mantenimientoCorrectivoForm.getFechaDeReparacionDesde().isEmpty()) {
                mantenimientoCorrectivoModel.setFechaDeReparacionDesde(mantenimientoCorrectivoForm.getFechaDeReparacionDesde());
            } else {
                mantenimientoCorrectivoModel.setFechaDeReparacionDesde(null);
            }
            if(mantenimientoCorrectivoForm.getFechaDeReparacionHasta() != null && !mantenimientoCorrectivoForm.getFechaDeReparacionHasta().isEmpty()) {
                mantenimientoCorrectivoModel.setFechaDeReparacionHasta(mantenimientoCorrectivoForm.getFechaDeReparacionHasta());
            } else {
                mantenimientoCorrectivoModel.setFechaDeReparacionHasta(null);
            }
            if(mantenimientoCorrectivoForm.getIntervaloReparacion() != null && !(mantenimientoCorrectivoForm.getIntervaloReparacion() == 0)) {
                mantenimientoCorrectivoModel.setIntervaloReparacion(Integer.valueOf(mantenimientoCorrectivoForm.getIntervaloReparacion()));
            } else {
                mantenimientoCorrectivoModel.setIntervaloReparacion(null);
            }
            
            mantenimientoCorrectivoModel.setIdUserFin(Integer.valueOf(Utils.getUserLoggedId(req)));
            mantenimientoCorrectivoModel.setEstado("Cerrado");
        }
        
        
        if(mantenimientoCorrectivoForm.getAction().equalsIgnoreCase("add") || mantenimientoCorrectivoForm.getAction().equalsIgnoreCase("edit")) {
            mantenimientoCorrectivoService.save(mantenimientoCorrectivoModel);
        } else {
            if(mantenimientoCorrectivoForm.getAction().equalsIgnoreCase("remove")) {
                if(mantenimientoCorrectivoModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de mantenimientoCorrectivo inválido.");
                    return modelAndView;                                    
                }
                
                mantenimientoCorrectivoService.delete(mantenimientoCorrectivoModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/mantenimientoCorrectivo");

        return modelAndView; 
    }

    @RequestMapping(value = "/mantenimientoCorrectivo/edit/{mantenimientoCorrectivopk}", method = RequestMethod.GET)
    public String editMantenimientoCorrectivo(@PathVariable String mantenimientoCorrectivopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(mantenimientoCorrectivopk == null) {
            model.addAttribute("errorMessage", "Error: MantenimientoCorrectivo inválido");         
            return "/error";                
        }
        
        String operacion = "";        
        String displayActionButton = "block";
        
        MantenimientoCorrectivoService mantenimientoCorrectivoService = new MantenimientoCorrectivoServiceImpl();   
        MantenimientoCorrectivoModel mantenimientoCorrectivo = mantenimientoCorrectivoService.getByPk(Integer.valueOf(mantenimientoCorrectivopk));
        if(mantenimientoCorrectivo == null) {
            model.addAttribute("errorMessage", "Error: MantenimientoCorrectivo inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!mantenimientoCorrectivo.getEstado().equalsIgnoreCase("abierto")) {
            model.addAttribute("errorMessage", "Error: estado incorrecto.");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_MANTENIMIENTO && user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA ) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        MantenimientoCorrectivoForm mantenimientoCorrectivoForm = new MantenimientoCorrectivoForm();
        mantenimientoCorrectivoForm.setPk(mantenimientoCorrectivo.getId().toString());
        if(mantenimientoCorrectivo.getFechaAlta() != null) {
            mantenimientoCorrectivoForm.setFechaAlta(mantenimientoCorrectivo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(mantenimientoCorrectivo.getIdMaquina() != null) {
            mantenimientoCorrectivoForm.setIdMaquina(mantenimientoCorrectivo.getIdMaquina().toString());
        }        
        if(mantenimientoCorrectivo.getComponente() != null && !mantenimientoCorrectivo.getComponente().isEmpty()) {
            mantenimientoCorrectivoForm.setComponente(mantenimientoCorrectivo.getComponente());
        }        
        if(mantenimientoCorrectivo.getHoraParada() != null && !mantenimientoCorrectivo.getHoraParada().isEmpty()) {
            mantenimientoCorrectivoForm.setHoraParada(mantenimientoCorrectivo.getHoraParada());
        }        
        if(mantenimientoCorrectivo.getPrioridad() != null && !mantenimientoCorrectivo.getPrioridad().isEmpty()) {
            mantenimientoCorrectivoForm.setPrioridad(mantenimientoCorrectivo.getPrioridad());
        }        
        if(mantenimientoCorrectivo.getProblema() != null && !mantenimientoCorrectivo.getProblema().isEmpty()) {
            mantenimientoCorrectivoForm.setProblema(mantenimientoCorrectivo.getProblema());
        }        
        if(mantenimientoCorrectivo.getFechaDeReparacionDesde() != null && !mantenimientoCorrectivo.getFechaDeReparacionDesde().isEmpty()) {
            mantenimientoCorrectivoForm.setFechaDeReparacionDesde(mantenimientoCorrectivo.getFechaDeReparacionDesde());
        }        
        if(mantenimientoCorrectivo.getFechaDeReparacionHasta() != null && !mantenimientoCorrectivo.getFechaDeReparacionHasta().isEmpty()) {
            mantenimientoCorrectivoForm.setFechaDeReparacionHasta(mantenimientoCorrectivo.getFechaDeReparacionHasta());
        }        
        if(mantenimientoCorrectivo.getIntervaloReparacion() != null) {
            mantenimientoCorrectivoForm.setIntervaloReparacion(mantenimientoCorrectivo.getIntervaloReparacion());
        }        
        if(mantenimientoCorrectivo.getHoraArranque() != null) {
            mantenimientoCorrectivoForm.setHoraArranque(mantenimientoCorrectivo.getHoraArranque());
        }        
        
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        mantenimientoCorrectivoForm.setFechaFin(fecha);

        if(user.getRol() == Utils.ROL_MANTENIMIENTO) {
            mantenimientoCorrectivoForm.setEstadoLabel("Cerrado");
            operacion = "cierre";
            displayActionButton = "block";
            
        }
        if(user.getRol() == Utils.ROL_OFICINA || user.getRol() == Utils.ROL_PLANTA) {
            operacion = "edit";
        }

        mantenimientoCorrectivoForm.setOperacion(operacion);
        
        mantenimientoCorrectivoForm.setAction("edit");
        model.addAttribute("mantenimientoCorrectivoForm", mantenimientoCorrectivoForm);  
        model.addAttribute("titleMantenimientoCorrectivo", "Editar Mantenimiento Correctivo");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("mantenimientoCorrectivoName", mantenimientoCorrectivo.getId() + " - " + mantenimientoCorrectivo.getIdMaquina());        
        
        List<MantenimientoCorrectivoModel> mantenimientosCorrectivo = mantenimientoCorrectivoService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> maquinas = new LinkedHashMap<String,String>();
        List<TipoModel> maquinasModel = tipoService.getByType("maquinaMantenimiento");

        Map<String,String> tiposReparacion = new LinkedHashMap<String,String>();
        List<TipoModel> tiposReparacionModel = tipoService.getByType("tipoReparacionMantenimiento");

        RepuestoService repuestoService = new RepuestoServiceImpl();   
        Map<String,String> repuestos = new LinkedHashMap<String,String>();
        List<RepuestoModel> repuestosModel = repuestoService.getAll();
         
                       
        List<MantenimientoCorrectivoDto> mantenimientosCorrectivoDtos = new ArrayList<MantenimientoCorrectivoDto>();
        for(MantenimientoCorrectivoModel mantenimientoCorrectivoModel: mantenimientosCorrectivo) {
            MantenimientoCorrectivoDto mantenimientoCorrectivoDto = new MantenimientoCorrectivoDto();
            mantenimientoCorrectivoDto.setPk(mantenimientoCorrectivoModel.getId().toString());
            mantenimientoCorrectivoDto.setFechaAlta(mantenimientoCorrectivoModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(mantenimientoCorrectivoModel.getFechaFin() != null) {
                mantenimientoCorrectivoDto.setFechaFin(mantenimientoCorrectivoModel.getFechaFin().toString().replace(" 00:00:00.0", ""));
            }
            if(mantenimientoCorrectivoModel.getIdMaquina() != null) {
                TipoModel maquina = tipoService.getByPk(mantenimientoCorrectivoModel.getIdMaquina());
                if(maquina != null) {
                    mantenimientoCorrectivoDto.setMaquina(maquina.getValor());
                }
            }
            if(mantenimientoCorrectivoModel.getPrioridad() != null) {
                mantenimientoCorrectivoDto.setPrioridad(mantenimientoCorrectivoModel.getPrioridad());
            }
            if(mantenimientoCorrectivoModel.getHoraParada() != null) {
                mantenimientoCorrectivoDto.setHoraParada(mantenimientoCorrectivoModel.getHoraParada());
            }
            if(mantenimientoCorrectivoModel.getHoraArranque() != null) {
                mantenimientoCorrectivoDto.setHoraArranque(mantenimientoCorrectivoModel.getHoraArranque());
            }
            if(mantenimientoCorrectivoModel.getEstado() != null) {
                mantenimientoCorrectivoDto.setEstado(mantenimientoCorrectivoModel.getEstado());
            }
            if(mantenimientoCorrectivoModel.getFechaDeReparacionDesde() != null) {
                mantenimientoCorrectivoDto.setFechaDeReparacionDesde(mantenimientoCorrectivoModel.getFechaDeReparacionDesde());
            }        
            if(mantenimientoCorrectivoModel.getFechaDeReparacionHasta() != null) {
                mantenimientoCorrectivoDto.setFechaDeReparacionHasta(mantenimientoCorrectivoModel.getFechaDeReparacionHasta());
            }        
            if(mantenimientoCorrectivoModel.getIntervaloReparacion() != null) {
                mantenimientoCorrectivoDto.setIntervaloReparacion(mantenimientoCorrectivoModel.getIntervaloReparacion().toString());
            }        
            
            mantenimientosCorrectivoDtos.add(mantenimientoCorrectivoDto);
        }
                       
        if(maquinasModel != null && !maquinasModel.isEmpty()){
            for(TipoModel tipoModel :maquinasModel) {
                maquinas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        if(tiposReparacionModel != null && !tiposReparacionModel.isEmpty()){
            for(TipoModel tipoModel :tiposReparacionModel) {
                tiposReparacion.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        if(repuestosModel != null && !repuestosModel.isEmpty()){
            for(RepuestoModel repuestoModel :repuestosModel) {
                repuestos.put(repuestoModel.getId().toString(), repuestoModel.getDescripcion());
            }
        }
        
        model.addAttribute("displayUser", "none");
        
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("maquinaList", maquinas);        
        model.addAttribute("tipoReparacionList", tiposReparacion);        
        model.addAttribute("repuestoList", repuestos);        
        model.addAttribute("mantenimientosCorrectivo", mantenimientosCorrectivoDtos);        
                                                        
        return "/mantenimientocorrectivo/mantenimientocorrectivo";        
    }
    
    @RequestMapping(value = "/mantenimientoCorrectivo/remove/{mantenimientoCorrectivopk}", method = RequestMethod.GET)
    public String removeMantenimientoCorrectivo(@PathVariable String mantenimientoCorrectivopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(mantenimientoCorrectivopk == null) {
            model.addAttribute("errorMessage", "Error: MantenimientoCorrectivo inválido");         
            return "/error";                
        }
        
        MantenimientoCorrectivoService mantenimientoCorrectivoService = new MantenimientoCorrectivoServiceImpl();   
        MantenimientoCorrectivoModel mantenimientoCorrectivo = mantenimientoCorrectivoService.getByPk(Integer.valueOf(mantenimientoCorrectivopk));
        if(mantenimientoCorrectivo == null) {
            model.addAttribute("errorMessage", "Error: MantenimientoCorrectivo inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        /*
        MantenimientoCorrectivoForm mantenimientoCorrectivoForm = new MantenimientoCorrectivoForm();
        mantenimientoCorrectivoForm.setPk(mantenimientoCorrectivo.getId().toString());
        if(mantenimientoCorrectivo.getFechaAlta() != null) {
            mantenimientoCorrectivoForm.setFechaAlta(mantenimientoCorrectivo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(mantenimientoCorrectivo.getTipo() != null && !mantenimientoCorrectivo.getTipo().isEmpty()) {
            mantenimientoCorrectivoForm.setTipo(mantenimientoCorrectivo.getTipo());
        }        
        if(mantenimientoCorrectivo.getIdArticulo() != null) {
            mantenimientoCorrectivoForm.setIdArticulo(mantenimientoCorrectivo.getIdArticulo().toString());
        } else {
            mantenimientoCorrectivoForm.setIdArticulo("-1");
        }
        if(mantenimientoCorrectivo.getIdMateriaPrima() != null) {
            mantenimientoCorrectivoForm.setIdMateriaPrima(mantenimientoCorrectivo.getIdMateriaPrima().toString());
        } else {
            mantenimientoCorrectivoForm.setIdMateriaPrima("-1");
        }
        if(mantenimientoCorrectivo.getIdInsumo() != null) {
            mantenimientoCorrectivoForm.setIdInsumo(mantenimientoCorrectivo.getIdInsumo().toString());
        } else {
            mantenimientoCorrectivoForm.setIdInsumo("-1");
        }                
        if(mantenimientoCorrectivo.getIdDeposito() != null) {
            mantenimientoCorrectivoForm.setIdDeposito(mantenimientoCorrectivo.getIdDeposito().toString());
        }
        if(mantenimientoCorrectivo.getExistentePeso() != null) {
            mantenimientoCorrectivoForm.setExistentePeso(mantenimientoCorrectivo.getExistentePeso().toString());
        }
        if(mantenimientoCorrectivo.getExistenteCantidad() != null) {
            mantenimientoCorrectivoForm.setExistenteCantidad(mantenimientoCorrectivo.getExistenteCantidad().toString());
        }
        if(mantenimientoCorrectivo.getAjustePeso() != null) {
            mantenimientoCorrectivoForm.setAjustePeso(mantenimientoCorrectivo.getAjustePeso().toString());
        }
        if(mantenimientoCorrectivo.getAjusteCantidad() != null) {
            mantenimientoCorrectivoForm.setAjusteCantidad(mantenimientoCorrectivo.getAjusteCantidad().toString());
        }
        if(mantenimientoCorrectivo.getResultadoPeso() != null) {
            mantenimientoCorrectivoForm.setResultadoPeso(mantenimientoCorrectivo.getResultadoPeso().toString());
        }
        if(mantenimientoCorrectivo.getResultadoCantidad() != null) {
            mantenimientoCorrectivoForm.setResultadoCantidad(mantenimientoCorrectivo.getResultadoCantidad().toString());
        }        
        if(mantenimientoCorrectivo.getMotivo() != null) {
            mantenimientoCorrectivoForm.setMotivo(mantenimientoCorrectivo.getMotivo());
        }
                
        mantenimientoCorrectivoForm.setAction("remove");
        model.addAttribute("mantenimientoCorrectivoForm", mantenimientoCorrectivoForm);  
        model.addAttribute("titleMantenimientoCorrectivo", "Eliminar MantenimientoCorrectivo");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("mantenimientoCorrectivoName", mantenimientoCorrectivo.getId() + " - " + mantenimientoCorrectivo.getTipo());
        
        List<MantenimientoCorrectivoModel> mantenimientosCorrectivo = mantenimientoCorrectivoService.getAll();

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiasPrimaModel = materiaPrimaService.getAll();

        InsumoService insumoService = new InsumoServiceImpl();   
        Map<String,String> insumos = new LinkedHashMap<String,String>();
        List<InsumoModel> insumosModel = insumoService.getAll();
        
        Map<String,String> depositos = new LinkedHashMap<String,String>();
                
        List<MantenimientoCorrectivoDto> mantenimientosCorrectivoDtos = new ArrayList<MantenimientoCorrectivoDto>();
        for(MantenimientoCorrectivoModel p: mantenimientosCorrectivo) {
            MantenimientoCorrectivoDto mantenimientoCorrectivoDto = new MantenimientoCorrectivoDto();
            mantenimientoCorrectivoDto.setPk(p.getId().toString());
            mantenimientoCorrectivoDto.setFechaAlta(p.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            mantenimientoCorrectivoDto.setTipo(p.getTipo());
            if(p.getTipo().equalsIgnoreCase("articulos") && p.getIdArticulo() != null) {
                String descripcion = "";
                ArticuloModel articuloModel = articuloService.getByPk(p.getIdArticulo());
                if(articuloModel != null){
                    descripcion = articuloModel.getDenominacion();
                }                
                mantenimientoCorrectivoDto.setDescripcion(descripcion);
            }                                    
            if(p.getTipo().equalsIgnoreCase("materiaPrima") && p.getIdMateriaPrima() != null) {
                String descripcion = "";
                MateriaPrimaModel materiaPrimaModel = materiaPrimaService.getByPk(p.getIdMateriaPrima());
                if(materiaPrimaModel != null){
                    descripcion = materiaPrimaModel.getDescripcion();
                }                
                mantenimientoCorrectivoDto.setDescripcion(descripcion);
            }                                    
            if(p.getTipo().equalsIgnoreCase("insumos") && p.getIdInsumo() != null) {
                String descripcion = "";
                InsumoModel insumoModel = insumoService.getByPk(p.getIdInsumo());
                if(insumoModel != null){
                    descripcion = insumoModel.getDescripcion();
                }                
                mantenimientoCorrectivoDto.setDescripcion(descripcion);
            }                                    
            mantenimientoCorrectivoDto.setAjusteCantidad(p.getAjusteCantidad().toString());
            mantenimientoCorrectivoDto.setAjustePeso(p.getAjusteCantidad().toString());
            mantenimientoCorrectivoDto.setExistenteCantidad(p.getExistenteCantidad().toString());
            mantenimientoCorrectivoDto.setExistentePeso(p.getExistenteCantidad().toString());
            mantenimientoCorrectivoDto.setResultadoCantidad(p.getResultadoCantidad().toString());
            mantenimientoCorrectivoDto.setResultadoPeso(p.getResultadoCantidad().toString());
            
            mantenimientosCorrectivoDtos.add(mantenimientoCorrectivoDto);
        }
                       
        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }
        if(materiasPrimaModel != null && !materiasPrimaModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiasPrimaModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }
        if(insumosModel != null && !insumosModel.isEmpty()){
            for(InsumoModel insumoModel :insumosModel) {
                insumos.put(insumoModel.getId().toString(), insumoModel.getDescripcion());
            }
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("displayActionButton", "block");
        model.addAttribute("articuloList", articulos);        
        model.addAttribute("insumoList", insumos);        
        model.addAttribute("materiaPrimaList", materiasPrima);                
        model.addAttribute("depositosList", depositos);        
        model.addAttribute("mantenimientosCorrectivo", mantenimientosCorrectivoDtos);    
        */
        
        return "/mantenimientocorrectivo/mantenimientocorrectivo";        
    }    
    
    @RequestMapping(value = "/mantenimientoCorrectivo/view/{mantenimientoCorrectivopk}", method = RequestMethod.GET)
    public String viewMantenimientoCorrectivo(@PathVariable String mantenimientoCorrectivopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(mantenimientoCorrectivopk == null) {
            model.addAttribute("errorMessage", "Error: MantenimientoCorrectivo inválido");         
            return "/error";                
        }
        
        MantenimientoCorrectivoService mantenimientoCorrectivoService = new MantenimientoCorrectivoServiceImpl();   
        MantenimientoCorrectivoModel mantenimientoCorrectivo = mantenimientoCorrectivoService.getByPk(Integer.valueOf(mantenimientoCorrectivopk));
        if(mantenimientoCorrectivo == null) {
            model.addAttribute("errorMessage", "Error: MantenimientoCorrectivo inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        MantenimientoCorrectivoForm mantenimientoCorrectivoForm = new MantenimientoCorrectivoForm();
        mantenimientoCorrectivoForm.setPk(mantenimientoCorrectivo.getId().toString());
        if(mantenimientoCorrectivo.getFechaAlta() != null) {
            mantenimientoCorrectivoForm.setFechaAlta(mantenimientoCorrectivo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(mantenimientoCorrectivo.getIdMaquina() != null) {
            mantenimientoCorrectivoForm.setIdMaquina(mantenimientoCorrectivo.getIdMaquina().toString());
        }        
        if(mantenimientoCorrectivo.getComponente() != null && !mantenimientoCorrectivo.getComponente().isEmpty()) {
            mantenimientoCorrectivoForm.setComponente(mantenimientoCorrectivo.getComponente());
        }        
        if(mantenimientoCorrectivo.getHoraParada() != null && !mantenimientoCorrectivo.getHoraParada().isEmpty()) {
            mantenimientoCorrectivoForm.setHoraParada(mantenimientoCorrectivo.getHoraParada());
        }        
        if(mantenimientoCorrectivo.getHoraArranque() != null && !mantenimientoCorrectivo.getHoraArranque().isEmpty()) {
            mantenimientoCorrectivoForm.setHoraArranque(mantenimientoCorrectivo.getHoraArranque());
        }        
        if(mantenimientoCorrectivo.getPrioridad() != null && !mantenimientoCorrectivo.getPrioridad().isEmpty()) {
            mantenimientoCorrectivoForm.setPrioridad(mantenimientoCorrectivo.getPrioridad());
        }        
        if(mantenimientoCorrectivo.getProblema() != null && !mantenimientoCorrectivo.getProblema().isEmpty()) {
            mantenimientoCorrectivoForm.setProblema(mantenimientoCorrectivo.getProblema());
        }        
        if(mantenimientoCorrectivo.getHoraArranque() != null && !mantenimientoCorrectivo.getHoraArranque().isEmpty()) {
            mantenimientoCorrectivoForm.setHoraArranque(mantenimientoCorrectivo.getHoraArranque());
        }        
        if(mantenimientoCorrectivo.getIdRepuesto() != null) {
            mantenimientoCorrectivoForm.setIdRepuesto(mantenimientoCorrectivo.getIdRepuesto().toString());
        }
        if(mantenimientoCorrectivo.getFechaFin() != null) {
            mantenimientoCorrectivoForm.setFechaFin(mantenimientoCorrectivo.getFechaFin().toString().replace(" 00:00:00.0", ""));
        }
        if(mantenimientoCorrectivo.getIdTipoReparacion() != null) {
            mantenimientoCorrectivoForm.setIdTipoReparacion(mantenimientoCorrectivo.getIdTipoReparacion().toString());
        }
        if(mantenimientoCorrectivo.getActividadRealizada() != null && !mantenimientoCorrectivo.getActividadRealizada().isEmpty()) {
            mantenimientoCorrectivoForm.setActividadRealizada(mantenimientoCorrectivo.getActividadRealizada());
        }                
        if(mantenimientoCorrectivo.getObservacion() != null && !mantenimientoCorrectivo.getObservacion().isEmpty()) {
            mantenimientoCorrectivoForm.setObservacion(mantenimientoCorrectivo.getObservacion());
        }        
        if(mantenimientoCorrectivo.getEstado() != null && !mantenimientoCorrectivo.getEstado().isEmpty()) {
            mantenimientoCorrectivoForm.setEstado(mantenimientoCorrectivo.getEstado());
        }        
        if(mantenimientoCorrectivo.getEstado() != null && !mantenimientoCorrectivo.getEstado().isEmpty()) {
            mantenimientoCorrectivoForm.setEstadoLabel(mantenimientoCorrectivo.getEstado());
        }        
        if(mantenimientoCorrectivo.getFechaDeReparacionDesde() != null && !mantenimientoCorrectivo.getFechaDeReparacionDesde().isEmpty()) {
            mantenimientoCorrectivoForm.setFechaDeReparacionDesde(mantenimientoCorrectivo.getFechaDeReparacionDesde());
        }        
        if(mantenimientoCorrectivo.getFechaDeReparacionHasta() != null && !mantenimientoCorrectivo.getFechaDeReparacionHasta().isEmpty()) {
            mantenimientoCorrectivoForm.setFechaDeReparacionHasta(mantenimientoCorrectivo.getFechaDeReparacionHasta());
        }        
        if(mantenimientoCorrectivo.getIntervaloReparacion() != null) {
            mantenimientoCorrectivoForm.setIntervaloReparacion(mantenimientoCorrectivo.getIntervaloReparacion());
        }        
        
        mantenimientoCorrectivoForm.setIdUserAlta(mantenimientoCorrectivo.getIdUserAlta().toString());
        UserService userService = new UserServiceImpl(); 
        UserModel userAlta = userService.getUserById(mantenimientoCorrectivo.getIdUserAlta());
        mantenimientoCorrectivoForm.setUserAlta(userAlta.getUsername());        

        UserModel userFin = userService.getUserById(mantenimientoCorrectivo.getIdUserFin());
        if(userFin != null) {
            mantenimientoCorrectivoForm.setUserFin(userFin.getUsername());
        }

        mantenimientoCorrectivoForm.setAction("view");
        model.addAttribute("mantenimientoCorrectivoForm", mantenimientoCorrectivoForm);  
        model.addAttribute("titleMantenimientoCorrectivo", "Ver Mantenimiento Correctivo " + mantenimientoCorrectivo.getId());
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("mantenimientoCorrectivoName", mantenimientoCorrectivo.getId() + " - " + mantenimientoCorrectivo.getIdMaquina());
        
        List<MantenimientoCorrectivoModel> mantenimientosCorrectivo = mantenimientoCorrectivoService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> maquinas = new LinkedHashMap<String,String>();
        List<TipoModel> maquinasModel = tipoService.getByType("maquinaMantenimiento");

        Map<String,String> tiposReparacion = new LinkedHashMap<String,String>();
        List<TipoModel> tiposReparacionModel = tipoService.getByType("tipoReparacionMantenimiento");

        RepuestoService repuestoService = new RepuestoServiceImpl();   
        Map<String,String> repuestos = new LinkedHashMap<String,String>();
        List<RepuestoModel> repuestosModel = repuestoService.getAll();
         
                       
        List<MantenimientoCorrectivoDto> mantenimientosCorrectivoDtos = new ArrayList<MantenimientoCorrectivoDto>();
        for(MantenimientoCorrectivoModel mantenimientoCorrectivoModel: mantenimientosCorrectivo) {
            MantenimientoCorrectivoDto mantenimientoCorrectivoDto = new MantenimientoCorrectivoDto();
            mantenimientoCorrectivoDto.setPk(mantenimientoCorrectivoModel.getId().toString());
            mantenimientoCorrectivoDto.setFechaAlta(mantenimientoCorrectivoModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(mantenimientoCorrectivoModel.getFechaFin() != null) {
                mantenimientoCorrectivoDto.setFechaFin(mantenimientoCorrectivoModel.getFechaFin().toString().replace(" 00:00:00.0", ""));
            }
            if(mantenimientoCorrectivoModel.getIdMaquina() != null) {
                TipoModel maquina = tipoService.getByPk(mantenimientoCorrectivoModel.getIdMaquina());
                if(maquina != null) {
                    mantenimientoCorrectivoDto.setMaquina(maquina.getValor());
                }
            }
            if(mantenimientoCorrectivoModel.getPrioridad() != null) {
                mantenimientoCorrectivoDto.setPrioridad(mantenimientoCorrectivoModel.getPrioridad());
            }
            if(mantenimientoCorrectivoModel.getHoraParada() != null) {
                mantenimientoCorrectivoDto.setHoraParada(mantenimientoCorrectivoModel.getHoraParada());
            }
            if(mantenimientoCorrectivoModel.getHoraArranque() != null) {
                mantenimientoCorrectivoDto.setHoraArranque(mantenimientoCorrectivoModel.getHoraArranque());
            }
            if(mantenimientoCorrectivoModel.getEstado() != null) {
                mantenimientoCorrectivoDto.setEstado(mantenimientoCorrectivoModel.getEstado());
            }
            if(mantenimientoCorrectivoModel.getFechaDeReparacionDesde() != null) {
                mantenimientoCorrectivoDto.setFechaDeReparacionDesde(mantenimientoCorrectivoModel.getFechaDeReparacionDesde());
            }        
            if(mantenimientoCorrectivoModel.getFechaDeReparacionHasta() != null) {
                mantenimientoCorrectivoDto.setFechaDeReparacionHasta(mantenimientoCorrectivoModel.getFechaDeReparacionHasta());
            }        
            if(mantenimientoCorrectivoModel.getIntervaloReparacion() != null) {
                mantenimientoCorrectivoDto.setIntervaloReparacion(mantenimientoCorrectivoModel.getIntervaloReparacion().toString());
            }        
            
            mantenimientosCorrectivoDtos.add(mantenimientoCorrectivoDto);
        }
                       
        if(maquinasModel != null && !maquinasModel.isEmpty()){
            for(TipoModel tipoModel :maquinasModel) {
                maquinas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        if(tiposReparacionModel != null && !tiposReparacionModel.isEmpty()){
            for(TipoModel tipoModel :tiposReparacionModel) {
                tiposReparacion.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        if(repuestosModel != null && !repuestosModel.isEmpty()){
            for(RepuestoModel repuestoModel :repuestosModel) {
                repuestos.put(repuestoModel.getId().toString(), repuestoModel.getDescripcion());
            }
        }
                                
        String operacion = "view";

        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", "none");
        model.addAttribute("operacion", operacion);        
        model.addAttribute("maquinaList", maquinas);        
        model.addAttribute("tipoReparacionList", tiposReparacion);        
        model.addAttribute("repuestoList", repuestos);        
        model.addAttribute("mantenimientosCorrectivo", mantenimientosCorrectivoDtos);        
        
                
        return "/mantenimientocorrectivo/mantenimientocorrectivo";        
    }    
    
}
