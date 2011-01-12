package org.gwtcom.server.domain;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Authority extends BaseDomainObject {

	private static final long serialVersionUID = 2355733708719567498L;
	
	@Column(name = "authname")
	private String _authname;

	public void setAuthname(String authname) {
		_authname = authname;
	}

	public String getAuthname() {
		return _authname;
	}
}