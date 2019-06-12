package com.teacher.model.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InputForm extends JFrame{
   JLabel la_name, la_age, la_job;
   public JTextField tf_name, tf_age, tf_job;
   
   public JButton bt_submit, bt_cancel;	
	
   public InputForm() {
	  setTitle("입력폼");
	  
	  la_name = new JLabel("이름");
	  la_age = new JLabel("나이");
	  la_job = new JLabel("직업");
	  
	  tf_name = new JTextField();
	  tf_age  = new JTextField();
	  tf_job  = new JTextField();
	  
	  bt_submit = new JButton("입력");
	  bt_cancel = new JButton("취소");
	
	  //la_name.setBounds(x, y, width, height);
	  //프레임에 붙는 컴포넌트에 시작좌표(location)와 사이즈를 설정하는 이유? NullLayout인 컨테이너에 부착시킬 때 필요.
	  la_name.setBounds(30,30, 30,30);
	  la_age.setBounds(30,80, 30,30);
	  la_job.setBounds(30,130, 30,30);
	  
	  tf_name.setBounds(70,30, 120,30);
	  tf_age.setBounds(70,80, 120,30);
	  tf_job.setBounds(70,130, 120,30);

	  bt_submit.setBounds(40,180, 70,30);
	  bt_cancel.setBounds(120,180, 70,30);
	  	  
	  setLayout(null);
	   add(la_name);
	   add(la_age);
	   add(la_job);
	   add(tf_name);
	   add(tf_age);
	   add(tf_job);
	   add(bt_submit);
	   add(bt_cancel);
	  
	  setBounds(500,200,240,280);
	  //setVisible(true);
	  //setDefaultCloseOperation(EXIT_ON_CLOSE); //전체 '자바응용프로그램 종료' 의미
   }//생성자
   
   public void showMsg(String msg) {
	   JOptionPane.showMessageDialog(this, msg);
   }//showMsg
   
   public void initText(){
	   tf_name.setText("");   
	   tf_age.setText("");   
	   tf_job.setText("");
	   tf_name.requestFocus();
   }
   
}//InputForm









