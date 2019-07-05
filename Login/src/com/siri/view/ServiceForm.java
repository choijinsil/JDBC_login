package com.siri.view;

import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.siri.model.vo.MembershipVO;

public class ServiceForm extends JFrame {

	// �����ư Ŭ���� ��ü���� ����
	// ��ư - ����, ����, ����, �˻�
	// ��ư - ��ü����, ����, ����, �̸��˻�
	// ���� ����� ������ ��ȸ
	// ���� ������ ����

	DefaultTableModel dtm;
	public JTable table;
	// public �������? �ٸ� ��Ű���� �ִ� Ŭ����(��Ʈ�ѷ�)���� ����ϱ� ����.
	JScrollPane scroll_table;

	public JButton bt_sel_all, bt_del, bt_up, bt_sel_name, bt_exit;

	public JMenuItem item_confirm;
	JMenu menu_admnin;
	JMenuBar bar;

	JPanel southp;

	JRadioButton radio_id, radio_name, radio_addr;
	ButtonGroup group;
	JTextField tf_search;
	JPanel panel_first, panel_second, panel_option;

	public ServiceForm() {
		setTitle("Display Data");

		Object[][] rowData = new Object[0][7];
		String[] columTitle = { "ID", "�̸�", "����", "����", "��ȭ��ȣ", "�ּ�", "����" };

		tf_search = new JTextField(15);

		radio_id = new JRadioButton("���̵�", true);
		radio_name = new JRadioButton("�̸�");
		radio_addr = new JRadioButton("�ּ�");

		group = new ButtonGroup();
		group.add(radio_id);
		group.add(radio_name);
		group.add(radio_addr);

		panel_first = new JPanel();
		panel_first.add(radio_id);
		panel_first.add(radio_name);
		panel_first.add(radio_addr);

		panel_second = new JPanel();
		panel_second.add(new Label("�˻���: "));
		panel_second.add(tf_search);

		panel_option = new JPanel();
		panel_option.setLayout(new GridLayout(2, 1));
		panel_option.add(panel_first);
		panel_option.add(panel_second);

		item_confirm = new JMenuItem("����");
		menu_admnin = new JMenu("������");
		menu_admnin.add(item_confirm);
		bar = new JMenuBar();
		bar.add(menu_admnin);

		setJMenuBar(bar);

		dtm = new DefaultTableModel(rowData, columTitle);

		table = new JTable(dtm);
		scroll_table = new JScrollPane(table);

		bt_sel_all = new JButton("��ü����");
		bt_up = new JButton("����");
		bt_del = new JButton("����");
		bt_sel_name = new JButton("�˻�");
		bt_exit = new JButton("����");

		southp = new JPanel();
		southp.add(bt_sel_all);
		southp.add(bt_up);
		southp.add(bt_del);
		southp.add(bt_sel_name);
		southp.add(bt_exit);

		add("Center", scroll_table);
		add("South", southp);

		setBounds(300, 200, 450, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}// ������

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}// showMsg

	public String showInput(String msg) {
		return JOptionPane.showInputDialog(this, msg);
	}// showInput

	// YES_OPTION�� 0, NO_OPTION�� 1, CLOSED_OPTION�� -1�� ��ȯ�Ѵ�

	public int showConfirm(String msg) {
		return JOptionPane.showConfirmDialog(this, msg);
	}// showConfirm

	public Map<String, String> optionMsg() {

		Map<String, String> map = new HashMap<String, String>();
		// options�� �迭
		int t = JOptionPane.showOptionDialog(this, panel_option, "Search", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		// Ȯ��, ��� , x : 0, 1, -1

		String key = tf_search.getText(); // �Է��� ��
		String title = "";// ���� ������ ��

		// ���� ��ư�� ���� �Ǿ��ٸ�
		if (radio_addr.isSelected()) {
			// �ؽ�Ʈ�� �����Ͷ�.
			title = radio_addr.getText();
		} else if (radio_id.isSelected()) {
			title = radio_id.getText();
		} else if (radio_name.isSelected()) {
			title = radio_name.getText();
		}

		map.put("title", title); // ������ ����
		map.put("key", key); // �˻�����

		return map;

	}
	
	public Map<String, String> optionMsg2() {

		Map<String, String> map = new HashMap<String, String>();
		// options�� �迭
		int t = JOptionPane.showOptionDialog(this, panel_option, "Search", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		// Ȯ��, ��� , x : 0, 1, -1

		String key = "%"+tf_search.getText()+"%"; // �Է��� ��
		String title = "";// ���� ������ ��

		// ���� ��ư�� ���� �Ǿ��ٸ�
		if (radio_addr.isSelected()) {
			// �ؽ�Ʈ�� �����Ͷ�.
			map.put("addr", key);
		} else if (radio_id.isSelected()) {
			map.put("id", key);
		} else if (radio_name.isSelected()) {
			map.put("name", key);
		}

		map.put("title", title); // ������ ����
		map.put("key", key); // �˻�����

		return map;

	}

	// ArrayList�ȿ� ����� MembershipVO������ JTable�� ����ϴ� ���.
	// {"ID","�̸�","����","����","��ȭ��ȣ","�ּ�","����"};
	public void displayTable(ArrayList<MembershipVO> list) {

		dtm.setRowCount(0);

		for (int i = 0; i < list.size(); i++) {
			MembershipVO vo = list.get(i);
			int mille = vo.getSsn2() / 1000000;

			// ���� ���ϱ�
			int age;

			if (mille < 3 || mille == 5 || mille == 6) { // 1,2,5,6
				age = Calendar.getInstance().get(Calendar.YEAR) - (1900 + vo.getSsn1() / 10000) + 1;
			} else { // 3,4,7,8
				age = Calendar.getInstance().get(Calendar.YEAR) - (2000 + vo.getSsn1() / 10000) + 1;
			}
			// ���� ���ϱ� ���׿����ڷ�
			String gender = (mille % 2 == 0) ? "����" : "����";

			Object[] rowData = { vo.getId(), vo.getName(), age, gender, /* ����?,����?, */vo.getPhone(), vo.getAddr(),
					vo.getJob() };
			dtm.addRow(rowData);
		}
	}// displayTable

}// ServiceForm
