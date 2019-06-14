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
			pro.load(new FileReader("conn/conn.properties")); // 파일 읽어오기 url, user, password
			Class.forName(pro.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이름 패턴 회원 정보조회
	public ArrayList<MembershipVO> findSearch(Map<String, String> map) {

		String title = map.get("title");
		String key = map.get("key");
		ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
		String sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where name like ? ";
		System.out.println("타이틀>>" + title + "키>>" + key);
		connect();
		try {
			if ("아이디".equals(title)) {
				sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where id like ? ";
			} else if ("이름".equals(title)) {
				sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where name like ? ";
			} else {
				sql = "select id, name, ssn1, ssn2, phone, addr, job from membership where addr like ? ";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%"); // %는 스트링(sql문법이 아니다.)
			System.out.println(sql);
			rs = pstmt.executeQuery(); // 이미 위에서 sql을 실행했기 때문에 빈괄호로 실행

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

	// admin확인
	public String findAdmin(String id, String pass) {
		String isAdmin = "";
		connect();
		try {

			String sql = "select id from membership where id = ? and pass=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); // sql 구문으로 변경될 염려가 없다!
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery(); // 이미 위에서 sql을 실행했기 때문에 빈괄호로 실행
			if (rs.next()) { // 아이디가 존재한다면

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
		// 아이디 중복체크
		connect();
		// MembershipVO vo = null;
		try {

			String sql = "select count(*) from membership where id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) { // 생략가능하다 count는 무조건 1행이 나올것이기 때문에 하지만 if문을 사용하는게 좋음
//				vo = new MembershipVO(rs.getString("id"), rs.getString("pass"), rs.getString("name"), rs.getInt("ssn1"),
//						rs.getInt("ssn2"), rs.getString("phone"), rs.getString("addr"), rs.getString("job"));
				return rs.getInt(1); // 선택된 회원정보 리턴!
				// 만약 count(*) 에 cnt라는 별칭을 줬다면 return rs.getInt("cnt")로 받아야 한다.
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
			pstmt.setString(1, "%" + name + "%"); // %는 스트링(sql문법이 아니다.)
			System.out.println(sql);
			rs = pstmt.executeQuery(); // 이미 위에서 sql을 실행했기 때문에 빈괄호로 실행

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
				return vo; // 선택된 회원정보 리턴!
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
			rs = pstmt.executeQuery(); // 이미 위에서 sql을 실행했기 때문에 빈괄호로 실행

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
			pstmt.setString(1, id); // sql 구문으로 변경될 염려가 없다!
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery(); // 이미 위에서 sql을 실행했기 때문에 빈괄호로 실행

			return rs.next(); // true인지 false인지

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	// 삽입
	public boolean create(MembershipVO vo) {
		// 생성
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
			rs = pstmt.executeQuery(); // 이미 위에서 sql을 실행했기 때문에 빈괄호로 실행

			return rs.next(); // true인지 false인지

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}

	public boolean findLogin3(String id, String pass) { // 제일 나음
		connect();

		try {
			stmt = conn.createStatement();
			// String sql="select count(*) from membership where id='gildong' and
			// pass='1234'";
			String sql = "select count(*) cnt from membership where id='" + id + "' and pass='" + pass + "'";
			System.out.println("로그인 SQL>>>" + sql);
			rs = stmt.executeQuery(sql);// (조회)sql문 실행요청
			if (rs.next()) {
				// rs.getInt("count(*)") ==> 함수명으로 사용가능
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
		 * <비권장> String sql="select * from membership"; ---> 만약 회원의 수의 10만이면 얻어오는 행의 수도
		 * 10만!!
		 * 
		 * String sql="select * from membership where id=id and pass=pass"; ---> 필요한
		 * 데이터는 id와 pass(2개)인데 '*'를 통해 불필요한 컬럼(6개)도 조회!!
		 * 
		 * String
		 * sql="select id,pass from membership where id='gildong' and pass='1234'"; --->
		 * 'gildong','1234' 데이터에 일치하는 조회 결과도 'gildong','1234'
		 * 
		 * <양호> String sql="select pass from membership where id='gildong'"; ---> 아이디에
		 * 일치하는 pass정보를 DB로 부터 조회 ---> 장점) 아이디가 존재하는지 여부 비밀번호가 일치하는지 여부를 자세히 파악하기에 좋다!!
		 * <권장> String sql="select count(*) from membership where id=id and pass=pass";
		 * 
		 */

		// String sql="select pass from membership where id='gildong'";
		connect();

		try {
			stmt = conn.createStatement();
			String sql = "select pass from membership where id='" + id + "'";
			System.out.println("로그인SQL>>>" + sql);
			rs = stmt.executeQuery(sql);// sql문 DB전달, sql문 실행요청!!
			if (rs.next()) {
				// 아이디 존재 O
				String dpass = rs.getString("pass");
				// dpass:DB에 저장된 비번 , pass:UI통해 전달된 비번
				if (dpass.equals(pass)) {
					// 1. 아이디 존재O, 비번 일치O
					return "success";
				} else {
					// 2. 아이디 존재O, 비번 일치X
					return "fail_pass";
				}

			} // else {
				// 3. 아이디 존재 X
				// return "fail_id";
				// }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		// 3. 아이디 존재 X
		return "fail_id";
	}// findLogin2

	private void connect() {
		try {
			// url, user, pwd를 따로 적어 줘도 된다. 하지만 왜 pro로 되냐면 getProperty(url, info)가능하다.
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void disconnect() {
		try {
			if (conn != null)
				conn.close(); // 이 하나만 사용해도 나머지 다 끊킨다.
		} catch (SQLException e) {
			e.printStackTrace();
		} // 이 하나만 사용해도 나머지 다 끊킨다.
	}

}
