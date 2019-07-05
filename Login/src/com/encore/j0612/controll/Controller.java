package com.encore.j0612.controll;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;

import com.encore.j0612.model.dao.MembershipDAO;
import com.encore.j0612.model.vo.MembershipVO;
import com.encore.j0612.view.JoinForm;
import com.encore.j0612.view.LoginForm;
import com.encore.j0612.view.ServiceForm;
import com.encore.j0612.view.UpdateForm;

public class Controller implements ActionListener{
   LoginForm loginForm;	//���ۺ�
   JoinForm  joinForm;
   UpdateForm  updateForm;
   ServiceForm  serviceForm;
  	
   public Controller() {
	  loginForm = new LoginForm();
	  //joinForm   = new JoinForm();
	  updateForm  = new UpdateForm();
	  serviceForm  = new ServiceForm();
	  
	  eventUp();
   }//������	
	
   private void eventUp() {//�̺�Ʈ �ҽ� ���  
	  //�α�����
	  loginForm.bt_login.addActionListener(this); 
	  loginForm.bt_join.addActionListener(this);
	  loginForm.tf_pass.addActionListener(this);
	  
	  
	  //������
	  serviceForm.bt_sel_all.addActionListener(this); 
	  serviceForm.bt_up.addActionListener(this);
	  serviceForm.bt_del.addActionListener(this);
	  serviceForm.bt_sel_name.addActionListener(this);
	  serviceForm.bt_exit.addActionListener(this);
	  serviceForm.item_confirm.addActionListener(this);
	  
	  serviceForm.addWindowListener(new WindowAdapter() {
		     //������X��ư ----> �α��� ��		
		public void windowClosing(WindowEvent e) {
		   serviceForm.setVisible(false);
		   loginForm.setVisible(true);
		}});
	  
	  //ȸ��������
	  updateForm.bt_submit.addActionListener(this);
	  updateForm.addWindowListener(new WindowAdapter() {
		     //ȸ��������X��ư ----> ������
		public void windowClosing(WindowEvent e) {
		   updateForm.setVisible(false);
		   serviceForm.setVisible(true);
	  }});
	  
   }//eventUp
   
   private void eventUp_join() {
	 //ȸ��������
		  joinForm.bt_submit.addActionListener(this);
		  joinForm.bt_checkid.addActionListener(this);
//		  joinForm.tf_id.addMouseListener(new MouseAdapter() {
//		         @Override
//		        public void mouseClicked(MouseEvent e) {
//		        	 checkId();
//		        }
//		  });
		  joinForm.tf_id.addKeyListener(new KeyAdapter() {
			  //  �����߻� --> keyPressedȣ��  --> 'a'UI�� ��� --->  keyReleasedȣ��
//			@Override
//			public void keyPressed(KeyEvent e) {//Ű���尡 �������� ȣ��
//				System.out.println("keyPressed==> "+ joinForm.tf_id.getText());
//			}

			@Override
			public void keyReleased(KeyEvent e) {//���ȴ� Ű���带 ��������
				//System.out.println("keyReleased==> "+ joinForm.tf_id.getText());
				MembershipDAO dao = new MembershipDAO();
				if(dao.findExistId(joinForm.tf_id.getText())== 1) {//�ߺ��� ���̵� ���
					joinForm.la_id_check.setForeground(Color.RED);
					joinForm.la_id_check.setText("������ξ��̵�");
				}else {
					joinForm.la_id_check.setForeground(Color.GREEN);
					joinForm.la_id_check.setText("��밡�ɾ��̵�");
				}
				
			}
		} );
		  
		  joinForm.tf_ssn1.addKeyListener(new KeyAdapter() {
			  @Override
			public void keyReleased(KeyEvent e) {
			      if(joinForm.tf_ssn1.getText().length()==6) {
			    	  joinForm.tf_ssn2.requestFocus();
			      }
			}
		  });
		  
		  
		  joinForm.addWindowListener(new WindowAdapter() {
		     //ȸ��������X��ư ----> �α��� ��		
			public void windowClosing(WindowEvent e) {
			   joinForm.setVisible(false);
			   loginForm.setVisible(true);
			}});
		  
   }

   @Override
   public void actionPerformed(ActionEvent e) {
	   Object ob = e.getSource();//�̺�Ʈ �߻���Ų ������Ʈ�� �ּ�
	   
	   if(ob==loginForm.bt_login || ob==loginForm.tf_pass) {
		   //1. �α�����: �α��ι�ư Ŭ�� ==> DB�α�����ȸ ,  ���������� �̵�
		   //2. UI�Է� ������ ���
		      String id = loginForm.tf_id.getText();
		      String pass = new String(loginForm.tf_pass.getPassword());
		                  //--------------------------------
		                  //              char []
		   /*
		             str.toCharArray()              str.getBytes()
		            <---------------               ------------>
		   char []ch               String str="abc"            byte []b  
		    {'a','b','c'}                                      {97,98,99}
		             ------------->                 <------------
		       String s1 =new String(ch);        String s2 = new String(b);
		             ----                               ---
		             "abc"                             "abc"
		             
		   */
		      
		   //3. ��ȣ��
		    MembershipDAO dao = new MembershipDAO();
		    if(dao.findLogin(id, pass)) {
		    	loginForm.showMsg("�α��μ���^O^*");
		    	
		    	serviceForm.displayTable(dao.findAll());
		    	serviceForm.loginId=id;
		    	
		    	loginForm.initText();
		    	
		    	loginForm.setVisible(false);
		    	serviceForm.setVisible(true);
		    }else {
		    	//loginForm.showMsg("�α��ν���T.T*");
		    	loginForm.showMsg("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�!!");
		    }
		    
//		     String result = dao.findLogin(id, pass);
//		     if(result.equals("success")) {
//		    	 loginForm.showMsg("�α��μ���^O^*");
//		     }else if(result.equals("fail_id")) {
//		    	 loginForm.showMsg("���̵� �������� �ʽ��ϴ�!!");
//		    	 //confirm("ȸ�������Ͻðڽ��ϱ�?");
//		     }else if(result.equals("fail_pass")) {
//		    	 loginForm.showMsg("��й�ȣ�� ��ġ���� �ʽ��ϴ�!!");
//		     }
		      
		   //4. ������ �̵�
	   }else if(joinForm !=null && ob== joinForm.bt_submit) {//ȸ��������: ��Ϲ�ư Ŭ����
		   MembershipDAO dao = new MembershipDAO();
		   
		 //�÷�: id,pass,name,ssn1,ssn2,phone,addr,job
		   //UI�� �Էµ� ������ ����!!
		  String id = joinForm.tf_id.getText();	
		  
		  //����� ���̵� ���� �˻�!!
		  //if(dao.findExistId(id)==1) {//�̹� ����� ���̵� �����Ѵٸ�
		  //if(joinForm.la_id_check.getText().equals("������ξ��̵�")) {
		  if(joinForm.la_id_check.getForeground() == Color.RED) {
			joinForm.showMsg("�̹� ������� ���̵� �Դϴ�!!");
			joinForm.tf_id.setText("");
			joinForm.tf_id.requestFocus();
			return;
		  }
		  
		  String pass = new String(joinForm.tf_pass.getPassword());
		  String name = joinForm.tf_name.getText();
		  String ssn1 = joinForm.tf_ssn1.getText();
		  String ssn2 = new String(joinForm.tf_ssn2.getPassword());
		  String phone = joinForm.tf_phone1.getText()+"-"+
				         joinForm.tf_phone2.getText()+"-"+
				         joinForm.tf_phone3.getText();
		  //phone="010-1234-5678"
		  String addr = joinForm.tf_addr.getText();
		  String job = joinForm.cb_job.getSelectedItem().toString();

		  MembershipVO vo = new MembershipVO(id, pass, name, 
				                 Integer.parseInt(ssn1), 
				                 Integer.parseInt(ssn2), phone, addr, job);
		   
		   //��
		   if(dao.create(vo)) {//DB�Է��� �����ߴٸ� (ȸ�����Լ���)
			   joinForm.showMsg("ȸ�����Լ���!!");
			   //ȸ��������  ----> �α�����   �̵�
			   joinForm.setVisible(false);
			   loginForm.setVisible(true);
		   }else {
			   joinForm.showMsg("�Է°��� Ȯ���ϼ���!!");
		   }
		   
	   }else if(ob == loginForm.bt_join) {//�α����� : �ű԰��Թ�ư Ŭ����
		   joinForm = new JoinForm();
		   eventUp_join();
		   loginForm.setVisible(false);
		   joinForm.setVisible(true);
	   }else if(ob == serviceForm.bt_sel_all) {//������: ��ü���� ��ư Ŭ����
		   MembershipDAO dao = new MembershipDAO();
		   ArrayList<MembershipVO> list = dao.findAll();
		   serviceForm.displayTable(list);
	   }else if(ob == serviceForm.bt_up) {//������: ���� ��ư Ŭ����
		   //findByIdȣ���� ���� ���̵� �� ������
		   String id;
		   if(serviceForm.authState)
		     id = serviceForm.showInput("������ ���̵�:");//������ ������
		   else
		     id = serviceForm.loginId;//������ ��������, �α��� ���̵�
		   
		   
		   //------------------------------------------------
		   MembershipDAO dao = new MembershipDAO();
		   MembershipVO vo = dao.findById(id);
		   //------------------------------------------------
		   if(vo == null){ //DB��  ��ġ�ϴ� ���̵� �������� �ʴ´ٸ�
		     serviceForm.showMsg("�������� �ʴ� ���̵��Դϴ�!!");
			   return; 
		   } 
		   //�������� ��ȸ���(vo)�� ���!!
		   updateForm.initText(vo);
		   
		   //�̵� : ������  ---> ȸ������ ������
		   serviceForm.setVisible(false);
		   updateForm.setVisible(true);
	   }else if(ob == updateForm.bt_submit) {//ȸ������������ : ���(����)��ư Ŭ����
		   //������ ����(������) ����		  
		   String phone = updateForm.tf_phone1.getText()+"-"+
		                  updateForm.tf_phone2.getText()+"-"+
		                  updateForm.tf_phone3.getText();		  
		   
		   //������ ������ �Ѱ��� ������(vo)���� ����
		   MembershipVO vo = new MembershipVO();
		     vo.setId(updateForm.tf_id.getText());
		     vo.setPass(new String(updateForm.tf_pass.getPassword()));
		     vo.setPhone(phone);
		     vo.setAddr(updateForm.tf_addr.getText());
		     vo.setJob(updateForm.cb_job.getSelectedItem().toString());
		   //----------------------------------------------------
		   MembershipDAO dao = new MembershipDAO();
		    if(dao.modify(vo)) {
		   //----------------------------------------------------
		   //DAO�������� �ݿ�(���̵�, ���� ������ ����)
		       serviceForm.displayTable(dao.findAll());	
		    	
		       updateForm.setVisible(false);
		       serviceForm.setVisible(true);
		    }else {
		       updateForm.showMsg("�Էµ� �����͸� Ȯ���ϼ���!!");
		    }
	   }else if(ob == serviceForm.bt_del) {//������: ������ư Ŭ����
		   String id;
		     if(serviceForm.authState)//�����ڶ��(������ ���� �ߴٸ�)
		       id = serviceForm.showInput("������ ���̵�:");
		     else//�Ϲ� ����ڶ��
		       id = serviceForm.loginId;
		   
		   MembershipDAO dao = new MembershipDAO();
		     if(serviceForm.showConfirm("���� ����/Ż�� �Ͻðڽ��ϱ�?")==0) {
		   
			     if(dao.remove(id)) {//���� ������
			    	serviceForm.displayTable(dao.findAll());
			     }else {
			    	serviceForm.showMsg("������ �����Ͽ����ϴ�!!"); 
			     }
		     }
		   
		   if(!serviceForm.authState) {
			 //�Ϲݻ������ ��� ������ �α��� ������ �̵�
			   serviceForm.setVisible(false);
			   loginForm.setVisible(true);
		   }
		     
		     
	   }else if(ob == serviceForm.bt_sel_name) {
		   //������: '�̸��˻�' ��ư Ŭ����   ----> '�˻�' ��ư Ŭ����
		   //String name = serviceForm.showInput("��ȸ�� �̸�:");
		   Map<String,String> map = serviceForm.showOption();
		   
		   //-----------------------------------------------
		   MembershipDAO dao = new MembershipDAO();
		   // ArrayList<MembershipVO> list = dao.findByName(name);
		   ArrayList<MembershipVO> list = dao.findSearch(map);
		   //-----------------------------------------------
		   //��ȸ�� ����� ��(JTable)�� �ݿ�
		   serviceForm.displayTable(list); 
		   
	   }else if(joinForm !=null && ob == joinForm.bt_checkid) {//ȸ��������: �ߺ�Ȯ�� ��ư Ŭ����
		   checkId();
		     
	   }else if(ob == serviceForm.item_confirm) {
		   //������: '������'�޴� - '����'������ Ŭ����
		   if(serviceForm.loginId.equals("admin")) {//admin ���̵�� �α��� ���� ��
			   serviceForm.showMsg("�����Ǿ����ϴ�!!");
			   serviceForm.authState=true;
		   }else {//admin�ƴ� ���̵�� �α��� ���� ��
		       serviceForm.showMsg("������ �α����� �ʿ��մϴ�!!");
		       if(serviceForm.showConfirm("�����ڷ� �α����Ͻðڽ��ϱ�?")==0) {
		          serviceForm.setVisible(false);
		          loginForm.setVisible(true);
		       }
		   }
		   
	   }else if(ob == serviceForm.bt_exit) {//������: ���� ��ư Ŭ����
		   serviceForm.authState=false;
		   serviceForm.setVisible(false);
		   loginForm.setVisible(true);
	   }
	   
   }//actionPerformed
   
   
   public void checkId() {
	   MembershipDAO dao = new MembershipDAO();
//	   if(dao.findExistId(joinForm.tf_id.getText()) == 1) {
//		   joinForm.showMsg("�̹� ������� ���̵��Դϴ�!!");
//	   }else {
//		   joinForm.showMsg("��밡���� ���̵��Դϴ�!!");
//	   }
	   
	   String id = joinForm.showInput("���̵��Է�:");
	   if(dao.findExistId(id) == 1) {
		   joinForm.showMsg("�̹� ������� ���̵��Դϴ�!!");
	   }else {
		   joinForm.showMsg("��밡���� ���̵��Դϴ�!!");
		   if(joinForm.showConfirm("�� ���̵� ����Ͻðڽ��ϱ�?") == 0) {
			   joinForm.tf_id.setText(id); 
		   }
	   }
   }//checkId
   
   
   public static void main(String[] args) {
	  new Controller();
   }

}



