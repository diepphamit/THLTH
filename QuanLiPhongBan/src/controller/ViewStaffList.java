package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Staff;
import dao.StaffDAO;

/**
 * Servlet implementation class ViewStaffList
 */
@WebServlet("/ViewStaffList")
public class ViewStaffList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StaffDAO staffDAO;
    public ViewStaffList() {
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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		System.out.print("\nViewStaffList: > Nhận yêu cầu "); //FIXME
		ArrayList<Staff> staffList = staffDAO.getAllStaffs();
		System.out.print("> Đã lấy danh sách nhân viên "); //FIXME
		request.setAttribute("StaffList", staffList);
		request.getRequestDispatcher("WEB-INF/ViewStaffList.jsp").include(request, response);
		request.getRequestDispatcher("WEB-INF/NewStaffButton.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

