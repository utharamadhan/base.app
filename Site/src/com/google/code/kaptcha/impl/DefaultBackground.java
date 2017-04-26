package com.google.code.kaptcha.impl;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.util.Configurable;

/**
 * Default implementation of {@link BackgroundProducer}, adds a gradient
 * background to an image. The gradient color is diagonal and made of Color From
 * (top left) and Color To (bottom right).
 */
public class DefaultBackground extends Configurable implements BackgroundProducer {
	/**
	 * @param baseImage
	 *            the base image
	 * @return an image with a gradient background added to the base image.
	 */
	public BufferedImage addBackground(final BufferedImage baseImage) {
		final Color _colorFrom = getConfig().getBackgroundColorFrom();
		final Color _colorTo = getConfig().getBackgroundColorTo();
		final int _width = baseImage.getWidth();
		final int _height = baseImage.getHeight();

		// create an opaque image
		final BufferedImage _ret = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D _graph = (Graphics2D) _ret.getGraphics();
		final RenderingHints _hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		_hints.add(new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY));
		_hints.add(new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));
		_hints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		_graph.setRenderingHints(_hints);
		
		final GradientPaint _paint = new GradientPaint(0, 0, _colorFrom, _width, _height, _colorTo);
		_graph.setPaint(_paint);
		_graph.fill(new Rectangle2D.Double(0, 0, _width, _height));

		// draw the transparent image over the background
		_graph.drawImage(baseImage, 0, 0, null);

		return _ret;
	}
}
