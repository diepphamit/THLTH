<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bean.Staff"%>
<%@page import="java.util.ArrayList"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Staff> staffList = (ArrayList<Staff>) request.getAttribute("StaffList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Xem danh sách nhân</title>
<jsp:include page="_bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h4>Danh sách nhân viên</h4>
			</div>
			<%
				if (staffList == null || staffList.isEmpty()) {
					out.print("<div class=\"panel-body\">Danh sách nhân viên rỗng!</div>");
				} else {
			%>
			<script type="javascript">
				function setSource(id) {
					document.getElementById("ModalFrame").src = 'UpdateStaff?id='+id;
					document.getElementById("demo").innerHTML = 'UpdateStaff?id='+id;
					//document.getElementById("ModalFrame").setAttribute('src', source);
					//alert(source);
				}
				function confirm(name) {
					if(confirm("Bạn thực sự muốn xóa " + name + "?")) {
						return true;
					} else {
						return false;
					} 
				}
			</script>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>STT</th>
							<th>Mã NV</th>
							<th>Họ tên</th>
							<th>Phòng</th>
							<th>Chức vụ</th>
							<th>Số ĐT</th>
							<th>Địa chỉ</th>
							<th>Xóa?</th>
						</tr>
					</thead>
					<tbody>

						<%
							int index = 1;
							for (Staff s : staffList) {
						%>
						<tr>
							<td><%=index++%></td>
							<td>
								<% if (s.getId().equals("admin")) {
									if (!session.getAttribute("UserId").toString().equals("admin")) {
										out.print(s.getId());
									} else {
										%>
										<a 
									data-toggle="modal"	data-target="#updateModal"
									onClick="javascript:document.getElementById('ModalFrame').src='UpdateStaff?Id=<%=s.getId() %>'" 
								><%=s.getId()%></a>
										<%
									}
									} else {
								%>
								<a 
									data-toggle="modal"	data-target="#updateModal"
									onClick="javascript:document.getElementById('ModalFrame').src='UpdateStaff?Id=<%=s.getId() %>'" 
								><%=s.getId()%></a>
								<%}%>
							</td>
							<td><%=s.getName()%></td>
							<td>
								<%if (s.getDept().getDeptId()>0) {%>
								<a href="ViewDept?Id=<%=s.getDept().getDeptId() %>">
									<%=s.getDept().getDeptName()%>
								</a>
								<%} %>
							</td>
							<td><%=s.getPosition().getPositionId()==0?"":s.getPosition().getPositionName()%></td>
							<td><%=s.getPhone()%></td>
							<td><%=s.getAddress()%></td>
							<td>
								<%if (!s.getId().equals("admin")) {%>
								<a href="DeleteStaff?Id=<%=s.getId() %>"
								onClick="return confirm('Chắc chắn muốn xóa?')"
								class="btn btn-danger btn-xs">Xóa</a>
								<%} %>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
			<%
				}
			%>
		<!-- ------------------------ -->
		<div id="updateModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg" style="width: 96%; height: 300px;">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Cập nhật thông tin nhân viên</h4>
					</div>
					<div class="modal-body">
						<iframe id="ModalFrame" src="" style="border: none; width: 100%; height: 250px;"></iframe>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onFocus="location.reload();">Đóng</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>