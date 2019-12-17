package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Staff;
import dao.StaffDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StaffDAO staffDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		staffDAO = new StaffDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("Logout") == null) {
			String userId = request.getParameter("UserId");
			String password = request.getParameter("Password");
			Staff staff = new Staff();
			staff.setId(userId);
			staff.setPassword(password);
			if (staffDAO.isExist(staff)) {
				request.getSession().setAttribute("UserId", userId);
			}
		} else {
			request.getSession().invalidate();
		}
		response.sendRedirect("HomePage.jsp");
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
