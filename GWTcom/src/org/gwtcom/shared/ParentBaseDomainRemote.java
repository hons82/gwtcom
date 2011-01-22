package org.gwtcom.shared;


public abstract class ParentBaseDomainRemote extends BaseDomainRemote {

	private Long _parentId;

	public void setParentId(Long parentId) {
		_parentId = parentId;
	}

	public Long getParentId() {
		return _parentId;
	}
}
