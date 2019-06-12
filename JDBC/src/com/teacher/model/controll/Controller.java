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
     <컨트롤러의 역할>★ - 전체 프로그램에 대한 흐름 제어!!
     1. 사용자 요청 분석 (예: 어떤 버튼을 눌렀는지)
     2. 사용자 입력 데이터 얻어오기 (예: 입력폼 또는 수정폼을 통해 입력된 데이터)
     3. ★모델객체생성(예: PersonDAO)
           - 메소드호출
           - 결과값 (리턴값) 얻기, 저장, 판단
     4. 페이지(뷰) 이동  (예: 메인(JTable)  ----> 입력폼)
     
        선택사항) 유효성 검사(valid check) <=== 사용자가 입력한 데이터에 대한!!
      	
   */
	
   //뷰등록
	MainView mainView;
	InputForm form;
	UpForm upForm;
	
	
   public Controller() {
	  //객체생성
	  mainView = new MainView();
	      mainView.displayTable(new PersonDAO().selectAll());
	  form     = new InputForm();
	  upForm   = new UpForm();
	  
	  
	  eventUp(); 
   }//생성자
   
   private void eventUp() {//이벤트 소스 등록
	  //메인뷰
	   mainView.bt_insert.addActionListener(this);
	   mainView.bt_update.addActionListener(this);
	   mainView.bt_del.addActionListener(this);
	   mainView.bt_exit.addActionListener(this);
	   
	  //입력폼
	   form.bt_submit.addActionListener(this);
	   //form == JFrame == Window
	   form.addWindowListener(new WindowAdapter() {
		                  //class 무명 extends WindowAdapter{  }
		   @Override
		  public void windowClosing(WindowEvent e) {//윈도우 프레임 우측상단X버튼 클릭시
			  form.setVisible(false);
			  mainView.setVisible(true);
		  }   
	   });
	   
	   
	  //수정폼
	   upForm.bt_submit.addActionListener(this);
	   upForm.bt_cancel.addActionListener(this);
	   upForm.addWindowListener(new WindowAdapter() {
           //class 무명 extends WindowAdapter{  }
			@Override
			public void windowClosing(WindowEvent e) {//윈도우 프레임 우측상단X버튼 클릭시
			  upForm.setVisible(false);
			  mainView.setVisible(true);
			}   
       }); 
	
   }//eventUp

@Override
   public void actionPerformed(ActionEvent e) {
	 Object ob = e.getSource();//action이벤트를 발생시킨 이벤트 소스의 주소 얻기
      
	 if(ob==mainView.bt_insert) {//1.메인뷰 : 입력버튼   ==> 분석: 입력폼요청!!
		//4.메인뷰  ---> 입력폼   이동!! 
		 
		 form.initText();
		 mainView.setVisible(false);
		 form.setVisible(true);
	 }else if(ob==form.bt_submit) {//1.입력폼: 입력버튼 ==> 분석: DB입력 요청!!
		 
		 //2. 입력데이터 얻기
		 String name = form.tf_name.getText().trim();
		 String age = form.tf_age.getText().trim();
		 String job = form.tf_job.getText().trim();
		 
		 
		 //5(옵션). 유효성 검사
		 
		 //빈값 체크,숫자검사
		 if(name.equals("")) {  form.showMsg("이름입력!!");  
		                        return;}
		 else if(age.length()==0) {  form.showMsg("나이입력!!");
		                        return;}
		 else if(!age.matches("[\\d]{1,3}")) {
			 form.showMsg("숫자만입력!!");
			 return;
		 }else if(job.length()<1)  {  form.showMsg("직업입력!!");
		                        return;}
			 
		 
		 //세개의 변수를  p변수로 정의하기(한개의 변수명으로 정의하기)
		 Person p = new Person(0, name, Integer.parseInt(age), job);
		 
		 //3. 모델 객체생성
		 PersonDAO dao = new PersonDAO();
		   if(dao.insert(p)) {//입력성공
			  //메인뷰에 출력할 (DB로부터) 전체 데이터 얻기
			  ArrayList<Person> list = dao.selectAll();
			  //Person []perArr = (Person[]) list.toArray();
			  mainView.displayTable(list);
			   
			  //입력폼 ----> 메인뷰
			  form.setVisible(false);
			  mainView.setVisible(true);
		   }
	 }else if(ob==mainView.bt_update) {
		 //1. 메인뷰: 수정버튼클릭시 ---> 수정폼 요청
		 
		 int row = mainView.table.getSelectedRow();
		 
		 if(row==-1) {//선택된 행이 없다면
		  //JOptionPane.showMessageDialog(mainView, "수정할 행을 선택!!");
		   mainView.showMsg("수정할 행을 선택!!");
		  return;	 
		 }
		 
		 //System.out.println("선택된 행 인덱스>>>"+ row);
		 //2.
		 Object value = mainView.table.getValueAt(row, 0);
		                              //0 column: 번호표현
		 //System.out.println("선택된 번호>>>"+ value);
		 int no = Integer.parseInt(value.toString());
				  //            3 <---- "3"
		 
		 upForm.upNo = no;//수정폼 멤버에 '번호'저장
		 
		 //3.
		 PersonDAO dao = new PersonDAO();
		   Person p = dao.select(no);
		   //DB조회값  ----> 수정폼 전달
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
		 //1. 수정폼: 수정버튼 클릭시 ----> DB수정 요청시
		 
		 //2.
		 String age =upForm.tf_age.getText();
		 String job = upForm.tf_job.getText();
		 
		//빈값 체크,숫자검사
		 if(age.length()==0) {  form.showMsg("나이입력!!");
		                        return;}
		 else if(!age.matches("[\\d]{1,3}")) {
			 form.showMsg("숫자만입력!!");
			 return;
		 }else if(job.length()<1)  {  form.showMsg("직업입력!!");
		                        return;}
		 
		 
		 
		 //upForm.upNo//수정폼 멤버에 저장된 '번호' 찾기
		 
		 Person p = new Person();//기본생성자   : (no=0, name=null, age=0, job=null)
		    p.setAge(Integer.parseInt(age));//: (no=0, name=null, age=20, job=null)
		    p.setJob(job);//: (no=0, name=null, age=20, job="장군")
		    p.setNo(upForm.upNo);//: (no=4, name=null, age=20, job="장군")
		//Person p = new Person(upForm.upNo, null,Integer.parseInt(age), job);//오버로딩 생성자
		    
		    
		 //3.
		 PersonDAO dao = new PersonDAO();
		 if(dao.update(p)) {//수정이 잘되었다면   ---> JTable에 수정내용을 반영
			 mainView.displayTable(dao.selectAll());//내용 갱신
			 
			 upForm.setVisible(false);
			 mainView.setVisible(true);
		 }
		 
	 }else if(ob == mainView.bt_del) {
		 //1.메인폼 : 삭제버튼 클릭시   ----> DB삭제 요청시
		 
		 //2.(삭제할 번호)데이터 얻기
		 String no = mainView.showInput("삭제할 번호:");
		 //취소버튼 또는 X버튼 클릭시 null 리턴
		 
		 if(no==null) {return;}
		 
		 if(/*no != null &&*/ !no.matches("[\\d]+")) {//null체크와 숫자체크
			 mainView.showMsg("숫자만 입력!!");
			 return;
		 }
		 
		 if(mainView.showConfirm("정말 삭제하시겠습니까?")==0) {
			 //3.
			 PersonDAO dao = new PersonDAO();
			 if(dao.delete(Integer.parseInt(no))) {
				 mainView.showMsg("삭제성공!!");
				 mainView.displayTable(dao.selectAll());
			 }else {
				 mainView.showMsg("삭제실패!!");
			 }
		 }//confirm ==>예!!
		 
	 }else if(ob == upForm.bt_cancel) {
		 upForm.initText();
	 }else if(ob == mainView.bt_exit) {//1. 메인뷰: 종료버튼 클릭시  ----> 프로그램 종료
		 System.exit(0);
	 }
	   
   }//actionPerformed
   
   
   
   public static void main(String[] args) {
	  new Controller();
   }

}



