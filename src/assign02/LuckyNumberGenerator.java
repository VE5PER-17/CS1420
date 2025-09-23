/**
 * 
 */
package assign02;

import java.util.Scanner;

public class LuckyNumberGenerator {

	/**
	 * CS1420
	 * @author vesper
	 * @version 2025-09-04
	 */
	public static void main(String[] args) {
		//I hate Gradescope.
		//I used a \t and it throw an Err.
		//ew.
		//System.out.println("~ LuckyNumberGenerator ~");

		// handling user input
		Scanner input = new Scanner(System.in);
		String username;
		String opt;
		boolean firstTime = true;

		// prompt
		do {

			if (firstTime) // user friendly prompt
				System.out.println("Please enter your name:");
			else
				System.out.println("Please enter your name(type exit to end):");
			
			username = input.nextLine();
			
			
			if ("exit".equals(username)) {	//close condition
				System.out.println("Good luck!");
				break;
			}

			
			System.out.println("Cool!" + username + ", Now enter your birth month:");
			
			
			//int month = Integer.parseInt(input.nextLine());
			int month;//get valid month
			
			opt = input.nextLine();
			if ("exit".equals(opt)) {	//close condition
				System.out.println("Good luck!");
				break;
			}
			
			try {
			    month = Integer.parseInt(opt);
			} catch (NumberFormatException e) {
			    System.out.println("Invalid month! Please enter a number.");
			    break;
			}
			
			if (month < 1 || month > 12) { // check the month is valid
				System.out.println("Invalid month!");
				break;
			}
			

			
			System.out.println("Last, enter your birth day: ");
			
			opt = input.nextLine();
			if ("exit".equals(opt)) {	//close condition
				System.out.println("Good luck!");
				break;
			}
			
			int date;//get valid date
			try {
				date = Integer.parseInt(opt);
			} catch (NumberFormatException e) {
				System.out.println("Invalid month! Please enter a number.");
			    break;
			}

			if (date < 1 || date > 31) { // check the date is valid
				System.out.println("Invalid date!");
				break;
			}
			
			if (month == 2 && date > 29) System.out.println("Bro lived on another planet where Feb is more than 29 days.");

			// processing
			//initializing the variable used for testing
			String vowel = "aeiouAEIOU";
			String consonantA = "bcdfghjklBCDFGHJKL";
			String consonantB = "mnpqrstvwxyzMNQRSTVWXYZ";

			int asciiCount = 0;

			// #1 adding all ASCII
			String firstChar = String.valueOf(username.charAt(0));
			//System.out.println(firstChar);
			
			if (vowel.contains(firstChar)) { // test out the first char

				int len = username.length();
				int i = 0;

				while (i < len) {
					asciiCount += (int) username.charAt(i);
					// System.out.println(asciiCount);
					i++;
				}
			} else if (consonantA.contains(firstChar)) {

				int len = username.length();
				int i = 0;

				while (i < len) {
					//if (i == 0) asciiCount += username.charAt(i);
					if ((i % 2) == 0) {
						asciiCount += username.charAt(i);
					}
					i++;

				}

			} else if (consonantB.contains(firstChar)) {

				int len = username.length();
				int i = 1;

				while (i < len) {
					if ((i % 2) != 0) {
						asciiCount += (int) username.charAt(i);
					}
					//System.out.println(asciiCount);
					i++;
				}
			}

			// generating lucky number
			//int luckyNumber = 0;
			int optNumb = (asciiCount % date) + month;
			//System.out.println(optNumb);
			
			/*
			// adding each digit
			String optStr = String.valueOf(optNumb);
			int len = optStr.length();

			// opt = optStr.charAt(0);
			int i = 0;
			int swap = 0;

			while (i < len) {
				String number = String.valueOf(optStr.charAt(i));
				swap = Integer.parseInt(number);

				luckyNumber += swap;
				i++;
			}
			*/

			// output
			String mM[] = { "empty", "January", "February", "March", "April", "May", "June", "July", "August",
					"September", "October", "November", "December" };
			System.out.printf("For %s born on %s %d, the lucky number is %d.\n", username, mM[month], date, optNumb);

			firstTime = false;
		} while ("exit".equals(username) == false);

		input.close();

	}

}
