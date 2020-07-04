package com.simplilearn.model;

public class Employees {
	
	//properties
	protected int id;
	protected String name;
	protected int age;
	protected String email;
	protected float salary;
	protected String dept;
	
	// zero arg constructor
	public Employees(){}
	
	// constructor with all feilds
	public Employees(int id, String name, int age, String email, float salary, String dept) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.salary = salary;
		this.dept = dept;
	}
	
	// constructor without id
	public Employees(String name, int age, String email, float salary, String dept) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.salary = salary;
		this.dept = dept;
	}
	
	//setter and getter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
}
