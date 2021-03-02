package consoleHello;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import constant.Constant;

public class consoleHello {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
	    System.out.println("1. Post comment viewer.");
	    System.out.println("2. Hello world!");
	    System.out.print("Select : ");
	    int number = input.nextInt();
	    
	    switch(number) {
	    case 1:
	    	openPostCommentViewer();
	    	break;
	    case 2:
	    	System.out.println("Hello world!"); 
	    	break;
	    default:
	    	System.out.println("404 not found.");
	    }
	    
	    System.exit(0);
	}
	
	public static void openPostCommentViewer() throws IOException {
		File file = new File(Constant.POST_COMMENT_VIEWER_PATH);
    	System.out.println("啟動應用程式：" + Constant.POST_COMMENT_VIEWER_PATH);
    	Desktop.getDesktop().open(file);
	}
}
