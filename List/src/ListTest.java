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
		v.add("ȫ�浿");
		v.add("�����");

		A a = new A();
		a.method(v);

		ArrayList ar = new ArrayList();
		ar.add("���ֿ�");
		ar.add("������");
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
		// ArrayList�� Vector�� ��빮���� ����.
		// �������� ArrayListó���ӵ��� ������. �ֳ� Vector���� ����ȭ �ڵ尡 �߰� �Ǿ��ֱ� ��������

		// �ڷῡ ���� �߰� �˻� ���� ����
		// String�� ���� �� �ִ�.
		Vector<String> v3 = new Vector<String>();
		v3.add("�浿");
		v3.add("����");
		v3.add("�ֿ�");
		// v3.add(100);
		// ������µǴ� ����?
		// ���� toString() �������̵��� �����ؼ� ����� �����͸� �����ش�.
		System.out.println("v3>>" + v3);

		// �������� �ڷ��� ��� ����.
		Vector v6 = new Vector(); // jdk 1.4���� �̷��� ��� ���Ŀ� ���� ��� ��� (generic)
		// == Vector<Object> v6= new Vector<Object>(); // jdk 1.4���� �̷��� ��� ���Ŀ� ���� ��� ���
		// (generic)
		v6.add("�浿");
		v6.add("����");
		v6.add("�ֿ�");
		v6.add(3000);
		v6.add(true);
		// ������µǴ� ����?
		// ���� toString() �������̵��� �����ؼ� ����� �����͸� �����ش�.
		System.out.println("v6>>" + v6);

		ArrayList<String> alist = new ArrayList<String>();
		alist.add("ȫ");
		alist.add("��");
		alist.add("��");
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

		// ArrayList LinkedList �ӵ� �׽�Ʈ!
		ArrayList<Person> list10 = new ArrayList<Person>();
		LinkedList<Person> list20 = new LinkedList<Person>();

		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			list10.add(new Person(1, "sirings", 13, "�л�"));
		}
		long end = System.currentTimeMillis();
		System.out.println("ArrayLit������ �Է½ð� (����):" + (end - start));

		
		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			list20.add(new Person(1, "sirings", 13, "�л�"));
		}
		end = System.currentTimeMillis();
		System.out.println("LinkedList������ �Է½ð� (����):" + (end - start));
		System.out.println("----------------------------");
		
		
		ArrayList<Person> list30 = new ArrayList<Person>();
		LinkedList<Person> list40 = new LinkedList<Person>();

		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			// 0�ε��� 
			list30.add(0, new Person(1, "sirings", 13, "�л�")); // 0�ε����� ���ֱ�
		}
		end = System.currentTimeMillis();
		System.out.println("ArrayLit������ �Է½ð� (����):" + (end - start));
		
		
		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			// 0�ε��� 
			list40.add(0, new Person(1, "sirings", 13, "�л�")); // 0�ε����� ���ֱ�
		}
		end = System.currentTimeMillis();
		System.out.println("LinkedList������ �Է½ð� (�߰�):" + (end - start));

	}

}
