package com.teacher.model.dto;

public class Person {
   //�Ӽ�����
	private int no;
	private String name;
	private int age;
	private String job;
	
    public Person() {
		
	}

	public Person(int no, String name, int age, String job) {
		this.no = no;
		this.name = name;
		this.age = age;
		this.job = job;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Person [no=" + no + ", name=" + name + ", age=" + age + ", job=" + job + "]";
	}
	
//	@Override
//	public String toString() {		
//		return no+": ["+name+","+age+","+job+"]";
//	}

	
    
}//class Person








