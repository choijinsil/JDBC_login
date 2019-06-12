package com.siri.view;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ServiceForm {

	// 보기버튼 클릭시 전체보기 나옴
	// 버튼 - 보기, 수정, 삭제, 검색
	// 버튼 - 전체보기, 수정, 삭제, 이름검색

	JTable table;
	DefaultTableModel dtm;
	JButton bt_sel_all, bt_up, bt_del, bt_sel_name, bt_exit;
	
	public ServiceForm() {
		
	}

}
