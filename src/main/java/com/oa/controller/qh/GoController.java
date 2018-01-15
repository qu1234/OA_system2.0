package com.oa.controller.qh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.vo.qh.Resource;
import com.oa.vo.qh.TreeVO;

@Controller
public class GoController {

	@RequestMapping("/to_tree")
	public String toTree(){
		return "tree";
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/initTree")
	public @ResponseBody List<TreeVO>  loads(HttpServletRequest request){
		String id=request.getParameter("id");
		System.out.println("aa"+id);
		List<TreeVO> treelist =new ArrayList<>();
		Map<String, Object>  map = new HashMap<String, Object>();
		if(id==null){
			try {
				Resource resource1 = new Resource(1, "xxx公司", "#", 0, "icon2",0);
				TreeVO tree1 = new TreeVO();
				tree1.setId(resource1.getId());
				tree1.setText(resource1.getName());
				tree1.setChecked(resource1.getChecked());
				tree1.setIconCls(resource1.getIcon());
				tree1.setParent_id(resource1.getParent_id());
				
				
				if(resource1.getParent_id()==0){
					tree1.setState("closed");
				} else {
					tree1.setState("open");
				}
				map.put("url1", resource1.getUrl());
				//tree1.setAttributes(map);
				treelist.add(tree1);
				return treelist;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else if(id.equals("1")){
			Resource resource2 = new Resource(2, "财务部", "#", 0, "icon1",1);
			Resource resource3 = new Resource(3, "人事部", "#", 0, "icon1",1);
			Resource resource4 = new Resource(4, "采购部", "#", 0, "icon1",1);
			TreeVO tree2 = new TreeVO();
			tree2.setId(resource2.getId());
			tree2.setText(resource2.getName());
			tree2.setChecked(resource2.getChecked());
			tree2.setIconCls(resource2.getIcon());
			tree2.setParent_id(resource2.getParent_id());
			if(resource2.getParent_id()!=0 ){
				tree2.setState("closed");
			} else {
				tree2.setState("open");
			}
			map.put("url2", resource2.getUrl());
			tree2.setAttributes(map);
			treelist.add(tree2);
			TreeVO tree3 = new TreeVO();
			tree3.setId(resource3.getId());
			tree3.setText(resource3.getName());
			tree3.setChecked(resource3.getChecked());
			tree3.setIconCls(resource3.getIcon());
			tree3.setParent_id(resource3.getParent_id());
			if(resource3.getParent_id()!=0 && resource3.getParent_id()<5){
				tree3.setState("closed");
			} else {
				tree3.setState("open");
			}
			map.put("url3", resource3.getUrl());
			tree3.setAttributes(map);
			treelist.add(tree3);
			
			TreeVO tree4 = new TreeVO();
			tree4.setId(resource4.getId());
			tree4.setText(resource4.getName());
			tree4.setChecked(resource4.getChecked());
			tree4.setIconCls(resource4.getIcon());
			tree4.setParent_id(resource4.getParent_id());
			if(resource4.getParent_id()!=0 && resource4.getParent_id()<5){
				tree4.setState("closed");
			} else {
				tree4.setState("open");
			}
			map.put("url4", resource4.getUrl());
			tree4.setAttributes(map);
			treelist.add(tree4);
			
			
			return treelist;
		}else if(id.equals("2")){
			Resource resource5 = new Resource(5, "樊文韬", "#", 0, "icon1",2);
			Resource resource6 = new Resource(6, "岳婷", "#", 0, "icon1",2);
			Resource resource7 = new Resource(7, "何立贵", "#", 0, "icon1",2);
			TreeVO tree5 = new TreeVO();
			tree5.setId(resource5.getId());
			tree5.setText(resource5.getName());
			tree5.setChecked(resource5.getChecked());
			tree5.setIconCls(resource5.getIcon());
			tree5.setParent_id(resource5.getParent_id());
			if(resource5.getParent_id()>1){
				tree5.setState("open");
			} else {
				tree5.setState("closed");
			}
			map.put("url5", resource5.getUrl());
			tree5.setAttributes(map);
			treelist.add(tree5);
			
			TreeVO tree6 = new TreeVO();
			tree6.setId(resource6.getId());
			tree6.setText(resource6.getName());
			tree6.setChecked(resource6.getChecked());
			tree6.setIconCls(resource6.getIcon());
			tree6.setParent_id(resource6.getParent_id());
			if(resource6.getParent_id()>1){
				tree6.setState("open");
			} else {
				tree6.setState("closed");
			}
			map.put("url6", resource6.getUrl());
			tree6.setAttributes(map);
			treelist.add(tree6);
			
			TreeVO tree7 = new TreeVO();
			tree7.setId(resource7.getId());
			tree7.setText(resource7.getName());
			tree7.setChecked(resource7.getChecked());
			tree7.setIconCls(resource7.getIcon());
			tree7.setParent_id(resource7.getParent_id());
			if(resource7.getParent_id()>1){
				tree7.setState("open");
			} else {
				tree7.setState("closed");
			}
			map.put("url7", resource7.getUrl());
			tree7.setAttributes(map);
			treelist.add(tree7);
			return treelist;
		}
		return null;
		}
}
