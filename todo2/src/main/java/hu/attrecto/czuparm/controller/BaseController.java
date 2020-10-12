package hu.attrecto.czuparm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.attrecto.czuparm.domain.User;
import hu.attrecto.czuparm.service.UserService;

@Controller
@RequestMapping(value="/api/base")
public class BaseController {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String registration(Model model, @ModelAttribute User user){
		if(userService.registerUser(user)) {
			return "auth/login";
		} else {
			model.addAttribute("error", "Ezzel az email címmel már regisztráltak!");
			return "registration";
		}
	}
}
