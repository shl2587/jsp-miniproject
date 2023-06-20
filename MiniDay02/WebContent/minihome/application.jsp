<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="minihome.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath" value="${pageContext.request.contextPath }" />
<c:set var="friendDAO" value="${FriendDAO.getInstance() }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<c:set var="row" value="${friendDAO.addFriend(param.myIdx, param.frIdx) }"/>

<c:if test="${row != 0 }">
	<c:set var="friend" value="${friendDAO.friend(param.myIdx) }"/>
	<form>
		<p><input type="checkbox" name="username" 	value="${friend.username }" ></p>
		<p><input type="checkbox" name="nickname"	value="${friend.nickname }" ></p>
		<p><input type="checkbox" name="email" 		value="${friend.email }" ></p>
		<p><input type="checkbox" name="gender" 	value="${friend.gender }" ></p>
		<p><input type="checkbox" name="birth"		value="${friend.birth }" ></p>
		<p><input type="submit" onclick="checked()" value="확인"></p>
	</form>
</c:if>

<script>
	
	function checked() {
		const cpath = "<c:out value='${cpath}' />"
		var infoList = document.querySelectorAll('input[type="checkbox", "checked"]')
		
		var url = cpath + '/minihome/application-list.jsp?'
		for (int i = 0; i < infoList.length; i++) {
			if (infoList[i] == document.getElementsByName("name")[0].value) {
				var key = document.getElementByName("name")[0]
			}
			if (i < infoList.length - 1) {
				url += (key + '=' infoList[i] + '&')	
			}
			else {
				url += (key + '=' infoList[i])
			}
		}
	}
</script>



</body>
</html>