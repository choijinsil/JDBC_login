import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		// Map - Ű�� ����
		// key�� ���� ���ڿ���
		// value�� ���� Object�� ���

		Map<String, Object> map = new HashMap<String, Object>();
		// HashMap<String, Object> map2= new HashMap<>(); <-- �̷��Ե� �����ϴ�.
		// ������ �����ϱ�!
		map.put("�̸�", "siri");
		map.put("����", 13);
		map.put("����", "�л�");

		// ������ ������ ��������
		System.out.println("key ���>>>" + map.keySet()); // [�̸�, ����, ����]
		System.out.println("value ���>>>" + map.values()); // [siri, 13, �л�]
		System.out.println("Ű���� ���>>>" + map.toString()); // {�̸�=siri, ����=13, ����=�л�}
		System.out.println("�ش�Ű�� ���� ���>>>" + map.get("����")); // 13

		// ������ �����ϱ�
		map.remove("�̸�");

		// ������ �����ϱ�
		map.replace("����", 10);

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("k1", "siri");
		map2.put("k2", "sani");
		map2.put("k3", "���");
		map2.put("k3", "������"); // �ߺ��Ǵ� Ű ���� ������ �����Ͱ� ���
		Collection<String> col = map2.values();
		Iterator<String> it = col.iterator();

		System.out.println("<< ��ü map2������ >>");
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("<< ��ü map2�� Ű ��� >>");
		Set<String> set = map2.keySet();
		Iterator<String> keyset = set.iterator();
		while (keyset.hasNext()) {
			String key = keyset.next();
			System.out.println(key + "�� ����� ������ :" + map2.get(key));
		}

		System.out.println("=====================");
		// ����
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("person", new Person(4, "�浿", 13, "�л�"));
		map3.put("k2", new Car("�ҳ�Ÿ", 4, 100));
		map3.put("�Ÿ�", "10km");

		Collection<Object> col2 = map3.values();
		Iterator<Object> it2 = col2.iterator();

		System.out.println("<< ��ü map2������ >>");
		while (it2.hasNext()) {
			System.out.println(it2.next());
		}

		System.out.println("�Ÿ�: " + map3.get("�Ÿ�"));
		Person ob = (Person) map3.get("person");
		System.out.println("�̸�: " + ob.getName());

	}

}
