<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, kr.co.myshop.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>제품 상세보기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="common.css">

<style>
.title { padding-top:36px; padding-bottom:20px; font-size:25px; text-align:center; }
.btn-group { padding-left:450px; }
.table { font-size:13px; }
.table tr td { max-width:400px; }
.table tr td img { max-width:400px; height:auto; }
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
<%
	Product vo = (Product) request.getAttribute("pro");
	if(sid!=null) {
		sid = sid;
	} else {
		sid = "guest";
	}
%>
<div class="container-fluid" id="content">
	<div class="row" id="content_row">
		<% if(sid!=null && sid.equals("admin")) { %>
		<%@ include file="../admin/admin_sidebar.jsp" %>
		<% } %>
		<% if(sid!=null && sid.equals("admin")) { %>
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
		<% } else { %>
		<main class="content container">
		<% } %>
			<h2 class="title">상세보기</h2>
			<table class="table">
				<tbody>
					<tr>
						<th>제품이미지</th>
						<td><img src="<%=request.getContextPath() %>/upload/<%=vo.getProPic() %>" alt="<%=vo.getProName() %>"></td>
					</tr>
					<tr>
						<th>제품 번호</th>
						<td><%=vo.getProNo() %></td>
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
						<th>제품 설명</th>
						<td><%=vo.getProSpec() %></td>
					</tr>
					<tr>
						<th>제품 가격</th>
						<td>
							판매가격 : <strong style="color:#000"><%=vo.getProPrice() %></strong> <br>
							세일 전 가격 : (<del><%=vo.getOriPrice() %></del>) 
						</td>
					</tr>
					<tr>
						<th>남은 수량</th>
						<td>
						<% if(vo.getAmount()!=0) { %>
							<%=vo.getAmount() %>
						<% } else { %>
						<strong style="color:#000">[품절]</strong>
						<% } %>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn-group">
				<a href="<%=request.getContextPath() %>/GetProductListCtrl" class="btn btn-outline-dark">목록</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<% if(sid.equals("admin")) { %>
				<a href="<%=request.getContextPath() %>/DeleteProductCtrl?proNo=<%=vo.getProNo() %>" class="btn btn-outline-dark">제품 삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%=request.getContextPath() %>/UpdateProductCtrl?proNo=<%=vo.getProNo() %>" class="btn btn-outline-dark">제품 정보 수정</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%=request.getContextPath() %>/GetProductWearingCtrl?proNo=<%=vo.getProNo() %>" class="btn btn-outline-dark">제품 입고</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<% } %>
				<% if(vo.getAmount()!=0 && sid!="guest") { %>
				<a href="<%=request.getContextPath() %>/GetSalesProductCtrl?proNo=<%=vo.getProNo() %>" class="btn btn-outline-dark">구매</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%=request.getContextPath() %>/InsertCartCtrl?proNo=<%=vo.getProNo() %>" class="btn btn-outline-dark">장바구니</a>
				<% } %>
			</div>
		</main>
	</div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>