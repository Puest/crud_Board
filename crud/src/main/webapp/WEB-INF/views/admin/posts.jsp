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
			<div class="col-2 bg-light p-3" style="min-height: 100vh;">
				<h5>관리 메뉴</h5>
				<ul class="nav nav-pills flex-column">
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
						<!-- 게시판으로 이동 버튼 -->
						<a href="/board/pageList" class="btn btn-primary btn-sm me-2">게시판</a>
					</div>
					<div>
						<!-- 로그아웃 버튼 -->
						<c:if test="${not empty sessionScope.loginUser}">
							<a href="/member/logout" class="btn btn-danger btn-sm">Logout</a>
						</c:if>
					</div>
				</div>

				<h2 class="text-left mt-4">게시글 관리</h2>
				<div class="text-left mb-3">게시글을 조회하거나 삭제 할 수 있습니다.</div>

				<!-- 회원 삭제 완료 알림 -->
				<div id="removeOK" class="alert alert-danger visually-hidden"
					role="alert">해당 게시글을 삭제했습니다.</div>

				<!-- 회원 목록 -->
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th>게시글 번호</th>
							<th>게시글 제목</th>
							<th>게시글 내용</th>
							<th>게시글 작성자</th>
							<th>게시글 작성일</th>
							<th>게시글 관리</th>
						</tr>
					</thead>
					<c:forEach var="post" items="${postList}">
						<tr>
							<td>${post.board_no}</td>
							<td>${post.title}</td>
							<td>${post.description}</td>
							<td>${post.writer}</td>
							<td><fmt:formatDate value="${post.updated_at}"
									pattern="yyyy-MM-dd HH:mm" timeZone="UTC" /></td>
							<td><a href="/board/read?board_no=${post.board_no}"><button
										type="button" class="btn btn-sm btn-primary">보기</button></a> <a
								href="deletePosts?board_no=${post.board_no}"><button
										type="button" class="btn btn-sm btn-danger"
										onclick="return confirm('해당 게시글을 삭제하시겠습니까?');">삭제</button></a></td>
						</tr>
					</c:forEach>
				</table>

				<!-- 페이지네이션 -->
				<div class="mt-3">
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center">
							<!-- prev 버튼 -->
							<li id="page-prev"><a class="page-link"
								href="posts${pageMaker.makerQuery(pageMaker.startPage-1)}"
								aria-label="Prev"><span aria-hidden="true">&laquo;</span></a></li>

							<c:forEach begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}" var="idx">
								<li id="pageNo${idx}" class="page-item"><a
									class="page-link" href="posts${pageMaker.makerQuery(idx)}"><span>${idx}</span></a></li>
							</c:forEach>

							<!-- next 버튼 -->
							<li id="page-next"><a class="page-link"
								href="posts${pageMaker.makerQuery(pageMaker.endPage + 1)}"
								aria-label="Next"><span aria-hidden="true">&raquo; </span></a></li>
						</ul>
					</nav>
				</div>
			</div>

		</div>
	</div>

	<!-- jQuery 로드 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<!-- Bootstrap JS Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		$(function() {
			setTotalPageSelect()

			//prev 버튼 활성화, 비활성화 처리
			var canPrev = '${pageMaker.prev}';
			if (canPrev !== 'true') {
				$('#page-prev').addClass('disabled');
			}

			//next 버튼 활성화, 비활성화 처리
			var canNext = '${pageMaker.next}';
			if (canNext !== 'true') {
				$('#page-next').addClass('disabled');
			}

			//현재 페이지 포인트 활성화
			var thisPage = '${pageMaker.ctr.pageNo}';
			$('#pageNo' + thisPage).addClass('active');

			var result = '${success}';
			$(function() {
				if (result === 'removeOK') {
					$('#removeOK').removeClass('visually-hidden');
					$('#removeOK').fadeOut(3000);
				}
			})
		})

		/* 페이징 함수 */
		function setTotalPageSelect() {
			var totalPageNum = '${pageMaker.ctr.totalPageNo}'; // 서버 데이터 반영
			var thisPage = '${pageMaker.ctr.pageNo}'; // 현재 페이지
			var $itemsPerPage = $('#itemsPerPage');

			// 초기 선택 값 설정
			$itemsPerPage.val(totalPageNum).prop("selected", true);

			// itemsPerPage가 바뀌면 링크 이동
			$itemsPerPage.on('change', function() {
				//pageMarker.makeQuery 사용 못하는 이유: makeQuery는 page만을 매개변수로 받기에 변경된 totalPageNo을 반영못함
				var selectedValue = $itemsPerPage.val();
				window.location.href = "pageList?pageNo=" + thisPage
						+ "&totalPageNo=" + selectedValue;
			})
		}
	</script>
</body>
</html>