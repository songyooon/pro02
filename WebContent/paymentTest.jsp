<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 api 테스트</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<h2>결제 모듈 API</h2>
	<br>
	<div id="pay">
	<h4>
		<label><input type="checkbox" value="1000">1000원</label>
		<label><input type="checkbox" value="5000">5000원</label>
		<label><input type="checkbox" value="10000">10000원</label>
		<label><input type="checkbox" value="50000">50000원</label>
	</h4>
	<h3>
	총 결제 금액
	<span style="dispaly:inline-block; width:150px; text-align:right;"></span>
	원
	</h3>
	<button class="btn btn-info">결제하기</button>
	<p id="paymentResult"></p>
	</div>
	<hr>
	<script>
	//결제모듈 연동
	$(document).ready(function(){
		var totalPay=0;
		$("#pay input").change(function(){
			if($(this).is(":checked")){
				totalPay += (Number)($(this).val());
			}else{
				totalPay -= (Number)($(this).val());
			}
			$("#pay span").html(totalPay);
		});
		$("#pay button").click(function(){
			var price = $("#pay span").html();
			//상품명_현재시간
			var d = new Date();
			var date = d.getFullYear()+''+(d.getMonth()+1)+''+d.getDate()+''+d.getHours()+''+d.getMinutes()+''+d.getSeconds();
			IMP.init('imp31083748'); // 결제 API를 사용하기 위한 코드 입력!
			IMP.request_pay({		//결제 요청
				merchant_uid : '상품명_' + date, //상점 거래 ID
				name : '결제테스트',				// 결제 명
				amount : price,					// 결제금액
				buyer_email : 'xxxxxxx@naver.com', // 구매자 email
				buyer_name : '구매자',				// 구매자 이름
				buyer_tel : '010-1234-4567',		// 구매자 전화번호
				buyer_addr : '서울특별시 영등포구',		// 구매자 주소
				buyer_postcode : '123-456'			// 구매자 우편번호
			}, function(rsp){
				if(rsp.success){
					var msg = '결제가 완료 되었습니다.';
					var r1 = '고유 아이디 : ' +rsp.imp_uid;
					var r2 = '상점 거래 아이디 : ' +rsp.merchant_uid;
					var r3 = '결제 금액 : ' +rsp.paid_amount;
					var r4 = '카드 승인 번호 : '+rsp.apply_num;
					alert(msg);
					$("#paymentResult").html(r1+"<br>"+r2+"<br>"+r3+"<br>"+r4);
				}else{
					$("#paymentResult").html('결제실패<br>'+'에러내용 : ' +rsp.error_msg);
				}	
			});
		});
	});
	</script>
</body>
</html>