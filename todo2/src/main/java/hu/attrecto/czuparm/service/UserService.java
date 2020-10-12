package hu.attrecto.czuparm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.attrecto.czuparm.domain.Role;
import hu.attrecto.czuparm.domain.User;
import hu.attrecto.czuparm.repository.RoleRepository;
import hu.attrecto.czuparm.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private static final String DEFAULT_ROLE="USER"; 
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}	

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	
	public void deleteUser(Long userId) {
		logger.info("A(z) " + userId + "-u user törlésre került");
		userRepository.deleteById(userId);
	}
	
	public List<User> getAllUser(){
		logger.info("Összes user lekérése");
		return userRepository.findAll();
	}

	public User getOneUser(Long userId) {
		logger.info("A(z) " + userId + " azonosítójú user lekérése");
		return userRepository.findById(userId).get();
	}

	public User saveUser(User user) {
		if(user.getId() == null){
			logger.info("A(z) " + user.getName() + " nevű user letétrehozásra került");
		} else {
			logger.info("A(z) " + user.getName() + " nevű user módosításra került");
		}
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);			
		}
		return new UserDetailsImp(user);
	}
	
	public boolean registerUser(User user) {
		User userCheck = userRepository.findByEmail(user.getEmail());
		
		if(userCheck != null) {
			return false;
		}
			
		
		Role role = roleRepository.findByRole(DEFAULT_ROLE);
		if(role == null) {
			user.addRole(new Role(DEFAULT_ROLE));
		} else {
			user.addRole(role);
		}
		saveUser(user);
		return true;
	}
	
}
