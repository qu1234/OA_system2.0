package com.oa.service.hlg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.jpa.IOaLeaveDao;
import com.oa.dao.jpa.IOaflowDao;
import com.oa.pojos.OaFlow;
import com.oa.vo.hlg.OaFlowVo;

@Service
@Transactional
public class FlowService {
	@Autowired
	private IOaLeaveDao leaveDao;
	@Autowired
	private IOaflowDao oaflowDao;
	
	/**
	 * 查询待办流程
	 */
	public Map<String,Object> queryFlowAll(int page,int limit,String name,String flowtitle){
		Pageable p = new PageRequest(page-1, limit);
		Specification<OaFlow> specification=new Specification<OaFlow>(){
			
			@Override
			public Predicate toPredicate(Root<OaFlow> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(name != null && !name.equals("")){
					list.add(cb.like(root.get("empName").as(String.class), "%"+name+"%"));
				}
				if(flowtitle != null && !flowtitle.equals("")){
					list.add(cb.like(root.get("flowTitle").as(String.class), "%"+flowtitle+"%"));
				}
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
			
		};
		Page<OaFlow> pa= oaflowDao.findAll(specification, p);
		List<OaFlowVo> list = flowToVolist(pa.getContent());
		Map<String, Object> dataMap=new HashMap<>();
		dataMap.put("code", 0);
		dataMap.put("count", oaflowDao.queryFlowcount());
		dataMap.put("data", list);
		return dataMap;
	}
	
	private List<OaFlowVo> flowToVolist(List<OaFlow> list2) {
		// TODO Auto-generated method stub
		List<OaFlowVo> list = new ArrayList<OaFlowVo>();
		for(OaFlow o:list2){
			list.add(toVo(o));
		}
		return list;
	}
	
	/**
	 * 将流程转换成实体
	 * @param o
	 * @return
	 * @author heligui
	 */
	private OaFlowVo toVo(OaFlow o){
		OaFlowVo vo=new OaFlowVo();
		vo.setFlowId(o.getFlowId());
		vo.setEmpName(o.getEmpName());
		vo.setFlowName(o.getFlowName());
		vo.setFlowTitle(o.getFlowTitle());
		vo.setFlowDate(o.getFlowDate()+"");
		vo.setFlowState(o.getFlowState()==1?"进行中":(o.getFlowState()==2?"完成":"作废"));
		vo.setFlowStep(o.getFlowStep()==1?"部门审批":(o.getFlowStep()==2?"总经理审批":"人事归档"));
		return vo;
	}
	
	private Date toDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 按流程标题查询
	 */
	public List<OaFlow> find_flowName_Like(String flowname){
		return oaflowDao.findByflowNameLike(flowname);
	}
	
}
