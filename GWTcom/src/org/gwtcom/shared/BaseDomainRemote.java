package org.gwtcom.shared;

import java.io.Serializable;

public abstract class BaseDomainRemote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8064810346354919280L;

	private Long _id;
	
	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
		_id = id;
	}
}
