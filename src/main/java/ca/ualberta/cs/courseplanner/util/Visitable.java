package ca.ualberta.cs.courseplanner.util;


public interface Visitable <V extends Visitor<V, T>, T extends Visitable<V, T>> {
	
	void accept (V visitor);

}
