package assign03;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * CS 1420 Assignment 3: Grade Predictor
 * Author: [Your Name Here]
 * Date: 2025-09-12
 */
public class testOut {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        Scanner fileScanner = null;
        boolean fileLoaded = false;
        
        // Keep prompting until a valid file is provided
        while (!fileLoaded) {
            System.out.println("Enter the file name with path:");
            String fileName = consoleScanner.nextLine();
            
            try {
                fileScanner = new Scanner(new File(fileName));
                fileLoaded = true;
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found. Please try again.");
            }
        }
        
        // Read the four double values
        double midterm = fileScanner.nextDouble();
        double finalExam = fileScanner.nextDouble();
        double labAverage = fileScanner.nextDouble();
        double weeklyQuizAverage = fileScanner.nextDouble();
        
        // Read the number of assignment scores
        int assignmentCount = fileScanner.nextInt();
        
        // Create array and read assignment scores
        int[] assignmentScores = new int[assignmentCount];
        for (int i = 0; i < assignmentCount; i++) {
            assignmentScores[i] = fileScanner.nextInt();
        }
        
        fileScanner.close();
        consoleScanner.close();
        
        // Calculate and report assignment statistics
        double assignmentSum = 0;
        int zeroCount = 0;
        int maxScore = assignmentScores[0];
        int minScore = assignmentScores[0];
        
        for (int score : assignmentScores) {
            assignmentSum += score;
            if (score == 0) {
                zeroCount++;
            }
            if (score > maxScore) {
                maxScore = score;
            }
            if (score < minScore) {
                minScore = score;
            }
        }
        
        double averageAssignmentScore = assignmentSum / assignmentCount;
        
        // Sort array to find median
        Arrays.sort(assignmentScores);
        int medianIndex = assignmentCount % 2 == 0 ? assignmentCount / 2 : assignmentCount / 2;
        int medianScore = assignmentScores[medianIndex];
        
        // Print assignment statistics
        System.out.printf("The average assignment score is %.2f.\n", averageAssignmentScore);
        System.out.println("The median assignment score is " + medianScore + ".");
        System.out.println("The number of 0 assignment scores is " + zeroCount + ".");
        System.out.println("The highest assignment score is " + maxScore + ".");
        System.out.println("The lowest assignment score is " + minScore + ".");
        
        // Define grade thresholds and corresponding letter grades
        double[] gradeThresholds = {70.0, 73.0, 77.0, 80.0, 83.0, 87.0, 90.0, 93.0};
        String[] letterGrades = {"C-", "C", "C+", "B-", "B", "B+", "A-", "A"};
        
        // Calculate current non-test portion of grade
        double currentGrade = (midterm * 0.10) +           // 10% midterm
                             (finalExam * 0.15) +          // 15% final exam
                             (labAverage * 0.10) +         // 10% labs
                             (weeklyQuizAverage * 0.05);   // 5% weekly quizzes
        // Assignments are 20% of grade
        currentGrade += averageAssignmentScore * 0.20;
        
        // Tests (excluding midterm and final) are 40% of grade
        // We need to find what average test score is needed to reach each threshold
        
        for (int i = 0; i < gradeThresholds.length; i++) {
            double neededTotal = gradeThresholds[i];
            double neededFromTests = neededTotal - currentGrade;
            double neededTestAverage = neededFromTests / 0.40;  // Tests are 40% of grade
            
            // Check if the needed test average is possible (0-100)
            if (neededTestAverage > 100) {
                if (i == gradeThresholds.length - 1) { // This is for 'A' grade
                    System.out.println("It is not possible to achieve A with these scores.");
                }
                // Don't print impossible grades except for A
            } else if (neededTestAverage < 0) {
                // If negative, they already exceed this threshold
                System.out.printf("A test average of 0.00 is needed to achieve %s.\n", letterGrades[i]);
            } else {
                System.out.printf("A test average of %.2f is needed to achieve %s.\n", neededTestAverage, letterGrades[i]);
            }
        }
    }
}