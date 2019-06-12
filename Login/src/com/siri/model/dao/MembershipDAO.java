package com.siri.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.siri.model.vo.MembershipVO;

public class MembershipDAO {
	// create, remove, modify, find, findAll
	Connection conn;
	Statement stmt;
	ResultSet rs;
	Properties pro;
	PreparedStatement pstmt;

	public MembershipDAO() {
		pro = new Properties();
		try {
			pro.load(new FileReader("conn/conn.properties")); // ���� �о���� url, user, password
			Class.forName(pro.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean findLogin(String id, String pass) {

		connect();
		try {

			String sql = "select id,pass from membership where id = ? and pass=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); // sql �������� ����� ������ ����!
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery(); // �̹� ������ sql�� �����߱� ������ ���ȣ�� ����

			return rs.next(); // true���� false����

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	// ����
	public boolean create(MembershipVO vo) {
		// ����
		connect();
		try {

			String sql = "insert into membership values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, vo.getSsn1());
			pstmt.setInt(5, vo.getSsn2());
			pstmt.setString(6, vo.getPhone());
			pstmt.setString(7, vo.getAddr());
			pstmt.setString(8, vo.getJob());
			System.out.println(sql);
			rs = pstmt.executeQuery(); // �̹� ������ sql�� �����߱� ������ ���ȣ�� ����

			return rs.next(); // true���� false����

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}

	public boolean findLogin3(String id, String pass) { // ���� ����
		connect();

		try {
			stmt = conn.createStatement();
			// String sql="select count(*) from membership where id='gildong' and
			// pass='1234'";
			String sql = "select count(*) cnt from membership where id='" + id + "' and pass='" + pass + "'";
			System.out.println("�α��� SQL>>>" + sql);
			rs = stmt.executeQuery(sql);// (��ȸ)sql�� �����û
			if (rs.next()) {
				// rs.getInt("count(*)") ==> (X) ���� (�Լ����� �÷������� ������� ����)
				// rs.getInt("cnt") ==> (O)
				int cnt = rs.getInt(1);
				if (cnt > 0)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}// findLogin

	public String findLogin2(String id, String pass) {

		/*
		 * <�����> String sql="select * from membership"; ---> ���� ȸ���� ���� 10���̸� ������ ���� ����
		 * 10��!!
		 * 
		 * String sql="select * from membership where id=id and pass=pass"; ---> �ʿ���
		 * �����ʹ� id�� pass(2��)�ε� '*'�� ���� ���ʿ��� �÷�(6��)�� ��ȸ!!
		 * 
		 * String
		 * sql="select id,pass from membership where id='gildong' and pass='1234'"; --->
		 * 'gildong','1234' �����Ϳ� ��ġ�ϴ� ��ȸ ����� 'gildong','1234'
		 * 
		 * <��ȣ> String sql="select pass from membership where id='gildong'"; ---> ���̵�
		 * ��ġ�ϴ� pass������ DB�� ���� ��ȸ ---> ����) ���̵� �����ϴ��� ���� ��й�ȣ�� ��ġ�ϴ��� ���θ� �ڼ��� �ľ��ϱ⿡ ����!!
		 * <����> String sql="select count(*) from membership where id=id and pass=pass";
		 * 
		 */

		// String sql="select pass from membership where id='gildong'";
		connect();

		try {
			stmt = conn.createStatement();
			String sql = "select pass from membership where id='" + id + "'";
			System.out.println("�α���SQL>>>" + sql);
			rs = stmt.executeQuery(sql);// sql�� DB����, sql�� �����û!!
			if (rs.next()) {
				// ���̵� ���� O
				String dpass = rs.getString("pass");
				// dpass:DB�� ����� ��� , pass:UI���� ���޵� ���
				if (dpass.equals(pass)) {
					// 1. ���̵� ����O, ��� ��ġO
					return "success";
				} else {
					// 2. ���̵� ����O, ��� ��ġX
					return "fail_pass";
				}

			} // else {
				// 3. ���̵� ���� X
				// return "fail_id";
				// }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		// 3. ���̵� ���� X
		return "fail_id";
	}// findLogin2

	private void connect() {
		try {
			// url, user, pwd�� ���� ���� �൵ �ȴ�. ������ �� pro�� �ǳĸ� getProperty(url, info)�����ϴ�.
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void disconnect() {
		try {
			if (conn != null)
				conn.close(); // �� �ϳ��� ����ص� ������ �� ��Ų��.
		} catch (SQLException e) {
			e.printStackTrace();
		} // �� �ϳ��� ����ص� ������ �� ��Ų��.
	}

}
