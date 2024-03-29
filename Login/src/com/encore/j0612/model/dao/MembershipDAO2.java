package com.encore.j0612.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.encore.j0612.model.vo.MembershipVO;
import com.ibatis.sqlmap.client.SqlMapClient;

import iba.MySqlMapClient;

public class MembershipDAO2 {
	//Controller클래스 : MembershipDAO  ---> MembershipDAO2 변경
	//membership.xml파일생성하고 DAO2에 있는 모든 sql문을 옮기고 실행
	SqlMapClient smc;
	
	public MembershipDAO2() {
	   smc = MySqlMapClient.getSqlMapInstance();
	}//생성자
	
	public boolean create(MembershipVO vo) {
	   try {
		    smc.insert("member.create",vo);
		    return true;
	   } catch (SQLException e) {
		e.printStackTrace();
	   }	   
		return false;
	}//create
		
	//수정폼에 입력된 데이터로 DB데이터 갱신(재입력)	
	public boolean modify(MembershipVO vo) {
	   try {
		  if(smc.update("member.modify",vo)==1) return true;
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	  return false;
	}//modify
	
	//회원삭제(탈퇴)
	public boolean remove(String id) {
		try {
			if(smc.delete("member.remove",id)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}//remov
	
	//특정 아이디의 (DB내에)존재 유무 체크  ==> 등록(insert)시 에러발생 방지
	public int findExistId(String id) throws SQLException {
	   return (Integer) smc.queryForObject("member.findExistId",id);
	}//findExistId
	
	
	//(전체)회원 정보 조회
	public ArrayList<MembershipVO> findAll() throws SQLException {
	  return (ArrayList<MembershipVO>) smc.queryForList("member.findAll");
	}//findAll
	
	
	//(이름패턴)회원 정보 조회
	public ArrayList<MembershipVO> findByName(String name) throws SQLException {	
	  return (ArrayList<MembershipVO>) smc.queryForList("member.findByName", "%"+name+"%");
	}//findAll
	
	
	//(이름패턴)회원 정보 조회
	public ArrayList<MembershipVO> findSearch(Map<String, String> map) throws SQLException {
		//파라미터 map: {id, '%yo%'} 또는  {name, '%yo%'} 또는 {addr, '%yo%'}
		return (ArrayList<MembershipVO>) smc.queryForList("member.findSearch", map);
	}//findAll
	
	
	//회원정보 수정(폼)에 필요한 데이터 조회(검색)
	//컬럼: id,pass,name,ssn1,ssn2,phone,addr,job
	
	public MembershipVO findById(String id) throws SQLException {
	   return (MembershipVO) smc.queryForObject("member.findById",id);
	}//findById
	
	
	
	public boolean findLogin(String id, String pass) throws SQLException {
	      Map<String, String> map = new HashMap<>();
	         map.put("id", id);
	         map.put("pass", pass);
	         
	      int cnt =(Integer)smc.queryForObject("member.findLogin",map);
	      if(cnt>0) return true;
	   return false;
	}//findLogin
	
	public String findLogin2(String id, String pass) throws SQLException {
		String dpass = (String) smc.queryForObject("member.findLogin2",id);
		if(dpass != null) {//아이디 존재 O
				if(dpass.equals(pass)) {//1. 아이디 존재O, 비번 일치O
					return "success";
				}else {//2. 아이디 존재O, 비번 일치X
					return "fail_pass";
				}
		}
	  return "fail_id";
	}//findLogin2
	
}//MembershipDAO



