package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeptDAO;
import dao.StaffDAO;

/**
 * Servlet implementation class DeleteAll
 */
@WebServlet("/DeleteAll")
public class DeleteAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptDAO deptDAO;
	StaffDAO staffDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAll() {
		super();
		deptDAO = new DeptDAO();
		staffDAO = new StaffDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!"admin".equals((request.getSession().getAttribute("UserId")))) {
			response.sendRedirect("HomePage.jsp");
			return;
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("Confirmed") != null) {
			if(deptDAO.deleteAllDepts()) {
				request.getSession().invalidate();
				response.getWriter().append("Đã xóa hết phòng ban!");
			} else {
				response.getWriter().append("Thao tác thất bại!");
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/DeleteAll.jsp").forward(request, response);
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
