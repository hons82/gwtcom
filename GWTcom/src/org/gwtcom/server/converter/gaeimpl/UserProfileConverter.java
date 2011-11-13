package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserProfileRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userProfileConverter")
public class UserProfileConverter extends AbstractIdConverter implements IConverter<UserProfileRemote, UserProfile> {

	@Autowired
	private FriendsConverter _friendsConverter;

	@Override
	public UserProfileRemote convertDomainToRemote(UserProfile domain) {
		// copies all the fields to the bigger class
		UserProfileRemote remote = new UserProfileRemote(_friendsConverter.convertDomainToRemote(domain));
		if (domain != null) {
		}
		return remote;
	}

	@Override
	public UserProfile convertRemoteToDomain(UserProfile domain, UserProfileRemote remote) {
		if (domain != null && remote != null) {
			domain = _friendsConverter.convertRemoteToDomain(domain, remote);
		}
		return domain;
	}

}