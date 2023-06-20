<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<c:set var="friendList" value="${friendDAO.getList(login.idx) }"/>
	<div class="profile">
		<div class="profilePic">
			<img src="">
		</div>
		<div class="profileContent">
			${memberDTO.content }
		</div>
		<div class="friendList">
			<select name="friendIdx">
				<option value="">★일촌 파도타기</option>
				<c:forEach var="friendDTO" items="${friendList }">
					<option value="${friendDTO.idx }">${friendDTO.friendName }</option>
				</c:forEach>
			</select>
		</div>
	</div>
</body>
</html>