<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Trang quản lí phòng ban</title>
<jsp:include page="WEB-INF/_bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<div class="text text-warning"
					style="font-size: 90px; text-align: center; margin-top: 10px">
					<span class="glyphicon glyphicon-fire"></span>
				</div>
			</div>
			<div class="col-md-8" style="text-align: center;">
				<h2 class="text text-success">
					<strong>CHƯƠNG TRÌNH QUẢN LÍ PHÒNG BAN</strong>
				</h2>
				<h4 class="text text-info">
					TH Lập Trình Mạng - Bài số 3: <strong>JSP</strong> & <strong>Servlet</strong> & <strong>MVC</strong>
				</h4>
				<h3>SVTH: Nguyễn Bá Anh - 12T2</h3>
			</div>
			<div class="col-md-2">
				<div class="text text-primary"
					style="font-size: 90px; text-align: center; margin-top: 10px">
					<span class="glyphicon glyphicon-king"></span>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<div class="list-group">
				<a class="list-group-item" href="HomePage.jsp" target="_main">Trang chủ</a>
				<a class="list-group-item" href="ViewStaffList" target="_main">Xem nhân viên</a>
				<a class="list-group-item" href="ViewDeptList" target="_main">Xemphòng ban</a>
				<a class="list-group-item" href="Search"target="_main">Tìm kiếm</a> 
				<a class="list-group-item" href="DeleteAll" target="_main">Xóa tất cả</a>
				<a class="list-group-item" href="About.jsp" target="_main">Trợ giúp chương trình</a>
				</div>
			</div>
			<div class="col-md-8">
				<div class="">
					<iframe src="HomePage.jsp" name="_main"
						style="border: none; width: 100%; height: 550px;"> </iframe>
				</div>
			</div>
			<div class="col-md-2">
				<div class="panel panel-info">
					<div class="panel-heading">
						<strong>Quảng cáo</strong>
					</div>
					<div class="panel-body">Liên hệ 0123456789</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">
						<strong>Quảng cáo</strong>
					</div>
					<div class="panel-body">Liên hệ 0123456789</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">
						<strong>Quảng cáo</strong>
					</div>
					<div class="panel-body">Liên hệ 0123456789</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>