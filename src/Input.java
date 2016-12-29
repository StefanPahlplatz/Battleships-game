import java.util.Scanner;

public class Input {
	/**
	 * Asks the user to input a string
	 * @param sc Scanner
	 * @return user input as String
	 */
	public static String getString(Scanner sc) {
		String value = "";
		if(sc.hasNext())
			value = sc.next();
		return value;
	}
	
	/**
	 * Asks the user to input an integer within the specified range
	 * @param sc Scanner
	 * @param min value to be accepted
	 * @param max value to be accepted
	 * @return integer
	 */
	public static int getInt(Scanner sc, int min, int max) {
		int value;
		do {
			value = getInt(sc);
		} while (value < min && value > max); // TODO: this and that
		return value;
	}
	
	/**
	 * Asks the user to input an integer
	 * @param sc Scanner
	 * @return integer
	 */
	public static int getInt(Scanner sc) {
	    while (!sc.hasNextInt()) {
	        System.out.print("*** That's not a number! ***\nEnter your number again: ");
	        sc.next();
	    }
		return sc.nextInt();
	}
}
