package com.google.code.kaptcha.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.text.TextProducer;
import com.google.code.kaptcha.text.WordRenderer;
import com.google.code.kaptcha.util.Configurable;

/**
 * Default {@link Producer} implementation which draws a captcha image using
 * {@link WordRenderer}, {@link GimpyEngine}, {@link BackgroundProducer}. Text
 * creation uses {@link TextProducer}.
 */
public class DefaultKaptcha extends Configurable implements Producer {
	
	private transient int width = 200;
	private transient int height = 50;

	/**
	 * Create an image which will have written a distorted text.
	 * 
	 * @param text
	 *            the distorted characters
	 * @return image with the text
	 */
	public BufferedImage createImage(final String text) {
		final WordRenderer _wordRenderer = getConfig().getWordRendererImpl();
		final GimpyEngine _gimpyEngine = getConfig().getObscurificatorImpl();
		final BackgroundProducer _bgProd = getConfig().getBackgroundImpl();
		final boolean _isBorderDrawn = getConfig().isBorderDrawn();
		this.width = getConfig().getWidth();
		this.height = getConfig().getHeight();

		BufferedImage _ret = _wordRenderer.renderWord(text, width, height); // NOPMD by Iman on 7/28/15 5:45 PM
		_ret = _gimpyEngine.getDistortedImage(_ret);
		_ret = _bgProd.addBackground(_ret);
		final Graphics2D graphics = _ret.createGraphics(); // NOPMD by Iman on 7/28/15 5:45 PM
		if (_isBorderDrawn) {
			drawBox(graphics);
		}
		
		return _ret;
	}

	private void drawBox(final Graphics2D graphics) {
		final Color _borderColor = getConfig().getBorderColor();
		final int _borderThickness = getConfig().getBorderThickness();
		graphics.setColor(_borderColor);

		if (_borderThickness != 1) {
			final BasicStroke _stroke = new BasicStroke((float) _borderThickness);
			graphics.setStroke(_stroke);
		}

		final Line2D _line1 = new Line2D.Double(0, 0, 0, width);
		graphics.draw(_line1);
		
		Line2D _line2 = new Line2D.Double(0, 0, width, 0);
		graphics.draw(_line2);
		
		_line2 = new Line2D.Double(0, height - 1, width, height - 1);
		graphics.draw(_line2);
		
		_line2 = new Line2D.Double(width - 1, height - 1, width - 1, 0);
		graphics.draw(_line2);
	}

	/**
	 * @return the text to be drawn
	 */
	public String createText() {
		return getConfig().getTextProducerImpl().getText();
	}
}
