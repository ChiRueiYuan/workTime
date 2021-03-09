package workTime.main.window;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import workTime.main.controller.BaseController;
import workTime.main.form.AddLeaveForm;
import workTime.main.form.UpdateEmployeeForm;
import workTime.main.model.Employee;
import workTime.main.service.EmployeeService;
import workTime.main.service.impl.EmployeeServiceImpl;

import javax.swing.JComboBox;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

public class demo extends BaseController {
	EmployeeService employeeService = new EmployeeServiceImpl<>();

	JFrame frame;
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
		
		JLabel chooseIdLabel = new JLabel("id");
		chooseIdLabel.setBounds(14, 42, 75, 19);
		frame.getContentPane().add(chooseIdLabel);
		
		JComboBox idComboBox = new JComboBox();
		idComboBox.setBounds(94, 39, 404, 25);
		frame.getContentPane().add(idComboBox);
		for(Employee employee : allEmployees) {
			idComboBox.addItem(employee.getId());
		}
		
		JComboBox agentComboBox = new JComboBox();
		agentComboBox.setBounds(94, 77, 404, 25);
		frame.getContentPane().add(agentComboBox);
		for(Employee employee : allEmployees) {
			agentComboBox.addItem(employee.getId());
		}
		
		JComboBox leaveTypeComboBox = new JComboBox();
		leaveTypeComboBox.setBounds(94, 152, 136, 25);
		frame.getContentPane().add(leaveTypeComboBox);
		for(int i = 1; i < 5; i++) {
			agentComboBox.addItem(i);
		}
		
		JLabel agentLabel = new JLabel("Agent : ");
		agentLabel.setBounds(14, 80, 75, 19);
		frame.getContentPane().add(agentLabel);
		
		JLabel fromLabel = new JLabel("From : ");
		fromLabel.setBounds(14, 120, 83, 19);
		frame.getContentPane().add(fromLabel);
		
		JLabel toLabel = new JLabel("To : ");
		toLabel.setBounds(251, 120, 83, 19);
		frame.getContentPane().add(toLabel);
		
		JLabel leaveTypeLabel = new JLabel("Leave type : ");
		leaveTypeLabel.setBounds(14, 150, 83, 19);
		frame.getContentPane().add(leaveTypeLabel);
		
		JLabel leaveReasonLabel = new JLabel("1: Special  2: Sick  3: Official  4: Personal");
		leaveReasonLabel.setBounds(244, 155, 293, 19);
		frame.getContentPane().add(leaveReasonLabel);
		
		JLabel reasonLabel = new JLabel("Reason : ");
		reasonLabel.setBounds(14, 186, 449, 19);
		frame.getContentPane().add(reasonLabel);
		
		JLabel noteLabel = new JLabel("Note : ");
		noteLabel.setBounds(14, 218, 83, 19);
		frame.getContentPane().add(noteLabel);
		
		JTextArea reasonTextArea = new JTextArea();
		reasonTextArea.setBounds(94, 184, 352, 25);
		frame.getContentPane().add(reasonTextArea);
		
		JTextArea noteTextArea = new JTextArea();
		noteTextArea.setBounds(94, 216, 352, 25);
		frame.getContentPane().add(noteTextArea);
		
		JDateChooser dateFromChooser = new JDateChooser();
		dateFromChooser.setBounds(93, 115, 136, 25);
		frame.getContentPane().add(dateFromChooser);
		
		JDateChooser dateToChooser = new JDateChooser();
		dateToChooser.setBounds(310, 115, 136, 25);
		frame.getContentPane().add(dateToChooser);
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = String.valueOf(idComboBox.getSelectedItem());
				String agent = String.valueOf(agentComboBox.getSelectedItem());
				
				Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long timeFrom = dateFromChooser.getDate().getTime();
				String dateFrom = format.format(timeFrom);
				
				long timeTo = dateToChooser.getDate().getTime();
				String dateTo = format.format(timeTo);
				
				int leaveType = (int)leaveTypeComboBox.getSelectedItem();
				
				String reason = reasonTextArea.getText();
				String note = noteTextArea.getText();
				
				AddLeaveForm addLeaveForm = new AddLeaveForm();
				addLeaveForm.setCreateBy(id);
				addLeaveForm.setAgentId(agent);
				addLeaveForm.setLeaveType(leaveType);
				addLeaveForm.setReason(reason);
				addLeaveForm.setNote(note);
				addLeaveForm.setDateFrom(dateFrom);
				addLeaveForm.setDateTo(dateTo);
			}
		});
		updateButton.setBounds(14, 250, 126, 40);
		frame.getContentPane().add(updateButton);
	}
}
