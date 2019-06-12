package com.teacher.model.controll;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.teacher.model.dao.PersonDAO;
import com.teacher.model.dto.Person;
import com.teacher.model.view.InputForm;
import com.teacher.model.view.MainView;
import com.teacher.model.view.UpForm;


public class Controller implements ActionListener{
   /*
     <��Ʈ�ѷ��� ����>�� - ��ü ���α׷��� ���� �帧 ����!!
     1. ����� ��û �м� (��: � ��ư�� ��������)
     2. ����� �Է� ������ ������ (��: �Է��� �Ǵ� �������� ���� �Էµ� ������)
     3. �ڸ𵨰�ü����(��: PersonDAO)
           - �޼ҵ�ȣ��
           - ����� (���ϰ�) ���, ����, �Ǵ�
     4. ������(��) �̵�  (��: ����(JTable)  ----> �Է���)
     
        ���û���) ��ȿ�� �˻�(valid check) <=== ����ڰ� �Է��� �����Ϳ� ����!!
      	
   */
	
   //����
	MainView mainView;
	InputForm form;
	UpForm upForm;
	
	
   public Controller() {
	  //��ü����
	  mainView = new MainView();
	      mainView.displayTable(new PersonDAO().selectAll());
	  form     = new InputForm();
	  upForm   = new UpForm();
	  
	  
	  eventUp(); 
   }//������
   
   private void eventUp() {//�̺�Ʈ �ҽ� ���
	  //���κ�
	   mainView.bt_insert.addActionListener(this);
	   mainView.bt_update.addActionListener(this);
	   mainView.bt_del.addActionListener(this);
	   mainView.bt_exit.addActionListener(this);
	   
	  //�Է���
	   form.bt_submit.addActionListener(this);
	   //form == JFrame == Window
	   form.addWindowListener(new WindowAdapter() {
		                  //class ���� extends WindowAdapter{  }
		   @Override
		  public void windowClosing(WindowEvent e) {//������ ������ �������X��ư Ŭ����
			  form.setVisible(false);
			  mainView.setVisible(true);
		  }   
	   });
	   
	   
	  //������
	   upForm.bt_submit.addActionListener(this);
	   upForm.bt_cancel.addActionListener(this);
	   upForm.addWindowListener(new WindowAdapter() {
           //class ���� extends WindowAdapter{  }
			@Override
			public void windowClosing(WindowEvent e) {//������ ������ �������X��ư Ŭ����
			  upForm.setVisible(false);
			  mainView.setVisible(true);
			}   
       }); 
	
   }//eventUp

@Override
   public void actionPerformed(ActionEvent e) {
	 Object ob = e.getSource();//action�̺�Ʈ�� �߻���Ų �̺�Ʈ �ҽ��� �ּ� ���
      
	 if(ob==mainView.bt_insert) {//1.���κ� : �Է¹�ư   ==> �м�: �Է�����û!!
		//4.���κ�  ---> �Է���   �̵�!! 
		 
		 form.initText();
		 mainView.setVisible(false);
		 form.setVisible(true);
	 }else if(ob==form.bt_submit) {//1.�Է���: �Է¹�ư ==> �м�: DB�Է� ��û!!
		 
		 //2. �Էµ����� ���
		 String name = form.tf_name.getText().trim();
		 String age = form.tf_age.getText().trim();
		 String job = form.tf_job.getText().trim();
		 
		 
		 //5(�ɼ�). ��ȿ�� �˻�
		 
		 //�� üũ,���ڰ˻�
		 if(name.equals("")) {  form.showMsg("�̸��Է�!!");  
		                        return;}
		 else if(age.length()==0) {  form.showMsg("�����Է�!!");
		                        return;}
		 else if(!age.matches("[\\d]{1,3}")) {
			 form.showMsg("���ڸ��Է�!!");
			 return;
		 }else if(job.length()<1)  {  form.showMsg("�����Է�!!");
		                        return;}
			 
		 
		 //������ ������  p������ �����ϱ�(�Ѱ��� ���������� �����ϱ�)
		 Person p = new Person(0, name, Integer.parseInt(age), job);
		 
		 //3. �� ��ü����
		 PersonDAO dao = new PersonDAO();
		   if(dao.insert(p)) {//�Է¼���
			  //���κ信 ����� (DB�κ���) ��ü ������ ���
			  ArrayList<Person> list = dao.selectAll();
			  //Person []perArr = (Person[]) list.toArray();
			  mainView.displayTable(list);
			   
			  //�Է��� ----> ���κ�
			  form.setVisible(false);
			  mainView.setVisible(true);
		   }
	 }else if(ob==mainView.bt_update) {
		 //1. ���κ�: ������ưŬ���� ---> ������ ��û
		 
		 int row = mainView.table.getSelectedRow();
		 
		 if(row==-1) {//���õ� ���� ���ٸ�
		  //JOptionPane.showMessageDialog(mainView, "������ ���� ����!!");
		   mainView.showMsg("������ ���� ����!!");
		  return;	 
		 }
		 
		 //System.out.println("���õ� �� �ε���>>>"+ row);
		 //2.
		 Object value = mainView.table.getValueAt(row, 0);
		                              //0 column: ��ȣǥ��
		 //System.out.println("���õ� ��ȣ>>>"+ value);
		 int no = Integer.parseInt(value.toString());
				  //            3 <---- "3"
		 
		 upForm.upNo = no;//������ ����� '��ȣ'����
		 
		 //3.
		 PersonDAO dao = new PersonDAO();
		   Person p = dao.select(no);
		   //DB��ȸ��  ----> ������ ����
		  /* 
		   p.getName()      upForm.tf_name.setText();
		   p.getAge()       upForm.tf_name.setText();
		   p.getJob()       upForm.tf_name.setText();
		  */
		   
		   upForm.old_name=p.getName();
		   upForm.old_age=p.getAge()+"";
		   upForm.old_job=p.getJob();
		   
		   upForm.tf_name.setText(p.getName());
		   upForm.tf_age.setText(p.getAge()+"");
		   //tf_age.setText(13); (X) tf_age.setText("13"); (O) 
		   upForm.tf_job.setText(p.getJob());
		 
		 //4.
		 mainView.setVisible(false);
		 upForm.setVisible(true);
	 }else if(ob == upForm.bt_submit) {
		 //1. ������: ������ư Ŭ���� ----> DB���� ��û��
		 
		 //2.
		 String age =upForm.tf_age.getText();
		 String job = upForm.tf_job.getText();
		 
		//�� üũ,���ڰ˻�
		 if(age.length()==0) {  form.showMsg("�����Է�!!");
		                        return;}
		 else if(!age.matches("[\\d]{1,3}")) {
			 form.showMsg("���ڸ��Է�!!");
			 return;
		 }else if(job.length()<1)  {  form.showMsg("�����Է�!!");
		                        return;}
		 
		 
		 
		 //upForm.upNo//������ ����� ����� '��ȣ' ã��
		 
		 Person p = new Person();//�⺻������   : (no=0, name=null, age=0, job=null)
		    p.setAge(Integer.parseInt(age));//: (no=0, name=null, age=20, job=null)
		    p.setJob(job);//: (no=0, name=null, age=20, job="�屺")
		    p.setNo(upForm.upNo);//: (no=4, name=null, age=20, job="�屺")
		//Person p = new Person(upForm.upNo, null,Integer.parseInt(age), job);//�����ε� ������
		    
		    
		 //3.
		 PersonDAO dao = new PersonDAO();
		 if(dao.update(p)) {//������ �ߵǾ��ٸ�   ---> JTable�� ���������� �ݿ�
			 mainView.displayTable(dao.selectAll());//���� ����
			 
			 upForm.setVisible(false);
			 mainView.setVisible(true);
		 }
		 
	 }else if(ob == mainView.bt_del) {
		 //1.������ : ������ư Ŭ����   ----> DB���� ��û��
		 
		 //2.(������ ��ȣ)������ ���
		 String no = mainView.showInput("������ ��ȣ:");
		 //��ҹ�ư �Ǵ� X��ư Ŭ���� null ����
		 
		 if(no==null) {return;}
		 
		 if(/*no != null &&*/ !no.matches("[\\d]+")) {//nullüũ�� ����üũ
			 mainView.showMsg("���ڸ� �Է�!!");
			 return;
		 }
		 
		 if(mainView.showConfirm("���� �����Ͻðڽ��ϱ�?")==0) {
			 //3.
			 PersonDAO dao = new PersonDAO();
			 if(dao.delete(Integer.parseInt(no))) {
				 mainView.showMsg("��������!!");
				 mainView.displayTable(dao.selectAll());
			 }else {
				 mainView.showMsg("��������!!");
			 }
		 }//confirm ==>��!!
		 
	 }else if(ob == upForm.bt_cancel) {
		 upForm.initText();
	 }else if(ob == mainView.bt_exit) {//1. ���κ�: �����ư Ŭ����  ----> ���α׷� ����
		 System.exit(0);
	 }
	   
   }//actionPerformed
   
   
   
   public static void main(String[] args) {
	  new Controller();
   }

}



