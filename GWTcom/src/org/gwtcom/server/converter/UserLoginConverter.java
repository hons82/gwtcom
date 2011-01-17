package org.gwtcom.server.converter;

import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.shared.UserLoginRemote;
import org.springframework.stereotype.Service;

@Service("userLoginConverter")
public class UserLoginConverter implements IConverter<UserLoginRemote, UserLogin> {
	
	@Override
	public UserLoginRemote convertDomainToRemote(UserLogin domain) {
		UserLoginRemote remote = new UserLoginRemote();
		if (domain != null) {
			remote.setId(domain.getId().getId());
			remote.setName(domain.getName());
			remote.setUsername(domain.getName());
		}
		return remote;
	}

	@Override
	public UserLogin convertRemoteToDomain(UserLoginRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}

}
