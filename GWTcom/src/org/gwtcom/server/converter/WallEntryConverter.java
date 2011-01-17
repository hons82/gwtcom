package org.gwtcom.server.converter;

import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.server.domain.WallEntry;
import org.gwtcom.shared.WallEntryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;

@Service("wallEntryConverter")
public class WallEntryConverter extends AbstractDatabaseConverter implements IConverter<WallEntryRemote, WallEntry> {

	private UserProfileConverter _userProfileConverter;

	@Autowired
	public void setUserProfileConverter(UserProfileConverter userProfileConverter) {
		_userProfileConverter = userProfileConverter;
	}

	@Override
	public WallEntryRemote convertDomainToRemote(WallEntry domain) {
		WallEntryRemote remote = new WallEntryRemote();
		if (domain != null) {
			remote.setId(domain.getId().getId());
			remote.setAuthor(_userProfileConverter.convertDomainToRemote(getUserProfileByKey(domain.getAuthor())));
			remote.setOwner(_userProfileConverter.convertDomainToRemote(getUserProfileByKey(domain.getOwner())));
			remote.setContent(domain.getContent());
			remote.setDateAdded(domain.getDateAdded());
		}
		return remote;
	}

	@Override
	public WallEntry convertRemoteToDomain(WallEntryRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserProfile getUserProfileByKey(Key userProfileKey) {
		return _entityManager.find(UserProfile.class, userProfileKey);
	}

}
