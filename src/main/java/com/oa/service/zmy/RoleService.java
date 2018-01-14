package com.oa.service.zmy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.jpa.IOaRoleDao;
import com.oa.pojos.OaRole;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	IOaRoleDao rDao;
	

	/**
	 * 增加角色
	 * @param r
	 * @return
	 * @throws Exception
	 */
	public Boolean addRole(OaRole r) throws Exception{
		if(r.getRoleName().equals("")){
			throw new Exception("请求参数为空");
		}
		try {
			OaRole role = rDao.save(r);
			if(role!=null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw new Exception("角色添加错误");
		}
	}
	/**
	 * 查询所有角色
	 * @return
	 * @throws Exception
	 */
	public List<OaRole> getRoles() throws Exception{
		try{
			List<OaRole> list = rDao.findOaRoles();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("角色查询错误");
		}
	}
	/**
	 * 编辑角色
	 * @param formRole
	 * @return
	 * @throws Exception
	 */
	public Boolean editRole(OaRole formRole) throws Exception{
		try {
			OaRole role = rDao.findOne(formRole.getRoleId());
			if(role!=null){
				role.setRoleName(formRole.getRoleName());
				role.setDeptId(formRole.getDeptId());
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("角色编辑错误");
		}
	}
	/**
	 * 删除角色
	 * @param formRole
	 * @return
	 * @throws Exception
	 */
	public Boolean deleteRole(OaRole formRole) throws Exception{
		try {
			rDao.delete(formRole.getRoleId());
			return true;
		} catch (Exception e) {
			throw new Exception("角色删除错误");
		}
	}
	
}
