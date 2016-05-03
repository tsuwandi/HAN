package main.component;

import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberField extends JTextField{

	public NumberField(){
		final int maxCharacters = 10;
		 ((AbstractDocument)this.getDocument()).setDocumentFilter(new DocumentFilter() {
	            public void replace(FilterBypass fb, int offs, int length,
	                    String str, AttributeSet a) throws BadLocationException {

	                String text = fb.getDocument().getText(0,
	                        fb.getDocument().getLength());
	                text += str;
	                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
	                        && text.matches("^[0-9]+[.]?[0-9]{0,2}$")) {
	                    super.replace(fb, offs, length, str, a);
	                } else {
	                    Toolkit.getDefaultToolkit().beep();
	                }
	            }

	            public void insertString(FilterBypass fb, int offs, String str,
	                    AttributeSet a) throws BadLocationException {

	                String text = fb.getDocument().getText(0,
	                        fb.getDocument().getLength());
	                text += str;
	                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
	                        && text.matches("^[0-9]+[.]?[0-9]{0,2}$")) {
	                    super.insertString(fb, offs, str, a);
	                } else {
	                    Toolkit.getDefaultToolkit().beep();
	                }
	            }
	        });
	}
}