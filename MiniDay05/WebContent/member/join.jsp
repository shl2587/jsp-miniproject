<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="cpath" value="${pageContext.request.contextPath }"/>
<c:set var="memberDAO" value="${MemberDAO.getInstance() }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KG미니홈피</title>
<link type="text/css" rel="stylesheet" href="${cpath }/css/style.css">
</head>
<body>

<main>
	<c:if test="${pageContext.request.method == 'GET'}">
		<form method="POST">
			<p>
				<input type="text" id="userid" name="userid" placeholder="아이디 입력" required autofocus>
				<a class="userid" href="javascript:idcheck();">중복확인</a>
			</p>
			<p><input type="text" name="username" placeholder="이름 입력" required></p>
			<p>
				<input type="text" id="nickname" name="nickname" placeholder="닉네임 입력" required autofocus>
				<a class="nickname" href="javascript:nicknamecheck();">중복확인</a>
			</p>
			<p>
				<input type="email" id="email" name="email" placeholder="이메일 입력" required autofocus>
				<a class="email" href="javascript:emailcheck();">사용 가능 확인</a>
			</p>
			<p>
				<select name="gender">
					<option value="성별" selected="selected">=== 성별 선택 ===</option>
					<option value="남성">남성</option>
					<option value="여성">여성</option>
				</select>
			</p>
			<p><input type="date" name="birth" required></p>
			<p>
				<input type="password" name="userpw" placeholder="패스워드 입력" required>
				<br><br>
				<input type="password" name="userpw_check" placeholder="패스워드 확인" required>
				<input type="button" onclick="passwordcheck()">중복확인
			</p>
			<p><input type="submit" value="회원가입"></p>
		</form>
	</c:if>
	
	<c:if test="${pageContext.request.method == 'POST'}">
		<jsp:useBean id="dto" class="member.MemberDTO" />
		<jsp:setProperty property="*" name="dto" />
		<c:set var="row" value="${memberDAO.insert(dto) }"/>
		<c:if test="${row != 0 }">
			<script>
				var nick="<c:out value='${dto.nickname}' />"
				const cpath = "<c:out value='${cpath}' />"
				var userid = "<c:out value='${dto.userid}' />"
				alert ( nick + '님 환영합니다!')
				location.href = cpath + '/minihome/home-create.jsp?userid=' + userid
			</script>
		</c:if>
		<c:if test="${row == 0 }">
			<script>
				alert ('회원가입 실패! 입력 정보를 다시 확인해주세요')
				history.go(-1)
			</script>
		</c:if>
	</c:if>
	
	<script>

		
		function idcheck() {
			const userid = document.getElementById('userid').value
			const cpath = "<c:out value='${cpath }'/>"
			
			location.href = cpath + '/member/duplicationcheck.jsp?userid=' + userid + '&check=userid'			
		}
		
		function nicknamecheck() {
			const nickname = document.getElementById('nickname').value
			const cpath = "<c:out value='${cpath }'/>"

			location.href = cpath + '/member/duplicationcheck.jsp?nickname=' + nickname + '&check=nickname'
		}
		
		function emailcheck() {
			const email = document.getElementById('email').value
			const cpath = "<c:out value='${cpath }'/>"

			location.href = cpath + '/member/duplicationcheck.jsp?email=' + email + '&check=email'
		}
	</script>
	<script>
		function passwordcheck() {
			
			const plist = document.querySelectorAll('input[type="password"]')
			if(plist[0].value == plist[1].value) {
				alert('비밀번호 일치')
			}
			else {
				alert('비밀번호가 일치하지 않습니다.')
			}
		}
	</script>
	
</main>
</body>
