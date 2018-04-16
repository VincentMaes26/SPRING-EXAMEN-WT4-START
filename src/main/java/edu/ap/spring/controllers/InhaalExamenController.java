package edu.ap.spring.controllers;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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
		String html = "<HTML>";
		   // get the bitcount of our counter
		   Set<String> examens = service.keys("inhaalexamens:lijst");
		   for(String e : examens) {
			   // make a key from the byte array
			   String key = new String(e);
			   // get hash with actors
			   Map<Object, Object> examenMap = service.hgetAll(key);
			   // get all parts of the key, eg : ["movies", "1998", "The Big Lebowski"]
			   String[] parts = key.split(":");
			   
			   html += "<li>" + parts[1] + " " + parts[1] + " " + parts[3];
			   html += "Actors : ";
			   // iterate over actors
			   for(Entry<Object, Object> entry : examens.entrySet()) {
				   html += entry.getValue() + ", ";
			   }
			   // strip off last ', '
			   html = html.substring(0, html.length() - 2);
		   }
		   html += "</BODY></HTML>";
		   
		   return html;
		
	}
}
