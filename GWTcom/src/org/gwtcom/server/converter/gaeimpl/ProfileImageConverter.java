package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.domain.ProfileImage;
import org.gwtcom.shared.ProfileImageRemote;
import org.springframework.stereotype.Repository;

import com.google.gwt.user.server.Base64Utils;

@Repository("profileImageConverter")
public class ProfileImageConverter extends AbstractIdConverter implements IConverter<ProfileImageRemote, ProfileImage> {

	@Override
	public ProfileImageRemote convertDomainToRemote(ProfileImage domain) {
		ProfileImageRemote remote = new ProfileImageRemote();
		if (domain != null) {
			remote.setId(convertFromID(domain.getId()));
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
