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
 * Servlet implementation class InsertStaff
 */
@WebServlet("/InsertStaff")
public class InsertStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptDAO deptDAO;
	PositionDAO positionDAO;
	StaffDAO staffDAO;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertStaff() {
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
		if(request.getSession().getAttribute("UserId")==null) {
			response.sendRedirect("HomePage.jsp");
			return;
		}
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.print("\nInsertStaff: ");
		if (request.getParameter("doInsert") != null) {
			System.out.print("> Nhận form "); //FIXME
			String id = "", password = "", name = "", address = "", phone = "";
			int deptId = 0, positionId = 0;
			try {
				id = request.getParameter("Id");
				password = request.getParameter("Password");
				name = request.getParameter("Name");
				phone = request.getParameter("Phone");
				address = request.getParameter("Address");
				deptId = Integer.parseInt(request.getParameter("DeptId"));
				positionId = Integer.parseInt(request.getParameter("PositionId"));
				System.out.print("> Đã lấy dữ liệu form "); //FIXME
			} catch (NumberFormatException e) {
			} catch (NullPointerException e) {}
			Staff staff = new Staff();
			staff.setId(id);
			staff.setPassword(password);
			staff.setName(name);
			staff.setAddress(address);
			staff.setPhone(phone);
			staff.setDept(deptDAO.getDept(deptId));
			staff.setPosition(positionDAO.getPosition(positionId));
			System.out.print("> Đã đóng gói dữ liệu "); //FIXME
			if (staffDAO.insertStaff(staff)) {
				System.out.print("> Thêm thành công. "); //FIXME
				response.sendRedirect("UpdateStaff?Id="+id);
			} else {
				System.out.print("> Thêm thất bại. "); //FIXME
				request.setAttribute("message", "Thêm nhân viên thất bại! Xin kiểm tra lại thông tin");
				request.setAttribute("Staff", staff);
				System.err.print("> Trả ngược dữ liệu cũ vừa nhập "); //FIXME
			}
		} else {
			System.out.print("> Gọi giao diện form "); //FIXME
		}
		System.out.print("> Lấy danh sách phòng ban "); //FIXME
		ArrayList<Dept> deptList = deptDAO.getAllDepts();
		System.out.print("> Lấy danh sách chức vụ "); //FIXME
		ArrayList<Position> posList = positionDAO.getAllPositions();
		request.setAttribute("DeptList", deptList);
		request.setAttribute("PosList", posList);
		System.out.println(">> Trang thêm nhân viên "); //FIXME
		request.getRequestDispatcher("WEB-INF/InsertStaff.jsp").include(request, response);
	}

}
