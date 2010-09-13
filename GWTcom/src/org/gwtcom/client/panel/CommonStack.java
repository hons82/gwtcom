package org.gwtcom.client.panel;

import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.event.INavigationMenuChangeEvent;
import org.gwtcom.client.event.NavigationMenuChangeEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * A tree displaying a set of email folders.
 */
public class CommonStack extends Composite {

	/**
	 * Specifies the images that will be bundled for this Composite and specify
	 * that tree's images should also be included in the same bundle.
	 */
	public interface Images extends ClientBundle, Tree.Resources {
		@Source("img/home.png")
		ImageResource drafts();

		@Source("img/home.png")
		ImageResource home();

		@Source("img/home.png")
		ImageResource inbox();

		@Source("img/home.png")
		ImageResource sent();

		@Source("img/home.png")
		ImageResource templates();

		@Source("img/home.png")
		ImageResource trash();

		@Source("img/noimage.png")
		ImageResource treeLeaf();
	}

	private Tree tree;
	private EventBus _eventbus;

	/**
	 * Constructs a new mailboxes widget with a bundle of images.
	 * @param eventbus2 
	 * 
	 * @param images
	 *            a bundle that provides the images for this widget
	 */
	public CommonStack(EventBus eventbus) {
		_eventbus = eventbus;
		
		Images images = GWT.create(Images.class);

		tree = new Tree(images);
		TreeItem root = new TreeItem(imageItemHTML(images.home(), "foo@example.com"));
		tree.addItem(root);

		addImageItem(root, "Inbox", images.inbox());
		addImageItem(root, "Drafts", images.drafts());
		addImageItem(root, "Templates", images.templates());
		addImageItem(root, "Sent", images.sent());
		addImageItem(root, "Trash", images.trash());

		root.setState(true);
		
		
		tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
			
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				NavigationMenuChangeEvent event2 = new NavigationMenuChangeEvent();
			}
		});
		
		initWidget(tree);
	}

	/**
	 * A helper method to simplify adding tree items that have attached images.
	 * {@link #addImageItem(TreeItem, String, ImageResource) code}
	 * 
	 * @param root
	 *            the tree item to which the new item will be added.
	 * @param title
	 *            the text associated with this item.
	 */
	private TreeItem addImageItem(TreeItem root, String title, ImageResource imageProto) {
		TreeItem item = new TreeItem(imageItemHTML(imageProto, title));
		root.addItem(item);
		return item;
	}

	/**
	 * Generates HTML for a tree item with an attached icon.
	 * 
	 * @param imageProto
	 *            the image prototype to use
	 * @param title
	 *            the title of the item
	 * @return the resultant HTML
	 */
	private String imageItemHTML(ImageResource imageProto, String title) {
		return AbstractImagePrototype.create(imageProto).getHTML() + " " + title;
	}
	
	public void addNavigationMenuChangedHandler(INavigationMenuChangeEvent handler){
		_eventbus.addHandler(NavigationMenuChangeEvent.TYPE, handler);
	}
}
