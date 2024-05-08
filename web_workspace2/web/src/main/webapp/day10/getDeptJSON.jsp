<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="netscape.javascript.JSObject"%>
<%@page import="vo.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

	<%
		// 부서 테이블의 데이터를 가져와서 JSON 형식으로 변환해서 화면에 출력
		
		// { deptno:10, dname:ACCOUNTING, loc:DALLAS } 
		
		JSONArray deptArray = new JSONArray();
		// dao 객체
		DeptDAO dao = new DeptDAO();
		// db로부터 부서 정보 모두 가져오기
		ArrayList<DeptVO> list = dao.selectAll();
		
		for(DeptVO vo : list) {
			// 부서 정보 하나가 들어간 JSONObject 선언
			JSONObject dept = new JSONObject();
			// map : key/value
			dept.put("deptno", vo.getDeptno());
			dept.put("dname", vo.getDname());
			dept.put("loc", vo.getLoc());
			
			// JSON 배열 객체에 JSON 객체를 담는다.
			deptArray.add(dept);
		}
		
		out.println(deptArray.toJSONString());
	%>
