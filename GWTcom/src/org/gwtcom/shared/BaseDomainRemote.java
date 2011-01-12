package org.gwtcom.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class BaseDomainRemote implements IsSerializable {

	private Long _id;
	
	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
		_id = id;
	}
}
