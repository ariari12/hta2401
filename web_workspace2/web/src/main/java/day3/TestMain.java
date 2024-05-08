package day3;

import vo.DeptVO;

public class TestMain {
	public static void main(String[] args) {
		DeptVO vo = new DeptVO();
		vo.setDeptno(1);
		vo.setDname("IT");
		vo.setLoc("SEOUL");
		
		System.out.println("vo: " + vo.toString());
//		System.out.println(vo.getDeptno() + " : " + vo.getDname() + " : " + vo.getLoc());
	}
}
