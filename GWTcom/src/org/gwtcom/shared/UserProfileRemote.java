package org.gwtcom.shared;

public class UserProfileRemote extends FriendEntryRemote {

	public UserProfileRemote() {

	}

	public UserProfileRemote(FriendEntryRemote friend) {
		if (friend != null) {
			setId(friend.getId());
			setParentId(friend.getParentId());
			setName(friend.getFirstname());
			setLastname(friend.getLastname());
			setProfileImage(friend.getProfileImage());
			setEmail(friend.getEmail());
			setGender(friend.getGender());
		}
	}

}
