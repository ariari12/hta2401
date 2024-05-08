<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sidebar">
	<div id="login">
		<form action="loginOk.jsp">
			<table>
			<%
				Object obj = session.getAttribute("MemberVO");
				MemberVO vo = (MemberVO)obj;
				if (vo == null) {
			%>
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
			<%
				} else {
					System.out.println("MemberVO 세션값: " + vo);
			%>
					<tr>
						<td colspan="2"><%= vo.getName() %>님 환영합니다.</td>
					</tr>
					<tr>
						<td colspan="2"> <input type="submit" value="로그아웃" />
					</tr>
					<tr>
						<td colspan="2"> <b>광고부분입니다.</b> </td>
					</tr>
			<%
				}
			%>
			</table>
		</form>
	</div>
</div>