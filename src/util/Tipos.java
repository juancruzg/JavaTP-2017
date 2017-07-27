package util;

public class Tipos {
	public static int toInt(String string) {
		int returnValue;
		
		try {
			returnValue = Integer.parseInt(string);
		}
		catch (NumberFormatException e) {
			returnValue = 0;
		}
		
		return returnValue;
	}
}
