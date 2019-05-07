package lab3;
import java.util.*;

public class Clauses {
	
	private Vector<String> negative = new Vector<String>();
	private Vector<String> positive = new Vector<String>();
	
	public Clauses(String clause){
		
		String[] splittedClause;
				
		clause = clause.replaceAll("U","").replaceAll("S","");
		splittedClause = clause.split("[\\s]");
		
		for(int i = 0; i<splittedClause.length; i++) {

				if(splittedClause[i].contains("-")){
					negative.add(splittedClause[i]); 
				}
				else {
					positive.add(splittedClause[i]); 
				}
		}
		
		System.out.println("positive = " + positive);
		System.out.println("negative = " + negative);
	}


}
