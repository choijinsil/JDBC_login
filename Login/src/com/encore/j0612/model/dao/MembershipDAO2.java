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
	//ControllerŬ���� : MembershipDAO  ---> MembershipDAO2 ����
	//membership.xml���ϻ����ϰ� DAO2�� �ִ� ��� sql���� �ű�� ����
	SqlMapClient smc;
	
	public MembershipDAO2() {
	   smc = MySqlMapClient.getSqlMapInstance();
	}//������
	
	public boolean create(MembershipVO vo) {
	   try {
		    smc.insert("member.create",vo);
		    return true;
	   } catch (SQLException e) {
		e.printStackTrace();
	   }	   
		return false;
	}//create
		
	//�������� �Էµ� �����ͷ� DB������ ����(���Է�)	
	public boolean modify(MembershipVO vo) {
	   try {
		  if(smc.update("member.modify",vo)==1) return true;
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	  return false;
	}//modify
	
	//ȸ������(Ż��)
	public boolean remove(String id) {
		try {
			if(smc.delete("member.remove",id)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}//remov
	
	//Ư�� ���̵��� (DB����)���� ���� üũ  ==> ���(insert)�� �����߻� ����
	public int findExistId(String id) throws SQLException {
	   return (Integer) smc.queryForObject("member.findExistId",id);
	}//findExistId
	
	
	//(��ü)ȸ�� ���� ��ȸ
	public ArrayList<MembershipVO> findAll() throws SQLException {
	  return (ArrayList<MembershipVO>) smc.queryForList("member.findAll");
	}//findAll
	
	
	//(�̸�����)ȸ�� ���� ��ȸ
	public ArrayList<MembershipVO> findByName(String name) throws SQLException {	
	  return (ArrayList<MembershipVO>) smc.queryForList("member.findByName", "%"+name+"%");
	}//findAll
	
	
	//(�̸�����)ȸ�� ���� ��ȸ
	public ArrayList<MembershipVO> findSearch(Map<String, String> map) throws SQLException {
		//�Ķ���� map: {id, '%yo%'} �Ǵ�  {name, '%yo%'} �Ǵ� {addr, '%yo%'}
		return (ArrayList<MembershipVO>) smc.queryForList("member.findSearch", map);
	}//findAll
	
	
	//ȸ������ ����(��)�� �ʿ��� ������ ��ȸ(�˻�)
	//�÷�: id,pass,name,ssn1,ssn2,phone,addr,job
	
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
		if(dpass != null) {//���̵� ���� O
				if(dpass.equals(pass)) {//1. ���̵� ����O, ��� ��ġO
					return "success";
				}else {//2. ���̵� ����O, ��� ��ġX
					return "fail_pass";
				}
		}
	  return "fail_id";
	}//findLogin2
	
}//MembershipDAO



