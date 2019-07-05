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
	
	Properties pro;//DB접속관련 정보 저장 객체
	
	public MembershipDAO() {
	   try {
		pro = new Properties();//속성 담는 객체       (속성0개)	
		   pro.load(new FileReader("conn/conn.properties"));
		   //속성driver,url,user,password 적재  (속성4개)
		   Class.forName(pro.getProperty("driver"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//생성자
	//회원가입:   UI에 입력된 데이터들을 DB테이블에 저장!!
		public boolean create(//String id,String pass,String name,int ssn1,int ssn2,String phone,String addr,String job
				           MembershipVO vo) {
		  connect();	
		  //컬럼: id,pass,name,ssn1,ssn2,phone,addr,job	
		  try {
			String sql="insert into membership (id,pass,name,ssn1,ssn2,phone,addr,job) "
			  		      + "values (?,?,?,?,?,?,?,?)"; //?:바인드변수, 데이터 채우기
			  stmt = conn.prepareStatement(sql);//(데이터를 제외한)sql문을 DB에 전송
			     stmt.setString(1, vo.getId());
			     stmt.setString(2, vo.getPass());
			     stmt.setString(3, vo.getName());
			     stmt.setInt(4, vo.getSsn1());
			     stmt.setInt(5, vo.getSsn2());
			     stmt.setString(6, vo.getPhone());
			     stmt.setString(7, vo.getAddr());
			     stmt.setString(8, vo.getJob());
			  stmt.executeUpdate();//전송된 sql문에 대해 실행요청!!
			  return true;
		   } catch (SQLException e) {
			e.printStackTrace();
		   } finally {
			disconnect();
		   }
		   
		  return false;
	}//create
		
		
		
	//수정폼에 입력된 데이터로 DB데이터 갱신(재입력)	
	public boolean modify(MembershipVO vo) {
	  connect();	
	  
	  try {
		String sql="update membership set pass=?, phone=?, addr=?, job=? "
		  		   + "where id=?";
		  stmt = conn.prepareStatement(sql);//sql전송
		     stmt.setString(1, vo.getPass());
		     stmt.setString(2, vo.getPhone());
		     stmt.setString(3, vo.getAddr());
		     stmt.setString(4, vo.getJob());
		     stmt.setString(5, vo.getId());
		  int t =stmt.executeUpdate();//sql실행요청
		  if(t==1) return true;//t:수정된 행의 갯수
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } finally {
		   disconnect();
	   }
	  return false;
	}//modify
	
	
	//회원삭제(탈퇴)
	public boolean remove(String id) {
		connect();	
		
		try {
			String sql="delete from membership where id=?";
			stmt = conn.prepareStatement(sql);//sql전송
			   stmt.setString(1, id);
			int t =stmt.executeUpdate();//sql실행요청
			if(t==1) return true;//t:수정된 행의 갯수
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}//remove
	
	
	//특정 아이디의 (DB내에)존재 유무 체크  ==> 등록(insert)시 에러발생 방지
	public int findExistId(String id) {
	   connect();
	   int count=0;
	   try {
		String sql="select count(*) as count from membership where id=?";
		   stmt = conn.prepareStatement(sql);//sql문 DB에 전송
		      //stmt.set자료형(물음표인덱스1~, 설정데이터);//?(바인드변수)에 대한 데이터 설정
		      stmt.setString(1,id);
		   rs=stmt.executeQuery();//전송한 sql문 실행요청
		   if(rs.next()) {
			    count = rs.getInt("count");//rs.getInt(인덱스1,2,3..또는  "컬럼명"  또는  "별명")
		   }
	    } catch (SQLException e) {
		    e.printStackTrace();
	    } finally {
	    	disconnect();	
	    }
	   
	   return count;
	}//findExistId
	
	
	//(전체)회원 정보 조회
	public ArrayList<MembershipVO> findAll() {
	  connect();	
	  ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
	   //컬럼: id,pass,name,ssn1,ssn2,phone,addr,job	
      try {
		String sql="select id,name,ssn1,ssn2,phone,addr,job "
				+ "from membership where id<>'admin'";
		  stmt = conn.prepareStatement(sql);//sql문 전송
		  rs=stmt.executeQuery();//sql문 실행요청(실행시점!!)
		  //덩어리
		  
		  while(rs.next()) {//행얻기
			  //열데이터 얻기
			MembershipVO vo = new MembershipVO();
			//7개의 관련있는 속성데이터를 묶어주기 위해 사용.
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
	
	//(이름패턴)회원 정보 조회
	public ArrayList<MembershipVO> findByName(String name) {
		connect();	
		ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
		//컬럼: id,pass,name,ssn1,ssn2,phone,addr,job	
		try {
			String sql="select id,name,ssn1,ssn2,phone,addr,job from membership "
					+ "where name like ?";
			stmt = conn.prepareStatement(sql);//sql문 전송
			  stmt.setString(1, "%"+name+"%");//'%홍%'
			rs=stmt.executeQuery();//sql문 실행요청(실행시점!!)
			//덩어리
			
			while(rs.next()) {//행얻기
				//열데이터 얻기
				MembershipVO vo = new MembershipVO();
				//7개의 관련있는 속성데이터를 묶어주기 위해 사용.
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
	
	
	//(이름패턴)회원 정보 조회
	public ArrayList<MembershipVO> findSearch(Map<String, String> map) {
		connect();	
		ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
		//컬럼: id,pass,name,ssn1,ssn2,phone,addr,job
		String title = map.get("title");
		String keyword = map.get("keyword");
		
		try {
			
			String sql="select id,name,ssn1,ssn2,phone,addr,job from membership ";
			
			if(title.equals("아이디"))
			  sql += "where id like ?";
			else if(title.equals("이름"))
			  sql += "where name like ?";
			else if(title.equals("주소"))
			  sql += "where addr like ?";
			
			stmt = conn.prepareStatement(sql);//sql문 전송
			stmt.setString(1, "%"+keyword+"%");//'%홍%'
			rs=stmt.executeQuery();//sql문 실행요청(실행시점!!)
			//덩어리
			
			while(rs.next()) {//행얻기
				//열데이터 얻기
				MembershipVO vo = new MembershipVO();
				//7개의 관련있는 속성데이터를 묶어주기 위해 사용.
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
	
	
	//회원정보 수정(폼)에 필요한 데이터 조회(검색)
	//컬럼: id,pass,name,ssn1,ssn2,phone,addr,job
	public MembershipVO findById(String id) {
	   connect();
	   MembershipVO vo=null;//조회된 결과행이 없음을 표현
	   
	   try {
		String sql="select id,pass,name,ssn1,ssn2,phone,addr,job from membership "
		   		      + "where id=?";
		   stmt = conn.prepareStatement(sql); //sql문 전송
		     stmt.setString(1, id);
		   rs = stmt.executeQuery();//전송sql문에 대한 실행 요청 
		   
		   if(rs.next()) {//아이디에 일치하는 행이 존재한다면
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
		  stmt = conn.prepareStatement(sql); //DB에 sql문 전송  ==> DBMS에서 파싱
		  
		     stmt.setString(1, id);//1: 첫번째 물음표
		     stmt.setString(2, pass);//2: 두번째 물음표
		     
		   //String sql="select count(*) from membership where id='gildong' and pass='1234'";
		   //String sql="select count(*) cnt from membership where id='"+id
		   //                                  +"' and pass='"+pass+"'";
		   System.out.println("로그인 SQL>>>"+ sql);
		   //rs = stmt.executeQuery(sql);//(조회)sql문 실행요청
		   rs = stmt.executeQuery();//왜? 이미 위에서 sql문을 전송했으므로 중복해서 전송X
		  
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
	    <비권장>	
		String sql="select * from membership";
		    ---> 만약 회원의 수의 10만이면  얻어오는 행의 수도 10만!!
		
		String sql="select * from membership where id=id and pass=pass";
            ---> 필요한 데이터는 id와 pass(2개)인데   '*'를 통해  불필요한 컬럼(6개)도 조회!!

		String sql="select id,pass from membership where id='gildong' and pass='1234'";
            ---> 'gildong','1234' 데이터에 일치하는 조회 결과도 'gildong','1234'
		
		<양호>
		String sql="select pass from membership where id='gildong'";
		    ---> 아이디에 일치하는 pass정보를 DB로 부터 조회
		    ---> 장점) 아이디가 존재하는지 여부
		                         비밀번호가 일치하는지 여부를 자세히 파악하기에 좋다!!
		<권장>   
		String sql="select count(*) from membership where id=id and pass=pass";
		
		*/
		
		
		//String sql="select pass from membership where id='gildong'";
		connect();
		
		try {
			//stmt = conn.createStatement();			
			String sql="select pass from membership where id='"+id+"'";
			System.out.println("로그인SQL>>>"+ sql);
			rs = stmt.executeQuery(sql);//sql문 DB전달, sql문 실행요청!!==> DBMS에서 파싱
			
			if(rs.next()) {
				//아이디 존재 O
				String dpass= rs.getString("pass");
				//dpass:DB에 저장된 비번 ,  pass:UI통해 전달된 비번
				if(dpass.equals(pass)) {
					//1. 아이디 존재O, 비번 일치O
					return "success";
				}else {
					//2. 아이디 존재O, 비번 일치X
					return "fail_pass";
				}
				
			}//else {
				//3. 아이디 존재 X
				//return "fail_id";
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
	  //3. 아이디 존재 X
	  return "fail_id";
	}//findLogin2
	
	private void connect() {//연결객체생성
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"),pro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void disconnect() {//DB자원반환
		try {
			if(conn != null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}//MembershipDAO



