/**
 * 답글달기 
 * Ajax 호출
 */

function getAjaxDetail(rno){
	$.ajax({
		url :'/reply/get/'+rno, 
		method : 'get',
		dataType : 'json',
		
		success : function(data, status){
			// 모달창 세팅
			$("#reply").val(data.reply);
			$("#replyer").val(data.replyer);
			$("#updatedate").val(data.updatedate);
			$("#rno").val(data.rno);
			
			// 버튼 보여주기
			$("#editBtn").show();
			$("#deleteBtn").show();
			$("#replydateLi").show();
			
			// 모달창 보여주기
			$("#myModal").modal("show");
		},
		error : function(xhr, status, error){
			console.log(error);
			alert('데이터를 조회중 오류가 발생했습니다.');
		}
			
	});
}

/**
 * ajax 통신을 이용하여 서버로 부터 json형식의 데이터를 획득
 *
 * 콜백함수를 이용하여 후 처리
 */
function getAjaxList(getAjaxList){
	
	$.ajax({
		// 서버 접속 URL
		url : '/reply/list/'+getAjaxList+'/'+$("#bno").val(),
		method  : 'get',
		// 반환 데이터 타입
		dataType : 'json',
		
		// 처리 성공
		success : function(data, textStatus, jqXHR){
			console.log("getList success!");
			console.log(data);
			// 서버로 부터 데이터 획득
			var replySrc = "";
			
			// 서버로 부터 가져온 데이터를 화면애 출력 해줍시다
			$.each(data.list, function(index, list){
				
				replySrc +="<li class='left clearfix' data-rno='"+list.rno+"'>";
				replySrc +="  <div><div class='header'><strong class='primary-font'>["
			    	   +list.rno+"] "+list.replyer+"</strong>"; 
			    replySrc +="    <small class='pull-right text-muted'>"
			           +list.updateTime+"</small></div>";
			    replySrc +="    <p>"+list.reply+"</p></div></li>";
			});
			
			// 리플테이블 내용 입력
			$(".chat").html(replySrc);
			
			// li태그에 이벤트를 걸어 줍니다.
			$(".chat li[data-rno]").on("click",function(){				
				// 상세 화면을 보여줍니다
				getAjaxDetail($(this).attr("data-rno"));
			});
			
			//페이징
			replyPageing(data.pageNavi);
		},
		
		// 처리 실패
		error : function(jqXHR, textStatus, errorThrown){
			console.log("error!");
			console.log("errorThrown", errorThrown);
			console.log("textStatus", textStatus);
			console.log("jqXHR", jqXHR);

			
		}
		
	});
	
}



function addAjax(){
	
	// 오브젝트를 만들어 데이터를 담아줍니다.
	var reply = {
            reply: $("#reply").val(),
            replyer: $("#replyer").val(),
            bno: $("#bno").val(),
            pageNo:1
          };
	
	
	console.log(reply);
	console.log(JSON.stringify(reply));
	
	$.ajax({
		url : '/reply/insert',
		method : 'post',
		
		data : JSON.stringify(reply), 
		contentType : 'application/json; charset=UTF-8',

		// 처리 성공
		success : function(data, textStatus){
			console.log("add success!");
			console.log("data", data);
			console.log("textStatus", textStatus);
			
			$("#myModal").modal("hide");
			// 등록후 리스트를 다시 조회
			getAjaxList(1);
			
		},
		
		// 처리 실패
		error : function(xhr, textStatus, errorThrown){
			console.log("error!");
			console.log("errorThrown", errorThrown);
			console.log("textStatus", textStatus);
			
		}
	});
}

function deleteAjax(){
	$.ajax({
		url : '/reply/delete/'+$("#rno").val(),
		method : 'post',
		dataType : 'json',
		success : function(data, status){
			console.log(data);
			
			$("#myModal").modal("hide");
			// 등록후 리스트를 다시 조회
			getAjaxList(1);
		},
		error : function(xhr, status, error){
			console.log(error)
		}
		
	});
	
}

function updateAjax(){
	var reply = {
			bno : $("#bno").val(),
			rno : $("#rno").val(),
			reply : $("#reply").val(),
			replyer : $("#replyer").val()
	};
	
	$.ajax({
		url : '/reply/update',
		method : 'post',
		
		data : JSON.stringify(reply), 
		contentType : 'application/json; charset=UTF-8',
		
		success : function(data, status){
			console.log(data);
			
			$("#myModal").modal("hide");
			// 등록후 리스트를 다시 조회
			getAjaxList($("#replyPageNo").val());
		},
		error : function(xhr, status, error){
			console.log(error);
		}
		
	});
}



$(document).ready(function(){
	
	// 모달창 보여주기
	$("#addReplyBtn").on("click",function(){
		addModal();
	});
	
	// 등록버튼
	$("#regBtn").on("click",function(){
		addAjax();
	});
	
	// 수정버튼
	$("#editBtn").on("click",function(){
		updateAjax();
	});
	
	// 삭제버튼
	$("#deleteBtn").on("click", function(){
		deleteAjax();
	});
	
	// 리플을 조회해서 화면에 출력
	getAjaxList(1);

});



//등록창
function addModal(){
	// 버튼 숨기기
	$("#editBtn").hide();
	$("#deleteBtn").hide();
	
	// 등록일 숨기기
	$("#replydateLi").hide();
	
	// 모달창 띄우기
	$("#myModal").modal("show");
	
	$("#reply").val("");
	$("#replyer").val("");
	
}

function replyPageing(pageNavi){
	$("#replyPageNo").val(pageNavi.cri.pageNo);
	
	var pageNaviContent = "";
	var startPage = pageNavi.startPage;
	
	if(pageNavi.prev){
		pageNaviContent +=
					'<li class="page-item" onclick="replyPage('+(startPage-1)+')">'
					+'	<a class="page-link" href="#">Previous</a>'
					+'</li>';
	}
	var endPage = pageNavi.endPage;
	
	for(startPage;startPage<=endPage;startPage++){
		if(startPage==pageNavi.cri.pageNo){
			pageNaviContent +=
				'<li class="page-item active">'
			    +  '<span class="page-link">'
			    +     startPage
			    +    '<span class="sr-only">(current)</span>'
			    +  '</span>'
		    	+'</li>';	
		}else{
			pageNaviContent +=
				'<li class="page-item" onclick="replyPage('+startPage+')"><a class="page-link" href="#">'+startPage+'</a></li>';
		}
	}
	
	if(pageNavi.next){
		pageNaviContent += 
					'<li class="page-item" onclick="replyPage('+(startPage+1)+')">'
					+'	<a class="page-link" href="#">Next</a>'
					+'</li>';
	}
	
	$(".pagination").html(pageNaviContent);
}

function replyPage(rpPageNo){
	getAjaxList(rpPageNo)
	
}
