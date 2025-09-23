
package assign04;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Vesper
 * @version 2025-09-18
 */
public class MethodPractice {

    /**
     * Converts centimeters to inches, rounding down to the nearest inch.
     *
     * @param centimeters The amount in centimeters (non-negative).
     * @return The equivalent amount in inches, rounded down, as an int.
     */
    public static int centimetersToInches(double centimeters) {
        return (int) (centimeters / (double) 2.54);
    }
    
    
    

    /**
     * encrypts a message using a shift cipher
     *
     * @param message The string message to encrypt.
     * @param shift   The amount to shift each character (non-negative).
     * @return The encrypted string message.
     */
    public static String shiftCipher(String message, int shift) {
        StringBuilder encrypted = new StringBuilder();
        
        int range = 126 - 32 + 1; // 95 characters from space to ~

        for (int i = 0; i < message.length(); i++) {
        	
            char ch = message.charAt(i);
            
            int charCode = (int) ch;  //force type convert

            if (charCode >= 32 && charCode <= 126) {
                
                int shiftedCode = charCode + shift;
                
                shiftedCode = ((shiftedCode - 32) % range + range) % range + 32;
                encrypted.append((char) shiftedCode);
                
            } else {
                
                encrypted.append(ch);
            }
        }
        
        return encrypted.toString();
    }

    /**
     * counts the number of positive numbers
     *
     * @param input Scanner object referring to the input data.
     * @return The count of positive numbers found.
     */
    public static int countPositive(Scanner input) {
    	
        int count = 0;
        
        while (input.hasNext()) {
            if (input.hasNextDouble()) {
            	
                double number = input.nextDouble();
                
                if (number > 0) {
                    count++;
                }
               
            } else {
                
                input.next();
            }
        }
        return count;
    }

    /**
     * Calculates the sum of elements in an array within a specified index range
     *
     * @param array       The int array to sum elements from
     * @param beginIndex  The starting index (inclusive) of the range
     * @param endIndex    The ending index (exclusive) of the range
     * @return The sum of elements in the specified range, or 0 if the range is invalid
     */
    public static int totalInRange(int[] array, int beginIndex, int endIndex) {
         // Check for invalid range conditions
        if (beginIndex < 0 || beginIndex >= array.length || endIndex < 0 || endIndex > array.length || beginIndex >= endIndex) {
            return 0;
        }

        int sum = 0;
        for (int i = beginIndex; i < endIndex; i++) {
            sum += array[i];
        }
        return sum;
    }

    /**
     * generates an array of even numbers
     *
     * @param length The desired length of the array (non-negative)
     * @return An int array containing the sequence of even numbers
     */
    public static int[] generateEvenArray(int length) {
        int[] evenArray = new int[length];
        for (int i = 0; i < length; i++) {
            evenArray[i] = 2 * i;
        }
        return evenArray;
    }
    
    

    /**
     * Calculates the sum of all even numbers from 0 up to a given limit (inclusive)
     
     *
     * @param limit The last even number to include in the sum (even and non-negative)
     * @return The sum of even numbers from 0 to limit
     */
    public static int sumEven(int limit) {
        if (limit < 0 || limit % 2 != 0) {
             // Handle invalid input gracefully, though spec says to assume valid
             return 0;
        }
        int arrayLength = (limit / 2) + 1; // for limit 4, we need indices 0,1,2 -> length 3
        int[] evens = generateEvenArray(arrayLength);

        // Sum all elements from index 0 to arrayLength (exclusive)
        return totalInRange(evens, 0, arrayLength);
    }

    /**
     * Calculates the base-2 logarithm of a positive integer, rounded it
     * This is equivalent to finding the position of the highest
     *
     * @param number The positive integer to find the log base 2 of.
     * @return The integer value of log2(number).
     */
    public static int logBaseTwo(int number) {
        if (number <= 0) {
            // Should not happen per spec, but good practice
            throw new IllegalArgumentException("Number must be positive");
        }
        
        
        int log = 0;
        while (number > 1) {
            number /= 2;
            log++;
        }
        
        return log;
    }

    public static void main(String[] args) {

        //Testing all the method
    	
    	// Provided
        System.out.println("Checking centimetersToInches(10.11). Expecting a result of 3. The actual result is " + centimetersToInches(10.11) + ".");
        System.out.println("Checking centimetersToInches(20.5). Expecting a result of 8. The actual result is " + centimetersToInches(20.5) + ".");
        // New
        System.out.println("Checking centimetersToInches(0.0). Expecting a result of 0. The actual result is " + centimetersToInches(0.0) + ".");
        System.out.println("Checking centimetersToInches(50.8). Expecting a result of 20. The actual result is " + centimetersToInches(50.8) + ".");


        // Testing shiftCipher  
        System.out.println("\n  Testing shiftCipher  ");
        
        //canvas
        System.out.println("Checking shiftCipher(\"hello\", 3). Expecting a result of khoor. The actual result is " + shiftCipher("hello", 3) + ".");
        System.out.println("Checking shiftCipher(\"(Zest!)\", 15). Expecting a result of 7it#$08. The actual result is " + shiftCipher("(Zest!)", 15) + ".");
        
        System.out.println("Checking shiftCipher(\" \", 10). Expecting a result of +. The actual result is " + shiftCipher(" ", 10) + "."); // Space (32) + 10 = 42 (+)
        System.out.println("Checking shiftCipher(\"~\", 1). Expecting a result that wraps (e.g., space). The actual result is " + shiftCipher("~", 1) + "."); // ~ (126) + 1 = 127 -> wraps to 32 (space)
        System.out.println("Checking shiftCipher(\"ABC\", 0). Expecting a result of ABC. The actual result is " + shiftCipher("ABC", 0) + "."); // No shift


        // Testing countPositive  
        System.out.println("\n  Testing countPositive  ");
        
        // canvas
        System.out.println("Checking countPositive(new Scanner(\"hello 0 10 2.2 string4 0.0\")). Expecting a result of 2. The actual result is " + countPositive(new Scanner("hello 0 10 2.2 string4 0.0")) + ".");
        
        System.out.println("Checking countPositive(new Scanner(\"-5 0 3.14 -2.7 1\")). Expecting a result of 2. The actual result is " + countPositive(new Scanner("-5 0 3.14 -2.7 1")) + ".");
        System.out.println("Checking countPositive(new Scanner(\"abc def ghi\")). Expecting a result of 0. The actual result is " + countPositive(new Scanner("abc def ghi")) + ".");
        System.out.println("Checking countPositive(new Scanner(\"\")). Expecting a result of 0. The actual result is " + countPositive(new Scanner("")) + ".");


        //  Testing totalInRange  
        System.out.println("\n  Testing totalInRange  ");
        
        // canvas
        System.out.println("Checking totalInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 2, 5). Expecting a result of 12. The actual result is " + totalInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 2, 5) + ".");
        System.out.println("Checking totalInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 5, 2). Expecting a result of 0. The actual result is " + totalInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 5, 2) + ".");
        
        System.out.println("Checking totalInRange(new int[]{10, 20, 30}, 0, 3). Expecting a result of 60. The actual result is " + totalInRange(new int[]{10, 20, 30}, 0, 3) + ".");
        System.out.println("Checking totalInRange(new int[]{10, 20, 30}, 1, 1). Expecting a result of 0. The actual result is " + totalInRange(new int[]{10, 20, 30}, 1, 1) + "."); // empty range
        System.out.println("Checking totalInRange(new int[]{10, 20, 30}, -1, 2). Expecting a result of 0. The actual result is " + totalInRange(new int[]{10, 20, 30}, -1, 2) + "."); // invalid start index


        //Testing generateEvenArray  
        System.out.println("\n  Testing generateEvenArray  ");
        
        //canvas
        System.out.println("Checking generateEvenArray(5). Expecting a result of [0, 2, 4, 6, 8]. The actual result is " + Arrays.toString(generateEvenArray(5)) + ".");
        
        System.out.println("Checking generateEvenArray(0). Expecting a result of []. The actual result is " + Arrays.toString(generateEvenArray(0)) + ".");
        System.out.println("Checking generateEvenArray(1). Expecting a result of [0]. The actual result is " + Arrays.toString(generateEvenArray(1)) + ".");
        System.out.println("Checking generateEvenArray(3). Expecting a result of [0, 2, 4]. The actual result is " + Arrays.toString(generateEvenArray(3)) + ".");


        // Testing sumEven  
        System.out.println("\n  Testing sumEven  ");
        
        //canvas
        System.out.println("Checking sumEven(4). Expecting a result of 6. The actual result is " + sumEven(4) + ".");
        System.out.println("Checking sumEven(100). Expecting a result of 2550. The actual result is " + sumEven(100) + ".");
        
        System.out.println("Checking sumEven(0). Expecting a result of 0. The actual result is " + sumEven(0) + ".");
        System.out.println("Checking sumEven(6). Expecting a result of 12. The actual result is " + sumEven(6) + "."); // 0+2+4+6
        System.out.println("Checking sumEven(10). Expecting a result of 30. The actual result is " + sumEven(10) + "."); // 0+2+4+6+8+10


        // Testing logBaseTwo  
        System.out.println("\n  Testing logBaseTwo  ");
        
        // canvas
        System.out.println("Checking logBaseTwo(512). Expecting a result of 9. The actual result is " + logBaseTwo(512) + ".");
        System.out.println("Checking logBaseTwo(1). Expecting a result of 0. The actual result is " + logBaseTwo(1) + ".");
        System.out.println("Checking logBaseTwo(12). Expecting a result of 3. The actual result is " + logBaseTwo(12) + ".");
        
        System.out.println("Checking logBaseTwo(2). Expecting a result of 1. The actual result is " + logBaseTwo(2) + ".");
        System.out.println("Checking logBaseTwo(1000). Expecting a result of 9. The actual result is " + logBaseTwo(1000) + ".");
        System.out.println("Checking logBaseTwo(15). Expecting a result of 3. The actual result is " + logBaseTwo(15) + ".");
    }
}