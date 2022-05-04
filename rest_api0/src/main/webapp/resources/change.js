/**
 * 
 */
$(function(){
	//입력 버튼 클릭 시 폼에 있는 데이터를 비동기식으로 전달
	$("[type='submit']").click(function(e){
		//submit 중지
		e.preventDefault();
		
		//입력 데이터 자바 스크립트 객체 생성
		let param = {
			userid:'404err',
			confirm_password:$("#confirm_password").val()
		}
		
		//ajax 통신
		$.ajax({
			url:'404err',
			type:'put',
			contentType:'application/json',
			data:JSON.stringify(param),
			success:function(data){
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}			
		})
	})
	
})