package com.ccw.testonline.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccw.testonline.bean.StudentBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.entity.Student;

@Controller
@RequestMapping("/student")
public class StudentController extends CommonController<Student, StudentBean> {

	/**
	 *  编辑学生信息
	 * @return
	 */
	@RequestMapping("/editforadmin")
	@ResponseBody
	public Map<String,Object> editforadmin(StudentBean editVo){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			BeanValidation.validate(editVo);
			int flag=super.commonService.edit(editVo,this.getSTATEMENT());
			if(flag>0){
				result.put("success", true);
			}
		} catch (Exception e) {
			logger.error("编辑学生信息失败！");
			result.put("success", false);
			e.printStackTrace();
		} 
		
		return result;
	}


}
