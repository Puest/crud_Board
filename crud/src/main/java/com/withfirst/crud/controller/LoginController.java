package com.withfirst.crud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member/*")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() throws Exception {
		logger.info("login GET...");
	} 
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(RedirectAttributes redirectAttributes) throws Exception {
		logger.info("login POST...");
		
		redirectAttributes.addFlashAttribute("login","loginOK");
		return "redirect:../board/allList";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info("login GET...");
	} 
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(RedirectAttributes redirectAttributes) throws Exception {
		logger.info("login POST...");
		
		redirectAttributes.addFlashAttribute("login","loginOK");
		return "redirect:/member/login";
	} 
}
