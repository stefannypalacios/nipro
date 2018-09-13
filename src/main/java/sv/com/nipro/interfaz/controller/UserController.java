package sv.com.nipro.interfaz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sv.com.nipro.interfaz.entities.User;
import sv.com.nipro.interfaz.repository.UserRepository;

import javax.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	public Page<User> getQuestions(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

}
