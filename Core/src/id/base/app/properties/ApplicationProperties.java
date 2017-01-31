package id.base.app.properties;

import java.io.File;
import java.util.Properties;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.util.PropertyUtils;

/**
 * 
 * @author Mardy Jonathan
 *
 */
public final class ApplicationProperties {
	
	/**
	 * 
	 */
	private ApplicationProperties() { }

	/**
	 * 
	 */
	private static final PropertyUtils PROPERTIES = new PropertyUtils();

	public static final String PATH = SystemConstant.GLOBAL_PATH;
	public static final String FILE = "application.properties";
	
	/**
	 * 
	 */
	static {
		synchronized (ApplicationProperties.class) {
			final Properties _props = new Properties();

			try {
				final File _propFile = new File(ApplicationProperties.PATH.concat(ApplicationProperties.FILE));
				ApplicationProperties.PROPERTIES.load(_propFile);
			} catch (final Throwable t) {
				throw new SystemException(new ErrorHolder("error load properties"));
			}
		}
	}

	/**
	 * Get property from file 'application.properties' located on context classpath.
	 * @param key : {@link String}, property key.
	 * @return {@link String}, property value.
	 */
	public static final String getProperty(final String key) {
		return ApplicationProperties.PROPERTIES.get(key);
	}
	
}
