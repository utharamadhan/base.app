package com.google.code.kaptcha.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.google.code.kaptcha.GimpyEngine;

/**
 * {@link FishEyeGimpy} adds fish eye effect with vertical and horizontal lines.
 */
public class FishEyeGimpy implements GimpyEngine {
	/**
	 * Applies distortion by adding fish eye effect and horizontal vertical
	 * lines.
	 * 
	 * @param _ret
	 *            the base image
	 * @return the distorted image
	 */
	public BufferedImage getDistortedImage(final BufferedImage _ret) {
		final Graphics2D _graph = (Graphics2D) _ret.getGraphics(); // NOPMD by Iman on 7/28/15 5:49 PM
		final int _imageHeight = _ret.getHeight();
		final int _imageWidth = _ret.getWidth();

		// want lines put them in a variable so we might configure these later
		final int _horizontalLines = _imageHeight / 7;
		final int _verticalLines = _imageWidth / 7;

		// calculate space between lines
		final int _horizontalGaps = _imageHeight / (_horizontalLines + 1);
		final int _verticalGaps = _imageWidth / (_verticalLines + 1);

		// draw the horizontal stripes
		for (int i = _horizontalGaps; i < _imageHeight; i = i + _horizontalGaps) {
			_graph.setColor(Color.blue);
			_graph.drawLine(0, i, _imageWidth, i);

		}

		// draw the vertical stripes
		for (int i = _verticalGaps; i < _imageWidth; i = i + _verticalGaps) {
			_graph.setColor(Color.red);
			_graph.drawLine(i, 0, i, _imageHeight);

		}

		// create a pixel array of the original image.
		// we need this later to do the operations on..
		final int _pix[] = new int[_imageHeight * _imageWidth]; // NOPMD by Iman on 7/28/15 5:49 PM
		int j = 0; // NOPMD by Iman on 7/28/15 5:48 PM
		for (int j1 = 0; j1 < _imageWidth; j1++) {
			for (int k1 = 0; k1 < _imageHeight; k1++) {
				_pix[j] = _ret.getRGB(j1, k1); // NOPMD by Iman on 7/28/15 5:49 PM
				j++; // NOPMD by Iman on 7/28/15 5:49 PM
			}

		}

		final double _distance = ranInt(_imageWidth / 4, _imageWidth / 3); // NOPMD by Iman on 7/28/15 5:49 PM

		// put the distortion in the (dead) middle
		final int _widthMiddle = _ret.getWidth() / 2; // NOPMD by Iman on 7/28/15 5:49 PM
		final int _heightMiddle = _ret.getHeight() / 2; // NOPMD by Iman on 7/28/15 5:49 PM

		// again iterate over all pixels..
		for (int x = 0; x < _ret.getWidth(); x++) {
			for (int y = 0; y < _ret.getHeight(); y++) {
				final int relX = x - _widthMiddle;
				final int relY = y - _heightMiddle;
				final double d1 = Math.sqrt(relX * relX + relY * relY); // NOPMD by Iman on 7/28/15 5:48 PM
				if (d1 < _distance) {
					final int j2 = _widthMiddle + (int) (((fishEyeFormula(d1 / _distance) * _distance) / d1) * (double) (x - _widthMiddle)); // NOPMD by Iman on 7/28/15 5:48 PM
					final int k2 = _heightMiddle + (int) (((fishEyeFormula(d1 / _distance) * _distance) / d1) * (double) (y - _heightMiddle)); // NOPMD by Iman on 7/28/15 5:48 PM
					_ret.setRGB(x, y, _pix[j2 * _imageHeight + k2]);
				}
			}

		}

		return _ret;
	}

	/**
	 * @param i
	 * @param j
	 * @return
	 */
	private int ranInt(final int i, final int j) { // NOPMD by Iman on 7/28/15 5:48 PM
		final double _d = Math.random(); // NOPMD by Iman on 7/28/15 5:48 PM
		return (int) ((double) i + (double) ((j - i) + 1) * _d);
	}

	/**
	 * implementation of: g(s) = - (3/4)s3 + (3/2)s2 + (1/4)s, with s from 0 to
	 * 1
	 * 
	 * @param s
	 * @return
	 */
	private double fishEyeFormula(final double s) { // NOPMD by Iman on 7/28/15 5:48 PM
		if (s < 0.0D) {
			return 0.0D; // NOPMD by Iman on 7/28/15 5:48 PM
		}
		
		if (s > 1.0D) {
			return s; // NOPMD by Iman on 7/28/15 5:48 PM
		} else {
			return -0.75D * s * s * s + 1.5D * s * s + 0.25D * s;
		}
	}
}
