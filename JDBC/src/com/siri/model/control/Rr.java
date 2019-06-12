package com.siri.model.control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.siri.model.dto.Person;

public class Rr {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	Properties pro;

	public Rr() {
		pro = new Properties();

		try {
			pro.load(new FileReader("conn/conn.properties")); // ���� �о����
			Class.forName(pro.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean insert(Person p) {
		connect();

		try {
			stmt = conn.createStatement();
			String sql = "insert into person values(person_seq.nextval, '" + p.getName() + "', " + p.getAge() + ", '"
					+ p.getJob() + "')";
			System.out.println("�߰� SQL -->" + sql);
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	private void connect() {
		try {
			// url, user, pwd�� ���� ���� �൵ �ȴ�. ������ �� pro�� �ǳĸ� getProperty(url, info)�����ϴ�.
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);

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
