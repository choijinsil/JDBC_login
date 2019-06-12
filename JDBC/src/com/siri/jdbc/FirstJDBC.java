package com.siri.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FirstJDBC {

	public static void main(String[] args) {
		try {// jdbc
				// 1. ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. ���ᰴü ����: connection
			// ms�� sql������ ����Ҷ��� ���� �������� ���� �����ϴ�. <-- user pass�� �ʿ����.
			// ������ ����Ŭ�� ��쿣 �ʿ�������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";

			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB���Ἲ��");

			// 3.
			// dml��� ����
			Statement stmt = conn.createStatement();

			// sql����ϱ�!
			String sql = "delete from dept_copy where deptno=10";
			// sql�����û
			// DML�� ��� executeUpdate - return int
			// DQL�� ��� executeQuery - return
			int t = stmt.executeUpdate(sql);
			System.out.println("T=" + t);
			if (t == 1) { // ������ ���� 1���̶��
				System.out.println("��������!!, T=" + t); // ���� ������ ���� ����
			}

			// dept_copy���� 20,30�� �μ��� �����ϰ� �����޼��� ����
			// �⺻�� auto commit���� �Ǿ��ִ�.
			// conn.setAutoCommit(false); ���� auto�� �����Ϸ��� �� ������ �����ϸ�ȴ�.
			sql = "delete from dept_copy where deptno in (20,30)";
			t = stmt.executeUpdate(sql);
			if (t > 0) {
				System.out.println("# �μ���������!!, T=" + t);
			} else {
				System.out.println("# ������ �μ��� �������� �ʾƿ�.");
			}

			// ---------------------Select�۾��� �ϰ�ʹٸ� -----------------
			// 4. result set - �����ü ����: Result Set
			// - ��ȸ�� ���(�࿭ ������)�� �����Ѵ�.
			sql = "select deptno, dname, loc from dept where deptno=20";
			// RS ���̺��� �� �����Ͷ�
			ResultSet rs = stmt.executeQuery(sql);

			rs.next(); // ����� ������ ���

			System.out.println(rs.getInt("deptno") // rs.getint(1) ��ü����
					+ "," + rs.getString("dname") + "," + rs.getString("loc"));
			
			System.out.println("rs�������� ����!");
			System.out.println("-----------------------------------");
			System.out.println("��ü �μ�����");
			// ���� ��ü �μ��� �μ���ȣ, ������, ������ġ
			sql="select deptno, dname, loc from dept";
			rs= stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString("dname")
				+","+rs.getString("loc") // ��ü�� ������ ���̴�.
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
