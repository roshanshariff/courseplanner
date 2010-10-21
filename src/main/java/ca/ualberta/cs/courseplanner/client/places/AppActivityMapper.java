package ca.ualberta.cs.courseplanner.client.places;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;


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
