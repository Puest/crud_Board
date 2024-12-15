<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자유 게시판</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center mb-4">게시판 목록</h2>

		<!-- 게시글 테이블 -->
		<table class="table table-striped table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col">No</th>
					<th scope="col">제목</th>
					<th scope="col">관리자</th>
					<th scope="col">작성시간</th>
				</tr>
			</thead>
			<!-- Controller에 list에 담겨있는 model 속성을 조회결과를 차례로 boardVO에 대입 -->
			<c:forEach var="boardVO" items="${memberList}">
				<tr>
					<td>${boardVO.board_no}</td>
					<td><a href="/board/read?board_no=${boardVO.board_no}"
						class="text-decoration-none">${boardVO.title}</a></td>
					<td>${boardVO.writer}</td>
					<td>${boardVO.updated_at}</td>
				</tr>
			</c:forEach>
		</table>

		<!-- 글 작성 버튼 -->
		<div class="text-end">
			<a href="/board/register" class="btn btn-primary">새 글 작성</a>
		</div>

	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>