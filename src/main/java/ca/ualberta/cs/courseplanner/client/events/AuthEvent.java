package ca.ualberta.cs.courseplanner.client.events;

import ca.ualberta.cs.courseplanner.client.authentication.AuthInfo;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;


public class AuthEvent extends GwtEvent<AuthEvent.Handler> implements AuthInfo {


	public static interface Handler extends EventHandler {

		void handleAuthEvent (AuthEvent e);

	}

	public static final Type<Handler> TYPE = new Type<Handler>();
	

	private final boolean authenticated;

	private final boolean admin;

	private final String userId;

	private final String password;

	private final String effectiveUserId;

	public AuthEvent (boolean authenticated, boolean admin,
			String userId, String password, String effectiveUserId) {
		super();
		this.authenticated = authenticated;
		this.admin = admin;
		this.userId = userId;
		this.password = password;
		this.effectiveUserId = effectiveUserId;
	}
	
	public AuthEvent (AuthInfo authInfo) {
		this (authInfo.isAuthenticated(), authInfo.isAdmin(), authInfo.getEffectiveUserId(),
				authInfo.getPassword(), authInfo.getEffectiveUserId());
	}

	@Override
	protected void dispatch (Handler handler) { handler.handleAuthEvent(this); }

	@Override
	public Type<Handler> getAssociatedType() { return TYPE; }

	@Override
	public boolean isAuthenticated () { return authenticated; }
	
	@Override
	public boolean isAdmin() { return admin; }

	@Override
	public String getUserId() { return userId; }

	@Override
	public String getPassword() { return password; }

	@Override
	public String getEffectiveUserId() { return effectiveUserId; }

}
