<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<!-- 왼쪽 메뉴 -->
			<div class="col-3 bg-light p-3" style="min-height: 100vh;">
				<h5>관리 메뉴</h5>
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link" href="members">회원관리</a>
					</li>
					<li class="nav-item"><a class="nav-link active" href="posts">게시글
							관리</a></li>
				</ul>
			</div>

			<div class="col-9">
				<!-- 상단 사용자 이름과 로그아웃 버튼 -->
				<div
					class="container d-flex justify-content-end align-items-center mt-3">
					<div class="me-3">
						<!-- 로그인된 사용자 이름 표시 -->
						<c:if test="${not empty sessionScope.loginUser}">
							<span class="fw-bold">${sessionScope.loginUser.username}님,
								환영합니다.</span>
						</c:if>
					</div>
					<div>
						<!-- 로그아웃 버튼 -->
						<c:if test="${not empty sessionScope.loginUser}">
							<a href="/member/logout" class="btn btn-danger btn-sm">Logout</a>
						</c:if>
					</div>
				</div>

				<h2 class="text-center my-4">게시글 관리</h2>

				<!-- 회원 목록 -->
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성일</th>
							<th>관리</th>
						</tr>
					</thead>
					<c:forEach var="post" items="${postList}">
						<tr>
							<td>${post.board_no}</td>
							<td>${post.title}</td>
							<td><fmt:formatDate value="${post.updated_at}"
									pattern="yyyy-MM-dd HH:mm" timeZone="UTC" /></td>
							<td><button type="button" class="btn btn-primary"
									onclick="goPost(${post.board_no})">이동</button>
								<button type="button" class="btn btn-danger"
									onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<!-- jQuery 로드 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<!-- Bootstrap JS Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		var result = '${success}';
		$(function() {
			if (result === 'removeOK') {
				$('#removeOK').removeClass('visually-hidden');
				$('#removeOK').fadeOut(3000);
			}
		})
	</script>
</body>
</html>