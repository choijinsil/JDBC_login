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
	// ���ۺ�� �α����� set visible
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
		// �ߺ�Ȯ��
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
					// �ؽ�Ʈ �ٲٴ� �� forground
					joinForm.bt_checkid.setBackground(Color.RED);
				} else {
					joinForm.bt_checkid.setBackground(Color.GREEN);
				}
			}
		});

		// �ֹι�ȣ ��Ŀ��
		joinForm.tf_ssn1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (joinForm.tf_ssn1.getText().length() == 6) {
					joinForm.tf_ssn2.requestFocus();
				}
			}
		});

		// ��ȭ��ȣ �Է�
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

		// ������ ����
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

			// �α��ν� admin�̸� true, ����ڸ� false

			if (isOk) {
				if ("admin".equals(isAdmin)) {
					possDml = true;
				} else {
					possDml = false;
				}

				loginForm.showMsg("�α��μ���!");
				loginForm.setVisible(false);
				serviceForm.setVisible(true);
				serviceForm.displayTable(dao.findAll());

			} else {
				loginForm.showMsg("�α��� ����");
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
			System.out.println("ȸ�����ԿϷ��ư");
			MembershipVO vo = new MembershipVO();
			vo.setId(joinForm.tf_id.getText().toString());
			vo.setPass(new String(joinForm.tf_pass.getPassword()));
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
				joinForm.initText();

			} else {
				joinForm.showMsg("�Է°��� Ȯ���ϼ���!");

			}

		} else if (ob == serviceForm.bt_sel_all) {
			MembershipDAO dao = new MembershipDAO();
			serviceForm.displayTable(dao.findAll());

		} else if (ob == serviceForm.bt_up) {
			MembershipDAO dao = new MembershipDAO();
			String id = serviceForm.showInput("������ ���̵� �Է����ּ���!");
			MembershipVO vo = dao.findById(id);
			// ���� ���̵� ���� ���� �����ϰ� �ϱ�

			// �������϶��� ��� ���̵� ��������
			if (possDml == true) {

				vo = dao.findById(id);
				if (vo == null) {
					// DB�� ��ġ�ϴ� ���̵� �������� �ʴ´ٸ�
					serviceForm.showMsg("��ġ�ϴ� ���̵� �����ϴ�.");
					return;
				}

				if (id.trim() != null) {
					serviceForm.setVisible(false);
					updateForm.setVisible(true);
					updateForm.settings(vo);
				} else {
					serviceForm.showMsg("������ ���̵� �Է����ּ���!");
				}
			} else {
				// �����ڰ� �ƴҶ�
				String isAdmin = new MembershipDAO().findAdmin(loginForm.tf_id.getText(),
						new String(loginForm.tf_pass.getPassword()));
				if (id.equals(isAdmin)) {
					serviceForm.setVisible(false);
					updateForm.setVisible(true);
					updateForm.settings(vo);
				} else {
					serviceForm.showMsg("������ ������ ���ּ���");
				}
			}
		} else if (ob == updateForm.bt_submit) {
			// �ؽ�Ʈ�� �����ͼ� vo�����
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
				// ����â���� ���� â���� �̵�
				updateForm.setVisible(false);
				serviceForm.setVisible(true);
				serviceForm.displayTable(dao.findAll());

			} else {
				updateForm.showMsg("�Էµ� �����͸� Ȯ���ϼ���!");
			}

		} else if (ob == serviceForm.bt_del) {
			MembershipDAO dao = new MembershipDAO();
			String id = serviceForm.showInput("������ ���̵� �Է����ּ���.");
			// �����ڰ� true���
			if (possDml == true) {
				if (dao.remove(id)) {
					serviceForm.displayTable(dao.findAll());
				} else {

					serviceForm.showMsg("������ ���̵� Ȯ�����ּ���.");
				}
				// ������ false���
			} else {
				String isAdmin = new MembershipDAO().findAdmin(loginForm.tf_id.getText(),
						new String(loginForm.tf_pass.getPassword()));

				if (id.equals(isAdmin)) {
					dao.remove(id);
					serviceForm.displayTable(dao.findAll());
					serviceForm.setVisible(false);
					loginForm.setVisible(true);
				} else {
					serviceForm.showMsg("������ ������ ���ּ���");
				}
			}
		} else if (ob == serviceForm.bt_sel_name) {
			// �̸� �˻��ϱ�
			MembershipDAO dao = new MembershipDAO();
			// String name = serviceForm.showInput("�˻��� �̸��� �Է����ּ���.");
			serviceForm.displayTable(dao.findSearch(serviceForm.optionMsg()));
			// serviceForm.displayTable(dao.findByName(name));

		} else if (ob == serviceForm.bt_exit) {
			System.exit(0);
		} else if (ob == joinForm.bt_checkid) {

		} else if (ob == serviceForm.item_confirm) {
//			if ("����".equals(serviceForm.item_confirm.getText())) {
			String isAdmin = new MembershipDAO().findAdmin(loginForm.tf_id.getText(),
					new String(loginForm.tf_pass.getPassword()));

			if ("admin".equals(isAdmin)) {
				// ������ ������ �Ǿ����� true
				possDml = true;
				serviceForm.showMsg("�̹������Ǿ����ϴ�!");
			} else {

				// YES_OPTION�� 0, NO_OPTION�� 1, CLOSED_OPTION�� -1�� ��ȯ�Ѵ�
				int y = serviceForm.showConfirm("�����ڷ� �α��� �ұ��?");
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
