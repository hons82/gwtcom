package org.gwtcom.server.converter;

import org.gwtcom.server.domain.ProfileImage;
import org.gwtcom.shared.ProfileImageRemote;
import org.springframework.stereotype.Service;

import com.google.gwt.user.server.Base64Utils;

@Service("profileImageConverter")
public class ProfileImageConverter implements AbstractConverter<ProfileImageRemote, ProfileImage> {

	@Override
	public ProfileImageRemote convertDomainToRemote(ProfileImage domain) {
		ProfileImageRemote remote = new ProfileImageRemote();
		if (domain!=null){
			remote.setId(domain.getId().getId());
			remote.setImage(Base64Utils.toBase64(domain.getPicture()));
			remote.setImageThumb(Base64Utils.toBase64(domain.getPictureThumb()));
		}
		return remote;
	}

	@Override
	public ProfileImage convertRemoteToDomain(ProfileImageRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}


}
