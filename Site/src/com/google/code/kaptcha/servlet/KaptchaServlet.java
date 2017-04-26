package com.google.code.kaptcha.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

/**
 * This servlet uses the settings passed into it via the Producer api.
 * 
 * @author testvoogd@hotmail.com
 * @author jon
 * @author cliffano
 */
@SuppressWarnings("serial")
public class KaptchaServlet extends HttpServlet implements Servlet {
	
	public static final Map<String, String> RESPONSE_HEADERS;
	static {
		synchronized (KaptchaServlet.class) {
			final Map<String, String> _headers = new HashMap<String, String>(0);
			// _headers.put("Expires", 0);
			// resp.setContentType("image/jpeg");
			
			_headers.put("Content-Type", "image/jpeg");
			_headers.put("Expires", "0"); // Set to expire far in the past.
			_headers.put("Cache-Control", "no-store, no-cache, must-revalidate"); // Set standard HTTP/1.1 no-cache headers.
			_headers.put("Cache-Control", "post-check=0, pre-check=0"); // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
			_headers.put("Pragma", "no-cache"); // Set standard HTTP/1.0 no-cache header.

			RESPONSE_HEADERS = Collections.unmodifiableMap(_headers);
		}
	}
	
	private final Properties props = new Properties();

	private Producer kaptchaProducer = null;

	private String sessionKeyValue = null;

	private String sessionKeyDateValue = null; // NOPMD by Iman on 7/28/15 5:50 PM

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(final ServletConfig conf) throws ServletException {
		super.init(conf);

		// Switch off disk based caching.
		ImageIO.setUseCache(false);

		final Enumeration<?> initParams = conf.getInitParameterNames();
		while (initParams.hasMoreElements()) {
			final String key = (String) initParams.nextElement();
			final String value = conf.getInitParameter(key);
			this.props.put(key, value);
		}

		final Config config = new Config(this.props);
		this.kaptchaProducer = config.getProducerImpl();
		this.sessionKeyValue = config.getSessionKey();
		this.sessionKeyDateValue = config.getSessionDate();
	}

	/** */
	@Override()
	public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		for (final String _key: KaptchaServlet.RESPONSE_HEADERS.keySet()) {
			resp.setHeader(_key, KaptchaServlet.RESPONSE_HEADERS.get(_key));
		}

		final String _text = this.kaptchaProducer.createText();
		final HttpSession _session = req.getSession();
		if (null != _session) {
			_session.setAttribute(this.sessionKeyValue, _text);
			_session.setAttribute(this.sessionKeyDateValue, new Date());
			String sessionId = _session.getId();
		    Cookie userCookie = new Cookie("JSESSIONID", sessionId);
		    resp.addCookie(userCookie);
		}

		// create the image with the text
		final BufferedImage bi = this.kaptchaProducer.createImage(_text); // NOPMD by Iman on 7/28/15 5:50 PM
		final ServletOutputStream out = resp.getOutputStream();

		// write the data out
		ImageIO.write(bi, "jpg", out);
	}
}
