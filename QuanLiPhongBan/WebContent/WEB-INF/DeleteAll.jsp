<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Xóa tất cả</title>
<jsp:include page="_bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h4>Xóa tất cả</h4>
			</div>
			<div class="panel-body">
				<p class="text text-lg text-danger">
					<strong>Cảnh báo</strong>: Thao tác này sẽ xóa hết tất cả mọi dữ
					liệu.
				</p>
			</div>
			<div class="list-group">
				<a class="list-group-item" href="DeleteAll?Confirmed"
				onClick="return confirm('Chắc chắn?')">
					<b>Xác nhận và xóa</b> : Sau khi xóa, tài khoản admin reset thành [admin/admin]
				</a>
			</div>
		</div>
	</div>
</body>
</html>