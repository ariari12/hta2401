package javaz.jdbc;

import java.util.Date;

public class ExerciseVO {
	// t_emp 테이블의 컬럼과 일치하는 타입의 필드들 선언
	private int empNo;
	private String eName;
	private int deptNo;
	private int sal;
	private Date hireDate;
	
	public ExerciseVO() { };
	
//	public ExerciseVO(int empNo, String eName, int deptNo, int sal) {
//		this.empNo = empNo;
//		this.eName = eName;
//		this.deptNo = deptNo;
//		this.sal = sal;
//	}
//	
//	public ExerciseVO(int empNo, String eName, int deptNo, int sal, Date hireDate) {
//		this.empNo = empNo;
//		this.eName = eName;
//		this.deptNo = deptNo;
//		this.sal = sal;
//		this.hireDate = hireDate;
//	}
	
	// setter / getter
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEName() {
		return eName;
	}
	public void setEName(String eName) {
		this.eName = eName;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int depNo) {
		this.deptNo = depNo;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
}
