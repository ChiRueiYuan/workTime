package main;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class demo {

	private JFrame frame;
	private JTextField textField;
	private ArrayList<String> arrows = new ArrayList<>();

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
		
		JTextField textField = new JTextField();
		textField.setBounds(152, 110, 116, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("¡ô");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addArrows("¡ô");
				textField.setText(arrows.toString());
			}
		});
		btnNewButton.setBounds(110, 0, 200, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("¡õ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addArrows("¡õ");
				textField.setText(arrows.toString());
			}
		});
		btnNewButton_1.setBounds(110, 200, 200, 50);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("¡ö");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addArrows("¡ö");
				textField.setText(arrows.toString());
			}
		});
		btnNewButton_2.setBounds(0, 25, 50, 200);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("¡÷");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addArrows("¡÷");
				textField.setText(arrows.toString());
			}
		});
		btnNewButton_3.setBounds(380, 25, 50, 200);
		frame.getContentPane().add(btnNewButton_3);
	}
	
	private void addArrows(String arrow) {
		arrows.add(arrow);
		if(arrows.size()>5) {
			removeArrows();
		}
	}
	
	private void removeArrows() {
		arrows.remove(0);
	}
}
