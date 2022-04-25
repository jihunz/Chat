package kr.ac.kopo.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
	
	@GetMapping("/")
	String index() {
		return "index";
	}
	
	@GetMapping("/chat")
	String chat() {
		return "chat";
	}
}
