/**
 * read.jsp 스크립트
 */
$(function(){
	//operForm 가져오기
	let operForm = $("#operForm");
	
	// list 버튼 클릭 시, list 페이지( /board/list ) 이동
	// list 버튼 => [type="reset"] == :reset  || 
	$(".btn-info").click(function(){
		location.href = "/board/list";
	})
	
	$(".btn-default").click(function(){
		  $("#operForm").submit();
	})
	
})