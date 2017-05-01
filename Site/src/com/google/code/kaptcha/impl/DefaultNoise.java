package com.google.code.kaptcha.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;

/**
 * The default implementation of {@link NoiseProducer}, adds a noise on an
 * image.
 */
public class DefaultNoise extends Configurable implements NoiseProducer {
	/**
	 * Draws a noise on the image. The noise curve depends on the factor values.
	 * Noise won't be visible if all factors have the value > 1.0f
	 * 
	 * @param image
	 *            the image to add the noise to
	 * @param factorOne
	 * @param factorTwo
	 * @param factorThree
	 * @param factorFour
	 */
	public void makeNoise(final BufferedImage image, final float factorOne, final float factorTwo, final float factorThree, final float factorFour) {
		final Color _color = getConfig().getNoiseColor();

		// image size
		final int _width = image.getWidth();
		final int _height = image.getHeight();

		// the points where the line changes the stroke and direction
		final Random _rand = new Random();

		// the curve from where the points are taken
		final CubicCurve2D _cc = new CubicCurve2D.Float(_width * factorOne, _height * _rand.nextFloat(), _width * factorTwo, _height * _rand.nextFloat(), _width * factorThree, _height * _rand.nextFloat(), _width * factorFour, _height * _rand.nextFloat());

		// creates an iterator to define the boundary of the flattened curve
		final PathIterator _pi = _cc.getPathIterator(null, 2);
		final Point2D _tmp[] = new Point2D[200]; // NOPMD by Iman on 7/28/15 5:47 PM
		int i = 0; // NOPMD by Iman on 7/28/15 5:46 PM

		// while pi is iterating the curve, adds points to tmp array
		while (!_pi.isDone()) {
			final float[] coords = new float[6]; // NOPMD by Iman on 7/28/15 5:46 PM
			switch (_pi.currentSegment(coords)) { // NOPMD by Iman on 7/28/15 5:46 PM
			case PathIterator.SEG_MOVETO:
			case PathIterator.SEG_LINETO:
				_tmp[i] = new Point2D.Float(coords[0], coords[1]); // NOPMD by Iman on 7/28/15 5:46 PM
			}
			i++; // NOPMD by Iman on 7/28/15 5:47 PM
			_pi.next();
		}

		final Point2D[] _pts = new Point2D[i];
		System.arraycopy(_tmp, 0, _pts, 0, i);

		final Graphics2D _graph = (Graphics2D) image.getGraphics();
		_graph.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

		_graph.setColor(_color);

		// for the maximum 3 point change the stroke and direction
		for (i = 0; i < _pts.length - 1; i++) {
			if (i < 3) {
				_graph.setStroke(new BasicStroke(0.9f * (4 - i))); // NOPMD by Iman on 7/28/15 5:46 PM
			}
			
			_graph.drawLine((int) _pts[i].getX(), (int) _pts[i].getY(), (int) _pts[i + 1].getX(), (int) _pts[i + 1].getY());
		}

		_graph.dispose();
	}
}
