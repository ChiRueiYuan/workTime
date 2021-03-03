package workTime.main.window;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class demo2 extends BaseController {
	EmployeeService employeeService = new EmployeeServiceImpl<>();

	private JFrame frame;
	private JPanel panel = new JPanel();
	private int size = 5;
	private int page = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demo2 window = new demo2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @return 
	 */
	public demo2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 561, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel.setBounds(0, 0, 543, 348);
		frame.getContentPane().add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu employeeMenu = new JMenu("Employee");
		JMenu formMenu = new JMenu("Form");
		JMenu timeRecordMenu = new JMenu("Time Record");
		
		JMenuItem getAllEmployeeMenuItem = new JMenuItem("琩高场Employee");
		getAllEmployeeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				ArrayList<Employee> allEmployees = employeeService.getAll(getConnection());
				
				int maxPage = (allEmployees.size()/size) + 1;
				
				JTextArea textArea = new JTextArea();
				JScrollPane scrollingArea = new JScrollPane(textArea);
				scrollingArea.setBounds(14, 40, 404, 164);
				panel.add(scrollingArea);
				
				ArrayList<Employee> employeeList = employeeService.getPaginationByQuery(getConnection(), page, size);
				for(Employee employee : employeeList) {
					textArea.append("id : " + employee.getId() + "\n");
					textArea.append("name : " + employee.getName() + "\n");
				}
				
				JButton prevButton = new JButton("<");
				prevButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(page > 1) {
							textArea.setText("");
							page--;
							try {
								ArrayList<Employee> employeeList = employeeService.getPaginationByQuery(getConnection(), page, size);
								for(Employee employee : employeeList) {
									textArea.append("id : " + employee.getId() + "\n");
									textArea.append("name : " + employee.getName() + "\n");
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}  
						}
					}
				});
				prevButton.setBounds(14, 230, 50, 40);
				panel.add(prevButton);
				
				JButton nextButton = new JButton(">");
				nextButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(page < maxPage) {
							textArea.setText("");
							page++;
							try {
								ArrayList<Employee> employeeList = employeeService.getPaginationByQuery(getConnection(), page, size);
								for(Employee employee : employeeList) {
									textArea.append("id : " + employee.getId() + "\n");
									textArea.append("name : " + employee.getName() + "\n");
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				});
				nextButton.setBounds(84, 230, 50, 40);
				panel.add(nextButton);
			}
		});
		
		JMenuItem getByIdEmployeeMenuItem = new JMenuItem("ID琩高Employee");
		getByIdEmployeeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				ArrayList<Employee> allEmployees = employeeService.getAll(getConnection());
				
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(14, 13, 404, 25);
				panel.add(comboBox);
				for(Employee employee : allEmployees) {
					comboBox.addItem(employee.getId());
				}
				
				JTextArea textArea = new JTextArea();
				JScrollPane scrollingArea = new JScrollPane(textArea);
				scrollingArea.setBounds(14, 40, 404, 164);
				panel.add(scrollingArea);
				
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
				btnNewButton.setBounds(14, 230, 150, 40);
				panel.add(btnNewButton);
			}
		});
		JMenuItem addEmployeeMenuItem = new JMenuItem("穝糤Employee");
		JMenuItem updateEmployeeMenuItem = new JMenuItem("эEmployee");
		JMenuItem deleteEmployeeMenuItem = new JMenuItem("埃Employee");
		employeeMenu.add(getAllEmployeeMenuItem);
		employeeMenu.add(getByIdEmployeeMenuItem);
		employeeMenu.add(addEmployeeMenuItem);
		employeeMenu.add(updateEmployeeMenuItem);
		employeeMenu.add(deleteEmployeeMenuItem);
		
		JMenuItem getAllFormMenuItem = new JMenuItem("琩高场Form");
		JMenuItem getByIdFormMenuItem = new JMenuItem("ID琩高Form");
		JMenuItem addLeaveFormMenuItem = new JMenuItem("穝糤LeaveForm");
		JMenuItem addQuitFormMenuItem = new JMenuItem("穝糤QuitForm");
		JMenuItem updateLeaveFormMenuItem = new JMenuItem("эLeaveForm");
		JMenuItem updateQuitFormMenuItem = new JMenuItem("эQuitForm");
		JMenuItem approveFormMenuItem = new JMenuItem("Form");
		JMenuItem deleteFormMenuItem = new JMenuItem("埃Form");
		formMenu.add(getAllFormMenuItem);
		formMenu.add(getByIdFormMenuItem);
		formMenu.add(addLeaveFormMenuItem);
		formMenu.add(addQuitFormMenuItem);
		formMenu.add(updateLeaveFormMenuItem);
		formMenu.add(updateQuitFormMenuItem);
		formMenu.add(approveFormMenuItem);
		formMenu.add(deleteFormMenuItem);
		
		JMenuItem getAllTimeRecordMenuItem = new JMenuItem("琩高场TimeRecord");
		JMenuItem getByIdTimeRecordMenuItem = new JMenuItem("ID琩高TimeRecord");
		timeRecordMenu.add(getAllTimeRecordMenuItem);
		timeRecordMenu.add(getByIdTimeRecordMenuItem);
		
		menuBar.add(employeeMenu);
		menuBar.add(formMenu);
		menuBar.add(timeRecordMenu);
		
	}
}
