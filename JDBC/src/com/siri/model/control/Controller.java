package com.siri.model.control;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.FocusManager;
import javax.swing.JOptionPane;

import com.siri.model.dao.PersonDAO;
import com.siri.model.dto.Person;
import com.siri.model.view.InputForm;
import com.siri.model.view.MainView;
import com.siri.model.view.UpForm;

public class Controller implements ActionListener {

	MainView mainView;
	InputForm form;
	UpForm upForm;
	// 미션~!
	// 유효성 검사 - 빈값에 대한 체크

	public Controller() {
		// 전체 프로그램에 대한 흐름 제어
		// 1. 사용자 요청 분석 ex.어떤 버튼을 눌렀는지
		// 2. 사용자 입력 데이터 얻어오기 ex. getText
		// 3. ** 모델객체 생성 ex. PersonDAO (결과값 얻기, 저장, 판단)
		// 4. 페이지(뷰) 이동 ex.메인 -> 입력폼

		// 선택사항 - 유효성 검사 -> 사용자가 입력한 데이터에 대한!

		mainView = new MainView();
		mainView.displayTable(new PersonDAO().selectAll());

		form = new InputForm();
		upForm = new UpForm();

		eventUp();

		mainView.scroll_table.getVerticalScrollBar()
				.setValue(mainView.scroll_table.getVerticalScrollBar().getMaximum());
	}

	private void eventUp() {
		// mainView
		mainView.bt_insert.addActionListener(this);
		mainView.bt_update.addActionListener(this);
		mainView.bt_del.addActionListener(this);
		mainView.bt_exit.addActionListener(this);

		// inputForm
		form.bt_submit.addActionListener(this);
		form.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				form.setVisible(false);
				mainView.setVisible(true);
			}
		});

		// upForm
		upForm.bt_submit.addActionListener(this);
		upForm.bt_cancel.addActionListener(this);
		upForm.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				upForm.setVisible(false);
				mainView.setVisible(true);
			}
		});

		// outForm
	}

	public static void main(String[] args) {
		new Controller();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == mainView.bt_insert) {
			// 4. 메인뷰 -> 입력폼으로 이동
			mainView.setVisible(false);
			form.setVisible(true);
		} else if (ob == form.bt_submit) { // db입력요청
			// 유효형 검사 나이 - 숫자값 - null값

			// 2. 사용자 입력 데이터 얻어오기
			String name = form.tf_name.getText().trim();
			String age = form.tf_age.getText().trim();
			String job = form.tf_job.getText().trim();

			// DTO로 전달
			Person p = new Person(0, name, Integer.parseInt(age), job);

			// 5. 데이터 유효성 검사
			if (name.equals("")) {
				form.showMsg("이름입력!");
				return;
			} else if (age.length() == 0) {
				form.showMsg("나이입력!");
				return;
			} else if (age.matches("[\\d]{1,3}")) {
				form.showMsg("숫자만입력!");
				return;
			} else if (job.length() < 1) {
				form.showMsg("직업입력!");
				return;
			}
			// 3. 모델 객체 생성
			PersonDAO dao = new PersonDAO();
			boolean isOk = dao.insert(p);

			// mainview에 장착하기
			if (isOk == true) {
				form.initText();
				mainView.displayTable(dao.selectAll());

				form.setVisible(false);
				mainView.setVisible(true);
			}

			mainView.scroll_table.getVerticalScrollBar()
					.setValue(mainView.scroll_table.getVerticalScrollBar().getMaximum());

			// FocusManager.getCurrentManager().focusNextComponent();
			// mainView.table.requestFocus();
			// mainView.table.setFocusCycleRoot(true);
			// mainView.table.colum
		} else if (ob == form.bt_cancel) {

		} else if (ob == mainView.bt_update) {
			PersonDAO dao = new PersonDAO();
			int row = mainView.table.getSelectedRow();

			if (row == -1) { // 선택된 행이 없다면 마치겠다.
				mainView.showMsg("수정할 행을 선택 !");
				return;
			}
			Object data = mainView.table.getValueAt(row, 0);// 수정할 번호 얻기
			int no = Integer.parseInt(data.toString());

			upForm.upNo = no;
			mainView.setVisible(false);
			Person p = dao.select(no);
			upForm.tf_name.setText(p.getName());
			upForm.tf_age.setText(p.getAge() + "");
			upForm.tf_job.setText(p.getJob());
			upForm.setVisible(true);

		} else if (ob == upForm.bt_submit) { // 수정버튼 클릭시
			System.out.println("수정 버튼 들어왔다?");
			PersonDAO dao = new PersonDAO();

			// System.out.println(upForm.tf_age.getText().toString() + "," +
			// upForm.tf_job.getText().toString());
			Person p = new Person(upForm.upNo, null, Integer.parseInt(upForm.tf_age.getText().toString()),
					upForm.tf_job.getText().toString());
			dao.update(p);

			if (dao.update(p)) {
				mainView.displayTable(new PersonDAO().selectAll());
				upForm.setVisible(false);
				mainView.setVisible(true);
			}
		} else if (ob == upForm.bt_cancel) {

			Person p = new PersonDAO().select(upForm.upNo);
			upForm.initText(p.getAge(), p.getJob());

		} else if (ob == mainView.bt_del) {

			PersonDAO dao = new PersonDAO();

			int msg = Integer.parseInt(mainView.showMsgDel("삭제하실 번호를 적어주세요."));
			
			
			if (dao.delete(msg)) {
				mainView.displayTable(dao.selectAll());
				mainView.showMsg(msg + "번 삭제 되었습니다.");
			} else {
				mainView.showMsg("없는 번호입니다.");
			}

		} else if (ob == mainView.bt_exit) {

		}
	}

}
