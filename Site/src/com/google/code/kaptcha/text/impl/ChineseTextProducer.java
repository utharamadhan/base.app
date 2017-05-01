package com.google.code.kaptcha.text.impl;

import java.util.Random;

import com.google.code.kaptcha.text.TextProducer;

/**
 * TextProducer Implementation that will return Chinese characters..
 */
public class ChineseTextProducer implements TextProducer
{
	private final transient String[] simplifiedChineseTexts = new String[]{ // NOPMD by Iman on 7/28/15 5:52 PM
			"åŒ…æ‹¬ç„¦ç‚¹", "æ–°é�“æ¶ˆç‚¹", "æœ�åˆ†ç›®æ�œ", "ç´¢å§“å��é›»", "å­�éƒµä»¶ä¿¡", "ä¸»æ—¨è«‹å›ž", "é›»å­�éƒµä»¶", "çµ¦æˆ‘æ‰€æœ‰", "è¨Žè«–å�€æ˜Ž", "ç™¼è¡¨æ–°æ–‡", "ç« æ­¤è¨Žè«–", "å�€æ‰€æœ‰æ–‡", "ç« å›žä¸»é¡Œ",
			"æ¨¹ç€�è¦½æ�œ"
	};

	/**
	 * @return random Chinese text
	 */
	public String getText()
	{
		return simplifiedChineseTexts[new Random().nextInt(simplifiedChineseTexts.length)];
	}
}
