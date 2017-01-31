package id.base.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author Mardy Jonathan
 *
 */
public class PropertyUtils extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6205882640797765941L;
	
	public PropertyUtils() { }

	public final void load(final File propsFile) {
		try {
			final InputStream _inStream = new FileInputStream(propsFile);
			this.load(_inStream);
			_inStream.close();
		} catch (final Throwable t) {
			t.getMessage();
		}
	}

	public final void load(final InputStream inStream) {
		try {
			final Properties _props = new Properties();
			_props.load(inStream);

			this.putAll(_props);
			_props.clear();
			inStream.close();
		} catch (final Throwable t) {
			t.getMessage();
		}
	}

	@Override()
	public final String remove(final Object key) {
		throw new UnsupportedOperationException();
	}
	
	@Override()
	public final void clear() {
		throw new UnsupportedOperationException();
	}
	
	@Override()
	public final void putAll(final Map<? extends String, ? extends String> map) {
		if (null == map || map.isEmpty()) {
			return;
		}
		
		synchronized (this) {
			try {
				super.putAll(map);
			} catch (final Throwable t) {
				t.getMessage();
			}
			
			this.replaceVars();
		}
	}

	public final void putAll(final Properties props) {
		if (null == props || props.isEmpty()) {
			return;
		}
		
		synchronized (this) {
			synchronized (props) {
				for (final Object _oKey: props.keySet()) {
					if (_oKey instanceof String) {
						final String _key = (String) _oKey;
						final String _value = props.getProperty(_key);
						
						if (null != _key && null != _value) {
							super.put(_key, _value);
						}
					}
				}
			}
			
			this.replaceVars();
		}
	}

	private final synchronized void replaceVars() {
		final Set<String> _set = this.keySet();
		for (final String _key: _set) {
			String _value = this.get(_key);
			for (final String _key2: this.keySet()) {
				final String _cons = "${" + _key2 + "}";
				_value = _value.replace(_cons, this.get(_key2));
				super.put(_key, _value);
			}
		}
	}

	public String[] getAllKeys() {
		final String[] _ret = new String[this.size()];
		
		int i = 0;
		for (final String _key: this.keySet()) {
			_ret[i] = _key;
			i++;
		}
		
		return _ret;
	}
}
