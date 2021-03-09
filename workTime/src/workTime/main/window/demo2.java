package workTime.main.window;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import workTime.main.controller.BaseController;
import workTime.main.form.AddEmployeeForm;
import workTime.main.form.AddLeaveForm;
import workTime.main.form.AddQuitForm;
import workTime.main.form.ApproveForm;
import workTime.main.form.UpdateEmployeeForm;
import workTime.main.form.UpdateLeaveForm;
import workTime.main.form.UpdateQuitForm;
import workTime.main.model.BaseForm;
import workTime.main.model.Employee;
import workTime.main.model.LeaveForm;
import workTime.main.model.QuitForm;
import workTime.main.model.TimeClockRecord;
import workTime.main.service.EmployeeService;
import workTime.main.service.FormService;
import workTime.main.service.TimeClockRecordService;
import workTime.main.service.impl.EmployeeServiceImpl;
import workTime.main.service.impl.FormServiceImpl;
import workTime.main.service.impl.TimeClockRecordServiceImpl;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class demo2 extends BaseController {
	EmployeeService employeeService = new EmployeeServiceImpl<>();
	FormService formService = new FormServiceImpl<>();
	TimeClockRecordService timeClockRecordService = new TimeClockRecordServiceImpl<>();

	private JFrame frame;
	private JPanel panel = new JPanel();
	private int size = 5;
	private int employeePage = 1;
	private int formPage = 1;
	private int timeClockRecordPage = 1;
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
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<Employee> allEmployees = employeeService.getAll(connection);
				
				int maxPage = (allEmployees.size()/size) + 1;
				
				JTextArea textArea = new JTextArea();
				JScrollPane scrollingArea = new JScrollPane(textArea);
				scrollingArea.setBounds(14, 40, 404, 164);
				panel.add(scrollingArea);
				
				ArrayList<Employee> employeeList = employeeService.getPaginationByQuery(connection, employeePage, size);
				for(Employee employee : employeeList) {
					textArea.append("id : " + employee.getId() + "\n");
					textArea.append("name : " + employee.getName() + "\n\n");
				}
				OK(connection, employeeList);
				
				JButton prevButton = new JButton("<");
				prevButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(employeePage > 1) {
							textArea.setText("");
							employeePage--;
							try {
								Connection connection = getConnection();
								ArrayList<Employee> employeeList = employeeService.getPaginationByQuery(connection, employeePage, size);
								for(Employee employee : employeeList) {
									textArea.append("id : " + employee.getId() + "\n");
									textArea.append("name : " + employee.getName() + "\n\n");
								}
								OK(connection, employeeList);
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
						if(employeePage < maxPage) {
							textArea.setText("");
							employeePage++;
							try {
								Connection connection = getConnection();
								ArrayList<Employee> employeeList = employeeService.getPaginationByQuery(connection, employeePage, size);
								for(Employee employee : employeeList) {
									textArea.append("id : " + employee.getId() + "\n");
									textArea.append("name : " + employee.getName() + "\n\n");
								}
								OK(connection, employeeList);
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
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
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
				
				OK(connection, allEmployees);
				
				JButton btnNewButton = new JButton("Get by id.");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textArea.setText("");
						try {
							Connection connection = getConnection();
							String id = String.valueOf(comboBox.getSelectedItem());
							Employee employee = employeeService.getById(connection, id);
							textArea.append("id : " + employee.getId() + "\n");
							textArea.append("name : " + employee.getName() + "\n");
							OK(connection, employee);
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
		addEmployeeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				JLabel nameLabel = new JLabel("Name : ");
				nameLabel.setBounds(14, 51, 57, 19);
				panel.add(nameLabel);
				
				JTextField inputName = new JTextField();
				inputName.setBounds(83, 48, 116, 25);
				panel.add(inputName);
				inputName.setColumns(10);
				
				JLabel idLabel = new JLabel("");
				idLabel.setBounds(14, 100, 500, 19);
				panel.add(idLabel);
				
				JButton btnNewButton = new JButton("Create employee.");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection connection = getConnection();
							AddEmployeeForm addEmployeeForm = new AddEmployeeForm();
							addEmployeeForm.setName(inputName.getText());
							String primaryKey = employeeService.addEmployee(connection, addEmployeeForm);
							idLabel.setText("New id : " + primaryKey);
							OK(connection, primaryKey);
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				btnNewButton.setBounds(14, 230, 150, 40);
				panel.add(btnNewButton);
			}
		});
		
		JMenuItem updateEmployeeMenuItem = new JMenuItem("эEmployee");
		updateEmployeeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<Employee> allEmployees = employeeService.getAll(connection);
				
				JLabel chooseIdLabel = new JLabel("Choose id");
				chooseIdLabel.setBounds(14, 42, 75, 19);
				panel.add(chooseIdLabel);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(94, 39, 404, 25);
				panel.add(comboBox);
				for(Employee employee : allEmployees) {
					comboBox.addItem(employee.getId());
				}
				
				JLabel nameLabel = new JLabel("Name : ");
				nameLabel.setBounds(14, 80, 57, 19);
				panel.add(nameLabel);
				
				JTextField inputName = new JTextField();
				inputName.setBounds(94, 77, 116, 25);
				panel.add(inputName);
				inputName.setColumns(10);
				
				JLabel leaveTimeLabel = new JLabel("Leave time : ");
				leaveTimeLabel.setBounds(14, 120, 83, 19);
				panel.add(leaveTimeLabel);
				
				JDateChooser dateChooser = new JDateChooser();
				dateChooser.setBounds(93, 115, 136, 25);
				panel.add(dateChooser);
				
				JLabel alertLabel = new JLabel("");
				alertLabel.setBounds(172, 207, 326, 19);
				panel.add(alertLabel);
				
				OK(connection, allEmployees);
				
				JButton updateButton = new JButton("Update");
				updateButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = String.valueOf(comboBox.getSelectedItem());
						
						String newName = null;
						if(inputName.getText() != null && !inputName.getText().equals("")) {
							newName = inputName.getText();
						}
						
						String leaveDate = null;
						if(dateChooser.getDate() != null) {
							long time = dateChooser.getDate().getTime();
							Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							leaveDate = format.format(time);
						}
						
						UpdateEmployeeForm updateEmployeeForm = new UpdateEmployeeForm();
						updateEmployeeForm.setName(newName);
						updateEmployeeForm.setLeaveAt(leaveDate);
						
						if(newName == null && leaveDate == null) {
							alertLabel.setText("You must input at least one.");
						} else {
							Connection connection = getConnection();
							employeeService.updateById(connection, id, updateEmployeeForm);
							OK(connection, id);
						}
					}
				});
				updateButton.setBounds(14, 196, 126, 40);
				panel.add(updateButton);
			}
		});
		
		JMenuItem deleteEmployeeMenuItem = new JMenuItem("埃Employee");
		deleteEmployeeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<Employee> allEmployees = employeeService.getAll(connection);
				
				JLabel chooseIdLabel = new JLabel("Choose id");
				chooseIdLabel.setBounds(14, 42, 75, 19);
				panel.add(chooseIdLabel);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(94, 39, 404, 25);
				panel.add(comboBox);
				for(Employee employee : allEmployees) {
					comboBox.addItem(employee.getId());
				}
				
				OK(connection, allEmployees);
				
				JButton deleteButton = new JButton("Delete");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = String.valueOf(comboBox.getSelectedItem());
						
						Connection connection = getConnection();
						employeeService.deleteById(connection, id);
						OK(connection, id);
					}
				});
				deleteButton.setBounds(14, 196, 126, 40);
				panel.add(deleteButton);
			}
		});
		
		employeeMenu.add(getAllEmployeeMenuItem);
		employeeMenu.add(getByIdEmployeeMenuItem);
		employeeMenu.add(addEmployeeMenuItem);
		employeeMenu.add(updateEmployeeMenuItem);
		employeeMenu.add(deleteEmployeeMenuItem);
		
		JMenuItem getAllFormMenuItem = new JMenuItem("琩高场Form");
		getAllFormMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				
				ArrayList<BaseForm> allBaseForm = formService.getAllBaseForm(connection);
				
				int maxPage = (allBaseForm.size()/size) + 1;
				
				JTextArea textArea = new JTextArea();
				JScrollPane scrollingArea = new JScrollPane(textArea);
				scrollingArea.setBounds(14, 40, 404, 164);
				panel.add(scrollingArea);
				
				ArrayList<BaseForm> formList = formService.getPaginationByQuery(connection, formPage, size);
				for(BaseForm baseForm : formList) {
					textArea.append("id : " + baseForm.getId() + "\n");
					textArea.append("type : " + baseForm.getType() + "\n");
					textArea.append("created by : " + baseForm.getCreateBy() + "\n");
					textArea.append("approved by : " + baseForm.getApproveBy() + "\n");
					textArea.append("agent id : " + baseForm.getAgentId() + "\n");
					textArea.append("note : " + baseForm.getNote() + "\n");
					textArea.append("created at : " + baseForm.getCreateAt() + "\n");
					textArea.append("last modified at : " + baseForm.getLastModifiedAt() + "\n\n");
				}
				
				OK(connection, formList);
				
				JButton prevButton = new JButton("<");
				prevButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(formPage > 1) {
							textArea.setText("");
							formPage--;
							try {
								Connection connection = getConnection();
								ArrayList<BaseForm> formList = formService.getPaginationByQuery(connection, formPage, size);
								for(BaseForm baseForm : formList) {
									textArea.append("id : " + baseForm.getId() + "\n");
									textArea.append("type : " + baseForm.getType() + "\n");
									textArea.append("created by : " + baseForm.getCreateBy() + "\n");
									textArea.append("approved by : " + baseForm.getApproveBy() + "\n");
									textArea.append("agent id : " + baseForm.getAgentId() + "\n");
									textArea.append("note : " + baseForm.getNote() + "\n");
									textArea.append("created at : " + baseForm.getCreateAt() + "\n");
									textArea.append("last modified at : " + baseForm.getLastModifiedAt() + "\n\n");
								}
								OK(connection, formList);
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
						if(formPage < maxPage) {
							textArea.setText("");
							formPage++;
							try {
								Connection connection = getConnection();
								ArrayList<BaseForm> formList = formService.getPaginationByQuery(connection, formPage, size);
								for(BaseForm baseForm : formList) {
									textArea.append("id : " + baseForm.getId() + "\n");
									textArea.append("type : " + baseForm.getType() + "\n");
									textArea.append("created by : " + baseForm.getCreateBy() + "\n");
									textArea.append("approved by : " + baseForm.getApproveBy() + "\n");
									textArea.append("agent id : " + baseForm.getAgentId() + "\n");
									textArea.append("note : " + baseForm.getNote() + "\n");
									textArea.append("created at : " + baseForm.getCreateAt() + "\n");
									textArea.append("last modified at : " + baseForm.getLastModifiedAt() + "\n\n");
								}
								OK(connection, formList);
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
		
		JMenuItem getByIdFormMenuItem = new JMenuItem("ID琩高Form");
		getByIdFormMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<BaseForm> allBaseForm = formService.getAllBaseForm(connection);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(14, 13, 404, 25);
				panel.add(comboBox);
				for(BaseForm baseForm : allBaseForm) {
					comboBox.addItem(baseForm.getId());
				}
				
				JTextArea textArea = new JTextArea();
				JScrollPane scrollingArea = new JScrollPane(textArea);
				scrollingArea.setBounds(14, 40, 404, 164);
				panel.add(scrollingArea);
				
				OK(connection, allBaseForm);
				
				JButton btnNewButton = new JButton("Get by id.");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textArea.setText("");
						try {
							Connection connection = getConnection();
							String id = String.valueOf(comboBox.getSelectedItem());
							BaseForm baseForm = formService.getById(connection, id);
							textArea.append("id : " + baseForm.getId() + "\n");
							textArea.append("type : " + baseForm.getType() + "\n");
							textArea.append("created by : " + baseForm.getCreateBy() + "\n");
							textArea.append("approved by : " + baseForm.getApproveBy() + "\n");
							textArea.append("agent id : " + baseForm.getAgentId() + "\n");
							textArea.append("note : " + baseForm.getNote() + "\n");
							textArea.append("created at : " + baseForm.getCreateAt() + "\n");
							textArea.append("last modified at : " + baseForm.getLastModifiedAt() + "\n");
							
							OK(connection, baseForm);
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				btnNewButton.setBounds(14, 230, 150, 40);
				panel.add(btnNewButton);
			}
		});
		
		JMenuItem addLeaveFormMenuItem = new JMenuItem("穝糤LeaveForm");
		addLeaveFormMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<Employee> allEmployees = employeeService.getAll(connection);
				
				JLabel chooseIdLabel = new JLabel("id");
				chooseIdLabel.setBounds(14, 42, 75, 19);
				panel.add(chooseIdLabel);
				
				JComboBox idComboBox = new JComboBox();
				idComboBox.setBounds(94, 39, 404, 25);
				panel.add(idComboBox);
				for(Employee employee : allEmployees) {
					idComboBox.addItem(employee.getId());
				}
				
				JComboBox agentComboBox = new JComboBox();
				agentComboBox.setBounds(94, 77, 404, 25);
				panel.add(agentComboBox);
				for(Employee employee : allEmployees) {
					agentComboBox.addItem(employee.getId());
				}
				
				JComboBox leaveTypeComboBox = new JComboBox();
				leaveTypeComboBox.setBounds(94, 152, 136, 25);
				panel.add(leaveTypeComboBox);
				for(int i = 1; i < 5; i++) {
					leaveTypeComboBox.addItem(i);
				}
				
				JLabel agentLabel = new JLabel("Agent : ");
				agentLabel.setBounds(14, 80, 57, 19);
				panel.add(agentLabel);
				
				JLabel fromLabel = new JLabel("From : ");
				fromLabel.setBounds(14, 120, 83, 19);
				panel.add(fromLabel);
				
				JLabel toLabel = new JLabel("To : ");
				toLabel.setBounds(251, 120, 83, 19);
				panel.add(toLabel);
				
				JLabel leaveTypeLabel = new JLabel("Leave type : ");
				leaveTypeLabel.setBounds(14, 150, 83, 19);
				panel.add(leaveTypeLabel);
				
				JLabel leaveReasonLabel = new JLabel("1: Special  2: Sick  3: Official  4: Personal");
				leaveReasonLabel.setBounds(244, 155, 293, 19);
				panel.add(leaveReasonLabel);
				
				JLabel reasonLabel = new JLabel("Reason : ");
				reasonLabel.setBounds(14, 186, 83, 19);
				panel.add(reasonLabel);
				
				JLabel noteLabel = new JLabel("Note : ");
				noteLabel.setBounds(14, 218, 83, 19);
				panel.add(noteLabel);
				
				JLabel alertLabel = new JLabel("");
				alertLabel.setBounds(154, 261, 292, 19);
				panel.add(alertLabel);
				
				JTextArea reasonTextArea = new JTextArea();
				reasonTextArea.setBounds(94, 184, 352, 25);
				panel.add(reasonTextArea);
				
				JTextArea noteTextArea = new JTextArea();
				noteTextArea.setBounds(94, 216, 352, 25);
				panel.add(noteTextArea);
				
				JDateChooser dateFromChooser = new JDateChooser();
				dateFromChooser.setBounds(93, 115, 136, 25);
				panel.add(dateFromChooser);
				
				JDateChooser dateToChooser = new JDateChooser();
				dateToChooser.setBounds(310, 115, 136, 25);
				panel.add(dateToChooser);
				
				OK(connection, allEmployees);
				
				JButton btnNewButton = new JButton("Add form.");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection connection = getConnection();
							String id = String.valueOf(idComboBox.getSelectedItem());
							String agent = String.valueOf(agentComboBox.getSelectedItem());
							
							Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							long timeFrom;
							String dateFrom = null;
							if(dateFromChooser.getDate() != null) {
								timeFrom = dateFromChooser.getDate().getTime();
								dateFrom = format.format(timeFrom);
							}
							
							long timeTo;
							String dateTo = null;
							if(dateToChooser.getDate() != null) {
								timeTo = dateToChooser.getDate().getTime();
								dateTo = format.format(timeTo);
							}
							
							int leaveType = (int)leaveTypeComboBox.getSelectedItem();
							
							String reason = reasonTextArea.getText();
							String note = noteTextArea.getText();
							
							if(dateFrom == null || dateTo == null || 
									leaveType < 1 || leaveType > 4 || reason == null || note == null) {
								alertLabel.setText("You must input all.");
							} else if(id.equals(agent)) {
								alertLabel.setText("You can not be agent.");
							} else {
								AddLeaveForm addLeaveForm = new AddLeaveForm();
								addLeaveForm.setCreateBy(id);
								addLeaveForm.setAgentId(agent);
								addLeaveForm.setLeaveType(leaveType);
								addLeaveForm.setReason(reason);
								addLeaveForm.setNote(note);
								addLeaveForm.setDateFrom(dateFrom);
								addLeaveForm.setDateTo(dateTo);
								String primaryKey = formService.addLeaveForm(connection, addLeaveForm);
								
								OK(connection, primaryKey);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				btnNewButton.setBounds(14, 250, 126, 40);
				panel.add(btnNewButton);
			}
		});
		
		JMenuItem addQuitFormMenuItem = new JMenuItem("穝糤QuitForm");
		addQuitFormMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<Employee> allEmployees = employeeService.getAll(connection);
				
				JLabel chooseIdLabel = new JLabel("id");
				chooseIdLabel.setBounds(14, 42, 75, 19);
				panel.add(chooseIdLabel);
				
				JComboBox idComboBox = new JComboBox();
				idComboBox.setBounds(94, 39, 404, 25);
				panel.add(idComboBox);
				for(Employee employee : allEmployees) {
					idComboBox.addItem(employee.getId());
				}
				
				JComboBox agentComboBox = new JComboBox();
				agentComboBox.setBounds(94, 77, 404, 25);
				panel.add(agentComboBox);
				for(Employee employee : allEmployees) {
					agentComboBox.addItem(employee.getId());
				}
				
				JLabel agentLabel = new JLabel("Agent : ");
				agentLabel.setBounds(14, 80, 57, 19);
				panel.add(agentLabel);
				
				JLabel quitLabel = new JLabel("Quit date : ");
				quitLabel.setBounds(14, 120, 83, 19);
				panel.add(quitLabel);
				
				JLabel reasonLabel = new JLabel("Reason : ");
				reasonLabel.setBounds(14, 186, 83, 19);
				panel.add(reasonLabel);
				
				JLabel noteLabel = new JLabel("Note : ");
				noteLabel.setBounds(14, 218, 83, 19);
				panel.add(noteLabel);
				
				JLabel alertLabel = new JLabel("");
				alertLabel.setBounds(154, 261, 292, 19);
				panel.add(alertLabel);
				
				JTextArea reasonTextArea = new JTextArea();
				reasonTextArea.setBounds(94, 184, 352, 25);
				panel.add(reasonTextArea);
				
				JTextArea noteTextArea = new JTextArea();
				noteTextArea.setBounds(94, 216, 352, 25);
				panel.add(noteTextArea);
				
				JDateChooser dateQuitChooser = new JDateChooser();
				dateQuitChooser.setBounds(93, 115, 136, 25);
				panel.add(dateQuitChooser);
				
				OK(connection, allEmployees);
				
				JButton btnNewButton = new JButton("Add form.");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection connection = getConnection();
							String id = String.valueOf(idComboBox.getSelectedItem());
							String agent = String.valueOf(agentComboBox.getSelectedItem());
							
							Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							long timeQuit;
							String dateQuit = null;
							if(dateQuitChooser.getDate() != null) {
								timeQuit = dateQuitChooser.getDate().getTime();
								dateQuit = format.format(timeQuit);
							}
							
							String reason = reasonTextArea.getText();
							String note = noteTextArea.getText();
							
							if(dateQuit == null || reason == null || note == null) {
								alertLabel.setText("You must input all.");
							} else if(id.equals(agent)) {
								alertLabel.setText("You can not be agent.");
							} else {
								AddQuitForm addQuitForm = new AddQuitForm();
								addQuitForm.setCreateBy(id);
								addQuitForm.setAgentId(agent);
								addQuitForm.setReason(reason);
								addQuitForm.setNote(note);
								addQuitForm.setQuitDate(dateQuit);;
								
								String primaryKey = formService.addQuitForm(connection, addQuitForm);
								
								OK(connection, primaryKey);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				btnNewButton.setBounds(14, 250, 126, 40);
				panel.add(btnNewButton);
			}
		});
		
		JMenuItem updateLeaveFormMenuItem = new JMenuItem("эLeaveForm");
		updateLeaveFormMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<Employee> allEmployees = employeeService.getAll(connection);
				ArrayList<LeaveForm> leaveFormList = formService.getAllLeaveForm(connection);
				
				JLabel chooseIdLabel = new JLabel("Form id");
				chooseIdLabel.setBounds(14, 42, 75, 19);
				panel.add(chooseIdLabel);
				
				JComboBox idComboBox = new JComboBox();
				idComboBox.setBounds(94, 39, 404, 25);
				panel.add(idComboBox);
				for(LeaveForm leaveForm : leaveFormList) {
					idComboBox.addItem(leaveForm.getId());
				}
				
				JComboBox agentComboBox = new JComboBox();
				agentComboBox.setBounds(94, 77, 404, 25);
				panel.add(agentComboBox);
				for(Employee employee : allEmployees) {
					agentComboBox.addItem(employee.getId());
				}
				
				JComboBox leaveTypeComboBox = new JComboBox();
				leaveTypeComboBox.setBounds(94, 152, 136, 25);
				panel.add(leaveTypeComboBox);
				for(int i = 1; i < 5; i++) {
					leaveTypeComboBox.addItem(i);
				}
				
				JLabel agentLabel = new JLabel("Agent : ");
				agentLabel.setBounds(14, 80, 57, 19);
				panel.add(agentLabel);
				
				JLabel fromLabel = new JLabel("From : ");
				fromLabel.setBounds(14, 120, 83, 19);
				panel.add(fromLabel);
				
				JLabel toLabel = new JLabel("To : ");
				toLabel.setBounds(251, 120, 83, 19);
				panel.add(toLabel);
				
				JLabel leaveTypeLabel = new JLabel("Leave type : ");
				leaveTypeLabel.setBounds(14, 150, 83, 19);
				panel.add(leaveTypeLabel);
				
				JLabel leaveReasonLabel = new JLabel("1: Special  2: Sick  3: Official  4: Personal");
				leaveReasonLabel.setBounds(244, 155, 293, 19);
				panel.add(leaveReasonLabel);
				
				JLabel reasonLabel = new JLabel("Reason : ");
				reasonLabel.setBounds(14, 186, 83, 19);
				panel.add(reasonLabel);
				
				JLabel noteLabel = new JLabel("Note : ");
				noteLabel.setBounds(14, 218, 83, 19);
				panel.add(noteLabel);
				
				JLabel alertLabel = new JLabel("");
				alertLabel.setBounds(154, 261, 292, 19);
				panel.add(alertLabel);
				
				JTextArea reasonTextArea = new JTextArea();
				reasonTextArea.setBounds(94, 184, 352, 25);
				panel.add(reasonTextArea);
				
				JTextArea noteTextArea = new JTextArea();
				noteTextArea.setBounds(94, 216, 352, 25);
				panel.add(noteTextArea);
				
				JDateChooser dateFromChooser = new JDateChooser();
				dateFromChooser.setBounds(93, 115, 136, 25);
				panel.add(dateFromChooser);
				
				JDateChooser dateToChooser = new JDateChooser();
				dateToChooser.setBounds(310, 115, 136, 25);
				panel.add(dateToChooser);
				
				OK(connection, leaveFormList);
				
				JButton btnNewButton = new JButton("Update");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection connection = getConnection();
							String id = String.valueOf(idComboBox.getSelectedItem());
							BaseForm baseForm = formService.getById(connection, id);
							LeaveForm leaveForm = formService.getLeaveFormById(connection, id);
							String agent = String.valueOf(agentComboBox.getSelectedItem());
							
							Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							long timeFrom;
							String dateFrom = null;
							if(dateFromChooser.getDate() != null) {
								timeFrom = dateFromChooser.getDate().getTime();
								dateFrom = format.format(timeFrom);
							}
							
							long timeTo;
							String dateTo = null;
							if(dateToChooser.getDate() != null) {
								timeTo = dateToChooser.getDate().getTime();
								dateTo = format.format(timeTo);
							}
							
							int leaveType = (int)leaveTypeComboBox.getSelectedItem();
							
							String reason = reasonTextArea.getText();
							String note = noteTextArea.getText();
							
							if(dateFrom == null && dateTo == null && 
									leaveType == leaveForm.getType() && reason.equals("") && note.equals("")) {
								alertLabel.setText("You must input at least one.");
							} else if(baseForm.getCreateBy().equals(agent)) {
								alertLabel.setText("You can not be agent.");
							} else {
								UpdateLeaveForm updateLeaveForm = new UpdateLeaveForm();
								updateLeaveForm.setLeaveType(leaveType);
								updateLeaveForm.setAgentId(agent);
								updateLeaveForm.setNote(note);
								updateLeaveForm.setReason(reason);
								updateLeaveForm.setDateFrom(dateFrom);
								updateLeaveForm.setDateTo(dateTo);
								formService.updateLeaveFormById(connection, id, updateLeaveForm);
								
								OK(connection, updateLeaveForm);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				btnNewButton.setBounds(14, 250, 126, 40);
				panel.add(btnNewButton);
			}
		});
		
		JMenuItem updateQuitFormMenuItem = new JMenuItem("эQuitForm");
		updateQuitFormMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<Employee> allEmployees = employeeService.getAll(connection);
				ArrayList<QuitForm> quitFormList = formService.getAllQuitForm(connection);
				
				JLabel chooseIdLabel = new JLabel("Form id");
				chooseIdLabel.setBounds(14, 42, 75, 19);
				panel.add(chooseIdLabel);
				
				JComboBox idComboBox = new JComboBox();
				idComboBox.setBounds(94, 39, 404, 25);
				panel.add(idComboBox);
				for(QuitForm quitForm : quitFormList) {
					idComboBox.addItem(quitForm.getId());
				}
				
				JComboBox agentComboBox = new JComboBox();
				agentComboBox.setBounds(94, 77, 404, 25);
				panel.add(agentComboBox);
				for(Employee employee : allEmployees) {
					agentComboBox.addItem(employee.getId());
				}
				
				JLabel agentLabel = new JLabel("Agent : ");
				agentLabel.setBounds(14, 80, 57, 19);
				panel.add(agentLabel);
				
				JLabel quitLabel = new JLabel("Quit date : ");
				quitLabel.setBounds(14, 120, 83, 19);
				panel.add(quitLabel);
				
				JLabel reasonLabel = new JLabel("Reason : ");
				reasonLabel.setBounds(14, 186, 83, 19);
				panel.add(reasonLabel);
				
				JLabel noteLabel = new JLabel("Note : ");
				noteLabel.setBounds(14, 218, 83, 19);
				panel.add(noteLabel);
				
				JLabel alertLabel = new JLabel("");
				alertLabel.setBounds(154, 261, 292, 19);
				panel.add(alertLabel);
				
				JTextArea reasonTextArea = new JTextArea();
				reasonTextArea.setBounds(94, 184, 352, 25);
				panel.add(reasonTextArea);
				
				JTextArea noteTextArea = new JTextArea();
				noteTextArea.setBounds(94, 216, 352, 25);
				panel.add(noteTextArea);
				
				JDateChooser dateQuitChooser = new JDateChooser();
				dateQuitChooser.setBounds(93, 115, 136, 25);
				panel.add(dateQuitChooser);
				
				OK(connection, allEmployees);
				
				JButton btnNewButton = new JButton("Update");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection connection = getConnection();
							String id = String.valueOf(idComboBox.getSelectedItem());
							BaseForm baseForm = formService.getById(connection, id);
							String agent = String.valueOf(agentComboBox.getSelectedItem());
							
							Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							long timeQuit;
							String dateQuit = null;
							if(dateQuitChooser.getDate() != null) {
								timeQuit = dateQuitChooser.getDate().getTime();
								dateQuit = format.format(timeQuit);
							}
							
							String reason = reasonTextArea.getText();
							String note = noteTextArea.getText();
							
							if(dateQuit == null && reason == null && note == null) {
								alertLabel.setText("You must input at least one.");
							} else if(baseForm.getCreateBy().equals(agent)) {
								alertLabel.setText("You can not be agent.");
							} else {
								UpdateQuitForm updateQuitForm = new UpdateQuitForm();
								updateQuitForm.setAgentId(agent);
								updateQuitForm.setReason(reason);
								updateQuitForm.setNote(note);
								updateQuitForm.setQuitDate(dateQuit);
								
								formService.updateQuitFormById(connection, id, updateQuitForm);
								
								OK(connection, updateQuitForm);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				btnNewButton.setBounds(14, 250, 126, 40);
				panel.add(btnNewButton);
			}
		});
		
		JMenuItem approveFormMenuItem = new JMenuItem("Form");
		approveFormMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<Employee> allEmployees = employeeService.getAll(connection);
				ArrayList<BaseForm> allBaseForm = formService.getAllBaseForm(connection);
				
				JLabel chooseIdLabel = new JLabel("Form id");
				chooseIdLabel.setBounds(14, 42, 75, 19);
				panel.add(chooseIdLabel);
				
				JComboBox idComboBox = new JComboBox();
				idComboBox.setBounds(94, 39, 404, 25);
				panel.add(idComboBox);
				for(BaseForm baseForm : allBaseForm) {
					idComboBox.addItem(baseForm.getId());
				}
				
				JComboBox approverComboBox = new JComboBox();
				approverComboBox.setBounds(94, 77, 404, 25);
				panel.add(approverComboBox);
				for(Employee employee : allEmployees) {
					approverComboBox.addItem(employee.getId());
				}
				
				JLabel approverLabel = new JLabel("Approver : ");
				approverLabel.setBounds(14, 80, 75, 19);
				panel.add(approverLabel);
				
				JLabel reasonLabel = new JLabel("Reason : ");
				reasonLabel.setBounds(14, 120, 83, 19);
				panel.add(reasonLabel);
				
				JLabel noteLabel = new JLabel("Note : ");
				noteLabel.setBounds(14, 150, 83, 19);
				panel.add(noteLabel);
				
				JLabel reasonDetailLabel = new JLabel("");
				reasonDetailLabel.setBounds(94, 120, 404, 19);
				panel.add(reasonDetailLabel);
				
				JLabel noteDetailLabel = new JLabel("");
				noteDetailLabel.setBounds(94, 150, 404, 19);
				panel.add(noteDetailLabel);
				
				JLabel alertLabel = new JLabel("");
				alertLabel.setBounds(14, 186, 449, 19);
				panel.add(alertLabel);
				
				OK(connection, allBaseForm);
				
				JButton approveButton = new JButton("Approve");
				approveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection connection = getConnection();
							String id = String.valueOf(idComboBox.getSelectedItem());
							BaseForm baseForm = formService.getById(connection, id);
							LeaveForm leaveForm = formService.getLeaveFormById(connection, id);
							String approver = String.valueOf(approverComboBox.getSelectedItem());
							
							ApproveForm approveForm = new ApproveForm();
							if(baseForm.getApproveBy() == null) {
								approveForm.setApprovedBy(approver);
								formService.approveById(connection, id, approveForm);
							} else {
								alertLabel.setText("This form is approved.");
							}
							
							OK(connection, approveForm);
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				approveButton.setBounds(14, 250, 126, 40);
				panel.add(approveButton);
				
				JButton detailButton = new JButton("Detail");
				detailButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection connection = getConnection();
							String id = String.valueOf(idComboBox.getSelectedItem());
							BaseForm baseForm = formService.getById(connection, id);
							if(baseForm.getType() == 1) {
								LeaveForm leaveForm = formService.getLeaveFormById(connection, id);
								reasonDetailLabel.setText(leaveForm.getReason());
							} else if(baseForm.getType() == 2) {
								QuitForm quitForm = formService.getQuitFormById(connection, id);
								reasonDetailLabel.setText(quitForm.getReason());
							}
							noteDetailLabel.setText(baseForm.getNote());
							
							OK(connection, id);
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				detailButton.setBounds(164, 250, 126, 40);
				panel.add(detailButton);
			}
		});
		
		JMenuItem deleteFormMenuItem = new JMenuItem("埃Form");
		deleteFormMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<BaseForm> allBaseForm = formService.getAllBaseForm(connection);
				
				JLabel chooseIdLabel = new JLabel("Form id");
				chooseIdLabel.setBounds(14, 42, 75, 19);
				panel.add(chooseIdLabel);
				
				JComboBox idComboBox = new JComboBox();
				idComboBox.setBounds(94, 39, 404, 25);
				panel.add(idComboBox);
				for(BaseForm baseForm : allBaseForm) {
					idComboBox.addItem(baseForm.getId());
				}
				
				OK(connection, allBaseForm);
				
				JButton deleteButton = new JButton("Delete");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection connection = getConnection();
							String id = String.valueOf(idComboBox.getSelectedItem());
							
							formService.deleteById(connection, id);
							OK(connection, id);
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				deleteButton.setBounds(14, 250, 126, 40);
				panel.add(deleteButton);
			}
		});
		
		formMenu.add(getAllFormMenuItem);
		formMenu.add(getByIdFormMenuItem);
		formMenu.add(addLeaveFormMenuItem);
		formMenu.add(addQuitFormMenuItem);
		formMenu.add(updateLeaveFormMenuItem);
		formMenu.add(updateQuitFormMenuItem);
		formMenu.add(approveFormMenuItem);
		formMenu.add(deleteFormMenuItem);
		
		JMenuItem getAllTimeRecordMenuItem = new JMenuItem("琩高场TimeRecord");
		getAllTimeRecordMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<TimeClockRecord> allTimeClockRecords = timeClockRecordService.getAll(connection);
				
				int maxPage = (allTimeClockRecords.size()/size) + 1;
				
				JTextArea textArea = new JTextArea();
				JScrollPane scrollingArea = new JScrollPane(textArea);
				scrollingArea.setBounds(14, 40, 404, 164);
				panel.add(scrollingArea);
				
				ArrayList<TimeClockRecord> timeClockRecordList = timeClockRecordService.getPaginationByQuery(connection, timeClockRecordPage, size);
				for(TimeClockRecord timeClockRecord : timeClockRecordList) {
					textArea.append("id : " + timeClockRecord.getId() + "\n");
					textArea.append("type : " + timeClockRecord.getType() + "\n");
					textArea.append("employee id : " + timeClockRecord.getEmployeeId() + "\n");
					textArea.append("create at : " + timeClockRecord.getCreateAt() + "\n\n");
				}
				OK(connection, timeClockRecordList);
				
				JButton prevButton = new JButton("<");
				prevButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(timeClockRecordPage > 1) {
							textArea.setText("");
							timeClockRecordPage--;
							try {
								Connection connection = getConnection();
								ArrayList<TimeClockRecord> timeClockRecordList = timeClockRecordService.getPaginationByQuery(connection, timeClockRecordPage, size);
								for(TimeClockRecord timeClockRecord : timeClockRecordList) {
									textArea.append("id : " + timeClockRecord.getId() + "\n");
									textArea.append("type : " + timeClockRecord.getType() + "\n");
									textArea.append("employee id : " + timeClockRecord.getEmployeeId() + "\n");
									textArea.append("create at : " + timeClockRecord.getCreateAt() + "\n\n");
								}
								OK(connection, timeClockRecordList);
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
						if(timeClockRecordPage < maxPage) {
							textArea.setText("");
							timeClockRecordPage++;
							try {
								Connection connection = getConnection();
								ArrayList<TimeClockRecord> timeClockRecordList = timeClockRecordService.getPaginationByQuery(connection, timeClockRecordPage, size);
								for(TimeClockRecord timeClockRecord : timeClockRecordList) {
									textArea.append("id : " + timeClockRecord.getId() + "\n");
									textArea.append("type : " + timeClockRecord.getType() + "\n");
									textArea.append("employee id : " + timeClockRecord.getEmployeeId() + "\n");
									textArea.append("create at : " + timeClockRecord.getCreateAt() + "\n\n");
								}
								OK(connection, timeClockRecordList);
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
		
		JMenuItem getByIdTimeRecordMenuItem = new JMenuItem("ID琩高TimeRecord");
		getByIdTimeRecordMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(panel);
				frame.getContentPane().repaint();
				panel = new JPanel();
				panel.setBounds(0, 0, 543, 348);
				panel.setLayout(null);
				frame.getContentPane().add(panel);
				
				Connection connection = getConnection();
				ArrayList<TimeClockRecord> allTimeClockRecords = timeClockRecordService.getAll(connection);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(14, 13, 404, 25);
				panel.add(comboBox);
				for(TimeClockRecord timeClockRecord : allTimeClockRecords) {
					comboBox.addItem(timeClockRecord.getId());
				}
				
				JTextArea textArea = new JTextArea();
				JScrollPane scrollingArea = new JScrollPane(textArea);
				scrollingArea.setBounds(14, 40, 404, 164);
				panel.add(scrollingArea);
				
				OK(connection, allTimeClockRecords);
				
				JButton btnNewButton = new JButton("Get by id.");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textArea.setText("");
						try {
							Connection connection = getConnection();
							String id = String.valueOf(comboBox.getSelectedItem());
							TimeClockRecord timeClockRecord = timeClockRecordService.getById(connection, id);
							textArea.append("id : " + timeClockRecord.getId() + "\n");
							textArea.append("type : " + timeClockRecord.getType() + "\n");
							textArea.append("employee id : " + timeClockRecord.getEmployeeId() + "\n");
							textArea.append("create at : " + timeClockRecord.getCreateAt() + "\n\n");
							OK(connection, timeClockRecord);
						} catch (Exception e1) {
							e1.printStackTrace();
						}  
					}
				});
				btnNewButton.setBounds(14, 230, 150, 40);
				panel.add(btnNewButton);
			}
		});
		timeRecordMenu.add(getAllTimeRecordMenuItem);
		timeRecordMenu.add(getByIdTimeRecordMenuItem);
		
		menuBar.add(employeeMenu);
		menuBar.add(formMenu);
		menuBar.add(timeRecordMenu);
		
	}
}
