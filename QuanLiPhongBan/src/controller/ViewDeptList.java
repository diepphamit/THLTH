package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Dept;
import dao.DeptDAO;

/**
 * Servlet implementation class ViewDeptList
 */
@WebServlet("/ViewDeptList")
public class ViewDeptList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptDAO deptDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDeptList() {
        super();
        deptDAO = new DeptDAO();
        // TODO Auto-generated constructor stub
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
		ArrayList<Dept> deptList = deptDAO.getAllDepts();
		request.setAttribute("DeptList", deptList);
		request.getRequestDispatcher("/WEB-INF/ViewDeptList.jsp").include(request, response);
		request.getRequestDispatcher("/WEB-INF/NewDeptForm.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
