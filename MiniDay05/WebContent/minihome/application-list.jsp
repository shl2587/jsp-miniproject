<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="minihome.*, friend.*"%>
<%@ include file="../header.jsp" %>

<%
	Enumeration<String> names = request.getParameterNames();
	String name = "";
	
	while(names.hasMoreElements()) {
		name = (String)names.nextElement();
	}
	
	request.setAttribute("name", name);
%>

<jsp:useBean id="dto" class="friend.FriendDTO"/>
<jsp:setProperty property="*" name="dto" />

<c:set var="requestList" value="${friendDAO.requestList(login.idx, name) }"/>
<c:set var="friendRe" value="${friendDAO.selectOne(login.idx, name) }"/>

<c:if test="${friendRe.member == login.idx }">
	<div>
		<%-- hidden hover로 정리 --%>
		<c:forEach var="dto" items="${requestList }">
			${dto.nickname }
			<c:if test="${name eq 'frIdx' }">
				<a href="${cpath }/minihome/application-result.jsp?idx=${param.frIdx }&stmt=1">
					<button>수락</button>
				</a>
				<a href="${cpath }/minihome/application-result.jsp?idx=${param.frIdx }&stmt=2">
					<button>거절</button>
				</a>
			</c:if>
		</c:forEach>
	</div>
</c:if>

</body>
</html>