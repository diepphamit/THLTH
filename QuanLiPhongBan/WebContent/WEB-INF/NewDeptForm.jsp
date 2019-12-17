<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="_bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-success">
			<div class="panel-body">
				<form class="form form-horizontal" method="get" action="InsertDept">
					<div class="col-xs-7 col-xs-offset-2">
						<input type="text" placeholder="Nhập tên phòng ban mới"
							value="" name="DeptName" class="form-control">
					</div>
					<div class="col-xs-3">
						<input type="hidden" name="doInsert"> <input type="submit"
							value="Thêm" class="btn btn-block btn-success">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>