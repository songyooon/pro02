<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, kr.co.myshop.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>제품 입고</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="common.css">

<style>
.title { padding-top:36px; padding-bottom:20px; font-size:25px; text-align:center; }
.btn-group { padding-left:450px; }
.table { font-size:13px; }
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
<%
	Product vo = (Product) request.getAttribute("pro");
%>
<div class="content container" id="content">
	<h2 class="title">제품 입고</h2>
	<form name="frm1" id="frm1" action="<%=request.getContextPath() %>/ProductWearingCtrl" method="post">
		<table class="table">
			<tbody>
				<tr>
					<th>제품이미지</th>
					<td><img src="<%=request.getContextPath() %>/upload/<%=vo.getProPic() %>" alt="<%=vo.getProName() %>"></td>
				</tr>
				<tr>
					<th>제품 번호</th>
					<td><%=vo.getProNo() %><br>
						<input type="hidden" name="proNo" id="proNo" value="<%=vo.getProNo() %>">
					</td>
				</tr>
				<tr>
					<th>카테고리 번호</th>
					<td><%=vo.getCateNo() %></td>
				</tr>
				<tr>
					<th>제품명</th>
					<td><%=vo.getProName() %></td>
				</tr>
				<tr>
					<th><strong style="color:#000">입고할 수량</strong></th>
					<td>
						<input type="number" name="amount" id="amount" class="form-control" />
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn-group">
			<button type="submit" class="btn btn-outline-dark">제품 입고</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=request.getContextPath() %>/GetProductListCtrl" class="btn btn-outline-dark">목록</a>
		</div>
	</form>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>