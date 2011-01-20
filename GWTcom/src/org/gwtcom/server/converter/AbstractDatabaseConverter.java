package org.gwtcom.server.converter;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDatabaseConverter {

	protected EntityManager _entityManager;

	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		_entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return _entityManager;
	}
}
