package springsecurity.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String welcome(Map<String, Object> model){
		model.put("time", new Date());
		return "welcome";
	}
	
	@RequestMapping("/hello")
	public String sayHello(Map<String, Object> model){
		model.put("message", "Hello!");
		return "hello";
	}
	
	@RequestMapping("/admin")
	public String admin(Map<String, Object> model){
		Runtime runtime = Runtime.getRuntime();
		model.put("message", runtime.freeMemory());
		return "admin";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/accessDenied")
	public String accessDenied(){
		return "accessDenied";
	}
	
}
