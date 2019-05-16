package lab3A;

public class Main {

	public static void main(String[] args) {

		//String a = "a V b V -c";
		//String b = "c V b"; 
		//String b =  "-a V -b -g";
		String a = "-b V c V t";
		String b = "-c V z V b";
		
		// Create clauses A and B 
		Clauses A = new Clauses(a);
		Clauses B = new Clauses(b);
		
		// Create empty clause to store result in
		Clauses C = new Clauses();
		
		if(C.Resolution(A, B) == null) {
			System.out.println("The resolution is false");			
		}
			
	}

}
