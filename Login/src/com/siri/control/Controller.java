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
	// ���ۺ�� �α����� set visible
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
				loginForm.showMsg("�α��μ���!");

			} else {
				loginForm.showMsg("�α��� ����");
			}
		} else if (ob == loginForm.bt_join) {
			loginForm.setVisible(false);
			joinForm.setVisible(true);

		} else if (ob == joinForm.bt_submit) {
			MembershipDAO dao = new MembershipDAO();
			System.out.println("ȸ�����ԿϷ��ư");
			MembershipVO vo = new MembershipVO();
			vo.setId(joinForm.tf_id.getText().toString());
			vo.setPass(joinForm.tf_pass.getText().toString());
			vo.setName(joinForm.tf_name.getText().toString());
			vo.setSsn1(Integer.parseInt(joinForm.tf_ssn1.getText().toString()));
			vo.setSsn2(Integer.parseInt(new String(joinForm.tf_ssn2.getPassword())));
			// char�� String���� ��ȯ�ϴ� ���
			vo.setPhone(joinForm.tf_phone1.getText().toString() + "-" + joinForm.tf_phone2.getText().toString() // ������
																												// //
																												// ���̱�
					+ "-" + joinForm.tf_phone3.getText().toString());
			vo.setAddr(joinForm.tf_addr.getText().toString());
			vo.setJob(joinForm.cb_job.getSelectedItem().toString());
			System.out.println(joinForm.cb_job.getSelectedItem().toString());

			// vo�� dao create �� ������ �Ǵµ�... numberformat����

			if (dao.create(vo)) {
				joinForm.showMsg("ȸ������ �Ϸ��߽��ϴ�."); // new�� ��ü ������ �ϰ� �Ǹ� join�䰡 �ΰ� ������� �ȴ�.
				joinForm.setVisible(false);
				loginForm.setVisible(true);

			} else {
				joinForm.showMsg("ȸ������ �����߽��ϴ�.");

			}
		}

	}

	public static void main(String[] args) {
		new Controller();
	}
}
