import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoSet {
	public static void main(String[] args) {
		// 1~45까지 중복되지 않는 6개의 수
		Random r = new Random();
		Set<Integer> set = new HashSet<Integer>();
		while (true) {
			set.add(r.nextInt(45) + 1);
			if (set.size() == 6)
				break;
		}

		Object[] ob = set.toArray();
		for (int i = 0; i < ob.length; i++) {
			System.out.println(ob[i]);
		}

	}
}
