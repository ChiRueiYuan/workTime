package workTime.main.window;

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

import workTime.main.controller.BaseController;
import workTime.main.model.Employee;
import workTime.main.service.EmployeeService;
import workTime.main.service.impl.EmployeeServiceImpl;

import javax.swing.JComboBox;

public class demo extends BaseController {
	EmployeeService employeeService = new EmployeeServiceImpl<>();

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
		frame.setBounds(100, 100, 600, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ArrayList<Employee> allEmployees = employeeService.getAll(getConnection());
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(14, 13, 404, 25);
		frame.getContentPane().add(comboBox);
		for(Employee employee : allEmployees) {
			comboBox.addItem(employee.getId());
		}
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollingArea = new JScrollPane(textArea);
		scrollingArea.setBounds(14, 13, 404, 164);
		frame.getContentPane().add(scrollingArea);
		
		JButton btnNewButton = new JButton("Get by id.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				try {
					String id = String.valueOf(comboBox.getSelectedItem());
					Employee employee = employeeService.getById(getConnection(), id);
					textArea.append("id : " + employee.getId() + "\n");
					textArea.append("name : " + employee.getName() + "\n");
				} catch (Exception e1) {
					e1.printStackTrace();
				}  
			}
		});
		btnNewButton.setBounds(14, 198, 200, 50);
		frame.getContentPane().add(btnNewButton);
	}
}
