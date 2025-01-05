<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 페이지</title>
<!-- Bootstrap CSS 추가 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<head>
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<h2 class="text-center mb-4">회원가입</h2>
				<form role="form" method="post" autocomplete="off">
					<!-- 아이디 -->
					<div class="input-group mb-3">
						<input type="text" class="form-control" id="username"
							name="username" placeholder="아이디" required>
					</div>

					<!-- 비밀번호 -->
					<div class="mb-3">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="비밀번호" required>
					</div>

					<!-- 이메일 -->
					<div class="mb-3">
						<input type="email" class="form-control" id="email" name="email"
							placeholder="이메일주소" required>
					</div>

					<!-- 이름 -->
					<div class="mb-3">
						<input type="text" class="form-control" id="name" name="name"
							placeholder="이름" required>
					</div>

					<button type="submit" class="btn btn-primary w-100 mb-2">회원가입</button>
				</form>

				<p>
					<a href="/member/login">로그인하기</a>
				</p>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>