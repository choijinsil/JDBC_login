package com.siri.dom;

class A {
	// 필드
	// 메소드
	// 동일클래스에서 메모리할당을 두번이상 하지 않겟다.
	// 보통 sql문 사용시, conn에서 많이 사용을 한다. 중복되고 동일한 내용 반복시에!
	// 싱글톤방식 - 메모리 낭비 방지

	private static A instance;

	public A() {

	}

	public static A getInstance() {
		if (instance == null) { // instance값이 null 이라면 A객체 생성 아니라면 생성 안한다.
			instance = new A();
		}
		return instance;
	}
}

class B {
	void hello() {
	}

	static void goodDay() {
	}
}

public class SinglleTonTest {
	// 싱글톤 - 특정 클래스에 대해서 한개의 메모리만 생성해서 공유 (메모리 절약)

	public static void main(String[] args) {
		A a = A.getInstance();
		A a1 = A.getInstance();
		A a2 = A.getInstance();

		System.out.println(a);
		System.out.println(a1);
		System.out.println(a2);

	}
}

/*
싱글톤(singleton)방식 ----> 메모리 낭비 방지
  예) 한개의 DB서버(DBMS)는  여러명의 사용자(application,응용프로그램)이 데이터 공유
    ---> 사용된 Connection(연결객체)은 바로바로 반환해서 다음 사람(프로그램)이 사용하도록
                     해야함.
                     
class VendingDAO{

    public void find(){
       Connection conn = new Connection();
    }
    public void insert(VO){
       Connection conn = new Connection();
    }
    ===> 현재 프로그램에서는 절대 2개 이상의 Connection객체가 생성될 수도 있음.
}
==============================================================
class MyDriverManager{
   static Connection conn;
    
    public static getConnection(){
       if(conn==null){
         conn = new Connection();
       }
       return conn;
    }
}
                         
class VendingDAO{
    public void find(){
       Connection conn = MyDriverManager.getConnection();
    }
    public void insert(VO){
       Connection conn = MyDriverManager.getConnection();
    }
    ===> 현재 프로그램에서는 절대 2개 이상의 Connection객체가 생성되지 않음
    
}                         



*/
