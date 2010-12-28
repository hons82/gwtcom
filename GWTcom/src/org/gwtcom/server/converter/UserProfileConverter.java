package org.gwtcom.server.converter;

import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserProfileRemote;
import org.springframework.stereotype.Service;

@Service("userProfileConverter")
public class UserProfileConverter implements AbstractConverter<UserProfileRemote, UserProfile> {

	@Override
	public UserProfileRemote convertDomainToRemote(UserProfile domain) {
		UserProfileRemote remote = new UserProfileRemote();
		if (domain != null) {
			remote.setEmail(domain.getEmail());
			remote.setGender(domain.getGender());
			remote.setId(domain.getId().getId());
			remote.setName(domain.getName());
			remote.setSurname(domain.getSurname());
		}
		return remote;
	}

	@Override
	public UserProfile convertRemoteToDomain(UserProfileRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}

}
