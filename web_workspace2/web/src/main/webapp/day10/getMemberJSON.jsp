<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// getMemberJSON.jsp
	JSONArray memberArray = new JSONArray();
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberVO> list = dao.selectAll();
		
		for (MemberVO vo : list) {
			JSONObject member = new JSONObject();
			member.put("no", vo.getNo());
			member.put("id", vo.getId());
			member.put("pw", vo.getPw());
			member.put("name", vo.getName());
			member.put("gender", vo.getGender());
			member.put("motive", vo.getMotive());
			member.put("zipcode", vo.getZipcode());
			member.put("addrs", vo.getAddrs());
			
			memberArray.add(member);
		}
		
		out.println(memberArray.toJSONString());
%>