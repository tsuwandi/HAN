/*
 *
 * Copyright (c) 2012-2014, PT Ace Global Consulting and Integration (AGCI)
 * All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing,
 * redistribution and use in source and binary forms, with or without
 * modification, is not permitted.
 *
 */
package main.component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberValidator extends PlainDocument {
	/**
	   * 
	   */
	private static final long serialVersionUID = 1L;
	private int maxCharacter;
	private boolean numberOnly;
	public String numberChars = "0123456789";
	
	public NumberValidator() {
		this(-1, false);
	}

	public NumberValidator(int maxCharacter, boolean numberOnly) {
		this.maxCharacter = maxCharacter;
		this.numberOnly = numberOnly;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if (numberOnly) {
			if (maxCharacter == -1) {
				if (checkString(str)) {
					super.insertString(offs, str, a);
				}
			} else {
				int oldTextLength = getLength();
				int newTextLength = str.length();
				if ((oldTextLength + newTextLength) <= maxCharacter) {
					if (checkString(str)) {
						super.insertString(offs, str, a);
					}
				}
			}
		} else {
			if (maxCharacter == -1) {
				super.insertString(offs, str, a);
			} else {
				int oldTextLength = getLength();
				int newTextLength = str.length();
				if ((oldTextLength + newTextLength) <= maxCharacter) {
					super.insertString(offs, str, a);
				}
			}
		}
	}

	private boolean checkString(String input) {
		boolean result = false;
		for (int i = 0; i < input.length(); i++) {
			if (numberChars.indexOf(input.charAt(i)) == -1) {
				result = false;
				break;
			} else {
				result = true;
			}
		}
		return result;
	}
	
	public int getMaxCharacter() {
		return maxCharacter;
	}

	public void setMaxCharacter(int maxCharacter) {
		this.maxCharacter = maxCharacter;
	}

	public boolean isNumberOnly() {
		return numberOnly;
	}

	public void setNumberOnly(boolean numberOnly) {
		this.numberOnly = numberOnly;
	}
}