package com.teacher.model.dao;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.teacher.model.dto.Person;


public class PersonDAO {//★DAO : Data(Base) Access Object
	                    //       데이터 베이스 전용 객체(DB관련된 일 전담!!)
	                    // ----> CRUD 작성 (Create, Read,  Update, Delete)
	                    //                insert  select   update  delete
	
	Connection conn;
	Statement  stmt;
	
	ResultSet  rs;
	
	//conn.properties파일 ==> DB연결정보 저장!!
	Properties pro;
	
	public PersonDAO() {
	   try {
		pro = new Properties();	//속성 무
		   pro.load(new FileReader("conn/conn.properties"));//속성 4개 적재			
		   Class.forName(pro.getProperty("driver"));
		   
	   } catch (Exception e) {
		e.printStackTrace();
	  }
	}//생성자
	
	

	
	
	
	
	//public boolean insert(자바빈즈) {}
	public boolean insert(Person p) {
	//또는public void insert(Person p) {}
	  connect();
	  
	  try {
		stmt = conn.createStatement();
		  String sql="insert into person (no,name,age,job) values "
		  		//+ "(1, '홍길동' , 13 , '학생' )";
		         + "(person_seq.nextval, '"+p.getName()+"' , "+p.getAge()
		          +" , '"+p.getJob()+"' )";
		  System.out.println("추가SQL==> "+ sql);
		  stmt.executeUpdate(sql);//DB에게 입력요청
		  return true;
		  
	  } catch (SQLException e) {
		e.printStackTrace();
	  } finally {
		  disconnect();
	  }
	  
	  return false;
	  
	}//insert
	
	//또는 public boolean update(자바빈즈) {}
	public boolean update(Person p) {
	   connect();	
		
	   try {
		stmt = conn.createStatement();
		   System.out.println("수정할 Person>>>"+ p);
		   //String sql="update person set age=16, job='학생2' where no=3";
		   String sql="update person set age="+p.getAge()+", job='"+p.getJob()+
				       "' where no="+p.getNo();
		   System.out.println("수정 SQL>>>"+ sql);
		   int t = stmt.executeUpdate(sql);//수정요청!!
		   System.out.println("수정된 행의 갯수>>>"+ t);
		   if(t==1)//수정이 성공하였다면 
			 return true;
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } finally {
		   disconnect();
	    }
	   
	   return false;
	}//update
	//또는public void update(Person p) {}
	
	//public void delete(프라이머리 키) {}
	public boolean delete(int no) {
	   connect();
	   
	   try {
		stmt = conn.createStatement();
		   //String sql="delete from person where no=3";
		   String sql="delete from person where no="+no;
		   int t = stmt.executeUpdate(sql);//(삭제)실행요청
		   if(t==1) {
			   return true;
		   }
	    } catch (SQLException e) {
		e.printStackTrace();
	    }finally {
	    	disconnect();
	    }
	   
	   return false;
	}//delete
	//또는 public void delete(int no) {}
	
	//public void select(프라이머리 키) {}
	public Person select(int no) {//한 개의 Person정보 얻어오기 --> 수정폼
	  connect();
		
	  try {
		stmt = conn.createStatement();
		  String sql="select name,age,job from person where no="+no;
		  rs = stmt.executeQuery(sql);
		  
		  if(rs.next()) {//조회된 행이 있다면
			  //DB로 부터 데이터 얻기  : rs.get~()
			   //rs.getString("name");
			   //rs.getString("age");//"13"
			   //rs.getInt("age");// 13
			   //rs.getString("job");
			  
			  Person p = new Person( no, rs.getString("name"),
					                 rs.getInt("age"),
					                 rs.getString("job")); 
			return p;  
		  }//else {
			  //특정 번호 없음!!
		  //}
	   } catch (SQLException e) {
		e.printStackTrace();
	   } finally {
		   disconnect();
	   }
	  
	  return null;//번호에 일치하는 Person정보를 얻지 못했을때
	}//select
	
	
	public ArrayList<Person> selectAll() {//모든 Person 정보 얻어오기
	  connect();	
      ArrayList<Person> list = new ArrayList<Person>();//(Person)데이터 담는 바구니
      
	  try {
		stmt = conn.createStatement();
		  String sql="select no,name,age,job from person order by no";
		  rs =stmt.executeQuery(sql);
		   while(rs.next()) {
			 /*  
		      int no= rs.getInt("no");//rs.get~() ==> DB로 부터 데이터 얻기
		      String name = rs.getString("name");
		      int age = rs.getInt("age");
		      String job = rs.getString("job");
		      
		      //4개의 변수를 하나의 변수로 저장
		      Person p = new Person(no, name, age, job);
		                         //p.getNo()
		      list.add(p);
		         //list.get(0)
		         //list.get(0).getNo()
		     */
			   
			   list.add(new Person(rs.getInt("no"), rs.getString("name"),
					               rs.getInt("age"), rs.getString("job")));
		   }
	    } catch (SQLException e) {
		   e.printStackTrace();
     	}finally {
           disconnect();
     	}
	  return list;	
	}//selectAll
	
	 
	private void connect() {
		  try {
			conn = DriverManager.getConnection(pro.getProperty("url"),pro);
					                           //pro.getProperty("user"),  
					                           //pro.getProperty("pwd"));
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}   
	  }//connect
	  
    private void disconnect() {
		  try {
				//연결끊기(DB자원반환)
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}//disconnect
	

}//PersonDAO



