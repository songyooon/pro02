<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, kr.co.myshop.util.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 박스 오피스 테스트</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
</head>
<body>
	<h2>영화 박스 오피스 </h2>
	<div id='movieList'>
		<button id='movieBtn' class="btn btn-primary">순위확인</button>
		<div id="movieResultWrap">
			<ul id="movieResult">
			</ul>
		</div>
	</div>
	<script>
	$(document).ready(function(){
		//4. 박스오피스 순위
		 $("#movieBtn").click(function(){
			$("#movieResult").empty(); 
			$.ajax({
				url : "<%=request.getContextPath() %>/SearchMovieServlet",
				type : "get",
				success : function(data){
					for(var index in data){
						$("#movieResult").append("<li></li>");
						$("#movieResult li:last-child").append("<h3><strong>순위 : " + data[index].rank+"</strong></h3>");
						$("#movieResult li:last-child").append("<p>영화제목 : " + data[index].movieNm+"</p><hr>");
					}
					console.log(data);
				},
				error : function(){
					console.log("실패");
				}
			});
		}); 
	});
	</script>
</body>
</html>