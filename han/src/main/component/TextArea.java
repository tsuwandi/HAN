package main.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class TextArea extends JScrollPane{
	JTextArea textArea;
	public TextArea(){
		super();
		textArea = new JTextArea();
		DocumentFilter filter = new UppercaseDocumentFilter();
		((AbstractDocument) textArea.getDocument()).setDocumentFilter(filter);
		textArea.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		setViewportView(textArea);
		setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	
}
