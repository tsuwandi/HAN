package main.component;

public class NumberFormat {

	public static String onTypeNum(int numLength, String value) {
		String words = null;

		if (numLength <= 10)
			words = String.format("%04d", Integer.valueOf(value));
		else if (numLength > 10 && numLength <= 100)
			words = String.format("%03d", Integer.valueOf(value));
		else
			words = String.format("%02d", Integer.valueOf(value));

		return words;
	}
}
