package com.encore.j0612.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinForm extends JFrame{
   public JTextField tf_id, tf_name, tf_ssn1, tf_phone1, tf_phone2, tf_phone3, tf_addr;
   public JPasswordField tf_pass, tf_pass2, tf_ssn2;
   public JButton bt_submit, bt_reset, bt_checkid;
   public JComboBox<String> cb_job;
          JLabel [] label;
   
   public JLabel la_id_check;
	
   public JoinForm() {
	  setTitle("회원가입");
	  
	  la_id_check = new JLabel();
	  
	  tf_id = new JTextField();
	    //tf_id.setEditable(false);
	  tf_name = new JTextField();
	  tf_ssn1 = new JTextField();
	  tf_phone1 = new JTextField();
	  tf_phone2 = new JTextField();
	  tf_phone3 = new JTextField();
	  tf_addr = new JTextField();
	  
	  tf_pass = new JPasswordField();
	  tf_pass2 = new JPasswordField();
	  tf_ssn2 = new JPasswordField();
	  
	  bt_submit = new JButton("등록");
	  bt_reset = new JButton("취소");
	  bt_checkid = new JButton("중복확인");
	  
	  String []jobTitle= {"학생","공무원","언론/출판","군인/경찰","일반사무직",
			  "영업직","기술/전문직","보건/의료","자영업","주부","기타" };

	  cb_job = new JComboBox<String>(jobTitle);
	  
	  //----------------------JLabel생성-------------------------------------
	  String []labelTitle= {"I  D","비  번","비번확인","이  름","주민번호",
			  "전화번호","주  소","직  업","-","-","-"};
	  label = new JLabel[labelTitle.length];
	  
	  for (int i = 0; i < labelTitle.length; i++) {
		 if(i<8)
		  label[i] = new JLabel(labelTitle[i]+":");
		 else
		  label[i] = new JLabel(labelTitle[i]);
		  //label[i].setFont(new Font("굴림", Font.BOLD, 20));
	  }
	  
	  //---------------------컴포넌트 바운즈--------------------------
	  tf_id.setBounds(80,30,100,25);
	  tf_pass.setBounds(80,70,100,25);
	  tf_pass2.setBounds(80,110,100,25);
	  tf_name.setBounds(80,150,100,25);
	  
	  tf_ssn1.setBounds(80,190,50,25);
	    label[8].setBounds(131, 190, 8,25);
	  tf_ssn2.setBounds(140,190,50,25);
	  
	  tf_phone1.setBounds(80,230,40,25);
	    label[9].setBounds(121, 230, 8,25);
	  tf_phone2.setBounds(130,230,40,25);
	    label[10].setBounds(171, 230, 8,25);
	  tf_phone3.setBounds(180,230,40,25);
	  
	  tf_addr.setBounds(80,270,200,25);
	  cb_job.setBounds(80,310,100,25);
	  
	  bt_submit.setBounds(50,360,90,25);
	  bt_reset.setBounds(150,360,90,25);
	  bt_checkid.setBounds(190,30,90,25);
	  la_id_check.setBounds(190,30,100,25);
	  //-----------------------------------------------------------
	  setLayout(null);
	  
	  int y=30;
	  for (int i = 0; i < label.length-3; i++) {
		  label[i].setBounds(10, y, 100, 25);
		  add(label[i]);
		  y+=40;
	  }
	  
	  add(label[8]);//"-"
	  add(label[9]);
	  add(label[10]);
	  
	  add(tf_id);
	  add(tf_pass);
	  add(tf_pass2);
	  add(tf_name);
	  add(tf_ssn1);
	  add(tf_ssn2);
	  add(tf_phone1);
	  add(tf_phone2);
	  add(tf_phone3);
	  add(tf_addr);
	  
	  add(cb_job);
	  
	  add(bt_submit);
	  add(bt_reset);
	  //add(bt_checkid);
	  add(la_id_check);
	  
	  setBounds(350,200,310,450);
	  //setVisible(true);
   }//생성자
   
   public void showMsg(String msg) {
	   JOptionPane.showMessageDialog(this, msg);
   }//showMsg
   
   public String showInput(String msg) {
	   return JOptionPane.showInputDialog(this, msg);
   }//showInput
   
   public int showConfirm(String msg) {
	   return JOptionPane.showConfirmDialog(this, msg);
   }//showConfirm
   
}//JoinForm







