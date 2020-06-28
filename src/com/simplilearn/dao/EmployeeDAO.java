package com.simplilearn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.simplilearn.model.Employees;

/**
 * 
 * EmployeeDAO for All CRUD Operation
 *
 */
public class EmployeeDAO {
	Connection conn = null;

	// create employee
	public boolean addEmployee(Employees emp) throws SQLException {
		// 1. create connection
		conn = DatabseConnection.initConnection();
		// create insert
		String INSERT_EMPLOYEE = "insert into employees (name,age,email,salary,dept) " + "values (?,?,?,?,?)";
		// create prepare statement
		PreparedStatement pstm = conn.prepareCall(INSERT_EMPLOYEE);
		pstm.setString(1, emp.getName());
		pstm.setInt(2, emp.getAge());
		pstm.setString(3, emp.getEmail());
		pstm.setFloat(4, emp.getSalary());
		pstm.setString(5, emp.getDept());

		// execute query
		int rowAffected = pstm.executeUpdate();
		pstm.close();
		conn.close();
		return (rowAffected > 0) ? true : false;

	}

	// list all employee
	public List<Employees> listALLEmployees() throws SQLException {
		List<Employees> emplist = new ArrayList<Employees>();
		// 1. create connection
		conn = DatabseConnection.initConnection();
		// create insert
		String READ_EMPLOYEES = "select * from  employees";
		// create statement
		Statement stm = conn.createStatement();

		// execute query
		ResultSet rst = stm.executeQuery(READ_EMPLOYEES);
		while (rst.next()) {
			int id = rst.getInt("id");
			String name = rst.getString("name");
			float salary = rst.getFloat("salary");
			String email = rst.getString("email");
			String dept = rst.getString("dept");
			int age = rst.getInt("age");

			Employees emp = new Employees(id, name, age, email, salary, dept);
			emplist.add(emp);
		}
		stm.close();
		conn.close();
		return emplist;
	}

	// delete Employee
	public boolean deleteEmployee(Employees emp) throws SQLException {
		// 1. create connection
		conn = DatabseConnection.initConnection();
		// delete employee
		String DELETE_EMPLOYEES = "delete from  employees where id=?";

		PreparedStatement pstm = conn.prepareStatement(DELETE_EMPLOYEES);
		pstm.setInt(1, emp.getId());

		// execute query
		int rowAffected = pstm.executeUpdate();

		pstm.close();
		conn.close();
		return (rowAffected > 0) ? true : false;

	}

	// update Employee
	public boolean updateEmployee(Employees emp) throws SQLException {
		// 1. create connection
		conn = DatabseConnection.initConnection();
		// delete employee
		String UPDATE_EMPLOYEES = "update employees set name=?, age=?, email=?, salary=?, dept=? where id=?";

		PreparedStatement pstm = conn.prepareStatement(UPDATE_EMPLOYEES);
		pstm.setString(1, emp.getName());
		pstm.setInt(2, emp.getAge());
		pstm.setString(3, emp.getEmail());
		pstm.setFloat(4, emp.getSalary());
		pstm.setString(5, emp.getDept());
		pstm.setInt(6, emp.getId());
		int rowAffected = pstm.executeUpdate();
		pstm.close();
		conn.close();
		return (rowAffected > 0) ? true : false;

	}

	// get one Employee
	public Employees getOneEmployee(int id) throws SQLException {
		Employees emp = null;
		// 1. create connection
		conn = DatabseConnection.initConnection();
		// create read one emp
		String READ_EMPLOYEES = "select * from  employees where id=?";
		// create statement
		PreparedStatement pstm = conn.prepareStatement(READ_EMPLOYEES);
		pstm.setInt(1, id);
		// execute query
		ResultSet rst = pstm.executeQuery();
		while (rst.next()) {
			int empid = rst.getInt("id");
			String name = rst.getString("name");
			float salary = rst.getFloat("salary");
			String email = rst.getString("email");
			String dept = rst.getString("dept");
			int age = rst.getInt("age");

			emp = new Employees(empid, name, age, email, salary, dept);
		}
		pstm.close();
		conn.close();
		return emp;
	}
}
