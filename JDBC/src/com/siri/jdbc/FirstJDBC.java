package com.siri.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FirstJDBC {

	public static void main(String[] args) {
		try {// jdbc
				// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 연결객체 생성: connection
			// ms의 sql서버를 사용할때는 따로 인증없이 접근 가능하다. <-- user pass가 필요없다.
			// 하지만 오라클의 경우엔 필요하지롱
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";

			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB연결성공");

			// 3.
			// dml사용 가능
			Statement stmt = conn.createStatement();

			// sql사용하기!
			String sql = "delete from dept_copy where deptno=10";
			// sql실행요청
			// DML의 경우 executeUpdate - return int
			// DQL의 경우 executeQuery - return
			int t = stmt.executeUpdate(sql);
			System.out.println("T=" + t);
			if (t == 1) { // 삭제된 행이 1행이라면
				System.out.println("삭제성공!!, T=" + t); // 삭제 수정된 행의 갯수
			}

			// dept_copy에서 20,30번 부서를 삭제하고 성공메세지 띄우기
			// 기본이 auto commit으로 되어있다.
			// conn.setAutoCommit(false); 만약 auto를 해제하려면 이 구문을 실행하면된다.
			sql = "delete from dept_copy where deptno in (20,30)";
			t = stmt.executeUpdate(sql);
			if (t > 0) {
				System.out.println("# 부서삭제성공!!, T=" + t);
			} else {
				System.out.println("# 삭제할 부서가 존재하지 않아요.");
			}

			// ---------------------Select작업을 하고싶다면 -----------------
			// 4. result set - 결과객체 생성: Result Set
			// - 조회된 결과(행열 데이터)를 저장한다.
			sql = "select deptno, dname, loc from dept where deptno=20";
			// RS 테이블을 슝 가져와라
			ResultSet rs = stmt.executeQuery(sql);

			rs.next(); // 행단위 데이터 얻기

			System.out.println(rs.getInt("deptno") // rs.getint(1) 대체가능
					+ "," + rs.getString("dname") + "," + rs.getString("loc"));
			
			System.out.println("rs가져오기 성공!");
			System.out.println("-----------------------------------");
			System.out.println("전체 부서정보");
			// 문제 전체 부서의 부서번호, 무서명, 무서위치
			sql="select deptno, dname, loc from dept";
			rs= stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString("dname")
				+","+rs.getString("loc") // 전체를 가져올 것이다.
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
