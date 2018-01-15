package com.oa.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.oa.pojos.OaEmp;

public interface IOaEmpDao extends JpaSpecificationExecutor<OaEmp>,JpaRepository<OaEmp, String>{
	
	@Query("select e from OaEmp e")
	public List<OaEmp> queryEmp(Pageable p);
	//select d.deptName,e.empName,e.empId,e.empSex,e.empBirth,e.empType,e.empState,j.jobName,j.jobLevel,e.empEntry from OaEmp e inner join e.oaDept d and inner join e.oaJob j
	
	@Query("select count(e) from OaEmp e")
	public int queryEmpcount();
	/**
	 * 根据部门分组
	 * @author qh
	 * @param deptId
	 * @return
	 */
	/*@Query(nativeQuery=true,value="select oa_emp.emp_id as empId , oa_emp.emp_name as empName  FROM oa_emp  where oa_emp.dept_id=?1")*/
	@Query(nativeQuery=true,value="select oa_emp.emp_id as empId , oa_emp.emp_name as empName , oa_emp.dept_id as deptId , oa_dept.dept_name as deptName FROM oa_emp LEFT JOIN oa_dept on oa_emp.dept_id=oa_dept.dept_id  \r\n" + 
			"group by oa_emp.dept_id,oa_emp.emp_name,oa_emp.emp_id,oa_dept.dept_name")
	public List<Object[]> selectEmpGroupByDept();
	
	/**
	 * 根据部门编号查询员工
	 * @param deptId
	 * @return
	 */
	@Query(nativeQuery=true,value="select oa_emp.emp_id as id,oa_emp.emp_name as text from oa_emp where oa_emp.dept_id=?1")
	public List<Object[]> queryEmpByDeptId(String deptId);
	
}
