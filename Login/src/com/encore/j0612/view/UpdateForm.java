package com.encore.j0612.view;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.encore.j0612.model.vo.MembershipVO;

public class UpdateForm extends JFrame{
   public JTextField tf_id, tf_name, tf_ssn1, tf_phone1, tf_phone2, tf_phone3, tf_addr;
   public JPasswordField tf_pass, tf_pass2, tf_ssn2;
   public JButton bt_submit, bt_reset;
   public JComboBox<String> cb_job;
          JLabel [] label;
   
	
   public UpdateForm() {
	  setTitle("ȸ����������");
	  
	  tf_id = new JTextField("abcd");
	    //tf_id.setEnabled(false);//��Ȱ��ȭ
	    tf_id.setEditable(false);//�����Ҵ�
	  tf_name = new JTextField();
	    tf_name.setEditable(false);
	  tf_ssn1 = new JTextField();
	    tf_ssn1.setEditable(false);
	  tf_phone1 = new JTextField();
	  tf_phone2 = new JTextField();
	  tf_phone3 = new JTextField();
	  tf_addr = new JTextField();
	  
	  tf_pass = new JPasswordField();
	  tf_pass2 = new JPasswordField();
	  tf_ssn2 = new JPasswordField();
	    tf_ssn2.setEditable(false);
	  
	  bt_submit = new JButton("���");
	  bt_reset = new JButton("���");
		  
	  String []jobTitle= {"�л�","������","���/����","����/����","�Ϲݻ繫��",
			  "������","���/������","����/�Ƿ�","�ڿ���","�ֺ�","��Ÿ" };

	  cb_job = new JComboBox<String>(jobTitle);
	  
	  //----------------------JLabel����-------------------------------------
	  String []labelTitle= {"I  D","��  ��","���Ȯ��","��  ��","�ֹι�ȣ",
			  "��ȭ��ȣ","��  ��","��  ��","-","-","-"};
	  label = new JLabel[labelTitle.length];
	  
	  for (int i = 0; i < labelTitle.length; i++) {
		 if(i<8)
		  label[i] = new JLabel(labelTitle[i]+":");
		 else
		  label[i] = new JLabel(labelTitle[i]);
		  //label[i].setFont(new Font("����", Font.BOLD, 20));
	  }
	  
	  //---------------------������Ʈ �ٿ���--------------------------
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
	  
	  setBounds(350,200,310,450);
	  //setVisible(true);
   }//������
   
   public void initText(MembershipVO vo) {
	   String pass = vo.getPass();
	   //String phone = vo.getPhone();//"010-1234-5678"
	   String []phone = vo.getPhone().split("-");// {"010","1234","5678"}
	   
	   tf_id.setText(vo.getId());
	   tf_pass.setText(pass);
	   tf_pass2.setText(pass);
	   tf_name.setText(vo.getName());
	   tf_ssn1.setText(vo.getSsn1()+"");
	   tf_ssn2.setText(vo.getSsn2()+"");
	   tf_phone1.setText(phone[0]);
	   tf_phone2.setText(phone[1]);
	   tf_phone3.setText(phone[2]);
	   tf_addr.setText(vo.getAddr());
	   
	   cb_job.setSelectedItem(vo.getJob());
	    
   }//initText
   
   public void showMsg(String msg) {
	   JOptionPane.showMessageDialog(this, msg);
   }//showMsg
   
}//JoinForm







