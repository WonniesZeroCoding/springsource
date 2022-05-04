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
			
			// bno가 있는 경우 제거
			actionForm.find("[name='bno']").remove();
			
			//action 수정
			actionForm.attr("action","/board/list");
			
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
	
	//타이틀 클릭 시
	$(".move").click(function(e){
		e.preventDefault(); // a태그 기능 중지
		
		//a가 가지고 있는 href 가지고 오기
		let href = $(this).attr("href");
		//list.jsp의 하단의 actionForm안에 bno 태그(bno값은 href가 가지고 있는 값으로) 추가하기
		//내용 보기에 들어갔을 때 사용자가 리스트 버튼이 아닌 뒤로 가기 버튼을 눌러서
		//목록으로 돌아올 때, bno가 추가되는 것 방지
		if(actionForm.find("[name='bno']").length!=0){
			actionForm.find("[name='bno']").val(href);
		} else {
		actionForm.append("<input type='hidden' name='bno' value='"+href+"'>"); // 값 추가는 append
			
		}
		//actionForm action 변경(/board/read)
		actionForm.attr("action","/board/read");
		//actionForm 보내기
		actionForm.submit();
	})
	
	// 검색 버튼 클릭 시
	$(".btn-default").click(function(e){
		//submit 기능 중지
		e.preventDefault();		
		//일단 searchForm가져오기
		let searchForm = $("#searchForm");
		//타입이나 키워드, 여러군대 들어갈 수 있다면 구별할 수 있어야하기 때문에
		if(searchForm.find("select[name='type']").val()==''){
			
		// type이 아무것도 선택 되지 않으면 경고 메세지 보내기
		alert("검색 기준을 선택해주세요");
		return false;				
		}		
		
		//keyword 값이 없으면 경고 메세지 주기
		if(searchForm.find("input[name='keyword']").val()==''){
			
		// type이 아무것도 선택 되지 않으면 경고 메세지 보내기
		alert("검색 내용을 입력해주세요");
		return false;			
		}	
		//검색 폼 안에 pageNum은 1로 변경
		searchForm.find("input[name='pageNum']").val("1");
		
		//검색 폼 전송 (<form>~</form>) 폼을 전송하면 그 안에 있는 ~ 모든 값 전송
		searchForm.submit();		
	})
})