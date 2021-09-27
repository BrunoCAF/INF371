package bugs;

import java.util.Scanner;

class Bug8 {
    public static void main(String[] args) {
    	System.out.print("Entrez un nombre n =  ");
	
    	Scanner scanner = new Scanner(System.in);
    	int n = Integer.parseInt(scanner.nextLine());
    	scanner.close();
    	
        int facto = 1;
        int i;
        for (i=1; i<=n; i=i+1)
                facto = facto * i;

        System.out.println(n+"! =  " + facto);
        
    }
}
