package main.component;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.util.StringUtils;

public class NumericField extends TextField implements KeyListener, FocusListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String format;
	String id;
	String actionMethod;
	
	DecimalFormatSymbols decimalFormatSymbols;
	DecimalFormat df;
	NumberValidator numberValidator;
	int maxLenght = 0;
	String txtValue = "";
	int value = 0;
	String numberChars = "0123456789";

	public NumericField(int maxLenght, String format){
		super();
		this.maxLenght = maxLenght;
		decimalFormatSymbols = new DecimalFormatSymbols(new Locale("in", "ID"));
		df = new DecimalFormat(format);
		numberValidator = new NumberValidator(maxLenght, true);
		
		setDocument(numberValidator);
		setColumns(maxLenght);
		df.setDecimalFormatSymbols(decimalFormatSymbols);
		df.setGroupingUsed(true);
		//df.setGroupingSize(3);
		df.setParseIntegerOnly(false);
		setValue(0);
		
		addKeyListener(this);
		addFocusListener(this);
	}
	
	public int getMaxLenght() {
		return maxLenght;
	}

	public void setMaxLenght(int maxLenght) {
		this.maxLenght = maxLenght;
		numberValidator.setMaxCharacter(this.maxLenght);
	}
	
	public int getValue() {
		if(txtValue.contains("."))
			value = Integer.valueOf(txtValue.replaceAll("\\.", ""));
		else
			value = Integer.valueOf(txtValue);
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		txtValue = df.format(value);
		numberChars = "0123456789.";
		numberValidator.setMaxCharacter(maxLenght+1);
		setText(txtValue);
	}
	
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
		this.setFormat(format);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActionMethod() {
		return actionMethod;
	}
	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	@Override
	public void focusGained(FocusEvent paramFocusEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent paramFocusEvent) {
		if(getText().equals("")) {
			setValue(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent paramKeyEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			 if(getText().length() == 0) {
				 setValue(0);
			 }
		}
	}

	@Override
	public void keyReleased(KeyEvent paramKeyEvent) {
		if(getText().length() != 0) {
			if(getText().contains("."))
				txtValue = df.format(Integer.valueOf(getText().replaceAll("\\.", "")));
			else 
				txtValue = df.format(Integer.valueOf(getText()));
			
			if(txtValue.contains(".")) {
				numberValidator.numberChars = "0123456789.";
				int separatorCount = StringUtils.countOccurrencesOf(txtValue, ".");
				numberValidator.setMaxCharacter(maxLenght+separatorCount);
				setText(txtValue);
			} else {
				numberChars = "0123456789";
				numberValidator.setMaxCharacter(maxLenght);
				setText(txtValue);
			}
		}
	}


}
