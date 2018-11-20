package sv.com.nipro.interfaz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutesController {

	@GetMapping("/")
	public String index() {
		return "login";
	}
	
	@GetMapping("/main")
	public String prueba() {
		return "transaction";
	}
}
