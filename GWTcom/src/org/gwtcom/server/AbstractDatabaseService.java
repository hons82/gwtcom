package org.gwtcom.server;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDatabaseService {
	
	protected EntityManager _entityManager;

	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		_entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return _entityManager;
	}
}
