package com.siri.view;

import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.siri.model.dao.MembershipDAO;
import com.siri.model.vo.MembershipVO;

public class UpdateForm extends JFrame {
	// joinform差紫
	// id, 戚硯, 爽肯腰硲 畷増 災管
	// 掻差溌昔 獄動 薦暗
	JLabel la_id, la_pass, la_pass2, la_name, la_ssn, la_phone, la_addr, la_job;

	public JTextField tf_id,  tf_name, tf_addr, tf_phone1, tf_phone2, tf_phone3, tf_ssn1;
	public JPasswordField tf_pass, tf_pass2,tf_ssn2; // 姥歳鞠嬢走澗 腰硲
	public JComboBox<String> cb_job;

	public JButton bt_submit, bt_reset;
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

	public UpdateForm() {
		cc();

		p1.add(la_id);
		p1.add(tf_id);

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
		// tf_id.setEnabled(false);//搾醗失鉢
		tf_id.setEditable(false);// 畷増災管
		tf_pass = new JPasswordField(10);
		tf_pass2 = new JPasswordField(10);
		tf_name = new JTextField(10);
		tf_name.setEditable(false);

		tf_ssn1 = new JTextField(5);
		tf_ssn1.setEditable(false);
		tf_ssn2 = new JPasswordField(5);
		tf_ssn2.setEditable(false);
		
		tf_phone1 = new JTextField(3);
		
		tf_phone2 = new JTextField(3);
		
		tf_phone3 = new JTextField(3);
		
		tf_addr = new JTextField(12);
		cb_job = new JComboBox<String>(job);
		cb_job.setPrototypeDisplayValue("けけけけけけけけけ");// 覗稽塘展脊 - 奄沙堂 (背雁庚切伸 掩戚稽)
		bt_submit = new JButton("去系");
		bt_reset = new JButton("昼社");
		la_id = new JLabel("焼  戚  巨: ");
		la_pass = new JLabel("搾        腰: ");
		la_pass2 = new JLabel("搾腰溌昔: ");
		la_name = new JLabel("戚        硯: ");
		la_ssn = new JLabel("爽肯腰硲: ");
		la_phone = new JLabel("穿鉢腰硲: ");
		la_addr = new JLabel("爽        社: ");
		la_job = new JLabel("送        穣: ");

		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		p5 = new Panel();
		p6 = new Panel();
		p7 = new Panel();
		p8 = new Panel();
		p9 = new Panel();

	}

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void settings(MembershipVO vo) {
		tf_id.setText(vo.getId());
		tf_pass.setText(vo.getPass());
		tf_pass2.setText(vo.getPass());
		tf_name.setText(vo.getName());
		tf_ssn1.setText(vo.getSsn1() + "");
		tf_ssn2.setText(vo.getSsn1() + "");
		String[] str = vo.getPhone().split("-");
		tf_phone1.setText(str[0]);
		tf_phone2.setText(str[1]);
		tf_phone3.setText(str[2]);
		tf_addr.setText(vo.getAddr());
		cb_job.setSelectedItem(vo.getJob().toString());
	}
	
//	public MembershipVO gettings() {
//		String id=tf_id.getText().toString();
//		String phone= tf_phone1.getText()+"-"+tf_phone2.getText()+"-"+tf_phone3.getText();
//		String addr= tf_addr.getText();
//		String job= cb_job.getSelectedObjects().toString();
//		String pass= new String(tf_pass.getPassword());
//		MembershipVO vo = new MembershipVO(id,phone,addr,job,pass);
//		
//		return vo;
//	}

}