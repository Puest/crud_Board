<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 작성글 목록</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<!-- 버튼 (팝업 열기) -->
	<div class="container mt-5">
		<h2>${username}님의작성글보기</h2>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#postModal">작성글 보기</button>
	</div>

	<!-- Bootstrap Modal -->
	<div class="modal fade" id="postModal" tabindex="-1"
		aria-labelledby="postModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="postModalLabel">${username}님의작성글</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 게시글 테이블 -->
					<table class="table table-striped">
						<thead>
							<tr>
								<th>글번호</th>
								<th>제목</th>
								<th>작성 시간</th>
							</tr>
						</thead>
						<tbody>
							<!-- 게시글 반복 출력 -->
							<c:forEach var="post" items="${posts}">
								<tr>
									<td>${post.boardNo}</td>
									<td>${post.title}</td>
									<td>${post.createdAt}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Bootstrap JS Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>