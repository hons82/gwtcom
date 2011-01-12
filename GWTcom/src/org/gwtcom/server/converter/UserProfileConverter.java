package org.gwtcom.server.converter;

import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserProfileRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userProfileConverter")
public class UserProfileConverter implements AbstractConverter<UserProfileRemote, UserProfile> {

	private ProfileImageConverter _profileImageConverter;

	@Autowired
	public void setProfileImageConverter(ProfileImageConverter profileImageConverter){
		_profileImageConverter = profileImageConverter;
	}
	
	@Override
	public UserProfileRemote convertDomainToRemote(UserProfile domain) {
		UserProfileRemote remote = new UserProfileRemote();
		if (domain != null) {
			remote.setId(domain.getId().getId());
			remote.setName(domain.getName());
			remote.setSurname(domain.getSurname());
			remote.setEmail(domain.getEmail());
			remote.setGender(domain.getGender());
			//remote.setProfileImage(_profileImageConverter.convertDomainToRemote(domain.getProfileImage()));
		}
		return remote;
	}

	@Override
	public UserProfile convertRemoteToDomain(UserProfileRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}

}
