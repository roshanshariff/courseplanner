package ca.ualberta.cs.courseplanner.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;


public class UserChangeEvent extends GwtEvent<UserChangeEvent.Handler> {
	

	public static interface Handler extends EventHandler {
		
		void onUserChange (UserChangeEvent e);

	}
	
	public static final Type<Handler> TYPE = new Type<Handler>();
	

	private final String userId;
	
	private final String password;
	
	private final boolean admin;
	
	private final String substituteUserId;
	
	public UserChangeEvent (String userId, String password, boolean admin, String substituteUserId) {
		super();
		this.userId = userId;
		this.password = password;
		this.admin = admin;
		this.substituteUserId = substituteUserId;
	}
	
	@Override
	protected void dispatch (Handler handler) {
		handler.onUserChange(this);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public String getSubstituteUserId() {
		return substituteUserId;
	}
	
}
