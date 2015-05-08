package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.entity.Classes;
import com.ccw.testonline.entity.Major;
import com.ccw.testonline.exception.ValidateException;

public class ClassesBean implements BaseBean<Classes> {

	private Integer classId;

	@NotNull
    private String className;

	@NotNull
    private Integer grade;

	@NotNull
    private Integer majorId;
    
	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	@Override
	public Classes toAddEntity() throws ValidateException {
		Classes clazz=null;
		try {
			BeanValidation.validate(this);
			clazz=new Classes();
			clazz.setClassName(this.className);
			clazz.setGrade(this.grade);
			Major major=null;
			if(this.majorId!=null){
				major=new Major();
				major.setMajorId(this.majorId);
			}
			clazz.setMajor(major);
		} catch (ValidateException e) {
			throw e;
		}
		return clazz;
	}

	@Override
	public Classes toEditEntity(Classes clazz) throws ValidateException {
		try {
			BeanValidation.validate(this);
			clazz.setClassName(this.className);
			clazz.setGrade(this.grade);
			Major major=null;
			if(this.majorId!=null){
				major=new Major();
				major.setMajorId(this.majorId);
			}
			clazz.setMajor(major);
		} catch (ValidateException e) {
			throw e;
		}
		return clazz;
	}

	@Override
	public Integer getId() {
		return this.classId;
	}

}
