package org.gwtcom.client.view.news;

import org.gwtcom.client.place.NewsListPlace;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite for displaying the details of a news message.
 */
public class NewsItemImpl extends ResizeComposite implements NewsItem {

	interface Binder extends UiBinder<Widget, NewsItemImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	Hidden id;
	@UiField
	PushButton back;
	@UiField
	Element title;
	@UiField
	Element author;
	@UiField
	Element date;
	@UiField
	HTML content;

	private Presenter _presenter;

	public NewsItemImpl() {
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void setData(NewsItemRemote item) {
		System.out.println(">>>>> NewsItem.setData()");
		if (item != null) {
			DateTimeFormat fmt = DateTimeFormat.getFormat("EEE. dd MMM. yyyy HH:mm:ss");

			id.setValue(String.valueOf(item.getId()));
			title.setInnerText(item.getTitle());
			author.setInnerText(item.getAuthor() != null
					&& (item.getAuthor().getSurname() != null || item.getAuthor().getName() != null) ? (item.getAuthor()
					.getSurname() != null ? item.getAuthor().getSurname() : "")
					+ " "
					+ (item.getAuthor().getName() != null ? item.getAuthor().getName() : "") : "<anonymous>");
			date.setInnerHTML(fmt.format(item.getDateAdded()));

			// WARNING: For the purposes of this demo, we're using HTML
			// directly, on
			// the assumption that the "server" would have appropriately
			// scrubbed
			// the
			// HTML. Failure to do so would open your application to XSS
			// attacks.
			content
					.setHTML("Die Wettbewerbskommission der Schweiz (Weko) nimmt Kooperationsvereinbarungen zwischen dem führenden Telekommunikationsunternehmen Swisscom und örtlichen Stromversorgern beim Bau von Glasfasernetzen unter die Lupe. Die Swisscom hatte Mitte 2009 angekündigt, in Ballungsräumen mit Fibre-to-the-Home (FTTH) künftig voll auf Glasfaser zu setzen. Bis 2015 will der Konzern die Städte Zürich, Genf, Basel, St. Gallen, Bern, Lausanne und den Kanton Freiburg flächendeckend mit FTTH versorgen. Dabei setzt die Swisscom nicht nur auf selbst installierte Glasfaserinfrastrukturen, sondern das Unternehmen will nach Möglichkeit auch Infrastrukturen nutzen, die bereits von örtlichen Elektrizitätsversorgern aufgebaut wurden, die den Ausbau eigener Glasfasernetze zuletzt stark vorangetrieben hatten. Und dies ist Wettbewerbern ein Dorn im Auge. So reichte etwa der Verband der Kabelnetzbetreiber, Swisscable, im Mai 2010 Beschwerde bei der Weko wegen eines Kooperationsvertrages zwischen der Stadt St. Gallen und Swisscom ein. Der Vorwurf: Marktmissbrauch. Weko-Angaben zufolge gingen in den vergangenen Monaten noch weitere Meldungen zu Kooperationen zwischen Elektrizitätsversorgern und der Swisscom bei der Wettbewerbsbehörde ein. Als erste Stadt habe St. Gallen einzelne Klauseln ihres Kooperationsvertrages im Rahmen eines Widerspruchsverfahrens zur Überprüfung gemeldet, weshalb diese Kooperation auch als erste beurteilt werde. Bei diesen Vertragsklauseln sehe die Weko teilweise 'wettbewerbsrechtliche Probleme', weshalb sie jetzt eine Vorabklärung eröffnet habe. Die ausgehandelten Vertragsklauseln, so die Weko, 'könnten gemäß einer ersten Einschätzung den Wettbewerb nachhaltig beschränken und langfristig zu einer Monopolisierung der Glasfasernetzinfrastruktur führen'. Ins Visier nehmen die Wettbewerbshüter auch die geplante Gründung eines Gemeinschaftsunternehmens zwischen der Swisscom und dem Energieversorger Groupe E zum Aufbau und Betrieb einer gemeinsamen Glasfaserinfrastruktur im Kanton Freiburg. Hier bestünden Anhaltspunkte, dass das Gemeinschaftsunternehmen 'eine marktbeherrschende Stellung begründet oder verstärkt'. (pmz) ");
		} else {
			id.setValue(String.valueOf(-1));
			title.setInnerText("<empty>");
			author.setInnerText("<empty>");
			date.setInnerHTML("<empty>");

			content.setHTML("");
		}
	}

	@Override
	public HasClickHandlers getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@UiHandler("back")
	public void onClickBack(ClickEvent e) {
		_presenter.goTo(new NewsListPlace());
	}

	@Override
	public void setPresenter(Presenter presenter) {
		_presenter = presenter;
	}
}