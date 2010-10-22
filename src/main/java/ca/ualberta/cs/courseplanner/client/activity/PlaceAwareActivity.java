package ca.ualberta.cs.courseplanner.client.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;


public interface PlaceAwareActivity extends Activity {
	
	boolean setNewPlace (Place place);

}
