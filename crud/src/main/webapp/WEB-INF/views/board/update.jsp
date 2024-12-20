<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center mb-4">글 수정하기</h2>
		<!-- 수정 폼 -->
		<form action="update" method="post">
			<input type="hidden" name="board_no" value="${boardVO.board_no}">
			
			<div class="mb-3">
				<label for="title" class="form-label">제목</label>
				<input type="text" class="form-control" id="title" name="title"
					value="${boardVO.title}" required>
			</div>
			
			<div class="mb-3">
				<label for="description" class="form-label">내용</label>
				<textarea class="form-control" id="description" name="description"
					rows="5" required>${boardVO.description}</textarea>
			</div>

			<div class="text-end">
				<button type="submit" class="btn btn-primary">수정 완료</button>
				<a href="read?board_no=${boardVO.board_no}" class="btn btn-secondary">취소</a>
			</div>
		</form>
	</div>
</body>
</html>