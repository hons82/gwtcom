package org.gwtcom.server.converter;

import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserProfileRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userProfileConverter")
public class UserProfileConverter implements
		IConverter<UserProfileRemote, UserProfile> {

	private ProfileImageConverter _profileImageConverter;

	@Autowired
	public void setProfileImageConverter(ProfileImageConverter profileImageConverter) {
		_profileImageConverter = profileImageConverter;
	}

	@Override
	public UserProfileRemote convertDomainToRemote(UserProfile domain) {
		UserProfileRemote remote = new UserProfileRemote();
		if (domain != null) {
			remote.setParentId(domain.getLogin().getId().getId());
			remote.setId(domain.getId().getId());
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
		if (domain != null && remote!=null) {
			domain.setName(remote.getName());
			domain.setSurname(remote.getSurname());
			domain.setEmail(remote.getEmail());
			domain.setGender(remote.getGender());
		}
		return domain;
	}

}
