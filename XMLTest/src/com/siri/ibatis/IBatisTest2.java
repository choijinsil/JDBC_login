package com.siri.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import iba.MySqlMapClient;

public class IBatisTest2 {

	public static void main(String[] args) {
		try {
			// XML문서내의 태그 호출
			SqlMapClient smc = MySqlMapClient.getSqlMapInstance();
			EmpVO vo = new EmpVO(8002, "김주원", 3000, 30);
			smc.insert("empClone.insert", vo);
			System.out.println("사원 입력 성공!");

			// 2. 사원 삭제 MARTIN사원, 삭제를 성공하면 MARTIN사원 삭제 메세지를 띄워주기
			// 삭제가 실패하면 MARTIN사원은 존재하지 않는다.
			String delEname = "MARTIN";
			int t = smc.delete("empClone.delete", delEname);
			if (t > 0) {
				System.out.println(delEname + "이 삭제되었습니다.");
			} else {
				System.out.println(delEname + "은 존재하지 않습니다.");
			}

			String upSalEname = "KING";
			t = smc.update("empClone.update", upSalEname);
			if (t > 0) {
				System.out.println(upSalEname + "의 연봉 인상이 정상처리 되었습니다.");
			}
			System.out.println();
			System.out.println("----------select 시작-----------");

			// 4.조회
			// 문제1) 특정사번(7788)을 전달하여 사원명 조회 - selectEname

			int searchEmpno = 7788;
			String ename = (String) smc.queryForObject("empClone.selectEname", searchEmpno);
			System.out.println("사번 7788의 조회된 사람명>>" + ename);

			// 문제2) 특정 부서에 근무하는 사원명 조회 - select30Enames
			int selectDeptNo = 10;
			List<String> ename2 = smc.queryForList("empClone.select30Enames", selectDeptNo);
			for (int i = 0; i < ename2.size(); i++) {
				System.out.println(selectDeptNo + "번 부서에 근무하는 사람>>" + ename2.get(i));
			}

			// 문제4) 특정부서에 근무하는 사원의 사원번호, 사원명, 급여, 부서번호를 출력
			List<EmpVO> list3 = smc.queryForList("empClone.selectDeptno", selectDeptNo);
			for (int i = 0; i < list3.size(); i++) {
				System.out.println(list3.get(i));
			}

			/*
			 * 문제5) 30번 부서에 근무하는 사원중 급여가 1300이하인 사원의 사원명과 급여를 출력. - selectEmpInfo 파라미터:
			 * deptno, sal ==> Map 리턴결과: ename, sal ==> Map
			 */
			Map<String, Integer> inMap = new HashMap<String, Integer>();
			inMap.put("deptno", 30);
			inMap.put("sal", 1300);
			List<Map> list4 = smc.queryForList("empClone.selectEmpInfo", inMap);

			System.out.println("사원정보 (1300) 이하");
			for (int i = 0; i < list4.size(); i++) {
				Map<String, Object> map = list4.get(i);
				System.out.println("사원명: " + map.get("ENAME") + "급여 :" + map.get("SAL"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
