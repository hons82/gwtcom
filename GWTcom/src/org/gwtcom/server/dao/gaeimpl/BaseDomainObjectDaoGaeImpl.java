package org.gwtcom.server.dao.gaeimpl;

import org.gwtcom.server.dao.BaseDomainObjectDao;
import org.gwtcom.server.domain.BaseDomainObject;
import org.springframework.stereotype.Repository;

@Repository("baseDomainObjectDao")
public class BaseDomainObjectDaoGaeImpl extends GenericDaoGaeImpl<BaseDomainObject, String> implements
		BaseDomainObjectDao {

}
