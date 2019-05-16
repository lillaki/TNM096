package lab3A;
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
				
		clause = clause.replaceAll("V","").replaceAll("S","");
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
	}
	
	public Clauses Resolution (Clauses A, Clauses B){

		String snitt = null;
		Clauses C = new Clauses();

		Vector<String> An = A.negative;
		Vector<String> Ap = A.positive;
		
		Vector<String> Bn = B.negative;
		Vector<String> Bp = B.positive;
		
		    // A.p ∩ B.n
			for (int i = 0; i < Ap.size(); i++)
			{
				if (Bn.contains(Ap.get(i))) {
					snitt = Ap.get(i);
				}
			}
			
			//A.n ∩ B.p
			for (int i = 0; (snitt == null) && (i < An.size()); i++)
			{
				
				if (Bp.contains(An.get(i))) {				
					snitt = An.get(i);
				}
			}
			
			// If there is an intersection
			if (snitt != null) {
				
				for (int i = 0; i < Ap.size(); i++) {
					if ((!Ap.get(i).equals(snitt)) && (!C.positive.contains(Ap.get(i)))){
						C.positive.add(Ap.get(i));
					}
				}

				for (int i = 0; i < An.size(); i++) {
					if (!An.get(i).equals(snitt) && !C.negative.contains(An.get(i)))
						C.negative.add(An.get(i));
				}

				for (int i = 0; i < Bp.size(); i++) {
					if (!Bp.get(i).equals(snitt) && !C.positive.contains(Bp.get(i)))
						C.positive.add(Bp.get(i));
				}

				for (int i = 0; i < Bn.size(); i++) {
					if (!Bn.get(i).equals(snitt) && !C.negative.contains(Bn.get(i)))
						C.negative.add(Bn.get(i));
				}
			}

			else {
				return null;
			}

			if(C.isEmpty()) {
				return null;
			}
			return C;
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
