package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.common.utils.MD5Utils;
import com.ccw.testonline.entity.Classes;
import com.ccw.testonline.entity.Student;
import com.ccw.testonline.exception.ValidateException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 在编辑学生时用于接收参数<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月15日 上午10:02:43 <br>
 * 
 */
public class StudentBean implements BaseBean<Student> {

	private Integer stuId;

	@NotNull
	private String stuName;

	private String oldPassword;

	private String stuPassword;

	private String newPassword;
	
	private String stuEmail;

	private String stuPhone;

	private Integer stuSex;

	@NotNull
	private Integer classId;

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getStuPassword() {
		return stuPassword;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public Integer getStuSex() {
		return stuSex;
	}

	public void setStuSex(Integer stuSex) {
		this.stuSex = stuSex;
	}

	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword;
	}

	@Override
	public Student toAddEntity() throws ValidateException {
		Student student = null;
		try {
			BeanValidation.validate(this);
			if (!StringUtils.isEmpty(this.stuPassword)) {
				student = new Student();
				student.setStuSex(stuSex);
				student.setStuName(this.stuName);
				if(this.stuPassword!=null){
					student.setStuPassword(MD5Utils.MD5(this.stuPassword));
				}else{
					throw new ValidateException();
				}
				
				student.setStuEmail(stuEmail);
				student.setStuPhone(stuPhone);
				Classes clazz = new Classes();
				clazz.setClassId(classId);
				student.setClazz(clazz);
			}

		} catch (ValidateException e) {
			throw e;
		}

		return student;
	}

	@Override
	public Student toEditEntity(Student entity) throws ValidateException {
		try {
			BeanValidation.validate(this);
			entity.setStuName(this.stuName);
			entity.setStuSex(stuSex);
			entity.setStuEmail(stuEmail);
			entity.setStuPhone(stuPhone);
			Classes clazz = new Classes();
			clazz.setClassId(this.classId);
			entity.setClazz(clazz);
		} catch (ValidateException e) {
			throw e;
		}
		return entity;
	}

	@Override
	public Integer getId() {
		return this.stuId;
	}

}
