package org.gwtcom.server.dao.gaeimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.gwtcom.server.dao.GenericDao;
import org.gwtcom.server.domain.BaseDomainObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.KeyFactory;

@Repository("genericDao")
public abstract class GenericDaoGaeImpl<T extends BaseDomainObject, PK extends Serializable> implements
		GenericDao<T, PK> {

	@PersistenceContext
	protected EntityManager _entityManager;
	@Autowired
	protected DatastoreService _datastoreService;

	protected Class<T> type;

	public GenericDaoGaeImpl() {
		this(null);
	}

	public GenericDaoGaeImpl(Class<T> type) {
		setType(type);
	}

	@Override
	public Class<T> getType() {
		return type;
	}

	@Override
	public void setType(final Class<T> type) {
		this.type = type;
	}

	@Override
	public T retrieve(PK id) {
		return _entityManager.find(this.getType(), KeyFactory.stringToKey((String) id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> retrieveAll() {
		List<T> resultList = _entityManager.createQuery(
				"SELECT FROM " + getType().getName() + " WHERE _userDeleted IS NULL").getResultList();
		resultList.size();
		return resultList;
	}

	@Override
	public T refresh(T entity) {
		_entityManager.refresh(entity);
		return entity;
	}

	@Override
	@Transactional(readOnly = false)
	public T saveOrUpdate(T entity) {
		if (entity.getId() != null) {
			entity = _entityManager.merge(entity);
		} else {
			_entityManager.persist(entity);
		}
		return entity;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(T entity, PK userId) {
		if (entity != null) {
			entity.setDateDeleted(new Date());
			entity.setUserDeleted(KeyFactory.stringToKey((String) userId));
			saveOrUpdate(entity);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(PK id, PK userId) {
		T entity = retrieve(id);
		delete(entity, userId);
	}

	@Override
	@Transactional(readOnly = false)
	public void purge(T entity) {
		if (entity != null) {
			_entityManager.remove(entity);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public T restore(PK id) {
		if (id != null) {
			T entity = retrieve(id);
			entity.setDateDeleted(null);
			saveOrUpdate(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void flush() {
		_entityManager.flush();
	}
}
