package com.oa.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.oa.pojos.OaScheduletype;

/**
 * 排班类型接口
 * @author qh
 *
 */
public interface IOaScheduletypeDao extends CrudRepository<OaScheduletype, String>{
	/**
	 * 分页查询排班类型表
	 * @param pageable
	 * @return
	 */
	@Query("select s from OaScheduletype s where s.shtStatus=1 order by s.shtId desc")
	public List<OaScheduletype>queryAllByPage(Pageable pageable);
	/**
	 * 查找最大的排班类型编号
	 * @return
	 */
	@Query("select MAX(o.shtId) from OaScheduletype o")
	public String queryMaxId();	

}
