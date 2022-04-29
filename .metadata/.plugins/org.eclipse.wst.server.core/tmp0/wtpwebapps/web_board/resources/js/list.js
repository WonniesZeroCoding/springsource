/**
 * 
 */
$(function(){
	//regBtn 클릭 시 /Board/register
	$("#regBtn").click(function(){
		location.href = "/board/register";
	})
	
	//게시물 등록 시, 모달 창 띄우기
	checkModal(result); // 글을 쓰고 오면 1번으로 실행
	
	history.replaceState({},null,null); // 3번
	
	function checkModal(result) {
		if(result=='' || history.state) {
			return;
		}
	
		if(parseInt(result) > 0) {
			$(".modal-body").html("게시물 "+result+" 번이 등록 되었습니다."); // 2번
		}
		
		$("#myModal").modal("show"); // 모달 감추고 싶으면 'hide'
	} // checkModal 종료 && modal은 그냥 뜨지 않는다. modal은 ("show")로 띄워져야함
	
	// 페이지 이동 버튼이 클릭이 되면
	let actionForm = $("#actionForm");
	$(".paginate_button a").click(function(e){
			e.preventDefault(); // a속성 금지
			
			//사용자가 선택한 페이지 번호 가져오기 // 선택한 요소의 href를 attr로 가져옴
			let pageNum = $(this).attr('href');  
			//가져온 번호를 actionForm 안의 pageNum 값으로 대체
			actionForm.find("[name='pageNum']").val(pageNum);
			//actionForm 보내기
				actionForm.submit();
	}) // paginate_button 종료
	
	//페이지 목록 개수가 클릭되면
	$(".form-control").change(function(){
	//actionForm 안의 amount 값을 변경하기	
		actionForm.find("[name='amount']").val($(this).val());		
	//actionForm 보내기
	actionForm.submit();
	})
})