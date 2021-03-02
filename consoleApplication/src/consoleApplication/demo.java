package consoleApplication;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class demo {

	private JFrame frame;
	URL url = null;
	InputStream inputStream = null;
	InputStreamReader inputStreamReader = null;
	BufferedReader br = null;
	String message = null;
	ArrayList<String> messageList = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demo window = new demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public demo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollingArea = new JScrollPane(textArea);
		scrollingArea.setBounds(14, 13, 404, 164);
		frame.getContentPane().add(scrollingArea);
		
		JButton btnNewButton = new JButton("1. View post.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				try {
					url = new URL("https://jsonplaceholder.typicode.com/posts");
					inputStream = url.openStream();
			    	inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			    	br = new BufferedReader(inputStreamReader);
			    	while ((message = br.readLine()) != null) {
			    		textArea.append(message + "\n");
			    	}
				} catch (Exception e1) {
					e1.printStackTrace();
				}  
			}
		});
		btnNewButton.setBounds(0, 190, 200, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("2. View comment.");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				try {
					url = new URL("https://jsonplaceholder.typicode.com/posts/1/comments");
					inputStream = url.openStream();
			    	inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			    	br = new BufferedReader(inputStreamReader);
			    	while ((message = br.readLine()) != null) {
			    		textArea.append(message + "\n");
			    	}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(232, 190, 200, 50);
		frame.getContentPane().add(btnNewButton_2);
	}
}
