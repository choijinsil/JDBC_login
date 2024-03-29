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
	public JPasswordField tf_pass, tf_pass2, tf_ssn2; // 姥歳鞠嬢走澗 腰硲
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

	String job[] = { "俳持", "因巷据", "情経/窒毒", "浦昔/井茸", "析鋼紫巷送", "慎穣送", "奄綬/穿庚送", "左闇/税戟", "切慎穣", "爽採", "奄展" };

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

	public void cc() { // 傾戚焼数 持失切
		setTitle("噺据亜脊");
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
		cb_job.setPrototypeDisplayValue("けけけけけけけけけ");// 覗稽塘展脊 - 奄沙堂 (背雁庚切伸 掩戚稽)
		bt_submit = new JButton("去系");
		bt_reset = new JButton("昼社");
		bt_checkid = new JButton("掻差溌昔");
		la_id = new JLabel("焼  戚  巨: ");
		la_pass = new JLabel("搾        腰: ");
		la_pass2 = new JLabel("搾腰溌昔: ");
		la_name = new JLabel("戚        硯: ");
		la_ssn = new JLabel("爽肯腰硲: ");
		la_phone = new JLabel("穿鉢腰硲: ");
		la_addr = new JLabel("爽        社: ");
		la_job = new JLabel("送        穣: ");

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
//				// char研 庚切稽 痕井 廃庚切梢 啄微陥.
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
