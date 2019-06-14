package com.siri.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
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

	// �̸� ���� ȸ�� ������ȸ
	public ArrayList<MembershipVO> findSearch(Map<String, String> map) {

		String title = map.get("title");
		String key = map.get("key");
		ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
		String sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where name like ? ";
		System.out.println("Ÿ��Ʋ>>" + title + "Ű>>" + key);
		connect();
		try {
			if ("���̵�".equals(title)) {
				sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where id like ? ";
			} else if ("�̸�".equals(title)) {
				sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where name like ? ";
			} else {
				sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where addr like ? ";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%"); // %�� ��Ʈ��(sql������ �ƴϴ�.)
			System.out.println(sql);
			rs = pstmt.executeQuery(); // �̹� ������ sql�� �����߱� ������ ���ȣ�� ����

			while (rs.next()) {
				MembershipVO vo = new MembershipVO(rs.getString("id"), rs.getString("name"), rs.getInt("ssn1"),
						rs.getInt("ssn2"), rs.getString("phone"), rs.getString("addr"), rs.getString("job"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// adminȮ��
	public String findAdmin(String id, String pass) {
		String isAdmin = "";
		connect();
		try {

			String sql = "select id from membership where id = ? and pass=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); // sql �������� ����� ������ ����!
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery(); // �̹� ������ sql�� �����߱� ������ ���ȣ�� ����
			if (rs.next()) { // ���̵� �����Ѵٸ�

				isAdmin = rs.getString("id");
				return isAdmin;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return isAdmin;
	}

	public int findExistId(String id) {
		// ���̵� �ߺ�üũ
		connect();
		// MembershipVO vo = null;
		try {

			String sql = "select count(*) from membership where id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) { // ���������ϴ� count�� ������ 1���� ���ð��̱� ������ ������ if���� ����ϴ°� ����
//				vo = new MembershipVO(rs.getString("id"), rs.getString("pass"), rs.getString("name"), rs.getInt("ssn1"),
//						rs.getInt("ssn2"), rs.getString("phone"), rs.getString("addr"), rs.getString("job"));
				return rs.getInt(1); // ���õ� ȸ������ ����!
				// ���� count(*) �� cnt��� ��Ī�� ��ٸ� return rs.getInt("cnt")�� �޾ƾ� �Ѵ�.
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return 0;

	}

	public boolean remove(String id) {
		connect();
		try {
			String sql = "delete from membership where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			int t = pstmt.executeUpdate();
			if (t == 1)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	public ArrayList<MembershipVO> findByName(String name) {

		ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
		connect();
		try {

			String sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where name like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%"); // %�� ��Ʈ��(sql������ �ƴϴ�.)
			System.out.println(sql);
			rs = pstmt.executeQuery(); // �̹� ������ sql�� �����߱� ������ ���ȣ�� ����

			while (rs.next()) {
				MembershipVO vo = new MembershipVO(rs.getString("id"), rs.getString("name"), rs.getInt("ssn1"),
						rs.getInt("ssn2"), rs.getString("phone"), rs.getString("addr"), rs.getString("job"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public boolean modify(MembershipVO vo) {
		connect();
		try {
			String sql = "update membership set pass=?, phone=?, addr=?, job=? where id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getAddr());
			pstmt.setString(4, vo.getJob());
			pstmt.setString(5, vo.getId());

			int t = pstmt.executeUpdate();
			if (t == 1)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}

	public MembershipVO findById(String id) {
		connect();
		MembershipVO vo = null;
		try {

			String sql = "select * from membership where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MembershipVO(rs.getString("id"), rs.getString("pass"), rs.getString("name"), rs.getInt("ssn1"),
						rs.getInt("ssn2"), rs.getString("phone"), rs.getString("addr"), rs.getString("job"));
				return vo; // ���õ� ȸ������ ����!
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}

	public ArrayList<MembershipVO> findAll() {
		ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
		connect();
		try {

			String sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where id<>'admin'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // �̹� ������ sql�� �����߱� ������ ���ȣ�� ����

			while (rs.next()) {
				MembershipVO vo = new MembershipVO(rs.getString("id"), rs.getString("name"), rs.getInt("ssn1"),
						rs.getInt("ssn2"), rs.getString("phone"), rs.getString("addr"), rs.getString("job"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
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
				// rs.getInt("count(*)") ==> �Լ������� ��밡��
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
