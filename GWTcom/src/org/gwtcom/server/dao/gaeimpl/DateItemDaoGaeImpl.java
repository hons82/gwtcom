package org.gwtcom.server.dao.gaeimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtcom.server.converter.gaeimpl.DateItemConverter;
import org.gwtcom.server.dao.DateItemDao;
import org.gwtcom.server.dao.UserLoginDao;
import org.gwtcom.server.domain.DateItem;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.DateItemRemote;
import org.gwtcom.shared.UserLoginRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

@Repository("DateItemDao")
public class DateItemDaoGaeImpl extends GenericDaoGaeImpl<DateItem, String> implements DateItemDao {

	@Autowired
	private DateItemConverter dateItemConverter;
	@Autowired
	protected UserLoginDao _userLoginDao;

	public DateItemDaoGaeImpl() {
		super(DateItem.class);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<DateItemRemote> getPublicDates() {
		List<DateItemRemote> ret = new ArrayList<DateItemRemote>();
		if (retrieveAll().size()<1) {
				createSampleDate();
		}
		//
		for (DateItem item : retrieveAll()) {
			ret.add(dateItemConverter.convertDomainToRemote(item));
		}
		return ret;
	}

	@Override
	public DateItemRemote getDateItem(String id) {
		System.out.println("Check for id <" + id + ">");
		DateItem item = retrieve(id);
		if (item != null) {
			System.out.println("item != null");
			return dateItemConverter.convertDomainToRemote(item);
		}
		System.out.println("item == null");
		return null;
	}

	private void createSampleDate() {
		DateItem newDateItem = new DateItem();
		newDateItem.setAuthor(null);
		newDateItem.setTitle("Die Auswirkungen der Sonnenstrahlen auf das Liebesleben der Pflastersteine "
				+ System.currentTimeMillis());
		newDateItem.setDateAdded(new Date(System.currentTimeMillis()));
		newDateItem
				.setContent(new Text(
						"Die Wettbewerbskommission der Schweiz (Weko) nimmt Kooperationsvereinbarungen zwischen dem führenden Telekommunikationsunternehmen Swisscom und örtlichen Stromversorgern beim Bau von Glasfasernetzen unter die Lupe. Die Swisscom hatte Mitte 2009 angekündigt, in Ballungsräumen mit Fibre-to-the-Home (FTTH) künftig voll auf Glasfaser zu setzen. Bis 2015 will der Konzern die Städte Zürich, Genf, Basel, St. Gallen, Bern, Lausanne und den Kanton Freiburg flächendeckend mit FTTH versorgen. Dabei setzt die Swisscom nicht nur auf selbst installierte Glasfaserinfrastrukturen, sondern das Unternehmen will nach Möglichkeit auch Infrastrukturen nutzen, die bereits von örtlichen Elektrizitätsversorgern aufgebaut wurden, die den Ausbau eigener Glasfasernetze zuletzt stark vorangetrieben hatten. Und dies ist Wettbewerbern ein Dorn im Auge. So reichte etwa der Verband der Kabelnetzbetreiber, Swisscable, im Mai 2010 Beschwerde bei der Weko wegen eines Kooperationsvertrages zwischen der Stadt St. Gallen und Swisscom ein. Der Vorwurf: Marktmissbrauch. Weko-Angaben zufolge gingen in den vergangenen Monaten noch weitere Meldungen zu Kooperationen zwischen Elektrizitätsversorgern und der Swisscom bei der Wettbewerbsbehörde ein. Als erste Stadt habe St. Gallen einzelne Klauseln ihres Kooperationsvertrages im Rahmen eines Widerspruchsverfahrens zur Überprüfung gemeldet, weshalb diese Kooperation auch als erste beurteilt werde. Bei diesen Vertragsklauseln sehe die Weko teilweise 'wettbewerbsrechtliche Probleme', weshalb sie jetzt eine Vorabklärung eröffnet habe. Die ausgehandelten Vertragsklauseln, so die Weko, 'könnten gemäß einer ersten Einschätzung den Wettbewerb nachhaltig beschränken und langfristig zu einer Monopolisierung der Glasfasernetzinfrastruktur führen'. Ins Visier nehmen die Wettbewerbshüter auch die geplante Gründung eines Gemeinschaftsunternehmens zwischen der Swisscom und dem Energieversorger Groupe E zum Aufbau und Betrieb einer gemeinsamen Glasfaserinfrastruktur im Kanton Freiburg. Hier bestünden Anhaltspunkte, dass das Gemeinschaftsunternehmen 'eine marktbeherrschende Stellung begründet oder verstärkt'. (pmz) "));
		saveOrUpdate(newDateItem);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean deleteDateItem(DateItemRemote item, UserLoginRemote loggedInUserRemote) {
		UserProfile loggedInUserProfile = _userLoginDao.getUserProfile(loggedInUserRemote);
		delete(item.getId(), KeyFactory.keyToString(loggedInUserProfile.getId()));
		return true;
	}

}
