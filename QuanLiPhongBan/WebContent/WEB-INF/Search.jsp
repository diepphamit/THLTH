<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String searchType = "", searchQuery = "";
	try {
		searchQuery = (String) request.getAttribute("searchQuery");
		searchType = (String) request.getAttribute("searchType");
	} catch (NullPointerException e) {
		searchQuery = "";
		searchType = "";		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tìm kiếm</title>
<jsp:include page="_bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4>Tìm kiếm</h4>
			</div>
			<div class="panel-body">
				<form method="get" action="#" class="form">
					<div class="col-xs-3">
						<div class="form-group">
							<select name="searchType" class="form-control">
								<option value="staff">Nhân viên</option>
								<option value="dept" <%="dept".equals(searchType)?"selected":"" %>>Phòng ban</option>
							</select>
						</div>
					</div>
					<div class="col-xs-9">
						<div class="input-group">
							<input type="text" name="searchQuery" value="<%=searchQuery!=null?searchQuery:"" %>" class="form-control">
							<span class="input-group-btn"> <input type="submit"
								value="Tìm kiếm" class="btn btn-success">
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>