package com.siri.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rr {
	public static void main(String[] args) {
		
		// �ش� DB�� �ε��� ���̴�. 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn= DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe"
					,"scott"
					,"tiger");
			
			System.out.println("DB���Ἲ��");
			
			Statement stmt= conn.createStatement();
			String sql="select deptno, dname, loc from dept where deptno=20";
			ResultSet rs= stmt.executeQuery(sql);
			rs.next();
			System.out.println(rs.getInt("deptno") // rs.getint(1) ��ü����
					+ "," + rs.getString("dname") + "," + rs.getString("loc"));
			
			System.out.println("rs�������� ����!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
