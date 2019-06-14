import java.security.AllPermission;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

class A {
	void method(Vector v) {

		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}
	}

	void method2(ArrayList ar) {
		for (int i = 0; i < ar.size(); i++) {
			System.out.println(ar.get(i));
		}
	}

	void methdCommon(List list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}

public class ListTest {

	public static void main(String[] args) {
		Vector v = new Vector();
		v.add("홍길동");
		v.add("길라임");

		A a = new A();
		a.method(v);

		ArrayList ar = new ArrayList();
		ar.add("김주원");
		ar.add("김유신");
		a.method2(ar);

		System.out.println("======================");
		List v2 = new Vector();
		v2.add(100);
		v2.add(200);
		a.methdCommon(v2);

		List ar2 = new ArrayList();
		ar2.add(3000);
		ar2.add(4000);
		a.methdCommon(ar2);
		// ArrayList와 Vector는 사용문법이 같다.
		// 차이점은 ArrayList처리속도가 빠르다. 왜냐 Vector에는 동기화 코드가 추가 되어있기 때문이지

		// 자료에 대한 추가 검색 수정 삭제
		// String만 받을 수 있다.
		Vector<String> v3 = new Vector<String>();
		v3.add("길동");
		v3.add("라임");
		v3.add("주원");
		// v3.add(100);
		// 정상출력되는 이유?
		// 벡터 toString() 오버라이딩을 구현해서 저장된 데이터를 보여준다.
		System.out.println("v3>>" + v3);

		// 여러가지 자료형 사용 가능.
		Vector v6 = new Vector(); // jdk 1.4까진 이렇게 사용 이후엔 위의 방법 사용 (generic)
		// == Vector<Object> v6= new Vector<Object>(); // jdk 1.4까진 이렇게 사용 이후엔 위의 방법 사용
		// (generic)
		v6.add("길동");
		v6.add("라임");
		v6.add("주원");
		v6.add(3000);
		v6.add(true);
		// 정상출력되는 이유?
		// 벡터 toString() 오버라이딩을 구현해서 저장된 데이터를 보여준다.
		System.out.println("v6>>" + v6);

		ArrayList<String> alist = new ArrayList<String>();
		alist.add("홍");
		alist.add("길");
		alist.add("동");
		//System.out.println("alist>>" + alist);
		System.out.println("##########################");
		a.methdCommon(alist);

		LinkedList<String> linkList = new LinkedList<String>();
		linkList.add("a");
		linkList.add("b");
		linkList.add("b");
		System.out.println("##########################");
		//System.out.println("linkList>>" + linkList);
		a.methdCommon(linkList);

		// ArrayList LinkedList 속도 테스트!
		ArrayList<Person> list10 = new ArrayList<Person>();
		LinkedList<Person> list20 = new LinkedList<Person>();

		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			list10.add(new Person(1, "sirings", 13, "학생"));
		}
		long end = System.currentTimeMillis();
		System.out.println("ArrayLit데이터 입력시간 (순차):" + (end - start));

		
		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			list20.add(new Person(1, "sirings", 13, "학생"));
		}
		end = System.currentTimeMillis();
		System.out.println("LinkedList데이터 입력시간 (순차):" + (end - start));
		System.out.println("----------------------------");
		
		
		ArrayList<Person> list30 = new ArrayList<Person>();
		LinkedList<Person> list40 = new LinkedList<Person>();

		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			// 0인덱스 
			list30.add(0, new Person(1, "sirings", 13, "학생")); // 0인덱스에 값넣기
		}
		end = System.currentTimeMillis();
		System.out.println("ArrayLit데이터 입력시간 (순차):" + (end - start));
		
		
		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			// 0인덱스 
			list40.add(0, new Person(1, "sirings", 13, "학생")); // 0인덱스에 값넣기
		}
		end = System.currentTimeMillis();
		System.out.println("LinkedList데이터 입력시간 (중간):" + (end - start));

	}

}
