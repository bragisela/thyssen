package com.empresa.thyssenplastic.controller;

import com.empresa.thyssenplastic.controller.form.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(ModelMap model) {

                model.addAttribute("userForm", new UserForm());         
		model.addAttribute("message", "Welcome");
                
		return "/login/login";

	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);
                model.addObject("name", "Test 1");

		return model;

	}

}