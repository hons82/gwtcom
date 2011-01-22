package org.gwtcom.server.converter;

import org.gwtcom.server.domain.DateItem;
import org.gwtcom.shared.DateItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dateItemConverter")
public class DateItemConverter implements IConverter<DateItemRemote, DateItem> {

	protected UserProfileConverter userProfileConverter;

	// protected EntityManager entityManager;
	//
	// @Autowired
	// public void setentityManager(EntityManager entityManager) {
	// this.entityManager = entityManager;
	// }

	@Autowired
	public void setUserProfileConverter(UserProfileConverter userProfileConverter) {
		this.userProfileConverter = userProfileConverter;
	}

	@Override
	public DateItemRemote convertDomainToRemote(DateItem domain) {
		DateItemRemote remote = new DateItemRemote();
		if (domain != null) {
			remote.setId(domain.getId().getId());
			remote.setTitle(domain.getTitle());
			remote.setDateAdded(domain.getDateAdded());
			// entityManager.createQuery("SELECT _author FROM " + NewsItem.class.getName()).getResultList();
			remote.setAuthor(userProfileConverter.convertDomainToRemote(domain.getAuthor()));
		}
		return remote;
	}

	@Override
	public DateItem convertRemoteToDomain(DateItem domain, DateItemRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}

}
