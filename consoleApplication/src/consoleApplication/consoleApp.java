package consoleApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class consoleApp {
	public static void main(String[] args) throws Exception{
		URL url = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader br = null;
		String message = null;
		
	    @SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
	    System.out.println("1. View post.");
	    System.out.println("2. View comment.");
	    System.out.print("Select : ");
	    int number = input.nextInt();
	    
	    switch(number) {
	    case 1:
	    	url = new URL("https://jsonplaceholder.typicode.com/posts");
	    	inputStream = url.openStream();
	    	inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
	    	br = new BufferedReader(inputStreamReader);
	    	while ((message = br.readLine()) != null) {
	    		System.out.println(message);
	    	}     
	    	break;
	    case 2:
	    	url = new URL("https://jsonplaceholder.typicode.com/posts/1/comments");
	    	inputStream = url.openStream();
	    	inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
	    	br = new BufferedReader(inputStreamReader);
	    	while ((message = br.readLine()) != null) {
	    		System.out.println(message);
	    	}   
	    	break;
	    default:
	    	System.out.println("404 not found.");
	    }
	    
	    
	    System.exit(0);
	}
}
