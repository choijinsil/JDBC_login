package com.siri.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiestTest {
	public static void main(String[] args) {
		// 속성 데이터(문자열) 를 담는 클래스
		// 키가 존재해서 해당키로 데이터를 열수있다.
		Properties pro = new Properties();

		// 데이터 저장, 입력
		// pro.setProperty(String key, String vaule);
		// key: 저장 또는 검색을 위한 값 ( 유일한값)
		// value: 저장하고자 하는 문자열 데이터
		pro.setProperty("k1", "길동");// k1키로 "길동"데이터 저장!!
		pro.setProperty("k2", "라임");
		pro.setProperty("k3", "주원");

		pro.setProperty("k3", "유신");// ---> k3에 "유신" 데이터 overwrite

		// 데이터 출력(조회)
		System.out.println("k1===> " + pro.getProperty("k1"));
		System.out.println("k2===> " + pro.getProperty("k2"));
		System.out.println("k3===> " + pro.getProperty("k3"));

		System.out.println("===============================");
		// 만약 키값을 모르는 경우?

		Enumeration enu = pro.propertyNames();
		// Enumeration: 열거형 인터페이스

		while (enu.hasMoreElements()) {
			Object key = enu.nextElement();
			System.out.println(key + ":" + pro.getProperty(key.toString()));
		}

		// test.properties파이로부터 속성데이터 얻어오기
		// 속성이름 = 속성데이터
		// 구분자 = 서로 다른 속성을 라인으로 구분한다.
		// "=" 보통 등호 앞과 뒤에 공백을 주지 않는다.

		try {
			pro.load(new FileReader("test.properties")); // 파일 읽어오기
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("이름: " + pro.getProperty("name"));
		System.out.println("이름: " + pro.getProperty("age"));
		System.out.println("직업: " + pro.getProperty("job"));
		System.out.println("연봉: " + pro.getProperty("sal"));
		
		
		
		
		
		
		
		
		

	}

}
