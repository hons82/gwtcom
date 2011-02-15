package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.shared.UserLoginRemote;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.KeyFactory;

@Repository("userLoginConverter")
public class UserLoginConverter  extends AbstractIdConverter implements IConverter<UserLoginRemote, UserLogin> {

	@Override
	public UserLoginRemote convertDomainToRemote(UserLogin domain) {
		UserLoginRemote remote = new UserLoginRemote();
		if (domain != null) {
			remote.setId(convertFromID(domain.getId()));
			remote.setName(domain.getName());
			remote.setUsername(domain.getName());
			remote.setProfileId(domain.getUserprofile() != null ? KeyFactory.keyToString(domain.getUserprofile().getId())
					: "");
		}
		return remote;
	}

	@Override
	public UserLogin convertRemoteToDomain(UserLogin domain, UserLoginRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}

}
