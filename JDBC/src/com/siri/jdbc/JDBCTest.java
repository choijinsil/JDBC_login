package com.siri.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
	// JDBC연습
	Connection conn;
	Statement stmt;
	// --------------DML 업무(insert, delete, update)
	ResultSet rs; // 행열저장
	// --------------DQL업무(select)

	public JDBCTest() {
		try {
			// 생성자
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 연결객체생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			//conn.setAutoCommit(false); // commit명령까지 기다려
			System.out.println("DB연결 성공");

			// 3. statement 실행객체 생성 -->DML 요청가능
			stmt = conn.createStatement();
			//conn.commit();
			//conn.rollback();

			// 문제1 사원테이블에 사원정보를 등록하시오
			String sql = "insert into emp3 (empno, ename, sal, deptno) values(7000,'홍길동',2000,10)";
			//stmt.executeUpdate(sql);
			sql = "insert into emp3 (empno, ename, sal, deptno) values(7002,'길라임',3000,20)";
			//stmt.executeUpdate(sql);
			sql = "insert into emp3 (empno, ename, sal, deptno) values(7004,'김주원',4000,30)";
			//stmt.executeUpdate(sql);
			
			System.out.println("사원정보 등록성공");
			
			// 길라임 사원삭제하기
			sql="delete from emp3 where ename='길라임'";
			int t=stmt.executeUpdate(sql);
			//System.out.println("DB삭제성공 -->"+t); // 삭제된 행의 갯수
			
			// 김주원 사원 10번부서로 이동!
			sql="update emp3 set deptno=10 where ename='김주원'";
			t=stmt.executeUpdate(sql);
			
			if(t>0) {
				System.out.println("DB수정성공");
			}else {
				System.out.println("#존재하는 이름이 없습니다.");
			}
			
			
			sql = "select empno, ename, sal, deptno from emp3";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString("ename")
				+","+rs.getInt("sal")+","+rs.getInt("deptno") // 전체를 가져올 것이다.
				);
			}

		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new JDBCTest();
	}
}
