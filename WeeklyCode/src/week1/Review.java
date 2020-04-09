package week1;

public class Review {
	public static void main(String[] args) {

		String s1 = "Hi";
		String s2 = "Pizza";
		String s3;
		s3 = new String ("happy");
		
		//String s3;
		//s3.equals
		//s3 = new String();
		s1 = s2;
		int i = 1000;
		int j = 4;

		i = j;   // shows what is moved. 
		

		if (i == j)   // show when to use == or .equals
			i = j;

		//	s1 = s2;   // to show how alias works

		if (s1 == s2)
			s1 = s2;
		
		//		s1 = s1.toLowerCase();   // just an example
		//  	s1 = s1.charAt() ==    // does not work!		
		
		if (s1.equals(s2))
			s1 = s2;	// error correcting

		System.out.println (s1);
	}



}
