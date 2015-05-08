package com.ccw.testonline.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccw.testonline.bean.IdsBean;
import com.ccw.testonline.bean.TeacherBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.entity.ClassTeacher;
import com.ccw.testonline.entity.Classes;
import com.ccw.testonline.entity.Teacher;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends CommonController<Teacher, TeacherBean> {
	
	
	@RequestMapping("/forClass/{teaId}")
	@ResponseBody
	public Map<String,Object> forClass(@RequestBody List<IdsBean> ids,@PathVariable Integer teaId){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			if(ids.size()==0){
				result.put("success", false);
				return result;
			}
			Teacher teacher=new Teacher(teaId);
			for (IdsBean idsBean : ids) {
				ClassTeacher classTeacher=new ClassTeacher(new Classes(idsBean.getId()),teacher);
				commonService.add(classTeacher, "com.ccw.testonline.entity.ClassTeacher", "insertSelective");
			}
				result.put("success", true);
		} catch (Exception e) {
			logger.error("分配失败！");
			result.put("success", false);
			e.printStackTrace();
		} 
		
		return result;
	}
}
