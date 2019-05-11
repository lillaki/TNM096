package lab3;
import java.util.*;

public class ClausesCalc {
	
	private Vector<String> negative = new Vector<String>();
	private Vector<String> positive = new Vector<String>();
	
	public Clauses(String clause){
		
		String[] splittedClause;
				
		clause = clause.replaceAll("U","").replaceAll("S","");
		splittedClause = clause.split("[\\s]");
		
		for(int i = 0; i<splittedClause.length; i++) {

				if(splittedClause[i].contains("-")){
					splittedClause[i] = splittedClause[i].replace("-", "");
					negative.add(splittedClause[i]); 
				}
				else {
					positive.add(splittedClause[i]); 
				}
		}
		
		System.out.println("positive = " + positive);
		System.out.println("negative = " + negative);
	}

	//För att det är enklare att göra det här än att skriva koden om och om igen :)
	//Borde vara i Clauses för att det är berkäningsfunktioner

	//Ta bort dubbletter
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

	//Är dom lika

	public boolean isEqual(){

		for (int i = 0; i < negative.size(); i++) {
			for (int j = 0; j < positive.size(); j++){

				if(positive.get(i).equals(negative.get(j)))
					return true;
			}
		}
		return false;
	}

	//Är det ett subset??
	public void isThisSubset(Clauses clauses){

		for (int i = 0; i < negative.size(); i++) {
			if(clauses.negative.contains(negative.get(i)) {
				return true;
			}
		}

		for (int i = 0; i < positive.size(); i++) {
			if(clauses.positive.contains(positive.get(i)) {
				return true;
			}
		}
		return false;
	}


	//Är det tomt
	public boolean isEmpty(){
		if(positive.size() + negative.size() == 0 ){
			return true;
		}
	}


	//Vi behöver en override funktion för equals. Tog från internet och skrev om lite
	@Override
	public boolean equals(Object obj) {
		//Taget från internet
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		//omskrivet från internet
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


	//Borde vara i ClausesSolver för att det är dom som är från solverdokumentet

	//Solver borde ha en constructor
	public Vector<Clauses> clausesVector = new Vector<Clauses>();

	//Då Lucas sa att vi bara skulle skicka in en String så tar denna in en String som är då vår "clause"
	//Den tar in en String och sedan skapar en ny Clause med detta och sen lägger in det i en vector av Clauses
	//Som vi senare använder oss av i Solver.
	ClausesSolver(String[] stringClauses)
	{
		for(int i = 0; i < stringClauses.length; i ++)
		{
			Clauses c = new Clauses(stringClauses[i]);

			if(!c.equals(0))
			{
				clausesVector.addElement(c);
			}
		}
	}

	//function Resolution(A,B) return resolvent of A and B, or false
	// input: clauses A and B; A and B are local variables
	public class Resolution(Clauses A, Clauses B){

		String snitt = null;
		Clauses C = new Clauses();

		Vector<String> An = A.negativeVector;
		Vector<String> Ap = A.positiveVector;
		Vector<String> Bn = B.negativeVector;
		Vector<String> Bp = A.positiveVector;


			// A.p ∩ B.n
			for (int i = 0; i < Ap.size(); i++)
			{
				if (Bn.contains(Ap.get(i)))
					snitt = Ap.get(i);
			}
			//A.n ∩ B.p
			for (int i = 0; snitt == null && i < Bp.size(); i++)
			{
				if (An.contains(Bp.get(i)))
					snitt = Bp.get(i);
			}

			//Om det finns något som är i snitt dvs if (A.p ∩ B.n) != {} || if (A.n ∩ B.p) != {} så ta ett element och
			// lägg in i ett snitt så att man kan söka ifall den finns i Ap, Bp, An, Bn. Om den finns där så ska den enligt
			// instruktionerna tas bort MEN istället så kan man se ifall den finns och finns den inte så kan man lägga till det i C.
			//Om snittet är null så behöver man inte lägga till något.
			//  C.p ∩ C.n != {} blir per automatik när man lägger till.

			if (snitt != null) {

				for (int i = 0; i < Ap.size(); i++) {
					if (!Ap.get(i).equals(snitt) && !C.positiveVector.contains(Ap.get(i)))
						C.positiveVector.add(Ap.get(i));
				}

				for (int i = 0; i < An.size(); i++) {
					if (!An.get(i).equals(snitt) && !C.negativeVector.contains(An.get(i)))
						C.negativeVector.add(An.get(i));
				}

				for (int i = 0; i < Bp.size(); i++) {
					if (!Bp.get(i).equals(snitt) && !C.positiveVector.contains(Bp.get(i)))
						C.positiveVector.add(Bp.get(i));
				}

				for (int i = 0; i < Bn.size(); i++) {
					if (!Bn.get(i).equals(snitt) && !C.negativeVector.contains(Bn.get(i)))
						C.negativeVector.add(Bn.get(i));
				}
			}

			else {
				return null;
			}

			//  och C inte har dubbletter och ifall snittet inte är tomt
			if(C.isEmpty()){return null;}

			return C;
		}

		//Solver
		public void Solver()
		{
			Clauses c = new Clauses();
			Vector<Clauses> KB = new Vector<Clauses>();

			do {
				//for each A, B in KB
				for (int i = 0; i < clausesVector.size() - 1; i++) {
					for (int j = i + 1; j < clausesVector.size(); j++) {

						//C ← Resolution(A,B)
						c = Resolution(clausesVector.get(i), clausesVector.get(j));

						// If i and j can be resolved into a new clause
						if (c != null) {
							KB.addElement(c);
						}
					}
				}

			} while (c != null && c.isEmpty())



	}


}
