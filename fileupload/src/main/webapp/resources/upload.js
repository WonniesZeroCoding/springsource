/**
 * 	uploadform_ajax 스크립트
 */
$(function(){
	$("#uploadBtn").click(function(){
		console.log("ajax 파일 업로드 호출")

		//폼 객체 생성
		let formData = new FormData();
		//첨부파일 목록 가져오기
		let inputFile = $("[name='uploadFile']");
		console.log(inputFile);


		let files = inputFile[0].files;
		
		// 폼 객체에 첨부 파일들 추가
		for(let i= 0;i<files.length;i++){
			formData.append("uploadFile",files[i]);
		}
		
		// enctype = "multipart/form-data"와 같은 콛의 의미
		
		//processData:false -> 데이터를 일반적인 쿼리 스트링 형태로 변환할 것인지 결정
		// 						기본 값은 (application);
		//ContentType:false => 기본 값은 true
		
		
		//폼 객체 ajax 전송
		$.ajax({
			url:'uploadAjax',
			type:'post',
			processData:false,
			contentType:false,
			data:formData,
			dataType:'json',
			success:function(result) {
				//console.log(result);
				showUploadFile(result);
			}
		})
	}) // uploadBtn 종료
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
			str += "<li><a href=\"javascript:showImage(\'"+oriPath+"\')\">";
			str += "<img src='/display?fileName="+fileCallPath+"'></a>";
			str += "<div>"+obj.fileName+"<span data-file='"+fileCallPath+"' data-type='image'> x </span>";
			str += "</div></li>";	
			}else { // txt 파일
			
			//다운 로드 경로
			let fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
			
			str += "<li><a href='/download?fileName="+fileCallPath+"'>";
			str += "<img src='/resources/img/attach.png'></a>";
			str += "<div>"+obj.fileName+"<span data-file='"+fileCallPath+"' data-type='file'> x </span>";
			str += "</div></li>";					
			}
	
		});
		
		uploadResult.append(str);
	}//showUploadFile 종료
	
	//첨부 파일 삭제(X 동작 시키기 -- 이벤트 위임)
	$(".uploadResult").on("click","span",function(){
		// span 태그의 ' data- '  속성 가져오기
		let targetFile = $(this).data("file");
		let type = $(this).data("type");
	
		//span 태그가 속해있는 li태그 가져오기
		let targetLi = $(this).closest("li");
	
		$.ajax({
			url:'/deleteFile',
			data:{
				fileName:targetFile,
				type:type
			},
			type:'post',
			success:function(result){
				console.log(result);
				//li 태그 제거
				targetLi.remove();
			}
		})
	
	})	
	
	
	
	// 이미지 종료
	$(".bigPictureWrapper").on("click",function(){
		$(".bigPicture").animate({width:'0',height:'0'},1000);
		
		// callback 함수
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		}, 1000);
	})
})

function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
					.animate({width:'100%',height:'100%'},1000);
}