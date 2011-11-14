package org.gwtcom.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Authority extends BaseDomainObject {

	@Column(name = "authname")
	private String _authname;

	public void setAuthname(String authname) {
		_authname = authname;
	}

	public String getAuthname() {
		return _authname;
	}
}