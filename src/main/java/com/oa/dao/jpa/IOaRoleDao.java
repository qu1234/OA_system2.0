package com.oa.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oa.pojos.OaRole;

public interface IOaRoleDao extends JpaRepository<OaRole, Integer>{
	
	/**
	 * 查找所有角色
	 * @return
	 */
	@Query("select new com.oa.pojos.OaRole(r.roleId,r.roleName,r.deptId) from OaRole r")
	public List<OaRole> findOaRoles();
}
