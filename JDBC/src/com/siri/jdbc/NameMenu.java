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
			System.out.println("DB���Ἲ��");

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
			// conn�� ���Ѱ��� ��������� ���ؼ� ����� ���ᰴü�� ��ȯ�ؾ� �Ѵ�.
			// DB�ڿ�: conn - stmt - rs
			// ��ȯ�� �ݴ��! rs.close, stmt.close, conn.close
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close(); // �� �ϳ��� ����ص� ������ �� ��Ų��.
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// orderby�� ���� ������ rowid�������� ����ȴ�.
	public void search() { // read
		connect();
		try {

			System.out.println("# �̸����");
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
			System.out.println("DB��������");
		} else {
			System.out.println("#�����ϴ� �̸��� �����ϴ�.");
		}
		disconnect();
	}

	public void delete(String delName) {// delete
		connect();
		try {

			sql = "delete from names where name='" + delName + "'";
			t = stmt.executeUpdate(sql);
			if (t > 0) {
				System.out.println("DB��������");
			} else {
				System.out.println("#�����ϴ� �̸��� �����ϴ�.");
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
					conn.close(); // �� �ϳ��� ����ص� ������ �� ��Ų��.
			} catch (SQLException e) {
				e.printStackTrace();
			} // �� �ϳ��� ����ص� ������ �� ��Ų��.
	}

}
