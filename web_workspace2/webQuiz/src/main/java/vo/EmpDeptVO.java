package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDeptVO {
	private int empno;
	private String ename;
	private int sal;
	private int deptno;
	private String dname;
	private String loc;
}
