<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Đăng nhập</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header" style="text-align: center;">
				<c:if test="${not empty message}">
					<div class="alert alert-${alert}">
						<strong>${message}</strong>
					</div>
				</c:if>
				<%--<div class="alert alert-danger">--%>
					<%--<strong>Login Fail!</strong>--%>
				<%--</div>--%>
				<h3>Đăng nhập</h3>
				<!-- <div class="d-flex justify-content-end social_icon">
                    <span><i class="fab fa-facebook-square"></i></span>
                    <span><i class="fab fa-google-plus-square"></i></span>
                    <span><i class="fab fa-twitter-square"></i></span>
                </div> -->
			</div>
			<div class="card-body">
				<form action="<c:url value='/dang-nhap'/>" id="formLogin" method="post">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control"
							   placeholder="Tên đăng nhập" name="userName">

					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" class="form-control"
							   placeholder="Mật khẩu" name="password">
					</div>
					<!-- <div class="row align-items-center remember">
                        <input type="checkbox">Remember Me
                    </div> -->
					<input type="hidden" value="login" name="action" />
					<div class="form-group">
						<input type="submit" value="Login" class="btn float-right login_btn" >
					</div>

				</form>
			</div>
			<!-- <div class="card-footer">
                <div class="d-flex justify-content-center links">
                    Don't have an account?<a href="#">Sign Up</a>
                </div>
                <div class="d-flex justify-content-center">
                    <a href="#">Forgot your password?</a>
                </div>
            </div> -->
		</div>
	</div>
</div>
</body>
</html>