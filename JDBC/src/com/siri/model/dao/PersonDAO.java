package com.siri.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.siri.model.dto.Person;

public class PersonDAO {
	// DAO - Database Access Object
	// DB���밴ü
	Connection conn;
	Statement stmt;
	ResultSet rs;
	Properties pro;

	public PersonDAO() {
		pro = new Properties();

		try {
			pro.load(new FileReader("conn/conn.properties")); // ���� �о����
			Class.forName(pro.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean insert(Person p) {
		// public void insert(Person p) {
		// ���ڰ� DTO, VO�� ����.

		connect();
		try {
			stmt = conn.createStatement();
			String sql = "insert into person values(person_seq.nextval, '" + p.getName() + "', " + p.getAge() + ", '"
					+ p.getJob() + "')"; // �̷��� DB�� ������ �ְ� �����شپ�
			// System.out.println("�߰� SQL --> " + sql);
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}

	public boolean update(Person p) {
		// ���ڰ� beans��
		// �Ǵ� public boolean update(Person p)
		connect();
		try {
			stmt = conn.createStatement();
			String sql = "update person set age=" + p.getAge() + " , job='" + p.getJob() + "' where no=" + p.getNo();
			System.out.println("����SQL>>>" + sql);
			int t = stmt.executeUpdate(sql);
			if (t > 0) {
				System.out.println("DB��������");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;

	}

	public boolean delete(int no) {
		// primary key�� ���� ����.
		connect();
		try {
			stmt = conn.createStatement();
			String sql = "delete from person where no=" + no;
			System.out.println("sql��Ȯ��: " + sql);
			int t = stmt.executeUpdate(sql);
			if (t > 0) {
				System.out.println("DB��������");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	public Person select(int no) {
		// �Ѱ��� Person���� ������ - ������
		connect();
		Person p = new Person();
		try {
			stmt = conn.createStatement();
			String sql = "select * from person where no=" + no;

			rs = stmt.executeQuery(sql);
			rs.next();

			p.setNo(rs.getInt("no"));
			p.setName(rs.getString("name"));
			p.setAge(rs.getInt("age"));
			p.setJob(rs.getString("job"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		System.out.println(p.getNo());
		return p;
	}

	public ArrayList<Person> selectAll() {
		ArrayList<Person> list = new ArrayList<Person>();
		connect();
		try {
			stmt = conn.createStatement();
			String sql = "select * from person order by no";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Person p = new Person(rs.getInt("no"), rs.getString("name"), rs.getInt("age"), rs.getString("job"));
//				p.setNo(rs.getInt("no"));
//				p.setName(rs.getString("name"));
//				p.setAge(rs.getInt("age"));
//				p.setJob(rs.getString("job"));

				list.add(p);
			}
			// System.out.println(list);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
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
