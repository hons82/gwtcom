package org.gwtcom.server;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDatabaseService {

	@PersistenceContext
	protected EntityManager _entityManager;

	public EntityManager getEntityManager() {
		return _entityManager;
	}
}
