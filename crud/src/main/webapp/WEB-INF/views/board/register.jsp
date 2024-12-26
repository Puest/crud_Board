<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4 text-center">게시판 글 작성</h2>
		<form role="form" method="post" enctype="multipart/form-data">
			<!-- page와 perPageNum  추가 -->
			<input type="hidden" name="pageNo" value="${ctr.pageNo}" /> <input
				type="hidden" name="totalPageNo" value="${ctr.totalPageNo}" />

			<!-- 제목 입력 -->
			<div class="mb-3">
				<label for="title" class="form-label">제목</label> <input type="text"
					class="form-control" id="title" name="title"
					placeholder="제목을 입력하세요" required>
			</div>

			<!-- 내용 입력 -->
			<div class="mb-3">
				<label for="content" class="form-label">내용</label>
				<textarea class="form-control" id="description" name="description"
					rows="5" placeholder="내용을 입력하세요" required></textarea>
			</div>

			<!-- 사용자 자동 등록(변경 비활성) -->
			<div class="mb-3">
				<label for="writer" class="form-label">작성자</label> <input
					type="text" class="form-control" id="writer" name="writer"
					value="${sessionScope.loginUser.username}" readonly>
			</div>

			<!-- 파일 업로드 -->
			<div class="mb-3">
				<label for="files" class="form-label">첨부파일</label> <input
					class="form-control" type="file" id="files" name="files" multiple>
			</div>

			<!-- 버튼 -->
			<div class="text-center">
				<button type="submit" class="btn btn-primary">등록</button>
				<a href="pageList" class="btn btn-secondary"> 취소</a>
			</div>
		</form>
	</div>
</body>
</html>