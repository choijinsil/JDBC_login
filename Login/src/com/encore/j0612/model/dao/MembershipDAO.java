package com.encore.j0612.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import com.encore.j0612.model.vo.MembershipVO;

public class MembershipDAO {
	
	Connection conn;
	//Statement  stmt;
	PreparedStatement stmt;
	ResultSet  rs;
	
	Properties pro;//DB���Ӱ��� ���� ���� ��ü
	
	public MembershipDAO() {
	   try {
		pro = new Properties();//�Ӽ� ��� ��ü       (�Ӽ�0��)	
		   pro.load(new FileReader("conn/conn.properties"));
		   //�Ӽ�driver,url,user,password ����  (�Ӽ�4��)
		   Class.forName(pro.getProperty("driver"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//������
	//ȸ������:   UI�� �Էµ� �����͵��� DB���̺� ����!!
		public boolean create(//String id,String pass,String name,int ssn1,int ssn2,String phone,String addr,String job
				           MembershipVO vo) {
		  connect();	
		  //�÷�: id,pass,name,ssn1,ssn2,phone,addr,job	
		  try {
			String sql="insert into membership (id,pass,name,ssn1,ssn2,phone,addr,job) "
			  		      + "values (?,?,?,?,?,?,?,?)"; //?:���ε庯��, ������ ä���
			  stmt = conn.prepareStatement(sql);//(�����͸� ������)sql���� DB�� ����
			     stmt.setString(1, vo.getId());
			     stmt.setString(2, vo.getPass());
			     stmt.setString(3, vo.getName());
			     stmt.setInt(4, vo.getSsn1());
			     stmt.setInt(5, vo.getSsn2());
			     stmt.setString(6, vo.getPhone());
			     stmt.setString(7, vo.getAddr());
			     stmt.setString(8, vo.getJob());
			  stmt.executeUpdate();//���۵� sql���� ���� �����û!!
			  return true;
		   } catch (SQLException e) {
			e.printStackTrace();
		   } finally {
			disconnect();
		   }
		   
		  return false;
	}//create
		
		
		
	//�������� �Էµ� �����ͷ� DB������ ����(���Է�)	
	public boolean modify(MembershipVO vo) {
	  connect();	
	  
	  try {
		String sql="update membership set pass=?, phone=?, addr=?, job=? "
		  		   + "where id=?";
		  stmt = conn.prepareStatement(sql);//sql����
		     stmt.setString(1, vo.getPass());
		     stmt.setString(2, vo.getPhone());
		     stmt.setString(3, vo.getAddr());
		     stmt.setString(4, vo.getJob());
		     stmt.setString(5, vo.getId());
		  int t =stmt.executeUpdate();//sql�����û
		  if(t==1) return true;//t:������ ���� ����
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } finally {
		   disconnect();
	   }
	  return false;
	}//modify
	
	
	//ȸ������(Ż��)
	public boolean remove(String id) {
		connect();	
		
		try {
			String sql="delete from membership where id=?";
			stmt = conn.prepareStatement(sql);//sql����
			   stmt.setString(1, id);
			int t =stmt.executeUpdate();//sql�����û
			if(t==1) return true;//t:������ ���� ����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}//remove
	
	
	//Ư�� ���̵��� (DB����)���� ���� üũ  ==> ���(insert)�� �����߻� ����
	public int findExistId(String id) {
	   connect();
	   int count=0;
	   try {
		String sql="select count(*) as count from membership where id=?";
		   stmt = conn.prepareStatement(sql);//sql�� DB�� ����
		      //stmt.set�ڷ���(����ǥ�ε���1~, ����������);//?(���ε庯��)�� ���� ������ ����
		      stmt.setString(1,id);
		   rs=stmt.executeQuery();//������ sql�� �����û
		   if(rs.next()) {
			    count = rs.getInt("count");//rs.getInt(�ε���1,2,3..�Ǵ�  "�÷���"  �Ǵ�  "����")
		   }
	    } catch (SQLException e) {
		    e.printStackTrace();
	    } finally {
	    	disconnect();	
	    }
	   
	   return count;
	}//findExistId
	
	
	//(��ü)ȸ�� ���� ��ȸ
	public ArrayList<MembershipVO> findAll() {
	  connect();	
	  ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
	   //�÷�: id,pass,name,ssn1,ssn2,phone,addr,job	
      try {
		String sql="select id,name,ssn1,ssn2,phone,addr,job "
				+ "from membership where id<>'admin'";
		  stmt = conn.prepareStatement(sql);//sql�� ����
		  rs=stmt.executeQuery();//sql�� �����û(�������!!)
		  //���
		  
		  while(rs.next()) {//����
			  //�������� ���
			MembershipVO vo = new MembershipVO();
			//7���� �����ִ� �Ӽ������͸� �����ֱ� ���� ���.
			  vo.setId(rs.getString("id"));
			  vo.setName(rs.getString("name"));
			  vo.setSsn1(rs.getInt("ssn1"));
			  vo.setSsn2(rs.getInt("ssn2"));
			  vo.setPhone(rs.getString("phone"));
			  vo.setAddr(rs.getString("addr"));
			  vo.setJob(rs.getString("job"));
			 
			  list.add(vo);
		  }//while
	    } catch (SQLException e) {
		   e.printStackTrace();
	    }finally {
	    	disconnect();
		}
      
	  return list;
	}//findAll
	
	//(�̸�����)ȸ�� ���� ��ȸ
	public ArrayList<MembershipVO> findByName(String name) {
		connect();	
		ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
		//�÷�: id,pass,name,ssn1,ssn2,phone,addr,job	
		try {
			String sql="select id,name,ssn1,ssn2,phone,addr,job from membership "
					+ "where name like ?";
			stmt = conn.prepareStatement(sql);//sql�� ����
			  stmt.setString(1, "%"+name+"%");//'%ȫ%'
			rs=stmt.executeQuery();//sql�� �����û(�������!!)
			//���
			
			while(rs.next()) {//����
				//�������� ���
				MembershipVO vo = new MembershipVO();
				//7���� �����ִ� �Ӽ������͸� �����ֱ� ���� ���.
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setSsn1(rs.getInt("ssn1"));
				vo.setSsn2(rs.getInt("ssn2"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddr(rs.getString("addr"));
				vo.setJob(rs.getString("job"));
				
				list.add(vo);
			}//while
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return list;
	}//findAll
	
	
	//(�̸�����)ȸ�� ���� ��ȸ
	public ArrayList<MembershipVO> findSearch(Map<String, String> map) {
		connect();	
		ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
		//�÷�: id,pass,name,ssn1,ssn2,phone,addr,job
		String title = map.get("title");
		String keyword = map.get("keyword");
		
		try {
			
			String sql="select id,name,ssn1,ssn2,phone,addr,job from membership ";
			
			if(title.equals("���̵�"))
			  sql += "where id like ?";
			else if(title.equals("�̸�"))
			  sql += "where name like ?";
			else if(title.equals("�ּ�"))
			  sql += "where addr like ?";
			
			stmt = conn.prepareStatement(sql);//sql�� ����
			stmt.setString(1, "%"+keyword+"%");//'%ȫ%'
			rs=stmt.executeQuery();//sql�� �����û(�������!!)
			//���
			
			while(rs.next()) {//����
				//�������� ���
				MembershipVO vo = new MembershipVO();
				//7���� �����ִ� �Ӽ������͸� �����ֱ� ���� ���.
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setSsn1(rs.getInt("ssn1"));
				vo.setSsn2(rs.getInt("ssn2"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddr(rs.getString("addr"));
				vo.setJob(rs.getString("job"));
				
				list.add(vo);
			}//while
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return list;
	}//findAll
	
	
	//ȸ������ ����(��)�� �ʿ��� ������ ��ȸ(�˻�)
	//�÷�: id,pass,name,ssn1,ssn2,phone,addr,job
	public MembershipVO findById(String id) {
	   connect();
	   MembershipVO vo=null;//��ȸ�� ������� ������ ǥ��
	   
	   try {
		String sql="select id,pass,name,ssn1,ssn2,phone,addr,job from membership "
		   		      + "where id=?";
		   stmt = conn.prepareStatement(sql); //sql�� ����
		     stmt.setString(1, id);
		   rs = stmt.executeQuery();//����sql���� ���� ���� ��û 
		   
		   if(rs.next()) {//���̵� ��ġ�ϴ� ���� �����Ѵٸ�
			   vo = new MembershipVO(rs.getString("id"), rs.getString("pass"),
					                 rs.getString("name"), rs.getInt("ssn1"), 
					                 rs.getInt("ssn2"),rs.getString("phone"),
					                 rs.getString("addr"), rs.getString("job"));
		   }
	    } catch (SQLException e) {
		    e.printStackTrace();
	    }finally {
	    	disconnect();
	    }
	   return vo;
	}//findById
	
	
	
	public boolean findLogin(String id, String pass) {
	   connect();	
	   /*
	       select count(*) from emp;   (O)
	       select deptno, count(*) from emp;  (X)
	       select deptno, count(*) from emp group by deptno;  (O)
	       select decode(1,2,3), count(*) from emp;  (X)
	       select decode(1,2,3), count(*) from emp group by decode(1,2,3);  (O)
	    */
	   
	   
	   
	   try {
		//stmt = conn.createStatement();
		  String sql="select count(*) from membership where id=? and pass=?";
		  stmt = conn.prepareStatement(sql); //DB�� sql�� ����  ==> DBMS���� �Ľ�
		  
		     stmt.setString(1, id);//1: ù��° ����ǥ
		     stmt.setString(2, pass);//2: �ι�° ����ǥ
		     
		   //String sql="select count(*) from membership where id='gildong' and pass='1234'";
		   //String sql="select count(*) cnt from membership where id='"+id
		   //                                  +"' and pass='"+pass+"'";
		   System.out.println("�α��� SQL>>>"+ sql);
		   //rs = stmt.executeQuery(sql);//(��ȸ)sql�� �����û
		   rs = stmt.executeQuery();//��? �̹� ������ sql���� ���������Ƿ� �ߺ��ؼ� ����X
		  
		   if(rs.next()) {
			   //rs.getInt("count(*)") ==> (O) 
			   //rs.getInt("cnt") ==> (O)
			   int cnt = rs.getInt(1);
			   if(cnt>0) return true;
		   }
	   } catch (SQLException e) {
		e.printStackTrace();
	   } finally {
		   disconnect();
	   }
	   return false;
	}//findLogin
	
	
	
	
	public String findLogin2(String id, String pass) {
	
	  /*
	    <�����>	
		String sql="select * from membership";
		    ---> ���� ȸ���� ���� 10���̸�  ������ ���� ���� 10��!!
		
		String sql="select * from membership where id=id and pass=pass";
            ---> �ʿ��� �����ʹ� id�� pass(2��)�ε�   '*'�� ����  ���ʿ��� �÷�(6��)�� ��ȸ!!

		String sql="select id,pass from membership where id='gildong' and pass='1234'";
            ---> 'gildong','1234' �����Ϳ� ��ġ�ϴ� ��ȸ ����� 'gildong','1234'
		
		<��ȣ>
		String sql="select pass from membership where id='gildong'";
		    ---> ���̵� ��ġ�ϴ� pass������ DB�� ���� ��ȸ
		    ---> ����) ���̵� �����ϴ��� ����
		                         ��й�ȣ�� ��ġ�ϴ��� ���θ� �ڼ��� �ľ��ϱ⿡ ����!!
		<����>   
		String sql="select count(*) from membership where id=id and pass=pass";
		
		*/
		
		
		//String sql="select pass from membership where id='gildong'";
		connect();
		
		try {
			//stmt = conn.createStatement();			
			String sql="select pass from membership where id='"+id+"'";
			System.out.println("�α���SQL>>>"+ sql);
			rs = stmt.executeQuery(sql);//sql�� DB����, sql�� �����û!!==> DBMS���� �Ľ�
			
			if(rs.next()) {
				//���̵� ���� O
				String dpass= rs.getString("pass");
				//dpass:DB�� ����� ��� ,  pass:UI���� ���޵� ���
				if(dpass.equals(pass)) {
					//1. ���̵� ����O, ��� ��ġO
					return "success";
				}else {
					//2. ���̵� ����O, ��� ��ġX
					return "fail_pass";
				}
				
			}//else {
				//3. ���̵� ���� X
				//return "fail_id";
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
	  //3. ���̵� ���� X
	  return "fail_id";
	}//findLogin2
	
	private void connect() {//���ᰴü����
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"),pro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void disconnect() {//DB�ڿ���ȯ
		try {
			if(conn != null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}//MembershipDAO



