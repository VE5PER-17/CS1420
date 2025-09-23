package assign01;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * @author vesper
 * @version 2025-09-02
 * @last_version 2025-09-02
 */

public class OnLab02{
	public static void main(String[] args) throws IOException {
		//set up
        File filename = new File("forest.txt");
        Scanner file = new Scanner(filename);
        
        //processing
        String treeName;
        int treeCount;
        /*
        while(file.hasNext()) {
        
        	treeName = file.next();     // scan next words
        	treeCount = file.nextInt(); // scan next integer
        	
        	System.out.println("Tree: " + treeName + " has a count of " + treeCount + ".");
        	
        }
        */
        //lab quest
        
        int totalTreeCount = 0;
        int countMin = Integer.MAX_VALUE;
        int countMax = Integer.MIN_VALUE;
        
        while(file.hasNext()) {
        	
        	treeName = file.next();     // scan next words
        	treeCount = file.nextInt(); // scan next integer
        	
        	totalTreeCount += treeCount; //update
        	
        	if(treeCount > countMax) {
        		countMax = treeCount;
        	}else if (treeCount < countMin){
        		countMin = treeCount;
        	}
        	
        	
        }
        
        
        System.out.println("Total tree count is " + totalTreeCount + ".");
        System.out.println("Highest tree count is " + countMax + ".");
        System.out.println("Lowest tree count is " + countMin + ".");
        
        //when all done
        file.close();
        		
	}
}
