package id.base.app;

import java.nio.charset.Charset;

/**
 * 
 * @author Iman
 *
 */
public class CharEncConsts {
	
	private static enum CharsetType {
		ASCII("ASCII"),
		ANSI("ANSI"),
		UTF_8("UTF-8"),
		UTF_16("UTF-16"),
		ISO_8859_1("ISO-8859-1");
		
		private final String alias;
		
		private CharsetType(final String alias) {
			this.alias = alias;
		}
		
	};
	
	public static final String DEFAULT_CHARSET_NAME = CharsetType.UTF_8.alias;
	
	public static final Charset DEFAULT_CHARSET = Charset.forName(CharEncConsts.DEFAULT_CHARSET_NAME);

	
}
