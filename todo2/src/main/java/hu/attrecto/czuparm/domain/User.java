package hu.attrecto.czuparm.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import hu.attrecto.czuparm.intf.Auditable;

@Entity
@Table(name = "users")
public class User implements Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userName;
	private String name;
	private String email;
	
	private String createUser;
	private LocalDateTime createDate;
	private String lastModifyUser;
	private LocalDateTime lastModifyDate;
		
	public User() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
