<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="minihome.*, friend.*"%>
<%@ include file="../header.jsp" %>

<c:set var="row" value="${friendDAO.addFriend(param.myIdx, param.frIdx) }"/>
<c:set var="requestStmt" value="${friendDAO.selectOne(param.myIdx, 'myIdx') }" />
<c:if test="${row != 0 }">
	<script>
		alert('일촌신청 완료!')
		location.href='${cpath}/minihome/minihome.jsp?idx=${requestStmt.usernum }'
	</script>
</c:if>
<c:if test="${row == 0 }">
	<script>
		alert('신청 실패...')
		history.go(-1)
	</script>
</c:if>

</body>
</html>