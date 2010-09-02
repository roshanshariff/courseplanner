package ca.ualberta.cs.courseplanner.util;


public class TextUtils {
	
	public static String escapeText (String text) {
	    return text.replaceAll("&", "&amp;")
	    		.replaceAll("\"", "&quot;")
	    		.replaceAll("\'", "&#39;")
	    		.replaceAll("<", "&lt;")
	    		.replaceAll(">", "&gt;");
	}

}
