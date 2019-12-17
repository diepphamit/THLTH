package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Dept;
import dao.DeptDAO;

/**
 * Servlet implementation class InsertDept
 */
@WebServlet("/InsertDept")
public class InsertDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DeptDAO deptDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDept() {
        super();
        deptDAO = new DeptDAO();
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
		request.setCharacterEncoding("UTF-8");
		System.out.print("\nInsertDept: ");
		if (request.getParameter("doInsert") != null) {
			System.out.print("> Nhận form "); //FIXME
			String deptName = "";
			try {
				deptName = request.getParameter("DeptName");
				System.out.print("> Đã lấy dữ liệu form "); //FIXME
			} catch (NumberFormatException e) {
			} catch (NullPointerException e) {}
			Dept dept = new Dept();
			dept.setDeptName(deptName);
			System.out.print("> Đã đóng gói dữ liệu "); //FIXME
			if (deptDAO.insertDept(dept)) {
				System.out.print("> Thêm thành công. "); //FIXME
				response.sendRedirect("ViewDeptList");
			} else {
				System.out.print("> Thêm thất bại. "); //FIXME
				response.getWriter().append("Thêm thất bại!");
			}
		} else {
			response.sendRedirect("ViewDeptList");
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
