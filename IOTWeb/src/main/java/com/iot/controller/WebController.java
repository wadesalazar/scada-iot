package com.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("IOTWebController")
public class WebController {
	
	String message = "Welcome";
 
    /*@RequestMapping("/")
    public String printHelloWorld(Model model) {
        model.addAttribute("message","What!");
 
        return "helloWorld";
    }*/
    
    /*String message = "Welcome";
    @RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("testValue");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}*/
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "login/loginPage";
	}

	@RequestMapping(value = "home/homePage", method = RequestMethod.POST)
	public String homePage() {
		return "home/homePage";
	}
    
    @RequestMapping("/iot")
	public ModelAndView showIotStreamer(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("showIotStreamer in controller");
 
		ModelAndView mv = new ModelAndView("iotStreamer");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
    
   
}
