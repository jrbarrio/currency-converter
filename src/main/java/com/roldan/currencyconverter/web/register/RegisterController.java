package com.roldan.currencyconverter.web.register;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roldan.currencyconverter.domain.model.User;
import com.roldan.currencyconverter.domain.model.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private UserRepository userRepository;
	private UserTranslator userTranslator;
	
	@Autowired
	public RegisterController(UserRepository userRepository, UserTranslator userTranslator) {
		this.userRepository = userRepository;
		this.userTranslator = userTranslator;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("userForm", new UserForm()); 
		return "registerForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processRegister(@Valid UserForm userForm, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		
		User user = userTranslator.translate(userForm); 
		userRepository.save(user);
		return "loginForm";
	}
}