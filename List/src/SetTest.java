import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SetTest {

	public static void main(String[] args) {
		// 중복x 순서x
		Set<String> set = new HashSet<String>();
		SortedSet<String> set2 = new TreeSet<String>(); // null입력불가
		
		// 데이터 추가
		set.add("홍길동");
		set.add("길라임");
		set.add("김주원");
		set.add("홍길동");
		set.add("이순신");
		set.add("홍길동");
		set.add(null);
		set.add(null);
		System.out.println("Set저장된 요소 겟수: "+set.size());
		
		Iterator< String> it= set.iterator();// 모든 데이터 얻어오기
		while(it.hasNext()) {
			
			System.out.println(it.next());
		} // it에 열거된 데이터가 존재한다면 true리턴
		
		
	}

}
