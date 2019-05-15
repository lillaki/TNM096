package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class NodeUtil {
	
	// Finds and return the different moves that are possible from the 0
    public static List<String> getSuccessors(String state) {
        List<String> successors = new ArrayList<String>();
        
        // indexOf sends back the first index where the 0 is.
        switch (state.indexOf("0")) { 
        	
        	// if the 0 is in the first index 
        	// 0xx
        	// xxx  
        	// xxx			
        
            case 0: {
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(1), state.charAt(0)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(3), state.charAt(0)).replace('*', state.charAt(3)));
                break;
            }
            
            // If the 0 is in the second index
        	// x0x
        	// xxx
        	// xxx
            
            case 1: {
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(0), state.charAt(1)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(2), state.charAt(1)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(4), state.charAt(1)).replace('*', state.charAt(4)));
                break;
            }
            
            // If the 0 is in the third index
        	// xx0
        	// xxx
        	// xxx
            
            case 2: {

                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(1), state.charAt(2)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(5), state.charAt(2)).replace('*', state.charAt(5)));
                break;
            }            
            
            // If the 0 is in the fourth index
        	// xxx
        	// 0xx
        	// xxx           

            case 3: {
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(0), state.charAt(3)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(4), state.charAt(3)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(6), state.charAt(3)).replace('*', state.charAt(6)));
                break;
            }
            
            // If the 0 is in the fifth index
        	// xxx
        	// x0x
        	// xxx  
            
            case 4: {
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(1), state.charAt(4)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(3), state.charAt(4)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(7), state.charAt(4)).replace('*', state.charAt(7)));
                break;
            }
            
            // If the 0 is in the sixth index
        	// xxx
        	// xx0
        	// xxx  
            
            case 5: {
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(2), state.charAt(5)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(8), state.charAt(5)).replace('*', state.charAt(8)));
                break;
            }
            
            // If the 0 is in the seventh index
        	// xxx
        	// xxx
        	// 0xx  
            
            case 6: {
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(3), state.charAt(6)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(7), state.charAt(6)).replace('*', state.charAt(7)));
                break;

            }
            
            // If the 0 is in the eights index
        	// xxx
        	// xxx
        	// x0x  
            
            case 7: {
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(4), state.charAt(7)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(6), state.charAt(7)).replace('*', state.charAt(6)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(8), state.charAt(7)).replace('*', state.charAt(8)));
                break;
            }
            
            // If the 0 is in the ninth index
        	// xxx
        	// xxx
        	// xx0  
            
            case 8: {
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(5), state.charAt(8)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(7), state.charAt(8)).replace('*', state.charAt(7)));
                break;
            }
        }

        return successors;
    }
    
  //*******************************************************************************************

    public static void printSolution(Node goalNode, Node root) {

        Stack<Node> solutionStack = new Stack<Node>();
        solutionStack.push(goalNode);
        
        while (!goalNode.getState().equals(root.getState())) {
            solutionStack.push(goalNode.getParent());
            goalNode = goalNode.getParent();
        }

        for (int i = solutionStack.size() - 1; i >= 0; i--) {
            System.out.println("+----------------+");
            
            System.out.println("| " + solutionStack.get(i).getState().substring(0, 3)+" |");
            System.out.println("| " + solutionStack.get(i).getState().substring(3, 6)+" |");
            System.out.println("| " + solutionStack.get(i).getState().substring(6, 9)+" |");

        }
        System.out.println("====================================================================================");
        System.out.println("** Number of transitions to get to the goal state from the initial state:  " + (solutionStack.size() - 1));
        System.out.println("====================================================================================");

    }

//*******************************************************************************************
    
}



