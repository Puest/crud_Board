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
		<form role="form" method="post">
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

			<!-- 사용자 등록 -->
			<div class="mb-3">
				<label for="writer" class="form-label">관리자</label> <input
					type="text" class="form-control" id="writer" name="writer"
					placeholder="관리자를 입력하세요" required>
			</div>

			<!-- 버튼 -->
			<div class="text-center">
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="reset" class="btn btn-secondary">취소</button>
			</div>
		</form>
	</div>
</body>
</html>