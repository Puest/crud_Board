<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">

		<h2 class="text-center mb-4">글 수정하기</h2>
		<!-- 수정 폼 -->
		<form action="update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="board_no" value="${boardVO.board_no}">

			<!-- page와 perPageNum & search와 keyword 추가 -->
			<input type="hidden" name="pageNo" value="${ctr.pageNo}" /> <input
				type="hidden" name="totalPageNo" value="${ctr.totalPageNo}" /> <input
				type="hidden" name="search" value="${ctr.search}" /> <input
				type="hidden" name="keyword" value="${ctr.keyword}" />

			<div class="mb-3">
				<label for="title" class="form-label">제목</label> <input type="text"
					class="form-control" id="title" name="title"
					value="${boardVO.title}" required>
			</div>

			<div class="mb-3">
				<label for="description" class="form-label">내용</label>
				<textarea class="form-control" id="description" name="description"
					rows="5" required>${boardVO.description}</textarea>
			</div>

			<!-- 파일 업로드 -->
			<div class="mb-3">
				<label for="files" class="form-label">첨부파일</label>
				<div id="file-container" class="border p-3 rounded mb-3"
					style="min-height: 50px;">
					<c:forEach var="file"  items="${fileList}" >
						<div id="file-list">
							<span>${file.org_file_name}</span> <input type="hidden"
								name="existingFiles" value="${file.fileId}" />
							<button type="button" class="btn btn-danger btn-sm"
								onclick="deleteExistingFile(this, ${file.file_id})">삭제</button>
						</div>
					</c:forEach>
				</div>
				<button type="button" class="btn btn-primary btn-sm"
					onclick="addFileField()">+ 파일 추가</button>
			</div>

			<div class="text-end">
				<button type="submit" class="btn btn-primary">수정 완료</button>
				<a href="read${ctr.makerQuery()}&board_no=${boardVO.board_no}"
					class="btn btn-secondary">취소</a>
			</div>
		</form>
	</div>

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
	
		function deleteExistingFile(button, file_id) {
			const fileItem = button.parentElement;
			fileItem.remove();
		}
	</script>
</body>
</html>