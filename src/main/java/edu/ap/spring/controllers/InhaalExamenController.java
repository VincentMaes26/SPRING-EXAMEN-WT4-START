package edu.ap.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ap.spring.redis.RedisService;

@Controller
public class InhaalExamenController {

	private RedisService service;
	
	@Autowired
	public void setRedisService(RedisService service) {
		this.service = service;
   }
	
	@RequestMapping("/")
	public String list(Model model) {
		model.addAttribute(arg0)
		
	}
}
