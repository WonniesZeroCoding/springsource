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
	
		function showUploadFile(result) {
		//업로드 결과 영역 가져오기
	    let uploadResult = $(".uploadResult ul")

		let str = "";
		
		$(result).each(function(idx,obj){
	
			if(obj.fileType){ // 이미지파일
			//썸네일 이미지 보여주기
			//썸네일 이미지 경로
			let fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
			
			//원본 파일 이미지 경로 만들기
			let oriPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			oriPath = oriPath.replace(new RegExp(/\\/g),"/");
			
			
			//코드는 get방식으로 들어오고 있음
			str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"' >"
			str += "<a href=\"javascript:showImage(\'"+oriPath+"\')\">";
			str += "<img src='/display?fileName="+fileCallPath+"'></a>";
			str += "<div>"+obj.fileName;
			str += "<button type='button' class='btn btn-warning btn-circle' data-file='"+fileCallPath+"' data-type='image'>";
			str += "<i class='fa fa-times'></i></button>";
			str += "</div></li>";	
			}else { // txt 파일
			
			//다운 로드 경로
			let fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
	
			str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"' >"
			str += "<a href='/download?fileName="+fileCallPath+"'>";
			str += "<img src='/resources/img/attach.png'></a>";
			str += "<div>"+obj.fileName;
			str += "<button type='button' class='btn btn-warning btn-circle' data-file='"+fileCallPath+"' data-type='files'>";
			str += "<i class='fa fa-times'></i></button>";
			str += "</div></li>";					
			}
	
		});
		console.log("업로드 파일 경로");
		console.log(str);
		
		uploadResult.append(str);
	}//showUploadFile 종료
	
	$(".btn-default").click(function(){
		  $("#operForm").submit();
		  
	
	})
	//첨부파일 ---------------------------------------------------------
	
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
	
		function showUploadFile(result) {
		//업로드 결과 영역 가져오기
	    let uploadResult = $(".uploadResult ul")

		let str = "";
		
		$(result).each(function(idx,obj){
			
			if(obj.fileType){ // 이미지파일
			//썸네일 이미지 보여주기
			//썸네일 이미지 경로
			let fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
			
			//원본 파일 이미지 경로 만들기
			let oriPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			oriPath = oriPath.replace(new RegExp(/\\/g),"/");
			
			
			//코드는 get방식으로 들어오고 있음
			str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"' >"
			str += "<a href=\"javascript:showImage(\'"+oriPath+"\')\">";
			str += "<img src='/display?fileName="+fileCallPath+"'></a>";
			str += "<div>"+obj.fileName;
			str += "</div></li>";	
			}else { // txt 파일
			
			//다운 로드 경로
			let fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
	
			str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"' >"
			str += "<a href='/download?fileName="+fileCallPath+"'>";
			str += "<img src='/resources/img/attach.png'></a>";
			str += "<div>"+obj.fileName;
			str += "</div></li>";					
			}
	
		});
		console.log("업로드 파일 경로");
		console.log(str);
		
		uploadResult.append(str);
	}//showUploadFile 종료
	
})