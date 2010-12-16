package org.gwtcom.client.view.navigation;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.StackLayoutPanel;

public class Shortcuts extends ResizeComposite {

	interface Binder extends UiBinder<StackLayoutPanel, Shortcuts> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField(provided=true)
	PublicMenu publicMenu;
	@UiField(provided=true)
	PrivateMenu privateMenu;

	private final PlaceController _placeController;

	public Shortcuts(PlaceController placeController) {
		_placeController = placeController;
		
		publicMenu = new PublicMenu(_placeController);
		privateMenu = new PrivateMenu(_placeController);
		initWidget(binder.createAndBindUi(this));
		
		initView();
	}
	
	private void initView(){
//		privateMenu.addNavigationMenuChangedHandler(new INavigationMenuChangeEvent() {
//
//			@Override
//			public void onCategoryChange(NavigationMenuChangeEvent event) {
//				// TODO Auto-generated method stub
//				System.out.println("EventType: "+event.getAssociatedType());
//				
//			}});
	
	}

	public void setLoggedIn(UserLoginRemote result) {
		publicMenu.setLoggedIn(result);
		privateMenu.setLoggedIn(result);
	}
	
}