package main;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class demo {

	private JFrame frame;

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
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 227);
		frame.getContentPane().add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu employeeMenu = new JMenu("Employee");
		JMenu formMenu = new JMenu("Form");
		JMenu timeRecordMenu = new JMenu("Time Record");
		
		JMenuItem getAllEmployeeMenuItem = new JMenuItem("琩高场Employee");
		JMenuItem getByIdEmployeeMenuItem = new JMenuItem("ID琩高Employee");
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
