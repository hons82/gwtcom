package org.gwtcom.shared;


public abstract class ParentBaseDomainRemote extends BaseDomainRemote {

	private String _parentId;

	public void setParentId(String parentId) {
		_parentId = parentId;
	}

	public String getParentId() {
		return _parentId;
	}
}
