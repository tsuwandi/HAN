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
	Long value = 0L;
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
	
	public Long getValue() {
		if(txtValue.contains("."))
			value = Long.valueOf(txtValue.replaceAll("\\.", ""));
		else
			value = Long.valueOf(txtValue);
		return value;
	}

	public void setValue(Long value) {
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
	
	@Override
	public String getText() {
		String result = super.getText();
		result = result.replaceAll("\\.", "");
		return result;
	}
	
	@Override
	public void setText(String t) {
		if(t.length() != 0) {
			if(t.contains("."))
				txtValue = df.format(Long.valueOf(t.replaceAll("\\.", "")));
			else 
				txtValue = df.format(Long.valueOf(t));
			
			if(txtValue.contains(".")) {
				numberValidator.numberChars = "0123456789.";
				int separatorCount = StringUtils.countOccurrencesOf(txtValue, ".");
				numberValidator.setMaxCharacter(maxLenght+separatorCount);
				super.setText(txtValue);
			} else {
				numberChars = "0123456789";
				numberValidator.setMaxCharacter(maxLenght);
				super.setText(txtValue);
			}
		}
		
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
			setValue(0L);
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
				 setValue(0L);
			 }
		}
	}

	@Override
	public void keyReleased(KeyEvent paramKeyEvent) {
		if(getText().length() != 0) {
			if(getText().contains("."))
				txtValue = df.format(Long.valueOf(getText().replaceAll("\\.", "")));
			else 
				txtValue = df.format(Long.valueOf(getText()));
			
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
