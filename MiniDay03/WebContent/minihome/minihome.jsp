<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<c:set var="friendList" value="${friendDAO.getList(login.idx) }"/>
<c:if test="${minihomeDTO.idx != param.idx }">
	<div class="minihomeTop">
		<a href="application.jsp?myIdx=${minihomeDTO.idx }&frIdx=${param.idx }">일촌 신청</a>
	</div>
</c:if>
	<div class="profile">
		<div class="profilePic">
			<img src="">
		</div>
		<div class="profileContent">
			${memberDTO.content }
		</div>
		<div class="friendList">
			<select name="friendIdx">
				<option value="">★일촌</option>
				<c:forEach var="friendDTO" items="${friendList }">
					<option value="${friendDTO.idx }">${friendDTO.friendName }</option>
				</c:forEach>
			</select>
			<select name="friendIdx">
				<option value="">파도타기</option>
			</select>
		</div>
	</div>
</body>
</html>