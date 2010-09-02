package ca.ualberta.cs.courseplanner.util;


public interface Visitor <V extends Visitor<V, T>, T extends Visitable<V, T>> {
	
	void visit (T visitable);

}
