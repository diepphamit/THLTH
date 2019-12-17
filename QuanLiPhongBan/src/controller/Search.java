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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StaffDAO staffDAO;
	DeptDAO deptDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		staffDAO = new StaffDAO();
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
		String searchType = "", searchQuery = "";
		try {
			searchQuery = request.getParameter("searchQuery");
			searchType = request.getParameter("searchType");
		} catch (NullPointerException e) {
		}
		request.setAttribute("searchQuery", searchQuery);
		request.setAttribute("searchType", searchType);
		request.getRequestDispatcher("/WEB-INF/Search.jsp").include(request, response);
		if (searchQuery == null || "".equals(searchQuery)) {
			// jump!
		} else {
			if ("staff".equals(searchType)) {
				ArrayList<Staff> staffList = staffDAO.getStaffByKeyword(searchQuery);
				request.setAttribute("StaffList", staffList);
				request.getRequestDispatcher("WEB-INF/ViewStaffList.jsp").include(request, response);
			} else if ("dept".equals(searchType)) {
				ArrayList<Dept> deptList = deptDAO.getDeptByKeyword(searchQuery);
				request.setAttribute("DeptList", deptList);
				request.getRequestDispatcher("WEB-INF/ViewDeptList.jsp").include(request, response);
			}
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

