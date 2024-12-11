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
	<div class="container">
		<h2 class="h3">Login</h2>
		<form action="/login" method="post">
			<div class="mb-3">
				<label for="username" class="form-label">Username</label> <input
					type="text" class="form-control" id="username" name="username"
					value="%{username}" required>
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input
					type="password" class="form-control" id="password" name="password">
			</div>

			<div class="checkbox mb-3">
				<input type="checkbox" value="remember-me"> <label>
					로그인 상태 유지 </label>
			</div>

			<!-- 에러 메시지 표시 -->
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger" role="alert">${errorMessage}</div>
			</c:if>

			<button type="submit" class="btn btn-primary">Login</button>

		</form>
	</div>
</body>
</html>
