<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center mb-4">게시글 상세보기</h2>

		<!-- 게시글 정보 -->
		<div class="card">
			<div class="card-header">
				<h4 id="post-title">${boardVO.title}(${boardVO.board_no})</h4>
			</div>
			<div class="card-body">
				
				<!-- 작성자와 작성 시간 -->
				<div class="mb-3">
					<p>
						<strong>관리자:</strong> ${boardVO.writer}
					</p>
					<p>
						<strong>등록일:</strong> ${boardVO.updated_at}
					</p>
				</div>
				<!-- 게시글 내용 -->
				<div class="mb-3">
					<p>
						<strong>내용:</strong>
					</p>
					<p>${boardVO.description}</p>
				</div>

				<!-- 버튼 -->
				<div class="text-end">
					<!-- 수정 버튼 -->
					<a href="/board/update?board_no=${boardVO.board_no}"
						class="btn btn-warning">수정하기</a>
					<!-- 삭제 버튼 -->
					<button id="btn-remove" class="btn btn-danger">삭제하기</button>
					<!-- 목록 버튼  -->
					<a href="/board/allList" class="btn btn-secondary">목록으로</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>