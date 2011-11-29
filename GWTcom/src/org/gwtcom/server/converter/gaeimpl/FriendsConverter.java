package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.FriendEntryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("friendsConverter")
public class FriendsConverter  extends AbstractIdConverter implements IConverter<FriendEntryRemote, UserProfile> {

	@Autowired
	private ProfileImageConverter _profileImageConverter;

	@Override
	public FriendEntryRemote convertDomainToRemote(UserProfile domain) {
		FriendEntryRemote remote = new FriendEntryRemote();
		if (domain != null) {
			remote.setId(convertFromID(domain.getId()));
			remote.setName(domain.getFirstname());
			remote.setLastname(domain.getLastname());
			remote.setEmail(domain.getEmail());
			remote.setGender(domain.getGender());
			remote.setProfileImage(_profileImageConverter.convertDomainToRemote(domain.getProfileImage()));
		}
		return remote;
	}

	@Override
	public UserProfile convertRemoteToDomain(UserProfile domain, FriendEntryRemote remote) {
		if (domain != null && remote != null) {
			domain.setId(convertToId(remote.getId()));
			domain.setFirstname(remote.getFirstname());
			domain.setLastname(remote.getLastname());
			domain.setEmail(remote.getEmail());
			domain.setGender(remote.getGender());
		}
		return domain;
	}

}
