/**
 * modify.jsp 스크립트
 */
$(function(){
	//operForm 가져오기
	let operForm = $("#operForm");
	
	$("button").click(function(e){
		e.preventDefault(); //submit 막기
		
		//현재 눌러진 버튼의 data-값을 가져오기
		let oper = $(this).data("oper"); // oper는 전역 속성
		
		if(oper == "modify") {
			operForm = $("[role='form']");
			
			//첨부파일 목록 가져가기
			 let str = "";
      
      // li 태그 정보 수집하기
      $(".uploadResult ul li").each(function(idx, obj){
         var job = $(obj);
         
         str += "<input type='hidden' name='attachList[" + idx + "].uuid' value='" + job.data("uuid") + "'>";   
         str += "<input type='hidden' name='attachList[" + idx + "].uploadPath' value='" + job.data("path") + "'>";   
         str += "<input type='hidden' name='attachList[" + idx + "].fileName' value='" + job.data("filename") + "'>";   
         str += "<input type='hidden' name='attachList[" + idx + "].fileType' value='" + job.data("type") + "'>";   
      });
      
      console.log("form 태그 삽입 전 : " + str);
			
		operForm.append(str);	
			
		}else if (oper == "remove") {
			operForm.attr('action','/board/remove');
		}else if (oper == "list") {
			operForm.find("[name='bno']").remove();
			operForm.attr('action','/board/list');
		}
		
	 	operForm.submit();
	})
	
		//첨부 파일 가져오기 - 무조건 실행되는 부분(있든-있으면 보여주고, 없든-없으면 안보여줌)
	$.getJSON({
		url:'getAttachList',
		data:{
			bno:bno
		},
		success:function(data){
			console.log(data);
			showUploadFile(data);
		}
		
	})
	
	// X 버튼 클릭 시 화면에서만 삭제
	// 파일 최종삭제는 modify 버튼을 누른 후에 적용되어야 함
   $(".uploadResult").on("click","button",function(){

      //span 태그가 속해있는 li 태그 가져오기
      let targetLi = $(this).closest("li");
      
  			if(confirm('정말로 파일을 삭제하시겠습니까?')){
            //li 태그 제어
            targetLi.remove();			
		}
	})
	// 첨부 파일 스크립트 종료 --------------------------------
	
})


























