package com.google.code.kaptcha.text.impl;

import java.util.Random;

import com.google.code.kaptcha.text.TextProducer;
import com.google.code.kaptcha.util.Configurable;

/**
 * {@link DefaultTextCreator} creates random text from an array of characters
 * with specified length.
 */
public class DefaultTextCreator extends Configurable implements TextProducer {
	/**
	 * @return the random text
	 */
	public String getText() {
		final int _len = getConfig().getTextProducerCharLength();
		final char[] _chars = getConfig().getTextProducerCharString();
		final Random _rand = new Random();
		final StringBuilder _text = new StringBuilder(0);
		for (int i = 0; i < _len; i++) {
			_text.append(_chars[_rand.nextInt(_chars.length)]);
		}

		return _text.toString();
	}
}
