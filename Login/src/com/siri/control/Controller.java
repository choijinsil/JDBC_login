package com.siri.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public Controller() {
		joinForm = new JoinForm();
		loginForm = new LoginForm();
		serviceForm = new ServiceForm();
		updateForm = new UpdateForm();

		loginForm.setVisible(true);
		joinForm.setVisible(true);
//		serviceForm.setVisible(true);
//		updateForm.setVisible(true);
		eventUp();
	}

	public void eventUp() {
		loginForm.bt_login.addActionListener(this);
		loginForm.bt_join.addActionListener(this);
		joinForm.bt_submit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == loginForm.bt_login) {

			boolean isOk = new MembershipDAO().findLogin(loginForm.tf_id.getText(),
					new String(loginForm.tf_pass.getPassword()));

			if (isOk) {
				loginForm.showMsg("로그인성공!");

			} else {
				loginForm.showMsg("로그인 실패");
			}
		} else if (ob == loginForm.bt_join) {
			loginForm.setVisible(false);
			joinForm.setVisible(true);

		} else if (ob == joinForm.bt_submit) {
			MembershipDAO dao = new MembershipDAO();
			System.out.println("회원가입완료버튼");
			MembershipVO vo = new MembershipVO();
			vo.setId(joinForm.tf_id.getText().toString());
			vo.setPass(joinForm.tf_pass.getText().toString());
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

			} else {
				joinForm.showMsg("회원가입 실패했습니다.");

			}
		}

	}

	public static void main(String[] args) {
		new Controller();
	}
}
