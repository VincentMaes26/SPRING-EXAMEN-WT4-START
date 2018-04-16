package edu.ap.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import edu.ap.spring.controllers.InhaalExamenController;
import edu.ap.spring.redis.RedisService;
import edu.ap.spring.model.*;

@SpringBootApplication
public class SpringBootApp {
	
	private RedisService service;
	private String CHANNEL = "edu:ap:redis";
	private String KEY = "edu:ap:test";
	
	@Autowired
	public void setRedisService(RedisService service) {
		this.service = service;
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {
			
	 		// Inhaalexamens
	 		Map<Object, Object > inhaalexamens = new HashMap<Object, Object>();
	 		InhaalExamen examen1 = new InhaalExamen("Jos", ".NET", "Niet genoeg gestudeerd.");
	 		InhaalExamen examen2 = new InhaalExamen("Jeff", "Webtech", "Niet genoeg gestudeerd.");
	 		if(!inhaalexamens.containsValue(examen1)) {
	 			inhaalexamens.put("examen1", examen1);
	 		}
	 		
	 		if(!inhaalexamens.containsValue(examen2)) {
	 			inhaalexamens.put("examen2", examen2);
	 		}
	 		service.hset("Inhaalexamens:lijst", inhaalexamens);
	 		
		};
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
}
