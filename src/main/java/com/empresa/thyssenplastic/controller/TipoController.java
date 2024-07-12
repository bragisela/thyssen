/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

import com.empresa.thyssenplastic.controller.form.TipoForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.TipoDto;
import com.empresa.thyssenplastic.model.TipoModel;
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
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author gusta
 */
@Controller
public class TipoController {


    @RequestMapping(value = "/tipo", method = RequestMethod.GET)
    public String getHomeTipo(HttpServletRequest req, ModelMap model) {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        TipoService tipoService = new TipoServiceImpl();

        TipoForm tipoForm = new TipoForm();
        tipoForm.setPk("-1");
        tipoForm.setAction("add");
        model.addAttribute("tipoForm", tipoForm);
        model.addAttribute("titleTipo", "Tipos");
        model.addAttribute("buttonLabel", "Guardar");

        List<TipoModel> tipos = tipoService.getAllByOrder();
        List<TipoDto> tiposDtos = new ArrayList<TipoDto>();
        for (TipoModel tipo : tipos) {
            TipoDto tipoDto = new TipoDto();
            tipoDto.setPk(tipo.getId().toString());
            tipoDto.setTipo(tipo.getTipo());
            tipoDto.setValor(tipo.getValor());

            tiposDtos.add(tipoDto);
        }

        model.addAttribute("tipos", tiposDtos);

        return "/configuracion/configuracion";
    }

    @RequestMapping(value = "/tipo/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveTipo(@ModelAttribute("tipoForm") TipoForm tipoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

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

        if (tipoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;
        }

        TipoService tipoService = new TipoServiceImpl();
        
        String sessionId = req.getSession().getId();
        String id = tipoForm.getPk();

        TipoModel tipoModel = null;
        if (id.equalsIgnoreCase("-1")) {
            tipoModel = new TipoModel();
        } else {
            tipoModel = tipoService.getByPk(Integer.valueOf(id));
            if (tipoModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de tipo inválido.");
                return modelAndView;
            }
        }
        tipoModel.setTipo(tipoForm.getTipo());
        tipoModel.setValor(tipoForm.getValor());
        if(tipoForm.getDensidad() != null && !tipoForm.getDensidad().isEmpty()) {
            tipoModel.setDensidad(Double.valueOf(tipoForm.getDensidad()));
        }
        if(tipoForm.getIndiceDeFluidez() != null && !tipoForm.getIndiceDeFluidez().isEmpty()) {
            tipoModel.setIndiceDeFluidez(Double.valueOf(tipoForm.getIndiceDeFluidez()));
        }
        
        if (tipoForm.getAction().equalsIgnoreCase("add") || tipoForm.getAction().equalsIgnoreCase("edit")) {
            tipoService.save(tipoModel);
        } else {
            if (tipoForm.getAction().equalsIgnoreCase("remove")) {
                if (tipoModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de tipo inválido.");
                    return modelAndView;
                }
                tipoService.delete(tipoModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/tipo");

        return modelAndView;
    }

    @RequestMapping(value = "/tipo/edit/{tipopk}", method = RequestMethod.GET)
    public String editInsumo(@PathVariable String tipopk, HttpServletRequest req, ModelMap model) throws Exception {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        if (tipopk == null) {
            model.addAttribute("errorMessage", "Error: Tipo inválido");
            return "/error/error";
        }

        TipoService tipoService = new TipoServiceImpl();
        TipoModel tipo = tipoService.getByPk(Integer.valueOf(tipopk));
        if (tipo == null) {
            model.addAttribute("errorMessage", "Error: Tipo inválido. No ha sido encontrado.");
            return "/error/error";
        }

        TipoForm tipoForm = new TipoForm();
        tipoForm.setPk(tipo.getId().toString());
        tipoForm.setTipo(tipo.getTipo());
        tipoForm.setValor(tipo.getValor());
        if(tipo.getDensidad() != null) {
            tipoForm.setDensidad(tipo.getDensidad().toString());
        }
        if(tipo.getIndiceDeFluidez() != null) {
            tipoForm.setIndiceDeFluidez(tipo.getIndiceDeFluidez().toString());
        }
        
        tipoForm.setAction("edit");
        model.addAttribute("tipoForm", tipoForm);
        model.addAttribute("titleTipo", "Editar Configuración");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("tipoName", tipo.getId() + " - " + tipo.getTipo() + " - " + tipo.getValor());

        List<TipoModel> tipos = tipoService.getAllByOrder();

        List<TipoDto> tiposDtos = new ArrayList<TipoDto>();
        for (TipoModel p : tipos) {
            TipoDto tipoDto = new TipoDto();
            tipoDto.setPk(p.getId().toString());
            tipoDto.setTipo(p.getTipo());
            tipoDto.setValor(p.getValor());
            
            tiposDtos.add(tipoDto);
        }

        model.addAttribute("tipos", tiposDtos);

        return "/configuracion/configuracion";
    }

    @RequestMapping(value = "/tipo/remove/{tipopk}", method = RequestMethod.GET)
    public String removeTipo(@PathVariable String tipopk, HttpServletRequest req, ModelMap model) throws Exception {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        if (tipopk == null) {
            model.addAttribute("errorMessage", "Error: Tipo inválido");
            return "/error/error";
        }

        TipoService tipoService = new TipoServiceImpl();
        TipoModel tipo = tipoService.getByPk(Integer.valueOf(tipopk));
        if (tipo == null) {
            model.addAttribute("errorMessage", "Error: Tipo inválido. No ha sido encontrado.");
            return "/error/error";
        }

        TipoForm tipoForm = new TipoForm();
        tipoForm.setPk(tipo.getId().toString());
        tipoForm.setTipo(tipo.getTipo());
        tipoForm.setValor(tipo.getValor());
        if(tipo.getDensidad() != null) {
            tipoForm.setDensidad(tipo.getDensidad().toString());
        }
        if(tipo.getIndiceDeFluidez() != null) {
            tipoForm.setIndiceDeFluidez(tipo.getIndiceDeFluidez().toString());
        }

        tipoForm.setAction("remove");
        model.addAttribute("tipoForm", tipoForm);
        model.addAttribute("titleTipo", "Eliminar Tipo");
        model.addAttribute("buttonLabel", "Eliminar");        
        model.addAttribute("tipoName", tipo.getId() + " - " + tipo.getTipo() + " - " + tipo.getValor());

        List<TipoModel> tipos = tipoService.getAllByOrder();

        List<TipoDto> tiposDtos = new ArrayList<TipoDto>();
        for (TipoModel p : tipos) {
            TipoDto tipoDto = new TipoDto();
            tipoDto.setPk(p.getId().toString());
            tipoDto.setTipo(p.getTipo());
            tipoDto.setValor(p.getValor());
            
            tiposDtos.add(tipoDto);
        }

        model.addAttribute("tipos", tiposDtos);

        return "/configuracion/configuracion";
    }
}
