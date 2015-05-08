package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.entity.Academy;
import com.ccw.testonline.entity.School;
import com.ccw.testonline.exception.ValidateException;

public class AcademyBean implements BaseBean<Academy> {
	
 	private Integer academyId;

 	@NotNull
    private String academyName;

    private String academyDesc;

    @NotNull
    private Integer schoolId;

	public Integer getAcademyId() {
		return academyId;
	}

	public void setAcademyId(Integer academyId) {
		this.academyId = academyId;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getAcademyDesc() {
		return academyDesc;
	}

	public void setAcademyDesc(String academyDesc) {
		this.academyDesc = academyDesc;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	@Override
	public Academy toAddEntity() throws ValidateException {
		Academy academy = null;
		try {
			BeanValidation.validate(this);
			academy = new Academy();
			academy.setAcademyName(this.academyName);
			academy.setAcademyDesc(this.academyDesc);
			School school=null;
			if(this.schoolId!=null){
				school=new School();
				school.setSchoolId(this.schoolId);
			}
			academy.setSchool(school);
		} catch (ValidateException e) {
			throw e;
		}
		return academy;
	}

	@Override
	public Academy toEditEntity(Academy entity) throws ValidateException {
		try {
			BeanValidation.validate(this);
			entity.setAcademyName(this.academyName);
			entity.setAcademyDesc(this.academyDesc);
			School school=null;
			if(this.schoolId!=null){
				school=new School();
				school.setSchoolId(this.schoolId);
			}
			entity.setSchool(school);
		} catch (ValidateException e) {
			throw e;
		}
		return entity;
	}

	@Override
	public Integer getId() {
		return this.academyId;
	}
	
    
	    
}
