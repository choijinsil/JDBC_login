package com.siri.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import iba.MySqlMapClient;

public class Ibatis {
	  public static void main(String[] args) {
		 try {
			 SqlMapClient smc = MySqlMapClient.getSqlMapInstance();
				
				//라임 사원 추가(8000,lime,2000,30)
				 //smc.insert("empClone.create");
				
				//부서 추가(50,'개발부','남부터미널')
				 Map<String, String> map = new HashMap<>();
				   //map.put(String key, Object value);
				   map.put("dname", "개발부");
				   map.put("loc", "남부터미");
				 smc.insert("deptClone.create",map);
				 //네임스페이스를 통해 서로 다른 XML문서에 작성된 id충돌을 방지할 수 있음.
				 
				 
				 System.out.println("입력성공^^*");
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  }//main
	}
