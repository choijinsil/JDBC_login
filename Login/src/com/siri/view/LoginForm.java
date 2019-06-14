package com.siri.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

public class LoginForm extends JFrame {
	public JTextField tf_id;
	public JPasswordField tf_pass;
	public JButton bt_login;
	public JButton bt_join;
	JLabel la_id;
	JLabel la_pass;
	JLabel la_blank;

	JPanel northP, centerP, southP;

	public LoginForm() {
		setTitle("Login Form");
		setLayout(new BorderLayout());
		// setLayout(new GridLayout(3, 3,10,20));

		tf_id = new JTextField(10);
		tf_pass = new JPasswordField(10);
		bt_login = new JButton("로그인");
		bt_join = new JButton("신규가입");
		northP = new JPanel();
		centerP = new JPanel();
		southP = new JPanel();

		la_id = new JLabel("ID               ");
		la_pass = new JLabel("Password");
		// la_blank = new JLabel();

		northP.add(la_id);
		northP.add(tf_id);

		centerP.add(la_pass);
		centerP.add(tf_pass);

		southP.add(bt_login);
		southP.add(bt_join);

		add("North", northP);
		add("Center", centerP);
		add("South", southP);

		// add(new JLabel());

		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void initText() {
		tf_id.setText("");
		tf_pass.setText("");
	}

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
}
