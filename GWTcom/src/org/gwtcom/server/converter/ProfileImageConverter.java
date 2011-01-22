package org.gwtcom.server.converter;

import org.gwtcom.server.domain.ProfileImage;
import org.gwtcom.shared.ProfileImageRemote;
import org.springframework.stereotype.Service;

import com.google.gwt.user.server.Base64Utils;

@Service("profileImageConverter")
public class ProfileImageConverter implements IConverter<ProfileImageRemote, ProfileImage> {

	@Override
	public ProfileImageRemote convertDomainToRemote(ProfileImage domain) {
		ProfileImageRemote remote = new ProfileImageRemote();
		if (domain != null) {
			remote.setId(domain.getId().getId());
			remote.setImage(domain.getPicture() != null ? Base64Utils.toBase64(domain.getPicture()) : null);
			remote.setImageThumb(domain.getPictureThumb() != null ? Base64Utils.toBase64(domain.getPictureThumb()) : null);
		}
		return remote;
	}

	@Override
	public ProfileImage convertRemoteToDomain(ProfileImage domain, ProfileImageRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}

}
