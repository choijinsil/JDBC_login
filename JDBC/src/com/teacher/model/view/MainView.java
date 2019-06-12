package com.teacher.model.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.teacher.model.dto.Person;


public class MainView extends JFrame {
	
   DefaultTableModel dtm;
   public JTable table;
   //public �������? �ٸ� ��Ű���� �ִ� Ŭ����(��Ʈ�ѷ�)���� ����ϱ� ����.
   JScrollPane scroll_table;
   
   public JButton bt_insert, bt_del, bt_update, bt_exit;
   
   JPanel southp,northp;
   
   JLabel la_time;
	
   public MainView() {
	  setTitle("MainView");
	  
	  Object [][]rowData = new Object[0][4];
	  //�� ũ�� 0�� ����?  ������(������ JTable�� �߰��� ����) 0�ε���(ù��)���� �ϱ� ���ؼ�
	  
	  String []columTitle = {"��ȣ","�̸�","����","����"};
	  
	  dtm = new DefaultTableModel(rowData,columTitle);
	  //dtm��������? dtm���� addRow() , removeRow() ����ϱ� ���ؼ�!!
	  
	  table = new JTable(dtm);
	  scroll_table = new JScrollPane(table);
	  //scroll_table��������?  1. ���̺�(������)�� ����� �����͸� ���� ���ؼ� (��ũ�ѹٰ� �ʿ�)!!
	  //                     2. ���̺��� �÷����� ������ �� ����.
	  
	  scroll_table.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
		
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			JScrollBar ob = (JScrollBar) e.getSource();
                  ob.setValue(ob.getMaximum());
		}
	});
	  
	  bt_insert = new JButton("�Է�");
	  bt_update = new JButton("����");
	  bt_del = new JButton("����");
	  bt_exit = new JButton("����");
	  
	  southp = new JPanel();
	    southp.add(bt_insert);
	    southp.add(bt_update);
	    southp.add(bt_del);
	    southp.add(bt_exit);
	    
      la_time = new JLabel();
      northp = new JPanel();
        northp.add(la_time);
     
        
	  add("Center", scroll_table);
	  add("South", southp);
	  add("North", northp);
	  
	  setBounds(300,200,350,300);
	  setVisible(true);
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  
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
   

   
   //ArrayList�ȿ� ����� Person������ JTable�� ����ϴ� ���.
   public void displayTable(ArrayList<Person> list) {
	    
	    dtm.setRowCount(0);//���� addRow�Ǵ� �� �����͸� 0�ε��� ���� ����ϰ���!!
	                       //������ JTable�� ��µ� ������ clear�ϴ� ����!!
	    
        for(int i=0; i<list.size(); i++) {//������ �ε��� �� == ����� ��
        	Person p = list.get(i);
        	Object []rowData= {p.getNo(), p.getName(), p.getAge(), p.getJob()};
        	dtm.addRow(rowData);
        }
        
        //JScrollBar bar= scroll_table.getVerticalScrollBar();
        //bar.setValue(bar.getMaximum());
        
   }//displayTable
   
   public void displayTable(Person[] personArr) {
	    dtm.setRowCount(0);//���� addRow�Ǵ� �� �����͸� 0�ε��� ���� ����ϰ���!!
	                       //������ JTable�� ��µ� ������ clear�ϴ� ����!!
	    
       for(int i=0; i<personArr.length; i++) {//������ �ε��� �� == ����� ��
       	
       	Object []rowData= {personArr[i].getNo(), 
       			           personArr[i].getName(), 
       			           personArr[i].getAge(), 
       			           personArr[i].getJob()};
       	dtm.addRow(rowData);
       }
    }//displayTable
   
}//MainView










