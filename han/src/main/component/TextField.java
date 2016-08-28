package main.component;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class TextField extends JTextField{

	private static final long serialVersionUID = 1L;

	public TextField(){
		super();
		DocumentFilter filter = new UppercaseDocumentFilter();
		((AbstractDocument) getDocument()).setDocumentFilter(filter);
	}
}
