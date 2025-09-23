/** CS 1420
* Assign03
*  @Author: Vesper
*  @Date: 2025-09-12
*/
package assign03;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GradePredictor {
	
	private static double assignAvg(int[] assignments) {
		int sum = 0;
		for (int score : assignments) {
			sum += score; 
		}
		return (double) sum / assignments.length; 
	}
	

	public static void main(String[] args) {
		
		
		Scanner console = new Scanner(System.in);
		Scanner fileScanner = null;
		
		
		System.out.print("Enter the file name with path: \n");
		String fileName = console.nextLine();
		
		//handle err
		try {
			File file = new File(fileName);
			fileScanner = new Scanner(file);
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
		}
		
		double midterm = fileScanner.nextDouble();
		double finalExam = fileScanner.nextDouble();
		double labAvg = fileScanner.nextDouble();
		double quizAvg = fileScanner.nextDouble();

		int numAssignments = fileScanner.nextInt();
		int[] assignments = new int[numAssignments];


		for (int i = 0; i < numAssignments; i++) {
			assignments[i] = fileScanner.nextInt();
		}

		fileScanner.close(); 

	
		double avgAssignment = assignAvg(assignments);

		// make copy of assignment array and sort it
		int[] sortedAssignments = assignments.clone();
		Arrays.sort(sortedAssignments);
		
		int min = sortedAssignments[0];
		int max = sortedAssignments[numAssignments - 1];
		int median;
		
		if (numAssignments % 2 == 0) {
			median = sortedAssignments[numAssignments / 2]; // if even, take middle
		} else {
			median = sortedAssignments[(numAssignments - 1) / 2]; // if odd, also take middle
		}

		// count how many zero scores
		int zeroCount = 0;
		for (int score : assignments) {
			if (score == 0) {
				zeroCount++;
			}
		}

		//print block
		System.out.printf("The average assignment score is %.2f.\n", avgAssignment);
		System.out.printf("The median assignment score is %d.\n", median);
		System.out.printf("The number of 0 assignment scores is %d.\n", zeroCount);
		System.out.printf("The highest assignment score is %d.\n", max);
		System.out.printf("The lowest assignment score is %d.\n", min);

		//wieight
		final double ASSIGNMENT_WEIGHT = 0.20;
		final double LAB_WEIGHT = 0.10;
		final double QUIZ_WEIGHT = 0.05;
		final double MIDTERM_WEIGHT = 0.10;
		final double FINAL_WEIGHT = 0.15;
		final double TEST_WEIGHT = 0.40; 

		double nonTestScore = (midterm * MIDTERM_WEIGHT) + (finalExam * FINAL_WEIGHT)
		                    + (avgAssignment * ASSIGNMENT_WEIGHT) 
		                    + (labAvg * LAB_WEIGHT) 
		                    + (quizAvg * QUIZ_WEIGHT);

		
		String[] grades = { "C-", "C", "C+", "B-", "B", "B+", "A-", "A" };
		double[] minScores = { 70, 73, 77, 80, 83, 87, 90, 93 }; // min score for each grade

		
		for (int i = 0; i < grades.length; i++) {
			double target = minScores[i]; 
			double neededTestAvg = (target - nonTestScore) / TEST_WEIGHT; 

			if (neededTestAvg <= 100.0) {
				if (neededTestAvg < 0) {
					neededTestAvg = 0.0;
				}
				System.out.printf("A test average of %.2f is needed to achieve %s.\n", neededTestAvg, grades[i]);
			} else {
				System.out.printf("It is not possible to achieve %s with these scores.\n", grades[i]);
				
			}
		}

		console.close(); 
		//Fk i finally finshed
	}
}