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
   //public 명시이유? 다른 패키지에 있는 클래스(컨트롤러)에서 사용하기 때문.
   JScrollPane scroll_table;
   
   public JButton bt_sel_all, bt_del, bt_up, bt_sel_name, bt_exit;
   
   JPanel southp;
   public String loginId;//로그인한 아이디 저장
   public boolean authState;  //인증상태를 표현 authentication
   
   
   public JMenuItem item_confirm;
   JMenu     menu_admin;
   JMenuBar  bar;
   
   //첫째패널에 위치할 컴포넌트  ==> 라디오 버튼 3개
   JRadioButton radio_id, radio_name, radio_addr;
   ButtonGroup group;
   
   //둘째패널에 위치할 컴포넌트    ==> 라벨, 텍스트필드
   JTextField  tf_search;
   
   JPanel panel_first, panel_second, panel_option;
   

   public ServiceForm() {
	  setTitle("Display Data");
	  
	  radio_id = new JRadioButton("아이디",true);
	  radio_name = new JRadioButton("이름");
	  radio_addr = new JRadioButton("주소");
	  
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
	     panel_second.add(new JLabel("검색어:"));
	     panel_second.add(tf_search);
	     
	  panel_option = new JPanel();  
	  panel_option.setLayout(new GridLayout(2,1));
	      panel_option.add(panel_first);   
	      panel_option.add(panel_second);   
	     
	  
	  item_confirm = new JMenuItem("인증");
	  
	  menu_admin = new JMenu("관리자");
	     menu_admin.add(item_confirm);
	     
	  bar  = new JMenuBar();
	     bar.add(menu_admin);
	     
	  setJMenuBar(bar);   
	     
	     
	  
	  Object [][]rowData = new Object[0][7];	  
	  String []columTitle = {"ID","이름","나이","성별","전화번호","주소","직업"};	  
	  dtm = new DefaultTableModel(rowData,columTitle);
	  
	  table = new JTable(dtm);
	  scroll_table = new JScrollPane(table);
	  
	  
	  bt_sel_all = new JButton("전체보기");
	  bt_up = new JButton("수정");
	  bt_del = new JButton("삭제");
	  //bt_sel_name = new JButton("이름검색");
	  bt_sel_name = new JButton("검색");
	  bt_exit = new JButton("종료");
	  
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
   
   public Map<String, String> showOption() {
	   //JOptionPane.showOptionDialog(parentComponent, message, title, 
	   //optionType, messageType, icon, options, initialValue)
	   String []options={"확인","취소"};
	  int t = JOptionPane.showOptionDialog(this, panel_option, "Search",
			   JOptionPane.OK_CANCEL_OPTION ,
			   JOptionPane.PLAIN_MESSAGE,//JOptionPane.ERROR_MESSAGE, 
			   null, 
			   null,//options,
			   null);//options[1])
	 //t==> 확인, 취소, X  : 0, 1, -1  
	  
	  String keyword= tf_search.getText();
	  String title="";// 만약 라디오 버튼 중 아이디가 선택되었다면  title= "아이디";
	                //"아이디" , "이름", "주소"
	  
	  if(radio_id.isSelected()) {//버튼그룹 중 아이디가 선택되었다면
		  title="아이디";  //title=radio_id.getText();
	  }else if(radio_name.isSelected()) {//버튼그룹 중 이름이 선택되었다면
		  title="이름";
	  }else {//if(radio_addr.isSelected()) {//버튼그룹 중 주소가 선택되었다면
		  title="주소";
	  }
	  
	  //문제!! ★ >>> 리턴에 keyword와 title을 동시 담고 싶다!!
	  //해결1) String []배열 사용
	  
	  //해결2) 새로운 VO객체를 만들어서 두개의 변수를 담기
	  /*
	          class SearchVO{
	              String keyword;
	              String title;
	          }
	   */
	  
	  
	  //해결3) Map객체에 두개의 변수를 담기  
	  //        - 새로 VO를 만드는 것이 부담스러울때 사용
	  //        - VO객체내에 정의된 변수(필드)10보다  아주 적은 변수갯수2를 사용할때
	  
	  //===> 해결2)와 해결3)을 많이 사용!!
	  
	  Map<String, String> map = new HashMap<>();
	    map.put("keyword",keyword);
	    map.put("title",title);
	    
	  return map;
   }//showOption
   
   public Map<String, String> showOption2() {	  
	   String []options={"확인","취소"};
	   int t = JOptionPane.showOptionDialog(this, panel_option, "Search",
			   JOptionPane.OK_CANCEL_OPTION ,
			   JOptionPane.PLAIN_MESSAGE,//JOptionPane.ERROR_MESSAGE, 
			   null, 
			   null,//options,
			   null);//options[1])	  
	   
	   String keyword= "%"+tf_search.getText()+"%";
	   
	   Map<String, String> map = new HashMap<>();
	   
	   if(radio_id.isSelected()) {//버튼그룹 중 아이디가 선택되었다면
		   map.put("id",keyword);  //map: {id, 'yo'}
	   }else if(radio_name.isSelected()) {//버튼그룹 중 이름이 선택되었다면
		   map.put("name",keyword);  //map: {name, '김'}
	   }else {//if(radio_addr.isSelected()) {//버튼그룹 중 주소가 선택되었다면
		   map.put("addr",keyword);  //map: {addr, '남'}
	   }	   
	   //map: {id, '%yo%'} 또는  {name, '%yo%'} 또는 {addr, '%yo%'} 
	   
	   return map;
   }//showOption2
   
   
   
   //ArrayList안에 저장된 MembershipVO정보를 JTable에 출력하는 기능.
   //JTable ==> {"ID","이름","나이","성별","전화번호","주소","직업"};
   public void displayTable(ArrayList<MembershipVO> list) {
	    
	    dtm.setRowCount(0);
	    
        for(int i=0; i<list.size(); i++) {
        	MembershipVO vo = list.get(i);
        	 int ssn1 = vo.getSsn1();//950203 , 070603
        	 int ssn2 = vo.getSsn2();//1012345, 401234
        	 
        	//나이구하기  ==> 현재연도 - 출생연도 + 1  ==> 2019 - (1900+95) + 1
        	int year = Calendar.getInstance().get(Calendar.YEAR);
        	int mille = ssn2/1000000;
        	int age;
        	    if(mille<3 || mille==5 || mille==6)//1,2,5,6
        	      age = year - (1900+ ssn1/10000) + 1;
        	    else//3,4,7,8
        	      age = year - (2000+ ssn1/10000) + 1;
        	
        	//삼항연산자   ==> (조건식)  ?   A : B
        	String gender = ( mille %2 ==0)  ?  "여자" : "남자";
        	 
        	Object []rowData= {vo.getId(),vo.getName(),age,gender,vo.getPhone(),
        			         vo.getAddr(),vo.getJob()};
        	dtm.addRow(rowData);
        } 
   }//displayTable
   
   
}//ServiceForm










