package com.simplilearn.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.dao.EmployeeDAO;
import com.simplilearn.model.Employees;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/")
public class ControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private EmployeeDAO empDAO;

	public void init() {
		empDAO = new EmployeeDAO();
	}

	public ControllerServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String action = request.getServletPath();
		
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertEmployee(request, response);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "/delete":
			deleteEmployee(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			updateEmployee(request, response);
			break;
		default:
			try {
				listEmployee(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher =  request.getRequestDispatcher("employee-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String age = request.getParameter("age");
			String salary = request.getParameter("salary");
			String dept = request.getParameter("dept");
			System.out.println("--------------------");
			System.out.println(name +" "+email +" "+age);
			Employees emp = new Employees(name, Integer.parseInt(age), email, Float.parseFloat(salary), dept);
			
			empDAO.addEmployee(emp);
			response.sendRedirect("list");
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employees emp = new Employees();
		emp.setId(id);
		try {
			empDAO.deleteEmployee(emp);
			response.sendRedirect("list");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employees emp;
		try {
			emp = empDAO.getOneEmployee(id);
			RequestDispatcher dispatcher =   request.getRequestDispatcher("employee-form.jsp");
			request.setAttribute("employee", emp);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		float salary = Float.parseFloat(request.getParameter("salary"));
		String dept = request.getParameter("dept");
		Employees emp = new Employees(id, name, age, email, salary, dept);
		try {
			empDAO.updateEmployee(emp);
			response.sendRedirect("list");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Employees> emplist = empDAO.listALLEmployees();
		request.setAttribute("emplist", emplist);
		RequestDispatcher dispatcher =  request.getRequestDispatcher("employee-list.jsp");
		dispatcher.forward(request, response);
	}
}
