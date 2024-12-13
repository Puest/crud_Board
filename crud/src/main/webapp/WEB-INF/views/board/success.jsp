<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>등록 완료</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <!-- 성공 메시지 -->
        <div class="text-center">
            <h2 class="text-success">게시글 등록이 완료되었습니다!</h2>
            <p class="mt-3">작성하신 게시글이 성공적으로 등록되었습니다.</p>
        </div>
        
        <!-- 버튼들 -->
        <div class="d-flex justify-content-center mt-4">
            <a href="/board/allList" class="btn btn-primary me-2">목록으로</a>
            <a href="/board/register" class="btn btn-secondary">새 글 작성</a>
        </div>
    </div>

    <!-- Bootstrap JS (optional, for interactivity) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
