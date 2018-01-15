package com.oa.controller.qh;


import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.oa.pojos.OaScheduletype;
import com.oa.service.qh.TreeNoteUtilService;
import com.oa.service.qh.ScheduletypeService;
import com.oa.vo.ReasonInformation;
import com.oa.vo.qh.ScheduletypeVoFrom;



/**
 * 排班类型controller
 * @author qh
 *
 */
@Controller
public class ScheduletypeController {
	@Autowired
	private ScheduletypeService scheduletypeService;
	@Autowired
	private TreeNoteUtilService treeNoteUtilService;
	/**
	 * 进入排班类型界面
	 * @return
	 */
	@RequestMapping("/to_Scheduletype")
	public String to_Scheduletype() {
		return "Scheduletype";
	}
	/**
	 * 分页查询排班类型
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/findScheduletTypeList")
	public @ResponseBody Map<String, Object> findTypeList(int page,int limit) {
		return scheduletypeService.queryAllScheduletype(page, limit);
	}
	/**
	 * 编辑排班类型表单
	 * @return
	 */
	@RequestMapping("/to_AddScheduletypeFrom")
	public String to_AddScheduletypeFrom() {
		return "AddScheduletype";
	}
	/**
	 * 新增一个排班类型
	 */
	@RequestMapping("/to_addType")
	public @ResponseBody String to_addType(ScheduletypeVoFrom sFrom) {
		
		scheduletypeService.addScheduletype(sFrom);
		
		return "aa";
	}
	/**
	 * 根据排班类型查找编号
	 * @param shtId
	 * @return
	 */
	@RequestMapping("/ObtainScheduletypeVo")
	public @ResponseBody OaScheduletype ObtainScheduletypeVo(String shtId){
		return scheduletypeService.queryScheduletypeById(shtId);
	}
	/**
	 * 编辑排班类型
	 * @param sFrom
	 * @return 
	 */
	@RequestMapping("/to_editScheduletype")
	public @ResponseBody ReasonInformation to_editScheduletype(ScheduletypeVoFrom sFrom) {
		return scheduletypeService.editScheduletype(sFrom);
	}
	/**
	 * 修改排班类型状态
	 * @param shtId
	 * @return 
	 */
	@RequestMapping("/to_updatetypeState")
	public @ResponseBody boolean to_updatetypeState(String shtId) {
		return scheduletypeService.updateScheduletypeShtStatus(shtId);
	}
	
/*	@RequestMapping("/to_findAllDept")
	public @ResponseBody Map<String, Object> to_findAllDept() {
		
		List<Object> list1 = new ArrayList<>();
		list1.add(new TreeVO(1, "aa", 1, "aa"));
		list1.add(new TreeVO(2, "aa", 1, "aa"));
		Map<String, Object> map1=new HashMap<>();
		map1.put("children",list1);
		List<Object> list = new ArrayList<>();
		list.add(new TreeVO(3, "ab", 1, 4, map1, "1"));
		list.add(new TreeVO(4, "bb", 1, "aa"));
		
		Map<String, Object> map=new HashMap<>();
		map.put("aa",list);
		return map;
		//scheduletypeService.queryAllDept();
	}*/
	
	/*@RequestMapping("/to_test")
	public String to_test() {
		return "test";
		//scheduletypeService.queryAllDept();
	}*/
	
	/*@RequestMapping("/to_findEmpGroupByDeptTree")
	public @ResponseBody List<DeptTreeVo> to_findEmpGroupByDeptTree(String deptId,HttpServletRequest request) {
		String id=request.getParameter("id");
		return treeNoteUtilService.queryEmpGroupByDept(deptId, id);
	}*/
	@RequestMapping("/findDeptAndEmpTree")
	public @ResponseBody List<Map<String, Object>> findDeptAndEmpTree() {
		return treeNoteUtilService.queryAllDept();
	}
}
