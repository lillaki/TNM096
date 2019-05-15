package lab1;

import java.util.*;

public class SearchTree {
    private Node root;
    private String goalSate;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getGoalSate() {
        return goalSate;
    }

    public void setGoalSate(String goalSate) {
        this.goalSate = goalSate;
    }

    public SearchTree(Node root, String goalSate) {
        this.root = root;
        this.goalSate = goalSate;
    }

//**********************************************************************************************

    public void aStar() {
        // StateSet is a set that contains node that are already visited, the closed list
        Set<String> stateSets = new HashSet<String>();
        int time = 0;
        
        Node node = new Node(root.getState());
        node.setTotalCost(0); // Total cost of the root node is 0 

        // The comparator compare the cost values and make the priority queue to be sorted based on cost values
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();

        // A queue that contains nodes and their cost values sorted. 10 is the initial size
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(10, nodePriorityComparator);
        Node currentNode = node;
        
        // Keep looping until the current node are in the goal state
        while (!currentNode.getState().equals(goalSate)) {
        	
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = NodeUtil.getSuccessors(currentNode.getState());
            
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n)) {
                	continue;
                }
                
                stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                
                child.setTotalCost(currentNode.getTotalCost() + 
                Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))),
                
                heuristicTwo(child.getState(), goalSate));
                
                nodePriorityQueue.add(child);
            } // End of for-loop 
            
            currentNode = nodePriorityQueue.poll();
            time += 1;
        }
        NodeUtil.printSolution(currentNode, root);
    }

    //*************************************************************************************************
    // This heuristic estimates the cost to the goal from current state by calculating the Manhathan distance from each misplaced
    // tile to its right position in the goal state
    
    private int heuristicTwo(String currentState, String goalSate) {
        int difference = 0;
        for (int i = 0; i < currentState.length(); i += 1)
            for (int j = 0; j < goalSate.length(); j += 1)
                if (currentState.charAt(i) == goalSate.charAt(j))
                    difference = difference + ((Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 + j / 3));
        return difference;
    }

}