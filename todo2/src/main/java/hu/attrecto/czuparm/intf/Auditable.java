package hu.attrecto.czuparm.intf;

import java.time.LocalDateTime;

public interface Auditable {
	
	public String getCreateUser();

    public void setCreateUser(String createUser);

    public LocalDateTime getCreateDate();

    public void setCreateDate(LocalDateTime createDate);

    public String getLastModifyUser();

    public void setLastModifyUser(String lastModifyUser);

    public LocalDateTime getLastModifyDate();

    public void setLastModifyDate(LocalDateTime lastModifyDate);

	
}
