import java.util.Scanner;

public class Input {
	public static String getString(Scanner sc) {
		String value = "";
		if(sc.hasNext())
			value = sc.next();
		return value;
	}
	
	public static int getInt(Scanner sc, int min, int max) {
		int value;
		do {
			value = getInt(sc);
		} while (value < min && value > max); // TODO: this and that
		return value;
	}
	
	public static int getInt(Scanner sc) {
	    while (!sc.hasNextInt()) {
	        System.out.print("*** That's not a number! ***\nEnter your number again: ");
	        sc.next();
	    }
		return sc.nextInt();
	}
}
