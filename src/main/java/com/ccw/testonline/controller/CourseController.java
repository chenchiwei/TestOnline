package com.ccw.testonline.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccw.testonline.bean.CourseBean;
import com.ccw.testonline.bean.IdsBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.bean.ResultBean;
import com.ccw.testonline.common.controller.CommonController;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.entity.Course;
import com.ccw.testonline.entity.Teacher;
import com.ccw.testonline.entity.TeacherCourse;
import com.ccw.testonline.exception.ServiceException;
import com.ccw.testonline.exception.ValidateException;

@Controller
@RequestMapping("/course")
public class CourseController extends CommonController<Course, CourseBean> {
	
	
	@RequestMapping("/findAllByTeaId/{teaId}")
	@ResponseBody
	public List<ResultBean> findAllByTeaId(@PathVariable Integer teaId){
		Map<String, Object> paramMap=new HashMap<String, Object>();
		List<ResultBean> list=null;
		try {
			paramMap.put("teaId", teaId);
			list=commonService.findList(paramMap, super.STATEMENT, "findAllByTeaId");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	@RequestMapping("/findAll}")
	@ResponseBody
	public List<ResultBean> findAll(){
		List<ResultBean> list=null;
		try {
			list=commonService.findList(null, super.STATEMENT, "findAll");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String,Object> add(CourseBean addBean){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			Course course=addBean.toAddEntity();
			int flag=this.commonService.add(course,STATEMENT,"insertSelective");
			if(flag>0){
				TeacherCourse teacherCourse=new TeacherCourse(null,new Teacher(addBean.getTeacherId()),course);
				commonService.add(teacherCourse, "com.ccw.testonline.entity.TeacherCourse", "insertSelective");
				result.put("success", true);
			}
		} catch (ServiceException | ValidateException e) {
			logger.error("添加信息失败！");
			result.put("success", false);
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 *  编辑信息
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String,Object> edit(CourseBean editBean){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			Course course=(Course) commonService.findById(editBean.getId(), STATEMENT);
			int flag=this.commonService.edit(editBean,STATEMENT);
			
			course.getTeacherCourse().setTeacher(new Teacher(editBean.getTeacherId()));
			course.getTeacherCourse().setCourse(course);
			if(flag>0){
				commonService.edit(course.getTeacherCourse(), "com.ccw.testonline.entity.TeacherCourse", "updateByPrimaryKeySelective");
				result.put("success", true);
			}
		} catch (Exception e) {
			logger.error("编辑信息失败！");
			result.put("success", false);
			e.printStackTrace();
		} 
		
		return result;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(@RequestBody List<IdsBean> ids){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			if(ids.size()==0){
				result.put("success", false);
				return result;
			}
			List<IdsBean> ids1=new ArrayList<>();
			for (IdsBean idsBean : ids) {
				//TeacherCourse teacherCourse=(TeacherCourse) commonService.findById(idsBean.getId(), "com.ccw.testonline.entity.TeacherCourse");
				Course course=(Course) commonService.findById(idsBean.getId(), STATEMENT);
				ids1.add(new IdsBean(course.getTeacherCourse().getTeaCourseId()));
			}
			int flag=this.commonService.delete(ids1,"com.ccw.testonline.entity.TeacherCourse","deleteByPrimaryKey");
			int flag1=this.commonService.delete(ids,STATEMENT);
			if(flag>0){
				result.put("success", true);
			}
		} catch (Exception e) {
			logger.error("删除信息失败！");
			result.put("success", false);
			e.printStackTrace();
		} 
		
		return result;
	}
}
