<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bean.Dept"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
	Dept dept = (Dept) request.getAttribute("Dept");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Xem phòng ban</title>
<jsp:include page="_bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h4>Thông tin phòng ban</h4>
			</div>
			<%
				if (dept == null) {
					out.print("NULL");
				} else {
			%>
			<table class="table">
				<tr>
					<th>Mã phòng ban</th>
					<th>Tên phòng ban</th>
					<th>Số nhân viên</th>
					<th>Ngày tạo</th>
				</tr>
				<tr>
					<td><%=dept.getDeptId()%></td>
					<td><a data-toggle="modal" 
						data-target="#updateNameModal"><%=dept.getDeptName()%></a>
					</td>
					<td><%=dept.getStaffNumber()%></td>
					<td><%=(new SimpleDateFormat("dd-MM-yyyy")
							.format(new Date(dept.getCreatedTime().getTime())))%></td>
				</tr>
			</table>
			<div id="updateNameModal" class="modal fade" role="dialog">
				<div class="modal-dialog modal-lg"
					style="width: 96%; height: 300px;">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Sửa tên phòng ban</h4>
						</div>
						<div class="modal-body">
							<div class="container">
								<form action="UpdateDept" method="get"
									class="form form-horizontal">
									<div class="form-group">
										<label class="col-sm-3 control-label">Mã phòng ban</label>
										<div class="col-sm-9">
											<input class="form-control" name="Id"
												value="<%=dept.getDeptId()%>" readonly>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label">Tên phòng ban</label>
										<div class="col-sm-9">
											<input class="form-control" name="DeptName"
												value="<%=dept.getDeptName()%>">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-6 col-sm-offset-3">
											<input class="btn btn-success btn-block" value="Cập nhật">
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" onFocus="location.reload();">Đóng</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>