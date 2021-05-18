<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		
		<%-- 글쓰기 영역 - 로그인 된 상태에서만 보임 --%>
		<c:if test="${not empty userId}">
			<%-- textarea의 테두리는 없애고 div에 테두리를 준다. --%>
			<div class="write-box border rounded m-3">
				<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
				
				<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
				<div class="d-flex justify-content-between">
					<div class="file-upload d-flex">
						<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
						<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
						<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
						<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>

						<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
						<div id="fileName" class="ml-2">
						</div>
					</div>
					<button id="writeBtn" class="btn btn-info">게시</button>
				</div>
			</div>
		</c:if>
		
		<%-- 타임라인 영역 --%>
		<%-- my: margin 위아래(y축) --%>
		<div class="timeline-box my-5">
			<%-- 반복문 --%>
			<c:forEach var="post" items="${postList}">
			
				<%-- 카드 하나하나마다 영역을 border로 나눔 --%>
				<div class="card border rounded mt-3">
					
					<%-- 글쓴이 아이디 및 ... 버튼(삭제) : 이 둘을 한 행에 멀리 떨어뜨려 나타내기 위해 d-flex, between --%>
					<div class="p-2 d-flex justify-content-between">
						<span class="font-weight-bold">${post.userName}</span>
						
						<%-- 클릭할 수 있는 ... 버튼 이미지 --%>
						<a href="#" class="more-btn">
							<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
						</a>
					</div>
					
					<%-- 카드 이미지 --%>
					<div class="card-img">
						<%-- 이미지가 존재하는 경우에만 노출 --%>
						<c:if test="${not empty post.imagePath}">
							<img src="${post.imagePath}" class="w-100" alt="이미지">
						</c:if>
					</div>
					
					<%-- 좋아요 --%>
					<div class="card-like m-3">
						<a href="#"><img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18px" height="18px"></a>
						<a href="#">좋아요 11개</a>
					</div>
					
					<%-- 글(Post) --%>
					<div class="card-post m-3">
						<span class="font-weight-bold">${post.userName}</span> 
						<span>
							${post.content}
						</span>
					</div>
					
					<%-- 댓글(Comment) --%>
					
					<%-- "댓글" --%>
					<div class="card-comment-desc border-bottom">
						<div class="ml-3 mb-1 font-weight-bold">댓글</div>
					</div>
					<div class="card-comment-list m-2">
						<%-- 댓글 목록 --%>
						<div class="card-comment m-1">
							<span class="font-weight-bold">hagulu : </span>
							<span> 분류가 잘 되었군요~</span>
							
							<%-- TODO: 댓글쓴이가 본인이면 삭제버튼 노출 --%>
							<a href="#" class="commentDelBtn">
								<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
							</a>
						</div>
						
						<div class="card-comment m-1">
							<span class="font-weight-bold">jun_coffee : </span>
							<span> 철이 없었죠 분류를 위해 클러스터를 썼다는게</span>
							
							<%-- TODO: 댓글쓴이가 본인이면 삭제버튼 노출 --%>
							<a href="#" class="commentDelBtn">
								<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
							</a>
						</div>
					</div>
					
					<%-- 댓글 쓰기 --%>
					<%-- 로그인 된 상태에서만 쓸 수 있다. --%>
					<c:if test="${not empty userId}">
						<div class="comment-write d-flex border-top mt-2">
							<input type="text" id="commentText" class="form-control border-0 mr-2" placeholder="댓글 달기"/> 
							<button type="button" class="btn btn-light commentBtn">게시</button>
						</div>
					</c:if>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	
	// 파일 업로드 이미지 버튼 클릭 - 사용자가 업로드를 할 수 있게 해줌
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // 기본 작동 중지
		$('#file').click(); // input file을 클릭한 것과 같은 동작
	});
	
	// 사용자가 파일 업로드를 했을 때 유효성 확인 및 업로드 된 파일 이름을 노출
	$('#file').on('change', function(e) {
		var name = e.target.files[0].name;
		
		// 확장자 유효성 확인
		var extension = name.split('.');
		if (extension.length < 2 || 
		 	(extension[extension.length - 1] != 'gif' 
		 	&& extension[extension.length - 1] != 'png' 
		 	&& extension[extension.length - 1] != 'jpg'
		 	&& extension[extension.length - 1] != 'jpeg')) {
		 	
		 	alert("이미지 파일만 업로드 할 수 있습니다.");
		 	$(this).val(""); // 이미 올라간 것을 확인한 것이기 때문에 비워주어야 한다.
		 	return;
		 }
		 
		 $("#fileName").text(name);
	});
	
	// 게시 submit
	$('#writeBtn').on('click', function(e) {
		var content = $('#writeTextArea').val();
		if (content.length < 1) {
			alert("내용을 입력해주세요");
			return;
		}
		
		var formData = new FormData(); // form 객체를 만든다. form 자체와는 달라서 seralize 안해도 된다.
		formData.append("file", $("#file")[0].files[0]); // 파일을 가져온다. 없으면 null
		formData.append("content", content);
		
		$.ajax({
			type:'POST',
			url:'/post/create',
			data: formData,
			enctype: 'multipart/form-data', // 파일 업로드를 위한 필수 설정
			processData: false, 			// 파일 업로드를 위한 필수 설정
            contentType: false,				// 파일 업로드를 위한 필수 설정
			success: function(data) {
				if (data.result == 'success') {
					location.reload(); // 새로고침
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				var errorMsg = jqXHR.responseJSON.status;
				alert(errorMsg + ":" + textStatus);
			}
		});
	});
});
</script>