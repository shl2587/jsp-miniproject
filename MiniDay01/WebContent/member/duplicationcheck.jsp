<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="join.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KG미니홈피</title>
</head>
<body>

<c:if test="${param.check eq 'userid' }">
	<c:set var="row" value="${memberDAO.duplicationcheck(param.check, param.userid) }"/>
		<c:if test="${row == 0 }">
			<script>
			function result() {
				if (confirm("사용 가능한 아이디 입니다. 사용하시겠습니까?")) {
					history.go(-1)			
				}
				else {
					alert("다른 아이디 입력 부탁드립니다")	
					location.href = 'join.jsp'			
				}					
			}
			result().start
			</script>
		</c:if>
		<c:if test="${row != 0 }">
			<script>
				alert("사용 불가능한 아이디입니다. 다른 아이디 입력 부탁드립니다")
				location.href = 'join.jsp'
			</script>
		</c:if>
</c:if>

<c:if test="${param.check eq 'nickname' }">
	<c:set var="row" value="${memberDAO.duplicationcheck(param.check, param.nickname) }"/>
		<c:if test="${row == 0 }">
			<script>
			function result() {
				if (confirm("사용 가능한 닉네임입니다. 사용하시겠습니까?")) {
					history.go(-1)
				}
				else {
					alert("다른 닉네임 입력 부탁드립니다")	
					location.href = 'join.jsp'
				}	
			}
			result().start
			</script>
		</c:if>
		<c:if test="${row != 0 }">
			<script>
				alert("사용 불가능한 닉네임입니다. 다른 닉네임 입력 부탁드립니다")
				history.go(-1)
			</script>
		</c:if>
</c:if>

<c:if test="${param.check eq 'email' }">
	<c:set var="row" value="${memberDAO.duplicationcheck(param.check, param.email) }"/>
		<c:if test="${row == 0 }">
			<script>
			function result() {
				if (confirm("사용 가능한 이메일입니다. 사용하시겠습니까?")) {
					history.go(-1)						
				}
				else {
					alert("다른 이메일입력 부탁드립니다")	
					location.href = 'join.jsp'
				}	
			}
			result().start
			</script>
		</c:if>
		<c:if test="${row != 0 }">
			<script>
				alert("사용 불가능한 이메일입니다. 다른 이메일 입력 부탁드립니다")
				location.href = 'join.jsp'
			</script>
		</c:if>
</c:if>



</body>
</html>