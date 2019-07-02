package com.siri.ibatis;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import iba.MySqlMapClient;

public class IBatisTest {

	public static void main(String[] args) {
		// 호출하기 위한 객체
		// sql 호출할 준비작업이 끝났다.

		// 1. 사워니추가
		try {
			SqlMapClient smc = MySqlMapClient.getSqlMapInstance();
			smc.insert("empcopy.insert1");
			System.out.println("입력 성공메세지");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
