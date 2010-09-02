package ca.ualberta.cs.courseplanner.client.intents;


public class SearchCoursesIntent extends Intent<Void, SearchCoursesIntent.Handler> {
	
	public static interface Handler extends Intent.Handler {
		
		void searchCourses (SearchCoursesIntent intent);

	}

	public static final Type<Handler> TYPE = new Type<Handler>();
	
	@Override
	protected void dispatch (Handler handler) {
		handler.searchCourses (this);
	}

	@Override
	public Type<Handler> getAssociatedType () {
		return TYPE;
	}
	
	public SearchCoursesIntent () {
		super();
	}
	
}
