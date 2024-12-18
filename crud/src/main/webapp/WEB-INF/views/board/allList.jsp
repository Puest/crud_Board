<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<!-- 사용자 이름과 로그아웃 버튼 -->
	<div class="container d-flex justify-content-end align-items-center mt-3">
		<div class="me-3">
			<!-- 로그인된 사용자 이름 표시 -->
			<c:if test="${not empty sessionScope.loginUser}">
				<span class="fw-bold">${sessionScope.loginUser.username}님, 환영합니다.</span>
			</c:if>
		</div>
		<div>
			<!-- 로그아웃 버튼 -->
			<c:if test="${not empty sessionScope.loginUser}">
				<a href="logout" class="btn btn-danger btn-sm">Logout</a>
			</c:if>
		</div>
	</div>

	<!-- 게시판 목록 -->
	<div class="container mt-3">

		<h2 class="text-center mb-4">게시판 목록</h2>

		<div id="registerOK" class="alert alert-info visually-hidden"
			role="alert">새 글이 등록되었습니다.</div>
		<div id="removeOK" class="alert alert-danger visually-hidden"
			role="alert">글이 삭제되었습니다.</div>
		<div id="updateNO" class="alert alert-danger visually-hidden"
			role="alert">수정 권한이 없습니다.</div>
		<div id="removeNO" class="alert alert-danger visually-hidden"
			role="alert">삭제 권한이 없습니다.</div>
			
		<!-- 게시글 테이블 -->
		<table class="table table-striped table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col">No</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
				</tr>
			</thead>
			<c:forEach var="boardVO" items="${memberList}">
				<tr>
					<td>${boardVO.board_no}</td>
					<td><a href="read?board_no=${boardVO.board_no}"
						class="text-decoration-none"> ${boardVO.title} </a></td>
					<td>${boardVO.writer}</td>
					<td><fmt:formatDate value="${boardVO.updated_at}"
							pattern="yyyy-MM-dd HH:mm" timeZone="UTC" /></td>
				</tr>
			</c:forEach>
		</table>

		<!-- 글 작성 버튼 -->
		<div class="text-end">
			<a href="register" class="btn btn-primary">새 글 작성</a>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		var result = '${result}';
		$(function() {
			if (result === 'registerOK') {
				$('#registerOK').removeClass('visually-hidden');
				$('#registerOK').fadeOut(3000);
			}

			if (result === 'removeOK') {
				$('#removeOK').removeClass('visually-hidden');
				$('#removeOK').fadeOut(3000);
			}
			
			if (result === 'updateNO') {
				$('#updateNO').removeClass('visually-hidden');
				$('#updateNO').fadeOut(3000);
			}
			
			if (result === 'removeNO') {
				$('#removeNO').removeClass('visually-hidden');
				$('#removeNO').fadeOut(3000);
			}
		})
	</script>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
