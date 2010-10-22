package ca.ualberta.cs.courseplanner.client.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;


public class PlaceAwareActivityMapper implements ActivityMapper {
	
	private final ActivityMapper wrapped;
	
	private Activity lastActivity;
	
	public PlaceAwareActivityMapper (ActivityMapper wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public Activity getActivity (Place place) {
		if (lastActivity == null
				|| !(lastActivity instanceof PlaceAwareActivity)
				|| !((PlaceAwareActivity)lastActivity).setNewPlace(place)) {
			lastActivity = wrapped.getActivity(place);
		}
		return lastActivity;
	}

}
