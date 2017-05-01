package com.google.code.kaptcha.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;
import com.jhlabs.image.RippleFilter;
import com.jhlabs.image.TransformFilter;
import com.jhlabs.image.WaterFilter;

/**
 * {@link WaterRipple} adds water ripple effect to an image.
 */
public class WaterRipple extends Configurable implements GimpyEngine {
	/**
	 * Applies distortion by adding water ripple effect.
	 * 
	 * @param baseImage
	 *            the base image
	 * @return the distorted image
	 */
	public BufferedImage getDistortedImage(final BufferedImage baseImage) {
		final NoiseProducer _noiseProducer = getConfig().getNoiseImpl();
		final BufferedImage _ret = new BufferedImage(baseImage.getWidth(), baseImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		final Graphics2D _graphics = (Graphics2D) _ret.getGraphics();
		final RippleFilter _rippleFilter = new RippleFilter();
		_rippleFilter.setWaveType(RippleFilter.SINE);
		_rippleFilter.setXAmplitude(2.6f);
		_rippleFilter.setYAmplitude(1.7f);
		_rippleFilter.setXWavelength(15);
		_rippleFilter.setYWavelength(5);
		_rippleFilter.setEdgeAction(TransformFilter.NEAREST_NEIGHBOUR);

		final WaterFilter _waterFilter = new WaterFilter();
		_waterFilter.setAmplitude(1.5f);
		_waterFilter.setPhase(10);
		_waterFilter.setWavelength(2);

		BufferedImage _effectImage = _waterFilter.filter(baseImage, null);
		_effectImage = _rippleFilter.filter(_effectImage, null);

		_graphics.drawImage(_effectImage, 0, 0, null, null);
		_graphics.dispose();

		_noiseProducer.makeNoise(_ret, .1f, .1f, .25f, .25f);
		_noiseProducer.makeNoise(_ret, .1f, .25f, .5f, .9f);
		return _ret;
	}
}
