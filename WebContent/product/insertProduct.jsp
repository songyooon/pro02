<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, kr.co.myshop.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>제품 등록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="common.css">
<style>
.title { padding-top:36px; padding-bottom:20px; font-size:25px; text-align:center; }
.btn-group { padding-left:450px; }
.table { font-size:13px; }
.form-control { font-size:13px; }
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
<%
	ArrayList<Category> cateList = (ArrayList<Category>) request.getAttribute("cateList");
%>
<div class="content container" id="content">
	<h2 class="title">제품 등록</h2>
	<form name="frm1" id="frm1" action="<%=request.getContextPath() %>/FileUpload" method="post" enctype="multipart/form-data" onsubmit="return imgCheck(this)">
		<table class="table">
			<tbody>
				<tr>
					<th>상품분류</th>
					<td>
						<select name="cateNo" id="cateNo" class="custom-select custom-select-lg mb-3">
							<% 
								for(int i=0;i<cateList.size();i++){
									Category cate = cateList.get(i);
							%>
							<option value="<%=cate.getCateNo() %>"><%=cate.getCateName() %></option>
							<%
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="proName" id="title" placeholder="상품명 입력" class="form-control" autofocus required /></td>
				</tr>
				<tr>
					<th>제품 설명</th>
					<td><textarea cols="80" rows="6" name="proSpec" id="proSpec" placeholder="제품 설명 입력" class="form-control" required></textarea></td>
				</tr>
				<tr>
					<th>제품 가격</th>
					<td><input type="text" name="oriPrice" id="oriPrice" placeholder="제품 가격 입력" class="form-control" required></td>
				</tr>
				<tr>
					<th>할인율</th>
					<td><input type="text" name="discountRate" id="discountRate" placeholder="할인율 입력" class="form-control" required></td>
				</tr>
				<tr>
					<th>제품 이미지1</th>
					<td>
						<input type="file" name="proPic" id="proPic" accept="image/*" class="form-control" required>
						<input type="hidden" name="img1" id="img1" />
					</td>
				</tr>
				<tr>
					<th>제품 이미지2</th>
					<td>
						<input type="file" name="proPic2" id="proPic2" accept="image/*" class="form-control">
						<input type="hidden" name="img2" id="img2" />
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn-group">
			<input type="submit" name="submit-btn" class="btn btn-outline-dark" value="제품 등록">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" name="reset-btn" class="btn btn-outline-dark" value="취소">&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=request.getContextPath() %>/GetProductListCtrl" class="btn btn-outline-dark">목록</a>
		</div>
	</form>
	<script>
	function imgCheck(f){
		var i1 = f.proPic.value;
		var i2 = f.proPic2.value;
		var ipos1 = i1.lastIndexOf("\\");
		var ipos2 = i2.lastIndexOf("\\");
		f.img1.value = i1.substr(ipos1+1);
		f.img2.value = i2.substr(ipos2+1);
		//f.img1.value = i1;
		//f.img2.value = i2;
	}
	</script>	
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>