package org.gwtcom.server.dao;

import java.util.List;

import org.gwtcom.server.domain.WallEntry;
import org.gwtcom.shared.WallEntryRemote;

public interface WallEntryDao extends GenericDao<WallEntry, String> {

	public List<WallEntryRemote> getPublicWallEntries(String userLoginId);

	public WallEntryRemote addWallPost(String userLoginId, String loggedInUserId, String content);

}
