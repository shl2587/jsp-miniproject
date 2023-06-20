<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="cpath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KG미니홈피</title>
<link type="text/css" rel="stylesheet" href="${cpath }/css/style.css">
</head>
<body>
<header>
	<div class="loginInfo">
		<c:if test="${not empty login }">
			<c:redirect url="homepi.jsp?idx=${login.idx }"/>
		</c:if>
		<c:if test="${empty login }">
			<div class="login_logo">
				<p><a href="${cpath }"><img src="image/logo.png">KG미니홈피</a></p>
			</div>
				<p>새롭게 쌓이는 흑역사, 우리 함께 만들어요.</p>
			<div class="login_join">
				<form action="${cpath }/member/login-action.jsp" method="post">
					<p><input type="text" name="userid" placeholder="아이디(이메일)" required autofocus></p>
					<p><input type="password" name="userpw" placeholder="비밀번호" required></p>
					<p><input type="submit" value="로그인">
					<a href="${cpath }/member/join.jsp">
						<button>회원가입</button>
					</a>
					</p>
				</form>
			</div>
		</c:if>
	</div>
</header>

</body>
</html>