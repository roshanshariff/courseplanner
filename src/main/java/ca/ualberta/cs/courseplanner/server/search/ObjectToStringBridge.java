package ca.ualberta.cs.courseplanner.server.search;

import org.hibernate.search.bridge.StringBridge;


public class ObjectToStringBridge implements StringBridge {

	@Override
	public String objectToString (Object object) {
		return object.toString();
	}

}
