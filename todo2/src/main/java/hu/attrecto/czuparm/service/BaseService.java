package hu.attrecto.czuparm.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import hu.attrecto.czuparm.domain.Todo;
import hu.attrecto.czuparm.domain.User;
import hu.attrecto.czuparm.intf.Auditable;

@Service
public class BaseService {
	
	private Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	private JavaMailSender emailSender;
	private TodoService todoService;
	private UserService userService;
	
	@Value("${mail.mailFrom}")
	private String mailFrom;
	
	@Value("${mail.mailSendingEnable}")
	private boolean mailSendingEnable;
	
	@Autowired
	public void setJavaMailSender(JavaMailSender emailSender) {
		this.emailSender=emailSender;
	}
	
	@Autowired
	public void setTodoService(TodoService todoService) {
		this.todoService=todoService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	public void setAuditable(Auditable auditable) {
		
		 if (auditable.getCreateDate() == null) {
	            auditable.setCreateDate(LocalDateTime.now());
	        }
	        if (auditable.getCreateUser() == null) {
	            auditable.setCreateUser("Létrehozó");
	        }
	        auditable.setLastModifyDate(LocalDateTime.now());
	        auditable.setLastModifyUser("Módosító");
	}
	
	@Scheduled(fixedDelay = 60000)
	private void sendEmailFromDeadLineTodos() {
		
		if(!mailSendingEnable) {
			return;
		}
		
		Map<Long, User> userWithId = new HashMap<Long, User>();
		Map<Long, List<Todo>> todoWithUserId = new HashMap<Long, List<Todo>>();
		
		logger.info("Közeli határidős todo email értesítés küldése");
		List<Todo> closeDeadLineTodos = todoService.getCloseDeadLineTodos();
		
		//hogy nem kelljen minden alkalommal lekérdezni a felhasználókat ahányszor emailt akarunk küldeni 
		closeDeadLineTodos.forEach(closeDeadLineTodo -> {
			if(!userWithId.containsKey(closeDeadLineTodo.getUserId())) {
				userWithId.put(closeDeadLineTodo.getUserId(), userService.getOneUser(closeDeadLineTodo.getUserId()));
			}
			if(!todoWithUserId.containsKey(closeDeadLineTodo.getUserId())) {
				List<Todo> todos = new ArrayList<Todo>();
				todos.add(closeDeadLineTodo);
				todoWithUserId.put(closeDeadLineTodo.getUserId(), todos);
			} else {
				todoWithUserId.get(closeDeadLineTodo.getUserId()).add(closeDeadLineTodo);
			}
		});
		
		userWithId.forEach((userId, user) -> {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(mailFrom);
			message.setSubject("Értesítés közelgő határidős feladatokról");
			message.setTo(user.getEmail());
			message.setText(createEmailText(user, todoWithUserId));
			logger.info("Email küldése a " + user.getName() + " felhasználónak a " + user.getEmail() + " címre a hamarosan lejáró feladatokról");
			try {
				emailSender.send(message);
				setTodosStatusToSent(userId, todoWithUserId);
			} catch(Exception e) {
				logger.error(user.getName() + " felhasználónak nem sikerült a feladatokról az e-mail kiküldése", e);
			}
		});
		
	}

	private void setTodosStatusToSent(Long userId, Map<Long, List<Todo>> todoWithUserId) {
		todoWithUserId.get(userId).forEach(todo->{
			todo.setNotificationSent(true);
		});
		todoService.saveTodos(todoWithUserId.get(userId));		
	}

	private String createEmailText(User user, Map<Long, List<Todo>> todoWithUserId) {
		StringBuffer sb = new StringBuffer();
		sb.append("Kedves "+user.getName() + "!");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("Önnek az alábbi feladatai hamoarosan lejárnak:");
		sb.append("\r\n");
		sb.append("\r\n");
		todoWithUserId.get(user.getId()).forEach(todo->{
			sb.append("-"+todo.getTitle());
			sb.append("\r\n");
		});		
		return sb.toString();
	}
	
}
