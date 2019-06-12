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
	// �̼�~!
	// ��ȿ�� �˻� - �󰪿� ���� üũ

	public Controller() {
		// ��ü ���α׷��� ���� �帧 ����
		// 1. ����� ��û �м� ex.� ��ư�� ��������
		// 2. ����� �Է� ������ ������ ex. getText
		// 3. ** �𵨰�ü ���� ex. PersonDAO (����� ���, ����, �Ǵ�)
		// 4. ������(��) �̵� ex.���� -> �Է���

		// ���û��� - ��ȿ�� �˻� -> ����ڰ� �Է��� �����Ϳ� ����!

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
			// 4. ���κ� -> �Է������� �̵�
			mainView.setVisible(false);
			form.setVisible(true);
		} else if (ob == form.bt_submit) { // db�Է¿�û
			// ��ȿ�� �˻� ���� - ���ڰ� - null��

			// 2. ����� �Է� ������ ������
			String name = form.tf_name.getText().trim();
			String age = form.tf_age.getText().trim();
			String job = form.tf_job.getText().trim();

			// DTO�� ����
			Person p = new Person(0, name, Integer.parseInt(age), job);

			// 5. ������ ��ȿ�� �˻�
			if (name.equals("")) {
				form.showMsg("�̸��Է�!");
				return;
			} else if (age.length() == 0) {
				form.showMsg("�����Է�!");
				return;
			} else if (age.matches("[\\d]{1,3}")) {
				form.showMsg("���ڸ��Է�!");
				return;
			} else if (job.length() < 1) {
				form.showMsg("�����Է�!");
				return;
			}
			// 3. �� ��ü ����
			PersonDAO dao = new PersonDAO();
			boolean isOk = dao.insert(p);

			// mainview�� �����ϱ�
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

			if (row == -1) { // ���õ� ���� ���ٸ� ��ġ�ڴ�.
				mainView.showMsg("������ ���� ���� !");
				return;
			}
			Object data = mainView.table.getValueAt(row, 0);// ������ ��ȣ ���
			int no = Integer.parseInt(data.toString());

			upForm.upNo = no;
			mainView.setVisible(false);
			Person p = dao.select(no);
			upForm.tf_name.setText(p.getName());
			upForm.tf_age.setText(p.getAge() + "");
			upForm.tf_job.setText(p.getJob());
			upForm.setVisible(true);

		} else if (ob == upForm.bt_submit) { // ������ư Ŭ����
			System.out.println("���� ��ư ���Դ�?");
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

			int msg = Integer.parseInt(mainView.showMsgDel("�����Ͻ� ��ȣ�� �����ּ���."));
			
			
			if (dao.delete(msg)) {
				mainView.displayTable(dao.selectAll());
				mainView.showMsg(msg + "�� ���� �Ǿ����ϴ�.");
			} else {
				mainView.showMsg("���� ��ȣ�Դϴ�.");
			}

		} else if (ob == mainView.bt_exit) {

		}
	}

}
