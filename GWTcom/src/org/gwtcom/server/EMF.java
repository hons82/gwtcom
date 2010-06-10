package org.gwtcom.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {

	private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");

	public EMF() {
	}

	public EntityManager entityManager() {
		return emfInstance.createEntityManager();
	}
}