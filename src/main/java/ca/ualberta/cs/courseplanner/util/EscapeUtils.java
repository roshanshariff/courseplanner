package ca.ualberta.cs.courseplanner.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class EscapeUtils {
	
	/** Makes text safe to display as HTML, by converting special
	 *  characters into entities.
	 *  
	 * @param str Text containing possible special characters.
	 * @return Text that can be safely written using innerHTML
	 */
	public static String escapeHtml (String str) {
	    return str.replaceAll("&", "&amp;")
	    		.replaceAll("\"", "&quot;")
	    		.replaceAll("\'", "&#39;")
	    		.replaceAll("<", "&lt;")
	    		.replaceAll(">", "&gt;");
	}
	
	/** Guarantees that a string will not contain the / character, and
	 * is exactly inverted by #unescapeSlash(String).
	 * @param str Arbitrary string
	 * @return String without / characters
	 */
	public static String escapeSlash (String str) {
		return str.replaceAll("%", "%25").replaceAll("/", "%2F");
	}
	
	/** Inverts the operation of #escapeSlash(String).
	 * @param str String produced by #escapeSlash(String str).
	 * @return Original string str.
	 */
	public static String unescapeSlash (String str) {
		return str.replaceAll("%2F", "/").replaceAll("%25", "%");
	}

	public static String encodeHistoryToken (String defaultKey, Map<String,String> entries) {
		
		StringBuilder result = new StringBuilder();

		String defaultValue = entries.get(defaultKey);
		if (defaultValue != null) result.append(escapeSlash(defaultValue));

		for (Map.Entry<String, String> entry: entries.entrySet()) {
			if (!entry.getKey().equals(defaultKey)) {
				result.append('/');
				if (entry.getKey() != null) result.append(entry.getKey());
				result.append(':');
				if (entry.getValue() != null) result.append(escapeSlash(entry.getValue()));
			}
		}
		
		return result.toString();
	}
	
	public static Map<String, String> decodeHistoryToken (String defaultKey, String historyToken) {
		
		String[] tokens = historyToken.split("/");
		
		if (tokens.length == 0) {
			return Collections.<String,String>emptyMap();
		}
		else {

			Map<String, String> result = new HashMap<String, String>();
			result.put(defaultKey, unescapeSlash(tokens[0]).trim());

			for (int i = 1; i < tokens.length; ++i) {
				String token = tokens[i];
				if (!token.equals("")) {
					int colon = token.indexOf(':');
					if (colon < 0) {
						result.put(token, "");
					}
					else {
						String key = token.substring(0, colon);
						String value = token.substring(colon+1);
						result.put(key, unescapeSlash(value));
					}
				}
			}

			return result;
		}
	}

}
