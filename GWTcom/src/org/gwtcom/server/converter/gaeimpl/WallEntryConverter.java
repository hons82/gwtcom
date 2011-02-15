package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.dao.UserProfileDao;
import org.gwtcom.server.domain.WallEntry;
import org.gwtcom.shared.WallEntryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("wallEntryConverter")
public class WallEntryConverter extends AbstractIdConverter implements IConverter<WallEntryRemote, WallEntry> {

	@Autowired
	private UserProfileConverter _userProfileConverter;
	@Autowired
	private UserProfileDao _userProfileDao;

	@Override
	public WallEntryRemote convertDomainToRemote(WallEntry domain) {
		WallEntryRemote remote = new WallEntryRemote();
		if (domain != null) {
			remote.setId(convertFromID(domain.getId()));
			remote.setAuthor(_userProfileConverter.convertDomainToRemote(_userProfileDao.retrieve(convertFromID(domain
					.getAuthor()))));
			remote.setOwner(_userProfileConverter.convertDomainToRemote(_userProfileDao.retrieve(convertFromID(domain
					.getOwner()))));
			remote.setContent(domain.getContent());
			remote.setDateAdded(domain.getDateAdded());
		}
		return remote;
	}

	@Override
	public WallEntry convertRemoteToDomain(WallEntry domain, WallEntryRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}

}
