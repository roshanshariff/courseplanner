package ca.ualberta.cs.courseplanner.model;


public enum SearchOrdering {
	
	DEFAULT,	// order by relevance, subject, number, id
	SUBJECT,	// sort by subject, number, id
	LEVEL,		// sort by level, subject, number, id
	LEVELREV	// sort by level reversed, subject, number, id
}
