/**
 * 
 */
package assign01;

/**
 * 
 */
public class CoinCalculator {

	/**
	 * @author Vesper
	 * @version 2025-08-21
	 * @latest_version 2025-08-27
	 */
	public static void main(String[] args) {

		// initializing
		int uid = 1609027;

		int dollar;
		int quater; // <= 3
		int dime; // <= 2
		int nickel; // <= 1
		int penny; // <= 4
		
		int opt1;
		int opt2;

		
		// processing

		opt1 = uid % 100; // Storage left pennies
		dollar = (uid - opt1) / 100;

		opt2 = opt1 % 25; // Storage the left pennies without overwritten
		quater = (opt1 - opt2) / 25;

		opt1 = opt2 % 10;
		dime = (opt2 - opt1) / 10;

		opt2 = opt1 % 5;
		nickel = (opt1 - opt2) / 5;

		penny = opt2; // update

		// final output
		System.out.println(uid + " pennies is equivalent to " + dollar + " dollar(s), " + quater + " quarter(s), " + dime + " dime(s), " + nickel + " nickel(s), and " + penny + " penny(ies).");
		
	}

}
