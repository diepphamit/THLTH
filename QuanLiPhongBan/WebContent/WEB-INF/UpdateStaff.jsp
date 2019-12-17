<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Staff"%>
<%@page import="bean.Position"%>
<%@page import="bean.Dept"%>
<%
	request.setCharacterEncoding("UTF-8");
	@SuppressWarnings("unchecked")
	ArrayList<Position> posList = (ArrayList<Position>) request.getAttribute("PosList");
	@SuppressWarnings("unchecked")
	ArrayList<Dept> deptList = (ArrayList<Dept>) request.getAttribute("DeptList");
	Staff staff = (Staff) request.getAttribute("Staff");
	String id = "", password = "", name = "", address = "", phone = "";
	int deptId = 0, positionId = 0;
	if (staff != null) {
		id = staff.getId();
		password = staff.getPassword();
		name = staff.getName();
		deptId = staff.getDept().getDeptId();
		positionId = staff.getPosition().getPositionId();
		address = staff.getAddress();
		phone = staff.getPhone();
	}
	String message = (String) request.getAttribute("message");
	boolean isAdmin = "admin".equals(staff.getId());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Cập nhật Nhân Viên</title>
<jsp:include page="_bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
				<form class="form form-horizontal" action="#" method="post" accept-charset="utf-8">
					<div class="form-group">
						<label class="col-xs-2 control-label">Tài khoản</label>
						<div class="col-xs-4">
							<input class="form-control" name="Id" value="<%=id%>" type="text"
								readonly>
						</div>
						<label class="col-xs-2 control-label">Họ tên</label>
						<div class="col-xs-4">
							<input class="form-control" name="Name" value="<%=name%>" type="text"
								required>
						</div>
					</div>
				
					<div class="form-group">
						<label class="col-xs-2 control-label">Phòng ban</label>
						<div class="col-xs-4">
							<select name="DeptId" class="form-control" <%=isAdmin?"disabled":"" %>>
								<%
									for (Dept d : deptList) {
								%>
								<option <%=d.getDeptId() == deptId ? "selected" : ""%>
									value="<%=d.getDeptId()%>"><%=d.getDeptName()%></option>
								<%
									}
								%>
							</select>
						</div>
						<label class="col-xs-2 control-label">Chức
							vụ</label>
						<div class="col-xs-4">
							<select name="PositionId" class="form-control" <%=isAdmin?"disabled":"" %>>
								<%
									for (Position p : posList) {
								%>
								<option <%=p.getPositionId() == positionId ? "selected" : ""%>
									value="<%=p.getPositionId()%>"><%=p.getPositionName()%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label">Địa chỉ</label>
						<div class="col-xs-10">
							<input class="form-control" name="Address" value="<%=address%>"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-2 control-label">Số ĐT</label>
						<div class="col-xs-10">
							<input class="form-control" name="Phone" value="<%=phone%>"
								type="text">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-5 col-xs-offset-4">
							<input class="btn btn-success btn-block" value="Cập nhật"
								type="submit"> <input type="hidden" name="doUpdate">
						</div>
					</div>
				</form>
			<%
				if (message != null) {
			%>
				<div class="panel-footer">
					<strong class="text text-danger"><%=message %></strong>
				</div>
			<%
				}
			%>
	</div>
</body>
</html>