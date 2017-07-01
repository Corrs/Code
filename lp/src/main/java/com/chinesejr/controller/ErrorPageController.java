package com.chinesejr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/errorPage")
public class ErrorPageController {
	@RequestMapping("/404")
    public ModelAndView notFound(ModelMap map, HttpServletRequest request) {
		ModelAndView result = new ModelAndView("404");
		map.put("baseUrl", request.getAttribute("baseUrl"));
        return result;
    }
	
	@RequestMapping("/401")
    public ModelAndView unauthorized(ModelMap map, HttpServletRequest request) {
		ModelAndView result = new ModelAndView("401");
		map.put("baseUrl", request.getAttribute("baseUrl"));
        return result;
    }

	@RequestMapping("/500")
    public ModelAndView internalServerError(ModelMap map, HttpServletRequest request) {
		ModelAndView result = new ModelAndView("500");
		map.put("baseUrl", request.getAttribute("baseUrl"));
        return result;
    }
}
