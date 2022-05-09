/**
 * read.jsp 스크립트
 */
$(function(){
	//operForm 가져오기
	let operForm = $("#operForm");
	
	// list 버튼 클릭 시, list 페이지( /board/list ) 이동
	// list 버튼 => [type="reset"] == :reset  || 
	$(".btn-info").click(function(){
	
		// operForm bno 태그 제거하기
		operForm.find("input[name='bno']").remove();
		//우선 find(찾을 것(태그 등))로 찾고 .remove() 로 제거
		
		// operForm action 수정 --> /board/list로 수정
		operForm.attr("action","/board/list");
	
		// operForm 보내기
		operForm.submit();
	
	})
	
	$(".btn-default").click(function(){
		  $("#operForm").submit();
		  
	
	})
	
	//첨부 파일 가져오기 - 무조건 실행되는 부분(있든-있으면 보여주고, 없든-없으면 안보여줌)
	$.getJSON({
		url:'getAttachList',
		data:{
			bno:bno
		},
		success:function(data){
			console.log(data);
			
		}
		
	})
	
	
	
})