package com.google.code.kaptcha.util;

/**
 * {@link ConfigException} is used to indicate an error in configuration
 * properties.
 */
public class ConfigException extends RuntimeException {
	
	private static final long serialVersionUID = 6937416954897707291L;

	public ConfigException(final String paramName, final String paramValue, final Throwable cause) {
		super("Invalid value '" + paramValue + "' for config parameter '" + paramName + "'.", cause);
	}

	public ConfigException(final String paramName, final String paramValue, final String message) {
		super("Invalid value '" + paramValue + "' for config parameter '" + paramName + "'. " + message);
	}
}
