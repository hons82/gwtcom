package org.gwtcom.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.gwtcom.client.service.NewsService;
import org.gwtcom.server.converter.NewsItemConverter;
import org.gwtcom.server.domain.NewsItem;
import org.gwtcom.shared.NewsItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	// TODO: this is just a test
	private boolean _first;

	private NewsItemConverter newsItemConverter;

	protected EntityManager entityManager;

	@Autowired
	public void setentityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Autowired
	public void setNewsItemConverter(NewsItemConverter newsItemConverter) {
		this.newsItemConverter = newsItemConverter;
	}

	public NewsServiceImpl() {
		_first = false;
	}

	@Override
	@Secured("ROLE_ADMIN")
	public List<NewsItemRemote> getPrivateNews() {
		List<NewsItemRemote> ret = getPublicNews();
		return ret;
	}

	/**
	 * @return
	 */
	@Override
	public List<NewsItemRemote> getPublicNews() {
		List<NewsItemRemote> ret = new ArrayList<NewsItemRemote>();
		// TODO: this is just a test
		for (NewsItem item : getNewsItems()) {
			if (!_first) {
				removeNewsItem(item);
			}
		}
		if (!_first) {
			for (int i = 0; i < 30; i++) {
				createCustomer();
			}
		}
		_first = true;
		//
		for (NewsItem item : getNewsItems()) {
			ret.add(newsItemConverter.convertDomainToRemote(item));
		}
		return ret;
	}

	private void createCustomer() {
		EntityTransaction tx = entityManager.getTransaction();
		NewsItem newNewsItem = new NewsItem();
		newNewsItem.setAuthor(null);
		newNewsItem.setTitle("Die Auswirkungen der Sonnenstrahlen auf das Liebesleben der Pflastersteine "
				+ System.currentTimeMillis());
		newNewsItem.setDateAdded(new Date(System.currentTimeMillis()));
		newNewsItem.setContent(new Text("Die Wettbewerbskommission der Schweiz (Weko) nimmt Kooperationsvereinbarungen zwischen dem führenden Telekommunikationsunternehmen Swisscom und örtlichen Stromversorgern beim Bau von Glasfasernetzen unter die Lupe. Die Swisscom hatte Mitte 2009 angekündigt, in Ballungsräumen mit Fibre-to-the-Home (FTTH) künftig voll auf Glasfaser zu setzen. Bis 2015 will der Konzern die Städte Zürich, Genf, Basel, St. Gallen, Bern, Lausanne und den Kanton Freiburg flächendeckend mit FTTH versorgen. Dabei setzt die Swisscom nicht nur auf selbst installierte Glasfaserinfrastrukturen, sondern das Unternehmen will nach Möglichkeit auch Infrastrukturen nutzen, die bereits von örtlichen Elektrizitätsversorgern aufgebaut wurden, die den Ausbau eigener Glasfasernetze zuletzt stark vorangetrieben hatten. Und dies ist Wettbewerbern ein Dorn im Auge. So reichte etwa der Verband der Kabelnetzbetreiber, Swisscable, im Mai 2010 Beschwerde bei der Weko wegen eines Kooperationsvertrages zwischen der Stadt St. Gallen und Swisscom ein. Der Vorwurf: Marktmissbrauch. Weko-Angaben zufolge gingen in den vergangenen Monaten noch weitere Meldungen zu Kooperationen zwischen Elektrizitätsversorgern und der Swisscom bei der Wettbewerbsbehörde ein. Als erste Stadt habe St. Gallen einzelne Klauseln ihres Kooperationsvertrages im Rahmen eines Widerspruchsverfahrens zur Überprüfung gemeldet, weshalb diese Kooperation auch als erste beurteilt werde. Bei diesen Vertragsklauseln sehe die Weko teilweise 'wettbewerbsrechtliche Probleme', weshalb sie jetzt eine Vorabklärung eröffnet habe. Die ausgehandelten Vertragsklauseln, so die Weko, 'könnten gemäß einer ersten Einschätzung den Wettbewerb nachhaltig beschränken und langfristig zu einer Monopolisierung der Glasfasernetzinfrastruktur führen'. Ins Visier nehmen die Wettbewerbshüter auch die geplante Gründung eines Gemeinschaftsunternehmens zwischen der Swisscom und dem Energieversorger Groupe E zum Aufbau und Betrieb einer gemeinsamen Glasfaserinfrastruktur im Kanton Freiburg. Hier bestünden Anhaltspunkte, dass das Gemeinschaftsunternehmen 'eine marktbeherrschende Stellung begründet oder verstärkt'. (pmz) "));
		tx.begin();
		entityManager.persist(newNewsItem);
		tx.commit();
	}

	@Secured("ROLE_ADMIN")
	private void removeNewsItem(NewsItem item) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.remove(item);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	private Collection<NewsItem> getNewsItems() {
		Collection<NewsItem> resultList = entityManager.createQuery("SELECT FROM " + NewsItem.class.getName())
				.getResultList();
		return resultList;
	}

	@Override
	public NewsItemRemote getNewsItem(Long id) {
		System.out.println("Check for id <" + id + ">");
		NewsItem item = getNewsItembyID(id);
		if (item != null) {
			System.out.println("item != null");
			return newsItemConverter.convertDomainToRemote(item);
		}
		System.out.println("item == null");
		return null;
	}

	public NewsItem getNewsItembyID(final Long id) {
		return entityManager.find(NewsItem.class, KeyFactory.createKey(NewsItem.class.getSimpleName(), id));
	}

}
