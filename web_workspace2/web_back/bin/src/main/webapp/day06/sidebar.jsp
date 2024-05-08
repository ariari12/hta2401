<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sidebar">
	<div id="login">
		<%
			/* session에 로그인 정보가 있는지 확인 */
			Object obj = session.getAttribute("vo");
			if(obj == null) {
		%>
		<form action="loginOk.jsp">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="id" id="" /></td>
				</tr>
				<tr>
					<td>PW</td>
					<td><input type="text" name="pw" id="" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인" />
						<input type="button" value="회원가입" />
					</td>
				</tr>
			</table>
		</form>
		<%
			} else {
				MemberVO vo = (MemberVO)obj;
		%>
			<div><%= vo.getName() %> 님 환영합니다</div>
			<a href="logout.jsp"><input type="button" value="로그아웃" /></a>
			<div><strong>광고부분입니다.</strong></div>
			
		<%
			}
		%>
	</div>
</div>