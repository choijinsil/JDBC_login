package com.siri.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.siri.model.dao.MembershipDAO;
import com.siri.model.vo.MembershipVO;
import com.siri.view.JoinForm;
import com.siri.view.LoginForm;
import com.siri.view.ServiceForm;
import com.siri.view.UpdateForm;

public class Controller implements ActionListener {
	// 시작뷰는 로그인폼 set visible
	JoinForm joinForm;
	LoginForm loginForm;
	ServiceForm serviceForm;
	UpdateForm updateForm;
	String ck_str;
	boolean possDml;

	public Controller() {
		joinForm = new JoinForm();
		loginForm = new LoginForm();
		loginForm.tf_pass.addActionListener(this);
		serviceForm = new ServiceForm();
		updateForm = new UpdateForm();

		loginForm.setVisible(true);
		joinForm.setVisible(false);
		serviceForm.setVisible(false);
		updateForm.setVisible(false);
		possDml = false;
		eventUp();
	}

	public void eventUp() {
		loginForm.bt_login.addActionListener(this);
		loginForm.bt_join.addActionListener(this);
		joinForm.bt_submit.addActionListener(this);

		joinForm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				joinForm.setVisible(false);
				loginForm.setVisible(true);
			}
		});

		serviceForm.bt_sel_all.addActionListener(this);
		serviceForm.bt_up.addActionListener(this);

		serviceForm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				serviceForm.setVisible(false);
				loginForm.setVisible(true);
			}
		});
		updateForm.bt_submit.addActionListener(this);

		updateForm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				updateForm.setVisible(false);
				serviceForm.setVisible(true);
			}
		});
		serviceForm.bt_del.addActionListener(this);
		serviceForm.bt_sel_name.addActionListener(this);
		serviceForm.bt_exit.addActionListener(this);
		joinForm.bt_checkid.addActionListener(this);
		// 중복확인
		joinForm.tf_id.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				System.out.println("keyPressed>>>"+ joinForm.tf_id.getText()	);
//			}

			@Override
			public void keyReleased(KeyEvent e) {
				int num = new MembershipDAO().findExistId(joinForm.tf_id.getText());
				System.out.println("keyReleased>>>" + joinForm.tf_id.getText());
				if (num == 1) {
					// 텍스트 바꾸는 건 forground
					joinForm.bt_checkid.setBackground(Color.RED);
				} else {
					joinForm.bt_checkid.setBackground(Color.GREEN);
				}
			}
		});

		// 주민번호 포커스
		joinForm.tf_ssn1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (joinForm.tf_ssn1.getText().length() == 6) {
					joinForm.tf_ssn2.requestFocus();
				}
			}
		});

		// 전화번호 입력
		joinForm.tf_phone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (joinForm.tf_phone1.getText().length() == 3) {
					joinForm.tf_phone2.requestFocus();
				}
			}
		});

		joinForm.tf_phone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (joinForm.tf_phone2.getText().length() == 4) {
					joinForm.tf_phone3.requestFocus();
				}
			}
		});

		// 관리자 인증
		serviceForm.item_confirm.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == loginForm.bt_login) {
			MembershipDAO dao = new MembershipDAO();
			boolean isOk = dao.findLogin(loginForm.tf_id.getText(), new String(loginForm.tf_pass.getPassword()));

			String isAdmin = new MembershipDAO().findAdmin(loginForm.tf_id.getText(),
					new String(loginForm.tf_pass.getPassword()));

			// 로그인시 admin이면 true, 사용자면 false

			if (isOk) {
				if ("admin".equals(isAdmin)) {
					possDml = true;
				} else {
					possDml = false;
				}

				loginForm.showMsg("로그인성공!");
				loginForm.setVisible(false);
				serviceForm.setVisible(true);
				serviceForm.displayTable(dao.findAll());

			} else {
				loginForm.showMsg("로그인 실패");
			}

		} else if (ob == loginForm.bt_join) {
			loginForm.setVisible(false);
			joinForm.setVisible(true);
			String ck_str2 = new String();
//			
//			while (joinForm.isOk) {

//			ck_str2 += joinForm.ck_str;

//			}

		} else if (ob == joinForm.bt_submit) {
			MembershipDAO dao = new MembershipDAO();
			System.out.println("회원가입완료버튼");
			MembershipVO vo = new MembershipVO();
			vo.setId(joinForm.tf_id.getText().toString());
			vo.setPass(new String(joinForm.tf_pass.getPassword()));
			vo.setName(joinForm.tf_name.getText().toString());
			vo.setSsn1(Integer.parseInt(joinForm.tf_ssn1.getText().toString()));
			vo.setSsn2(Integer.parseInt(new String(joinForm.tf_ssn2.getPassword())));
			// char를 String으로 변환하는 방법
			vo.setPhone(joinForm.tf_phone1.getText().toString() + "-" + joinForm.tf_phone2.getText().toString() // 세개로
																												// //
																												// 붙이깅
					+ "-" + joinForm.tf_phone3.getText().toString());
			vo.setAddr(joinForm.tf_addr.getText().toString());
			vo.setJob(joinForm.cb_job.getSelectedItem().toString());
			System.out.println(joinForm.cb_job.getSelectedItem().toString());

			// vo를 dao create 에 넣으면 되는데... numberformat에러

			if (dao.create(vo)) {
				joinForm.showMsg("회원가입 완료했습니다."); // new로 객체 생성을 하게 되면 join뷰가 두개 띄워지게 된다.
				joinForm.setVisible(false);
				loginForm.setVisible(true);
				joinForm.initText();

			} else {
				joinForm.showMsg("입력값을 확인하세요!");

			}

		} else if (ob == serviceForm.bt_sel_all) {
			MembershipDAO dao = new MembershipDAO();
			serviceForm.displayTable(dao.findAll());

		} else if (ob == serviceForm.bt_up) {
			MembershipDAO dao = new MembershipDAO();
			String id = serviceForm.showInput("수정할 아이디를 입력해주세요!");
			MembershipVO vo = dao.findById(id);
			// 본인 아이디만 수정 삭제 가능하게 하기

			// 관리자일때만 모든 아이디 수정가능
			if (possDml == true) {

				vo = dao.findById(id);
				if (vo == null) {
					// DB에 일치하는 아이디가 존재하지 않는다면
					serviceForm.showMsg("일치하는 아이디가 없습니다.");
					return;
				}

				if (id.trim() != null) {
					serviceForm.setVisible(false);
					updateForm.setVisible(true);
					updateForm.settings(vo);
				} else {
					serviceForm.showMsg("수정할 아이디를 입력해주세요!");
				}
			} else {
				// 관리자가 아닐때
				String isAdmin = new MembershipDAO().findAdmin(loginForm.tf_id.getText(),
						new String(loginForm.tf_pass.getPassword()));
				if (id.equals(isAdmin)) {
					serviceForm.setVisible(false);
					updateForm.setVisible(true);
					updateForm.settings(vo);
				} else {
					serviceForm.showMsg("관리자 인증을 해주세요");
				}
			}
		} else if (ob == updateForm.bt_submit) {
			// 텍스트값 가져와서 vo만들기
			MembershipDAO dao = new MembershipDAO();
			String id = updateForm.tf_id.getText().toString();
			String phone = updateForm.tf_phone1.getText() + "-" + updateForm.tf_phone2.getText() + "-"
					+ updateForm.tf_phone3.getText();
			String addr = updateForm.tf_addr.getText();
			String job = updateForm.cb_job.getSelectedItem().toString();
			String pass = new String(updateForm.tf_pass.getPassword());

			MembershipVO vo = new MembershipVO();
			vo.setId(id);
			vo.setPass(pass);
			vo.setPhone(phone);
			vo.setAddr(addr);
			vo.setJob(job);

			if (dao.modify(vo)) {
				// 수정창에서 서비스 창으로 이동
				updateForm.setVisible(false);
				serviceForm.setVisible(true);
				serviceForm.displayTable(dao.findAll());

			} else {
				updateForm.showMsg("입력된 데이터를 확인하세요!");
			}

		} else if (ob == serviceForm.bt_del) {
			MembershipDAO dao = new MembershipDAO();
			String id = serviceForm.showInput("삭제할 아이디를 입력해주세요.");
			// 관리자가 true라면
			if (possDml == true) {
				if (dao.remove(id)) {
					serviceForm.displayTable(dao.findAll());
				} else {

					serviceForm.showMsg("삭제할 아이디를 확인해주세요.");
				}
				// 관리자 false라면
			} else {
				String isAdmin = new MembershipDAO().findAdmin(loginForm.tf_id.getText(),
						new String(loginForm.tf_pass.getPassword()));

				if (id.equals(isAdmin)) {
					dao.remove(id);
					serviceForm.displayTable(dao.findAll());
					serviceForm.setVisible(false);
					loginForm.setVisible(true);
				} else {
					serviceForm.showMsg("관리자 인증을 해주세요");
				}
			}
		} else if (ob == serviceForm.bt_sel_name) {
			// 이름 검색하기
			MembershipDAO dao = new MembershipDAO();
			// String name = serviceForm.showInput("검색할 이름을 입력해주세요.");
			serviceForm.displayTable(dao.findSearch(serviceForm.optionMsg()));
			// serviceForm.displayTable(dao.findByName(name));

		} else if (ob == serviceForm.bt_exit) {
			System.exit(0);
		} else if (ob == joinForm.bt_checkid) {

		} else if (ob == serviceForm.item_confirm) {
//			if ("인증".equals(serviceForm.item_confirm.getText())) {
			String isAdmin = new MembershipDAO().findAdmin(loginForm.tf_id.getText(),
					new String(loginForm.tf_pass.getPassword()));

			if ("admin".equals(isAdmin)) {
				// 관리자 인증이 되었을때 true
				possDml = true;
				serviceForm.showMsg("이미인증되었습니다!");
			} else {

				// YES_OPTION은 0, NO_OPTION은 1, CLOSED_OPTION은 -1을 반환한다
				int y = serviceForm.showConfirm("관리자로 로그인 할까요?");
				if (y == 0) {
					serviceForm.setVisible(false);
					loginForm.setVisible(true);
					loginForm.initText();
					loginForm.tf_id.requestFocus();
				} else if (y == 1) {
					return;
				} else {
					return;
				}
			}
//			}
		}
	}

	public static void main(String[] args) {
		new Controller();
	}

}
