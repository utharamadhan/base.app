package com.google.code.kaptcha.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;
import com.jhlabs.image.RippleFilter;
import com.jhlabs.image.ShadowFilter;
import com.jhlabs.image.TransformFilter;

/**
 * {@link ShadowGimpy} adds shadow to the text on the image and two noises.
 */
public class ShadowGimpy extends Configurable implements GimpyEngine {
	/**
	 * Applies distortion by adding shadow to the text and also two noises.
	 * 
	 * @param baseImage
	 *            the base image
	 * @return the distorted image
	 */
	public BufferedImage getDistortedImage(final BufferedImage baseImage) {
		final NoiseProducer _noiseProducer = getConfig().getNoiseImpl();
		final BufferedImage _ret = new BufferedImage(baseImage.getWidth(), baseImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		final Graphics2D _graph = (Graphics2D) _ret.getGraphics();
		final ShadowFilter _shadowFilter = new ShadowFilter();
		_shadowFilter.setRadius(10);
		_shadowFilter.setDistance(5);
		_shadowFilter.setOpacity(1);

		final Random _rand = new Random();
		final RippleFilter _rippleFilter = new RippleFilter();
		_rippleFilter.setWaveType(RippleFilter.SINE);
		_rippleFilter.setXAmplitude(7.6f);
		_rippleFilter.setYAmplitude(_rand.nextFloat() + 1.0f);
		_rippleFilter.setXWavelength(_rand.nextInt(7) + 8);
		_rippleFilter.setYWavelength(_rand.nextInt(3) + 2);
		_rippleFilter.setEdgeAction(TransformFilter.BILINEAR);

		BufferedImage _effectImage = _rippleFilter.filter(baseImage, null);
		_effectImage = _shadowFilter.filter(_effectImage, null);

		_graph.drawImage(_effectImage, 0, 0, null, null);
		_graph.dispose();

		// draw lines over the image and/or text
		_noiseProducer.makeNoise(_ret, .1f, .1f, .25f, .25f);
		_noiseProducer.makeNoise(_ret, .1f, .25f, .5f, .9f);

		return _ret;
	}
}
