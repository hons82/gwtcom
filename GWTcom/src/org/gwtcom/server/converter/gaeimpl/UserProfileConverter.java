package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserProfileRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userProfileConverter")
public class UserProfileConverter  extends AbstractIdConverter implements IConverter<UserProfileRemote, UserProfile> {

	@Autowired
	private ProfileImageConverter _profileImageConverter;

	@Override
	public UserProfileRemote convertDomainToRemote(UserProfile domain) {
		UserProfileRemote remote = new UserProfileRemote();
		if (domain != null) {
			remote.setId(convertFromID(domain.getId()));
			remote.setName(domain.getName());
			remote.setSurname(domain.getSurname());
			remote.setEmail(domain.getEmail());
			remote.setGender(domain.getGender());
			remote.setProfileImage(_profileImageConverter.convertDomainToRemote(domain.getProfileImage()));
		}
		return remote;
	}

	@Override
	public UserProfile convertRemoteToDomain(UserProfile domain, UserProfileRemote remote) {
		if (domain != null && remote != null) {
			domain.setId(convertToId(remote.getId()));
			domain.setName(remote.getName());
			domain.setSurname(remote.getSurname());
			domain.setEmail(remote.getEmail());
			domain.setGender(remote.getGender());
		}
		return domain;
	}

}
