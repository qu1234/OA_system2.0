package com.oa.vo.qh;

public class EmpVo {
	private String empId;
	private String empName;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public EmpVo(String empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}
	
	public EmpVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "EmpVo [empId=" + empId + ", empName=" + empName + "]";
	}
}
