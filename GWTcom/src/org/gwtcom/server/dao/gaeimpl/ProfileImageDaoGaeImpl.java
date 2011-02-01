package org.gwtcom.server.dao.gaeimpl;

import org.gwtcom.server.dao.ProfileImageDao;
import org.gwtcom.server.domain.ProfileImage;
import org.springframework.stereotype.Repository;

@Repository("profileImageDao")
public class ProfileImageDaoGaeImpl extends GenericDaoGaeImpl<ProfileImage, String> implements ProfileImageDao {

	public ProfileImageDaoGaeImpl(){
		super(ProfileImage.class);
	}
}
