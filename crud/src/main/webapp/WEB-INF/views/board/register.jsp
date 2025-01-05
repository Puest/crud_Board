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
		<form role="form" method="post" enctype="multipart/form-data">
			<!-- page와 perPageNum  추가 -->
			<input type="hidden" name="pageNo" value="${ctr.pageNo}" /> <input
				type="hidden" name="totalPageNo" value="${ctr.totalPageNo}" />

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

			<!-- 사용자 자동 등록(변경 비활성) -->
			<div class="mb-3">
				<label for="writer" class="form-label">작성자</label> <input
					type="text" class="form-control" id="writer" name="writer"
					value="${sessionScope.loginUser.username}" readonly>
			</div>

			<!-- 파일 업로드 -->
			<div class="mb-3">
				<label for="files" class="form-label">첨부파일</label>
				<div id="file-container" class="border p-3 rounded mb-3"
					style="min-height: 50px;">
					<div id="file-list">
						<!-- 파일 목록이 여기에 추가됩니다 -->
					</div>
				</div>
				<button type="button" class="btn btn-primary btn-sm"
					onclick="addFileField()">+ 파일 추가</button>
			</div>

			<!-- 버튼 -->
			<div class="text-center">
				<button type="submit" class="btn btn-primary">등록</button>
				<a href="pageList${ctr.makerQuery()}" class="btn btn-secondary"> 취소</a>
			</div>
		</form>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
		function addFileField() {
			const fileList = document.getElementById('file-list');
			const fileItem = document.createElement('div');
			fileItem.className = 'd-flex align-items-center mb-2';
			fileItem.innerHTML = `
				<input type="file" name="files" class="form-control me-2" />
				<button type="button" class="btn btn-danger btn-sm" onclick="deleteFileField(this)">삭제</button>
			`;
			fileList.appendChild(fileItem);
		}


		function deleteFileField(button) {
			const fileItem = button.parentElement;
			fileItem.remove();
		}
	</script>
</body>
</html>