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
	// DB전용객체
	Connection conn;
	Statement stmt;
	ResultSet rs;
	Properties pro;

	public PersonDAO() {
		pro = new Properties();

		try {
			pro.load(new FileReader("conn/conn.properties")); // 파일 읽어오기
			Class.forName(pro.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean insert(Person p) {
		// public void insert(Person p) {
		// 인자값 DTO, VO가 들어간다.

		connect();
		try {
			stmt = conn.createStatement();
			String sql = "insert into person values(person_seq.nextval, '" + p.getName() + "', " + p.getAge() + ", '"
					+ p.getJob() + "')"; // 이렇게 DB에 넣을수 있게 적어준다앙
			// System.out.println("추가 SQL --> " + sql);
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
		// 인자값 beans가
		// 또는 public boolean update(Person p)
		connect();
		try {
			stmt = conn.createStatement();
			String sql = "update person set age=" + p.getAge() + " , job='" + p.getJob() + "' where no=" + p.getNo();
			System.out.println("수정SQL>>>" + sql);
			int t = stmt.executeUpdate(sql);
			if (t > 0) {
				System.out.println("DB수정성공");
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
		// primary key가 보통 들어간다.
		connect();
		try {
			stmt = conn.createStatement();
			String sql = "delete from person where no=" + no;
			System.out.println("sql문확인: " + sql);
			int t = stmt.executeUpdate(sql);
			if (t > 0) {
				System.out.println("DB삭제성공");
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
		// 한개의 Person정보 얻어오기 - 수정폼
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
