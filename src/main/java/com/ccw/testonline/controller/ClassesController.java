package com.ccw.testonline.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccw.testonline.bean.ClassesBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.entity.Classes;
import com.ccw.testonline.exception.ServiceException;

@Controller
@RequestMapping("/class")
public class ClassesController extends CommonController<Classes, ClassesBean> {
	
	@RequestMapping("/listPage")
	@ResponseBody
	public PageVO<Object> findListByMainPage(PageBean pageBean){
		PageVO<Object> pageVo=null;
		try {
			int count=commonService.getNum(null, STATEMENT, "getCount1");
			pageVo=this.commonService.findListByPage(pageBean, STATEMENT, "findListByPage1",count);
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		}
		
		return pageVo;
	}
	
	@RequestMapping("/findAllByTeaId/{teaId}")
	@ResponseBody
	public PageVO<Classes> findAllByTeaId(PageBean pageBean,@PathVariable Integer teaId){

		PageVO<Classes> pageVo=null;
		try {
			Map<String,Object> paramMap=new HashMap<>();
			paramMap.put("teaId", teaId);
			paramMap.put("SORT", "c.class_id");
			paramMap.put("DIR", "asc");
			pageVo=commonService.findListByPage(pageBean, super.STATEMENT, paramMap);
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		}
		
		return pageVo;
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List findAll(){
		List findList=null;
		try {
			findList = commonService.findList(null, STATEMENT, "findAll");
		} catch (ServiceException e) {
			logger.error("查询信息失败！");
			e.printStackTrace();
		}
		
		return findList;
	}
	
	@RequestMapping("/findByAcademyId/{academyId}")
	@ResponseBody
	public PageVO<Object> findByAcademyId(@PathVariable Integer academyId,PageBean bean){
		PageVO<Object> listByPage=null;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("academyId", academyId);
			int count=commonService.getNum(paramMap, STATEMENT, "getCount2");
			listByPage=this.commonService.findListByPage(paramMap,bean, STATEMENT, "findByAcademyId",count);
			//listByPage = commonService.findListByPage(pageVo, STATEMENT, paramMap, "findByAcademyId");
		} catch (ServiceException e) {
			logger.error("查询信息失败！");
			e.printStackTrace();
		}
		
		return listByPage;
	}
}
