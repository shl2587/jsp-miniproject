<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<!-- 로그인 -->
<div class="login_wrap">
	<div class="login_logo">
		<p><a href="${cpath }"><img src="${cpath }/image/logo.png"></a></p>
	</div>
	<div class="login_main">
		<form method="post" action="${cpath }/member/login-action.jsp">
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



</body>
</html>