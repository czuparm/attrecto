package hu.attrecto.czuparm.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.attrecto.czuparm.intf.Auditable;

@Entity
@Table(name = "todos")
public class Todo implements Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long userId;
	private String titel;
	private String description;
	private LocalDateTime deadLine;
	private TodoStatus todoStatus;
	
	private String createUser;
	private LocalDateTime createDate;
	private String lastModifyUser;
	private LocalDateTime lastModifyDate;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getTitel() {
		return titel;
	}
	
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDateTime getDeadLine() {
		return deadLine;
	}
	
	public void setDeadLine(LocalDateTime deadLine) {
		this.deadLine = deadLine;
	}
	
	public TodoStatus getTodoStatus() {
		return todoStatus;
	}
	
	public void setTodoStatus(TodoStatus todoStatus) {
		this.todoStatus = todoStatus;
	}
	
	@Override
	public String getCreateUser() {
		return createUser;
	}
	
	@Override
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@Override
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	@Override
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String getLastModifyUser() {
		return lastModifyUser;
	}
	
	@Override
	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	
	@Override
	public LocalDateTime getLastModifyDate() {
		return lastModifyDate;
	}
	
	@Override
	public void setLastModifyDate(LocalDateTime lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	
	

}
