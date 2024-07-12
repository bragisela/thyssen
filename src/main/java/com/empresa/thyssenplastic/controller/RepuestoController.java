/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

import com.empresa.thyssenplastic.controller.form.RepuestoForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.RepuestoDto;
import com.empresa.thyssenplastic.model.RepuestoModel;
import com.empresa.thyssenplastic.service.RepuestoService;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.RepuestoServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author gusta
 */
@Controller
public class RepuestoController {
//header referencia a uri

    @RequestMapping(value = "/repuesto", method = RequestMethod.GET)
    public String getHomeRepuesto(HttpServletRequest req, ModelMap model) {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        RepuestoService repuestoService = new RepuestoServiceImpl();

        RepuestoForm repuestoForm = new RepuestoForm();
        repuestoForm.setPk("-1");
        repuestoForm.setAction("add");
        model.addAttribute("repuestoForm", repuestoForm);
        model.addAttribute("titleRepuesto", "Repuestos");
        model.addAttribute("buttonLabel", "Guardar");

        //va a mostrar todos los respuestos en la grilla del jsp
        List<RepuestoModel> repuestos = repuestoService.getAll();
        List<RepuestoDto> repuestosDtos = new ArrayList<RepuestoDto>();
        for (RepuestoModel repuesto : repuestos) {
            RepuestoDto repuestoDto = new RepuestoDto();
            repuestoDto.setPk(repuesto.getId().toString());
            repuestoDto.setDescripcion(repuesto.getDescripcion());

            if (repuesto.getStock() != null) {
                repuestoDto.setStock(repuesto.getStock().toString());
            }
            if (repuesto.getStockMinimo() != null) {
                repuestoDto.setStockMinimo(repuesto.getStockMinimo().toString());
            }
            if (repuesto.getCodigo() != null) {
                repuestoDto.setCodigo(repuesto.getCodigo());
            }
            
            repuestosDtos.add(repuestoDto);
        }
        model.addAttribute("repuestos", repuestosDtos);

        return "/repuesto/repuesto";
    }

    @RequestMapping(value = "/repuesto/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveRepuesto(@ModelAttribute("repuestoForm") RepuestoForm repuestoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

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

        if (repuestoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;
        }

        RepuestoService repuestoService = new RepuestoServiceImpl();

        String descripcion = repuestoForm.getDescripcion();
        String stock = repuestoForm.getStock();
        String sessionId = req.getSession().getId();
        String id = repuestoForm.getPk();

        RepuestoModel repuestoModel = null;
        if (id.equalsIgnoreCase("-1")) {
            repuestoModel = new RepuestoModel();
            repuestoModel.setBaja(Boolean.FALSE);
        } else {
            repuestoModel = repuestoService.getByPk(Integer.valueOf(id));
            if (repuestoModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de repuesto inválido.");
                return modelAndView;
            }
        }
        repuestoModel.setDescripcion(descripcion);
        if(stock != null) {
            repuestoModel.setStock(Integer.valueOf(stock));
        }
        if(repuestoForm.getStockMinimo() != null) {
            repuestoModel.setStockMinimo(Integer.valueOf(repuestoForm.getStockMinimo()));
        }
        if (repuestoForm.getCodigo() != null) {
            repuestoModel.setCodigo(repuestoForm.getCodigo());
        }

    
        if (repuestoForm.getAction().equalsIgnoreCase("add") || repuestoForm.getAction().equalsIgnoreCase("edit")) {
            repuestoService.save(repuestoModel);
        } else {
            if (repuestoForm.getAction().equalsIgnoreCase("remove")) {
                if (repuestoModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de repuesto inválido.");
                    return modelAndView;
                }
                repuestoModel.setBaja(Boolean.TRUE);
                repuestoService.save(repuestoModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/repuesto");

        return modelAndView;
    }
//edit

    @RequestMapping(value = "/repuesto/edit/{repuestopk}", method = RequestMethod.GET)
    public String editInsumo(@PathVariable String repuestopk, HttpServletRequest req, ModelMap model) throws Exception {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        if (repuestopk == null) {
            model.addAttribute("errorMessage", "Error: Repuesto inválido");
            return "/error/error";
        }

        RepuestoService repuestoService = new RepuestoServiceImpl();
        RepuestoModel repuesto = repuestoService.getByPk(Integer.valueOf(repuestopk));
        if (repuesto == null) {
            model.addAttribute("errorMessage", "Error: Repuesto inválido. No ha sido encontrado.");
            return "/error/error";
        }

        RepuestoForm repuestoForm = new RepuestoForm();
        repuestoForm.setPk(repuesto.getId().toString());
        repuestoForm.setDescripcion(repuesto.getDescripcion());
        if (repuesto.getStock() != null) {
            repuestoForm.setStock(repuesto.getStock().toString());
        }
        if (repuesto.getStockMinimo() != null) {
            repuestoForm.setStockMinimo(repuesto.getStockMinimo().toString());
        }
        if (repuesto.getCodigo() != null) {
            repuestoForm.setCodigo(repuesto.getCodigo());
        }

        repuestoForm.setAction("edit");
        model.addAttribute("repuestoForm", repuestoForm);
        model.addAttribute("titleRepuesto", "Editar Configuración");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("repuestoName", repuesto.getId() + " - " + repuesto.getDescripcion());

        List<RepuestoModel> repuestos = repuestoService.getAll();

        List<RepuestoDto> repuestosDtos = new ArrayList<RepuestoDto>();
        for (RepuestoModel p : repuestos) {
            RepuestoDto repuestoDto = new RepuestoDto();
            repuestoDto.setPk(p.getId().toString());
            repuestoDto.setDescripcion(p.getDescripcion());
            if (repuesto.getStock() != null) {
                repuestoForm.setStock(repuesto.getStock().toString());
            }
            if (repuesto.getStockMinimo() != null) {
                repuestoForm.setStockMinimo(repuesto.getStockMinimo().toString());
            }
            if (repuesto.getCodigo() != null) {
                repuestoForm.setCodigo(repuesto.getCodigo());
            }            
        }

        model.addAttribute("repuestos", repuestosDtos);

        return "/repuesto/repuesto";
    }

    @RequestMapping(value = "/repuesto/remove/{repuestopk}", method = RequestMethod.GET)
    public String removeRepuesto(@PathVariable String repuestopk, HttpServletRequest req, ModelMap model) throws Exception {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        if (repuestopk == null) {
            model.addAttribute("errorMessage", "Error: Repuesto inválido");
            return "/error/error";
        }

        RepuestoService repuestoService = new RepuestoServiceImpl();
        RepuestoModel repuesto = repuestoService.getByPk(Integer.valueOf(repuestopk));
        if (repuesto == null) {
            model.addAttribute("errorMessage", "Error: Repuesto inválido. No ha sido encontrado.");
            return "/error/error";
        }

        RepuestoForm repuestoForm = new RepuestoForm();
        repuestoForm.setPk(repuesto.getId().toString());
        repuestoForm.setDescripcion(repuesto.getDescripcion());
        repuestoForm.setStock(repuesto.getStock().toString());

        if (repuesto.getId() != null) {
            repuestoForm.setPk(repuesto.getId().toString());
        }
        if (repuesto.getStock() != null) {
            repuestoForm.setStock(repuesto.getStock().toString());
        }
        if (repuesto.getStockMinimo() != null) {
            repuestoForm.setStockMinimo(repuesto.getStockMinimo().toString());
        }
        if (repuesto.getCodigo() != null) {
            repuestoForm.setCodigo(repuesto.getCodigo());
        }

        repuestoForm.setAction("remove");
        model.addAttribute("repuestoForm", repuestoForm);
        model.addAttribute("titleRepuesto", "Eliminar Repuesto");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("repuestoName", repuesto.getId() + " - " + repuesto.getDescripcion());

        List<RepuestoModel> repuestos = repuestoService.getAll();

        List<RepuestoDto> repuestosDtos = new ArrayList<RepuestoDto>();
        for (RepuestoModel p : repuestos) {
            RepuestoDto repuestoDto = new RepuestoDto();
            repuestoDto.setPk(p.getId().toString());
            repuestoDto.setDescripcion(p.getDescripcion());

            RepuestoModel repuestosModel = repuestoService.getByPk(p.getId());
            if (repuestosModel != null) {
                repuestoDto.setPk(repuestosModel.getId().toString());
            }
            if (repuesto.getStock() != null) {
                repuestoForm.setStock(repuesto.getStock().toString());
            }
            if (repuesto.getStockMinimo() != null) {
                repuestoForm.setStockMinimo(repuesto.getStockMinimo().toString());
            }
            if (repuesto.getCodigo() != null) {
                repuestoForm.setCodigo(repuesto.getCodigo());
            }

            repuestosDtos.add(repuestoDto);
        }

        model.addAttribute("repuestos", repuestosDtos);

        return "/repuesto/repuesto";
    }
}
