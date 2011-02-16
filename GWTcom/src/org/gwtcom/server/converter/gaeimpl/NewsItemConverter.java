package org.gwtcom.server.converter.gaeimpl;

import org.gwtcom.server.converter.IConverter;
import org.gwtcom.server.domain.NewsItem;
import org.gwtcom.shared.NewsItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("newsItemConverter")
public class NewsItemConverter extends AbstractIdConverter implements IConverter<NewsItemRemote, NewsItem> {

	@Autowired
	protected UserProfileConverter userProfileConverter;

	@Override
	public NewsItemRemote convertDomainToRemote(NewsItem domain) {
		NewsItemRemote remote = new NewsItemRemote();
		if (domain != null) {
			remote.setId(convertFromID(domain.getId()));
			remote.setTitle(domain.getTitle());
			remote.setDateAdded(domain.getDateAdded());
			// entityManager.createQuery("SELECT _author FROM " + NewsItem.class.getName()).getResultList();
//			remote.setAuthor(userProfileConverter.convertDomainToRemote(domain.getAuthor()));
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
