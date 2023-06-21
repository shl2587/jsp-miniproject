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
			<c:redirect url="minihome/minihome.jsp?idx=${login.idx }"/>
		</c:if>
		<c:if test="${empty login }">
			<div class="login_logo">
				<p><a href="${cpath }"><img src="image/logo.png">KG미니홈피</a></p>
			</div>
				<p>새롭게 쌓이는 흑역사, 우리 함께 만들어요.</p>
			<div class="login_join">
				<a href="${cpath }/member/login.jsp">
					<button>로그인</button>
				</a>
				<a href="${cpath }/member/join.jsp">
					<button>회원가입</button>
				</a>
			</div>
		</c:if>
	</div>
</header>

</body>
</html>