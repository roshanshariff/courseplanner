package ca.ualberta.cs.courseplanner.client.places;

import ca.ualberta.cs.courseplanner.util.Visitable;

import com.google.gwt.app.place.Place;


public abstract class AppPlace extends Place implements Visitable<AppPlaceVisitor, AppPlace> {

}