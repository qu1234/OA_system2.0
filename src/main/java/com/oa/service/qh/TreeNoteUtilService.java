package com.oa.service.qh;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.LifecycleListener;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.jpa.IOaDeptDao;
import com.oa.dao.jpa.IOaEmpDao;
import com.oa.vo.qh.DeptTreeVo;
import com.oa.vo.qh.DeptVo;


@Service
@Transactional
public class TreeNoteUtilService {
	@Autowired
	private IOaEmpDao iEmpdao;
	@Autowired
	private IOaDeptDao iOaDeptDao;

	public List<Map<String, Object>> queryAllDept() {
		
		List<Object[]> list = iOaDeptDao.queryAllDept();
		//System.out.println(list);
		List<DeptVo> listDeptVo=new ArrayList<>();
		for (Object[] obj : list) {
			
			DeptVo deptVo=new DeptVo((String)obj[0],(String)obj[1]);
			listDeptVo.add(deptVo);
		}
		return queryEmpGroupByDept(listDeptVo);
	}
	
	public List<Map<String, Object>> queryEmpGroupByDept(List<DeptVo> listDeptVo) {
		List<Map<String, Object>> treeData = new ArrayList<>();
		for (DeptVo deptVo : listDeptVo) {
			HashMap<String, Object> map=new HashMap<>();
			map.put("id",deptVo.getId());
			map.put("text",deptVo.getText());
			List<Object[]> queryEmpByDeptId = iEmpdao.queryEmpByDeptId(deptVo.getId());
			List<DeptTreeVo>list=new ArrayList<>();
			 for (Object[] obj : queryEmpByDeptId) {
				DeptTreeVo deptTreeVo=new DeptTreeVo(deptVo.getId(),(String)obj[0],(String)obj[1]);
				list.add(deptTreeVo);
			}
			map.put("children", list);
			treeData.add(map);
		}
		return treeData;
		
		/*List<DeptTreeVo>list2=new ArrayList<>();
		for (Object[] obj : list) {
			if(obj.length==0) {
				System.out.println("bb"+deptId);
				return null;
			}else {
				DeptTreeVo deptTreeVo=new DeptTreeVo(deptId,(String)obj[0],(String)obj[1]);
				list2.add(deptTreeVo);
				return list2;
			}
		}
		return null;	*/
	}
}
