<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>

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

		<!-- 수정 완료 알림 -->
		<div id="updateOK" class="alert alert-warning visually-hidden"
			role="alert">글이 수정되었습니다.</div>

		<!-- 게시글 정보 -->
		<div class="card">
			<div class="card-header">
				<h4 id="post-title">제목:
					${boardVO.title}(${boardVO.board_no})</h4>
			</div>
			<div class="card-body">
				<!-- 작성자와 작성 시간 -->
				<div class="mb-3">
					<p>
						<strong>관리자:</strong> ${boardVO.writer}
					</p>
					<p>
						<strong>등록일:</strong>
						<fmt:formatDate value="${boardVO.updated_at}"
							pattern="yyyy-MM-dd HH:mm" timeZone="UTC" />
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
					<c:if test="${sessionScope.loginUser.username eq boardVO.writer || sessionScope.loginUser.role eq 'admin'}">
						<!-- 수정 버튼 -->
						<a href="update${ctr.makerQuery()}&board_no=${boardVO.board_no}"
							class="btn btn-warning">수정하기</a>
						<!-- 삭제 버튼 -->
						<button id="btn-remove" class="btn btn-danger">삭제하기</button>
					</c:if>
					<!-- 목록 버튼  -->
					<a href="pageList${ctr.makerQuery()}" class="btn btn-secondary">목록으로</a>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		var result = '${result}';
		//삭제 버튼 누르면 삭제할 것이냐고 묻고 삭제한다고 하면 주소이동(BoardController의 remove 메소드 호출)
		$(function() {
			$('#btn-remove').click(function() {
				if (confirm("게시글을 삭제하시겠습니까?")) {
					self.location.href = "delete${ctr.makerQuery()}&board_no=${boardVO.board_no}";
				}
			});

			if (result === 'updateOK') {
				$('#updateOK').removeClass('visually-hidden');
				$('#updateOK').fadeOut(2000);
			}
		});
	</script>
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>