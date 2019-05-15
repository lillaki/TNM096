package lab3;
import java.util.*;

public class ClausesSolver {
	
	public Vector<Clauses> clausesVector = new Vector<Clauses>();
	
	// Only send in one string that are our 'clause' and create a new clause and put it in a vector of clauses
	// that we will use later in solver
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
	
	// Function Resolution(A,B) return resolvent of A and B, or false
	// Input: clauses A and B; A and B are local variables
	public Clauses Resolution (Clauses A, Clauses B){

		String snitt = null;
		Clauses C = new Clauses();

		Vector<String> An = A.negative;
		Vector<String> Ap = A.positive;
		Vector<String> Bn = B.negative;
		Vector<String> Bp = A.positive;


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

			// Om det finns något som är i snitt dvs if (A.p ∩ B.n) != {} || if (A.n ∩ B.p) != {} så ta ett element och
			// lägg in i ett snitt så att man kan söka ifall den finns i Ap, Bp, An, Bn. Om den finns där så ska den enligt
			// instruktionerna tas bort MEN istället så kan man se ifall den finns och finns den inte så kan man lägga till det i C.
			// Om snittet är null så behöver man inte lägga till något.
			// C.p ∩ C.n != {} blir per automatik när man lägger till.

			if (snitt != null) {

				for (int i = 0; i < Ap.size(); i++) {
					if (!Ap.get(i).equals(snitt) && !C.positive.contains(Ap.get(i)))
						C.positive.add(Ap.get(i));
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

		//  och C inte har dubbletter och ifall snittet inte är tomt
			if(C.isEmpty()) {
				return null;
			}

			return C;
		}
	
	//Solver
	public void Solver() {
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

		} while (c != null && c.isEmpty()); 

	}
	

}
