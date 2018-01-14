package com.oa.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.oa.pojos.OaFlow;

public interface IOaflowDao extends JpaSpecificationExecutor<OaFlow>,JpaRepository<OaFlow, String>{
	
	/**
	 * 查询所有待办流程记录
	 */
	@Query("select f from OaFlow f")
	public List<OaFlow> queryFlow(Pageable p);
	
	/**
	 * 统计总记录条数
	 */
	@Query("select count(f) from OaFlow f")
	public int queryFlowcount();
	
	/**
	 * 按流程标题模糊查询
	 */
	public List<OaFlow> findByflowNameLike(String flowname);
}
