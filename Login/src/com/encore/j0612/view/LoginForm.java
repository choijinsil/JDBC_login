package com.encore.j0612.view;

import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame 
{
    public JTextField tf_id;
    public JPasswordField tf_pass;
    public JButton bt_login,bt_join;
	JLabel la_id, la_pass;
	
  public LoginForm()
	{		
		setTitle("Login Form");
		
		tf_id = new JTextField();			  
		tf_pass = new JPasswordField();
		
		bt_login = new JButton("로그인");
		bt_join = new JButton("신규가입");
		 bt_join.setFocusable(false);
	
	    la_id = new JLabel("I  D");
		la_pass = new JLabel("Password");
		
		tf_id.setBounds(80,30,100,25);
		tf_pass.setBounds(80,65,100,25);
		bt_login.setBounds(90,110,80,25);
		bt_join.setBounds(190,30,90,25);
		la_id.setBounds(8,30,80,25);
		la_pass.setBounds(8,65,90,25);
		
		setLayout(null);
		add(tf_id);
		add(tf_pass);
		add(bt_login);
		add(bt_join);
		add(la_id);
		add(la_pass);
		
		setBounds(400,300,300,180);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//X버튼클릭시 전체 응용프로그램 종료
	
	}//생성자   
  
    public void showMsg(String msg) {
    	JOptionPane.showMessageDialog(this, msg);
    }//showMsg
    
    public void initText() {
    	tf_id.setText("");
    	tf_pass.setText("");
    	tf_id.requestFocus();
    }
    
}//LoginForm







