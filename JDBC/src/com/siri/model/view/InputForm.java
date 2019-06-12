package com.siri.model.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class InputForm extends JFrame {
	// 일정하면 배열, 일정치 않으면 벡터

	// 객체 선언

	JLabel jName;
	JLabel jAge;
	JLabel jJob;
	JLabel space;

	public JTextField tf_name;
	public JTextField tf_age;
	public JTextField tf_job;

	public JButton bt_submit;
	public JButton bt_cancel;

	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;

	public InputForm() {
		// 객체 생성

		jName = new JLabel("이름");
		jAge = new JLabel("나이");
		jJob = new JLabel("직업");
		space = new JLabel("\n");

		tf_name = new JTextField(10);
		tf_age = new JTextField(10);
		tf_job = new JTextField(10);

		bt_submit = new JButton("입력");
		bt_cancel = new JButton("취소");

		panel4 = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		setLayout(new GridLayout(5, 1));
		// 이름
		panel1.setLayout(new FlowLayout());
		panel1.add(jName);
		panel1.add(tf_name);
		// 나이
		panel2.setLayout(new FlowLayout());
		panel2.add(jAge);
		panel2.add(tf_age);
		// 직업
		panel3.setLayout(new FlowLayout());
		panel3.add(jJob);
		panel3.add(tf_job);
		// 입력 취소 버튼
		panel4.setLayout(new FlowLayout());
		panel4.add(bt_submit);
		panel4.add(bt_cancel);

		// 세로정렬!
		add(space);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);

		// 보여지기
		setSize(250, 300);
		// setVisible(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE); // 전체 자바 응용 프로그램의미

	}

	public void initText() {
		tf_name.setText("");
		tf_age.setText("");
		tf_job.setText("");
	}
	
	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
}
