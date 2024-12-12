<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Login Page</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2 class="h2 text-center mb-3">Login</h2>
		<form action="/login" method="post">
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="username"
					name="username" placeholder="아이디"> <label
					for="username">아이디</label>
			</div>
			<div class="form-floating mb-3">
				<input type="password" class="form-control" id="password"
					name="password" placeholder="비밀번호"> <label
					for="password" class="form-label">비밀번호</label>
			</div>
			
			<button type="submit" class="btn btn-primary">Login</button>

		</form>
	</div>
</body>
</html>
