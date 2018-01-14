package com.oa.service.qh;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.jpa.IOaDeptDao;
import com.oa.dao.jpa.IOaEmpDao;
import com.oa.dao.jpa.IOaScheduletypeDao;
import com.oa.pojos.OaDept;
import com.oa.pojos.OaScheduletype;
import com.oa.vo.ReasonInformation;
import com.oa.vo.qh.EmpVo;
import com.oa.vo.qh.ScheduletypeVo;
import com.oa.vo.qh.ScheduletypeVoFrom;

/**
 * 排班类型业务类
 * 
 * @author qh
 *
 */
@Service
@Transactional
public class ScheduletypeService {
	@Autowired
	private IOaScheduletypeDao iScheduletypeDAO;
	@Autowired
	private IOaDeptDao iOaDeptDao;
	@Autowired
	private IOaEmpDao iOaEmpDao;
	public Map<String, Object> queryAllScheduletype(int page, int limit) {
		Pageable pageable = new PageRequest(page - 1, limit);
		// 获取当前页的记录
		List<OaScheduletype> list = iScheduletypeDAO.queryAllByPage(pageable);
		// 获取总的记录条数
		long total = iScheduletypeDAO.count();
		Map<String, Object> map = new HashMap<>();
		map.put("count", total);
		map.put("data", MapToList(list));
		map.put("code", 0);
		return map;
	}

	/**
	 * 将map集合转换为list集合
	 * 
	 * @param list
	 * @return
	 */
	private List<ScheduletypeVo> MapToList(List<OaScheduletype> list) {
		if (list == null)
			return null;
		List<ScheduletypeVo> list2 = new ArrayList<>();
		for (OaScheduletype oasheduletype : list) {
			ScheduletypeVo vo = typePojoToVo(oasheduletype);
			list2.add(vo);
		}
		return list2;
	}

	/**
	 * 将排班类型的pojo转换为vo
	 * 
	 * @param oaScheduletype
	 * @return
	 */
	private ScheduletypeVo typePojoToVo(OaScheduletype oaScheduletype) {
		String week = "";
		ScheduletypeVo sVo = new ScheduletypeVo();
		sVo.setShtId(oaScheduletype.getShtId());
		sVo.setShtName(oaScheduletype.getShtName());
		week += (oaScheduletype.getShtW1() == 1 ? "星期一," : "");
		week += (oaScheduletype.getShtW2() == 1 ? "星期二," : "");
		week += (oaScheduletype.getShtW3() == 1 ? "星期三," : "");
		week += (oaScheduletype.getShtW4() == 1 ? "星期四," : "");
		week += (oaScheduletype.getShtW5() == 1 ? "星期五," : "");
		week += (oaScheduletype.getShtW6() == 1 ? "星期六," : "");
		week += (oaScheduletype.getShtW7() == 1 ? "星期天," : "");
		if(week.length()>0) {
			sVo.setShtWeek(week.substring(0, week.length() - 1));
		}else {
			sVo.setShtWeek(week);
		}
		sVo.setShtRemark(oaScheduletype.getShtRemark());
		sVo.setShtStatus(oaScheduletype.getShtStatus() == 1 ? "启用" : "禁用");
		return sVo;
	}
	/**
	 * 新增一个排班类型
	 * @param sFrom
	 * @return
	 */
	public ReasonInformation addScheduletype(ScheduletypeVoFrom sFrom) {
		 OaScheduletype oaScheduletype=stvfToPojo(sFrom);
		 oaScheduletype.setShtStatus(1);
		 oaScheduletype.setShtId(queryScheduletypeMaxId());
		 try {
			 iScheduletypeDAO.save(oaScheduletype);
			 return new ReasonInformation();
		} catch (Exception e) {
			e.printStackTrace();
			return new ReasonInformation(false,e.getMessage());
		}
	}
	/**
	 * 将排班类型的vo转换为pojo
	 * @param sFrom
	 * @return
	 */
	public OaScheduletype stvfToPojo(ScheduletypeVoFrom sFrom) {
		 OaScheduletype oaScheduletype=new OaScheduletype();
		 oaScheduletype.setShtName(sFrom.getShtName());
		 oaScheduletype.setShtRemark(sFrom.getShtRemark());
		 oaScheduletype.setShtW1(sFrom.getShtW1()==null?0:1);
		 oaScheduletype.setShtW2(sFrom.getShtW2()==null?0:1);
		 oaScheduletype.setShtW3(sFrom.getShtW3()==null?0:1);
		 oaScheduletype.setShtW4(sFrom.getShtW4()==null?0:1);
		 oaScheduletype.setShtW5(sFrom.getShtW5()==null?0:1);
		 oaScheduletype.setShtW6(sFrom.getShtW6()==null?0:1);
		 oaScheduletype.setShtW7(sFrom.getShtW7()==null?0:1);
		 return oaScheduletype ;
	}
	/**
	 * 根据id查找排班类型
	 * @return 
	 * @return 
	 */
	public OaScheduletype queryScheduletypeById(String shtId) {
		return iScheduletypeDAO.findOne(shtId);
		
	}
	/**
	 * 编辑排班类型
	 * @param sFrom
	 * @return 
	 */
	public ReasonInformation editScheduletype(ScheduletypeVoFrom sFrom) {
		try {
			OaScheduletype stvfToPojo = stvfToPojo(sFrom);
			stvfToPojo.setShtId(sFrom.getShtId());
			stvfToPojo.setShtStatus(Integer.parseInt(sFrom.getShtStatus()));
			iScheduletypeDAO.save(stvfToPojo);
			return new ReasonInformation();
		} catch (Exception e) {
			return new ReasonInformation(e.getMessage());
		}
	}

	/**
	 * 查找排班类型的最大编号
	 * 
	 * @return
	 */
	private String queryScheduletypeMaxId() {
		String id = "sht";
		DateFormat format = new SimpleDateFormat("yyyy");
		id += format.format(new Date());

		String shtId = iScheduletypeDAO.queryMaxId();
		if (shtId == null) {
			id = id + "001";
		} else {
			int n = Integer.parseInt(shtId.substring(7)) + 1;
			if (n < 10) {
				id = id + "00" + n;
			} else if (n < 100) {
				id = id + "0" + n;
			} else {
				id = id + n;
			}
		}
		return id;
	}
	
	/**
	 * 修改排班类型状态
	 * @param shtId
	 * @return 
	 */
	public boolean updateScheduletypeShtStatus(String shtId) {
		try {
			OaScheduletype oaScheduletype=iScheduletypeDAO.findOne(shtId);
			oaScheduletype.setShtStatus(0);
			return true;
		} catch (Exception e) {
			  e.printStackTrace();
			  return false;
		}
	}
	/**
	 * 
	 */
	public void queryAllDept() {
		List<OaDept> queryDept = iOaDeptDao.queryDept();
		for (OaDept oaDept : queryDept) {
			findEmpByDeptId(oaDept.getDeptId());
		}
		
	}
	public void findEmpByDeptId(String deptId) {
		List<Object[]> queryEmpByDeptId = iOaEmpDao.queryEmpByDeptId(deptId);
		List<EmpVo>list=new ArrayList<>();
		for (Object[] objects : queryEmpByDeptId) {
			EmpVo empVo=new EmpVo((String)objects[0],(String)objects[1]);
			list.add(empVo);
		}
		
		for (EmpVo empVo : list) {
			System.out.println(empVo);
		}
	}
}
