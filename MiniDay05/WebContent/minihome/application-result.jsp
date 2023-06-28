<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<c:set var="row" value="${friendDAO.acceptResult(param.idx, param.stmt) }"/>

<c:if test="${row == 1 }">
	<script>
		alert('이제 일촌이 되었습니다. 같이 흑역사를 만들어 보아요!!!')
		location.href = '${cpath}/minihome/minihome.jsp?idx=${login.idx}'
	</script>
</c:if>

<c:if test="${row == -1 }">
	<script>
		alert('일촌을 거절하였습니다. 흑역사를 공유할수 없습니다ㅠㅠ')
		location.href = '${cpath}/minihome/minihome.jsp?idx=${login.idx}'
	</script>
</c:if>

<c:if test="${row == 0 }">
	<script>
		alert('일촌수락에 실패하였습니다.')
		history.go(-1)
	</script>
</c:if>


	
</body>
</html>