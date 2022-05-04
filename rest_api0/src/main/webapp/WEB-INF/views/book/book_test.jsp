<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
		<div class="container">
			<h3>도서정보</h3>
			<button type="button" id="all" class="btn btn-secondary">도서 목록</button>
			<button type="button" id="get" class="btn btn-primary">특정 도서 정보</button>
			<button type="button" id="delete" class="btn btn-danger">도서 정보 삭제</button>
			<button type="button" id="update" class="btn btn-success">도서 정보 수정</button>

			<div id="result"> 
				<table class="table"></table>
			</div>
			
			<div>
				<form action="" method="post">
					<div>
						<label >코드</label>
						<input type="text" name="code" id="code" />
					</div>
					<div>
						<label >제목</label>
						<input type="text" name="title" id="title" />
					</div>
					<div>
						<label >저자</label>
						<input type="text" name="writer" id="writer" />
					</div>
					<div>
						<label >가격</label>
						<input type="text" name="price" id="price" />
					</div>
					<div>
						<button type="button" id="insert">입력</button>
					</div>															
				</form>
			</div>
			
		</div>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
$(function(){
	$("#all").click(function(){
		//ajax 방식으로 데이터 요청
		//아래가 ajax의 기본 형태
		/* $.ajax({
			url:'',
			method:'',
			data:{
				
			},
			dataType:'json',
			success:function(){
				//서버의 응답 코드 (HTTP 상태코드) 200일 때,
			}
		}) */

		//가져올 데이터가 json 이라면
		$.getJSON({
			url:'list',
			success:function(data){ // 데이터가 success가 되야 올 수 있음
				console.log(data); 
				
				//본문 table 영역 변경하기
			 	let result = $("#result table"); // result의 table 영역 가져와서
				let str = "";
			 	$.each(data, function(idx, item){
			 		str +="<tr>"
			 		str +="<td>"+item.code +"</td>";
			 		str +="<td>"+item.title +"</td>";
			 		str +="<td>"+item.writer +"</td>";
			 		str +="<td>"+item.price +"</td>";
			 		str += "</tr>";
			 	})
			 	result.html(str);
			} // 함수를 쓰고 싶으면 호출을 해야하는데 위 방식은 호출을 안했음.. 이렇게 호출 없이 특정 이벤트가 일어나면 불려지는 형태의 함수는 콜백 함수라고 함
		})
	}) // all 종료 (script 정리 잘해야 함.. 찾기 힘듬)
	
	// /book/1000 + get => 1000 번 도서에 대한 정보를 가져오겠다 ) 라는 식으로 만들 예정
	$("#get").click(function(){
		$.getJSON({ //get방식으로, JSON으로 받겠다
			url : '1000',
			success:function(item){
				let result = $("#result table"); 
				let str = "";
				
				str +="<tr>"
		 		str +="<td>"+item.code +"</td>";
		 		str +="<td>"+item.title +"</td>";
		 		str +="<td>"+item.writer +"</td>";
		 		str +="<td>"+item.price +"</td>";
		 		str += "</tr>";
				
		 		result.html(str);
			}	
		})
	}) // get 종료
	
	$("#delete").click(function(){
		$.ajax({
			url:'3000',
			type:'delete', //@DeleteMapping 연결
			success:function(data) {
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
		})
	}) // delete 종료
	
	$("#update").click(function(){
		
		let param = {price:52500}
		
		// 데이터를 보낼 때, dafault로 보내는 형식 : 'application/x-www-form-urlencoded;charset=UTF-8' not supported --> 미디어 타입이 맞지 않을 때 나오는 오류
		// JSON.stringify() : 자바 스크립트 객체를 json 형태로 변환
		$.ajax({ // 보내야하는 형태! 는! 무조건 ajax
			url:'1004',
			type:'put',
			contentType:'application/json', // 미디어 타입(JSON으로)을 맞추기 위해 contentType 사용
			data:JSON.stringify(param), //{price:25000} <--- 자바 스크립트 객체임 
			success:function(data) {
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			} // 어느정도 틀이 정해져있음 결국 C(insert)R(read)U(update)D(delete)이기 때문에.
		})
	}) // update 종료
	
	$("#insert").click(function(){
		let param = {
				code:$("#code").val(),
				title:$("#title").val(),
				writer:$("#writer").val(),
				price:$("#price").val()
		};
		
		// 데이터를 json 형태로 넘길떄
		/* $.ajax({
			url:"new",
			type:"post",
			contentType:"application/json",
			data:JSON.stringify(param),
			success:function(data) {
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
		}) */
		
		// 데이터를 폼으로 보낼 때 (비동기식)
		$.ajax({
			url:"new2",
			type:"post",
			data:$("form").serialize(), // serialize() : (데이터를 json형태로 넘길 때와 다른점은 이 부분 밖에 없음) 
			success:function(data) {
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
		})
	})
})
</script>
</body>
</html>