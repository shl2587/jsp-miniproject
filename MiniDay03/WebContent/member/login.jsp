<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KG미니홈피</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
%>

<c:set var="cpath" value="${pageContext.request.contextPath }" />
<c:set var="memberDAO" value="${MemberDAO.getInstance() }" />

<!-- 로그인 -->
<c:if test="${pageContext.request.method == 'GET' }">
	<div class="login_wrap">
		<div class="login_logo">
			<p><a href="${cpath }"><img src="${cpath }/image/logo.png"></a></p>
		</div>
		<div class="login_main">
			<form method="post" >
				<p><input type="text" name="userid" placeholder="아이디(이메일)" required autofocus></p>
				<p><input type="password" name="userpw" placeholder="비밀번호" required></p>
				<p><input type="submit" value="로그인"></p>
			</form>
		</div>
		<div class="login_bottom">
			<a href="${cpath }/member/search.jsp">아이디/비밀번호 찾기</a>
			<p> | </p>
			<a href="${cpath }/member/join.jsp">회원가입</a>
		</div>
</div>
</c:if>

<c:if test="${pageContext.request.method == 'POST' }">
	<jsp:useBean id="user" class="member.MemberDTO" />
	<jsp:setProperty property="*" name="user" />
	<c:set var="login" value="${memberDAO.login(user) }" scope="session"/>
	<c:if test="${not empty login }">
		<script>
			alert("로그인 성공")
			location.href='${cpath}'
		</script>
	</c:if>
	<c:if test="${empty login }">
		<script>
			alert("로그인 실패")
			history.go(-1)
		</script>
	</c:if>
</c:if>

</body>
</html>