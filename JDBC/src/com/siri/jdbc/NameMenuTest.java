package com.siri.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

// 유효성 검사 - 유효한 숫자인지?
/*
<NameMenu, NameMenuTest추가>
1. 번호에 숫자아닌 값이 입력되는 경우ooo
   화면에 ----> #번호만 입력하세요!!
   
2. 중복이름입력 방지
  예) "홍길동"이 배열names에 저장되어 있는 경우 ooo
   화면에 ----> #이미 입력된 이름입니다!!
   
3. 수정과 삭제에서 없는 이름에 대한 체크ooo
   화면에 ----> #존재하지 않는 이름입니다!!
   
4. 배열에 5개의 이름이 전부 입력되었을 때 초과 메시지 출력
   화면에 ----> #더 이상 입력할 수 없습니다
                           한 개 이상의 이름을 먼저 삭제한 후 입력하세요!!    
                           */
public class NameMenuTest {
	public static void main(String[] args) throws IOException, SQLException {

		// 반복되는 메뉴 출력(화면 뷰 처리)
		// readLine 입력받는 것
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean isOk;
		String no;
		NameMenu nm = new NameMenu(); // 레퍼런스메뉴

		do {
			System.out.println();
			System.out.println("<이름메뉴>");
			System.out.println("1.추가 2.검색 3.수정 4.삭제 5.종료");
			System.out.print("번호입력 -->");
			no = br.readLine();
			// 번호 숫자 아닌 값이 입력되는 경우 번호만 입력하세요!

			switch (no) { // jdk 7버젼부터 switch문에 조건값이 들어간다.

			case "1":
				// 5개 입력시 입력을 할 수 없습니다.
				System.out.print("이름입력: ");
				String name = br.readLine();
				nm.add(name);
				break;

			case "2":
				nm.search();
				break;

			case "3":
				// 중복이름 입력방지
				System.out.print("기존이름입력: ");
				String oldName = br.readLine();

				System.out.print("변경이름입력: ");
				String newName = br.readLine();
				nm.update(oldName, newName);
				break;

			case "4":
				// 없는 이름 체크
				System.out.print("삭제할 이름입력: ");
				String delName = br.readLine();

				nm.delete(delName);

				break;

			}
		} while (!no.equals("5")); // 1,2,3,4 입력했다면
	}

}
