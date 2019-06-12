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
	// public 명시이유? 다른 패키지에 있는 클래스(컨트롤러)에서 사용하기 때문.
	public JScrollPane scroll_table;

	public JButton bt_insert, bt_del, bt_update, bt_exit;

	JPanel southp, northp;

	JLabel la_time;

	public MainView() {
		setTitle("MainView");

		Object[][] rowData = new Object[0][4];
		// 행 크기 0인 이유? 시작을(앞으로 JTable에 추가될 행을) 0인덱스(첫행)부터 하기 위해서

		String[] columTitle = { "번호", "이름", "나이", "직업" };

		dtm = new DefaultTableModel(rowData, columTitle);
		// dtm생성이유? dtm내의 addRow() , removeRow() 사용하기 위해서!!

		table = new JTable(dtm);
		scroll_table = new JScrollPane(table);

//		.setValue(scroll_table.getVerticalScrollBar().getMaximum());

		// scroll_table생성이유? 1. 테이블(사이즈)을 벗어나는 데이터를 보기 위해서 (스크롤바가 필요)!!
		// 2. 테이블의 컬럼명을 보여줄 수 있음.

		bt_insert = new JButton("입력");
		bt_update = new JButton("수정");
		bt_del = new JButton("삭제");
		bt_exit = new JButton("종료");

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

	}// 생성자

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	// 삭제할 번호
	public String showMsgDel(String msg) {
		return JOptionPane.showInputDialog(this, msg);
	}

	// 벡터안에 저장된 Person정보를 JTable에 출력하는 기능.
	public void displayTable(ArrayList<Person> arrayList) {
		dtm.setRowCount(0);// 새로 addRow되는 행 데이터를 0인덱스 부터 출력하겠음!!
							// 이전에 JTable에 출력된 내용을 clear하는 역할!!

		for (int i = 0; i < arrayList.size(); i++) {// 벡터의 인덱스 수 == 사람의 수

			Object[] rowData = { arrayList.get(i).getNo(), arrayList.get(i).getName(), arrayList.get(i).getAge(),
					arrayList.get(i).getJob() };

			dtm.addRow(rowData);
		}
	}// displayTable

}// MainView
