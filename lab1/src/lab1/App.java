package lab1;

public class App {
    final static private String EASY = "123405786";
    final static private String MEDIUM = "250148736";
    final static private String HARD = "167480325";
    final static private String GOAL_STATE = "123456780";

    public static void main(String[] args) {
        String rootState = EASY;
        long startTime = System.currentTimeMillis();
        
        // Check if the puzzle is solvable
        if (!canItBeSolved(rootState)) {
        	System.out.print("The puzzle " + rootState + " cannot be solved!\n");
        }
        else {
	        // Put the init state in the root of the searchTree
	        SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);
	        search.aStar();
        }

        long finishTime = System.currentTimeMillis();
        long totalTime = finishTime - startTime;
        System.out.println("Time  :" + totalTime);
        
    }
    
    //***************************************************************************
    
    //Count inversions
    static int getSolvableCount(String str){
    	
    	// Convert string to separate integers
        int size = str.length();
        int [] arr = new int [size];
        for(int i=0; i<size; i++) {
           arr[i] = Character.getNumericValue(str.charAt(i));
        }
        
        int inv_count = 0;
        for (int i = 0; i < 9 - 1; i++)
            for (int j = i + 1; j < 9; j++)
                if (arr[j] > 0 && arr[i] > 0 && arr[i] > arr[j])
                    inv_count++;
        return inv_count;
    }

    // Returns true if puzzle is solvable
    static boolean canItBeSolved(String puzzle){
        // Count inversions
        int invCount = getSolvableCount(puzzle);

        // Return true if inversion count is even
        return (invCount % 2 == 0);
    }
}
