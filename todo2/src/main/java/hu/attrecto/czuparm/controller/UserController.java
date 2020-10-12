package hu.attrecto.czuparm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.attrecto.czuparm.domain.User;
import hu.attrecto.czuparm.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/getAllUser")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}	
	
	@PostMapping(value = "/delete")
	public void deleteUser(Long userId){
		userService.deleteUser(userId);		
	}
	
	@PostMapping(value = "/getOneUser")
	public User getOneUser(Long userId) {
		return userService.getOneUser(userId);
	}
	
	@PostMapping(value = "/saveUser")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
}
