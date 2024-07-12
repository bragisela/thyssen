/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

import com.empresa.thyssenplastic.controller.form.ConfiguracionForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.ConfiguracionDto;
import com.empresa.thyssenplastic.model.ConfiguracionModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.service.ConfiguracionService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.ConfiguracionServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
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
public class ConfiguracionController {

    @RequestMapping(value = "/configuracion", method = RequestMethod.GET)
    public String getHomeConfiguracion(HttpServletRequest req, ModelMap model) {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        TipoService tipoService = new TipoServiceImpl();

        ConfiguracionForm configuracionForm = new ConfiguracionForm();
        configuracionForm.setPk("-1");
        configuracionForm.setAction("add");
        model.addAttribute("configuracionForm", configuracionForm);
        model.addAttribute("titleConfiguracion", "Alta Configuración");
        model.addAttribute("buttonLabel", "Guardar");

        ConfiguracionService configuracionService = new ConfiguracionServiceImpl();
        List<ConfiguracionModel> configuraciones = configuracionService.getAll();
        //los set de los campos a mostrar en la tabla
        List<ConfiguracionDto> configuracionesDtos = new ArrayList<ConfiguracionDto>();
        for (ConfiguracionModel configuracion : configuraciones) {
            ConfiguracionDto configuracionDto = new ConfiguracionDto();
            configuracionDto.setPk(configuracion.getId().toString());
            configuracionDto.setDescripcion(configuracion.getDescripcion());

            TipoModel repuestosModel = tipoService.getByPk(configuracion.getIdDato());
            if (repuestosModel != null) {
                configuracionDto.setIdDato(repuestosModel.getValor());
            }
            
            
            if (configuracion.getDensidad() != null) {
                configuracionDto.setDensidad(configuracion.getDensidad());
            }
            configuracionesDtos.add(configuracionDto);
        }

        Map<String, String> repuestos = new LinkedHashMap<String, String>();
        List<TipoModel> repuestosModel = tipoService.getByType("datoconfiguracion");
        if (repuestosModel != null && !repuestosModel.isEmpty()) {
            for (TipoModel tipoModel : repuestosModel) {
                repuestos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        model.addAttribute("tipoList", repuestos);
        model.addAttribute("configuraciones", configuracionesDtos);

        return "/configuracion/configuracion";
    }

    @RequestMapping(value = "/configuracion/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveConfiguracion(@ModelAttribute("configuracionForm") ConfiguracionForm configuracionForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;
        }

        if (!Utils.isAutenticated(req)) {
            modelAndView.setViewName("/login/login");
            model.addAttribute("userForm", new UserForm());
            return modelAndView;
        }

        if (configuracionForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;
        }

        ConfiguracionService configuracionService = new ConfiguracionServiceImpl();

        String descripcion = configuracionForm.getDescripcion();
        String densidad = configuracionForm.getDensidad();
        String sessionId = req.getSession().getId();
        String id = configuracionForm.getPk();

        ConfiguracionModel configuracionModel = null;
        if (id.equalsIgnoreCase("-1")) {
            configuracionModel = new ConfiguracionModel();
        } else {
            configuracionModel = configuracionService.getByPk(Integer.valueOf(id));
            if (configuracionModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de configuración inválido.");
                return modelAndView;
            }
        }
        configuracionModel.setDescripcion(descripcion);
        configuracionModel.setDensidad(densidad);

        if (configuracionForm.getIdDato() != null && !configuracionForm.getIdDato().equals("-1")) {
            configuracionModel.setIdDato(Integer.valueOf(configuracionForm.getIdDato()));
        } else {
            configuracionModel.setIdDato(null);
        }

        if (configuracionForm.getAction().equalsIgnoreCase("add") || configuracionForm.getAction().equalsIgnoreCase("edit")) {
            configuracionService.save(configuracionModel);
        } else {
            if (configuracionForm.getAction().equalsIgnoreCase("remove")) {
                if (configuracionModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de configuración inválido.");
                    return modelAndView;
                }
                configuracionService.delete(configuracionModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/configuracion");

        return modelAndView;
    }

    @RequestMapping(value = "/configuracion/edit/{configuracionpk}", method = RequestMethod.GET)
    public String editInsumo(@PathVariable String configuracionpk, HttpServletRequest req, ModelMap model) throws Exception {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        if (configuracionpk == null) {
            model.addAttribute("errorMessage", "Error: Configuración inválida");
            return "/error/error";
        }

        ConfiguracionService configuracionService = new ConfiguracionServiceImpl();
        ConfiguracionModel configuracion = configuracionService.getByPk(Integer.valueOf(configuracionpk));
        if (configuracion == null) {
            model.addAttribute("errorMessage", "Error: Configuración inválida. No ha sido encontrada.");
            return "/error/error";
        }

        ConfiguracionForm configuracionForm = new ConfiguracionForm();
        configuracionForm.setPk(configuracion.getId().toString());
        configuracionForm.setDescripcion(configuracion.getDescripcion());
        configuracionForm.setDensidad(configuracion.getDensidad());

        if (configuracion.getDensidad() != null) {
            configuracionForm.setDensidad(configuracion.getDensidad());
        }

        if (configuracion.getIdDato() != null) {
            configuracionForm.setIdDato(configuracion.getIdDato().toString());
        }

        configuracionForm.setAction("edit");
        model.addAttribute("configuracionForm", configuracionForm);
        model.addAttribute("titleConfiguracion", "Editar Configuración");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("configuracionName", configuracion.getId() + " - " + configuracion.getDescripcion());

        List<ConfiguracionModel> configuraciones = configuracionService.getAll();

        List<ConfiguracionDto> configuracionesDtos = new ArrayList<ConfiguracionDto>();
        for (ConfiguracionModel p : configuraciones) {
            ConfiguracionDto configuracionDto = new ConfiguracionDto();
            configuracionDto.setPk(p.getId().toString());
            configuracionDto.setDescripcion(p.getDescripcion());
            if (configuracion.getDensidad() != null) {
                configuracionForm.setDensidad(configuracion.getDensidad());
            }
        }

        TipoService tipoService = new TipoServiceImpl();
        Map<String, String> repuestos = new LinkedHashMap<String, String>();
        List<TipoModel> repuestosModel = tipoService.getAll();
        if (repuestosModel != null && !repuestosModel.isEmpty()) {
            for (TipoModel tipoModel : repuestosModel) {
                repuestos.put(tipoModel.getId().toString(), tipoModel.getTipo());
            }
        }
        model.addAttribute("tipoList", repuestos);
        model.addAttribute("configuraciones", configuracionesDtos);

        return "/configuracion/configuracion";
    }

    @RequestMapping(value = "/configuracion/remove/{configuracionpk}", method = RequestMethod.GET)
    public String removeConfiguracion(@PathVariable String configuracionpk, HttpServletRequest req, ModelMap model) throws Exception {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        if (configuracionpk == null) {
            model.addAttribute("errorMessage", "Error: Configuración inválida");
            return "/error/error";
        }

        ConfiguracionService configuracionService = new ConfiguracionServiceImpl();
        ConfiguracionModel configuracion = configuracionService.getByPk(Integer.valueOf(configuracionpk));
        if (configuracion == null) {
            model.addAttribute("errorMessage", "Error: Configuración inválida. No ha sido encontrada.");
            return "/error/error";
        }

        TipoService tipoService = new TipoServiceImpl();

        ConfiguracionForm configuracionForm = new ConfiguracionForm();
        configuracionForm.setPk(configuracion.getId().toString());
        configuracionForm.setDescripcion(configuracion.getDescripcion());
        configuracionForm.setDensidad(configuracion.getDensidad());

        if (configuracion.getIdDato() != null) {
            configuracionForm.setIdDato(configuracion.getIdDato().toString());
        }
        if (configuracion.getDensidad() != null) {
            configuracionForm.setDensidad(configuracion.getDensidad());
        }
        configuracionForm.setAction("remove");
        model.addAttribute("configuracionForm", configuracionForm);
        model.addAttribute("titleConfiguracion", "Eliminar Configuracion");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("configuracionName", configuracion.getId() + " - " + configuracion.getDescripcion());

        List<ConfiguracionModel> configuraciones = configuracionService.getAll();

        List<ConfiguracionDto> configuracionesDtos = new ArrayList<ConfiguracionDto>();
        for (ConfiguracionModel p : configuraciones) {
            ConfiguracionDto configuracionDto = new ConfiguracionDto();
            configuracionDto.setPk(p.getId().toString());
            configuracionDto.setDescripcion(p.getDescripcion());
            
             TipoModel repuestosModel = tipoService.getByPk(p.getIdDato());
            if (repuestosModel != null) {
                configuracionDto.setIdDato(repuestosModel.getValor());
            }
            

            if (configuracion.getDensidad() != null) {
                configuracionForm.setDensidad(configuracion.getDensidad());
            }

            configuracionesDtos.add(configuracionDto);
        }

        Map<String, String> repuestos = new LinkedHashMap<String, String>();
        List<TipoModel> repuestosModel = tipoService.getAll();
        if (repuestosModel != null && !repuestosModel.isEmpty()) {
            for (TipoModel tipoModel : repuestosModel) {
                repuestos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        model.addAttribute("tipoList", repuestos);
        model.addAttribute("configuraciones", configuracionesDtos);

        return "/configuracion/configuracion";
    }
}
