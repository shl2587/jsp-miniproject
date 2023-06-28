<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>


<c:set var="resultStmt" value="${friendDAO.selectOne(login.idx, 'myIdx') }"/>

<c:if test="${login.idx == param.idx}">
	<a href="application-list.jsp?myIdx=${login.idx } ">
		일촌 신청 현황
	</a>
	<br>
	<a href="application-list.jsp?frIdx=${login.idx }">
		일촌 수락 대기
	</a>
</c:if>

<c:if test="${login.idx != param.idx }">
	<c:if test="${empty resultStmt && reultStmt.username != login.idx}">
		<a href="application.jsp?myIdx=${login.idx }&frIdx=${param.idx }">일촌 신청</a>
	</c:if>
	<c:if test="${resultStmt.statement == 0 }">
		<p>일촌 수락 대기중...</p>
	</c:if>
	<c:if test="${resultStmt.statement == 1 }">
		<p>★일촌★</p>
	</c:if>
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
		</select>
		<select name="friendIdx">
			<option value="">파도타기</option>
		</select>
	</div>
</div>
</body>
</html>