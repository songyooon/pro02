<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, java.text.*, kr.co.myshop.vo.*, kr.co.myshop.view.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>메인 페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="common.css">
<style>
.in_container { clear:both; width:1400px; margin:0 auto; }
.in_container:after { content:""; display:block; clear:both; }
.card-title { overflow:hidden; text-overflow:ellipsis; white-space:nowrap; }
.card-text {   overflow: hidden;  text-overflow: ellipsis;  display: -webkit-box;
  -webkit-line-clamp: 2;  -webkit-box-orient: vertical; }
#best, #new, #hot { margin-bottom:40px; }  
#best li, #new li, #hot li   { margin-bottom:24px; } 
.cate_title { padding-top:24px; padding-bottom:24px; padding-left:36px; } 

    a:link { color: rgb(0, 0, 0); text-decoration:none !important}	
    a:visited { color: #000; text-decoration:none !important}	
    a:hover { color: rgb(0, 0, 0); text-decoration:none !important}
    a:active { color: rgb(0, 0, 0); text-decoration:none !important}
</style>
</head>
<body>
<%
	GetIndexProductListCtrl indexPro = new GetIndexProductListCtrl();
	ArrayList<Product> bestPro = indexPro.bestProduct();
	ArrayList<Product> hotPro = indexPro.hotProduct();
	ArrayList<Product> newPro = indexPro.newProduct();
%>
<%@ include file="header.jsp" %>
<div class="container" id="content">
	<h2 class="cate_title">BEST</h2>
	<ul class="row" id="best" style="list-style: none;">
		<% for(int i=0;i<bestPro.size();i++) { 
			Product vo = bestPro.get(i);
		%>	
		<li class="col-xl-3 col-lg-4 col-md-6 col-sm-12">
			<div class="card" style="width: 18rem;">
			  <img src="upload/<%=vo.getProPic() %>" class="card-img-top" alt="<%=vo.getProName() %>">
			  <div class="card-body">
			    <h5 class="card-title"><%=vo.getProName() %></h5>
			    <p class="card-text"><%=vo.getProSpec() %></p>
			    <a href="<%=request.getContextPath() %>/GetProductDetailCtrl?proNo=<%=vo.getProNo() %>" class="btn btn-outline-dark">자세히 보기</a>
			  </div>
			</div>
		</li>
		<% } %>
	</ul>
	<br><hr><br>	
	<h2 class="cate_title">NEW</h2>
	<ul class="row" id="new" style="list-style: none;">
		<% for(int i=0;i<newPro.size();i++) { 
			Product vo = newPro.get(i);
		%>	
		<li class="col-xl-3 col-lg-4 col-md-6 col-sm-12">
			<div class="card" style="width: 18rem;">
			  <img src="upload/<%=vo.getProPic() %>" class="card-img-top" alt="<%=vo.getProName() %>">
			  <div class="card-body">
			    <h5 class="card-title"><%=vo.getProName() %></h5>
			    <p class="card-text"><%=vo.getProSpec() %></p>
			    <a href="<%=request.getContextPath() %>/GetProductDetailCtrl?proNo=<%=vo.getProNo() %>" class="btn btn-outline-dark">자세히 보기</a>
			  </div>
			</div>
		</li>
		<% } %>
	</ul>
	<br><hr><br>
	<h2 class="cate_title">HOT</h2>
	<ul class="row" id="hot" style="list-style: none;">
		<% for(int i=0;i<hotPro.size();i++) { 
			Product vo = hotPro.get(i);
		%>	
		<li class="col-xl-3 col-lg-4 col-md-6 col-sm-12">
			<div class="card" style="width: 18rem;">
			  <img src="upload/<%=vo.getProPic() %>" class="card-img-top" alt="<%=vo.getProName() %>">
			  <div class="card-body">
			    <h5 class="card-title"><%=vo.getProName() %></h5>
			    <p class="card-text"><%=vo.getProSpec() %></p>
			    <a href="<%=request.getContextPath() %>/GetProductDetailCtrl?proNo=<%=vo.getProNo() %>" class="btn btn-outline-dark">자세히 보기</a>
			  </div>
			</div>
		</li>
		<% } %>
	</ul>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>