package hu.attrecto.czuparm.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import hu.attrecto.czuparm.intf.Auditable;

@Service
public class BaseService {
	
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
	
}
