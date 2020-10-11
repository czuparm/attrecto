package hu.attrecto.czuparm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.attrecto.czuparm.domain.User;
import hu.attrecto.czuparm.repository.UserRepository;

@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
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
		logger.info("A(z) " + userId + "törlésre került");
		userRepository.deleteById(userId);
	}
	
	public List<User> getAllUser(){
		logger.info("Összes user lekérése");
		return userRepository.findAll();
	}

	public User getOnUser(Long userId) {
		logger.info("A(z) " + userId + "lekérése");
		return userRepository.findById(userId).get();
	}

	public User saveUser(User user) {
		if(user.getId() == null){
			logger.info("A(z) " + user.getUserName() + "nevű user letétrehozásra került");
		} else {
			logger.info("A(z) " + user.getUserName() + "nevű user módosításra került");
		}
		baseService.setAuditable(user);
		return userRepository.save(user);
	}
	
}
