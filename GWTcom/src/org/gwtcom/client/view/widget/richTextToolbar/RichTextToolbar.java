package org.gwtcom.client.view.widget.richTextToolbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RichTextToolbar extends Composite {

	public interface CwConstants extends Constants {

		String cwblack();

		String cwblue();

		String cwbold();

		String cwcolor();

		String cwcreateLink();

		String cwfont();

		String cwgreen();

		String cwhr();

		String cwindent();

		String cwinsertImage();

		String cwitalic();

		String cwjustifyCenter();

		String cwjustifyLeft();

		String cwjustifyRight();

		String cwlarge();

		String cwmedium();

		String cwnormal();

		String cwol();

		String cwoutdent();

		String cwred();

		String cwremoveFormat();

		String cwremoveLink();

		String cwsize();

		String cwsmall();

		String cwstrikeThrough();

		String cwsubscript();

		String cwsuperscript();

		String cwul();

		String cwunderline();

		String cwwhite();

		String cwxlarge();

		String cwxsmall();

		String cwxxlarge();

		String cwxxsmall();

		String cwyellow();
	}

	/**
	 * We use an inner EventHandler class to avoid exposing event methods on the RichTextToolbar itself.
	 */
	private class EventHandler implements ClickHandler, ChangeHandler, KeyUpHandler {

		@Override
		public void onChange(ChangeEvent event) {
			Widget sender = (Widget) event.getSource();

			if (sender == backColors) {
				basic.setBackColor(backColors.getValue(backColors.getSelectedIndex()));
				backColors.setSelectedIndex(0);
			} else if (sender == foreColors) {
				basic.setForeColor(foreColors.getValue(foreColors.getSelectedIndex()));
				foreColors.setSelectedIndex(0);
			} else if (sender == fonts) {
				basic.setFontName(fonts.getValue(fonts.getSelectedIndex()));
				fonts.setSelectedIndex(0);
			} else if (sender == fontSizes) {
				basic.setFontSize(fontSizesConstants[fontSizes.getSelectedIndex() - 1]);
				fontSizes.setSelectedIndex(0);
			}
		}

		@Override
		public void onClick(ClickEvent event) {
			Widget sender = (Widget) event.getSource();

			if (sender == bold) {
				basic.toggleBold();
			} else if (sender == italic) {
				basic.toggleItalic();
			} else if (sender == underline) {
				basic.toggleUnderline();
			} else if (sender == subscript) {
				basic.toggleSubscript();
			} else if (sender == superscript) {
				basic.toggleSuperscript();
			} else if (sender == strikethrough) {
				basic.toggleStrikethrough();
			} else if (sender == indent) {
				basic.rightIndent();
			} else if (sender == outdent) {
				basic.leftIndent();
			} else if (sender == justifyLeft) {
				basic.setJustification(RichTextArea.Justification.LEFT);
			} else if (sender == justifyCenter) {
				basic.setJustification(RichTextArea.Justification.CENTER);
			} else if (sender == justifyRight) {
				basic.setJustification(RichTextArea.Justification.RIGHT);
			} else if (sender == insertImage) {
				String url = Window.prompt("Enter an image URL:", "http://");
				if (url != null) {
					basic.insertImage(url);
				}
			} else if (sender == createLink) {
				String url = Window.prompt("Enter a link URL:", "http://");
				if (url != null) {
					basic.createLink(url);
				}
			} else if (sender == removeLink) {
				basic.removeLink();
			} else if (sender == hr) {
				basic.insertHorizontalRule();
			} else if (sender == ol) {
				basic.insertOrderedList();
			} else if (sender == ul) {
				basic.insertUnorderedList();
			} else if (sender == removeFormat) {
				basic.removeFormat();
			} else if (sender == richText) {
				// We use the RichTextArea's onKeyUp event to update the toolbar status.
				// This will catch any cases where the user moves the cursur using the
				// keyboard, or uses one of the browser's built-in keyboard shortcuts.
				updateStatus();
			}
		}

		@Override
		public void onKeyUp(KeyUpEvent event) {
			Widget sender = (Widget) event.getSource();
			if (sender == richText) {
				// We use the RichTextArea's onKeyUp event to update the toolbar status.
				// This will catch any cases where the user moves the cursur using the
				// keyboard, or uses one of the browser's built-in keyboard shortcuts.
				updateStatus();
			}
		}
	}

	private static final RichTextArea.FontSize[] fontSizesConstants = new RichTextArea.FontSize[] {
			RichTextArea.FontSize.XX_SMALL, RichTextArea.FontSize.X_SMALL, RichTextArea.FontSize.SMALL,
			RichTextArea.FontSize.MEDIUM, RichTextArea.FontSize.LARGE, RichTextArea.FontSize.X_LARGE,
			RichTextArea.FontSize.XX_LARGE };

	private final CwConstants strings = (CwConstants) GWT.create(CwConstants.class);
	private static final RichTextToolbarClientBundle richTextToolbarClientBundle = GWT
			.create(RichTextToolbarClientBundle.class);
	private final EventHandler handler = new EventHandler();

	private final RichTextArea richText;
	private final RichTextArea.Formatter basic;

	private final VerticalPanel outer = new VerticalPanel();
	private final HorizontalPanel topPanel = new HorizontalPanel();
	private final HorizontalPanel bottomPanel = new HorizontalPanel();
	private ToggleButton bold;
	private ToggleButton italic;
	private ToggleButton underline;
	private ToggleButton subscript;
	private ToggleButton superscript;
	private ToggleButton strikethrough;
	private PushButton indent;
	private PushButton outdent;
	private PushButton justifyLeft;
	private PushButton justifyCenter;
	private PushButton justifyRight;
	private PushButton hr;
	private PushButton ol;
	private PushButton ul;
	private PushButton insertImage;
	private PushButton createLink;
	private PushButton removeLink;
	private PushButton removeFormat;

	private ListBox backColors;
	private ListBox foreColors;
	private ListBox fonts;
	private ListBox fontSizes;

	/**
	 * Creates a new toolbar that drives the given rich text area.
	 * 
	 * @param richText
	 *           the rich text area to be controlled
	 */
	public RichTextToolbar(RichTextArea richText) {
		this.richText = richText;
		this.basic = richText.getFormatter();

		outer.add(topPanel);
		outer.add(bottomPanel);
		topPanel.setWidth("100%");
		bottomPanel.setWidth("100%");

		initWidget(outer);
		setStyleName("gwt-RichTextToolbar");
		richText.addStyleName("hasRichTextToolbar");

		if (basic != null) {
			topPanel.add(bold = createToggleButton(richTextToolbarClientBundle.bold(), strings.cwbold()));
			topPanel.add(italic = createToggleButton(richTextToolbarClientBundle.italic(), strings.cwitalic()));
			topPanel.add(underline = createToggleButton(richTextToolbarClientBundle.underline(), strings.cwunderline()));
			topPanel.add(subscript = createToggleButton(richTextToolbarClientBundle.subscript(), strings.cwsubscript()));
			topPanel.add(superscript = createToggleButton(richTextToolbarClientBundle.superscript(),
					strings.cwsuperscript()));
			topPanel
					.add(justifyLeft = createPushButton(richTextToolbarClientBundle.justifyLeft(), strings.cwjustifyLeft()));
			topPanel.add(justifyCenter = createPushButton(richTextToolbarClientBundle.justifyCenter(),
					strings.cwjustifyCenter()));
			topPanel.add(justifyRight = createPushButton(richTextToolbarClientBundle.justifyRight(),
					strings.cwjustifyRight()));
			topPanel.add(strikethrough = createToggleButton(richTextToolbarClientBundle.strikeThrough(),
					strings.cwstrikeThrough()));
			topPanel.add(indent = createPushButton(richTextToolbarClientBundle.indent(), strings.cwindent()));
			topPanel.add(outdent = createPushButton(richTextToolbarClientBundle.outdent(), strings.cwoutdent()));
			topPanel.add(hr = createPushButton(richTextToolbarClientBundle.hr(), strings.cwhr()));
			topPanel.add(ol = createPushButton(richTextToolbarClientBundle.ol(), strings.cwol()));
			topPanel.add(ul = createPushButton(richTextToolbarClientBundle.ul(), strings.cwul()));
			topPanel
					.add(insertImage = createPushButton(richTextToolbarClientBundle.insertImage(), strings.cwinsertImage()));
			topPanel.add(createLink = createPushButton(richTextToolbarClientBundle.createLink(), strings.cwcreateLink()));
			topPanel.add(removeLink = createPushButton(richTextToolbarClientBundle.removeLink(), strings.cwremoveLink()));
			topPanel.add(removeFormat = createPushButton(richTextToolbarClientBundle.removeFormat(),
					strings.cwremoveFormat()));
		}

		if (basic != null) {
			bottomPanel.add(backColors = createColorList("Background"));
			bottomPanel.add(foreColors = createColorList("Foreground"));
			bottomPanel.add(fonts = createFontList());
			bottomPanel.add(fontSizes = createFontSizes());

			// We only use these handlers for updating status, so don't hook them up
			// unless at least basic editing is supported.
			richText.addKeyUpHandler(handler);
			richText.addClickHandler(handler);
		}
	}

	private ListBox createColorList(String caption) {
		ListBox lb = new ListBox();
		lb.addChangeHandler(handler);
		lb.setVisibleItemCount(1);

		lb.addItem(caption);
		lb.addItem(strings.cwwhite(), "white");
		lb.addItem(strings.cwblack(), "black");
		lb.addItem(strings.cwred(), "red");
		lb.addItem(strings.cwgreen(), "green");
		lb.addItem(strings.cwyellow(), "yellow");
		lb.addItem(strings.cwblue(), "blue");
		return lb;
	}

	private ListBox createFontList() {
		ListBox lb = new ListBox();
		lb.addChangeHandler(handler);
		lb.setVisibleItemCount(1);

		lb.addItem(strings.cwfont(), "");
		lb.addItem(strings.cwnormal(), "");
		lb.addItem("Times New Roman", "Times New Roman");
		lb.addItem("Arial", "Arial");
		lb.addItem("Courier New", "Courier New");
		lb.addItem("Georgia", "Georgia");
		lb.addItem("Trebuchet", "Trebuchet");
		lb.addItem("Verdana", "Verdana");
		return lb;
	}

	private ListBox createFontSizes() {
		ListBox lb = new ListBox();
		lb.addChangeHandler(handler);
		lb.setVisibleItemCount(1);

		lb.addItem(strings.cwsize());
		lb.addItem(strings.cwxxsmall());
		lb.addItem(strings.cwxsmall());
		lb.addItem(strings.cwsmall());
		lb.addItem(strings.cwmedium());
		lb.addItem(strings.cwlarge());
		lb.addItem(strings.cwxlarge());
		lb.addItem(strings.cwxxlarge());
		return lb;
	}

	private PushButton createPushButton(ImageResource img, String tip) {
		PushButton pb = new PushButton(new Image(img));
		pb.addClickHandler(handler);
		pb.setTitle(tip);
		return pb;
	}

	private ToggleButton createToggleButton(ImageResource img, String tip) {
		ToggleButton tb = new ToggleButton(new Image(img));
		tb.addClickHandler(handler);
		tb.setTitle(tip);
		return tb;
	}

	/**
	 * Updates the status of all the stateful buttons.
	 */
	private void updateStatus() {
		if (basic != null) {
			bold.setDown(basic.isBold());
			italic.setDown(basic.isItalic());
			underline.setDown(basic.isUnderlined());
			subscript.setDown(basic.isSubscript());
			superscript.setDown(basic.isSuperscript());
			strikethrough.setDown(basic.isStrikethrough());
		}
	}
}
