<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bean.Dept"%>
<%@page import="java.util.ArrayList"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Dept> deptList = (ArrayList<Dept>) request.getAttribute("DeptList");
	Dept failedDept = (Dept) request.getAttribute("Dept");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Xem danh sách phòng ban</title>
<jsp:include page="_bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h4>Danh sách phòng ban</h4>
			</div>

			<%
				if (deptList == null || deptList.isEmpty()) {
					out.print("<div class=\"panel-body\">Danh sách phòng ban rỗng!</div>");
				} else {
			%>
			<table class="table ">
				<thead>
					<tr>
						<th>Mã PB</th>
						<th>Tên PB</th>
						<th>Xóa?</th>
					</tr>
				</thead>
				<tbody>
					<%
						/*int i =10; while (i-->0)*/ for (Dept d : deptList) {
					%>
					<tr>
						<td><%=d.getDeptId()%></td>
						<td><a href="ViewDept?Id=<%=d.getDeptId()%>"> <%=d.getDeptName()%>
						</a></td>
						<td><a href="DeleteDept?id=<%=d.getDeptId()%>"
							onClick="return confirm('Chắc chắn muốn xóa?')"
							class="btn btn-danger btn-xs btn-block">Xóa</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>