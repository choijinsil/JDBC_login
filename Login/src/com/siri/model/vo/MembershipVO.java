package com.siri.model.vo;

public class MembershipVO {

	private String id;
	private String pass;
	private String name;
	private int ssn1;
	private int ssn2;
	private String phone;
	private String addr;
	private String job;

	public MembershipVO() {

	}

	public MembershipVO(String id, String pass, String name, int ssn1, int ssn2, String phone, String addr,
			String job) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.ssn1 = ssn1;
		this.ssn2 = ssn2;
		this.phone = phone;
		this.addr = addr;
		this.job = job;
	}
	
	public MembershipVO(String id, String name, int ssn1, int ssn2, String phone, String addr,
			String job) {
		super();
		this.id = id;
		this.name = name;
		this.ssn1 = ssn1;
		this.ssn2 = ssn2;
		this.phone = phone;
		this.addr = addr;
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSsn1() {
		return ssn1;
	}

	public void setSsn1(int ssn1) {
		this.ssn1 = ssn1;
	}

	public int getSsn2() {
		return ssn2;
	}

	public void setSsn2(int ssn2) {
		this.ssn2 = ssn2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "MembershipVO [id=" + id + ", pass=" + pass + ", name=" + name + ", ssn1=" + ssn1 + ", ssn2=" + ssn2
				+ ", phone=" + phone + ", addr=" + addr + ", job=" + job + "]";
	}

}
