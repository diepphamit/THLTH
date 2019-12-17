package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Dept;
import bean.Staff;
import dao.DeptDAO;
import dao.StaffDAO;

/**
 * Servlet implementation class ViewDept
 */
@WebServlet("/ViewDept")
public class ViewDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptDAO deptDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewDept() {
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

		int id = Integer.parseInt(request.getParameter("Id"));
		Dept dept = deptDAO.getDept(id);
		if (dept != null) {
			// Lấy danh sách nhân viên của phòng ban đó.
			ArrayList<Staff> staffList = new StaffDAO().getStaffByDeptId(id);
			dept.setStaffNumber(staffList == null ? 0 : staffList.size());
			// Đẩy về thông tin phòng ban
			request.setAttribute("Dept", dept);
			request.getRequestDispatcher("/WEB-INF/ViewDept.jsp").include(request, response);
			request.setAttribute("StaffList", staffList);
			request.getRequestDispatcher("WEB-INF/ViewStaffList.jsp").include(request, response);
		} else {
			response.getWriter().append("NULL");
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
