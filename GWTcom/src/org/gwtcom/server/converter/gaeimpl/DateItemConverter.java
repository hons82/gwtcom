package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.domain.DateItem;
import org.gwtcom.shared.DateItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dateItemConverter")
public class DateItemConverter extends AbstractIdConverter implements IConverter<DateItemRemote, DateItem> {

	@Autowired
	protected UserProfileConverter userProfileConverter;

	public void setUserProfileConverter(UserProfileConverter userProfileConverter) {
		this.userProfileConverter = userProfileConverter;
	}

	@Override
	public DateItemRemote convertDomainToRemote(DateItem domain) {
		DateItemRemote remote = new DateItemRemote();
		if (domain != null) {
			remote.setId(convertFromID(domain.getId()));
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
