package hu.attrecto.czuparm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.attrecto.czuparm.domain.User;
import hu.attrecto.czuparm.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private BaseService baseService;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}

	public User getOnUser(Long userId) {
		return userRepository.findById(userId).get();
	}

	public User saveUser(User user) {
		baseService.setAuditable(user);
		return userRepository.save(user);
	}
	
}
