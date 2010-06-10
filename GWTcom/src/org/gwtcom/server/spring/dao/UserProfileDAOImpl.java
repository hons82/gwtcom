package org.gwtcom.server.spring.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class UserProfileDAOImpl<UserProfile> extends HibernateDaoSupport implements UserProfileDAO<UserProfile>{
	
	@Transactional(readOnly=true)
	public UserProfile getUserProfile(int id){
		return (UserProfile) getHibernateTemplate().findByNamedQueryAndNamedParam("UserProfileById", "UserProfileId", id);
	}

}
