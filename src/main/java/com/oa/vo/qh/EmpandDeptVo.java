package com.oa.vo.qh;

public class EmpandDeptVo {
	
	private String deptId;
	private String deptName;
	private String empId;
	private String empName;
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
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
	public EmpandDeptVo(String deptId, String deptName, String empId, String empName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.empId = empId;
		this.empName = empName;
	}
	public EmpandDeptVo() {
		super();
		// TODO Auto-generated constructor stub
	}
}
