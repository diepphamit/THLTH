package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Dept;
import bean.Position;
import bean.Staff;
import dao.DeptDAO;
import dao.PositionDAO;
import dao.StaffDAO;

/**
 * Servlet implementation class UpdateStaff
 */
@WebServlet("/UpdateStaff")
public class UpdateStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptDAO deptDAO;
	PositionDAO positionDAO;
	StaffDAO staffDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateStaff() {
		super();
		deptDAO = new DeptDAO();
		positionDAO = new PositionDAO();
		staffDAO = new StaffDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("UserId") == null) {
			response.sendRedirect("HomePage.jsp");
			return;
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("Id");
		if (request.getParameter("changePassword") != null) {
			String oldPassword, newPassword, confirmedPassword;
			oldPassword = request.getParameter("oldPassword");
			newPassword = request.getParameter("newPassword");
			confirmedPassword = request.getParameter("confirmedPassword");
			String userId = request.getSession().getAttribute("UserId").toString();
			if (newPassword.equals(confirmedPassword) 
					&& staffDAO.changePassword(userId, oldPassword, newPassword)) {
				response.getWriter().append("Đổi MK thành công!<hr>");
			} else {
				response.getWriter().append("Đổi MK thất bại!<hr>");
			}
			return;
		}
		if (request.getParameter("doUpdate") == null) {
			Staff staff = staffDAO.getStaff(id);
			if (staff != null) {
				request.setAttribute("Staff", staff);
				System.out.print("> Lấy danh sách phòng ban "); // FIXME
				ArrayList<Dept> deptList = deptDAO.getAllDepts();
				System.out.print("> Lấy danh sách chức vụ "); // FIXME
				ArrayList<Position> posList = positionDAO.getAllPositions();
				request.setAttribute("DeptList", deptList);
				request.setAttribute("PosList", posList);
				request.getRequestDispatcher("WEB-INF/UpdateStaff.jsp").forward(request, response);
			} else {
				response.getWriter().append("NULL");
			}
		} else { // Do Update
			try {
				String name = request.getParameter("Name");
				int deptId, positionId;
				try {
					deptId = Integer.parseInt(request.getParameter("DeptId"));
					positionId = Integer.parseInt(request.getParameter("PositionId"));
				} catch(NullPointerException | NumberFormatException e) {deptId = 0; positionId=0;}
				String address = request.getParameter("Address");
				String phone = request.getParameter("Phone");
				Staff staff = new Staff();
				staff.setAddress(address);
				staff.setDept(new DeptDAO().getDept(deptId));
				staff.setPosition(new PositionDAO().getPosition(positionId));
				staff.setName(name);
				staff.setId(id);
				staff.setPhone(phone);
				if (this.staffDAO.updateStaff(staff)) {
					System.out.print("> Thành công! "); // FIXME
					response.sendRedirect("UpdateStaff?Id=" + id);
				} else {
					System.out.print("> Thất bại! "); // FIXME
					response.sendRedirect("UpdateStaff?Id=" + id);
				}
			} catch (NullPointerException e) {
				response.sendRedirect("UpdateStaff?Id=" + id);
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
