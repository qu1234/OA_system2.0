package com.oa.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.oa.pojos.OaDept;


public interface IOaDeptDao extends CrudRepository<OaDept, String>{
	
	@Query("select d from OaDept d")
	public List<OaDept> queryDept();
	
	/**
	 * @author qh
	 * @return
	 */
	@Query(nativeQuery=true,value="select oa_dept.dept_id as id , oa_dept.dept_name as text from oa_dept")
	public List<Object[]> queryAllDept();
}
