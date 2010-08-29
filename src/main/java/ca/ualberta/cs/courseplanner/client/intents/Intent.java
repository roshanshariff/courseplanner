package ca.ualberta.cs.courseplanner.client.intents;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;


public abstract class Intent <V, H extends Intent.Handler> extends GwtEvent<H> {
	
	public static interface Handler extends EventHandler { }
	
	public static interface Callback <V> {
		
		void onCompleted (V result);
		
		void onCancelled ();
		
	}
	
	protected final Callback<V> callback;
	
	public Intent (Callback<V> callback) {
		this.callback = callback;
	}
	
	public Intent () {
		this(null);
	}
	
	public void completed (V result) {
		if (callback != null) {
			callback.onCompleted(result);
		}
	}
	
	public void cancelled () {
		if (callback != null) {
			callback.onCancelled();
		}
	}

}
