package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.entity.Course;
import com.ccw.testonline.entity.Teacher;
import com.ccw.testonline.entity.TeacherCourse;
import com.ccw.testonline.exception.ValidateException;

public class CourseBean implements BaseBean<Course> {

	private Integer courseId;

	@NotNull
    private String courseName;
	
	@NotNull
    private Integer teacherId;

    private String courseDesc;
	
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	@Override
	public Course toAddEntity() throws ValidateException {
		Course course = null;
		try {
			BeanValidation.validate(this);
			course = new Course();
			course.setCourseName(this.courseName);
			course.setCourseDesc(this.courseDesc);
		} catch (ValidateException e) {
			throw e;
		}
		return course;
	}

	@Override
	public Course toEditEntity(Course course) throws ValidateException {
		try {
			BeanValidation.validate(this);
			course.setCourseName(this.courseName);
			course.setCourseDesc(this.courseDesc);
		} catch (ValidateException e) {
			throw e;
		}
		return course;
	}

	@Override
	public Integer getId() {
		return this.courseId;
	}

}
