/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

import com.empresa.thyssenplastic.controller.form.ReporteForm;
import com.empresa.thyssenplastic.controller.form.PlanillaPlegadoForm;
import com.empresa.thyssenplastic.controller.form.PlanillaPalletForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import java.util.Hashtable;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletBultoModel;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBultoDto; 
import com.empresa.thyssenplastic.dto.OrdenDeProduccionPalletDto;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.dto.ReporteDto;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.MantenimientoCorrectivoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel; 
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.RepuestoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.ReporteService;
import com.empresa.thyssenplastic.service.RepuestoService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletBultoService;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletBultoServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.ReporteServiceImpl;
import com.empresa.thyssenplastic.service.impl.RepuestoServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author gusta
 */
@Controller
public class ReporteController {


    @RequestMapping(value = "/reporte", method = RequestMethod.GET)
    public String getHomeReporte(HttpServletRequest req, ModelMap model) {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        ReporteForm reporteForm = new ReporteForm();        
        reporteForm.setAction("search");
        model.addAttribute("reporteForm", reporteForm);
        model.addAttribute("titleReporte", "Reporte");
        model.addAttribute("buttonLabel", "Buscar");

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> maquinas = new LinkedHashMap<String,String>();
        maquinas.put("-1", "Todas");
        List<TipoModel> tiposModel = tipoService.getByType("maquinaMantenimiento");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                maquinas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        model.addAttribute("maquinaList", maquinas);                       

        return "/reporte/reporte";
    }

    @RequestMapping(value = "/reporte/search", method = RequestMethod.POST)
    public String searchReporte(@ModelAttribute("reporteForm") ReporteForm reporteForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return "/error";
        }

        if (!Utils.isAutenticated(req)) {
            modelAndView.setViewName("/login/login");
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        if (reporteForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return "/error";
        }

        ReporteService reporteService = new ReporteServiceImpl();

        reporteForm.setAction("search");
        model.addAttribute("reporteForm", reporteForm);
        model.addAttribute("titleReporte", "Reporte");
        model.addAttribute("buttonLabel", "Buscar");
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date fechaDesde = new Date();
        Date fechaHasta = null;
        Integer idMaquina = null;
        if(reporteForm.getFechaDesde() != null && !reporteForm.getFechaDesde().isEmpty()) {
            fechaDesde = formato.parse(reporteForm.getFechaDesde());
        }
        if(reporteForm.getFechaHasta() != null && !reporteForm.getFechaHasta().isEmpty()) {
            fechaHasta = formato.parse(reporteForm.getFechaHasta());
        }
        if(reporteForm.getIdMaquina() != null && !reporteForm.getIdMaquina().isEmpty()) {
            idMaquina = Integer.valueOf(reporteForm.getIdMaquina());
        }

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> maquinas = new LinkedHashMap<String,String>();
        maquinas.put("-1", "Todas");
        List<TipoModel> tiposModel = tipoService.getByType("maquinaMantenimiento");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                maquinas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        RepuestoService repuestoService = new RepuestoServiceImpl();   
        Map<String,String> repuestos = new LinkedHashMap<String,String>();
        List<RepuestoModel> repuestosModel = repuestoService.getAll();
        if(repuestosModel != null && !repuestosModel.isEmpty()){
            for(RepuestoModel repuestoModel :repuestosModel) {
                repuestos.put(repuestoModel.getId().toString(), repuestoModel.getDescripcion());
            }
        }
        
        List<MantenimientoCorrectivoModel> reportes = reporteService.getAllByFilter(fechaDesde, fechaHasta, idMaquina);
        List<ReporteDto> reportesDtos = new ArrayList<ReporteDto>();
        for (MantenimientoCorrectivoModel reporte : reportes) {
            ReporteDto reporteDto = new ReporteDto();
            reporteDto.setPk(reporte.getId().toString());
            reporteDto.setFechaAlta(reporte.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(reporte.getHoraArranque() != null) {
                reporteDto.setHoraAranque(reporte.getHoraArranque());
            }
            if(reporte.getHoraParada() != null) {
                reporteDto.setHoraParada(reporte.getHoraParada());
            }
            if(reporte.getProblema() != null) {
                reporteDto.setProblema(reporte.getProblema());
            }
            /*
            if(reporte.getTiempoReparacion() != null) {
                reporteDto.setTiempoReparacion(reporte.getTiempoReparacion());
            }
            */
            if(reporte.getIdMaquina() != null) {
                reporteDto.setMaquina(maquinas.get(reporte.getIdMaquina().toString()));
            }
            if(reporte.getIdRepuesto() != null) {
                reporteDto.setRepuesto(repuestos.get(reporte.getIdRepuesto().toString()));
            }
            if(reporte.getEstado() != null) {
                reporteDto.setEstado(reporte.getEstado());
            }
                        
            reportesDtos.add(reporteDto);
        }

        model.addAttribute("reportes", reportesDtos);
        model.addAttribute("maquinaList", maquinas);                       
        modelAndView.setViewName("redirect:/reporte");

        return "/reporte/reporte";
    }
    
    @RequestMapping(value = "/reporte/planillaPlegado", method = RequestMethod.GET)
    public String getHomeReportePlanillaPlegado(HttpServletRequest req, ModelMap model) {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        PlanillaPlegadoForm planillaPlegadoForm = new PlanillaPlegadoForm();        
        planillaPlegadoForm.setAction("search");
        model.addAttribute("planillaPlegadoForm", planillaPlegadoForm);
        model.addAttribute("titleReporte", "Planilla de plegado");
        model.addAttribute("buttonLabel", "Buscar");
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        List<OrdenDeProduccionModel> ordenesDeProduccionModel = ordenDeProduccionService.getAll();
        
        List<String> ordeness = new ArrayList<String>();
        
        if(ordenesDeProduccionModel != null && !ordenesDeProduccionModel.isEmpty()){
            for(OrdenDeProduccionModel ordenDeProduccionModel :ordenesDeProduccionModel) {
                ordeness.add(ordenDeProduccionModel.getId().toString());
            }
        }
        
        Collections.reverse(ordeness);
       
        model.addAttribute("provinciaList", ordeness);

        return "/reporte/planillaplegado";
    }
    
    @RequestMapping(value = "/reporte/searchplegado", method = RequestMethod.POST)
    public String searchReportePlanilla(@ModelAttribute("PlanillaPlegadoForm") PlanillaPlegadoForm planillaPlegadoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return "/error";
        }

        if (!Utils.isAutenticated(req)) {
            modelAndView.setViewName("/login/login");
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        if (planillaPlegadoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return "/error";
        }

        ReporteService reporteService = new ReporteServiceImpl();

        planillaPlegadoForm.setAction("search");
        model.addAttribute("planillaPlegadoForm", planillaPlegadoForm);
        model.addAttribute("titleReporte", "Planilla de plegado");
        model.addAttribute("buttonLabel", "Buscar");
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date fechaDesde = new Date();
        
        //Integer idOrdenDeProduccion = null;
        if(planillaPlegadoForm.getFechaDesde() != null && !planillaPlegadoForm.getFechaDesde().isEmpty()) {
            fechaDesde = formato.parse(planillaPlegadoForm.getFechaDesde());
        }
        
        //if(planillaPlegadoForm.getIdOrdenDeProduccion() != null && !planillaPlegadoForm.getIdOrdenDeProduccion().isEmpty()) {
            //idOrdenDeProduccion = Integer.valueOf(planillaPlegadoForm.getIdOrdenDeProduccion());
        //}
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        
        //List<OrdenDeProduccionBultoModel> ordenDeProduccionBultosModel = ordenDeProduccionBultoService.getByOrdenDeProduccionAndFechaAlta(idOrdenDeProduccion, fechaDesde);
        
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultosModel2 = ordenDeProduccionBultoService.getByFechaAlta(fechaDesde);
       
       
        List<OrdenDeProduccionBultoDto> ordenDeProduccionBultosDtos = new ArrayList<OrdenDeProduccionBultoDto>();
        
         if(ordenDeProduccionBultosModel2 != null && !ordenDeProduccionBultosModel2.isEmpty()) {
            for(OrdenDeProduccionBultoModel ordenDeProduccionBultoModel: ordenDeProduccionBultosModel2) {
                OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                ordenDeProduccionBultoDto.setPk(ordenDeProduccionBultoModel.getId().toString());
                ordenDeProduccionBultoDto.setIdOrdenDeProduccion(ordenDeProduccionBultoModel.getIdOrdenDeProduccion().toString());
                ordenDeProduccionBultoDto.setFechaAlta(ordenDeProduccionBultoModel.getFechaAlta().toString().replace(".0", ""));
                if(ordenDeProduccionBultoModel.getEstado() != null && !ordenDeProduccionBultoModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionBultoDto.setEstado(ordenDeProduccionBultoModel.getEstado());
                    if(ordenDeProduccionBultoModel.getEstado().equals("ok")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("observado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                if(ordenDeProduccionBultoModel.getObservaciones() != null){
                  ordenDeProduccionBultoDto.setObservaciones(ordenDeProduccionBultoModel.getObservaciones());
                }
                
                ordenDeProduccionBultoDto.setCodigo(ordenDeProduccionBultoModel.getCodigo());
                ordenDeProduccionBultoDto.setEstaEnPallet(ordenDeProduccionBultoModel.getEstaEnPallet().toString());
                            
                
                String plegadora = "";
                TipoService tipoService = new TipoServiceImpl();
                if(ordenDeProduccionBultoModel.getIdPlegadora() != null) {
                    TipoModel plegadoraModel = tipoService.getByPk(ordenDeProduccionBultoModel.getIdPlegadora());
                    if(plegadora != null) {
                        plegadora = plegadoraModel.getValor();
                    }
                }        
                ordenDeProduccionBultoDto.setPlegadora(plegadora);
                
                
                ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);
                
                
            }
        }
         
        //OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl(); 
        //OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(idOrdenDeProduccion);
                
        //ArticuloService articuloService = new ArticuloServiceImpl();
        //ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        //if(articulo == null) {
          //model.addAttribute("denominacion", "-");                           
        //}else{
          //model.addAttribute("denominacion", articulo.getDenominacion());
        //}
        
        
        Map<String, List<OrdenDeProduccionBultoDto>> mapaAgrupado = new Hashtable<String, List<OrdenDeProduccionBultoDto>>();
        for (OrdenDeProduccionBultoDto dto : ordenDeProduccionBultosDtos) {
        String plegadora = dto.getPlegadora();

        if (!mapaAgrupado.containsKey(plegadora)) {
            mapaAgrupado.put(plegadora, new ArrayList<OrdenDeProduccionBultoDto>());
        }

         mapaAgrupado.get(plegadora).add(dto);
        }


        Map<String, Map<String, List<OrdenDeProduccionBultoDto>>> mapaAgrupadoDoble = new Hashtable<String, Map<String, List<OrdenDeProduccionBultoDto>>>();
        Map<String, String> denominacionPorOrden = new Hashtable<String, String>();

        for (OrdenDeProduccionBultoDto dto : ordenDeProduccionBultosDtos) {
            String idOrdenDeProduccionn = dto.getIdOrdenDeProduccion();
            String plegadora = dto.getPlegadora();

            // Si no hay un mapa externo para la orden de producción, créalo
            if (!mapaAgrupadoDoble.containsKey(idOrdenDeProduccionn)) {
                mapaAgrupadoDoble.put(idOrdenDeProduccionn, new Hashtable<String, List<OrdenDeProduccionBultoDto>>());
            }

            Map<String, List<OrdenDeProduccionBultoDto>> mapaInterno = mapaAgrupadoDoble.get(idOrdenDeProduccionn);

            // Si no hay un mapa interno para la plegadora, créalo
            if (!mapaInterno.containsKey(plegadora)) {
                mapaInterno.put(plegadora, new ArrayList<OrdenDeProduccionBultoDto>());
                //mapaInterno.put(plegadora, new ArrayList<>()); // Cambio aquí
            }
            
            // Verifica si ya existe la asociación para evitar reemplazar denominaciones
            if (!denominacionPorOrden.containsKey(idOrdenDeProduccionn)) {
                OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
                OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.parseInt(idOrdenDeProduccionn));

                ArticuloService articuloService = new ArticuloServiceImpl();
                ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());

                String denominacion = (articulo != null) ? articulo.getDenominacion() : "-";
                denominacionPorOrden.put(idOrdenDeProduccionn, denominacion);
            }

            mapaInterno.get(plegadora).add(dto);
        }
        
        
        System.out.println("*** agrupado:"+ mapaAgrupadoDoble);
         
        
        model.addAttribute("ordenDeProduccionBultosAgrupados", mapaAgrupado);
        model.addAttribute("ordenDeProduccionBultosAgrupadosDoble", mapaAgrupadoDoble);
        //model.addAttribute("maquinaList", ordenDeProduccionBultosModel);
        model.addAttribute("ordenDeProduccionBultos", ordenDeProduccionBultosDtos);
        model.addAttribute("ordenDeProduccionBultosAgrupados", mapaAgrupado);
        model.addAttribute("denominacionPorOrden", denominacionPorOrden);
        model.addAttribute("orden", planillaPlegadoForm.getIdOrdenDeProduccion());
        model.addAttribute("fecha", planillaPlegadoForm.getFechaDesde());
        modelAndView.setViewName("redirect:/reporte");
        

        return "/reporte/planillaplegado";
    }
    
    @RequestMapping(value = "/reporte/planillaPallet", method = RequestMethod.GET)
    public String getHomeReportePlanillaPallet(HttpServletRequest req, ModelMap model) {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        PlanillaPalletForm planillaPalletForm = new PlanillaPalletForm();        
        planillaPalletForm.setAction("search");
        model.addAttribute("planillaPalletForm", planillaPalletForm);
        model.addAttribute("titleReporte", "Planilla de Pallet");
        model.addAttribute("buttonLabel", "Buscar");
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        List<OrdenDeProduccionModel> ordenesDeProduccionModel = ordenDeProduccionService.getAll();
        
        List<String> ordeness = new ArrayList<String>();
        
        if(ordenesDeProduccionModel != null && !ordenesDeProduccionModel.isEmpty()){
            for(OrdenDeProduccionModel ordenDeProduccionModel :ordenesDeProduccionModel) {
                ordeness.add(ordenDeProduccionModel.getId().toString());
            }
        }
        
        Collections.reverse(ordeness);
       
        model.addAttribute("provinciaList", ordeness);

        return "/reporte/planillapallet";
    }
    
    
    @RequestMapping(value = "/reporte/searchpallet", method = RequestMethod.POST)
    public String searchReportePlanillaPlegado(@ModelAttribute("PlanillaPalletForm") PlanillaPalletForm planillaPalletForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return "/error";
        }

        if (!Utils.isAutenticated(req)) {
            modelAndView.setViewName("/login/login");
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        if (planillaPalletForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return "/error";
        }

        planillaPalletForm.setAction("search");
        model.addAttribute("planillaPalletForm", planillaPalletForm);
        model.addAttribute("titleReporte", "Planilla de Pallet");
        model.addAttribute("buttonLabel", "Buscar");
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date fechaDesde = new Date();
        
        Date fechaHasta = new Date();
        
        Integer idOrdenDeProduccion = null;
        if(planillaPalletForm.getFechaDesde() != null && !planillaPalletForm.getFechaDesde().isEmpty()) {
            fechaDesde = formato.parse(planillaPalletForm.getFechaDesde());
        }
        
        if(planillaPalletForm.getFechaHasta() != null && !planillaPalletForm.getFechaHasta().isEmpty()) {
            fechaHasta = formato.parse(planillaPalletForm.getFechaHasta());
        }
        
        if(planillaPalletForm.getIdOrdenDeProduccion() != null && !planillaPalletForm.getIdOrdenDeProduccion().isEmpty()) {
            idOrdenDeProduccion = Integer.valueOf(planillaPalletForm.getIdOrdenDeProduccion());
        }
        System.out.println("*** fecha:"+ fechaHasta);
        
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        
        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletsModel = ordenDeProduccionPalletService.getAllByOrdenDeProduccionAndFecha(idOrdenDeProduccion, fechaDesde, fechaHasta);       
        List<OrdenDeProduccionPalletDto> ordenDeProduccionPalletsDtos = new ArrayList<OrdenDeProduccionPalletDto>();
        
        if(ordenDeProduccionPalletsModel != null && !ordenDeProduccionPalletsModel.isEmpty()) {
            for(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel: ordenDeProduccionPalletsModel) {
                OrdenDeProduccionPalletDto ordenDeProduccionPalletDto = new OrdenDeProduccionPalletDto();
                ordenDeProduccionPalletDto.setPk(ordenDeProduccionPalletModel.getId().toString());
                ordenDeProduccionPalletDto.setFechaAlta(ordenDeProduccionPalletModel.getFechaAlta().toString().replace(".0", ""));
                System.out.println("*** ordenDeProduccionPalletsDtos:"+ ordenDeProduccionPalletModel.getCodigo());
                if(ordenDeProduccionPalletModel.getEstado() != null && !ordenDeProduccionPalletModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionPalletDto.setEstado(ordenDeProduccionPalletModel.getEstado());
                    if(ordenDeProduccionPalletModel.getEstado().equals("ok")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("observado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionPalletDto.setCodigo(ordenDeProduccionPalletModel.getCodigo());
                
                List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
                System.out.println("*** antes:"+ palletbultoList);
               
                ordenDeProduccionPalletDto.setCantidadBultos(String.valueOf(palletbultoList.size()));
                
               String listaCodigos = "";
               for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                if(bulto != null) {
                 listaCodigos += bulto.getCodigo() + " ";
                }
               }
              
               List<String> listaCodigos2 = new ArrayList<String>();
               
               for (int i = 1; i <= 10; i++) {
                if (i <= palletbultoList.size()) {
                    OrdenDeProduccionPalletBultoModel item = palletbultoList.get(i - 1);
                    OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());

                    if (bulto != null) {
                        listaCodigos2.add(bulto.getCodigo());
                    } else {
                        listaCodigos2.add("-");
                    }
                } else {
                    listaCodigos2.add("-"); // Agrega "-" si no hay suficientes elementos en palletbultoList
                }
            }
            

               ordenDeProduccionPalletDto.setListaCodigoBultos(listaCodigos);
               ordenDeProduccionPalletDto.setListaBultos(listaCodigos2);


                Double pesoTotal = 0.0;
                if(!palletbultoList.isEmpty()) {
                    for(OrdenDeProduccionPalletBultoModel palletbulto: palletbultoList) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletbulto.getIdOrdenDeProduccionBulto());
                        if(bulto != null) {
                            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                            if(bobina != null) {
                                pesoTotal += bobina.getPesoTotal();
                            }
                        }
                   }
                }
                ordenDeProduccionPalletDto.setPesoTotal(pesoTotal.toString());
                
                OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl(); 
                OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(idOrdenDeProduccion);
                
                ArticuloService articuloService = new ArticuloServiceImpl();
                ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                if(articulo == null) {
                    model.addAttribute("denominacion", "-");                           
                }else{
                    model.addAttribute("denominacion", articulo.getDenominacion());
                }
                  
                ordenDeProduccionPalletsDtos.add(ordenDeProduccionPalletDto);
                model.addAttribute("ordenDeProduccionPallets", ordenDeProduccionPalletsDtos);
     
                model.addAttribute("orden", planillaPalletForm.getIdOrdenDeProduccion());
                model.addAttribute("fecha", planillaPalletForm.getFechaDesde());
                model.addAttribute("fechah", planillaPalletForm.getFechaHasta());
              
            }
        }
        
        return "/reporte/planillapallet";
    }
        

}
