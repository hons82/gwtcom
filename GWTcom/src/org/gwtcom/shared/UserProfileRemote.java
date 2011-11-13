package org.gwtcom.shared;

public class UserProfileRemote extends FriendEntryRemote {

	public UserProfileRemote() {

	}

	public UserProfileRemote(FriendEntryRemote friend) {
		if (friend != null) {
			setId(friend.getId());
			setParentId(friend.getParentId());
			setName(friend.getName());
			setSurname(friend.getSurname());
			setProfileImage(friend.getProfileImage());
			setEmail(friend.getEmail());
			setGender(friend.getGender());
		}
	}

}
