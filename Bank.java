package com.emrebank.bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Bank extends JFrame{
	public static String directory = System.getProperty("user.dir");
	private static final long serialVersionUID = 1L;
	public static FileManagement fileManage = new FileManagement();
	public static int Money = 0;
	public static JLabel lblName;
	public static JLabel lblOutput;
	public static File moneyAccounts = new File(directory + "/moneyAmount.txt");
	public static ArrayList<String> moneyAmounts = new ArrayList<String>();
	public Bank() {
		setTitle("Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		lblName = new JLabel("");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(6, 96, 878, 67);
		getContentPane().add(lblName);

		JTextArea WithdrawOrDeposit = new JTextArea();
		WithdrawOrDeposit.setWrapStyleWord(true);
		WithdrawOrDeposit.setLineWrap(true);
		WithdrawOrDeposit.setBounds(179, 224, 610, 142);
		getContentPane().add(WithdrawOrDeposit);

		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Withdrawal = Integer.parseInt(WithdrawOrDeposit.getText());
				Money =  Money - Withdrawal;
				lblOutput.setText("Removed " + Withdrawal + " dollars from your account.");
			}
		});
		btnWithdraw.setBounds(6, 219, 117, 29);
		getContentPane().add(btnWithdraw);

		JButton btnReceive = new JButton("Deposit");
		btnReceive.setBounds(6, 260, 117, 29);
		btnReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Deposited = Integer.parseInt(WithdrawOrDeposit.getText());
				Money = Deposited + Money;
				lblOutput.setText("Added " + Deposited + " dollars to your account.");

			}
		});
		getContentPane().add(btnReceive);

		lblOutput = new JLabel("");
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBounds(189, 378, 600, 67);
		getContentPane().add(lblOutput);

		setSize(890,490);
		setVisible(true);
		while (true) {
			
			if (!moneyAccounts.exists()) {
				fileManage.createFile(moneyAccounts);
			}
		}


	}
	public void setNameAndMoney(String name, int money) {
		lblName.setText(name + ": $" + money);
	}
	
	public static void main(String[] args) { 
		new Bank();
	}
}
