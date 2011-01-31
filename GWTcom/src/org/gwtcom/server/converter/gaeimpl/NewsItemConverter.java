package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.domain.NewsItem;
import org.gwtcom.shared.NewsItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.KeyFactory;

@Repository("newsItemConverter")
public class NewsItemConverter  implements IConverter<NewsItemRemote, NewsItem> {

	protected UserProfileConverter userProfileConverter;

	// protected EntityManager entityManager;
	//
	// @Autowired
	// public void setentityManager(EntityManager entityManager) {
	// this.entityManager = entityManager;
	// }

	@Autowired
	public void setUserProfileConverter(UserProfileConverter userProfileConverter) {
		this.userProfileConverter = userProfileConverter;
	}

	@Override
	public NewsItemRemote convertDomainToRemote(NewsItem domain) {
		NewsItemRemote remote = new NewsItemRemote();
		if (domain != null) {
			remote.setId(KeyFactory.keyToString(domain.getId()));
			remote.setTitle(domain.getTitle());
			remote.setDateAdded(domain.getDateAdded());
			// entityManager.createQuery("SELECT _author FROM " + NewsItem.class.getName()).getResultList();
			remote.setAuthor(userProfileConverter.convertDomainToRemote(domain.getAuthor()));
			remote.setContent(domain.getContent() != null ? domain.getContent().getValue() : "");
		}
		return remote;
	}

	@Override
	public NewsItem convertRemoteToDomain(NewsItem domain, NewsItemRemote remote) {
		// TODO Auto-generated method stub
		return null;
	}

}
