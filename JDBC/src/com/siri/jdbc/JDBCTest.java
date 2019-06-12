package com.siri.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
	// JDBC����
	Connection conn;
	Statement stmt;
	// --------------DML ����(insert, delete, update)
	ResultSet rs; // �࿭����
	// --------------DQL����(select)

	public JDBCTest() {
		try {
			// ������
			// 1. ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. ���ᰴü����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			//conn.setAutoCommit(false); // commit��ɱ��� ��ٷ�
			System.out.println("DB���� ����");

			// 3. statement ���ఴü ���� -->DML ��û����
			stmt = conn.createStatement();
			//conn.commit();
			//conn.rollback();

			// ����1 ������̺� ��������� ����Ͻÿ�
			String sql = "insert into emp3 (empno, ename, sal, deptno) values(7000,'ȫ�浿',2000,10)";
			//stmt.executeUpdate(sql);
			sql = "insert into emp3 (empno, ename, sal, deptno) values(7002,'�����',3000,20)";
			//stmt.executeUpdate(sql);
			sql = "insert into emp3 (empno, ename, sal, deptno) values(7004,'���ֿ�',4000,30)";
			//stmt.executeUpdate(sql);
			
			System.out.println("������� ��ϼ���");
			
			// ����� ��������ϱ�
			sql="delete from emp3 where ename='�����'";
			int t=stmt.executeUpdate(sql);
			//System.out.println("DB�������� -->"+t); // ������ ���� ����
			
			// ���ֿ� ��� 10���μ��� �̵�!
			sql="update emp3 set deptno=10 where ename='���ֿ�'";
			t=stmt.executeUpdate(sql);
			
			if(t>0) {
				System.out.println("DB��������");
			}else {
				System.out.println("#�����ϴ� �̸��� �����ϴ�.");
			}
			
			
			sql = "select empno, ename, sal, deptno from emp3";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString("ename")
				+","+rs.getInt("sal")+","+rs.getInt("deptno") // ��ü�� ������ ���̴�.
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
