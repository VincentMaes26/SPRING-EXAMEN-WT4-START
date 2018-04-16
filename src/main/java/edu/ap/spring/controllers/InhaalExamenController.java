package edu.ap.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.spring.redis.RedisService;

@Controller
public class InhaalExamenController {

	private RedisService service;
	private List<String> redisMessages = new ArrayList<String>();
	
	@Autowired
	public void setRedisService(RedisService service) {
		this.service = service;
   }
	
	@RequestMapping("/messages")
	   @ResponseBody
	   public String messages() {
		   String html = "<HTML><HEAD><meta http-equiv=\"refresh\" content=\"5\"></HEAD>";
		   html += "<BODY><h1>Messages</h1><br/><br/><ul>";
		   for(String m : redisMessages) {
			   html += "<li>" + m;
		   }
		   html += "</BODY></HTML>";
		   
		   return html;
	   }
	
	@RequestMapping("/")
	public String list() {
		String html = "<HTML>";
		   // get the bitcount of our counter
		Set<String> examens = service.keys("inhaalexamens:lijst");
		for(String e : examens) {
			// make a key from the byte array
			String key = new String(e);
			// get hash with actors
			Map<Object, Object> examenMap = service.hgetAll(key);

			for(Entry<Object, Object> entry : examenMap.entrySet()) {
				html += entry.getValue() + "</br> ";
			}   
		}
		html += "</BODY></HTML>";
		return html;
		   
	}
	
	// Messaging
	   public void onMessage(String message) {
		   this.redisMessages.add(message);
	   }
	
}
