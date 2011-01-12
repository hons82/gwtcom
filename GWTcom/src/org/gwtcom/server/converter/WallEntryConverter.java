package org.gwtcom.server.converter;

import org.gwtcom.server.domain.WallEntry;
import org.gwtcom.shared.WallEntryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wallEntryConverter")
public class WallEntryConverter implements AbstractConverter<WallEntryRemote, WallEntry> {

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
			// TODO
			// remote.setAuthor(_userProfileConverter.convertDomainToRemote(domain.getAuthor()));
			// remote.setOwner(_userProfileConverter.convertDomainToRemote(domain.getOwner()));
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

}
