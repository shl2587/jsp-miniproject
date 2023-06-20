<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<jsp:useBean id="user" class="member.MemberDTO" />
<jsp:setProperty property="*" name="user" />
<c:set var="login" value="${memberDAO.login(user) }" scope="session"/>
<c:if test="${not empty login }">
	<script>
		alert("로그인 성공");
		location.href='${cpath}';
	</script>
</c:if>
<c:if test="${empty login }">
	<script>
		alert("로그인 실패");
		location.href='${cpath}/member/login.jsp';
	</script>
</c:if>

</body>
</html>