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


public class PersonDAO {//��DAO : Data(Base) Access Object
	                    //       ������ ���̽� ���� ��ü(DB���õ� �� ����!!)
	                    // ----> CRUD �ۼ� (Create, Read,  Update, Delete)
	                    //                insert  select   update  delete
	
	Connection conn;
	Statement  stmt;
	
	ResultSet  rs;
	
	//conn.properties���� ==> DB�������� ����!!
	Properties pro;
	
	public PersonDAO() {
	   try {
		pro = new Properties();	//�Ӽ� ��
		   pro.load(new FileReader("conn/conn.properties"));//�Ӽ� 4�� ����			
		   Class.forName(pro.getProperty("driver"));
		   
	   } catch (Exception e) {
		e.printStackTrace();
	  }
	}//������
	
	

	
	
	
	
	//public boolean insert(�ڹٺ���) {}
	public boolean insert(Person p) {
	//�Ǵ�public void insert(Person p) {}
	  connect();
	  
	  try {
		stmt = conn.createStatement();
		  String sql="insert into person (no,name,age,job) values "
		  		//+ "(1, 'ȫ�浿' , 13 , '�л�' )";
		         + "(person_seq.nextval, '"+p.getName()+"' , "+p.getAge()
		          +" , '"+p.getJob()+"' )";
		  System.out.println("�߰�SQL==> "+ sql);
		  stmt.executeUpdate(sql);//DB���� �Է¿�û
		  return true;
		  
	  } catch (SQLException e) {
		e.printStackTrace();
	  } finally {
		  disconnect();
	  }
	  
	  return false;
	  
	}//insert
	
	//�Ǵ� public boolean update(�ڹٺ���) {}
	public boolean update(Person p) {
	   connect();	
		
	   try {
		stmt = conn.createStatement();
		   System.out.println("������ Person>>>"+ p);
		   //String sql="update person set age=16, job='�л�2' where no=3";
		   String sql="update person set age="+p.getAge()+", job='"+p.getJob()+
				       "' where no="+p.getNo();
		   System.out.println("���� SQL>>>"+ sql);
		   int t = stmt.executeUpdate(sql);//������û!!
		   System.out.println("������ ���� ����>>>"+ t);
		   if(t==1)//������ �����Ͽ��ٸ� 
			 return true;
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } finally {
		   disconnect();
	    }
	   
	   return false;
	}//update
	//�Ǵ�public void update(Person p) {}
	
	//public void delete(�����̸Ӹ� Ű) {}
	public boolean delete(int no) {
	   connect();
	   
	   try {
		stmt = conn.createStatement();
		   //String sql="delete from person where no=3";
		   String sql="delete from person where no="+no;
		   int t = stmt.executeUpdate(sql);//(����)�����û
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
	//�Ǵ� public void delete(int no) {}
	
	//public void select(�����̸Ӹ� Ű) {}
	public Person select(int no) {//�� ���� Person���� ������ --> ������
	  connect();
		
	  try {
		stmt = conn.createStatement();
		  String sql="select name,age,job from person where no="+no;
		  rs = stmt.executeQuery(sql);
		  
		  if(rs.next()) {//��ȸ�� ���� �ִٸ�
			  //DB�� ���� ������ ���  : rs.get~()
			   //rs.getString("name");
			   //rs.getString("age");//"13"
			   //rs.getInt("age");// 13
			   //rs.getString("job");
			  
			  Person p = new Person( no, rs.getString("name"),
					                 rs.getInt("age"),
					                 rs.getString("job")); 
			return p;  
		  }//else {
			  //Ư�� ��ȣ ����!!
		  //}
	   } catch (SQLException e) {
		e.printStackTrace();
	   } finally {
		   disconnect();
	   }
	  
	  return null;//��ȣ�� ��ġ�ϴ� Person������ ���� ��������
	}//select
	
	
	public ArrayList<Person> selectAll() {//��� Person ���� ������
	  connect();	
      ArrayList<Person> list = new ArrayList<Person>();//(Person)������ ��� �ٱ���
      
	  try {
		stmt = conn.createStatement();
		  String sql="select no,name,age,job from person order by no";
		  rs =stmt.executeQuery(sql);
		   while(rs.next()) {
			 /*  
		      int no= rs.getInt("no");//rs.get~() ==> DB�� ���� ������ ���
		      String name = rs.getString("name");
		      int age = rs.getInt("age");
		      String job = rs.getString("job");
		      
		      //4���� ������ �ϳ��� ������ ����
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
				//�������(DB�ڿ���ȯ)
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}//disconnect
	

}//PersonDAO



