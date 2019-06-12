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
			pro.load(new FileReader("conn/conn.properties")); // 파일 읽어오기
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
			System.out.println("추가 SQL -->" + sql);
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
			// url, user, pwd를 따로 적어 줘도 된다. 하지만 왜 pro로 되냐면 getProperty(url, info)가능하다.
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
					conn.close(); // 이 하나만 사용해도 나머지 다 끊킨다.
			} catch (SQLException e) {
				e.printStackTrace();
			} // 이 하나만 사용해도 나머지 다 끊킨다.
	}

}
