<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="minihome.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="cpath" value="${pageContext.request.contextPath }"/>
<c:set var="minihomeDAO" value="${MinihomeDAO.getInstance() }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Minihompi Create</title>
</head>
<body>


<c:if test="${pageContext.request.method == 'GET' }">
	<form method="POST">
		<p><input type="hidden" name="userid" value="${param.userid }" ></p>
		<p><input type="text" name="title" placeholder="미니홈피 대문글 작성"></p>
		<p><input type="submit" value="생성하기"></p>
	</form>
</c:if>

<c:if test="${pageContext.request.method == 'POST' }">
	<c:set var="row" value="${minihomeDAO.createHome(param.userid, param.title) }" />
	<c:if test="${row != 0 }">
		<script>
			alert('축하드립니다🎉🎉🎉.  미니홈피 생성이 완료되었습니다.')
			alert('지금부터 흑역사를 만들어보아요!!!')
			const cpath = "<c:out value='${cpath }' />"                                                                                                              
			location.href = cpath + '/index.jsp'
		</script>
	</c:if>
	<c:if test="${row == 0 }">
		<script>
			alert('미니홈피 생성에 실패하였습니다. 당신은 흑역사를 생성할 수 없습니다😒😒😒')
			history.go(-1)
		</script>
	</c:if>
</c:if>
</body>
</html>