package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.common.utils.MD5Utils;
import com.ccw.testonline.entity.Academy;
import com.ccw.testonline.entity.Teacher;
import com.ccw.testonline.exception.ValidateException;

public class TeacherBean implements BaseBean<Teacher> {

	private Integer teaId;

	@NotNull
    private String teaName;

	@NotNull
    private String teaPassword;

    private String teaEmail;

    private String teaPhone;
    
    private Integer academyId;
    
    private Integer teaSex;
    

	public Integer getTeaId() {
		return teaId;
	}

	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public Integer getAcademyId() {
		return academyId;
	}

	public void setAcademyId(Integer academyId) {
		this.academyId = academyId;
	}

	public Integer getTeaSex() {
		return teaSex;
	}

	public void setTeaSex(Integer teaSex) {
		this.teaSex = teaSex;
	}

	public String getTeaPassword() {
		return teaPassword;
	}

	public void setTeaPassword(String teaPassword) {
		this.teaPassword = teaPassword;
	}

	public String getTeaEmail() {
		return teaEmail;
	}

	public void setTeaEmail(String teaEmail) {
		this.teaEmail = teaEmail;
	}

	public String getTeaPhone() {
		return teaPhone;
	}

	public void setTeaPhone(String teaPhone) {
		this.teaPhone = teaPhone;
	}

	@Override
	public Teacher toAddEntity() throws ValidateException {
		Teacher teacher=null;
		try {
			BeanValidation.validate(this);
			teacher=new Teacher();
			teacher.setTeaName(this.teaName);
			teacher.setTeaEmail(this.teaEmail);
			teacher.setTeaSex(teaSex);
			teacher.setTeaPhone(this.teaPhone);
			teacher.setTeaPassword(MD5Utils.MD5(this.teaPassword));
			if(academyId!=null){
				Academy academy=new Academy();
				academy.setAcademyId(academyId);
				teacher.setAcademy(academy);
			}
		} catch (ValidateException e) {
			throw e;
		}
		return teacher;
	}

	@Override
	public Teacher toEditEntity(Teacher teacher) throws ValidateException {
		try {
			BeanValidation.validate(this);
			teacher.setTeaName(this.teaName);
			teacher.setTeaEmail(this.teaEmail);
			teacher.setTeaSex(teaSex);
			teacher.setTeaPhone(this.teaPhone);
			teacher.setTeaPassword(MD5Utils.MD5(this.teaPassword));
			if(academyId!=null){
				Academy academy=new Academy();
				academy.setAcademyId(academyId);
				teacher.setAcademy(academy);
			}
			
		} catch (ValidateException e) {
			throw e;
		}
		return teacher;
	}

	@Override
	public Integer getId() {
		return this.teaId;
	}

}
