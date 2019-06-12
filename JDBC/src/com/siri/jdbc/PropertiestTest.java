package com.siri.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiestTest {
	public static void main(String[] args) {
		// �Ӽ� ������(���ڿ�) �� ��� Ŭ����
		// Ű�� �����ؼ� �ش�Ű�� �����͸� �����ִ�.
		Properties pro = new Properties();

		// ������ ����, �Է�
		// pro.setProperty(String key, String vaule);
		// key: ���� �Ǵ� �˻��� ���� �� ( �����Ѱ�)
		// value: �����ϰ��� �ϴ� ���ڿ� ������
		pro.setProperty("k1", "�浿");// k1Ű�� "�浿"������ ����!!
		pro.setProperty("k2", "����");
		pro.setProperty("k3", "�ֿ�");

		pro.setProperty("k3", "����");// ---> k3�� "����" ������ overwrite

		// ������ ���(��ȸ)
		System.out.println("k1===> " + pro.getProperty("k1"));
		System.out.println("k2===> " + pro.getProperty("k2"));
		System.out.println("k3===> " + pro.getProperty("k3"));

		System.out.println("===============================");
		// ���� Ű���� �𸣴� ���?

		Enumeration enu = pro.propertyNames();
		// Enumeration: ������ �������̽�

		while (enu.hasMoreElements()) {
			Object key = enu.nextElement();
			System.out.println(key + ":" + pro.getProperty(key.toString()));
		}

		// test.properties���̷κ��� �Ӽ������� ������
		// �Ӽ��̸� = �Ӽ�������
		// ������ = ���� �ٸ� �Ӽ��� �������� �����Ѵ�.
		// "=" ���� ��ȣ �հ� �ڿ� ������ ���� �ʴ´�.

		try {
			pro.load(new FileReader("test.properties")); // ���� �о����
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("�̸�: " + pro.getProperty("name"));
		System.out.println("�̸�: " + pro.getProperty("age"));
		System.out.println("����: " + pro.getProperty("job"));
		System.out.println("����: " + pro.getProperty("sal"));
		
		
		
		
		
		
		
		
		

	}

}
