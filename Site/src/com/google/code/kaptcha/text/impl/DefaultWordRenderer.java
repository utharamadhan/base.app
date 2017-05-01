package com.google.code.kaptcha.text.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.google.code.kaptcha.text.WordRenderer;
import com.google.code.kaptcha.util.Configurable;

/**
 * The default implementation of {@link WordRenderer}, creates an image with a
 * word rendered on it.
 */
public class DefaultWordRenderer extends Configurable implements WordRenderer {
	/**
	 * Renders a word to an image.
	 * 
	 * @param word
	 *            The word to be rendered.
	 * @param width
	 *            The width of the image to be created.
	 * @param height
	 *            The height of the image to be created.
	 * @return The BufferedImage created from the word.
	 */
	public BufferedImage renderWord(final String word, final int width, final int height) {
		final int _fontSize = getConfig().getTextProducerFontSize();
		final Font[] _fonts = getConfig().getTextProducerFonts(_fontSize);
		final Color _color = getConfig().getTextProducerFontColor();
		final int _charSpace = getConfig().getTextProducerCharSpace();
		final BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D _g2D = ret.createGraphics();
		_g2D.setColor(_color);

		final RenderingHints _hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		_hints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		_g2D.setRenderingHints(_hints);

		final FontRenderContext _frc = _g2D.getFontRenderContext();
		final Random _random = new Random();
		final int _startPosY = (height - _fontSize) / 5 + _fontSize;
		final char[] _wordChars = word.toCharArray();
		final Font[] _chosenFonts = new Font[_wordChars.length];
		final int[] _charWidths = new int[_wordChars.length];
		int _widthNeeded = 0;
		for (int i = 0; i < _wordChars.length; i++) {
			_chosenFonts[i] = _fonts[_random.nextInt(_fonts.length)];

			final char[] _charToDraw = new char[] { _wordChars[i] };
			final GlyphVector _gv = _chosenFonts[i].createGlyphVector(_frc, _charToDraw);
			_charWidths[i] = (int) _gv.getVisualBounds().getWidth();
			if (i > 0) {
				_widthNeeded = _widthNeeded + 2;
			}
			_widthNeeded = _widthNeeded + _charWidths[i];
		}

		int _startPosX = (width - _widthNeeded) / 2;
		for (int i = 0; i < _wordChars.length; i++) {
			_g2D.setFont(_chosenFonts[i]);
			final char[] _charToDraw = new char[] { _wordChars[i] };
			_g2D.drawChars(_charToDraw, 0, _charToDraw.length, _startPosX, _startPosY);
			_startPosX = _startPosX + (int) _charWidths[i] + _charSpace;
		}

		return ret;
	}
}
