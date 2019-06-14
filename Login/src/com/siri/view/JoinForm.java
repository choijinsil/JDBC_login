package com.siri.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.siri.model.dao.MembershipDAO;

public class JoinForm extends JFrame {
	JLabel la_id, la_pass, la_pass2, la_name, la_ssn, la_phone, la_addr, la_job;

	public JTextField tf_id, tf_name, tf_addr, tf_phone1, tf_phone2, tf_phone3, tf_ssn1;
	public JPasswordField tf_pass, tf_pass2, tf_ssn2; // 구분되어지는 번호
	public JComboBox<String> cb_job;

	public JButton bt_submit, bt_reset, bt_checkid;
	//public String ck_str;
	//public boolean isOk;
	Panel p1;
	Panel p2;
	Panel p3;
	Panel p4;
	Panel p5;
	Panel p6;
	Panel p7;
	Panel p8;
	Panel p9;

	String job[] = { "학생", "공무원", "언론/출판", "군인/경찰", "일반사무직", "영업직", "기술/전문직", "보건/의료", "자영업", "주부", "기타" };

	public JoinForm() {
		cc();

		p1.add(la_id);
		p1.add(tf_id);
		p1.add(bt_checkid);

		p2.add(la_pass);
		p2.add(tf_pass);

		p3.add(la_pass2);
		p3.add(tf_pass2);

		p4.add(la_name);
		p4.add(tf_name);

		p5.add(la_ssn);
		p5.add(tf_ssn1);
		p5.add(new JLabel("-"));
		p5.add(tf_ssn2);

		p6.add(la_phone);
		p6.add(tf_phone1);
		p6.add(new JLabel("-"));
		p6.add(tf_phone2);
		p6.add(new JLabel("-"));
		p6.add(tf_phone3);

		p7.add(la_addr);
		p7.add(tf_addr);

		p8.add(la_job);
		p8.add(cb_job);

		p9.add(bt_submit);

		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		add(p7);
		add(p8);
		add(p9, new FlowLayout(FlowLayout.CENTER));

		setSize(300, 400);
		setVisible(true);

	}

	public void cc() { // 레이아웃 생성자
		setTitle("회원가입");
		setLayout(new FlowLayout(FlowLayout.LEFT));

		tf_id = new JTextField(10);
		tf_pass = new JPasswordField(10);
		tf_pass2 = new JPasswordField(10);
		tf_name = new JTextField(10);
		tf_ssn1 = new JTextField(5);
		tf_ssn2 = new JPasswordField(5);
		tf_phone1 = new JTextField(3);
		tf_phone2 = new JTextField(3);
		tf_phone3 = new JTextField(3);
		tf_addr = new JTextField(12);
		cb_job = new JComboBox<String>(job);
		cb_job.setPrototypeDisplayValue("ㅁㅁㅁㅁㅁㅁㅁㅁㅁ");// 프로토타입 - 기본틀 (해당문자열 길이로)
		bt_submit = new JButton("등록");
		bt_reset = new JButton("취소");
		bt_checkid = new JButton("중복확인");
		la_id = new JLabel("아  이  디: ");
		la_pass = new JLabel("비        번: ");
		la_pass2 = new JLabel("비번확인: ");
		la_name = new JLabel("이        름: ");
		la_ssn = new JLabel("주민번호: ");
		la_phone = new JLabel("전화번호: ");
		la_addr = new JLabel("주        소: ");
		la_job = new JLabel("직        업: ");

//		ck_str = new String();
		//isOk = true;

		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		p5 = new Panel();
		p6 = new Panel();
		p7 = new Panel();
		p8 = new Panel();
		p9 = new Panel();
		
//		tf_id.addKeyListener(new KeyListener() {
//
//			@Override
//			public void keyTyped(KeyEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//				//isOk = false;
//
//			}
//
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// System.out.println(e.getKeyChar());
//				// char를 문자로 변경 한문자씩 찍힌다.
//				ck_str = Character.toString(e.getKeyChar());
//
//				System.out.print(ck_str);
//
//			}
//		});

	}
	
	public void initText() {
		tf_addr.setText("");
		tf_id.setText("");
		tf_name.setText("");
		tf_pass.setText("");
		tf_pass2.setText("");
		tf_phone1.setText("");
		tf_phone2.setText("");
		tf_phone3.setText("");
		tf_ssn1.setText("");
		tf_ssn2.setText("");
		
		
	}

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

}
