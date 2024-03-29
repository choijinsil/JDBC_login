import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		// Map - 키와 벨류
		// key는 보통 문자열을
		// value는 보통 Object를 사용

		Map<String, Object> map = new HashMap<String, Object>();
		// HashMap<String, Object> map2= new HashMap<>(); <-- 이렇게도 가능하다.
		// 데이터 저장하기!
		map.put("이름", "siri");
		map.put("나이", 13);
		map.put("직업", "학생");

		// 저장한 데이터 꺼내오기
		System.out.println("key 출력>>>" + map.keySet()); // [이름, 나이, 직업]
		System.out.println("value 출력>>>" + map.values()); // [siri, 13, 학생]
		System.out.println("키벨류 출력>>>" + map.toString()); // {이름=siri, 나이=13, 직업=학생}
		System.out.println("해당키의 값을 출력>>>" + map.get("나이")); // 13

		// 데이터 삭제하기
		map.remove("이름");

		// 데이터 수정하기
		map.replace("나이", 10);

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("k1", "siri");
		map2.put("k2", "sani");
		map2.put("k3", "사니");
		map2.put("k3", "최진실"); // 중복되는 키 값중 마지막 데이터가 덮어씀
		Collection<String> col = map2.values();
		Iterator<String> it = col.iterator();

		System.out.println("<< 전체 map2데이터 >>");
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("<< 전체 map2의 키 출력 >>");
		Set<String> set = map2.keySet();
		Iterator<String> keyset = set.iterator();
		while (keyset.hasNext()) {
			String key = keyset.next();
			System.out.println(key + "에 저장된 데이터 :" + map2.get(key));
		}

		System.out.println("=====================");
		// 문제
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("person", new Person(4, "길동", 13, "학생"));
		map3.put("k2", new Car("소나타", 4, 100));
		map3.put("거리", "10km");

		Collection<Object> col2 = map3.values();
		Iterator<Object> it2 = col2.iterator();

		System.out.println("<< 전체 map2데이터 >>");
		while (it2.hasNext()) {
			System.out.println(it2.next());
		}

		System.out.println("거리: " + map3.get("거리"));
		Person ob = (Person) map3.get("person");
		System.out.println("이름: " + ob.getName());

	}

}
