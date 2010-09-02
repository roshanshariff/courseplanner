package ca.ualberta.cs.courseplanner.client.places;

import com.google.gwt.app.place.Activity;
import com.google.gwt.app.place.ActivityMapper;
import com.google.gwt.app.place.Place;


public class AppActivityMapper implements ActivityMapper, AppPlaceVisitor {
	
	private Activity activity;

	@Override
	public void visit (AppPlace visitable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Activity getActivity (Place place) {
		if (place instanceof AppPlace) {
			visit ((AppPlace) place);
			return activity;
		}
		else {
			return null;
		}
	}

}
