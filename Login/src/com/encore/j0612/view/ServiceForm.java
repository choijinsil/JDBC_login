package com.encore.j0612.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.encore.j0612.model.vo.MembershipVO;


public class ServiceForm extends JFrame {
	
   DefaultTableModel dtm;
   public JTable table;
   //public �������? �ٸ� ��Ű���� �ִ� Ŭ����(��Ʈ�ѷ�)���� ����ϱ� ����.
   JScrollPane scroll_table;
   
   public JButton bt_sel_all, bt_del, bt_up, bt_sel_name, bt_exit;
   
   JPanel southp;
   public String loginId;//�α����� ���̵� ����
   public boolean authState;  //�������¸� ǥ�� authentication
   
   
   public JMenuItem item_confirm;
   JMenu     menu_admin;
   JMenuBar  bar;
   
   //ù°�гο� ��ġ�� ������Ʈ  ==> ���� ��ư 3��
   JRadioButton radio_id, radio_name, radio_addr;
   ButtonGroup group;
   
   //��°�гο� ��ġ�� ������Ʈ    ==> ��, �ؽ�Ʈ�ʵ�
   JTextField  tf_search;
   
   JPanel panel_first, panel_second, panel_option;
   

   public ServiceForm() {
	  setTitle("Display Data");
	  
	  radio_id = new JRadioButton("���̵�",true);
	  radio_name = new JRadioButton("�̸�");
	  radio_addr = new JRadioButton("�ּ�");
	  
	  group = new ButtonGroup();
	     group.add(radio_id);
	     group.add(radio_name);
	     group.add(radio_addr);
	     
	  panel_first = new JPanel();
	     panel_first.add(radio_id);
	     panel_first.add(radio_name);
	     panel_first.add(radio_addr);
	     
	  tf_search = new JTextField(15);   
	  panel_second = new JPanel();
	     panel_second.add(new JLabel("�˻���:"));
	     panel_second.add(tf_search);
	     
	  panel_option = new JPanel();  
	  panel_option.setLayout(new GridLayout(2,1));
	      panel_option.add(panel_first);   
	      panel_option.add(panel_second);   
	     
	  
	  item_confirm = new JMenuItem("����");
	  
	  menu_admin = new JMenu("������");
	     menu_admin.add(item_confirm);
	     
	  bar  = new JMenuBar();
	     bar.add(menu_admin);
	     
	  setJMenuBar(bar);   
	     
	     
	  
	  Object [][]rowData = new Object[0][7];	  
	  String []columTitle = {"ID","�̸�","����","����","��ȭ��ȣ","�ּ�","����"};	  
	  dtm = new DefaultTableModel(rowData,columTitle);
	  
	  table = new JTable(dtm);
	  scroll_table = new JScrollPane(table);
	  
	  
	  bt_sel_all = new JButton("��ü����");
	  bt_up = new JButton("����");
	  bt_del = new JButton("����");
	  //bt_sel_name = new JButton("�̸��˻�");
	  bt_sel_name = new JButton("�˻�");
	  bt_exit = new JButton("����");
	  
	  southp = new JPanel();
	    southp.add(bt_sel_all);
	    southp.add(bt_up);
	    southp.add(bt_del);
	    southp.add(bt_sel_name);
	    southp.add(bt_exit);
	    
	  add("Center", scroll_table);
	  add("South", southp);
	  
	  setBounds(300,200,450,300);
	  //setVisible(true);
	  
   }//������
   
   public void showMsg(String msg) {
	   JOptionPane.showMessageDialog(this, msg);
   }//showMsg
   
   public String showInput(String msg) {
	   return JOptionPane.showInputDialog(this, msg);
   }//showInput
   
   public int showConfirm(String msg) {
	   return JOptionPane.showConfirmDialog(this, msg);
   }//showConfirm
   
   public Map<String, String> showOption() {
	   //JOptionPane.showOptionDialog(parentComponent, message, title, 
	   //optionType, messageType, icon, options, initialValue)
	   String []options={"Ȯ��","���"};
	  int t = JOptionPane.showOptionDialog(this, panel_option, "Search",
			   JOptionPane.OK_CANCEL_OPTION ,
			   JOptionPane.PLAIN_MESSAGE,//JOptionPane.ERROR_MESSAGE, 
			   null, 
			   null,//options,
			   null);//options[1])
	 //t==> Ȯ��, ���, X  : 0, 1, -1  
	  
	  String keyword= tf_search.getText();
	  String title="";// ���� ���� ��ư �� ���̵� ���õǾ��ٸ�  title= "���̵�";
	                //"���̵�" , "�̸�", "�ּ�"
	  
	  if(radio_id.isSelected()) {//��ư�׷� �� ���̵� ���õǾ��ٸ�
		  title="���̵�";  //title=radio_id.getText();
	  }else if(radio_name.isSelected()) {//��ư�׷� �� �̸��� ���õǾ��ٸ�
		  title="�̸�";
	  }else {//if(radio_addr.isSelected()) {//��ư�׷� �� �ּҰ� ���õǾ��ٸ�
		  title="�ּ�";
	  }
	  
	  //����!! �� >>> ���Ͽ� keyword�� title�� ���� ��� �ʹ�!!
	  //�ذ�1) String []�迭 ���
	  
	  //�ذ�2) ���ο� VO��ü�� ���� �ΰ��� ������ ���
	  /*
	          class SearchVO{
	              String keyword;
	              String title;
	          }
	   */
	  
	  
	  //�ذ�3) Map��ü�� �ΰ��� ������ ���  
	  //        - ���� VO�� ����� ���� �δ㽺���ﶧ ���
	  //        - VO��ü���� ���ǵ� ����(�ʵ�)10����  ���� ���� ��������2�� ����Ҷ�
	  
	  //===> �ذ�2)�� �ذ�3)�� ���� ���!!
	  
	  Map<String, String> map = new HashMap<>();
	    map.put("keyword",keyword);
	    map.put("title",title);
	    
	  return map;
   }//showOption
   
   public Map<String, String> showOption2() {	  
	   String []options={"Ȯ��","���"};
	   int t = JOptionPane.showOptionDialog(this, panel_option, "Search",
			   JOptionPane.OK_CANCEL_OPTION ,
			   JOptionPane.PLAIN_MESSAGE,//JOptionPane.ERROR_MESSAGE, 
			   null, 
			   null,//options,
			   null);//options[1])	  
	   
	   String keyword= "%"+tf_search.getText()+"%";
	   
	   Map<String, String> map = new HashMap<>();
	   
	   if(radio_id.isSelected()) {//��ư�׷� �� ���̵� ���õǾ��ٸ�
		   map.put("id",keyword);  //map: {id, 'yo'}
	   }else if(radio_name.isSelected()) {//��ư�׷� �� �̸��� ���õǾ��ٸ�
		   map.put("name",keyword);  //map: {name, '��'}
	   }else {//if(radio_addr.isSelected()) {//��ư�׷� �� �ּҰ� ���õǾ��ٸ�
		   map.put("addr",keyword);  //map: {addr, '��'}
	   }	   
	   //map: {id, '%yo%'} �Ǵ�  {name, '%yo%'} �Ǵ� {addr, '%yo%'} 
	   
	   return map;
   }//showOption2
   
   
   
   //ArrayList�ȿ� ����� MembershipVO������ JTable�� ����ϴ� ���.
   //JTable ==> {"ID","�̸�","����","����","��ȭ��ȣ","�ּ�","����"};
   public void displayTable(ArrayList<MembershipVO> list) {
	    
	    dtm.setRowCount(0);
	    
        for(int i=0; i<list.size(); i++) {
        	MembershipVO vo = list.get(i);
        	 int ssn1 = vo.getSsn1();//950203 , 070603
        	 int ssn2 = vo.getSsn2();//1012345, 401234
        	 
        	//���̱��ϱ�  ==> ���翬�� - ������� + 1  ==> 2019 - (1900+95) + 1
        	int year = Calendar.getInstance().get(Calendar.YEAR);
        	int mille = ssn2/1000000;
        	int age;
        	    if(mille<3 || mille==5 || mille==6)//1,2,5,6
        	      age = year - (1900+ ssn1/10000) + 1;
        	    else//3,4,7,8
        	      age = year - (2000+ ssn1/10000) + 1;
        	
        	//���׿�����   ==> (���ǽ�)  ?   A : B
        	String gender = ( mille %2 ==0)  ?  "����" : "����";
        	 
        	Object []rowData= {vo.getId(),vo.getName(),age,gender,vo.getPhone(),
        			         vo.getAddr(),vo.getJob()};
        	dtm.addRow(rowData);
        } 
   }//displayTable
   
   
}//ServiceForm










