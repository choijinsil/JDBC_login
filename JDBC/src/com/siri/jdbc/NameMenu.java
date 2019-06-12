package com.siri.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NameMenu {

	Connection conn;
	Statement stmt;
	ResultSet rs;

	int t;
	String sql;

	public NameMenu() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("DB연결성공");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(String name) { // create
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			stmt = conn.createStatement();
			sql = "insert into names values('" + name + "')";
			rs = stmt.executeQuery(sql);
			System.out.println("sql==>" + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// conn은 유한개라 다음사람을 위해서 사용한 연결객체는 반환해야 한다.
			// DB자원: conn - stmt - rs
			// 반환은 반대로! rs.close, stmt.close, conn.close
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close(); // 이 하나만 사용해도 나머지 다 끊킨다.
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// orderby를 주지 않으면 rowid기준으로 실행된다.
	public void search() { // read
		connect();
		try {

			System.out.println("# 이름목록");
			sql = "select * from names ";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	public void update(String oldName, String newName) throws SQLException {// update
		connect();

		sql = "update names set name='" + newName + "' where name='" + oldName + "'";
		t = stmt.executeUpdate(sql);
		if (t > 0) {
			System.out.println("DB수정성공");
		} else {
			System.out.println("#존재하는 이름이 없습니다.");
		}
		disconnect();
	}

	public void delete(String delName) {// delete
		connect();
		try {

			sql = "delete from names where name='" + delName + "'";
			t = stmt.executeUpdate(sql);
			if (t > 0) {
				System.out.println("DB삭제성공");
			} else {
				System.out.println("#존재하는 이름이 없습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	private void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void disconnect() {
		if (conn != null)
			try {
				if (conn != null)
					conn.close(); // 이 하나만 사용해도 나머지 다 끊킨다.
			} catch (SQLException e) {
				e.printStackTrace();
			} // 이 하나만 사용해도 나머지 다 끊킨다.
	}

}
