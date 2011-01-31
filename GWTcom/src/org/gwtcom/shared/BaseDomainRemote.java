package org.gwtcom.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class BaseDomainRemote implements IsSerializable {

	private String _id;

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

}
