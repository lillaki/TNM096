package lab3;
import java.util.*;

public class Clauses{
	
	public Vector<String> negative = new Vector<String>();
	public Vector<String> positive = new Vector<String>();
	
	public Clauses() {
		negative.add("");
		positive.add("");
		
	}
	
	public Clauses(String clause){
		
		String[] splittedClause;
				
		clause = clause.replaceAll("U","").replaceAll("S","");
		splittedClause = clause.split("[\\s]");
		
		for(int i = 0; i<splittedClause.length; i++) {

				if(splittedClause[i].contains("-")){
					String s = splittedClause[i];
					s = s.replaceAll("-","");
					
					negative.add(s);
				}
				else {
					positive.add(splittedClause[i]); 
				}
		}
		
		System.out.println("positive = " + positive);
		System.out.println("negative = " + negative);
	}
	

	public void removeDuplicates(){

		for (int i = 0; i < negative.size(); i++) {
			for (int j = 0; j < positive.size(); j++){

				if(negative.get(i).equals(positive.get(j)))
				{
					negative.removeElementAt(i);
					positive.removeElementAt(j);
				}
			}
		}
	}


	public boolean isEqual(){

		for (int i = 0; i < negative.size(); i++) {
			for (int j = 0; j < positive.size(); j++){

				if(positive.get(i).equals(negative.get(j)))
					return true;
			}
		}
		return false;
	}
	
	public boolean isThisSubset(Clauses clauses){

		for (int i = 0; i < negative.size(); i++) {
			if(clauses.negative.contains(negative.get(i))) {
				return true;
			}
		}

		for (int i = 0; i < positive.size(); i++) {
			if(clauses.positive.contains(positive.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty(){
		if(positive.size() + negative.size() == 0 ){
			return true;
		}
		else {
			return false; 
		}
	}
	
	// We need an override function for equals
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Clauses anotherClauses = (Clauses) obj;

		if (negative == null) {
			if (anotherClauses.negative != null)
				return false;
		} else if (!negative.equals(anotherClauses.negative))
			return false;
		if (positive == null) {
			if (anotherClauses.positive != null)
				return false;
		} else if (!positive.equals(anotherClauses.positive))
			return false;
		return true;
	}
	
}
