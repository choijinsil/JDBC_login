import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SetTest {

	public static void main(String[] args) {
		// �ߺ�x ����x
		Set<String> set = new HashSet<String>();
		SortedSet<String> set2 = new TreeSet<String>(); // null�ԷºҰ�
		
		// ������ �߰�
		set.add("ȫ�浿");
		set.add("�����");
		set.add("���ֿ�");
		set.add("ȫ�浿");
		set.add("�̼���");
		set.add("ȫ�浿");
		set.add(null);
		set.add(null);
		System.out.println("Set����� ��� �ټ�: "+set.size());
		
		Iterator< String> it= set.iterator();// ��� ������ ������
		while(it.hasNext()) {
			
			System.out.println(it.next());
		} // it�� ���ŵ� �����Ͱ� �����Ѵٸ� true����
		
		
	}

}
