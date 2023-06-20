<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="cpath" value="${pageContext.request.contextPath }" />
<c:set var="memberDAO" value="${MemberDAO.getInstance() }" />
<c:set var="minihomeDAO" value="${MinihomeDAO.getInstance() }" />
<c:set var="minihomeDTO" value="${minihomeDAO.selectOne(login.idx) }"/>
<c:set var="friendDAO" value="${FriendDAO.getInstance() }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:if test="${not empty login }">
	<c:set var="title" value="${minihomeDTO.title }" />
	<title>${title }</title>
</c:if>
<c:if test="${empty login }">
	<c:redirect url="home.jsp"></c:redirect>
</c:if>
</head>
<body>
	<div class="top">
		<div class="visitors">
			today <div class="todayVisit">${minihomeDTO.todayVisit }</div> | total ${minihomeDTO.totalVisit }
		</div>
		<div class="topRight">
			<div class="title">
				${title }
			</div>
			<div class="path">
				<a href="${cpath }/header.jsp?idx=${login.idx }">${cpath }/header.jsp?idx=${login.idx }</a>
			</div>
		</div>
	</div>
	<div class="menuBar">
		<p><a href="${cpath }/homepi.jsp">홈</a></p>
		<p><a href="${cpath }/minihome/profile.jsp">프로필</a></p>
		<p><a href="${cpath }/board/board.jsp">게시판</a></p>
		<p><a href="${cpath }/board/movie.jsp">동영상</a></p>
		<p><a href="${cpath }/board/home.jsp">방명록</a></p>
	</div>
	<div class="sideMenuBar">
		<div class="feeling">
			<p>
				고독 <input type="range" min="0" max="100" value="50" name="loneliness">
			</p>
			<p>
				슬픔 <input type="range" min="0" max="100" value="50" name="sadness">
			</p>
			<p>
				쓸쓸 <input type="range" min="0" max="100" value="50" name="solitude">
			</p>
		</div>
		<div class="toHome">
			<p>싸이월드 홈</p>
		</div>
		<div class="scrap">
			<p>
				<a href="">스크랩</a> | <a href="">즐겨찾기</a>
			</p>
		</div>
		<div class="bgm">
			<iframe width="200" height="30"
				src="https://www.youtube.com/embed/6bCiSCkxI90?autoplay=1"
				title="✔문득 싸이월드가 그리울때 l 싸이월드 감성 BGM 노래모음 미니홈피" frameborder="0"
				allow="controls; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen>
			</iframe>
		</div>
	</div>
</body>
</html>