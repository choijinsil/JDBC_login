package com.siri.model.view;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.siri.model.dto.Person;

public class MainView extends JFrame {
	DefaultTableModel dtm;
	public JTable table;
	// public �������? �ٸ� ��Ű���� �ִ� Ŭ����(��Ʈ�ѷ�)���� ����ϱ� ����.
	public JScrollPane scroll_table;

	public JButton bt_insert, bt_del, bt_update, bt_exit;

	JPanel southp, northp;

	JLabel la_time;

	public MainView() {
		setTitle("MainView");

		Object[][] rowData = new Object[0][4];
		// �� ũ�� 0�� ����? ������(������ JTable�� �߰��� ����) 0�ε���(ù��)���� �ϱ� ���ؼ�

		String[] columTitle = { "��ȣ", "�̸�", "����", "����" };

		dtm = new DefaultTableModel(rowData, columTitle);
		// dtm��������? dtm���� addRow() , removeRow() ����ϱ� ���ؼ�!!

		table = new JTable(dtm);
		scroll_table = new JScrollPane(table);

//		.setValue(scroll_table.getVerticalScrollBar().getMaximum());

		// scroll_table��������? 1. ���̺�(������)�� ����� �����͸� ���� ���ؼ� (��ũ�ѹٰ� �ʿ�)!!
		// 2. ���̺��� �÷����� ������ �� ����.

		bt_insert = new JButton("�Է�");
		bt_update = new JButton("����");
		bt_del = new JButton("����");
		bt_exit = new JButton("����");

		southp = new JPanel();
		southp.add(bt_insert);
		southp.add(bt_update);
		southp.add(bt_del);
		southp.add(bt_exit);

		la_time = new JLabel();
		northp = new JPanel();
		northp.add(la_time);

		add("Center", scroll_table);
		add("South", southp);
		add("North", northp);

		setBounds(300, 200, 350, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}// ������

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	// ������ ��ȣ
	public String showMsgDel(String msg) {
		return JOptionPane.showInputDialog(this, msg);
	}

	// ���;ȿ� ����� Person������ JTable�� ����ϴ� ���.
	public void displayTable(ArrayList<Person> arrayList) {
		dtm.setRowCount(0);// ���� addRow�Ǵ� �� �����͸� 0�ε��� ���� ����ϰ���!!
							// ������ JTable�� ��µ� ������ clear�ϴ� ����!!

		for (int i = 0; i < arrayList.size(); i++) {// ������ �ε��� �� == ����� ��

			Object[] rowData = { arrayList.get(i).getNo(), arrayList.get(i).getName(), arrayList.get(i).getAge(),
					arrayList.get(i).getJob() };

			dtm.addRow(rowData);
		}
	}// displayTable

}// MainView
