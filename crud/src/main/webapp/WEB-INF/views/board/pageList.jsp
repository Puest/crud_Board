<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>

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

		<div class="row">
			<div class="d-flex justify-content-end align-items-center mb-2">
				<!-- perPageNum의 값을 정하는 select 박스 -->
				<label for="itemsLabel" class="me-2"><strong>페이지당
						항목 수:</strong></label>
				<select id="itemsPerPage" class="form-select w-auto">
					<option value="10">10</option>
					<option value="15">15</option>
					<option value="20">20</option>
				</select>
			</div>
		</div>

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
			<c:forEach var="boardVO" items="${list}">
				<tr>
					<td>${boardVO.board_no}</td>
					<td><a
						href="read${pageMaker.makerQuery(pageMaker.ctr.pageNo)}&board=${boardVO.board_no}"
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
		
		<!-- 페이지네이션 -->
		<div class="mt-3">
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
					<!-- prev 버튼 -->
					<li id="page-prev"><a class="page-link"
						href="pageList${pageMaker.makerQuery(pageMaker.startPage-1)}"
						aria-label="Prev"><span aria-hidden="true">&laquo; 이전</span></a></li>

					<c:forEach begin="${pageMaker.startPage}"
						end="${pageMaker.endPage}" var="idx">
						<li id="pageNo${idx} page-item"><a class="page-link"
							href="pageList${pageMaker.makerQuery(idx)}"><span>${idx}</span></a></li>
					</c:forEach>

					<!-- next 버튼 -->
					<li id="page-next"><a class="page-link"
						href="pageList${pageMaker.makerQuery(pageMaker.endPage-1)}"
						aria-label="Next"><span aria-hidden="true">다음 &raquo; </span></a></li>
				</ul>
			</nav>
		</div>
	</div>


	<!-- Jquery JS -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

	<script>
		$(function() {
			setPerPageNumSelect();

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

			//현재 페이지 파란색으로 활성화
			var thisPage = '${pageMaker.ctr.pageNo}';
			//매번 refresh 되므로 다른 페이지 removeClass 할 필요는 없음->Ajax 이용시엔 해야함
			$('#pageNo' + thisPage).addClass('active');
		})

		/* 버튼 설정 */
		function setPerPageNumSelect() {
			var totalPageNum = '${pageMaker.ctr.totalPageNo}';
			var thisPage = '${pageMaker.ctr.pageNo}';
			var $itemsPerPage = $('#itemsPerPage');
			$itemsPerPage.val(totalPageNum).prop("selected", true);
			// itemsPerPage가 바뀌면 링크 이동
			$itemsPerPage.on('change', function() {
				//pageMarker.makeQuery 사용 못하는 이유: makeQuery는 page만을 매개변수로 받기에 변경된 totalPageNo을 반영못함
				window.location.href = "pageList?page=" + thisPage
						+ "&totalPage=" + $itemsPerPage.val();
			})
		}
	</script>



	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
