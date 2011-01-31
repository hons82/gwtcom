package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.dao.UserProfileDao;
import org.gwtcom.server.domain.WallEntry;
import org.gwtcom.shared.WallEntryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.KeyFactory;

@Repository("wallEntryConverter")
public class WallEntryConverter implements IConverter<WallEntryRemote, WallEntry> {

	@Autowired
	private UserProfileConverter _userProfileConverter;
	@Autowired
	private UserProfileDao _userProfileDao;

	@Override
	public WallEntryRemote convertDomainToRemote(WallEntry domain) {
		WallEntryRemote remote = new WallEntryRemote();
		if (domain != null) {
			remote.setId(KeyFactory.keyToString(domain.getId()));
			remote.setAuthor(_userProfileConverter.convertDomainToRemote(_userProfileDao.retrieve(KeyFactory
					.keyToString(domain.getAuthor()))));
			remote.setOwner(_userProfileConverter.convertDomainToRemote(_userProfileDao.retrieve(KeyFactory
					.keyToString(domain.getOwner()))));
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

	// private UserProfile getUserProfileByKey(Key userProfileKey) {
	// return userProfileKey != null ? _entityManager.find(UserProfile.class, userProfileKey) : null;
	// }
}
