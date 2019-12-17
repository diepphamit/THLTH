package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeptDAO;

/**
 * Servlet implementation class DeleteDept
 */
@WebServlet("/DeleteDept")
public class DeleteDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptDAO deptDAO;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteDept() {
		super();
		deptDAO = new DeptDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("UserId")==null) {
			response.sendRedirect("HomePage.jsp");
			return;
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {}
		if (deptDAO.deleteDept(id)) {
			response.sendRedirect("ViewDeptList");
		} else {
			response.getWriter().append("Xoá thất bại!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
