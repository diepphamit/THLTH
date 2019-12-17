package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StaffDAO;

/**
 * Servlet implementation class DeleteStaff
 */
@WebServlet("/DeleteStaff")
public class DeleteStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       StaffDAO staffDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStaff() {
        super();
        staffDAO = new StaffDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("UserId")==null) {
			response.sendRedirect("HomePage.jsp");
			return;
		}
		String id = request.getParameter("Id");
		if (id == null) {
			response.sendRedirect("ViewStaffList");
		} else if (staffDAO.deleteStaff(id)) {
			response.sendRedirect("ViewStaffList");
		} else {
			response.getWriter().append("Xoá thất bại!");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
