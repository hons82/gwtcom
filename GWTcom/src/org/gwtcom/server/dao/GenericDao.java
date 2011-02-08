package org.gwtcom.server.dao;

import java.io.Serializable;
import java.util.List;

import org.gwtcom.server.domain.BaseDomainObject;

public interface GenericDao<T extends BaseDomainObject, PK extends Serializable> {

	public T retrieve(PK id);

	public List<T> retrieveAll();
	
	public T saveOrUpdate(T entity);
	
	public void delete(T entity);
	
	public void purge(T entity);
	
	public T restore(PK id);
	
	public Class<T> getType();

	public void setType(Class<T> type);

	public T refresh(T entity);

}
