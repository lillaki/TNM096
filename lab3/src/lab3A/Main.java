package lab3A;

public class Main {

	public static void main(String[] args) {

		String a = "a V b V -c";
		String b = "c V b"; 
		//String b =  "d U b -g";
				
		// Create clauses A and B 
		Clauses A = new Clauses(a);
		Clauses B = new Clauses(b);
		
		// Create empty clause to store result in
		Clauses C = new Clauses();
		
		if(C.Resolution(A, B) == null) {
			System.out.println("The resolution is false");			
		}
		else {
			printClause();
		}
		
	}
	// Write a print function to print the result with 'V' 
	public static void printClause() {
		System.out.println("There is a C");
	}

}
