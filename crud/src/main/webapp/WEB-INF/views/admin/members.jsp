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
	<div class="container mt-5">
		<h2 class="text-center mb-4">회원 관리</h2>

		<!-- 회원 삭제 완료 알림 -->
		<div id="updateOK" class="alert alert-danger visually-hidden"
			role="alert">회원을 삭제했습니다.</div>

		<!-- 검색 기능 -->


		<!-- 회원 목록 -->
		<table class="table table-striped text-center">
			<thead>
				<tr>
					<th>회원 번호</th>
					<th>회원 이름</th>
					<th>회원 아이디</th>
					<th>회원 이메일</th>
					<th>회원 권한</th>
					<th>게시글 수</th>
					<th>댓글 수</th>
					<th>계정 생성일</th>
					<th>관리</th>
				</tr>
			</thead>
			<c:forEach var="memberVO" items="${memberList}">
				<tr>
					<td>${memberVO.seq}</td>
					<td>${memberVO.name}</td>
					<td>${memberVO.username}</td>
					<td>${memberVO.email}</td>
					<td>${memberVO.role}</td>
					<td><a href="#" data-bs-toggle="modal"
						data-bs-target="#postModal"
						onclick="loadPosts('${memberVO.username}')">${memberVO.post_cnt}</a></td>
					<td>${memberVO.comment_cnt}</td>
					<td><fmt:formatDate value="${memberVO.created_at}"
							pattern="yyyy-MM-dd HH:mm" timeZone="UTC" /></td>
					<td><button id="btn-update" class="btn btn-sm btn-warning">수정</button>
						<a href="delete?seq=${memberVO.seq}" class="btn btn-sm btn-danger"
						onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="postModal" tabindex="-1"
		aria-labelledby="postModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- 이곳에 posts.jsp의 내용이 동적으로 삽입 -->

			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- Bootstrap JS Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		function loadPosts(writer) {
		    fetch('/admin/posts?writer=' + writer)
				.then(response => response.text())
				.then(data => {
					// 모달 내용을 불러온 HTML로 교체
		            document.querySelector('#postModal .modal-content').innerHTML = data;
		        });
		}
		
		function goPost(boardNo) {
			const contextPath = "<%= request.getContextPath() %>";
			console.log(contextPath);
			location.href = contextPath + "/board/read?board_no=" + boardNo;
		}
    </script>
	
	
</body>
</html>