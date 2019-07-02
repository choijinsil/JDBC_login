package com.siri.model.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.siri.model.vo.MembershipVO;

import iba.MySqlMapClient;

public class MembershipDAO2 {
	// Controller클래스 : MembershipDAO ---> MembershipDAO2 변경
	// membership.xml파일생성하고 DAO2에 있는 모든 sql문을 옮기고 실행
	SqlMapClient smc;

	public MembershipDAO2() {
		smc = MySqlMapClient.getSqlMapInstance();
	}// 생성자

	public boolean create(MembershipVO vo) {
		try {
			smc.insert("member.create", vo);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}// create

	// 수정폼에 입력된 데이터로 DB데이터 갱신(재입력)
	public boolean modify(MembershipVO vo) {
		try {
			if (smc.update("member.modify", vo) == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}// modify

	// 회원삭제(탈퇴)
	public boolean remove(String id) {
		try {
			if (smc.delete("member.remove", id) == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}// remov

	// 특정 아이디의 (DB내에)존재 유무 체크 ==> 등록(insert)시 에러발생 방지
	public int findExistId(String id) throws SQLException {
		return (Integer) smc.queryForObject("member.findExistId", id);
	}// findExistId

	// (전체)회원 정보 조회
	public List<MembershipVO> findAll() throws SQLException {

		return smc.queryForList("member.findAll");

	}// findAll

	// (이름패턴)회원 정보 조회
	public List<MembershipVO> findByName(String name) throws SQLException {

		return smc.queryForList("member.findByName", "%" + name + "%");

	}// findAll

	// (이름패턴)회원 정보 조회
	public ArrayList<MembershipVO> findSearch(Map<String, String> map) {
		connect();
		ArrayList<MembershipVO> list = new ArrayList<MembershipVO>();
		// 컬럼: id,pass,name,ssn1,ssn2,phone,addr,job
		String title = map.get("title");
		String keyword = map.get("keyword");

		try {

			String sql = "select id,name,ssn1,ssn2,phone,addr,job from membership ";

			if (title.equals("아이디"))
				sql += "where id like ?";
			else if (title.equals("이름"))
				sql += "where name like ?";
			else if (title.equals("주소"))
				sql += "where addr like ?";

			stmt = conn.prepareStatement(sql);// sql문 전송
			stmt.setString(1, "%" + keyword + "%");// '%홍%'
			rs = stmt.executeQuery();// sql문 실행요청(실행시점!!)
			// 덩어리

			while (rs.next()) {// 행얻기
				// 열데이터 얻기
				MembershipVO vo = new MembershipVO();
				// 7개의 관련있는 속성데이터를 묶어주기 위해 사용.
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setSsn1(rs.getInt("ssn1"));
				vo.setSsn2(rs.getInt("ssn2"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddr(rs.getString("addr"));
				vo.setJob(rs.getString("job"));

				list.add(vo);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;
	}// findAll

	// 회원정보 수정(폼)에 필요한 데이터 조회(검색)
	public MembershipVO findById(String id) throws SQLException {

		return (MembershipVO) smc.queryForObject("member.findById", id);
	}// findById

	public boolean findLogin(String id, String pass) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id); // 꼭 매핑 잘해야 한다.
		map.put("pass", pass);
		int cnt = (Integer) smc.queryForObject("member.findLogin", map);
		if (cnt > 0) {
			return true;
		}
		return false;
	}// findLogin

	public String findLogin2(String id, String pass) throws SQLException {
		String dpass = (String) smc.queryForObject("member.findlogin2", id);

		if (dpass != null) {
			// 아이디가 존재 한다면
			if (dpass.equals(pass)) {
				return "sucess";
			} else {
				return "fail_pass";
			}
		}
		// 3. 아이디 존재 X
		return "fail_id";
	}// findLogin2

}// MembershipDAO
