package com.ccw.testonline.bean;

import java.util.Date;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.entity.ClassCourse;
import com.ccw.testonline.entity.Classes;
import com.ccw.testonline.entity.Course;
import com.ccw.testonline.entity.Homework;
import com.ccw.testonline.exception.ValidateException;

public class HomeworkBean implements BaseBean<Homework>  {
    private Integer teaHwId;

    private Integer classId;
    
    private Integer courseId;

    private String teaHwName;

    private Date teaHwAskTime;

    public Integer getTeaHwId() {
        return teaHwId;
    }

    public void setTeaHwId(Integer teaHwId) {
        this.teaHwId = teaHwId;
    }

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getTeaHwName() {
        return teaHwName;
    }

    public void setTeaHwName(String teaHwName) {
        this.teaHwName = teaHwName == null ? null : teaHwName.trim();
    }

    public Date getTeaHwAskTime() {
        return teaHwAskTime;
    }

    public void setTeaHwAskTime(Date teaHwAskTime) {
        this.teaHwAskTime = teaHwAskTime;
    }

	@Override
	public Homework toAddEntity() throws ValidateException {
		Homework entity = null;
		try {
			BeanValidation.validate(this);
			entity = new Homework();
			entity.setTeaHwName(this.teaHwName);
			entity.setTeaHwAskTime(this.teaHwAskTime);
			
		} catch (ValidateException e) {
			throw e;
		}
		return entity;
	}

	@Override
	public Homework toEditEntity(Homework entity) throws ValidateException {
		try {
			BeanValidation.validate(this);
			entity = new Homework();
			entity.setTeaHwName(this.teaHwName);
			entity.setTeaHwAskTime(this.teaHwAskTime);
		} catch (ValidateException e) {
			throw e;
		}
		return entity;
	}

	@Override
	public Integer getId() {
		return this.teaHwId;
	}
}