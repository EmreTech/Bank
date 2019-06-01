package com.emrebank.bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Login extends JFrame {
	public static String directory = System.getProperty("user.dir");
	public static File accounts = new File(directory + "/accounts.txt");
	public static FileManagement filemanage = new FileManagement();
	public ArrayList<String> accountsList;
	public boolean mainProgramRun = false;
	public Login() {
		getContentPane().setBackground(Color.GREEN);
		setBackground(Color.GREEN);
		setTitle("Bank Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("Login & Register");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(246, 6, 398, 96);
		getContentPane().add(label);

		user = new JTextField();
		user.setBounds(380, 130, 130, 26);
		getContentPane().add(user);
		user.setColumns(10);

		label_1 = new JLabel("Name:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(328, 135, 50, 16);
		getContentPane().add(label_1);

		label_2 = new JLabel("Password:");
		label_2.setBounds(315, 178, 63, 16);
		getContentPane().add(label_2);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = user.getText();
				pass = new String(password.getPassword());
				filemanage.readLineFromFile(accounts, accountsList);
				/*System.out.println(accountsList);
				System.out.println(name + " " + pass); */
				for (int x = 0; x < accountsList.size(); x++) {
					boolean nameInList = accountsList.get(x).contains(name);
					boolean passInList = accountsList.get(x).contains(pass);
					if (nameInList == passInList) {
						mainProgramRun = true;
						JOptionPane.showMessageDialog(null, 
								"Logged in with no errors. Welcome to EmreBank.");
						//System.out.println(mainProgramRun);
					}
				}

			}
		});
		btnLogin.setBounds(198, 272, 117, 29);
		getContentPane().add(btnLogin);

		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = user.getText();
				pass = new String(password.getPassword());
				/*System.out.println(directory);
				System.out.println(accountsList);
				System.out.println(name + " " + pass); */              
				filemanage.appendToFile(accounts, name + "\n" + pass);
				JOptionPane.showMessageDialog(null, 
						"Created account with no errors. Please login with your new acount.");

			}
		});
		btnRegister.setBounds(527, 272, 117, 29);
		getContentPane().add(btnRegister);

		password = new JPasswordField();
		password.setEchoChar('*');
		password.setBounds(380, 173, 130, 26);
		getContentPane().add(password);

		message = new JOptionPane();
		getContentPane().add(message);
		
		setSize(890,490);
		
		while (true) {
			accountsList = filemanage.returnAccountsList();
			if (!accounts.exists())
				filemanage.createFile(accounts);
			if (mainProgramRun == true) {
				exit();
			}
		}
	}
	
	public static void exit() {
		new Bank();
	}
	public String returnName() {
		return name;
	}
	

	private static final long serialVersionUID = 1L;
	private JTextField user;
	private JLabel label_1;
	private JLabel label_2;
	private JButton btnLogin;
	private JButton btnRegister;
	private JPasswordField password;
	public static JOptionPane message;
	public String name;
	public String pass;

	//@SuppressWarnings("static-access")
	
}
