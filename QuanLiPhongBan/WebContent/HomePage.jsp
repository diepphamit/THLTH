<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Trang chủ</title>
<jsp:include page="WEB-INF/_bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-success">
			<div class="panel-body">
				<%
					if (session.getAttribute("UserId") == null) {
				%>
				Chào bạn, xin đăng nhập trước khi thao tác.
				<hr>
				<form action="Login" class="form-horizontal" method="post">
					<div class="form-group">
						<label class="col-xs-4 control-label">Tài khoản</label>
						<div class="col-xs-6">
							<input type="text" class="form-control" name="UserId">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-4 control-label">Mật khẩu</label>
						<div class="col-xs-6">
							<input type="password" class="form-control" name="Password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-3 col-xs-offset-4">
							<input type="submit" class="btn btn-block btn-success"
								value="Đăng nhập">
						</div>
						<div class="col-xs-3">
							<input type="reset" class="btn btn-block btn-warning" value="Xóa">
						</div>
					</div>
				</form>
				<%
					} else {
				%>
				<h3>Chào <b><%=session.getAttribute("UserId")%></b>
				<a style="float:right!important" class="btn btn-danger" href="Login?Logout">Thoát</a></h3>
					Các chức năng đã sẵn sàng sửa dụng.<hr>
				<h4>Đổi mật khẩu</h4>
					<form class="form form-horizontal" role="form" action="UpdateStaff"
						method="post">
						<input type="hidden" name="changePassword">
						<div class="form-group">
							<label class="control-label col-xs-4">Mật khẩu cũ</label>
							<div class="col-xs-6">
								<input name="oldPassword" type="Password" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-4">Mật khẩu mới</label>
							<div class="col-xs-6">
								<input name="newPassword" type="password" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-4">Xác nhận mật khẩu</label>
							<div class="col-xs-6">
								<input name="confirmedPassword" type="password"
									class="form-control">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-4 col-xs-4">
								<input name="" type="submit" class="btn btn-success btn-block"
									value="Đổi mật khẩu">
							</div>
						</div>
					</form>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>