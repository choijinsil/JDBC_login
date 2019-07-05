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

	// 보기버튼 클릭시 전체보기 나옴
	// 버튼 - 보기, 수정, 삭제, 검색
	// 버튼 - 전체보기, 수정, 삭제, 이름검색
	// 이전 저장된 데이터 조회
	// 이전 데이터 수정

	DefaultTableModel dtm;
	public JTable table;
	// public 명시이유? 다른 패키지에 있는 클래스(컨트롤러)에서 사용하기 때문.
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
		String[] columTitle = { "ID", "이름", "나이", "성별", "전화번호", "주소", "직업" };

		tf_search = new JTextField(15);

		radio_id = new JRadioButton("아이디", true);
		radio_name = new JRadioButton("이름");
		radio_addr = new JRadioButton("주소");

		group = new ButtonGroup();
		group.add(radio_id);
		group.add(radio_name);
		group.add(radio_addr);

		panel_first = new JPanel();
		panel_first.add(radio_id);
		panel_first.add(radio_name);
		panel_first.add(radio_addr);

		panel_second = new JPanel();
		panel_second.add(new Label("검색어: "));
		panel_second.add(tf_search);

		panel_option = new JPanel();
		panel_option.setLayout(new GridLayout(2, 1));
		panel_option.add(panel_first);
		panel_option.add(panel_second);

		item_confirm = new JMenuItem("인증");
		menu_admnin = new JMenu("관리자");
		menu_admnin.add(item_confirm);
		bar = new JMenuBar();
		bar.add(menu_admnin);

		setJMenuBar(bar);

		dtm = new DefaultTableModel(rowData, columTitle);

		table = new JTable(dtm);
		scroll_table = new JScrollPane(table);

		bt_sel_all = new JButton("전체보기");
		bt_up = new JButton("수정");
		bt_del = new JButton("삭제");
		bt_sel_name = new JButton("검색");
		bt_exit = new JButton("종료");

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

	}// 생성자

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}// showMsg

	public String showInput(String msg) {
		return JOptionPane.showInputDialog(this, msg);
	}// showInput

	// YES_OPTION은 0, NO_OPTION은 1, CLOSED_OPTION은 -1을 반환한다

	public int showConfirm(String msg) {
		return JOptionPane.showConfirmDialog(this, msg);
	}// showConfirm

	public Map<String, String> optionMsg() {

		Map<String, String> map = new HashMap<String, String>();
		// options는 배열
		int t = JOptionPane.showOptionDialog(this, panel_option, "Search", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		// 확인, 취소 , x : 0, 1, -1

		String key = tf_search.getText(); // 입력한 값
		String title = "";// 라디오 선택한 값

		// 라디오 버튼이 선택 되었다면
		if (radio_addr.isSelected()) {
			// 텍스트를 가져와라.
			title = radio_addr.getText();
		} else if (radio_id.isSelected()) {
			title = radio_id.getText();
		} else if (radio_name.isSelected()) {
			title = radio_name.getText();
		}

		map.put("title", title); // 선택한 제목
		map.put("key", key); // 검색내용

		return map;

	}
	
	public Map<String, String> optionMsg2() {

		Map<String, String> map = new HashMap<String, String>();
		// options는 배열
		int t = JOptionPane.showOptionDialog(this, panel_option, "Search", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		// 확인, 취소 , x : 0, 1, -1

		String key = "%"+tf_search.getText()+"%"; // 입력한 값
		String title = "";// 라디오 선택한 값

		// 라디오 버튼이 선택 되었다면
		if (radio_addr.isSelected()) {
			// 텍스트를 가져와라.
			map.put("addr", key);
		} else if (radio_id.isSelected()) {
			map.put("id", key);
		} else if (radio_name.isSelected()) {
			map.put("name", key);
		}

		map.put("title", title); // 선택한 제목
		map.put("key", key); // 검색내용

		return map;

	}

	// ArrayList안에 저장된 MembershipVO정보를 JTable에 출력하는 기능.
	// {"ID","이름","나이","성별","전화번호","주소","직업"};
	public void displayTable(ArrayList<MembershipVO> list) {

		dtm.setRowCount(0);

		for (int i = 0; i < list.size(); i++) {
			MembershipVO vo = list.get(i);
			int mille = vo.getSsn2() / 1000000;

			// 나이 구하기
			int age;

			if (mille < 3 || mille == 5 || mille == 6) { // 1,2,5,6
				age = Calendar.getInstance().get(Calendar.YEAR) - (1900 + vo.getSsn1() / 10000) + 1;
			} else { // 3,4,7,8
				age = Calendar.getInstance().get(Calendar.YEAR) - (2000 + vo.getSsn1() / 10000) + 1;
			}
			// 성별 구하기 삼항연산자로
			String gender = (mille % 2 == 0) ? "여자" : "남자";

			Object[] rowData = { vo.getId(), vo.getName(), age, gender, /* 나이?,성별?, */vo.getPhone(), vo.getAddr(),
					vo.getJob() };
			dtm.addRow(rowData);
		}
	}// displayTable

}// ServiceForm
