package com.google.code.kaptcha.util;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Field;

/**
 * This class provides helper methods in parsing configuration values.
 */
public class ConfigHelper {
	/** */
	public Color getColor(final String paramName, final String paramValue, final Color defaultColor) {
		Color color;
		
		if ("".equals(paramValue) || paramValue == null) {
			color = defaultColor;
		} else if (paramValue.indexOf(",") > 0) {
			color = createColorFromCommaSeparatedValues(paramName, paramValue);
		} else {
			color = createColorFromFieldValue(paramName, paramValue);
		}
		
		return color;
	}

	/** */
	public Color createColorFromCommaSeparatedValues(final String paramName, final String paramValue) {
		Color color;
		
		String[] colorValues = paramValue.split(",");
		try {
			int r = Integer.parseInt(colorValues[0]);
			int g = Integer.parseInt(colorValues[1]);
			int b = Integer.parseInt(colorValues[2]);
			if (colorValues.length == 4) {
				int a = Integer.parseInt(colorValues[3]);
				color = new Color(r, g, b, a);
			} else if (colorValues.length == 3) {
				color = new Color(r, g, b);
			} else {
				throw new ConfigException(paramName, paramValue, "Color can only have 3 (RGB) or 4 (RGB with Alpha) values.");
			}
		} catch (final NumberFormatException nfe) {
			throw new ConfigException(paramName, paramValue, nfe);
		} catch (final ArrayIndexOutOfBoundsException aie) {
			throw new ConfigException(paramName, paramValue, aie);
		} catch (final IllegalArgumentException iae) {
			throw new ConfigException(paramName, paramValue, iae);
		}
		
		return color;
	}

	/** */
	public Color createColorFromFieldValue(final String paramName, final String paramValue) {
		Color color;
		
		try {
			Field field = Class.forName("java.awt.Color").getField(paramValue);
			color = (Color) field.get(null);
		} catch (final NoSuchFieldException nsfe) {
			throw new ConfigException(paramName, paramValue, nsfe);
		} catch (final ClassNotFoundException cnfe) {
			throw new ConfigException(paramName, paramValue, cnfe);
		} catch (final IllegalAccessException iae) {
			throw new ConfigException(paramName, paramValue, iae);
		}
		
		return color;
	}

	/** */
	public Object getClassInstance(final String paramName, final String paramValue, final Object defaultInstance, final Config config) {
		Object instance;
		
		if ("".equals(paramValue) || paramValue == null) {
			instance = defaultInstance;
		} else {
			try {
				instance = Class.forName(paramValue).newInstance();
			} catch (final IllegalAccessException iae) {
				throw new ConfigException(paramName, paramValue, iae);
			} catch (final ClassNotFoundException cnfe) {
				throw new ConfigException(paramName, paramValue, cnfe);
			} catch (final InstantiationException ie) {
				throw new ConfigException(paramName, paramValue, ie);
			}
		}

		setConfigurable(instance, config);

		return instance;
	}

	/** */
	public Font[] getFonts(final String paramName, final String paramValue, final int fontSize, final Font[] defaultFonts) {
		Font[] fonts;
		
		if ("".equals(paramValue) || paramValue == null) {
			fonts = defaultFonts;
		} else {
			String[] fontNames = paramValue.split(",");
			fonts = new Font[fontNames.length];
			for (int i = 0; i < fontNames.length; i++) {
				fonts[i] = new Font(fontNames[i], Font.BOLD, fontSize);
			}
		}
		
		return fonts;
	}

	/** */
	public int getPositiveInt(final String paramName, final String paramValue, final int defaultInt) {
		int intValue;
		
		if ("".equals(paramValue) || paramValue == null) {
			intValue = defaultInt;
		} else {
			try {
				intValue = Integer.parseInt(paramValue);
				if (intValue < 1) {
					throw new ConfigException(paramName, paramValue, "Value must be greater than or equals to 1.");
				}
			} catch (final NumberFormatException nfe) {
				throw new ConfigException(paramName, paramValue, nfe);
			}
		}
		
		return intValue;
	}

	/** */
	public char[] getChars(final String paramName, final String paramValue, final char[] defaultChars) {
		char[] chars;
		
		if ("".equals(paramValue) || paramValue == null) {
			chars = defaultChars;
		} else {
			chars = paramValue.toCharArray();
		}
		
		return chars;
	}

	/** */
	public boolean getBoolean(final String paramName, final String paramValue, final boolean defaultValue) {
		boolean booleanValue;
		
		if ("yes".equals(paramValue) || "".equals(paramValue) || paramValue == null) {
			booleanValue = defaultValue;
		} else if ("no".equals(paramValue)) {
			booleanValue = false;
		} else {
			throw new ConfigException(paramName, paramValue, "Value must be either yes or no.");
		}
		
		return booleanValue;
	}

	/** */
	private void setConfigurable(final Object object, final Config config) {
		if (object instanceof Configurable) {
			((Configurable) object).setConfig(config);
		}
	}
}
